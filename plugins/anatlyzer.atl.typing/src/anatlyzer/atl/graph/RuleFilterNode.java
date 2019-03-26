package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.OCL.OclExpression;

public class RuleFilterNode implements ConstraintNode {

	private OclExpression	expr;

	public RuleFilterNode(OclExpression expr) {
		this.expr = expr;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, expr);
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return AbstractDependencyNode.problemInExpression(lp, expr);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression subExp) {
		return AbstractDependencyNode.expressionInExpression(subExp, expr);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		gv.addNode(this, ATLSerializer.serialize(expr), true);
	}

	/**
	 * Rule filters should be calculated by Rule's nodes.
	 */
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new IllegalStateException(); 
	}

	
	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		return model.gen(expr);
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void genIdentification(PathId id) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		throw new UnsupportedOperationException();
	}
}
