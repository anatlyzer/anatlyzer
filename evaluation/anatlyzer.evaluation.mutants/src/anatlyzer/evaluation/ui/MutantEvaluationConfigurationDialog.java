package anatlyzer.evaluation.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class MutantEvaluationConfigurationDialog extends Dialog {

	private Button btnGenerateNewMutants;
	private Button btnGenerateTestModels;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public MutantEvaluationConfigurationDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.RESIZE | SWT.TITLE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		btnGenerateNewMutants = new Button(container, SWT.CHECK);
		btnGenerateNewMutants.setText("Generate new mutants");
		
		btnGenerateTestModels = new Button(container, SWT.CHECK);
		btnGenerateTestModels.setText("Generate test models");

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
		return new Point(280, 177);
	}

	@Override
	protected void okPressed() {
		this.generateTestModels = btnGenerateTestModels.getSelection();
		this.generateNewMutants = btnGenerateNewMutants.getSelection();
		super.okPressed();
	}
	
	// Option access
	private boolean generateTestModels;
	private boolean generateNewMutants;
	
	public boolean getOptionGenerateTestModels() {
		return generateTestModels;
	}
	
	public boolean getOptionGenerateNewMutants() {
		return generateNewMutants;
	}
	
	
}
