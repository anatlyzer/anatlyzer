package anatlyzer.atl.editor.quickfix.visualization;

import org.eclipse.jface.viewers.LabelProvider;

import anatlyzer.atl.editor.quickfix.search.ISearchEdge;
import anatlyzer.atl.editor.quickfix.search.ISearchState;
import anatlyzer.atl.editor.quickfix.visualization.SearchContentProvider.StartNode;
public class SearchLabelProvider extends LabelProvider  {

	@Override
	public String getText(Object element) {
		if ( element instanceof ISearchEdge ) {
			ISearchEdge e = (ISearchEdge) element;
			
			String str = e.getQuickfix().getDisplayString();
			return str.substring(0, Math.min(10, str.length()));
		} else if ( element instanceof ISearchState ) {
			ISearchState s = (ISearchState) element;
			return "p: " + s.getAnalysisResult().getProblems().size() + " ";
		} else if ( element instanceof StartNode ) {
			StartNode n = (StartNode) element;
			return "s: " + n.state.getAnalysisResult().getProblems().size() + " ";
		}
		return element.toString();
	}

}
