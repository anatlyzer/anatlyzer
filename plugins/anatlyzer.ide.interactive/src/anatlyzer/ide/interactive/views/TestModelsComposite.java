package anatlyzer.ide.interactive.views;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.UncoveredClass;
import anatlyzer.ide.interactive.editors.InteractiveTransformationEditor;
import anatlyzer.ide.interactive.views.InteractiveProcess.DynamicCoverageData;
import anatlyzer.ide.interactive.views.InteractiveProcess.ExecutedModel;
import anatlyzer.ide.model.IInteractiveTransformationModelChanger;
import anatlyzer.ide.model.TestCase;
import anatlyzer.ide.model.TestCasesData;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.ResourceManager;

public class TestModelsComposite extends Composite {

	private Text text;
	private Tree table;
	private TreeViewer tableViewer;
	private InteractiveProcess process;
	private IInteractiveTransformationModelChanger changer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param interactiveTransformationEditor
	 */
	public TestModelsComposite(Composite parent, IInteractiveTransformationModelChanger changer) {
		super(parent, SWT.NONE);
		this.changer = changer;
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setImage(ResourceManager.getPluginImage("org.eclipse.debug.ui", "/icons/full/etool16/run_exc.png"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				runTestCases();
			}
		});
		btnNewButton.setText("Run");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tableViewer = new TreeViewer(composite_1, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		table = tableViewer.getTree();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				openTestCaseModel(tableViewer);
			}
		});
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TreeViewerColumn tableViewerColumn = new TreeViewerColumn(tableViewer, SWT.NONE);
		TreeColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Test case");
		tableViewerColumn.setLabelProvider(new FirstColumnViewLabelProvider());

		TreeViewerColumn tableViewerColumn_status = new TreeViewerColumn(tableViewer, SWT.NONE);
		TreeColumn tblclmnNewColumn_status = tableViewerColumn_status.getColumn();
		tblclmnNewColumn_status.setWidth(100);
		tblclmnNewColumn_status.setText("Status");
		tableViewerColumn_status.setLabelProvider(new SecondColumnViewLabelProvider());

		TreeViewerColumn tableViewerColumn_1 = new TreeViewerColumn(tableViewer, SWT.NONE);
		TreeColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Path");
		tableViewerColumn_1.setLabelProvider(new ThirdColumnViewLabelProvider());

//		TreeViewerColumn tableViewerColumn_3 = new TreeViewerColumn(tableViewer, SWT.NONE);
//		TreeColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
//		tblclmnNewColumn_3.setWidth(100);
//		tblclmnNewColumn_3.setText("Information");
//		tableViewerColumn_3.setLabelProvider(new ThirdColumnViewLabelProvider());

		init();
	}

	protected void runTestCases() {
		if (process != null) {
			try {
				process.executeTestCases();
				tableViewer.refresh();
				
			} catch (CoreException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void openTestCaseModel(@NonNull TreeViewer viewer) {
		TestCase testCase = (TestCase) ((IStructuredSelection) viewer.getSelection()).getFirstElement();
		
		IWorkbenchWindow window= PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		
		IFile file = process.getInteractiveConfigurationFile();
		IEditorDescriptor desc = PlatformUI.getWorkbench().
		        getEditorRegistry().getDefaultEditor(file.getName());
		try {
			IEditorPart part = page.openEditor(new FileEditorInput(file), desc.getId());
			InteractiveTransformationEditor editor = (InteractiveTransformationEditor) part;
			
			ExecutedModel model = process.getExecutedModel(testCase);
			if (model == null)
				throw new IllegalStateException("Can't find execution for " + testCase.getName());
			editor.setCurrentModel(testCase, model);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}

	private void init() {
		ContentProvider provider = new ContentProvider();
		tableViewer.setContentProvider(provider);
		// tableViewer.setLabelProvider(provider);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setAnalysis(InteractiveProcess process) {
		this.process = process;
		tableViewer.setInput(process.getTestCasesData());
	}
	
	@Nullable
	public InteractiveProcess getProcess() {
		return process;
	}
	
	private static class ContentProvider extends ColumnLabelProvider implements IStructuredContentProvider, ITreeContentProvider {

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof ExecutedModel) {
				ExecutedModel em = (ExecutedModel) parentElement;
			}
			return null;
		}

		@Override
		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof ExecutedModel) {
				return false;
			}
			return false;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof TestCasesData) {
				return ((TestCasesData) inputElement).getTestCases().toArray(new Object[0]);
				// return ((DynamicCoverageData) inputElement).getExecutions().toArray(new Object[0]);
			}
			return null;
		}
		
		@Override
		public String getText(Object element) {
//			if (element instanceof ExecutedModel) {
//				ExecutedModel exec = ((ExecutedModel) element);
//				return getInputModels(exec);
//			}

			if (element instanceof TestCase) {
				TestCase t = ((TestCase) element);
				return t.getName();
			}
			return null;
		}
	}
	
	public static class FirstColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {

		@Override
		public String getText(Object element) {
			if (element instanceof TestCase) {
				TestCase t = ((TestCase) element);
				return t.getName();
			}
			return null;
		}
		
		@Override
		public StyledString getStyledText(Object element) {
			return new StyledString(getText(element));
		}
		
	}

	public class SecondColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {

		@Override
		public String getText(Object element) {
			if (element instanceof TestCase) {
				TestCase t = ((TestCase) element);
				return t.getStatus().name();
			}
			return null;
		}
		
		@Override
		public StyledString getStyledText(Object element) {
			String text = getText(element);
			if (text == null)
				return null;
			return new StyledString(text);
		}
	}

	public class ThirdColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {

		@Override
		public String getText(Object element) {
			if (element instanceof TestCase) {
				TestCase t = ((TestCase) element);
				return t.getInputs().values().stream().collect(Collectors.joining(", "));
			}
			return null;
		}
		
		@Override
		public StyledString getStyledText(Object element) {
			String text = getText(element);
			if (text == null)
				return null;
			return new StyledString(text);
		}
	}
}
