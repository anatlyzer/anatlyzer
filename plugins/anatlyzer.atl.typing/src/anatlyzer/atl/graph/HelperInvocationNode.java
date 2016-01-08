package anatlyzer.atl.graph;

import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class HelperInvocationNode extends AbstractDependencyNode {

	private Helper	helper;

	public HelperInvocationNode(Helper h) {
		this.helper = h;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		if ( helper.getStaticReturnType() instanceof Metaclass )
			slice.addExplicitMetaclass((Metaclass) helper.getStaticReturnType());

		Type[] args = ATLUtils.getArgumentTypes(helper);
		for (Type type : args) {
			if ( type instanceof Metaclass) slice.addExplicitMetaclass((Metaclass) type);
		}
		
		if ( helper instanceof ContextHelper) {
			ContextHelper h = (ContextHelper) helper;
			if ( h.getContextType() instanceof Metaclass ) 
				slice.addExplicitMetaclass((Metaclass) h.getContextType());
		}
		
		
		generatedDependencies(slice);
	}
	

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		// Problem could be in the rest of the helper structure??
		return problemInExpression(lp, ATLUtils.getBody(helper)) || checkDependenciesAndConstraints(lp);
	}

	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		// Problem could be in the rest of the helper structure??
		return expressionInExpression(exp, ATLUtils.getBody(helper)) || checkDependenciesAndConstraints(exp);
	}

	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, 
				"def:" + (ATLUtils.isContextHelper(helper) ? ATLUtils.getHelperType(helper).getName() + "." : "") + 
				ATLUtils.getHelperName(helper), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {	
		return getDepending().genCSP(model);
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		return getDepending().genWeakestPrecondition(model);
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void genIdentification(PathId id) {
		String s = null;
		if ( helper instanceof ContextHelper) {
			s = PathId.typeSig(ATLUtils.getHelperType(helper));			
		} else {
			s = "TM";
		}
		
		s += "." + ATLUtils.getHelperName(helper) + "[";		
		s += ATLUtils.getHelperArguments(helper).stream().map(p -> PathId.typeSig(p.getType())).collect(Collectors.joining(","));
		s += "]";
		id.next(s);
		followDepending(node -> node.genIdentification(id));
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}

}
