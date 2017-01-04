package anatlyzer.atl.analyser.batch.invariants;

import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;

public interface IInvariantNode {

	OclExpression genExpr(CSPModel builder);
	Pair<LetExp, LetExp> genIteratorBindings(CSPModel builder, Iterator it);
	
	MatchedRule getContext();

	void genErrorSlice(ErrorSlice slice);
	
	void getTargetObjectsInBinding(Set<OutPatternElement> elems);

	
}
