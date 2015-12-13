package anatlyzer.atl.editor.quickfix.search;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.errors.Problem;

public class AbstractSearch {

	protected void baseSearch(AnalysisResult analysis) {
		for (Problem problem : analysis.getProblems()) {
			MockMarker iMarker = new MockMarker(problem, analysis);
			ICompletionProposal[] qfxs = AnalysisQuickfixProcessor.getQuickfixes(iMarker);
			for (ICompletionProposal prop : qfxs) {
				AbstractAtlQuickfix qfx = (AbstractAtlQuickfix) prop;
				baseBranch(problem, qfx, analysis);
			}
		}
	}

	protected void baseBranch(Problem problem, AbstractAtlQuickfix qfx, AnalysisResult baseAnalysis) {
	}
	
}
