package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.MultiNode;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class SplitNodeOperation extends AbstractInvariantReplacerNode {

	private List<IInvariantNode> paths;
	private OperationCallExp expr;

	public SplitNodeOperation(List<IInvariantNode> paths, OperationCallExp expr) {
		super(null);
		this.paths = paths;
		this.expr = expr;
		paths.forEach(p -> {
			if ( p instanceof MultiNode ) {
				throw new IllegalStateException("Multinode found: " + expr.getLocation());
			}
		});
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		paths.forEach(p -> p.genErrorSlice(slice));
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		Assert.isTrue(paths.size() > 1);
		
		// Asume the operation is a boolean expression
		
		OclExpression result = paths.get(0).genExpr(builder);
		for(int i = 1; i < paths.size(); i++) {
			IInvariantNode node = paths.get(i);
			
			OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
			// op.setOperationName("or");
			op.setOperationName("and"); // Every possible path must be true

			op.setSource(result);
			op.getArguments().add(node.genExpr(builder));
			
			result = op;
		}
		
		return result;
	}
	
	@Override
	public OclExpression genExprNorm(CSPModel2 builder) {
		return genExpr(builder);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("Split operation node. ", expr), true);
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
		// Do nothing
		return null;
	}
		
	@Override
	public boolean isUsed(InPatternElement e) {
		throw new UnsupportedOperationException();
	}

	
}