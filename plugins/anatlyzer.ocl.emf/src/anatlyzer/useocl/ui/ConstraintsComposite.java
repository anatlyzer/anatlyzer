package anatlyzer.useocl.ui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.ocl.examples.xtext.base.basecs.ConstraintCS;
import org.eclipse.ocl.examples.xtext.base.basecs.ImportCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.PackageDeclarationCS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.graphics.Image;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.jface.viewers.TableViewerColumn;

import anatlyzer.ocl.emf.OclValidator;
import anatlyzer.ocl.emf.OclValidator.ValidationResult;
import anatlyzer.ocl.emf.ResourceToLibrary;
import anatlyzer.useocl.ui.ConstraintsContentProvider.InvariantData;
import anatlyzer.useocl.ui.WitnessProvider.WitnessModel;
import anatlyzer.useocl.ui.WitnessProvider.WitnessModelList;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.StyledText;

public class ConstraintsComposite extends Composite {
	private Text txtOclModel;
	private Table table;
	private CompleteOCLDocumentCS oclResource;
	private TableViewer tableViewer;
	private StyledText txtInfo;
	private Composite cmpModelView;
	private TableViewer tblViewerModel;

	private WitnessModelList witnessFoundList = new WitnessModelList();
	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConstraintsComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Label lblOclModel = new Label(this, SWT.NONE);
		lblOclModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblOclModel.setText("OCL model");
		
		txtOclModel = new Text(this, SWT.BORDER);
		txtOclModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnBrowse = new Button(this, SWT.NONE);
		btnBrowse.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));
		btnBrowse.setText("Browse...");
		
		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		TabItem tbmConstraints = new TabItem(tabFolder, SWT.NONE);
		tbmConstraints.setText("Constraints");
		
		tableViewer = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		tableViewer.setContentProvider(new ConstraintsContentProvider());
		tbmConstraints.setControl(table);
		
	    TableViewerColumn selectCheckBox = createTableViewerColumn("Select", 0, tableViewer);
	    selectCheckBox.getColumn().setResizable(false);
        selectCheckBox.setLabelProvider(new ColumnLabelProvider(){
            public String getText(Object element) {
                if(element instanceof InvariantData && element != null){
                    if(((InvariantData)element).isSelected()){
                        return Character.toString((char)0x2611);
                    }else{
                        return Character.toString((char)0x2610);
                    }
                }
                return super.getText(element);
                //return null;
            }

            @Override
            public Image getImage(Object element) {
                /*if(element instanceof TableMetaData && element != null){
                    if(((TableMetaData)element).getIsSelected()){
                        return checked;
                    }else{
                        return unChecked;
                    }
                }
                return super.getImage(element);*/
                return null;
            }
        });
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE | SWT.CHECK);
		TableColumn tblclmnConstraint = tableViewerColumn.getColumn();
		tblclmnConstraint.setWidth(100);
		tblclmnConstraint.setResizable(true);
		tblclmnConstraint.setText("Class");
		tableViewerColumn.setLabelProvider(new ConstraintsLabelProviders.FirstColumnViewLabelProvider());
		// tableViewerColumn.setEditingSupport(new InvariantCheckEditingSupport(tableViewer));
		
		TableViewerColumn tableViewerColumn2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnConstraint2 = tableViewerColumn2.getColumn();
		tblclmnConstraint2.setWidth(100);
		tblclmnConstraint2.setResizable(true);
		tblclmnConstraint2.setText("Constraint");
		tableViewerColumn2.setLabelProvider(new ConstraintsLabelProviders.SecondColumnViewLabelProvider());

//		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
//		TableColumn tblclmnInfo = tableViewerColumn_1.getColumn();
//		tblclmnInfo.setWidth(100);
//		tblclmnInfo.setText("Info");

		selectCheckBox.getColumn().setWidth(50);
		//tblclmnConstraint.setWidth(40);
		//tblclmnConstraint2.setWidth(40);
		
//		
//	    TableColumnLayout tableLayout = new TableColumnLayout();
//	    parent.setLayout(tableLayout);
//
//		table.setColumnData(selectCheckBox.getColumn(), new ColumnWeightData(10));
//		table.setColumnData(tblclmnConstraint.getColumn(), new ColumnWeightData(90));

	    //Set column type (checkbox)
		selectCheckBox.setEditingSupport(new InvariantCheckEditingSupport(tableViewer));
		
		TabItem tbtmModels = new TabItem(tabFolder, SWT.NONE);
		tbtmModels.setText("Models");
		
		SashForm sashForm = new SashForm(tabFolder, SWT.NONE);
		tbtmModels.setControl(sashForm);
		
		tblViewerModel = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		Table tblModels = tblViewerModel.getTable();
		
		cmpModelView = new Composite(sashForm, SWT.NONE);
		cmpModelView.setLayout(new FillLayout(SWT.HORIZONTAL));
		sashForm.setWeights(new int[] {1, 3});
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_composite = new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1);
		gd_composite.widthHint = 444;
		composite.setLayoutData(gd_composite);
		
		Button btnGenExample = new Button(composite, SWT.NONE);
		btnGenExample.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generateExample();
			}
		});
		btnGenExample.setText("Generate example");
		
		Button btnComplete = new Button(composite, SWT.NONE);
		btnComplete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				completeModel();
			}
		});
		btnComplete.setText("Complete model");
		
		Button btnSaveToXmi = new Button(composite, SWT.NONE);
		btnSaveToXmi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveToXmi();
			}
		});
		btnSaveToXmi.setText("Save to XMI");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txtInfo = new StyledText(composite_1, SWT.BORDER);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
	 
		
		WitnessProvider wp = new WitnessProvider();
		tblViewerModel.setContentProvider(wp);
		tblViewerModel.setLabelProvider(wp);
	}

	//Creates the column
    private TableViewerColumn createTableViewerColumn(String header, int idx, TableViewer tableViewer2) 
    {
        //To put checkbox centered in cell.
        int infoLocation = SWT.LEFT;
        if(idx == 1){
            infoLocation = SWT.CENTER;
        }
        TableViewerColumn column = new TableViewerColumn(tableViewer2, infoLocation, idx);
        column.getColumn().setText(header);
        column.getColumn().setResizable(true);
        column.getColumn().setMoveable(true);

        return column;
    }
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setInput(CompleteOCLDocumentCS doc) {
		this.oclResource = doc;
		txtOclModel.setText(doc.eResource().getURI().toString());
		tableViewer.setInput(new ConstraintsContentProvider.OclDocumentData(doc));
		tableViewer.refresh();
	}

	//
	// Button actions
	// 
    protected void generateExample() {    
    	ConstraintsContentProvider.OclDocumentData data = (ConstraintsContentProvider.OclDocumentData) tableViewer.getInput();
    	// List<ConstraintCS> constraints = data.getInvariants().stream().map(i -> i.getConstraint()).collect(Collectors.toList());
    	
    	OclValidator validator = new OclValidator();
    	
    	// Add the meta-models
		CompleteOCLDocumentCS doc = data.getDoc();
		for(PackageDeclarationCS i : doc.getPackages()) {
			validator.addMetamodel(i.getPackage().getEPackage());
		}

		// Add the constraints
		for (InvariantData i : data.getInvariants()) {
			if ( i.isSelected() )
				validator.addConstraint(i.getConstraint());
		}
	
		try {
			validator.invoke();
			ValidationResult result = validator.getResult();
			if ( result.sat() ) {
				showMessage("SAT!");
				witnessFoundList.createModel(result.getWitnessModel());
				this.tblViewerModel.setInput(witnessFoundList);
				this.tblViewerModel.refresh();
				UIUtils.createModelViewer(cmpModelView, result.getWitnessModel().getModelAsOriginal(), null);
			} else if ( result.unsat() ) {
				showMessage("UNSAT!");
			} else {
				showMessage("There are errors");
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			showMessage(e.getMessage());
		}
	}


	protected void saveToXmi() {
		IStructuredSelection s = (IStructuredSelection) this.tblViewerModel.getSelection();
		WitnessModel m = (WitnessModel) s.getFirstElement();
		
		IResource r = UIUtils.showChooseNewFileDialog(getShell());
		if ( r != null ) {		
			try {
				m.getModel().getModelAsOriginal().save(new FileOutputStream(r.getFullPath().toOSString()), null);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	protected void completeModel() {
		IResource r = UIUtils.showChooseChooseFileDialog(getShell());
		
		
	}

	private void showMessage(String message) {
		txtInfo.setText(message);
	}

	
}
