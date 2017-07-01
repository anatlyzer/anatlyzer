package anatlyzer.ocl.ui;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
// import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.IWitnessVisualizer;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.ocl.ui.IConstraintCheckerBridge.TranslationResult;

public class CheckConstraintDialog extends Dialog {
	private Text txtMetamodel;
	private Resource mmResource;
	private StyledText oclText;
	private Composite lblImage;
	private ScrolledComposite scrolledComposite;
	private Composite NoscrolledComposite;
	private Combo combo;
	private List<IConstraintCheckerBridge> bridges;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param resource 
	 */
	public CheckConstraintDialog(Shell parentShell, Resource resource) {
		super(parentShell);
		setShellStyle(SWT.RESIZE);
		this.mmResource = resource;
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
		
		oclText = new StyledText(composite, SWT.BORDER);
		oclText.setFont(SWTResourceManager.getFont("Monospace", 10, SWT.NORMAL));
		oclText.setSize(472, 129);
		oclText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
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
		
		combo = new Combo(composite_1, SWT.NONE);
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
		
		// scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		NoscrolledComposite = new Composite(container, SWT.NONE);;
		NoscrolledComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
//		lblImage = new Label(scrolledComposite, SWT.NONE);
//		lblImage.setText("Witness model");
//		lblImage = new Composite(scrolledComposite, SWT.NONE);
//		lblImage.setLayout(new FillLayout(SWT.VERTICAL));
//		scrolledComposite.setContent(lblImage);
//		scrolledComposite.setMinSize(lblImage.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		initValues();
		
		return container;
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
		return new Point(800, 600);
	}
	
	//
	// Behaviour methods
	//

	private void initValues() {
		this.txtMetamodel.setText(mmResource.getURI().toString());
		this.combo.removeAll();
		
		this.bridges = new ArrayList<>(OCLExtensionsUtil.getLanguageBridges());
		for (IConstraintCheckerBridge b: bridges) {
			this.combo.add(b.getName());
		}
		if ( bridges.size() > 0 ) {
			this.combo.select(0);
		}
	}

	protected void loadMetamodel() {
		// TODO Auto-generated method stub
		
	}

	protected void browseMetamodel() {
		// TODO Auto-generated method stub
		
	}

	protected void evaluateExpression() {
		if ( combo.getSelectionIndex() == -1 )
			return;
		
		IConstraintCheckerBridge b = bridges.get(combo.getSelectionIndex());
		
		TranslationResult result = b.translate(this.oclText.getText(), mmResource);
		if ( result.hasErrors() ) {
			showError(result.getErrors().stream().collect(Collectors.joining("\n")));
		}
		
		List<OclExpression> expr = result.getExpressions();
		
		try {
			ConstraintSatisfactionChecker checker = ConstraintSatisfactionChecker.
				withExpr(expr).
				withFinder(WitnessUtil.getFirstWitnessFinder()).
				configureMetamodel("MM", mmResource).
				check();
			
			System.out.println(checker.getFinderResult());
			if ( AnalyserUtils.isConfirmed(checker.getFinderResult()) ) {
				drawWitness(checker.getWitnessModel());
				//checker.getWitnessModel();
			} else {
				showError("Constraint NOT satisfiable.");
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			showError(e.getMessage());
		}
		
	}

	private void showError(String text) {
		Label l = new Label(NoscrolledComposite, SWT.NONE);
		l.setText(text);
		NoscrolledComposite.pack();
		return;		
	}
	
	private void drawWitness(IWitnessModel witness) {
//		for (Control ctrl : lblImage.getChildren()) {
//			ctrl.dispose();
//		}
//
//		if (witness == null) {
//			Label l = new Label(lblImage, SWT.NONE);
//			l.setText("No witness is available");
//			return;
//		}
//
		IWitnessVisualizer visualizer = WitnessUtil.getWitnessVisualizer(witness);
//		if (visualizer == null) {
//			Label l = new Label(lblImage, SWT.NONE);
//			l.setText("No visualizer is available.");
//			return;
//		}

		for (Control ctrl : NoscrolledComposite.getChildren()) {
		ctrl.dispose();
		}
		visualizer.render(NoscrolledComposite);
		NoscrolledComposite.pack();

	}

	
}
