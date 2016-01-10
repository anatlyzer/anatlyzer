package anatlyzer.atl.editor.quickfix.visualization;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

import anatlyzer.atl.editor.quickfix.search.ISearchEdge;
import anatlyzer.atl.editor.quickfix.search.ISearchState;
import anatlyzer.atl.editor.quickfix.search.InteractiveSearch;

public class SearchContentProvider implements IGraphContentProvider {

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object getSource(Object rel) {
		if ( rel instanceof ISearchEdge ) {
			return ((ISearchEdge) rel).getSource();
		} else if ( rel instanceof StartNode ) {
			return null;
		}
		throw new IllegalStateException();
	}

	@Override
	public Object getDestination(Object rel) {
		if ( rel instanceof ISearchEdge ) {
			return ((ISearchEdge) rel).getTarget();
		} else if ( rel instanceof StartNode ) {
			return null;
		}
		throw new IllegalStateException();
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if ( inputElement instanceof StartNode ) {
			// return new Object[] { ((StartNode) inputElement).state };
			List<ISearchEdge> allElements = ((InteractiveSearch) ((StartNode) inputElement).state).getAllEdges();
			if ( allElements.size() == 0 ) {
				return new Object[] { inputElement };
			}
			return allElements.toArray();
		}
		throw new IllegalStateException();
	}

	
	
	public static class StartNode {
		public ISearchState state;

		public StartNode(ISearchState state) {
			this.state = state;
		}

		
	}

	
	/*
	@Override
	public Object[] getElements(Object inputElement) {
		if ( inputElement instanceof InteractiveSearch ) {
			return new Object[] { ((InteractiveSearch) inputElement).getState() };
		} else if ( inputElement instanceof SearchState ) {
			SearchState state = (SearchState) inputElement;
			return state.getPath().getNext().stream().map(e -> e.getNext()).collect(Collectors.toList()).toArray();			
		}
		System.out.println(inputElement);
		return new Object[] { };
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }

	@Override
	public Object[] getRelationships(Object source, Object dest) {
		
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
