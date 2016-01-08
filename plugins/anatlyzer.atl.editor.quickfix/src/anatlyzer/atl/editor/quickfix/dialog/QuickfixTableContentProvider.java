package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;

public class QuickfixTableContentProvider implements IStructuredContentProvider {
	private List<AtlProblemQuickfix> quickfixes;
	
	@Override
	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.quickfixes = (List<AtlProblemQuickfix>) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {			
		return quickfixes.toArray();
	}
}