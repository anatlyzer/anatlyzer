package anatlyzer.atl.editor.quickfix.search;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;

public class AbstractSearch {
	protected IWitnessFinder finder;
	protected AnalysisResult analysis;

	public AbstractSearch(IWitnessFinder finder, AnalysisResult analysis) {
		this.finder = finder;
		this.analysis = analysis;
		// The first thing is to make sure that all problems are properly confirmed or discarded
		confirmOrDiscardProblems(analysis);
	}

	protected void baseSearch(AnalysisResult analysis) {
		for (Problem problem : analysis.getProblems()) {
			if ( ! isActualProblem(problem, analysis) ) 
				continue;
			
			MockMarker iMarker = new MockMarker(problem, analysis);
			ICompletionProposal[] qfxs = AnalysisQuickfixProcessor.getQuickfixes(iMarker);
			for (ICompletionProposal prop : qfxs) {
				AbstractAtlQuickfix qfx = (AbstractAtlQuickfix) prop;
				baseBranch(problem, qfx, analysis);
			}
		}
	}

	protected boolean isActualProblem(Problem problem, AnalysisResult analysis) {	
		return AnalyserUtils.isConfirmed(problem) || AnalyserUtils.isInternalError(problem); 
	}

	protected void confirmOrDiscardProblems(AnalysisResult analysis) {
		for (Problem problem : analysis.getProblems()) {
			if ( problem.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {
				ProblemStatus status = finder.catchInternalErrors(true).find(problem, analysis);
				problem.setStatus(status);
			}			
		
		}
	}
	
	protected void baseBranch(Problem problem, AbstractAtlQuickfix qfx, AnalysisResult baseAnalysis) {
	}
	
}
