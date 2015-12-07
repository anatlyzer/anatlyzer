package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

public interface AtlProblemQuickfix extends ICompletionProposal {

	/**
	 * Checkes whether the quickfix can be applied to the problem described
	 * in the marker.
	 * 
	 * @param marker
	 * @return
	 */
	public boolean isApplicable(IMarker marker) throws CoreException;
	
	/**
	 * Provides the the quickfix with the marker. 
	 * The marker will be the same as the one provided to isApplicable(marker),
	 * but this method will be called after isApplicable.
	 * 
	 * @param marker
	 */
	public void setErrorMarker(IMarker marker);

	/**
	 * @return True when the quick fix will change the meta-model.
	 */
	public boolean isMetamodelChanging();
	
	/**
	 * @return The logical name of the meta-model that will be changed, as given in the ATL transformation.
	 * @throws IllegalStateException if {@link #isMetamodelChanging()} is false.
	 */
	public String getChangedMetamodel();
	
}
