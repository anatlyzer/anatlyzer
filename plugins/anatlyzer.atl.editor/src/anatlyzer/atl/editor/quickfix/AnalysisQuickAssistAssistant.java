package anatlyzer.atl.editor.quickfix;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.QuickAssistAssistant;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;

public class AnalysisQuickAssistAssistant extends QuickAssistAssistant {
	
	private AtlEditor fEditor;

	public AnalysisQuickAssistAssistant(AtlEditor atlEditor) {
		this.fEditor = atlEditor;
		// IContentAssistProcessor
		// ICompletionProposal 
		this.setQuickAssistProcessor(new AnalysisQuickfixProcessor(fEditor));
		this.setRestoreCompletionProposalSize(EditorsPlugin.getDefault().getDialogSettingsSection("quick_assist_proposal_size")); //$NON-NLS-1$
		this.setInformationControlCreator(getQuickAssistAssistantInformationControlCreator());
		this.enableColoredLabels(true);
		// Events about completion... not needed for the moment
		// addCompletionListener(new ICompletionListener() { .. });
	}
	
	
	/* Copied from AtlSourceViewerConfiguration, because it was private */
	private IInformationControlCreator getQuickAssistAssistantInformationControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent, EditorsPlugin.getAdditionalInfoAffordanceString());
			}
		};
	}	
	
}
