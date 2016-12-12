package anatlyzer.atl.analyser.batch.invariants;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.OclExpression;

public interface IInvariantNode {

	OclExpression genExpr(CSPModel builder);
	
	MatchedRule getContext();

	void genErrorSlice(ErrorSlice slice);
	
}
