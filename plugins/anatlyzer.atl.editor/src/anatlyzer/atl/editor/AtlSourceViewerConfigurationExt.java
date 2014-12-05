package anatlyzer.atl.editor;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.quickassist.QuickAssistAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.m2m.atl.adt.ui.text.AtlSourceViewerConfiguration;
import org.eclipse.m2m.atl.adt.ui.text.AtlTextTools;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.ITextEditor;

import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;

public class AtlSourceViewerConfigurationExt extends
		AtlSourceViewerConfiguration {

	public AtlSourceViewerConfigurationExt(AtlTextTools tools,
			ITextEditor editor) {
		super(tools, editor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IQuickAssistAssistant getQuickAssistAssistant( ISourceViewer sourceViewer) {
		QuickAssistAssistant assistant= new QuickAssistAssistant();
		assistant.setQuickAssistProcessor(new AnalysisQuickfixProcessor());
		assistant.setRestoreCompletionProposalSize(EditorsPlugin.getDefault().getDialogSettingsSection("quick_assist_proposal_size")); //$NON-NLS-1$
		assistant.setInformationControlCreator(getQuickAssistAssistantInformationControlCreator());

		return assistant;
	}
	
	/* Copied from superclass, becuase it was private */
	private IInformationControlCreator getQuickAssistAssistantInformationControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent, EditorsPlugin.getAdditionalInfoAffordanceString());
			}
		};
	}
}
