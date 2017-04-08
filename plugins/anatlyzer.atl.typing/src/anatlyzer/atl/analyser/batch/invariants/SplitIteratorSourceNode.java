package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.SequenceExp;

public class SplitIteratorSourceNode extends AbstractInvariantReplacerNode {

		private List<IInvariantNode> nodes;
		private IteratorExp iterator;

		public SplitIteratorSourceNode(IteratorExp expr, List<IInvariantNode> resolutions) {
			super(null);
			this.iterator = expr;
			this.nodes = resolutions;
			this.nodes.forEach(n -> n.setParent(this));
		}
		
		@Override
		public void genErrorSlice(ErrorSlice slice) {
			this.nodes.forEach(n -> n.genErrorSlice(slice));
		}
		
		@Override
		public OclExpression genExpr(CSPModel2 builder) {
			return genAux(builder, (node) -> node.genExpr(builder));
		}
		
		@Override
		public OclExpression genExprNormalized(CSPModel2 builder) {
			return genAux(builder, (node) -> node.genExprNormalized(builder));
		}
		
		public OclExpression genAux(CSPModel2 builder, Function<IInvariantNode, OclExpression> gen) {
			String name = iterator.getName();
			
			String mergeOp = "and";
			if ( name.equals("exists") ) {
				mergeOp = "or";
			}
			else if ( name.equals("forAll") ) {
				mergeOp = "and";
			} else if ( name.equals("one") ) {				
				return mergeOne(gen);
			} else if ( name.equals("select") || name.equals("reject") || name.equals("collect") ) {				
				return InvariantRewritingUtils.combineUnion(builder, nodes, gen);
			} 
			
			
			return InvariantRewritingUtils.combineOperator(builder, nodes, mergeOp, gen);
		}
		
		// <split-exp>->one(x | p(x)) => <path1>->select(x|p(x))->union(...)->size() = 1
		private OclExpression mergeOne( Function<IInvariantNode, OclExpression> gen) {
			
			CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			
			for(int i = 0; i < nodes.size(); i++) {
				IInvariantNode node = nodes.get(i);
				
				OclExpression exp = gen.apply(node);
				if ( ! (exp instanceof IteratorExp) && !(((IteratorExp) exp).getName().equals("one")) ) {
					throw new IllegalStateException();
				}
				
				IteratorExp oneToSelect = (IteratorExp) exp;
				oneToSelect.setName("select");				

				if ( i == 0 ) {
					colOp.setSource(oneToSelect);
				} else {
					colOp.setOperationName("union");
					colOp.getArguments().add(oneToSelect);
					
					CollectionOperationCallExp tmp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
					tmp.setSource(colOp);
					colOp = tmp;					
				}
			}			
			
			colOp.setOperationName("size");
			
			OperatorCallExp eqCall = OCLFactory.eINSTANCE.createOperatorCallExp();
			eqCall.setOperationName("=");
			eqCall.setSource(colOp);
			IntegerExp integer = OCLFactory.eINSTANCE.createIntegerExp();
			integer.setIntegerSymbol(1);
			eqCall.getArguments().add(integer);
			
			return eqCall;
		}

		@Override
		public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
			gv.addNode(this, gvText("Split iterator. ", iterator), true);
			nodes.forEach(p -> {
				p.genGraphviz(gv);
				gv.addEdge(p, this);
			});
		}
		
		@Override
		public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { 
			this.nodes.forEach(n -> n.getTargetObjectsInBinding(elems));
		}
		
		@Override
		public boolean isUsed(InPatternElement e) {
			return this.nodes.stream().anyMatch(a -> a.isUsed(e));
		}

	}