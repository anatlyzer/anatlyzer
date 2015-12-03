package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalAnalyser;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.quickfixast.QuickfixApplication;

public class SpeculativeThread extends Thread {
	private AtlProblemQuickfix qfx;
	private AnalysisResult baseAnalysis;
	private AnalysisResult newAnalysis;
	private Problem problem;

	public SpeculativeThread(AnalysisResult baseAnalysis, Problem p, AtlProblemQuickfix qfx) {
		this.qfx = qfx;
		this.baseAnalysis = baseAnalysis;
		this.problem = p;
	}
	
	@Override
	public void run() {
		IncrementalAnalyser inc = new IncrementalAnalyser(baseAnalysis);
		Problem tgtProblem = (Problem) inc.getNewModel().getTarget(problem);
		if ( tgtProblem == null )
			throw new IllegalStateException();
		
		qfx.setErrorMarker(new MockMarker(tgtProblem, new AnalysisResult(inc)));

		QuickfixApplication qfa;
		try {
			qfa = ((AbstractAtlQuickfix) qfx).getQuickfixApplication();
			qfa.apply();
			// What happen if the qfx is meta-model changing?

			inc.perform();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		newAnalysis = new AnalysisResult(inc);
	}
	
	public AnalysisResult getNewAnalysis() {
		return newAnalysis;
	}
}
