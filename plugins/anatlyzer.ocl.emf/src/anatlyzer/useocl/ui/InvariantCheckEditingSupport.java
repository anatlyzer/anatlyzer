package anatlyzer.useocl.ui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import anatlyzer.useocl.ui.ConstraintsContentProvider.InvariantData;

// Copied from:
//    https://stackoverflow.com/questions/40605677/how-can-i-create-a-checkbox-in-a-tableviewer-of-jface
public class InvariantCheckEditingSupport extends EditingSupport{
    private CheckboxCellEditor cellEditor;


    public InvariantCheckEditingSupport(ColumnViewer viewer) {
        super(viewer);
        // TODO Auto-generated constructor stub
        //cellEditor = new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
        cellEditor = new CheckboxCellEditor(((TableViewer)viewer).getTable());
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        // TODO Auto-generated method stub
        return cellEditor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        // TODO Auto-generated method stub
        return ((InvariantData) element).isSelected();
    }

    @Override
    protected void setValue(Object element, Object value) {
        // TODO Auto-generated method stub
        ((InvariantData) element).setSelected(Boolean.valueOf((boolean) value));
        getViewer().update(element, null);
    }
}