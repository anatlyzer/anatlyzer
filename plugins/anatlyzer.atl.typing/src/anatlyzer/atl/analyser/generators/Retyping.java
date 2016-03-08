package anatlyzer.atl.analyser.generators;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.processing.AbstractVisitor;

public class Retyping extends AbstractVisitor {

	private RetypingStrategy strategy;
	
	public Retyping(EObject root) {
		strategy = new RetypingToSet(root);
	}	
	
	public Retyping perform() {
		strategy.perform();
		return this;
	}

	// Just on the helper signature. Useful for new helpers whose content
	// has already been retyped
	public void performOnHelperSignature(Helper h) {
		strategy.performOnHelperSignature(h);
	}

	public boolean usesSeqApproximation() {
		return ! strategy.usesSeqApproximation();
	}
	
	public boolean usesSubtypeSelectionOnFeatureAccess() {
		return ! strategy.usesSubtypeSelectionOnFeatureAccess();
	}

	public List<Helper> getNewHelpers() {
		return strategy.getNewHelpers();
	}


	
}
