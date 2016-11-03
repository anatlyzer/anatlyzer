package anatlyzer.atl.editor.quickfix;

import java.util.List;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atlext.ATL.LocatedElement;

public interface AtlQuickAssistSet {
	public void setCanExpectUserInteraction(boolean b);
	public void setElement(LocatedElement element, AnalysisResult result);

	/**
	 * Checkes whether the quickassist can be applied to the
	 * set element.
	 * 
	 * @return
	 */
	public boolean isApplicable();
	
	/**
	 * If isApplicable == true, it retrieves the list of applicable
	 * quickassist for the element.
	 * @return the list of applicable quickassists
	 */
	public List<AtlQuickAssist> getQuickAssists();




	
}
