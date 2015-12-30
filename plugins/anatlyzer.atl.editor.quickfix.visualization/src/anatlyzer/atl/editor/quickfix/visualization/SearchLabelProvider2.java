package anatlyzer.atl.editor.quickfix.visualization;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.IConnectionStyleProvider;
import org.eclipse.zest.core.viewers.IEntityStyleProvider;

import anatlyzer.atl.editor.quickfix.search.ISearchEdge;
import anatlyzer.atl.editor.quickfix.search.ISearchState;
import anatlyzer.atl.editor.quickfix.search.InteractiveSearch.SearchEdge;
import anatlyzer.atl.editor.quickfix.search.SearchState;
import anatlyzer.atl.editor.quickfix.visualization.SearchContentProvider.StartNode;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

public class SearchLabelProvider2 implements ILabelProvider, IColorProvider, IFontProvider, IEntityStyleProvider, IConnectionStyleProvider {

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
		return getElementText(element);
	}
	
	public static String getElementText(Object element) {
		if ( element instanceof ISearchEdge ) {
			ISearchEdge e = (ISearchEdge) element;
			String str = e.getQuickfix().getDisplayString();
			return str.substring(0, Math.min(str.length(), 10));
		} else if ( element instanceof ISearchState ) {
			ISearchState s = (ISearchState) element;
			return "p: " + s.getAnalysisResult().getProblems().size() + " ";
		} else if ( element instanceof StartNode ) {
			StartNode n = (StartNode) element;
			return "s: " + n.state.getAnalysisResult().getProblems().size() + " ";
		}
		return element.toString();
	}

	@Override
	public Color getNodeHighlightColor(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getBorderColor(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getBorderHighlightColor(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBorderWidth(Object entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getBackgroundColour(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getForegroundColour(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFigure getTooltip(Object element) {
		// See: http://www.eclipse.org/mylyn/sandbox/zest/GraphSnippet4.html
		String str = "";
		if ( element instanceof ISearchEdge ) {
			ISearchEdge e = (ISearchEdge) element;
			str = e.getQuickfix().getDisplayString();
		} else if ( element instanceof ISearchState ) {
			ISearchState s = (ISearchState) element;
			str = "p: " + s.getAnalysisResult().getProblems().size() + " ";
		} else if ( element instanceof StartNode ) {
			StartNode n = (StartNode) element;
			str = "s: " + n.state.getAnalysisResult().getProblems().size() + " ";
		}

		IFigure tooltip1 = new Label(str);		
		return tooltip1;
	}

	@Override
	public boolean fisheyeNode(Object entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getConnectionStyle(Object rel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getColor(Object rel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getHighlightColor(Object rel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLineWidth(Object rel) {
		// TODO Auto-generated method stub
		return 0;
	}

}
