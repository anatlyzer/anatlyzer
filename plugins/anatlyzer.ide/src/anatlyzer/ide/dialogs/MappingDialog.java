package anatlyzer.ide.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class MappingDialog extends Dialog {

	private ITransformationMapping tm;

	public MappingDialog(Shell parentShell, ITransformationMapping tm) {
		super(parentShell);
		this.tm = tm;
		setBlockOnOpen(false);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		// container.setLayout(new GridLayout(1, false));
		container.setLayout(new GridLayout(1, false));
		
		MappingComposite child = new MappingComposite(container);
		child.setTransformationMapping(tm);
		GridData gd_cmpChild = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		// gd_cmpChild.heightHint = 34;
		child.setLayoutData(gd_cmpChild);
		
		return container;
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(826, 515);
	}


}
