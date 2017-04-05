package anatlyzer.atl.analyser.batch.invariants;

import java.util.function.Function;
import java.util.function.Supplier;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atlext.OCL.OclExpression;

public interface IGenChaining {

	public OclExpression genExprChaining(CSPModel2 builder, Function<OclExpression, OclExpression> generator, Supplier<OclExpression> falsePart);
	
	public OclExpression genExprChainingNorm(CSPModel2 builder, Function<OclExpression, OclExpression> generator, Supplier<OclExpression> falsePart);

}
