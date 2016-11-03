package anatlyzer.atl.editor;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.quickassist.QuickAssistAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.m2m.atl.adt.ui.text.AtlSourceViewerConfiguration;
import org.eclipse.m2m.atl.adt.ui.text.AtlTextTools;
import org.eclipse.ui.texteditor.ITextEditor;

import anatlyzer.atl.editor.quickfix.AnalysisQuickAssistAssistant;

public class AtlSourceViewerConfigurationExt extends
		AtlSourceViewerConfiguration {

	public AtlSourceViewerConfigurationExt(AtlTextTools tools,
			ITextEditor editor) {
		super(tools, editor);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public IQuickAssistAssistant getQuickAssistAssistant( ISourceViewer sourceViewer) {
		QuickAssistAssistant assistant= new AnalysisQuickAssistAssistant((AtlEditor) this.getEditor());
		return assistant;
	}
	
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		IContentAssistant assistant = super.getContentAssistant(sourceViewer);
		
		return assistant;
	}
	
}
