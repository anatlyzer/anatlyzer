package anatlyzer.atl.editor.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import anatlyzer.atl.editor.views.AnalysisView.LocalProblemNode;
import anatlyzer.atl.editor.views.AnalysisView.TreeNode;
import anatlyzer.atl.editor.views.AnalysisView.TreeParent;

public class AnalysisViewComparator extends ViewerComparator {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	public AnalysisViewComparator() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
	    int rc = 0;
	    
	    TreeNode n1 = (TreeNode) e1;
	    TreeNode n2 = (TreeNode) e2;
	    
	    if ( n1.getParent() instanceof TreeParent ) {
	    	// Works to make "batch analysis" first
	    	return e1.getClass().getSimpleName().compareTo(e2.getClass().getSimpleName());
	    }
	    
	    if ( n1 instanceof LocalProblemNode && propertyIndex == 1) {
	    	int l1 = ((LocalProblemNode) n1).getLineLocation();
	    	int l2 = ((LocalProblemNode) n2).getLineLocation();
	    	
	    	if ( l1 < l2 ) return -1;
	    	if ( l2 < l1 ) return +1;
	    }
	    
	    switch (propertyIndex) {
	    case 0: rc = n1.toString().compareTo(n2.toString()); break;
	    case 1: rc = n1.toColumn1().compareTo(n2.toColumn1()); break;
	    default:
	    	rc =  n1.toString().compareTo(n2.toString());
	    }
	    
	    // If descending order, flip the direction
	    if (direction == DESCENDING) {
	      rc = -rc;
	    }
	    return rc;
	}


}
