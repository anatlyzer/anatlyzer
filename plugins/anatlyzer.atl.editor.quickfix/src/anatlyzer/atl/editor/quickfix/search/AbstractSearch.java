package anatlyzer.atl.editor.quickfix.search;

import static anatlyzer.atl.editor.quickfix.search.SearchUtil.getQuickfixes;
import static anatlyzer.atl.editor.quickfix.search.SearchUtil.isActualProblem;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.search.ISearchExpansionStrategy.Expansion;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
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
	/**
	 * This implements the expansion strategy that just expands all problems and quick fixes.
	 * @param analysis
	 */
	protected void baseSearch(AnalysisResult analysis) {
		for (Problem problem : analysis.getProblems()) {
			if ( ! isActualProblem(problem, analysis) ) 
				continue;
			
			for (ICompletionProposal prop : getQuickfixes(problem, analysis)) {
				AbstractAtlQuickfix qfx = (AbstractAtlQuickfix) prop;
				baseBranch(problem, qfx, analysis);
			}
		}
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
