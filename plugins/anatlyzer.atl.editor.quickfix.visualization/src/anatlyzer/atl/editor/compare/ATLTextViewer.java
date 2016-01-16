package anatlyzer.atl.editor.compare;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.contentmergeviewer.TextMergeViewer;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.m2m.atl.adt.ui.AtlUIPlugin;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.editor.AtlSourceViewerConfigurationExt;

public class ATLTextViewer extends TextMergeViewer {

	public ATLTextViewer(Composite parent, CompareConfiguration configuration) {
		super(parent, configuration);
	}
	
	
	protected void configureTextViewer(TextViewer textViewer) {
		if(textViewer instanceof ISourceViewer){
			AtlEditorExt editor = new AtlEditorExt();		

			SourceViewerConfiguration configuration= new AtlSourceViewerConfigurationExt(AtlUIPlugin.getDefault().getTextTools(), editor);
			((ISourceViewer)textViewer).configure(configuration);
		}
	}

	@Override
	public String getTitle() {
		return "ATL Compare";
	}
	
}