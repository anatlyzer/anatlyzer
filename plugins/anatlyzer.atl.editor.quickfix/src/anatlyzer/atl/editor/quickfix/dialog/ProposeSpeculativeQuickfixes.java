package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixAction;
import anatlyzer.atl.editor.quickfix.QuickfixDialog;
import anatlyzer.atl.editor.views.AnalysisView;
import anatlyzer.atl.editor.views.IAnalysisView;
import anatlyzer.atl.editor.views.IAnalysisViewAction;
import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.Problem;

public class ProposeSpeculativeQuickfixes extends Action implements IAnalysisViewAction {

	private IAnalysisView analysisView;

	public ProposeSpeculativeQuickfixes() {
		setText("Quick fix (speculative)");
		setToolTipText("Propose speculative quick fixes");
		setImageDescriptor(Images.quickfix_16x16);
	}

	@Override
	public void setAnalysisView(IAnalysisView analysisView) {
		this.analysisView = analysisView;
	}

	@Override
	public void run() {
		// Problem selection = analysisView.getProblem();
		
		new QuickfixAction(analysisView) {
			
			@Override
			protected Dialog createDialog(List<AtlProblemQuickfix> quickfixesList) {
				return new SpeculativeQuickfixDialog(analysisView.getAssociatedEditor().getSite().getShell(), 
						analysisView.getCurrentAnalysis(),
						analysisView.getProblem(),
						quickfixesList);
			}

			@Override
			protected AtlProblemQuickfix getSelected(Dialog dialog) {
				return ((SpeculativeQuickfixDialog) dialog).getQuickfix();
			}
		}.run();;
	}
}
