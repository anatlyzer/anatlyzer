package anatlyzer.atl.analyser.batch.invariants;

import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;

public interface IInvariantNode {

	OclExpression genExpr(CSPModel2 builder);
	Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt);
	
	MatchedRule getContext();

	void genErrorSlice(ErrorSlice slice);
	
	void getTargetObjectsInBinding(Set<OutPatternElement> elems);
	
	void setParent(IInvariantNode node);
	IInvariantNode  getParent();
	
	boolean isUsed(InPatternElement e);
	
}
