package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
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
	public void genGraphviz(GraphvizBuffer gv) {
		gv.addNode(this, USESerializer.gen(expr), true);
	}

	/**
	 * Rule filters should be calculated by Rule's nodes.
	 */
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new IllegalStateException(); 
	}

	
	@Override
	public OclExpression genCSP(CSPModel model) {
		return model.gen(expr);
	}

}
