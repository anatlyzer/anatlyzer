package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
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

public class SplitNode implements IInvariantNode {

	private List<IInvariantNode> paths;
	private CollectionOperationCallExp expr;
	private IInvariantNode parent;

	public SplitNode(List<IInvariantNode> paths, CollectionOperationCallExp expr) {
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
	public SourceContext<RuleWithPattern> getContext() {
		// TODO Auto-generated method stub
		return null;
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
	public void setParent(IInvariantNode node) {
		this.parent = node;
	}
	
	@Override
	public IInvariantNode getParent() {
		return this.parent;
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		throw new UnsupportedOperationException();
	}

}
