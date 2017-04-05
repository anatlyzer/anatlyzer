package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.graph.ConstraintNode;
import anatlyzer.atl.graph.DependencyNode;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public interface IInvariantNode {

	OclExpression genExpr(CSPModel2 builder);
	Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt);
	
	SourceContext<? extends RuleWithPattern> getContext();

	void genErrorSlice(ErrorSlice slice);
	
	void getTargetObjectsInBinding(Set<OutPatternElement> elems);
	
	void setParent(IInvariantNode node);
	IInvariantNode  getParent();
	
	boolean isUsed(InPatternElement e);

	
	void genGraphviz(GraphvizBuffer<IInvariantNode> gv);

	OclExpression genExprNormalized(CSPModel2 builder);
	
	// This is for genExprNorm
	List<Iterator> genIterators(CSPModel2 builder,	VariableDeclaration optTargetVar);
}
