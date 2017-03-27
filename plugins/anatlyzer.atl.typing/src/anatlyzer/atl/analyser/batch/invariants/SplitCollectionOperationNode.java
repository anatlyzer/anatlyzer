package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.SequenceExp;

public class SplitCollectionOperationNode extends AbstractInvariantReplacerNode {

	private List<IInvariantNode> paths;
	private CollectionOperationCallExp expr;

	public SplitCollectionOperationNode(List<IInvariantNode> paths, CollectionOperationCallExp expr) {
		super(null);
		this.paths = paths;
		this.expr = expr;
		this.paths.forEach(p -> p.setParent(this));
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		paths.forEach(p -> p.genErrorSlice(slice));
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		String op = expr.getOperationName();
		
		// Boolean operations
		if ( "isEmpty".equals(op) || "notEmpty".equals(op) ) {
			return InvariantRewritingUtils.combineBoolean(builder, paths, "and");
		}
		
		
		// assume the paths are from collections...
		SequenceExp seq = OCLFactory.eINSTANCE.createSequenceExp();
		for (IInvariantNode iInvariantNode : paths) {
			seq.getElements().add(iInvariantNode.genExpr(builder));
		}
		
		CollectionOperationCallExp flatten = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		flatten.setOperationName("flatten");
		flatten.setSource(seq);
		
		return flatten;
	}

	@Override
	public OclExpression genExprNorm(CSPModel2 builder) {
		return genExpr(builder);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("Split colOp. ", expr), true);
		paths.forEach(p -> {
			p.genGraphviz(gv);
			gv.addEdge(p, this);
		});
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		paths.forEach(n -> n.getTargetObjectsInBinding(elems));
	}

	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		// do nothing
		return null;
	}

	@Override
	public boolean isUsed(InPatternElement e) {
		throw new UnsupportedOperationException();
	}

}
