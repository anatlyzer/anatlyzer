package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;

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
//			if ( this.nodes.size() == 1 ) {
//				return this.nodes.get(0).genExpr(builder);
//			}
						
			String mergeOp = "and";
			if ( iterator.getName().equals("exists") ) {
				mergeOp = "or";
			}
			else if ( iterator.getName().equals("forAll") ) {
				mergeOp = "and";
			} if ( iterator.getName().equals("one") ) {
				mergeOp = "xor";
			}
			
			OclExpression result = nodes.get(0).genExpr(builder);
			for(int i = 1; i < nodes.size(); i++) {
				IInvariantNode node = nodes.get(i);
				
				OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
				op.setOperationName(mergeOp);

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