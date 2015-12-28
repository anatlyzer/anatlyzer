package anatlyzer.atl.editor.quickfix;

import java.util.Collections;

import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.quickfixast.QuickfixApplication;

public class SpeculativeQuickfixUtils {
	
	/**
	 * It modifies the given quickfix... 
	 * 
	 * @param baseAnalysis
	 * @param problem
	 * @param qfx
	 * @return
	 */
	public static IncrementalCopyBasedAnalyser createIncrementalAnalyser(AnalysisResult baseAnalysis, Problem problem, AtlProblemQuickfix qfx) {
		IncrementalCopyBasedAnalyser inc = null;
		/*
		if ( qfx.isMetamodelChanging() ) {
			inc = new IncrementalAnalyser(baseAnalysis, Collections.singletonList(qfx.getChangedMetamodel()));			
		} else {
			inc = new IncrementalAnalyser(baseAnalysis);
		}
		*/
		
		inc = new IncrementalCopyBasedAnalyser(baseAnalysis, baseAnalysis.getNamespace().getLogicalNamesToMetamodels().keySet());
		
		Problem tgtProblem = (Problem) inc.getNewModel().getTarget(problem);
		if ( tgtProblem == null )
			throw new IllegalStateException();
		
		
		qfx.resetCache();
		
		MockMarker mock = new MockMarker(tgtProblem, new AnalysisResult(inc));
		try {
			if ( ! qfx.isApplicable(mock) ) {
				throw new IllegalStateException();
			}
		} catch (CoreException e1) {
			throw new IllegalStateException();
		}
		
		qfx.setErrorMarker(mock);

		QuickfixApplication qfa;
		try {
			qfa = ((AbstractAtlQuickfix) qfx).getQuickfixApplication();
			if ( qfa == null ) {
				throw new IllegalStateException("No actual implementation for quick fix: " + qfx);
			}
			qfa.apply();
			// What happen if the qfx is meta-model changing?

			// The analysis is not executed, delegated to the client
			// inc.perform();
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return inc;
	}
}
