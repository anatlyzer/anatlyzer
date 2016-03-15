package witness.generator;

import kodkod.engine.Solution.Outcome;

import org.eclipse.emf.ecore.resource.Resource;

public class USEResult {
	private int scope;
	private Outcome outcome;
	private Resource model;
	private boolean satisfyAllInvariants;

	public USEResult(Outcome outcome, boolean satisfyAllInvariants, Resource model, int defaultScope) {
		this.outcome   = outcome;
		this.satisfyAllInvariants = satisfyAllInvariants;
		this.model = model;
		this.scope = defaultScope;
	}
	
	public Resource getModel() {
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
}