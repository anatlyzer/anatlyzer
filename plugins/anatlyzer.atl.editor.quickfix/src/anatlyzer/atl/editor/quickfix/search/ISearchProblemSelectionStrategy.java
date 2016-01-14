package anatlyzer.atl.editor.quickfix.search;

// This sorts the problems to prioritise the depth-first traversal
public interface ISearchProblemSelectionStrategy {
	public void step();
}
