package anatlyzer.atl.explanations;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

public interface AtlProblemExplanation {

	public void setMarker(IMarker marker);
	
	/**
	 * Checks whether the quick fix can be applied to the problem described
	 * in the marker.
	 * @return if it is applicable
	 */
	public boolean isApplicable();
	
	/**
	 * @return A detailed description of the problem.
	 */
	public void setDetailedProblemDescription(StyledText text);

	public void setAdditionalInfo(Composite scrolledComposite);
	
}
