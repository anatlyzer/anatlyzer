package anatlyzer.atl.editor.quickfix.search;

import java.util.List;

import anatlyzer.atl.analyser.AnalysisResult;

public interface ISearchState {
	public SearchPath getPath();
	public AnalysisResult getAnalysisResult();
	public List<ISearchEdge> getNextStates();
	
	
}