package anatlyzer.visualizer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.views.IAnalysisView;
import anatlyzer.atl.editor.views.IAnalysisViewAction;
import anatlyzer.visualizer.views.ElementConnectionsView;

public class VisualizeUnconnectedElements extends Action implements IAnalysisViewAction {

	private IAnalysisView analysisView;

	public VisualizeUnconnectedElements() {
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
		try {
			if ( analysisView.getUnconnectedElementAnalysis() != null ) {
				ElementConnectionsView view = (ElementConnectionsView) PlatformUI.getWorkbench().
						getActiveWorkbenchWindow().
						getActivePage().showView(ElementConnectionsView.ID);
				
				view.setAnalysis(analysisView.getUnconnectedElementAnalysis());
			}
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
