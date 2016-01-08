package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;

public class QuickfixTableLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {	}

	@Override
	public void dispose() { }

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) { }

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		AtlProblemQuickfix qf = (AtlProblemQuickfix) element;
		if ( columnIndex == 0 ) {
			return qf.getDisplayString();
		}
		return null;
	}
	
}
