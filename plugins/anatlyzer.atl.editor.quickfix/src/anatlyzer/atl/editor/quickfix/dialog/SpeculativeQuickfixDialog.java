package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.layout.FillLayout;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.impact.ImpactComputation;
import anatlyzer.atl.model.ATLModel.ITracedATLModel;
import anatlyzer.atl.problemtracking.ProblemTracker;

import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.custom.SashForm;

public class SpeculativeQuickfixDialog extends Dialog implements  SpeculativeListener {
	private Table table;
	private List<AtlProblemQuickfix> quickfixes;
	private TableViewer tableViewer;
	private Label lblProblems;
	
	private AtlProblemQuickfix selectedElement;
	private Table tblProblems;
	private AnalysisResult analysisResult;
	private Problem problem;
	private SpeculativeMainThread speculativeThread;
	private TableViewer tblViewerProblems;
	private TabFolder tabFolderImpact;
	
	private Table tblFixed;
	private TableViewer tblViewerFixed;

	private Table tblNewProblems;
	private TableViewer tblViewerNewProblems;
	private TabItem tbtmNewProblems;
	private TabItem tbtmAllProblems;
	private TabItem tbtmFixedProblems;
	private SashForm sashForm;
	private Composite composite_1;
	private Composite composite_2;

	
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param problem 
	 * @param analysisResult 
	 */
	public SpeculativeQuickfixDialog(Shell parentShell, AnalysisResult analysisResult, Problem problem, List<AtlProblemQuickfix> quickfixes) {
		super(parentShell);
		setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE);
		setBlockOnOpen(true);
		
		this.analysisResult = analysisResult;
		this.problem = problem;
		this.quickfixes = quickfixes;
	}

	/**
	 * This is called when the dialog is actually created and the widgets are already
	 * available.
	 */
	protected void initThreads() {
		if ( speculativeThread != null ) {
			throw new IllegalStateException();
		}
		speculativeThread = new SpeculativeMainThread(analysisResult, problem, quickfixes);
		speculativeThread.setListener(this);
		speculativeThread.start();
	}

	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		sashForm = new SashForm(container, SWT.NONE);
		sashForm.setSashWidth(5);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label lblFixes = new Label(composite_1, SWT.NONE);
		lblFixes.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		lblFixes.setSize(64, 17);
		lblFixes.setText("Quick fixes");
		
		tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setSize(183, 186);
		
		TextViewer textViewer = new TextViewer(composite_1, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		styledText.setSize(79, 198);
		
				tableViewer.setContentProvider(new QuickfixTableContentProvider());
				tableViewer.setLabelProvider(new QuickfixTableLabelProvider());
				tableViewer.setInput(quickfixes);
				tableViewer.addSelectionChangedListener(new QuickfixSelectionListener());
		
		composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		lblProblems = new Label(composite_2, SWT.NONE);
		lblProblems.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblProblems.setSize(140, 17);
		lblProblems.setText("Problems after quick fix");
		
		TabFolder tabFolder = new TabFolder(composite_2, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSize(330, 119);
		
		tbtmFixedProblems = new TabItem(tabFolder, SWT.NONE);
		tbtmFixedProblems.setText("Fixed problems");
		
		tblViewerFixed = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tblFixed = tblViewerFixed.getTable();
		tbtmFixedProblems.setControl(tblFixed);
		
				tbtmNewProblems = new TabItem(tabFolder, SWT.NONE);
				tbtmNewProblems.setText("New problems");
				
				tblViewerNewProblems = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
				tblNewProblems = tblViewerNewProblems.getTable();
				tbtmNewProblems.setControl(tblNewProblems);
				
						tbtmAllProblems = new TabItem(tabFolder, SWT.NONE);
						tbtmAllProblems.setText("Remaining problems");
						
						tblViewerProblems = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
						tblProblems = tblViewerProblems.getTable();
						tbtmAllProblems.setControl(tblProblems);
						
						
						tblViewerProblems.setContentProvider(new ProblemsViewContentProvider());
						tblViewerProblems.setLabelProvider(new ProblemsViewLabelProvider());
						
								tblViewerFixed.setContentProvider(new ProblemsViewContentProvider());
								tblViewerFixed.setLabelProvider(new ProblemsViewLabelProvider());
								
										tblViewerNewProblems.setContentProvider(new ProblemsViewContentProvider());
										tblViewerNewProblems.setLabelProvider(new ProblemsViewLabelProvider());
		
		Composite composite = new Composite(composite_2, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setSize(375, 140);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
						
						tabFolderImpact = new TabFolder(composite, SWT.NONE);
		sashForm.setWeights(new int[] {1, 2});

		initThreads();
		
		return container;
	}

	class QuickfixSelectionListener implements ISelectionChangedListener {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			AtlProblemQuickfix current = (AtlProblemQuickfix) ((IStructuredSelection) event.getSelection()).getFirstElement();
			if ( speculativeThread.isFinished(current) ) {
				lblProblems.setText("Analysing new problems...");
			} else {
				updateAnalysis(current);
			}
		}		
	}
	
	private void updateAnalysis(AtlProblemQuickfix current) {
		AnalysisResult r = speculativeThread.getAnalysis(current);
		lblProblems.setText("New analysis: " + r.getProblems().size() + " problems");	
		
		tbtmAllProblems.setText("Remaining problems (" + r.getProblems().size() + ")");
		tblViewerProblems.setInput(r);
		tblViewerProblems.refresh();

		// Set<Problem> fixedProblems = new ProblemTracker(this.analysisResult, r).fixedProblems();
		ImpactComputation ic = new ImpactComputation(this.analysisResult, r, (ITracedATLModel) r.getATLModel());
		ic.perform();
		tbtmFixedProblems.setText("Fixed problems (" + ic.getFixedProblems().size() + ")");
		tblViewerFixed.setInput(ic.getFixedProblems());
		tblViewerFixed.refresh();

		tbtmNewProblems.setText("New problems (" + ic.getNewProblems().size() + ")");
		tblViewerNewProblems.setInput(ic.getNewProblems());
		tblViewerNewProblems.refresh();
		
		int i = 0;
		for (ImpactInformation information : DialogExtensions.getImpactInformationExtensions()) {
			TabItem item = null;
			if ( tabFolderImpact.getItemCount() <= i ) {
				item = new TabItem(tabFolderImpact, SWT.NONE);
			} else {
				item = tabFolderImpact.getItem(i);
			}
	
			item.setText(information.getName());
			Composite c = new Composite(tabFolderImpact, SWT.NONE);
			c.setLayout(new FillLayout(SWT.HORIZONTAL));
			item.setControl(c);
			information.initialize(c, r);
	
			i++;
		}
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
		return new Point(695, 436);
	}

	@Override
	protected void okPressed() {
		IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
		if ( selection.size() > 0 ) {
			selectedElement = (AtlProblemQuickfix) selection.getFirstElement();
		}
		super.okPressed();
	}
	
	public AtlProblemQuickfix getQuickfix() {
		return selectedElement;
	}

	
	//
	// Speculative listener callbacks
	//
	
	@Override
	public void finished(Problem p, AtlProblemQuickfix qfx, AnalysisResult r) {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				AtlProblemQuickfix current = (AtlProblemQuickfix) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
				if ( current == qfx ) {
					updateAnalysis(current);
				}
			}
		});
	}
}
