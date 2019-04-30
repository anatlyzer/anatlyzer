package anatlyzer.useocl.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLCSResource;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;
import org.eclipse.ocl.xtext.completeoclcs.PackageDeclarationCS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import anatlyzer.atl.witness.IScrollingIterator;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atl.witness.XMIPartialModel;
import anatlyzer.ocl.emf.OclValidator;
import anatlyzer.ocl.emf.OclValidator.ValidationResult;
import anatlyzer.useocl.ui.ConstraintsContentProvider.InvariantData;
import anatlyzer.useocl.ui.WitnessProvider.WitnessModel;
import anatlyzer.useocl.ui.WitnessProvider.WitnessModelList;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

public class ConstraintsComposite extends Composite {
	private Text txtOclModel;
	private Table table;
	private CompleteOCLDocumentCS oclResource;
	private TableViewer tableViewer;
	private StyledText txtInfo;
	private Composite cmpModelView;
	private TableViewer tblViewerModel;

	private WitnessModelList witnessFoundList = new WitnessModelList();
	private TreeViewer treeViewer;
	private IProject project;
	private boolean useScrolling;
	
	
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
		tblclmnConstraint.setText("Constraint");
		tableViewerColumn.setLabelProvider(new ConstraintsLabelProviders.FirstColumnViewLabelProvider());
		// tableViewerColumn.setEditingSupport(new InvariantCheckEditingSupport(tableViewer));
		
		TableViewerColumn tableViewerColumn2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnConstraint2 = tableViewerColumn2.getColumn();
		tblclmnConstraint2.setWidth(100);
		tblclmnConstraint2.setResizable(true);
		tblclmnConstraint2.setText("Class");
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
		tblViewerModel.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				showSelectedModel();
			}
		});
		Table tblModels = tblViewerModel.getTable();
		
		cmpModelView = new Composite(sashForm, SWT.NONE);
		cmpModelView.setLayout(new FillLayout(SWT.HORIZONTAL));
		sashForm.setWeights(new int[] {1, 3});
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_composite = new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1);
		gd_composite.widthHint = 444;
		composite.setLayoutData(gd_composite);

		Button btnVerifyConstraints = new Button(composite, SWT.NONE);
		btnVerifyConstraints.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO: Use the different automatic bounds
				generateExample(); 
			}
		});
		btnVerifyConstraints.setText("Verify constraints");

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

		Button btnUseScrolling = new Button(composite, SWT.CHECK);
		btnUseScrolling.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConstraintsComposite.this.useScrolling = ((Button) e.getSource()).getSelection();				
			}
		});
		btnUseScrolling.setText("Scrolling");

//		Button btnSaveToXmi = new Button(composite, SWT.NONE);
//		btnSaveToXmi.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				saveToXmi();
//			}
//		});
//		
//		btnSaveToXmi.setText("Save to XMI");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		txtInfo = new StyledText(composite_1, SWT.BORDER);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		Button errorDetails = new Button(composite_1, SWT.NONE);
		errorDetails.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveToXmi();
			}
		});
		
		errorDetails.setText("Show errors");
	 
		
		WitnessProvider wp = new WitnessProvider();
		tblViewerModel.setContentProvider(wp);
		tblViewerModel.setLabelProvider(wp);
	}

	protected void showSelectedModel() {
		ISelection s = tblViewerModel.getSelection();
		if ( s instanceof StructuredSelection ) {
			WitnessModel m = (WitnessModel) ((StructuredSelection) s).getFirstElement();
			this.treeViewer = UIUtils.createModelViewer(cmpModelView, m.getModel().getModelAsOriginal(), null, this.treeViewer);			
			// UIUtils.createModelViewer(cmpModelView, m.getModel().getModelAsOriginal(), null);
		}
		
	}
	
	private boolean isScrolling() {
		return useScrolling;
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

	public void setInput(CompleteOCLDocumentCS doc, IProject project) {
		this.oclResource = doc;
		this.project = project;
		txtOclModel.setText(doc.eResource().getURI().toString());
		tableViewer.setInput(new ConstraintsContentProvider.OclDocumentData(doc));
		tableViewer.refresh();
	}

	//
	// Button actions
	// 
    protected void generateExample() {
    	generateExample(null, isScrolling());
    }

    protected void generateExample(XMIPartialModel partialModel, boolean useScrolling) {
    	ConstraintsContentProvider.OclDocumentData data = (ConstraintsContentProvider.OclDocumentData) tableViewer.getInput();
    	// List<ConstraintCS> constraints = data.getInvariants().stream().map(i -> i.getConstraint()).collect(Collectors.toList());
    	
    	OclValidator validator = new OclValidator();
    	IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder();
    	validator.withWitnessFinder(wf);
    	if ( isScrolling() ) {
    		wf.setScrollingMode(UseWitnessFinder.ScrollingMode.ONE);
    	}
    	
    	// Add the meta-models
		CompleteOCLDocumentCS doc = data.getDoc();
		
		for(PackageDeclarationCS i : doc.getOwnedPackages()) {
			validator.addMetamodel(i.getReferredPackage().getEPackage());
		}

		// Add the bounds after having configured the packages, so that the bounds can be checked against real classes
		for(PackageDeclarationCS i : doc.getOwnedPackages()) {
			extractBounds(i, validator);
		}

		CompleteOCLCSResource r = (CompleteOCLCSResource) doc.eResource();
		//@NonNull ASResource pivotResource = r.getCS2AS().getASResource();
		validator.addOclDefinition(r);
		
		
//		for (ImportCS i : doc.getOwnedImports()) {
//			if ( i.getPivot() instanceof Import ) {
//				Import imp = (Import) i.getPivot();
//				Namespace ns = imp.getImportedNamespace();
//				if ( ns instanceof Model ) {
//					Model m = (Model) ns;
//					System.out.println(m);
//				}
//			}
//			System.out.println(i.getPivot());
//			
//		}
		
//		// Add the constraints
//		for (InvariantData i : data.getInvariants()) {
//			if ( i.isSelected() )
//				validator.addConstraint(i.getConstraint());
//		}

		
		if ( partialModel != null ) {
			validator.setPartialModel(partialModel);
		}
		
		try {
			validator.invoke();
			ValidationResult result = validator.getResult();
			if ( result.sat() ) {
				showMessage("SAT!");
				
				if ( ! isScrolling() ) {
					Resource newModel = result.getWitnessModel().getModelAsOriginal();
					if (project != null) {
						IFolder outputs = getOutputFolder();
												
						saveModelAutomatically(newModel, outputs, "output");
					}
					
					witnessFoundList.createModel(result.getWitnessModel());
					this.tblViewerModel.setInput(witnessFoundList);
					this.tblViewerModel.refresh();
					this.treeViewer = UIUtils.createModelViewer(cmpModelView, newModel, null, this.treeViewer);
					// UIUtils.createModelViewer(cmpModelView, result.getWitnessModel().getModelAsOriginal(), null);
				} else {
					if (project == null) {
						showMessage("Can't find a project");
						return;
					}
					
					IFolder outputs = getOutputFolder();
					int i = 0;					
					IScrollingIterator iterator = result.getScrollingIterator();
					while ( iterator.hasNext() ) {
						IWitnessModel model = iterator.next();
						
						saveModelAutomatically(model.getModelAsOriginal(), outputs, "scrolling");
						i++;
						if ( i > 5 ) {
							break;
						}
					}
				}
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

	private void saveModelAutomatically(Resource newModel, IFolder outputs, String prefix)
			throws IOException, FileNotFoundException {
		
		int size = new File(outputs.getLocation().toOSString()).list().length;

		IFile f = outputs.getFile(prefix + "." + (size + 1) + ".xmi");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		newModel.save(new FileOutputStream(f.getLocation().toOSString()), map);
	}

	private IFolder getOutputFolder() throws CoreException {
		IFolder outputs = project.getFolder("outputs-esolver");
		if (! outputs.exists()) {
			outputs.create(true, true, null);
		}
		return outputs;
	}


	protected void completeModel() {
		IResource r = UIUtils.showChooseChooseFileDialog(getShell());
		if ( r != null ) {
			ResourceSet rs = new ResourceSetImpl();
			Resource model = rs.getResource(URI.createFileURI(r.getFullPath().toOSString()), true);
			XMIPartialModel partialModel = new XMIPartialModel(model);
			generateExample(partialModel, isScrolling());
		}		
	}
    
	private void extractBounds(PackageDeclarationCS pkg, OclValidator validator) {
		String text = pkg.toString();
		BufferedReader reader = new BufferedReader(new StringReader(text));
		reader.lines().
			filter(l -> l.contains("@bounds")).
			forEach(l -> extractBounds(l, validator));			
	}

	private void extractBounds(String l, OclValidator validator) {
		Pattern regexp = Pattern.compile("bounds\\s+(\\w+)\\s+([0-9]+)\\.\\.([0-9]+)");
		Matcher matcher = regexp.matcher(l);
		if ( matcher.find() ) {
			String className = matcher.group(1);
			int min = Integer.parseInt(matcher.group(2));
			int max = Integer.parseInt(matcher.group(3));
			validator.addBounds(className, min, max);
		}
	}

	protected void saveToXmi() {
		IStructuredSelection s = (IStructuredSelection) this.tblViewerModel.getSelection();
		WitnessModel m = (WitnessModel) s.getFirstElement();
		
		IResource r = UIUtils.showChooseNewFileDialog(getShell());
		if ( r != null ) {		
			try {
				Resource original = m.getModel().getModelAsOriginal();
				Map<String, Object> options = new HashMap();
				options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
				original.save(new FileOutputStream(r.getFullPath().toOSString()), options);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private void showMessage(String message) {
		txtInfo.setText(message);
	}

	
}
