package anatlyzer.atl.explanations;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.Problem;

public abstract class AbstractAtlExplanation implements AtlProblemExplanation {

	private IMarker marker;

	public void setMarker(IMarker marker) {
		this.marker = marker;		
	};
	
	protected boolean checkProblemType(Class<?> class1) {
		try {
			Object p = marker.getAttribute(AnATLyzerBuilder.PROBLEM); 
			return class1.isInstance( p);
		} catch (CoreException e) {
			return false;
		}
	}

	public Problem getProblem() {
		try {
			return (Problem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}


	public AnalysisResult getAnalysis() {
		try {
			return (AnalysisResult) marker.getAttribute(AnATLyzerBuilder.ANALYSIS_DATA);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}
}
