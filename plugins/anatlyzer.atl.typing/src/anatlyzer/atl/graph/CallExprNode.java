package anatlyzer.atl.graph;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLSerializer;
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
		if ( call instanceof OperationCallExp ) {
			for (OclExpression exp : ((OperationCallExp) call).getArguments()) {
				OclSlice.slice(slice, exp);
			}
		}
		// call.getSource() does not work, why???
		generatedDependencies(slice);
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, call) || problemInArguments(lp) || checkDependenciesAndConstraints(lp);
	}
	
	private boolean problemInArguments(LocalProblem lp) {
		if ( call instanceof OperationCallExp ) {
			for (OclExpression exp : ((OperationCallExp) call).getArguments()) {
				if ( problemInExpressionCached(lp, exp) ) 
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpressionCached(exp, call) || expressionInArguments(exp) || checkDependenciesAndConstraints(exp);
	}

	private boolean expressionInArguments(OclExpression expToCheck) {
		if ( call instanceof OperationCallExp ) {
			for (OclExpression exp : ((OperationCallExp) call).getArguments()) {
				if ( expressionInExpressionCached(expToCheck, exp) ) 
					return true;
			}
		}
		return false;
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, ATLSerializer.serialize(call), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		return genInlining(model, () -> getDepending().genCSP(model, this), (e) -> model.gen(e), false);
	}
	

	/**
	 * 
	 * @param model
	 * @param genDepending
	 * @param copier
	 * @param setLetType The setting of let types is made optional because I am not sure if it impact the USE serialization, 
	 *                   so for the moment let's be conservative in this part
	 * @return
	 */
	protected OclExpression genInlining(CSPModel model, 
			Supplier<OclExpression> genDepending, 
			Function<OclExpression, OclExpression> copier, 
			boolean setLetType) {
		if ( call.isIsStaticCall() ) {
			Callable moduleCallable = call.getStaticResolver();
			
			LetExp topLet = null;
			LetExp innerLet = null;
			
			for(int i = 0; i < moduleCallable.getCallableParameters().size(); i++) {
				EList<CallableParameter> p = moduleCallable.getCallableParameters();
				OclExpression actualParameter = ((OperationCallExp) call).getArguments().get(i);
				VariableDeclaration paramVar  = p.get(i).getParamDeclaration();
				String varName = p.get(i).getName();
				
				LetExp let = model.createLetScope(copier.apply(actualParameter), null, varName);
				if ( setLetType ) let.getVariable().setType(ATLUtils.getOclType(actualParameter.getInferredType()));
				
				VariableDeclaration newVar = let.getVariable();
				model.addToScope(paramVar, newVar);
				
				if ( topLet == null ) {
					topLet = let;					
				} else {
					innerLet.setIn_(let);
				}					
				innerLet = let;
			}
			
			OclExpression inlineCall   = genDepending.get(); // getDepending().genCSP(model);
			if ( topLet != null ) {
				innerLet.setIn_(inlineCall);
				inlineCall = topLet;
			}
			
			return inlineCall;
		} else {
			OclExpression receptorExpr = copier.apply(call.getSource());
			ContextHelper invokedHelper = null;
			if ( call.getDynamicResolvers().size() > 1 ) {
				// throw new UnsupportedOperationException();
				HelperInvocationNode hn = (HelperInvocationNode) getDepending();
				invokedHelper = call.getDynamicResolvers().stream().
					filter(h -> h == hn.getHelper()).
					findFirst().orElseThrow(() -> new IllegalStateException("Expected helper " + call));
				
			} else {
				// This is what was at the beginning, which works fine for cases when
				// there is no polymorphism
				invokedHelper = call.getDynamicResolvers().get(0);
			}
			
			LetExp topLet = genCodeForDynamicResolver(model, genDepending,
					copier, setLetType, receptorExpr, invokedHelper);
			return topLet;
		}
	}

	protected LetExp genCodeForDynamicResolver(CSPModel model,
			Supplier<OclExpression> genDepending,
			Function<OclExpression, OclExpression> copier, boolean setLetType,
			OclExpression receptorExpr, ContextHelper contextHelper) {
		// TODO: There may be several helpers... ??
		LetExp topLet = model.createLetScope(receptorExpr, null, "genSelf");
		for (VariableDeclaration vd : ATLUtils.findSelfReferences(contextHelper)) {
			model.addToScope(vd, topLet.getVariable());
		}

		
		if ( setLetType ) topLet.getVariable().setType(ATLUtils.getOclType(receptorExpr.getInferredType()));

		
		LetExp innerLet = topLet;
		for(int i = 0; i < contextHelper.getCallableParameters().size(); i++) {
			EList<CallableParameter> p = contextHelper.getCallableParameters();
			OclExpression actualParameter = ((OperationCallExp) call).getArguments().get(i);
			VariableDeclaration paramVar  = p.get(i).getParamDeclaration();
			String varName = p.get(i).getName();
			
			LetExp let = model.createLetScope(copier.apply(actualParameter), null, varName);
			if ( setLetType ) let.getVariable().setType(ATLUtils.getOclType(actualParameter.getInferredType()));
			
			VariableDeclaration newVar = let.getVariable();
			model.addToScope(paramVar, newVar);
			
			innerLet.setIn_(let);
			innerLet = let;
		}

		
		OclExpression inlineCall   = genDepending.get(); // getDepending().genCSP(model);
		innerLet.setIn_(inlineCall);
		return topLet;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		return genInlining(model, () -> getDepending().genWeakestPrecondition(model), (expr) -> model.atlCopy(expr), true); 
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
		visitor.visitAfter(this);
	}
	
	
}
