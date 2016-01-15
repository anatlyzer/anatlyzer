package anatlyzer.atl.editor.quickfix.search;

import static anatlyzer.atl.editor.quickfix.search.SearchUtil.isActualProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.SpeculativeQuickfixUtils;
import anatlyzer.atl.editor.quickfix.search.ISearchExpansionStrategy.Expansion;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.witness.IWitnessFinder;

public class InteractiveSearch extends AbstractSearch implements ISearchState {
	// This is the state
	private SearchPath path;
	private List<ISearchEdge> edges = new ArrayList<ISearchEdge>();
	
	private ISearchListener listener;

	public InteractiveSearch(SearchPath path, AnalysisResult analysis, IWitnessFinder finder) {
		super(finder, analysis);		
		this.path = path;
	}
	
	@Override
	public List<ISearchState> getAllNextStates() {
		return getAllEdges().stream().
				map(e -> e.getTarget()).
				filter(e -> !(e instanceof SearchError)).
				collect(Collectors.toList());
	}
	
	public List<ISearchEdge> getAllEdges() {
		ArrayList<ISearchEdge> all = new ArrayList<ISearchEdge>();
		all.addAll(this.getNextStates());
		for (ISearchEdge edge : this.getNextStates()) {
			addElements(all, edge);
		}
		return all;
	}

	private void addElements(ArrayList<ISearchEdge> all, ISearchEdge edge) {
		all.addAll(edge.getTarget().getNextStates());
		for (ISearchEdge newEdge : edge.getTarget().getNextStates()) {
			addElements(all, newEdge);				
		}
	}		

	@Override
	public SearchPath getPath() {
		return path;
	}
	
	@Override
	public AnalysisResult getAnalysisResult() {
		return analysis;
	}


	@Override
	public List<ISearchEdge> getNextStates() {
		return java.util.Collections.unmodifiableList(edges);
	}

	@Override
	public void expand() {
		baseSearch(analysis);	
	}

	@Override
	public void expand(ISearchExpansionStrategy strategy) {
		for (Expansion expansion : strategy.getExpansion(this)) {
			if ( ! isActualProblem(expansion.p, analysis) ) 
				continue;
			
			baseBranch(expansion.p, expansion.qfx, analysis);
		}
	}

	@Override
	protected void baseBranch(Problem problem, AbstractAtlQuickfix qfx, AnalysisResult baseAnalysis) {
		SearchPath newPath = path.add(qfx);;
		SearchEdge edge = null;
		try {
			IncrementalCopyBasedAnalyser inc = SpeculativeQuickfixUtils.createIncrementalAnalyser(baseAnalysis, problem, qfx);
			inc.perform();
			AnalysisResult result = new AnalysisResult(inc);
			
			edge = new SearchEdge(qfx, this, new InteractiveSearch(newPath, result, finder));
		} catch ( Throwable e ) {
			// If there is a problem, then I just create an error node
			edge = new SearchEdge(qfx, this, new SearchError(newPath, e));
		}
			
		edges.add(edge);
		
		if ( listener != null ) {
			listener.newBranch(this, edge);
		}
	}
	
	public void setListener(ISearchListener listener) {
		this.listener = listener;
	}
	
	public static interface ISearchListener {
		void newBranch(ISearchState previousState, ISearchEdge newEdge);
	}
	
	public class SearchEdge implements ISearchEdge {
		private ISearchState src;
		private ISearchState state;
		private AtlProblemQuickfix qfx;

		public SearchEdge(AtlProblemQuickfix qfx, ISearchState src, ISearchState state) {
			this.src   = src;
			this.state = state;
			this.qfx   = qfx;
		}
		
		@Override
		public AtlProblemQuickfix getQuickfix() {
			return qfx;
		}

		@Override
		public ISearchState getTarget() {
			return state;
		}
		
		@Override
		public ISearchState getSource() {
			return src;
		}
	}
	
}
