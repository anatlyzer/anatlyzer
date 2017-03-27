package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class InvariantRewritingUtils {

	public static OclExpression combineBoolean(CSPModel2 builder, List<IInvariantNode> nodes, String mergeOp) {
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
	
}
