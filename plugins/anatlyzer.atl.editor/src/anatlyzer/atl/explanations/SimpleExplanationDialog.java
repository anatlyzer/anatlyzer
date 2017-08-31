package anatlyzer.atl.explanations;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class SimpleExplanationDialog extends Dialog {

	public static final int SAVE_WITNESS = 1024;
	protected AtlProblemExplanation explanation;
	protected ExplanationComposite explanationComposite;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param exp 
	 */
	public SimpleExplanationDialog(Shell parentShell, AtlProblemExplanation exp) {
		super(parentShell);
		setShellStyle(SWT.RESIZE);
		this.explanation = exp;
	}

	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		explanationComposite = new ExplanationComposite(container, SWT.NONE);
		explanationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		explanationComposite.setExplanation(explanation);
		
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

		createButton(parent, SAVE_WITNESS,
				"Save witness", false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if ( buttonId == SAVE_WITNESS ) { 
			explanationComposite.saveWitness(explanation);
		}
		super.buttonPressed(buttonId);
	}	

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

}
