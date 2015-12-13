package anatlyzer.atl.editor.quickfix.search;

import anatlyzer.atl.analyser.AnalysisResult;

public class SearchState {
	private SearchPath path;
	private AnalysisResult analysis;

	public SearchState(SearchPath path, AnalysisResult analysis) {
		this.path = path;
		this.analysis = analysis;
	}
	
	public SearchPath getPath() {
		return path;
	}
	
	public AnalysisResult getAnalysis() {
		return analysis;
	}
}
