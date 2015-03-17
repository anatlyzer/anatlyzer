package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.index.AnalysisResult;

public abstract class QuickfixUtil {
	protected boolean checkProblemType(IMarker marker, Class<?> class1) {
		try {
			Object p = marker.getAttribute(AnATLyzerBuilder.PROBLEM); 
			return class1.isInstance( p);
		} catch (CoreException e) {
			return false;
		}
	}
	
	protected AnalysisResult getAnalyserData(IMarker marker) {
		try {
			return (AnalysisResult) marker.getAttribute(AnATLyzerBuilder.ANALYSIS_DATA);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}		
	}
}
