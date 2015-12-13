package anatlyzer.atl.editor.quickfix.search;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;

public interface ISearchEdge {
	public AtlProblemQuickfix getQuickfix();
	public ISearchState getSource();
	public ISearchState getTarget();
}
