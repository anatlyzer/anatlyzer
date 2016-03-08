package anatlyzer.atl.analyser.generators;

import java.util.List;

import anatlyzer.atlext.ATL.Helper;

public interface RetypingStrategy {

	void perform();
	void performOnHelperSignature(Helper h);

	boolean usesSeqApproximation();

	boolean usesSubtypeSelectionOnFeatureAccess();

	List<Helper> getNewHelpers();


}
