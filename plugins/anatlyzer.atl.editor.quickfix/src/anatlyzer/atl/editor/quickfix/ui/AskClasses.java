package anatlyzer.atl.editor.quickfix.ui;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;

public class AskClasses extends Dialog {
	
	private java.util.List<EClass> classes;
	private List list;
	private ListViewer listViewer;
	private EClass selectedClass;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AskClasses(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		list = listViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		initValues();
		
		return container;
	}

	private void initValues() {
		for (EClass eClass : classes) {
			list.add(eClass.getName());
		}
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(467, 226);
	}

	public void setClasses(java.util.List<EClass> classes) {
		this.classes = classes;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if ( buttonId == IDialogConstants.OK_ID ) {
			String[] selection = list.getSelection();
			if ( selection.length > 0 ) {
				selectedClass = classes.stream().filter(c -> c.getName().equals(selection[0])).findAny().orElse(null);
			}			
		}
		super.buttonPressed(buttonId);
	}
	
	public EClass getSelectedClass() {
		return selectedClass;
	}
}
