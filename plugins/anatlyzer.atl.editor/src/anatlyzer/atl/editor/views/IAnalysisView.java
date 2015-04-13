package anatlyzer.atl.editor.views;

import org.eclipse.jface.viewers.IStructuredSelection;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.errors.Problem;

public interface IAnalysisView {
	public enum Kind {
		UNCONNECTED_ELEMENTS,
		PROBLEM 
	}
	
	Kind getSelectionKind();
	IStructuredSelection getSelection();	
	
	public Result getUnconnectedElementAnalysis();
	public Problem getProblem();
	
}
