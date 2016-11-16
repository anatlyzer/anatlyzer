package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;

public class QuickfixTableLabelProvider implements ITableLabelProvider {

	private boolean useColumns;

	public QuickfixTableLabelProvider(boolean useColumns) {
		this.useColumns = useColumns;
	}
	
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
		AtlProblemQuickfix qfx = (AtlProblemQuickfix) element;
		try {
		if ( useColumns ) {
			switch (columnIndex) {
			case 0:
				if ( qfx.requiresUserIntervention() )
					return "U";
				
				Integer num = (Integer) qfx.getData(ISpeculativeConstants.FOUND_PROBLEMS);
				if ( num == null ) {
					return "?";
				}
				return num.toString();
			case 1:
				return qfx.getDisplayString();
			}
			
			return null;
		} else {			
			return qfx.getDisplayString();
		}
		} catch ( Exception e ) {
			e.printStackTrace();
			return "Cannot get description: " + qfx;
		}
	}
	
	
	public static ColumnViewerComparator createFoundProblemsComparator(ColumnViewer viewer, TableViewerColumn column) {
		return new ColumnViewerComparator(viewer, column) {
			@Override
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				Integer num1 = (Integer) ((AtlProblemQuickfix) e1).getData(ISpeculativeConstants.FOUND_PROBLEMS);
				Integer num2 = (Integer) ((AtlProblemQuickfix) e2).getData(ISpeculativeConstants.FOUND_PROBLEMS);
				if ( num1 == null ) num1 = Integer.MAX_VALUE;
				if ( num2 == null ) num2 = Integer.MAX_VALUE;

				return num1.compareTo(num2);
			}

		};	
	}

	public static ColumnViewerComparator createDisplayStringComparator(ColumnViewer viewer, TableViewerColumn column) {
		return new ColumnViewerComparator(viewer, column) {
			@Override
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				String str1 = ((AtlProblemQuickfix) e1).getDisplayString();
				String str2 = ((AtlProblemQuickfix) e2).getDisplayString();

				return str1.compareTo(str2);
			}

		};	
	}

}
