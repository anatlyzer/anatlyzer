package anatlyzer.atl.editor.quickfix.search;

import java.util.List;

public class SearchStrategyAll implements ISearchExpansionStrategy {
	
	private ISearchState initialNode;

	public SearchStrategyAll(ISearchState initial) {
		this.initialNode = initial;
	}	
	
	public void step() {
		List<ISearchEdge> allEdges = initialNode.getAllEdges();
		if ( allEdges.size() == 0 ) {
			initialNode.expand();
		} else {
			allEdges.stream().
				filter(e -> e.getTarget().getNextStates().size() == 0).
				map(e -> e.getTarget()).
				forEach(s -> {
					s.expand();
				});
		}		
	}
	
}
