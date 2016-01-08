package anatlyzer.atl.editor.quickfix.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;

public class SearchPath {
	private List<SearchElement> thePath = new ArrayList<SearchElement>();
	private List<SearchEdge> next = new ArrayList<SearchEdge>();
	
	
	public SearchPath add(AtlProblemQuickfix qfx) {
		SearchPath result = new SearchPath();
		result.thePath = new ArrayList<SearchElement>(thePath);
		result.thePath.add(new SearchElement(qfx));		
		next.add(new SearchEdge(qfx, result));
		
		return result;
	}
	
	public List<AtlProblemQuickfix> getAppliedQuickfixes() {
		return thePath.stream().map(e -> e.getQfx()).collect(Collectors.toList());
	}
	
	public List<SearchEdge> getNext() {
		return java.util.Collections.unmodifiableList(next);
	}

	public int size() {
		return thePath.size();
	}
	
	@Override
	public String toString() {
		return "Search path: \n  " + thePath.stream().map(p -> p.toString()).collect(Collectors.joining("\n  "));
	}
	
	public static class SearchEdge {
		private SearchPath next;
		private AtlProblemQuickfix qfx;

		public SearchEdge(AtlProblemQuickfix qfx, SearchPath next) {
			this.qfx = qfx;
			this.next = next;
		}
		
		public AtlProblemQuickfix getQfx() {
			return qfx;
		}
		
		public SearchPath getNext() {
			return next;
		}
	}
}
