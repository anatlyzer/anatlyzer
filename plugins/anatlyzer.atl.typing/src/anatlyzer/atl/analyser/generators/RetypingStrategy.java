package anatlyzer.atl.analyser.generators;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atlext.ATL.Helper;

public interface RetypingStrategy {
	// Must be called before any other method
	void setRoot(EObject root);

	void perform();
	void performOnHelperSignature(Helper h);

	boolean usesSeqApproximation();

	boolean usesSubtypeSelectionOnFeatureAccess();

	List<Helper> getNewHelpers();

	
	public static final NullStrategy NULL = new NullStrategy();
	
	public static class NullStrategy implements RetypingStrategy {

		@Override
		public void setRoot(EObject root) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void perform() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void performOnHelperSignature(Helper h) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean usesSeqApproximation() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean usesSubtypeSelectionOnFeatureAccess() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public List<Helper> getNewHelpers() {
			return Collections.emptyList();
		}
		
	}
}
