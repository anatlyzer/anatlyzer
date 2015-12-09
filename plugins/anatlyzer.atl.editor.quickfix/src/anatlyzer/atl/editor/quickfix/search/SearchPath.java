package anatlyzer.atl.editor.quickfix.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;

public class SearchPath {
	private List<String> thePath = new ArrayList<String>();
	
	public SearchPath add(AtlProblemQuickfix qfx) {
		SearchPath result = new SearchPath();
		result.thePath = new ArrayList<String>(thePath);
		result.thePath.add(qfx.getDisplayString() + " " + qfx.getClass().getSimpleName());
		return result;
	}

	public int size() {
		return thePath.size();
	}
	
	@Override
	public String toString() {
		return "Search path: \n  " + thePath.stream().collect(Collectors.joining("\n  "));
	}
}
