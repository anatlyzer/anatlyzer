package anatlyzer.visualizer.quickassist;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.visualizer.views.BindingResolutionInfoView;
import anatlyzer.visualizer.views.ResolveBindingView;

public class VisualizeBinding implements AtlQuickAssist {

	private LocatedElement element;
	private AnalysisResult result;

	public VisualizeBinding() {
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
	public void resetCache() {

	}

	@Override
	public boolean isMetamodelChanging() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getChangedMetamodel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCanExpectUserInteraction(boolean b) {
		// TODO Auto-generated method stub

	}

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
			view.setBinding((Binding) ATLUtils.getContainer(getElement(), Binding.class));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}

}
