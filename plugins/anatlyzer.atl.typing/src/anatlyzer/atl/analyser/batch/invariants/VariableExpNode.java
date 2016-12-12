package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableExp;

public class VariableExpNode extends AbstractInvariantReplacerNode {

	private VariableExp exp;

	public VariableExpNode(VariableExp exp, MatchedRule ctx) {
		super(new ArrayList<IInvariantNode>(), ctx);
		if ( ctx == null )
			throw new IllegalArgumentException("No context for " + exp);
		this.exp = exp;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// nothing to do
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		VariableExp copy = OCLFactory.eINSTANCE.createVariableExp();
//		// TODO: Do variable re-assignment well
		copy.setReferredVariable(exp.getReferredVariable());
		return copy;
	}
	
}
