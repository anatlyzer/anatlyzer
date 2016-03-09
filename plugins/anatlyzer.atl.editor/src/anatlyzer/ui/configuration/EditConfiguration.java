package anatlyzer.ui.configuration;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;

public class EditConfiguration extends Dialog {

	private Button btnDoRecursionUnfolding;
	private Button btnContinousWitnessFinder;
	private Button btnGenerateDebugInformation;
	private Label lblMetamodelStrategy;
	private Combo cmbMetamodelStrategy;
	private Button btnCheckDiscardCause;
	private Label lblNewLabel_1;
	private Combo cmbGraphicalWitness;
	private TransformationConfiguration configuration;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public EditConfiguration(Shell parentShell, TransformationConfiguration configuration) {
		super(parentShell);
		this.configuration = configuration;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel.setText("Model finding");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		btnGenerateDebugInformation = new Button(container, SWT.CHECK);
		btnGenerateDebugInformation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnGenerateDebugInformation.setText("Generate debug files");
		new Label(container, SWT.NONE);
		
		btnContinousWitnessFinder = new Button(container, SWT.CHECK);
		btnContinousWitnessFinder.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnContinousWitnessFinder.setText("Use continous witness finder");
		new Label(container, SWT.NONE);
		
		btnDoRecursionUnfolding = new Button(container, SWT.CHECK);
		btnDoRecursionUnfolding.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnDoRecursionUnfolding.setText("Unfold recursion");
		new Label(container, SWT.NONE);
		
		btnCheckDiscardCause = new Button(container, SWT.CHECK);
		btnCheckDiscardCause.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnCheckDiscardCause.setText("Check discard cause");
		new Label(container, SWT.NONE);
		
		lblMetamodelStrategy = new Label(container, SWT.NONE);
		lblMetamodelStrategy.setText("Meta-model strategy");
		
		cmbMetamodelStrategy = new Combo(container, SWT.NONE);
		cmbMetamodelStrategy.setItems(new String[] {"Error path", "Mandatory full meta-model", "Mandatory effective meta-metamodel"});
		cmbMetamodelStrategy.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("Graphical witness format");
		
		cmbGraphicalWitness = new Combo(container, SWT.NONE);
		cmbGraphicalWitness.setItems(new String[] {"None", "PlantUML"});
		cmbGraphicalWitness.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		setWidgetValues();
		
		return container;
	}

	private void setWidgetValues() {
		btnGenerateDebugInformation.setSelection(configuration.isWitnessFinderDebugMode());
		btnCheckDiscardCause.setSelection(configuration.getCheckDiscardCause());
		btnContinousWitnessFinder.setSelection(configuration.isContinousWitnessFinder());
		btnDoRecursionUnfolding.setSelection(configuration.getDoRecursionUnfolding());
		
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

	@Override
	protected void okPressed() {
		// Set the configuration
		configuration.setWitnessFinderDebugMode(btnGenerateDebugInformation.getSelection());
		configuration.setCheckDiscardCause(btnCheckDiscardCause.getSelection());
		configuration.setContinousWitnessFinder(btnContinousWitnessFinder.getSelection());
		configuration.setDoRecursionUnfolding(btnDoRecursionUnfolding.getSelection());
				
		super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(635, 456);
	}

}
