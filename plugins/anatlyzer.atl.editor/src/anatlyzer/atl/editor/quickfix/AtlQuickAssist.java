package anatlyzer.atl.editor.quickfix;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atlext.ATL.LocatedElement;

public interface AtlQuickAssist extends ICompletionProposal, AtlCompletionProposal {

	public void setCanExpectUserInteraction(boolean b);
	public void setElement(LocatedElement elem, AnalysisResult result);
	
	/**
	 * Checks whether the quickassist can be applied to the element
	 * passed in setElement.
	 * 
	 * @return
	 */
	public boolean isApplicable();
	
	public LocatedElement getElement();
	
	public AnalysisResult getAnalysisResult();
	
}
