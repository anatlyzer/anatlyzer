package anatlyzer.atl.editor.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.window.Window;

import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.editor.views.IAnalysisView;
import anatlyzer.atl.errors.Problem;

public abstract class QuickfixAction extends Action {
	private IAnalysisView analysisView;
	public QuickfixAction(IAnalysisView view) {
		this.analysisView = view;
	}
	
	public void run() {
		Problem selection = analysisView.getProblem();
		showQuickfixDialog(selection);
	}

	protected void showQuickfixDialog(Problem p) {
		ICompletionProposal[] quickfixes = (ICompletionProposal[]) AnalysisQuickfixProcessor.getQuickfixes(new MockMarker(p, analysisView.getCurrentAnalysis()) );
		List<AtlProblemQuickfix> quickfixesList = new ArrayList<AtlProblemQuickfix>();
		for (ICompletionProposal prop : quickfixes) {
			quickfixesList.add((AtlProblemQuickfix) prop);
		}
						
		Dialog dialog = createDialog(quickfixesList);
		if ( dialog.open() == Window.OK ) {
			AtlProblemQuickfix qf = getSelected(dialog);
			AtlEditorExt editor = analysisView.getAssociatedEditor();
			if ( editor != null ) {
				IDocument doc = editor.getDocumentProvider().getDocument(editor.getEditorInput());
				qf.apply(doc);
			}
		}
	}

	protected abstract Dialog createDialog(List<AtlProblemQuickfix> quickfixesList);
	protected abstract AtlProblemQuickfix getSelected(Dialog dialog);

}
