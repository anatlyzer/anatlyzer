package anatlyzer.atl.editor.quickfix.ui;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class AskFeature extends Dialog {
	private Text txtFeatureName;
	private Text txtCardinality;
	
	private String featureName;
	private Metaclass enclosingType;
	private Type featureType;
	private int lowerBound = 0;
	private int upperBound = 1;
	private boolean isContainment;
	private Combo cmbTypes;
	private String featureTypeStr;
	private Button chkContainment;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AskFeature(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(3, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNewLabel.setText("Feature name: ");
		
		txtFeatureName = new Text(container, SWT.BORDER);
		GridData gd_txtFeatureName = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_txtFeatureName.widthHint = 344;
		txtFeatureName.setLayoutData(gd_txtFeatureName);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("Cardinality:");
		
		txtCardinality = new Text(container, SWT.BORDER);
		txtCardinality.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setText("0..1, 1..1, 0..*, 1..*, *");
		
		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setAlignment(SWT.RIGHT);
		lblNewLabel_2.setText("Type:");
		
		cmbTypes = new Combo(container, SWT.NONE);
		cmbTypes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		cmbTypes.setText("cmbTypes");
		new Label(container, SWT.NONE);
		
		chkContainment = new Button(container, SWT.CHECK);
		chkContainment.setText("Containment (only for references)");
		new Label(container, SWT.NONE);

		initValues();
		
		return container;
	}

	private void initValues() {
		if ( featureName != null ) txtFeatureName.setText(featureName);
		
		txtCardinality.setText(lowerBound + ".." + (upperBound == -1 ? "*" : upperBound));

		cmbTypes.removeAll();
		if ( enclosingType != null ) {
			ASTUtils.getMetamodelClasses(enclosingType).forEach(c -> cmbTypes.add(c.getName()));
		}

		cmbTypes.add("EString");
		cmbTypes.add("EInteger");
		cmbTypes.add("EBoolean");
		cmbTypes.add("EDouble");
		cmbTypes.add("EFloat");
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

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(467, 226);
	}

	public void setFeatureName(String name) {
		this.featureName = name;
	}

	public void setEnclosingType(Metaclass type) {
		this.enclosingType = type;
	}
	
	public void setFeatureType(Type t) {
		this.featureType = t;
	}

	public void setBounds(int lower, int upper) {
		this.lowerBound = lower;
		this.upperBound = upper;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if ( buttonId == IDialogConstants.OK_ID ) {
			this.featureName = txtFeatureName.getText();
			if ( txtCardinality.getText().trim().equals("*") ) {
				this.lowerBound = 0;
				this.upperBound = -1;
			} else if ( txtCardinality.getText().trim().equals("1") ) {
				this.lowerBound = 0;
				this.upperBound = 1;				
			} else {
				this.lowerBound = Integer.parseInt(txtCardinality.getText().split("\\.\\.")[0].trim());
				String up = txtCardinality.getText().split("\\.\\.")[1].trim();
				this.upperBound = up.equals("*") ? -1 : Integer.parseInt(up);
			}
			this.featureTypeStr = cmbTypes.getText();
			this.isContainment = chkContainment.getSelection();
		}
		super.buttonPressed(buttonId);
	}
	
	
	public String getFeatureName() {
		return this.featureName;
	}
	
	public int getUpperBound() {
		return this.upperBound;
	}
	
	public int getLowerBound() {
		return lowerBound; 
	}
	
	public String getFeatureType() {
		return featureTypeStr;
	}
	
	public boolean isContainment() {
		return isContainment;
	}
	
}
