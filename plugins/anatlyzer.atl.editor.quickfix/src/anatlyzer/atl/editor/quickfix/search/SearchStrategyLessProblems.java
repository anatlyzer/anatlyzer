package anatlyzer.atl.editor.quickfix.search;

import java.util.Optional;

public class SearchStrategyLessProblems implements ISearchExpansionStrategy {
	
	private ISearchState initialNode;

	public SearchStrategyLessProblems(ISearchState initial) {
		this.initialNode = initial;
	}	
	
	public void step() {
		Optional<ISearchState> minNode = initialNode.getAllNextStates().stream().min((s1, s2) -> {
			return Integer.compare(s1.getAnalysisResult().getPossibleProblems().size(), 
					s2.getAnalysisResult().getPossibleProblems().size());			
		});
		
		if ( minNode.isPresent() ) {
			minNode.get().expand();
		} else {
			initialNode.expand();			
		}
	}
	
}
