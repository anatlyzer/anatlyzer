package anatlyzer.atl.editor.quickfix.search;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.util.AnalyserUtils;

public class SearchUtil {
	public static boolean isActualProblem(Problem problem, AnalysisResult analysis) {	
		return AnalyserUtils.isConfirmed(problem) || AnalyserUtils.isInternalError(problem); 
	}
		
	public static AbstractAtlQuickfix[] getQuickfixes(Problem problem, AnalysisResult analysis) {
		MockMarker iMarker = new MockMarker(problem, analysis);
		ICompletionProposal[] proposals = AnalysisQuickfixProcessor.getQuickfixes(iMarker);
		AbstractAtlQuickfix[] qfxs = new AbstractAtlQuickfix[proposals.length];
		for (int i = 0; i < proposals.length; i++) {
			qfxs[i] = (AbstractAtlQuickfix) proposals[i];
		}
		return qfxs;
	}
}
