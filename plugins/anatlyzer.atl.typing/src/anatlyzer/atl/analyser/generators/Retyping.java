package anatlyzer.atl.analyser.generators;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.processing.AbstractVisitor;

public class Retyping extends AbstractVisitor {

	public static final String IS_BUILTIN_OPERATION = "IS_BULTIN_OPERATION";
	private RetypingStrategy strategy;
	
	public Retyping(EObject root) {
		strategy = new RetypingToSet(root);
	}	

	public Retyping(EObject root, RetypingStrategy strategy) {
		if ( strategy == null ) {
			strategy = new RetypingToSet();
		}
		strategy.setRoot(root);
		this.strategy = strategy;
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
		return strategy.usesSeqApproximation();
	}
	
	public boolean usesSubtypeSelectionOnFeatureAccess() {
		return strategy.usesSubtypeSelectionOnFeatureAccess();
	}

	public List<Helper> getNewHelpers() {
		return strategy.getNewHelpers();
	}


	
}
