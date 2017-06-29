package anatlyzer.ocl.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CheckConstraintDialog extends Dialog {
	private Text txtMetamodel;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public CheckConstraintDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.VERTICAL));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		StyledText styledText = new StyledText(composite, SWT.BORDER);
		styledText.setFont(SWTResourceManager.getFont("Monospace", 10, SWT.NORMAL));
		styledText.setSize(472, 129);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_1.setBounds(0, 0, 64, 64);
		composite_1.setLayout(new GridLayout(6, false));
		
		Label lblEcore = new Label(composite_1, SWT.NONE);
		lblEcore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEcore.setText("Ecore:");
		
		txtMetamodel = new Text(composite_1, SWT.BORDER);
		txtMetamodel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		Button btnBrowse = new Button(composite_1, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				browseMetamodel();
			}
		});
		btnBrowse.setText("...");
		
		Button btnLoad = new Button(composite_1, SWT.NONE);
		btnLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadMetamodel();
			}
		});
		btnLoad.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		btnLoad.setText("Load");
		
		Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setItems(new String[] {"ATL/OCL", "EMF/OCL"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		combo.select(0);
		
		Button btnEvaluate = new Button(composite_1, SWT.NONE);
		btnEvaluate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				evaluateExpression();
			}
		});
		btnEvaluate.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		btnEvaluate.setText("Evaluate");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Label lblImage = new Label(scrolledComposite, SWT.NONE);
		lblImage.setText("Witness model");
		scrolledComposite.setContent(lblImage);
		scrolledComposite.setMinSize(lblImage.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		return container;
	}

	protected void loadMetamodel() {
		// TODO Auto-generated method stub
		
	}

	protected void evaluateExpression() {
		// TODO Auto-generated method stub
		
	}

	protected void browseMetamodel() {
		// TODO Auto-generated method stub
		
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
		return new Point(484, 424);
	}
}
