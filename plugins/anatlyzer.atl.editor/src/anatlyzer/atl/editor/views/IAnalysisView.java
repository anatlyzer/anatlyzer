package anatlyzer.atl.editor.views;

import org.eclipse.jface.viewers.IStructuredSelection;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.errors.Problem;

public interface IAnalysisView {
	public enum Kind {
		UNCONNECTED_ELEMENTS,
		PROBLEM 
	}
	
	Kind getSelectionKind();
	IStructuredSelection getSelection();	
	
	public AtlEditorExt getAssociatedEditor();
	public AnalysisResult getCurrentAnalysis();
	public Result getUnconnectedElementAnalysis();
	public Problem getProblem();
	
	public void refresh();
	
	// void setUnconnectedElementsResult(Result r);
}
