package anatlyzer.atl.analyser.batch.invariants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class CollectionOperationCallExpNode extends AbstractInvariantReplacerNode {

	private CollectionOperationCallExp exp;
	private IInvariantNode source;
	private List<IInvariantNode> args;

	public CollectionOperationCallExpNode(IInvariantNode source, CollectionOperationCallExp exp, List<IInvariantNode> args) {
		super(source.getContext());
		this.source = source;
		this.exp = exp;
		this.args = args;
		
		source.setParent(this);
		args.forEach(a -> a.setParent(this));
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		source.genErrorSlice(slice);
		// TODO: Arguments
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
//		// This is a merger node, so get all the parents and put then in a unique collection
//		SetExp colExp = OCLFactory.eINSTANCE.createSetExp();
//		
//		for (IInvariantNode parent : parents) {
//			colExp.getElements().add( parent.genExpr(builder) );
//		}
//		
//		CollectionOperationCallExp flattened = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
//		flattened.setSource(colExp);
//		flattened.setOperationName("flatten");
//		
//		CollectionOperationCallExp copy = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
//		copy.setSource( flattened );
//		copy.setOperationName(exp.getOperationName());
//
//		if ( exp.getArguments().size() != 0 )
//			throw new IllegalStateException();
//
//		return copy;

		CollectionOperationCallExp copy = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		copy.setSource( source.genExpr(builder) );
		copy.setOperationName(exp.getOperationName());
		for (IInvariantNode node : args) {
			copy.getArguments().add(node.genExpr(builder));
		}
		return copy;
	}
	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		CollectionOperationCallExp copy = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		copy.setSource( source.genExprNormalized(builder) );
		copy.setOperationName(exp.getOperationName());
		for (IInvariantNode node : args) {
			copy.getArguments().add(node.genExprNormalized(builder));
		}
		return copy;
	}
	
	@Override
	public List<Iterator> genIterators(CSPModel2 builder, VariableDeclaration optTargetVar) {
		MatchedRule rule = (MatchedRule) context.getRule();
		if ( rule.getInPattern().getElements().size() == 1 ) {
			InPatternElement firstElem = context.getRule().getInPattern().getElements().get(0);
			return Collections.singletonList( createIterator(builder, 
					firstElem, 
					getSuperVars(rule, firstElem.getVarName()),
					optTargetVar));
		} else {
			// See if the code of IteratorExp could be factorized somehow
			throw new IllegalStateException("Multiple iterator propagation for collection operations not supported yet");
		}		
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {				
		gv.addNode(this, "ColOp: " + this.exp.getOperationName(), true);		
		this.source.genGraphviz(gv);
		gv.addEdge(this.source, this);
		args.forEach(a -> { 
			a.genGraphviz(gv); 
			gv.addEdge(a, this); 
		});
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { 
		source.getTargetObjectsInBinding(elems);
	}

	@Override
	public boolean isUsed(InPatternElement e) {
		return source.isUsed(e) || args.stream().anyMatch(a -> a.isUsed(e));
	}
}
