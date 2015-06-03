package anatlyzer.atl.util;

import java.util.function.Supplier;
import java.util.stream.Collector;

import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

public class ExpressionJoiner {
	private OclExpression current;
	private Supplier<OperationCallExp> joinOp;

	public ExpressionJoiner(Supplier<OperationCallExp> joinOp) {
		this.joinOp  = joinOp;
	}
	
	public ExpressionJoiner add(OclExpression next) {
		if ( current != null ) {
				OperationCallExp call = joinOp.get();
			call.setSource(next);
			call.getArguments().add(current);
			this.current = call;
		} else {
			this.current = next;
		}
		return this;
	}
	
    public ExpressionJoiner merge(ExpressionJoiner other) {
    	if ( other.current != null ) {
    		add(other.current);
    	}
    	return this;
    }
    
    public OclExpression toOclExpression() {
    	return current;
    }

    public static Collector<OclExpression, ?, OclExpression> joining(Supplier<OperationCallExp> joinOp) {
    	return Collector.<OclExpression, ExpressionJoiner, OclExpression>of(
    			() -> new ExpressionJoiner(joinOp), 
    			ExpressionJoiner::add, 
    			ExpressionJoiner::merge,
    			ExpressionJoiner::toOclExpression, new Collector.Characteristics[0] );
    }

}
