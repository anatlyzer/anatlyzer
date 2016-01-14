package anatlyzer.atl.editor.quickfix.search;

import static anatlyzer.atl.editor.quickfix.search.SearchUtil.getQuickfixes;
import static anatlyzer.atl.editor.quickfix.search.SearchUtil.isActualProblem;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.Problem;

public class ExpansionStrategyAll implements ISearchExpansionStrategy {

	@Override
	public List<Expansion> getExpansion(ISearchState state) {
		AnalysisResult analysis = state.getAnalysisResult();

		ArrayList<Expansion> expansion = new ArrayList<ISearchExpansionStrategy.Expansion>();
		for (Problem problem : analysis.getProblems()) {
			if ( ! isActualProblem(problem, analysis) ) 
				continue;
			
			for (AbstractAtlQuickfix prop : getQuickfixes(problem, analysis)) {
				expansion.add(new Expansion(problem, prop));
			}
		}
		return expansion;
	}
		
}
