package anatlyzer.atl.editor.quickfix;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

public interface AtlProblemQuickfixSet {

	/**
	 * Checkes whether the quickfix can be applied to the problem described
	 * in the marker.
	 * 
	 * @param marker
	 * @return
	 */
	public boolean isApplicable(IMarker marker);
	
	/**
	 * If isApplicable == true, it retrieves the list of applicable
	 * quickfixes for the marker.
	 * @return the list of applicable quickfixes
	 */
	public List<AtlProblemQuickfix> getQuickfixes(IMarker marker);

}
