package anatlyzer.atl.editor.quickfix;

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
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;

public class QuickfixDialog extends Dialog {
	private Table table;
	private List<AtlProblemQuickfix> quickfixes;
	private TableViewer tableViewer;
	private AtlProblemQuickfix selectedElement;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public QuickfixDialog(Shell parentShell, List<AtlProblemQuickfix> quickfixes) {
		super(parentShell);
		setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE);
		setBlockOnOpen(true);
		
		this.quickfixes = quickfixes;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		Label lblFixes = new Label(container, SWT.NONE);
		lblFixes.setText("Fixes");
		
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TextViewer textViewer = new TextViewer(container, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tableViewer.setContentProvider(new QuickfixContentProvider());
		tableViewer.setLabelProvider(new QuickfixLabelProvider());
		tableViewer.setInput(quickfixes);
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
		return new Point(450, 300);
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
