package anatlyzer.atl.editor.quickfix.visualization;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
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
import org.eclipse.zest.core.widgets.ZestStyles;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.BindingProblemQuickFix;
import anatlyzer.atl.editor.quickfix.search.ISearchEdge;
import anatlyzer.atl.editor.quickfix.search.ISearchState;
import anatlyzer.atl.editor.quickfix.search.SearchError;
import anatlyzer.atl.editor.quickfix.visualization.SearchContentProvider.StartNode;

public class SearchLabelProvider2 implements ILabelProvider, IColorProvider, IEntityStyleProvider, IConnectionStyleProvider {
	private Device device = Display.getCurrent ();
	
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

	
//	// From IFontProvider
//	@Override
//	public Font getFont(Object element) {
//		return null;
//	}

	@Override
	public Color getForeground(Object element) {
		return new Color(device, 0, 0, 0);
	}

	@Override
	public Color getBackground(Object element) {
		if ( element instanceof SearchError ) {
			return new Color(device, 200, 0, 0);			
		} else if ( element instanceof ISearchState ) {
			ISearchState s = (ISearchState) element;
			if ( s.getAnalysisResult().getProblems().size() == 0 ) {
				return new Color(device, 200, 200, 0);
			}			
		}
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
//			ISearchEdge e = (ISearchEdge) element;
//			String str = e.getQuickfix().getDisplayString();
//			return str.substring(0, Math.min(str.length(), 10));
			return null;
		} else if ( element instanceof SearchError ) {
			return "X";
		} else if ( element instanceof ISearchState ) {
			ISearchState s = (ISearchState) element;
			if ( s.getAnalysisResult().getPossibleProblems().size() == 0 ) {
				return "Fixed";
			}			
			return "p: " + s.getAnalysisResult().getPossibleProblems().size() + " ";
		} else if ( element instanceof StartNode ) {
			StartNode n = (StartNode) element;
			return "s: " + n.state.getAnalysisResult().getPossibleProblems().size() + " ";
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
		return getBackground(entity);
	}

	@Override
	public Color getForegroundColour(Object entity) {
		return getForeground(entity);
	}

	@Override
	public IFigure getTooltip(Object element) {
		// See: http://www.eclipse.org/mylyn/sandbox/zest/GraphSnippet4.html
		String str = "";
		if ( element instanceof ISearchEdge ) {
			ISearchEdge e = (ISearchEdge) element;
			str = e.getQuickfix().getDisplayString();
		} else if ( element instanceof SearchError ) {
			str = ((SearchError) element).getError().getMessage();
		} else if ( element instanceof ISearchState ) {
			ISearchState s = (ISearchState) element;
			str = "p: " + s.getAnalysisResult().getPossibleProblems().size() + " ";
		} else if ( element instanceof StartNode ) {
			StartNode n = (StartNode) element;
			str = "s: " + n.state.getAnalysisResult().getPossibleProblems().size() + " ";
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
	public int getConnectionStyle(Object element) {
		if ( element instanceof ISearchEdge ) {
			AtlProblemQuickfix qfx = ((ISearchEdge) element).getQuickfix();
			if ( qfx instanceof BindingProblemQuickFix ) {
				return ZestStyles.CONNECTIONS_DASH;
			}
		}
		return ZestStyles.CONNECTIONS_SOLID;
	}

	// The color for the connection
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
	public int getLineWidth(Object element) {
		if ( element instanceof ISearchEdge ) {
			AtlProblemQuickfix qfx = ((ISearchEdge) element).getQuickfix();
			if ( qfx instanceof BindingProblemQuickFix ) {
				return 2;
			}
		}
		return 0;
	}

}
