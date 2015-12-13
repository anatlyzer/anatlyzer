package anatlyzer.atl.editor.quickfix.visualization;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.editor.quickfix.search.ISearchEdge;
import anatlyzer.atl.editor.quickfix.search.ISearchState;
import anatlyzer.atl.editor.quickfix.search.InteractiveSearch.SearchEdge;
import anatlyzer.atl.editor.quickfix.search.SearchState;
import anatlyzer.atl.editor.quickfix.visualization.SearchContentProvider.StartNode;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
public class SearchLabelProvider2 implements ILabelProvider, IColorProvider, IFontProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Font getFont(Object element) {
		return null;
	}

	@Override
	public Color getForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if ( element instanceof BindingResolution ) {
			Device device = Display.getCurrent ();
			return new Color(device, 150, 150, 150);			
		}
		else if ( element instanceof ResolvedRuleInfo ) {
			Device device = Display.getCurrent ();
			return new Color(device, 160, 64, 64);
		} else if ( element instanceof RuleResolutionInfo ) {
			Device device = Display.getCurrent ();
			return new Color(device, 64, 160, 64);
		}
		
		Device device = Display.getCurrent ();
		return new Color(device, 64, 160, 64);
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if ( element instanceof ISearchEdge ) {
			ISearchEdge e = (ISearchEdge) element;
			return e.getQuickfix().getDisplayString().substring(0, 10);
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
