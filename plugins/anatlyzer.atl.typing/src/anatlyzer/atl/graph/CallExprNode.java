package anatlyzer.atl.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class CallExprNode extends AbstractDependencyNode {

	private PropertyCallExp	call;

	public CallExprNode(PropertyCallExp start) {
		this.call = start;
	}
	
	public PropertyCallExp getCall() {
		return call;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, call.getSource());
		// call.getSource() does not work, why???
		generatedDependencies(slice);
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpression(lp, call) || checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpression(exp, call) || checkDependenciesAndConstraints(exp);
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, USESerializer.gen(call).asString(), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		// TODO: Parameter passing simply nesting let expressions
		if ( call.isIsStaticCall() ) {
			Callable moduleCallable = call.getStaticResolver();
			
			LetExp topLet = null;
			LetExp innerLet = null;
			
			for(int i = 0; i < moduleCallable.getCallableParameters().size(); i++) {
				EList<CallableParameter> p = moduleCallable.getCallableParameters();
				OclExpression actualParameter = ((OperationCallExp) call).getArguments().get(i);
				VariableDeclaration paramVar  = p.get(i).getParamDeclaration();
				String varName = p.get(i).getName();
				
				LetExp let = model.createLetScope(model.gen(actualParameter), null, varName);

				VariableDeclaration newVar = let.getVariable();
				model.addToScope(paramVar, newVar);
				
				if ( topLet == null ) {
					topLet = let;					
				} else {
					innerLet.setIn_(let);
				}					
				innerLet = let;
			}
			
			OclExpression inlineCall   = getDepending().genCSP(model);
			if ( topLet != null ) {
				innerLet.setIn_(inlineCall);
				inlineCall = topLet;
			}
			
			return inlineCall;
		} else {
			OclExpression receptorExpr = model.gen(call.getSource());
			if ( call.getDynamicResolvers().size() > 1 ) 
				throw new UnsupportedOperationException();
			
			ContextHelper contextHelper = call.getDynamicResolvers().get(0);
			
			// TODO: There may be several helpers... ??
			LetExp topLet = model.createLetScope(receptorExpr, null, "genSelf");
			for (VariableDeclaration vd : findSelfReferences(contextHelper)) {
				model.addToScope(vd, topLet.getVariable());
			}

			LetExp innerLet = topLet;
			for(int i = 0; i < contextHelper.getCallableParameters().size(); i++) {
				EList<CallableParameter> p = contextHelper.getCallableParameters();
				OclExpression actualParameter = ((OperationCallExp) call).getArguments().get(i);
				VariableDeclaration paramVar  = p.get(i).getParamDeclaration();
				String varName = p.get(i).getName();
				
				LetExp let = model.createLetScope(model.gen(actualParameter), null, varName);

				VariableDeclaration newVar = let.getVariable();
				model.addToScope(paramVar, newVar);
				
				innerLet.setIn_(let);
				innerLet = let;
			}

			
			OclExpression inlineCall   = getDepending().genCSP(model);
			innerLet.setIn_(inlineCall);
			return topLet;	
		}
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}

	private List<VariableDeclaration> findSelfReferences(ContextHelper contextHelper) {
		ArrayList<VariableDeclaration> selfs = new ArrayList<VariableDeclaration>();
		TreeIterator<EObject> it = contextHelper.eAllContents();
		while ( it.hasNext() ) {
			EObject atlObj = it.next();
			if ( atlObj instanceof VariableExp && ((VariableExp) atlObj).getReferredVariable().getVarName().equals("self") )
				selfs.add(((VariableExp) atlObj).getReferredVariable());
		}
		return selfs;
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		// For the moment just gathering the enclosing element
		for(DependencyNode n : dependencies) {
			n.genTransformationSlice(slice);
		}					

		Callable callable = call.getStaticResolver();
		if ( callable instanceof Helper) {
			throw new UnsupportedOperationException();
		} else if ( callable instanceof StaticRule) {
			if ( callable instanceof LazyRule) {
				LazyRule r = (LazyRule) callable;
				// For the moment not distinguishing between lazy and normal
				slice.addRule(r);
			} else {
				throw new UnsupportedOperationException("CalledRuleAnn not supported");
			}
		}
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(call, v) != null || getDepending().isVarRequiredByErrorPath(v);
	}
	
	@Override
	public void genIdentification(PathId id) {
		String s = id.gen(call.getSource());
		if ( call instanceof NavigationOrAttributeCallExp ) {
			s += "." + ((NavigationOrAttributeCallExp) call).getName();
		} else if ( call instanceof OperationCallExp ) {		
			s += "." + ((OperationCallExp) call).getOperationName();
			s += "[" + ((OperationCallExp) call).getArguments().stream().map(arg -> id.gen(arg)).collect(Collectors.joining(",")) + "]";			
		}
		
		id.next(s);
		followDepending(node -> node.genIdentification(id));
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
	
	
}
