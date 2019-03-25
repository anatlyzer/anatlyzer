package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ErrorUtils;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public abstract class ExpressionProblemNode<P extends LocalProblem> extends AbstractProblemNode<LocalProblem> {

	protected OclExpression	expr;

	public ExpressionProblemNode(P p, OclExpression expr) {
		super(p);
		this.expr = expr;
	}


	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, expr) || checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression subExp) {
		return expressionInExpressionCached(subExp, expr) || checkDependenciesAndConstraints(subExp);
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, expr);
		generatedDependencies(slice);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		String msg = ErrorUtils.getShortError(problem);
		
		gv.addNode(this, msg + "\\n" + ATLSerializer.serialize(expr), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		return model.createBooleanLiteral(true);
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
	public boolean isStraightforward() {
		return true;
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(expr, v) != null 
			|| (getDepending() != null && getDepending().isVarRequiredByErrorPath(v));
	}

	@Override
	public void genIdentification(PathId id) {
		id.next(id.gen(expr));
		followDepending(node -> node.genIdentification(id));
	}
	
}
