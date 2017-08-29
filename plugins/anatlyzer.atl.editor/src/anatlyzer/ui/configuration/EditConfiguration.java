package anatlyzer.ui.configuration;

import java.util.Iterator;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.RowLayout;

import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ProblemSets;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
	private TableViewer tableViewerContinous;
	private Table tblBatchMode;
	private TableViewer tableViewerBatch;
	private Table tblIgnored;
	private TableViewer tableViewerIgnored;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public EditConfiguration(Shell parentShell, TransformationConfiguration configuration) {
		super(parentShell);
		setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE);
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
		
		tableViewerContinous = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tblContinuous = tableViewerContinous.getTable();
		tblContinuous.setLinesVisible(true);
		
		Composite compositeContinousButtons = new Composite(compProblemsContinuous, SWT.NONE);
		RowLayout rl_compositeContinousButtons = new RowLayout(SWT.HORIZONTAL);
		rl_compositeContinousButtons.fill = true;
		rl_compositeContinousButtons.center = true;
		compositeContinousButtons.setLayout(rl_compositeContinousButtons);
		compositeContinousButtons.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		
		Button btnContinousToBatch = new Button(compositeContinousButtons, SWT.CENTER);
		btnContinousToBatch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveContinousToBatch(e);
			}
		});
		btnContinousToBatch.setAlignment(SWT.LEFT);
		btnContinousToBatch.setText(">> Batch");
		
		Button btnContinousToIgnored = new Button(compositeContinousButtons, SWT.CENTER);
		btnContinousToIgnored.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveContinousToIgnored(e);
			}
		});
		btnContinousToIgnored.setAlignment(SWT.LEFT);
		btnContinousToIgnored.setText(">> Ignored");
		
		compProblemsBatch = new Composite(sashForm, SWT.NONE);
		compProblemsBatch.setLayout(new GridLayout(1, false));
		
		lblBatchMode = new Label(compProblemsBatch, SWT.NONE);
		lblBatchMode.setText("Batch mode");
		lblBatchMode.setBounds(0, 0, 108, 17);
		
		tableViewerBatch = new TableViewer(compProblemsBatch, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tblBatchMode = tableViewerBatch.getTable();
		tblBatchMode.setLinesVisible(true);
		tblBatchMode.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite compositeBatchButtons = new Composite(compProblemsBatch, SWT.NONE);
		RowLayout rl_compositeBatchButtons = new RowLayout(SWT.HORIZONTAL);
		rl_compositeBatchButtons.fill = true;
		rl_compositeBatchButtons.center = true;
		compositeBatchButtons.setLayout(rl_compositeBatchButtons);
		compositeBatchButtons.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		
		Button btnBatchToContinous = new Button(compositeBatchButtons, SWT.NONE);
		btnBatchToContinous.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveBatchToContinous(e);
			}
		});
		btnBatchToContinous.setAlignment(SWT.LEFT);
		btnBatchToContinous.setText("<< Continous");
		
		Button btnBatchToIgnored = new Button(compositeBatchButtons, SWT.NONE);
		btnBatchToIgnored.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveBatchToIgnored(e);
			}
		});
		btnBatchToIgnored.setAlignment(SWT.LEFT);
		btnBatchToIgnored.setText(">> Ignored");

		
		compProblemsIgnored = new Composite(sashForm, SWT.NONE);
		compProblemsIgnored.setLayout(new GridLayout(1, false));
		
		lblIgnored = new Label(compProblemsIgnored, SWT.NONE);
		lblIgnored.setText("Ignored");
		lblIgnored.setBounds(0, 0, 72, 17);
		
		tableViewerIgnored = new TableViewer(compProblemsIgnored, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tblIgnored = tableViewerIgnored.getTable();
		tblIgnored.setLinesVisible(true);
		tblIgnored.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite compositeIgnoredButtons = new Composite(compProblemsIgnored, SWT.NONE);
		RowLayout rl_compositeIgnoredButtons = new RowLayout(SWT.HORIZONTAL);
		rl_compositeIgnoredButtons.fill = true;
		rl_compositeIgnoredButtons.center = true;
		compositeIgnoredButtons.setLayout(rl_compositeIgnoredButtons);
		compositeIgnoredButtons.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		
		Button btnIgnoredToContinous = new Button(compositeIgnoredButtons, SWT.NONE);
		btnIgnoredToContinous.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveIgnoredToContinous(e);
			}
		});
		btnIgnoredToContinous.setAlignment(SWT.LEFT);
		btnIgnoredToContinous.setText("<< Continous");
		
		Button btnIgnoredToBatch = new Button(compositeIgnoredButtons, SWT.NONE);
		btnIgnoredToBatch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveIgnoredToBatch(e);
			}
		});
		btnIgnoredToBatch.setAlignment(SWT.LEFT);
		btnIgnoredToBatch.setText(">> Batch");

		
		
		sashForm.setWeights(new int[] {1, 1, 1});

		setWidgetValues();
		
		return dialogParent;
	}

	private void setWidgetValues() {
		btnGenerateDebugInformation.setSelection(configuration.isWitnessFinderDebugMode());
		btnCheckDiscardCause.setSelection(configuration.getCheckDiscardCause());
		btnContinousWitnessFinder.setSelection(configuration.isContinousWitnessFinder());
		btnDoRecursionUnfolding.setSelection(configuration.getDoRecursionUnfolding());
		txtTimeOut.setText(configuration.getTimeOut() == -1 ? "" : configuration.getTimeOut() + "");
		
		ProblemSets problems = configuration.getAvailableProblems();
		tableViewerContinous.setContentProvider(new ArrayContentProvider());
		tableViewerContinous.setLabelProvider(new ProblemTableLabelProvider());		
		tableViewerBatch.setContentProvider(new ArrayContentProvider());
		tableViewerBatch.setLabelProvider(new ProblemTableLabelProvider());
		tableViewerIgnored.setContentProvider(new ArrayContentProvider());
		tableViewerIgnored.setLabelProvider(new ProblemTableLabelProvider());
		
		tableViewerContinous.setInput(problems.getContinous());
		tableViewerBatch.setInput(problems.getBatch());
		tableViewerIgnored.setInput(problems.getIgnored());
		
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
				
		String txt = txtTimeOut.getText().trim();
		if ( txt.isEmpty() ) {
			configuration.setTimeOut(-1);
		} else {
			configuration.setTimeOut(Long.parseLong(txt));
		}
		
		super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(635, 456);
	}
	
	public class ProblemTableLabelProvider implements ITableLabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) { }

		@Override
		public void dispose() { }

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {	}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			EClass pClass = (EClass) element;
			return AnalyserUtils.getProblemDescription(pClass);
		}
	}
	
	protected void moveIgnoredToBatch(SelectionEvent e) {
		move((IStructuredSelection) tableViewerIgnored.getSelection(), 
				configuration.getAvailableProblems()::moveToBatch);
	}

	protected void moveIgnoredToContinous(SelectionEvent e) {
		move((IStructuredSelection) tableViewerIgnored.getSelection(), 
				configuration.getAvailableProblems()::moveToContinous);		
	}

	protected void moveBatchToIgnored(SelectionEvent e) {
		move((IStructuredSelection) tableViewerBatch.getSelection(), 
				configuration.getAvailableProblems()::moveToIgnored);
	}

	protected void moveBatchToContinous(SelectionEvent e) {
		move((IStructuredSelection) tableViewerBatch.getSelection(), 
				configuration.getAvailableProblems()::moveToContinous);		
	}

	protected void moveContinousToIgnored(SelectionEvent e) {
		move((IStructuredSelection) tableViewerContinous.getSelection(), 
				configuration.getAvailableProblems()::moveToIgnored);
	}

	protected void moveContinousToBatch(SelectionEvent e) {
		move((IStructuredSelection) tableViewerContinous.getSelection(), 
				configuration.getAvailableProblems()::moveToBatch);
	}

	protected void move(IStructuredSelection selection, Consumer<EClass> e) {
		Iterator<?> it = selection.iterator();
		while ( it.hasNext() ) {
			e.accept((EClass) it.next());
		}
		tableViewerContinous.refresh();
		tableViewerBatch.refresh();
		tableViewerIgnored.refresh();
	}
	

}
