package anatlyzer.atl.editor.quickfix.search;

import java.util.List;

public class SearchStrategyAll implements ISearchProblemSelectionStrategy {
	
	private ISearchState initialNode;
	private ISearchExpansionStrategy expansionStrategy;

	public SearchStrategyAll(ISearchState initial, ISearchExpansionStrategy expansionStrategy) {
		this.initialNode = initial;
		this.expansionStrategy = expansionStrategy;
	}	
	
	public void step() {
		List<ISearchEdge> allEdges = initialNode.getAllEdges();
		if ( allEdges.size() == 0 ) {
			initialNode.expand(expansionStrategy);
		} else {
			allEdges.stream().
				filter(e -> e.getTarget().getNextStates().size() == 0).
				map(e -> e.getTarget()).
				forEach(s -> {
					s.expand(expansionStrategy);
				});
		}		
	}
	
}
