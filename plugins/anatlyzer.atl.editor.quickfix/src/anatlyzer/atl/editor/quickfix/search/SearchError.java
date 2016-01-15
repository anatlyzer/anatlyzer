package anatlyzer.atl.editor.quickfix.search;

import java.util.Collections;
import java.util.List;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;

public class SearchError implements ISearchState {

	private SearchPath path;
	private Throwable error;

	public SearchError(SearchPath path, Throwable e) {
		this.path = path;
		this.error = e;
	}

	public Throwable getError() {
		return error;
	}
	
	@Override
	public SearchPath getPath() {
		return path;
	}

	@Override
	public AnalysisResult getAnalysisResult() {
		throw new IllegalStateException();
	}

	@Override
	public List<ISearchEdge> getNextStates() {
		return Collections.emptyList();
	}
	
	@Override
	public List<ISearchState> getAllNextStates() {
		return Collections.emptyList();
	}
	
	@Override
	public List<ISearchEdge> getAllEdges() {
		return Collections.emptyList();
	}
	
	@Override
	public void expand() {
		// Does nothing
	}

	@Override
	public void expand(ISearchExpansionStrategy strategy) {
		// Does nothing
	}
}
