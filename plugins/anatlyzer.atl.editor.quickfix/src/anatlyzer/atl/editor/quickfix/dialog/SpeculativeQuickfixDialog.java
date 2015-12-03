package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
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

public class SpeculativeQuickfixDialog extends Dialog {
	private Table table;
	private List<AtlProblemQuickfix> quickfixes;
	private TableViewer tableViewer;
	private Label lblProblems;
	
	private AtlProblemQuickfix selectedElement;
	private Table tblProblems;
	private AnalysisResult analysisResult;
	private Problem problem;
	private SpeculativeMainThread speculativeThread;

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
		speculativeThread.start();
	}

	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		
		Label lblFixes = new Label(container, SWT.NONE);
		lblFixes.setText("Quick fixes");
		
		lblProblems = new Label(container, SWT.NONE);
		lblProblems.setText("Problems after quick fix");
		
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite.minimumWidth = 200;
		composite.setLayoutData(gd_composite);
		
		TableViewer tableViewer_1 = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tblProblems = tableViewer_1.getTable();
		
		TextViewer textViewer = new TextViewer(container, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		new Label(container, SWT.NONE);

		tableViewer.setContentProvider(new QuickfixContentProvider());
		tableViewer.setLabelProvider(new QuickfixLabelProvider());
		tableViewer.setInput(quickfixes);
		tableViewer.addSelectionChangedListener(new QuickfixSelectionListener());
		
		
		initThreads();
		
		return container;
	}

	class QuickfixContentProvider implements IStructuredContentProvider {
		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {			
			return quickfixes.toArray();
		}
	}
	
	class QuickfixLabelProvider implements ITableLabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {	}

		@Override
		public void dispose() { }

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) { }

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			AtlProblemQuickfix qf = (AtlProblemQuickfix) element;
			if ( columnIndex == 0 ) {
				return qf.getDisplayString();
			}
			return null;
		}
		
	}
	
	class QuickfixSelectionListener implements ISelectionChangedListener {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			AtlProblemQuickfix current = (AtlProblemQuickfix) ((IStructuredSelection) event.getSelection()).getFirstElement();
			if ( speculativeThread.isFinished(current) ) {
				lblProblems.setText("Analysing new problems...");
			} else {
				AnalysisResult r = speculativeThread.getAnalysis(current);
				lblProblems.setText("New analysis: " + r.getProblems().size() + " problems");		
			}
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
		return new Point(575, 335);
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
}
