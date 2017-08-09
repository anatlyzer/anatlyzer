package anatlyzer.visualizer.quickassist;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;
import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;

public class VisualizeRuleRelationships implements AtlQuickAssist {

	private LocatedElement element;
	private AnalysisResult result;

	public VisualizeRuleRelationships() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean requiresUserIntervention() {
		return true;
	}

	@Override
	public boolean canExpectUserInteraction() {
		return true;
	}

	@Override
	public void apply(IDocument document) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point getSelection(IDocument document) {
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Visualize rule relationships";
	}

	@Override
	public String getDisplayString() {
		return "Visualize rule relationships";
	}
	
	@Override
	public Image getImage() {
		return Images.visualize_16x16.createImage();
	}

	@Override
	public IContextInformation getContextInformation() {
		return null;
	}

	@Override
	public void resetCache() { }

	@Override
	public boolean isMetamodelChanging() { return false; }


	@Override
	public String getChangedMetamodel() { return null; }

	@Override
	public void setCanExpectUserInteraction(boolean b) { }

	@Override
	public void setElement(LocatedElement elem, AnalysisResult result) {
		this.element = elem;
		this.result = result;
	}

	@Override
	public boolean isApplicable() {
		return getElement() instanceof MatchedRule;
	}

	@Override
	public LocatedElement getElement() {
		return element;
	}

	@Override
	public AnalysisResult getAnalysisResult() {
		return result;
	}

}
