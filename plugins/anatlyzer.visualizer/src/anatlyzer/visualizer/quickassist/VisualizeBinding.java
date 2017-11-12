package anatlyzer.visualizer.quickassist;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;
import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.visualizer.views.ResolveBindingContentProvider;
import anatlyzer.visualizer.views.ResolveBindingLabelProvider;
import anatlyzer.visualizer.views.ResolveBindingView;

public class VisualizeBinding implements AtlQuickAssist {

	private LocatedElement element;
	private AnalysisResult result;

	@Override
	public boolean requiresUserIntervention() {
		return true;
	}

	@Override
	public boolean canExpectUserInteraction() {
		return true;
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
		 Binding container = ATLUtils.getContainer(getElement(), Binding.class);
		 return container != null;
	}

	@Override
	public LocatedElement getElement() {
		return element;
	}

	@Override
	public AnalysisResult getAnalysisResult() {
		return this.result;
	}

	@Override
	public void apply(IDocument document) {
		ResolveBindingView view;
		try {
			view = (ResolveBindingView) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(ResolveBindingView.ID);
			
			// view.setBinding((Binding) ATLUtils.getContainer(getElement(), Binding.class));
			view.setViewData(new ResolveBindingContentProvider(), new ResolveBindingLabelProvider(), ATLUtils.getContainer(getElement(), Binding.class), getAnalysisResult().getAnalyser());
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Point getSelection(IDocument document) {
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Visualize binding";
	}

	@Override
	public String getDisplayString() {
		return "Visualize binding";
	}
	
	@Override
	public Image getImage() {
		return Images.visualize_16x16.createImage();
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}

}
