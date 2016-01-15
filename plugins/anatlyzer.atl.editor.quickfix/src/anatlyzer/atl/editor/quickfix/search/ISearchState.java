package anatlyzer.atl.editor.quickfix.search;

import java.util.List;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;

public interface ISearchState {
	public SearchPath getPath();
	public AnalysisResult getAnalysisResult();
	public List<ISearchEdge> getNextStates();
	
	public List<ISearchEdge> getAllEdges();
	public List<ISearchState> getAllNextStates();
	
	public void expand();
	public void expand(ISearchExpansionStrategy strategy);
}
