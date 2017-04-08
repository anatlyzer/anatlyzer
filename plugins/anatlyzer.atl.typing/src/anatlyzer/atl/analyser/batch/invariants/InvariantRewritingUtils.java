package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.function.Function;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class InvariantRewritingUtils {

	public static OclExpression combineOperator(CSPModel2 builder, List<IInvariantNode> nodes, String mergeOp, Function<IInvariantNode, OclExpression> gen) {
		OclExpression result = gen.apply(nodes.get(0));
		for(int i = 1; i < nodes.size(); i++) {
			IInvariantNode node = nodes.get(i);
			
			OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
			op.setOperationName(mergeOp);

			op.setSource(result);
			op.getArguments().add(gen.apply(node));
			
			result = op;
		}
		return result;
	}

	public static OclExpression combineUnion(CSPModel2 builder, List<IInvariantNode> nodes, Function<IInvariantNode, OclExpression> gen) {
		final String opName = "union";
		if ( nodes.size() < 2 ) {
			throw new IllegalArgumentException();
		}
		
		CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		colOp.setOperationName(opName);
		colOp.setSource(gen.apply(nodes.get(0)));
		
		for(int i = 1; i < nodes.size(); i++) {
			IInvariantNode node = nodes.get(i);

			colOp.getArguments().add(gen.apply(node));
				
			if ( i + 1 < nodes.size() ) {
				CollectionOperationCallExp tmp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				tmp.setOperationName(opName);
				tmp.setSource(colOp);
				colOp = tmp;									
			}
		}			
		
		return colOp;
		
	}
	
}
