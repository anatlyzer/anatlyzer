package anatlyzer.atl.witness;

import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclExpression;

public class NullStatsCollector implements IFinderStatsCollector {

	@Override
	public void withMainConstraint(OclExpression constraint) { }

	@Override
	public void withPrecondition(OclExpression body) { }

	@Override
	public void withFrameCondition(OclExpression exp) { }

	@Override
	public void withHelper(Helper helper) { }

	@Override
	public void withSolvingTimeNanos(long solvingTime) { }

	@Override
	public long getSolvingTimeNanos() { return 0; }
	
}
