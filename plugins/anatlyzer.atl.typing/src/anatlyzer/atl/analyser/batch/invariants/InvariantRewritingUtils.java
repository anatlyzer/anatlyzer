package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.function.Function;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class InvariantRewritingUtils {

	public static OclExpression combineBoolean(CSPModel2 builder, List<IInvariantNode> nodes, String mergeOp, Function<IInvariantNode, OclExpression> gen) {
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
	
}
