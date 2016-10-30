package anatlyzer.ui.configuration;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;

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
	private Label lblTimeOut;
	private Text txtTimeOut;
	private TabItem tbtmProblems;
	private SashForm sashForm;
	private Composite compProblemsContinuous;
	private Composite compProblemsBatch;
	private Composite compProblemsIgnored;
	private Label lblC;
	private Label lblBatchMode;
	private Label lblIgnored;
	private Composite composite;
	private Table tblContinuous;
	private TableViewer tableViewer;
	private Table tblBatchMode;
	private TableViewer tableViewer_1;
	private Table tblIgnored;
	private TableViewer tableViewer_2;

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
		Composite dialogParent = (Composite) super.createDialogArea(parent);
		dialogParent.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder folder = new TabFolder(dialogParent, SWT.NONE);
		
		TabItem item = new TabItem(folder, SWT.NONE);
		item.setText("Model finding");
		Composite container = new Composite(folder, SWT.NONE);
		item.setControl(container);
		
		container.setLayout(new GridLayout(4, false));
		
		btnGenerateDebugInformation = new Button(container, SWT.CHECK);
		btnGenerateDebugInformation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnGenerateDebugInformation.setText("Generate debug files");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		btnContinousWitnessFinder = new Button(container, SWT.CHECK);
		btnContinousWitnessFinder.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnContinousWitnessFinder.setText("Use continous witness finder");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		btnDoRecursionUnfolding = new Button(container, SWT.CHECK);
		btnDoRecursionUnfolding.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnDoRecursionUnfolding.setText("Unfold recursion");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		btnCheckDiscardCause = new Button(container, SWT.CHECK);
		btnCheckDiscardCause.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnCheckDiscardCause.setText("Check discard cause");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		lblTimeOut = new Label(container, SWT.NONE);
		lblTimeOut.setText("Time out (milliseconds)");
		
		txtTimeOut = new Text(container, SWT.BORDER);
		GridData gd_txtTimeOut = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtTimeOut.widthHint = 159;
		txtTimeOut.setLayoutData(gd_txtTimeOut);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		lblMetamodelStrategy = new Label(container, SWT.NONE);
		lblMetamodelStrategy.setText("Meta-model strategy");
		
		cmbMetamodelStrategy = new Combo(container, SWT.NONE);
		cmbMetamodelStrategy.setItems(new String[] {"Error path", "Mandatory full meta-model", "Mandatory effective meta-metamodel"});
		cmbMetamodelStrategy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
		
		lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("Graphical witness format");
		
		cmbGraphicalWitness = new Combo(container, SWT.NONE);
		cmbGraphicalWitness.setItems(new String[] {"None", "PlantUML"});
		cmbGraphicalWitness.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		tbtmProblems = new TabItem(folder, SWT.NONE);
		tbtmProblems.setText("Problems");
		
		sashForm = new SashForm(folder, SWT.NONE);
		tbtmProblems.setControl(sashForm);
		
		compProblemsContinuous = new Composite(sashForm, SWT.NONE);
		compProblemsContinuous.setLayout(new GridLayout(1, false));
		
		lblC = new Label(compProblemsContinuous, SWT.NONE);
		lblC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblC.setText("Continuous mode");
		
		composite = new Composite(compProblemsContinuous, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new TableColumnLayout());
		
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tblContinuous = tableViewer.getTable();
		tblContinuous.setHeaderVisible(true);
		tblContinuous.setLinesVisible(true);
		
		compProblemsBatch = new Composite(sashForm, SWT.NONE);
		compProblemsBatch.setLayout(new GridLayout(1, false));
		
		lblBatchMode = new Label(compProblemsBatch, SWT.NONE);
		lblBatchMode.setText("Batch mode");
		lblBatchMode.setBounds(0, 0, 108, 17);
		
		tableViewer_1 = new TableViewer(compProblemsBatch, SWT.BORDER | SWT.FULL_SELECTION);
		tblBatchMode = tableViewer_1.getTable();
		tblBatchMode.setLinesVisible(true);
		tblBatchMode.setHeaderVisible(true);
		tblBatchMode.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		compProblemsIgnored = new Composite(sashForm, SWT.NONE);
		compProblemsIgnored.setLayout(new GridLayout(1, false));
		
		lblIgnored = new Label(compProblemsIgnored, SWT.NONE);
		lblIgnored.setText("Ignored");
		lblIgnored.setBounds(0, 0, 72, 17);
		
		tableViewer_2 = new TableViewer(compProblemsIgnored, SWT.BORDER | SWT.FULL_SELECTION);
		tblIgnored = tableViewer_2.getTable();
		tblIgnored.setLinesVisible(true);
		tblIgnored.setHeaderVisible(true);
		tblIgnored.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] {1, 1, 1});

		setWidgetValues();
		
		return dialogParent;
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
