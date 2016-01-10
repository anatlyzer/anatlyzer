package anatlyzer.atl.editor.quickfix.visualization;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import anatlyzer.atl.editor.quickfix.search.ISearchState;

public class FilterNumberOfProblems extends ViewerFilter {

	private int value;

	public FilterNumberOfProblems(int value) {
		this.value = value;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if ( value < 0 )
			return true;
		System.out.println(element);
		if ( element instanceof ISearchState ) {
			ISearchState s = (ISearchState) element;
			
			return  selectionCriteria(s) ||
					s.getAllNextStates().stream().anyMatch(this::selectionCriteria);
		}
		return true;
	}

	protected boolean selectionCriteria(ISearchState s) {
		return s.getAnalysisResult().getPossibleProblems().size() <= value;
	}
	
}
