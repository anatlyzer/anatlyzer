package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclExpression;

public interface IFinderStatsCollector {

	void withMainConstraint(OclExpression constraint);
	void withSolvingTimeNanos(long solvingTime);
	void withPrecondition(OclExpression body);
	void withFrameCondition(OclExpression exp);
	void withHelper(Helper helper);

	public static class DefaultFinderStatsCollector implements IFinderStatsCollector {
		public OclExpression mainConstraint;
		public List<OclExpression> preconditions = new ArrayList<>();
		public List<OclExpression> frameConditions = new ArrayList<>();
		public List<Helper> helpers = new ArrayList<>();
		private long solvingTimeNanos;
		
		@Override
		public void withMainConstraint(OclExpression constraint) {
			this.mainConstraint = copy(constraint);
		}

		private OclExpression copy(OclExpression constraint) {
			return (OclExpression) ATLCopier.copySingleElement(constraint);					
		}

		@Override
		public void withPrecondition(OclExpression body) {
			this.preconditions.add(copy(body));
		}

		@Override
		public void withFrameCondition(OclExpression exp) {
			this.frameConditions.add(copy(exp));			
		}

		@Override
		public void withHelper(Helper helper) {
			this.helpers.add((Helper) ATLCopier.copySingleElement(helper));
		}
		
		@Override
		public void withSolvingTimeNanos(long solvingTime) {
			this.solvingTimeNanos = solvingTime;
		}
		
		public OclExpression getMainConstraint() {
			return mainConstraint;
		}
		
		public List<OclExpression> getPreconditions() {
			return preconditions;
		}
		
		public List<OclExpression> getFrameConditions() {
			return frameConditions;
		}
		
		public List<Helper> getHelpers() {
			return helpers;
		}		
		
		@Override
		public long getSolvingTimeNanos() {
			return this.solvingTimeNanos;
		}
		
	}

	default double getSolvingTimeSeconds() {
		return this.getSolvingTimeNanos() / 1000_000_000.0;
	}
	
	long getSolvingTimeNanos();
	
}
