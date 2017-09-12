package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.impact.ImpactComputation;
import anatlyzer.atl.model.ATLModel.ITracedATLModel;

public class SpeculativeQuickfixComposite extends Composite implements  SpeculativeListener {
	private Table table;
	private List<AtlProblemQuickfix> quickfixes;
	private TableViewer tableViewer;
	private Label lblProblems;
	
	private Table tblProblems;
	private AnalysisResult analysisResult;
	private Problem problem;
	private SpeculativeMainThread speculativeThread;
	private TableViewer tblViewerProblems;
	
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
	private TextViewer quickfixInformationText;
	private TabFolder tabFolder;

	private Group textImpact;
	//private TabFolder tabFolderImpact;

	
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param problem 
	 * @param analysisResult 
	 */
	public SpeculativeQuickfixComposite(Composite parent, AnalysisResult analysisResult, Problem problem, List<AtlProblemQuickfix> quickfixes) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));

		this.analysisResult = analysisResult;
		this.problem = problem;
		this.quickfixes = quickfixes;

		init(parent);
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	protected void init(Composite container) {
//		Composite container = (Composite) super.createDialogArea(parent);
//		container.setLayout(new GridLayout(1, false));
//		container.setSize(800, 600);
		
		sashForm = new SashForm(this, SWT.NONE);
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
		
		quickfixInformationText = new TextViewer(composite_1, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		StyledText styledText = quickfixInformationText.getTextWidget();
		styledText.setWordWrap(true);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		styledText.setSize(79, 198);

		TableViewerColumn columnNumProblems = new TableViewerColumn(tableViewer, SWT.NONE);
		columnNumProblems.getColumn().setWidth(50);
		columnNumProblems.getColumn().setText("#");
		columnNumProblems.getColumn().setMoveable(true);
		ColumnViewerComparator problemSorter =  QuickfixTableLabelProvider.createFoundProblemsComparator(tableViewer, columnNumProblems);
				
		TableViewerColumn columnQfx = new TableViewerColumn(tableViewer, SWT.NONE);
		columnQfx.getColumn().setWidth(200);
		columnQfx.getColumn().setText("Quickfix");
		columnQfx.getColumn().setMoveable(true);
		QuickfixTableLabelProvider.createDisplayStringComparator(tableViewer, columnQfx);
		
		tableViewer.getTable().setLinesVisible(true);
		tableViewer.getTable().setHeaderVisible(true);
		
		tableViewer.setContentProvider(new QuickfixTableContentProvider());
		tableViewer.setLabelProvider(new QuickfixTableLabelProvider(true));
		
		tableViewer.setInput(quickfixes);
		tableViewer.addSelectionChangedListener(new QuickfixSelectionListener());
		
		problemSorter.setSorter(problemSorter, ColumnViewerComparator.ASC);
				
				
		composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		lblProblems = new Label(composite_2, SWT.NONE);
		lblProblems.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblProblems.setSize(140, 17);
		lblProblems.setText("Problems after quick fix");
		
		tabFolder = new TabFolder(composite_2, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSize(330, 119);
		
		tbtmFixedProblems = new TabItem(tabFolder, SWT.NONE);
		tbtmFixedProblems.setText("Fixed problems");
		
		tblViewerFixed = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tblFixed = tblViewerFixed.getTable();
		tbtmFixedProblems.setControl(tblFixed);
		
		tbtmNewProblems = new TabItem(tabFolder, SWT.NONE);
		tbtmNewProblems.setText("New problems");

		tblViewerNewProblems = new TableViewer(tabFolder, SWT.BORDER
				| SWT.FULL_SELECTION);
		tblNewProblems = tblViewerNewProblems.getTable();
		tbtmNewProblems.setControl(tblNewProblems);

		tbtmAllProblems = new TabItem(tabFolder, SWT.NONE);
		tbtmAllProblems.setText("Remaining problems");

		tblViewerProblems = new TableViewer(tabFolder, SWT.BORDER
				| SWT.FULL_SELECTION);
		tblProblems = tblViewerProblems.getTable();
		tbtmAllProblems.setControl(tblProblems);

		tblViewerProblems.setContentProvider(new ProblemsViewContentProvider());
		tblViewerProblems.setLabelProvider(new ProblemsViewLabelProvider());

		tblViewerFixed.setContentProvider(new ProblemsViewContentProvider());
		tblViewerFixed.setLabelProvider(new ProblemsViewLabelProvider());

		tblViewerNewProblems
				.setContentProvider(new ProblemsViewContentProvider());
		tblViewerNewProblems.setLabelProvider(new ProblemsViewLabelProvider());
		
		Composite composite = new Composite(composite_2, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setSize(375, 140);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
			
		textImpact = new Group(composite, SWT.BORDER); 
		textImpact.setLayout(new FillLayout(SWT.VERTICAL));
		
		// tabFolderImpact = new TabFolder(composite, SWT.NONE);
		
		
		sashForm.setWeights(new int[] {2, 3});

		initThreads();
		
	}

	private AtlProblemQuickfix selectedQuickfix;

	class QuickfixSelectionListener implements ISelectionChangedListener {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			AtlProblemQuickfix current = (AtlProblemQuickfix) ((IStructuredSelection) event.getSelection()).getFirstElement();
			SpeculativeQuickfixComposite.this.selectedQuickfix = current;
			if ( speculativeThread.isFinished(current) ) {
				lblProblems.setText("Analysing new problems...");
			} else {
				updateAnalysis(current);
			}
		}		
	}
	
	private void updateAnalysis(AtlProblemQuickfix current) {
		AnalysisResult r = speculativeThread.getAnalysis(current);
		
		// This happens when the quick fix requires user intervention
		if ( current.requiresUserIntervention() ) {
			tabFolder.setVisible(false);
			// tabFolderImpact.setVisible(false);
			textImpact.setVisible(false);
			lblProblems.setText("Speculative analysis not possible. Quick fix requires user intervention.");
			this.quickfixInformationText.getTextWidget().setText("");
			return;			
		} else if ( r == null ) {
			tabFolder.setVisible(false);
			// tabFolderImpact.setVisible(false);
			textImpact.setVisible(false);
			lblProblems.setText("There was an internal error in the speculative analysis. Sorry!");
			this.quickfixInformationText.getTextWidget().setText("");
			return;			
		}
		
		tabFolder.setVisible(true);
		textImpact.setVisible(true);
		
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
		
		lblProblems.setText(ic.isFixed(this.problem)? "Original problem fixed" : "Original problem not fixed");


		String info = current.getAdditionalProposalInfo() + "\n\n";
		
		if ( ic.isFixed(this.problem) ) {
			info += "The fix will solve the problem, ";
		} else {
			info += "The fix will NOT solve the problem, ";			
		}

		if ( ic.getNewProblems().size() > 0 ) {
			info += "produces " + ic.getNewProblems().size() + " new problems, " ;
		} else {
			info += "produces no new problems, " ;			
		}
		
		if ( ic.getFixedProblems().size() > 1 ) {
			info += "and fixes " + (ic.getFixedProblems().size() - 1) + " additional problems.";
		} else {		
			info += "and it does not fix any additional problem.";
		}
		
		this.quickfixInformationText.getTextWidget().setText(info);
		
		
//		int i = 0;
//		for (ImpactInformation information : DialogExtensions.getImpactInformationExtensions()) {
//			TabItem item = null;
//			if ( tabFolderImpact.getItemCount() <= i ) {
//				item = new TabItem(tabFolderImpact, SWT.NONE);
//			} else {
//				item = tabFolderImpact.getItem(i);
//			}
//	
//			item.setText(information.getName());
//			Composite c = new Composite(tabFolderImpact, SWT.NONE);
//			c.setLayout(new FillLayout(SWT.HORIZONTAL));
//			item.setControl(c);
//			information.initialize(c, current, ic);
//			i++;
//		
//		}
//		tabFolderImpact.pack();
//		tabFolderImpact.getParent().layout();			

		for (Control control : textImpact.getChildren()) {
			control.dispose();
		}
		
		List<ImpactInformation> infoImpact = DialogExtensions.getImpactInformationExtensions();
		if ( infoImpact.size() > 0 ) {
			infoImpact.get(0).initialize(textImpact, current, ic);
		}
		textImpact.pack(true);
		textImpact.getParent().layout();			
		
	}
	
	//
	// Speculative listener callbacks
	//
	
	@Override
	public void finished(Problem p, AtlProblemQuickfix qfx, AnalysisResult r) {
		// Set data about the found problems inside the quickfix
		if ( r != null ) {
			qfx.setData(ISpeculativeConstants.FOUND_PROBLEMS, r.getProblems().size());
		} else {
			qfx.setData(ISpeculativeConstants.FOUND_PROBLEMS, null);			
		}
		
		getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				AtlProblemQuickfix current = (AtlProblemQuickfix) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
				if ( current == qfx ) {
					updateAnalysis(current);
				}
				tableViewer.refresh(true);
			}
		});
	}

	public AtlProblemQuickfix getSelected() {
		return selectedQuickfix;
//		IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
//		if ( selection.size() > 0 ) {
//			return (AtlProblemQuickfix) selection.getFirstElement();
//		}
//		return null;
	}
	
	
}
