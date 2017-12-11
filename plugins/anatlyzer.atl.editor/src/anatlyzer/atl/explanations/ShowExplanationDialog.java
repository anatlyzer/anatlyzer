package anatlyzer.atl.explanations;

import org.eclipse.jface.action.Action;

import anatlyzer.atl.editor.views.AnalysisViewBatchNodes.UnconnectedComponentsAnalysis;
import anatlyzer.atl.editor.views.AnalysisViewBatchNodes.UnconnectedElement;
import anatlyzer.atl.editor.views.IAnalysisView;
import anatlyzer.atl.editor.views.IAnalysisView.Kind;
import anatlyzer.atl.editor.views.IAnalysisViewAction;
import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.Problem;

public class ShowExplanationDialog extends Action implements IAnalysisViewAction {

	private IAnalysisView view;

	public ShowExplanationDialog() {
		setText("Explain problem");
		setToolTipText("Open a dialog with additional explanations about the problem");
		setImageDescriptor(Images.explanation_16x16);
	}
	
	@Override
	public void setAnalysisView(IAnalysisView analysisView) {
		this.view = analysisView;
	}
	
	@Override
	public void run() {
		Kind kind = view.getSelectionKind();
		
		if ( kind == Kind.PROBLEM ) {
			Problem p = view.getProblem();
			AtlProblemExplanation exp = ExplanationFinder.find(p, view.getCurrentAnalysis());
			if ( exp != null ) {
				SimpleExplanationDialog dialog = new SimpleExplanationDialog(null, exp);
				dialog.open();
			}
		} else if ( kind == Kind.UNCONNECTED_ELEMENTS ) {
			Object e = view.getSelection().getFirstElement();
			if ( e instanceof UnconnectedComponentsAnalysis ) {
				// do something
			} else if ( e instanceof UnconnectedElement ) {
				UnconnectedElementExplanation exp = new UnconnectedElementExplanation(((UnconnectedElement) e).getCluster());
				SimpleExplanationDialog dialog = new SimpleExplanationDialog(null, exp);
				dialog.open();				
			}
			
		}
	}
	
	

}
