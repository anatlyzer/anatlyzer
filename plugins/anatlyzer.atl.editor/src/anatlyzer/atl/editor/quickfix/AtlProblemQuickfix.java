package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

public interface AtlProblemQuickfix extends ICompletionProposal {

	/**
	 * Checkes whether the quickfix can be applied to the problem described
	 * in the marker.
	 * 
	 * @param marker
	 * @return
	 */
	public boolean isApplicable(IMarker marker);
	
	/**
	 * Provides the the quickfix with the marker. 
	 * The marker will be the same as the one provided to isApplicable(marker),
	 * but this method will be called after isApplicable.
	 * 
	 * @param marker
	 */
	public void setErrorMarker(IMarker marker);

}
