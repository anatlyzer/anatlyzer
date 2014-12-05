package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.OCL.OclExpression;

public class FlattenOverNonNestedCollectionNode<P extends LocalProblem> extends ExpressionProblemNode<P> {

	public FlattenOverNonNestedCollectionNode(P p, OclExpression expr) {
		super(p, expr);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		super.genErrorSlice(slice);
	}
	
	@Override
	public OclExpression genCSP(CSPModel model) {
		return super.genCSP(model);
	}
	
	@Override
	public boolean isStraightforward() {
		return true;
	}

}
