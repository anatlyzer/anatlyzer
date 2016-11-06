package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;

public interface AtlProblemQuickfix extends ICompletionProposal, AtlCompletionProposal {

	public static final String GUI_MODE_ATTR = "GUI_MODE";

	/**
	 * Checks whether the quickfix can be applied to the problem described
	 * in the marker.
	 * 
	 * @param marker
	 * @return
	 */
	public boolean isApplicable(IMarker marker) throws CoreException;
	
	/**
	 * @return true when the quick fix cannot be automatically applied 
	 * without asking the user some additional information (i.e., with a dialog).
	 */
	public boolean requiresUserIntervention();
	
	/**
	 * @return true when the quick fix can run in GUI mode, meaning that it can
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
	 * Provides the the quickfix with the marker. 
	 * The marker will be the same as the one provided to isApplicable(marker),
	 * but this method will be called after isApplicable.
	 * 
	 * @param marker
	 */
	public void setErrorMarker(IMarker marker);

	/**
	 * @return The current error marker.
	 */
	public IMarker getErrorMarker();
	
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
	 * @return The problem associated to the quick fix.
	 * @throws CoreException if 
	 */
	public Problem getProblem() throws CoreException;

	
	/**
	 * This is used to set external data inside the quick fix.
	 */
	public void setData(Object key, Object value);
	
	public Object getData(Object key);
}
