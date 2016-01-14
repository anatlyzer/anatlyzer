package anatlyzer.atl.editor.quickfix.search;

import static anatlyzer.atl.editor.quickfix.search.SearchUtil.getQuickfixes;
import static anatlyzer.atl.editor.quickfix.search.SearchUtil.isActualProblem;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ChangeMetamodel;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;

public class ExpansionStrategyMoreFixed implements ISearchExpansionStrategy {

	@Override
	public List<Expansion> getExpansion(ISearchState state) {
		AnalysisResult analysis = state.getAnalysisResult();
		
		ArrayList<Expansion> expansion = new ArrayList<ISearchExpansionStrategy.Expansion>();
		for (Problem problem : analysis.getProblems()) {
			if ( ! isActualProblem(problem, analysis) ) 
				continue;
		
			if ( problem instanceof AccessToUndefinedValue ) {
				expansion.add(selectQfx((AccessToUndefinedValue) problem, analysis));
				return expansion;
			} else {			
				for (AbstractAtlQuickfix prop : getQuickfixes(problem, analysis)) {
					expansion.add(new Expansion(problem, prop));
					return expansion;
				}
			}
		}
		return expansion;
	}

	private Expansion selectQfx(AccessToUndefinedValue problem, AnalysisResult analysis) {
		AbstractAtlQuickfix one = null;
		for (AbstractAtlQuickfix prop : getQuickfixes(problem, analysis)) {
			if ( prop instanceof AccessToUndefinedValue_ChangeMetamodel ) {
				return new Expansion(problem, prop);				
			}
			one = prop;
		}		
		return new Expansion(problem, one);
	}
		
}
