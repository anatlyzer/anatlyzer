package anatlyzer.atl.editor.quickfix;

import anatlyzer.atl.analyser.AnalysisResult;

/**
 * Common interface for AtlProblemQuickfix and AtlQuickAssist
 * 
 * @author jesus
 *
 */
public interface AtlCompletionProposal {
	/**
	 * @return true when the quick fix cannot be automatically applied 
	 * without asking the user some additional information (i.e., with a dialog).
	 */
	public boolean requiresUserIntervention();
	
	/**
	 * @return true when the quick assist can run in GUI mode, meaning that it can
	 * show dialogs and expect user interaction.
	 */
	public boolean canExpectUserInteraction();
	
	/**
	 * This method is needed to implement speculative quick fixes when isApplicable is not side-effect free.
	 * A speculative quick fix has two calls to isApplicable and setErrorMarker, first with the original 
	 * problem, and then with a copy which is ultimately the problem over which the quick fix will be appplied. 
	 * In the middle, resetCache is called.
	 */
	public void resetCache();
	
	/**
	 * @return True when the quick fix will change the meta-model.
	 */
	public boolean isMetamodelChanging();
	
	/**
	 * @return The logical name of the meta-model that will be changed, as given in the ATL transformation.
	 * @throws IllegalStateException if {@link #isMetamodelChanging()} is false.
	 */
	public String getChangedMetamodel();
	
	/**
	 * Return the analysis over which the quick fix is applied.
	 */
	public AnalysisResult getAnalysisResult();

}
