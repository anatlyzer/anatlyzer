package witness.generator;

import kodkod.engine.Solution.Outcome;
import anatlyzer.atl.witness.IWitnessModel;

public class USEResult {
	private int scope;
	private Outcome outcome;
	private IWitnessModel model;
	private boolean satisfyAllInvariants;
	private long time = -1;
	
	public USEResult(Outcome outcome, boolean satisfyAllInvariants, IWitnessModel model, int defaultScope) {
		this.outcome   = outcome;
		this.satisfyAllInvariants = satisfyAllInvariants;
		this.model = model;
		this.scope = defaultScope;
	}
	
	public IWitnessModel getModel() {
		return model;
	}
	
	public int getDefaultScope() {
		return scope;
	}
	
	public boolean isSatisfiable() {
		return isSatisfiable(outcome, satisfyAllInvariants);
	}
	
	public static boolean isSatisfiable(Outcome outcome, boolean satisfyAllInvariants) {
		return satisfyAllInvariants && 
				outcome == Outcome.SATISFIABLE ||
				outcome == Outcome.TRIVIALLY_SATISFIABLE;
	}

	public boolean isDiscarded() {
		return satisfyAllInvariants && 
				outcome == Outcome.UNSATISFIABLE ||
				outcome == Outcome.TRIVIALLY_UNSATISFIABLE;
	}
	
	public boolean isUnsupported() {
		return ! satisfyAllInvariants;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}
}