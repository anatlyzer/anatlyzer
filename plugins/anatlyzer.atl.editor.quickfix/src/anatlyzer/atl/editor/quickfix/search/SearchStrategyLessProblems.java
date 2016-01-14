package anatlyzer.atl.editor.quickfix.search;

import java.util.Optional;

public class SearchStrategyLessProblems implements ISearchProblemSelectionStrategy {
	
	private ISearchState initialNode;
	private ISearchExpansionStrategy expansionStrategy;

	public SearchStrategyLessProblems(ISearchState initial, ISearchExpansionStrategy expansionStrategy) {
		this.initialNode = initial;
		this.expansionStrategy = expansionStrategy;
	}	
	
	public void step() {
		Optional<ISearchState> minNode = initialNode.getAllNextStates().stream().min((s1, s2) -> {
			return Integer.compare(s1.getAnalysisResult().getPossibleProblems().size(), 
					s2.getAnalysisResult().getPossibleProblems().size());			
		});
		
		if ( minNode.isPresent() ) {
			minNode.get().expand(expansionStrategy);
		} else {
			initialNode.expand(expansionStrategy);			
		}
	}
	
}
