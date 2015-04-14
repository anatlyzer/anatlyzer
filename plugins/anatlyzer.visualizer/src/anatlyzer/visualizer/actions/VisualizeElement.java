package anatlyzer.visualizer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.views.IAnalysisView;
import anatlyzer.atl.editor.views.IAnalysisView.Kind;
import anatlyzer.atl.editor.views.IAnalysisViewAction;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.visualizer.views.BindingResolutionInfoView;
import anatlyzer.visualizer.views.ElementConnectionsView;

public class VisualizeElement extends Action implements IAnalysisViewAction {

	private IAnalysisView analysisView;

	public VisualizeElement() {
		setText("Visualize");
		setToolTipText("Visualize element connections");
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_DEF_VIEW));		
	}

	@Override
	public void setAnalysisView(IAnalysisView analysisView) {
		this.analysisView = analysisView;
	}
	
	@Override
	public void run() {
		Kind kind = analysisView.getSelectionKind();
		
		if ( kind == Kind.UNCONNECTED_ELEMENTS ) {
			visualizeUnconnectedElements(); 
		} else if ( kind == Kind.PROBLEM ) {
			visualizeProblem(analysisView.getProblem());
		}
	}
	
	private void visualizeProblem(Problem p) {
		if ( p instanceof BindingResolution ) {
			try {
				BindingResolutionInfoView view = (BindingResolutionInfoView) PlatformUI
						.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().showView(BindingResolutionInfoView.ID);

				view.setProblem((BindingResolution) p);
			} catch (PartInitException e) {
				e.printStackTrace();
			}			
		}
	}

	public void visualizeUnconnectedElements() {
		try {
			if ( analysisView.getUnconnectedElementAnalysis() != null ) {
				ElementConnectionsView view = (ElementConnectionsView) PlatformUI.getWorkbench().
						getActiveWorkbenchWindow().
						getActivePage().showView(ElementConnectionsView.ID);
				
				view.setAnalysis(analysisView.getUnconnectedElementAnalysis());
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
