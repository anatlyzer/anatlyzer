package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atlext.OCL.PropertyCallExp;

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
	
	public Type determineReturnType(PropertyCallExp pcall) {
		Unknown anyType = TypesFactory.eINSTANCE.createUnknown();
		return anyType;		
	}
}
