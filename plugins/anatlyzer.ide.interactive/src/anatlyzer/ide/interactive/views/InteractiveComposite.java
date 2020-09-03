package anatlyzer.ide.interactive.views;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.ClassCoverage;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.FullyCovered;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.FullyUncoveredClass;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.PossiblyUncoveredClass;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InteractiveComposite extends Composite {
	private Text text;
	private Tree table;
	private TreeViewer tableViewer;
	private InteractiveProcess process;
	private Predicate<EClass> classFilter = this::hideAbstractClass;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InteractiveComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnCheckButton = new Button(composite, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckButton.getSelection()) 
					InteractiveComposite.this.classFilter = (c) -> true;
				else
					InteractiveComposite.this.classFilter = InteractiveComposite.this::hideAbstractClass;
			}
		});
		btnCheckButton.setText("Show abstract");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tableViewer = new TreeViewer(composite_1, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		table = tableViewer.getTree();
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TreeViewerColumn tableViewerColumn = new TreeViewerColumn(tableViewer, SWT.NONE);
		TreeColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Element");
		tableViewerColumn.setLabelProvider(new FirstColumnViewLabelProvider());
		
		TreeViewerColumn tableViewerColumn_1 = new TreeViewerColumn(tableViewer, SWT.NONE);
		TreeColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Coverage");
		tableViewerColumn_1.setLabelProvider(new SecondColumnViewLabelProvider());

		TreeViewerColumn tableViewerColumn_3 = new TreeViewerColumn(tableViewer, SWT.NONE);
		TreeColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Information");
		tableViewerColumn_3.setLabelProvider(new ThirdColumnViewLabelProvider());

		init();
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
		tableViewer.setFilters(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof EClass) {
					return classFilter.test((EClass) element);
				}
				return true;
			}
		});
		tableViewer.setInput(process.getInputMetamodels());
	}
	
	@Nullable
	public InteractiveProcess getProcess() {
		return process;
	}
	
	private static class ContentProvider extends ColumnLabelProvider implements IStructuredContentProvider, ITreeContentProvider {

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof MetamodelNamespace) {
				MetamodelNamespace ns = (MetamodelNamespace) parentElement;
				return ns.getAllClasses().toArray(new Object[0]);
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
			if (element instanceof MetamodelNamespace) {
				return true;
			}
			return false;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List) inputElement).toArray(new Object[0]);
			}
			return null;
		}
		
		@Override
		public String getText(Object element) {
			if (element instanceof MetamodelNamespace) {
				return ((MetamodelNamespace) element).getName();
			} else if (element instanceof EClass) {
				return ((EClass) element).getName();
			}
			return null;
		}
	}
	
	public static class FirstColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {

		@Override
		public String getText(Object element) {
			if (element instanceof MetamodelNamespace) {
				return ((MetamodelNamespace) element).getName();
			} else if (element instanceof EClass) {
				return ((EClass) element).getName();
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
			if (process == null)
				return null;
			
			if (element instanceof EClass) {
				ClassCoverage c = process.getCoverageData().getCoverage((EClass) element);
				if (c == null)
					return "??";
				if (c instanceof FullyUncoveredClass) 
					return "U";
				if (c instanceof PossiblyUncoveredClass) 
					return "P";
				if (c instanceof FullyCovered) 
					return "C";
				
				// This shouldn't happen
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
			if (element instanceof EClass) {
				ClassCoverage c = process.getCoverageData().getCoverage((EClass) element);
				return c.getRules().stream().map(r -> r.getName()).collect(Collectors.joining(", "));
			} else {
				// % Compute a percentage?
				return null;
			}
		}
		
		@Override
		public StyledString getStyledText(Object element) {
			String text = getText(element);
			if (text == null)
				return null;
			return new StyledString(text);
		}
	}
	
	private boolean hideAbstractClass(EClass c) {
		return ! c.isAbstract();
	}
}
