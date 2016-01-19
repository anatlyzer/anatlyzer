package anatlyzer.atl.editor.compare;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.IViewerCreator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

public class ATLTextViewerCreator implements IViewerCreator {

	public ATLTextViewerCreator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Viewer createViewer(Composite parent, CompareConfiguration config) {
		return new ATLTextViewer(parent, config);
	}

}
