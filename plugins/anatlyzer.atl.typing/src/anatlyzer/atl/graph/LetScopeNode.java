package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class LetScopeNode extends AbstractDependencyNode {

	private LetExp let;

	public LetScopeNode(LetExp let) {
		this.let = let;
	}


	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, let.getVariable().getInitExpression());
		OclSlice.slice(slice, let.getIn_());
		generatedDependencies(slice);
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return 	problemInExpression(lp, let.getVariable().getInitExpression()) ||
				problemInExpression(lp, let.getIn_()) ||
				checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return 	expressionInExpression(exp, let.getVariable().getInitExpression()) ||
				expressionInExpression(exp, let.getIn_()) ||
				checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "let " + let.getVariable().getVarName() + " = ...", leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		// return model.gen(let);
		OclExpression varInit = model.gen(let.getVariable().getInitExpression());
		LetExp genLet = model.createLetScope(varInit, null, let.getVariable().getVarName());

		model.addToScope(let.getVariable(), genLet.getVariable());
		OclExpression dep = getDepending().genCSP(model);
		genLet.setIn_(dep);
		return genLet;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(let, v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}

}
