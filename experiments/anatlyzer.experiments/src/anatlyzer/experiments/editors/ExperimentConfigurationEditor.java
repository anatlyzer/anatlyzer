package anatlyzer.experiments.editors;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.configuration.ExperimentConfiguration;
import anatlyzer.experiments.configuration.ExperimentConfigurationReader;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.extensions.NewExperimentExtension;
import anatlyzer.experiments.extensions.NewExperimentExtension.DeclaredAction;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class ExperimentConfigurationEditor extends MultiPageEditorPart implements IResourceChangeListener{

	protected IExperiment experiment;

	/** The text editor used in page 0. */
	private TextEditor editor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;
	/**
	 * Creates a multi-page editor example.
	 */
	public ExperimentConfigurationEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	/**
	 * Creates page 0 of the multi-page editor,
	 * which contains a text editor.
	 */
	void createPage0() {
		try {
			editor = new TextEditor();
		

			int index = addPage(editor, getEditorInput());
			setPageText(index, editor.getTitle());
		
		
			IHandlerService serv = (IHandlerService) getSite().getService(IHandlerService.class);
			MyCopyHandler cp = new MyCopyHandler();
			serv.activateHandler(org.eclipse.ui.IWorkbenchCommandConstants.EDIT_PASTE, cp);
			//serv.activateHandler(org.eclipse.ui.IWorkbenchCommandConstants.EDIT_, cp);

		} catch (PartInitException e) {
			ErrorDialog.openError(
				getSite().getShell(),
				"Error creating nested text editor",
				null,
				e.getStatus());
		}
	}

	/**
	 * Creates page 2 of the multi-page editor,
	 * which shows the sorted text.
	 */
	void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		
		
		// Get info for additional buttons
		ExperimentConfiguration conf = readConfigurationFile();
		List<DeclaredAction> actions = Collections.emptyList();
		if ( conf != null ) {
			actions = NewExperimentExtension.getActions(conf);
		}
		
		
		layout.numColumns = 5 + actions.size();
		composite.setLayout(layout);
		
		// Execute
		Button executeButton = new Button(composite, SWT.NONE);
		GridData gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 1;
		executeButton.setLayoutData(gd);
		executeButton.setText("Execute");
		
		executeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				executeExperiment();
			}
		});

		Button saveData = new Button(composite, SWT.NONE);
		GridData gdsaveData = new GridData(GridData.BEGINNING);
		gdsaveData.horizontalSpan = 1;
		saveData.setLayoutData(gdsaveData);
		saveData.setText("Save data");
		
		saveData.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				saveData();
			}
		});

		
		Button openData = new Button(composite, SWT.NONE);
		GridData gdopenData = new GridData(GridData.BEGINNING);
		gdopenData.horizontalSpan = 1;
		openData.setLayoutData(gdopenData);
		openData.setText("Open data");
		
		openData.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				openData();
			}
		});

		
		Button exportToExcel = new Button(composite, SWT.NONE);
		GridData gdExcel = new GridData(GridData.BEGINNING);
		gdExcel.horizontalSpan = 1;
		exportToExcel.setLayoutData(gdExcel);
		exportToExcel.setText("Export to Excel");
		
		exportToExcel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				exportToExcel();
			}
		});

		for(int i = 0; i < actions.size(); i++) {
			Button b = new Button(composite, SWT.NONE);
			GridData bd = new GridData(GridData.BEGINNING);
			// gdExcel.horizontalSpan = 2 + i + 1;
			bd.horizontalSpan = 1;
			b.setLayoutData(bd);
			b.setText(actions.get(i).name);
			final IExperimentAction a = actions.get(i).action;
			b.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					executeAction(a);
				}

				private void executeAction(IExperimentAction a) {
					FileEditorInput input = (FileEditorInput) getEditorInput();
					a.setMessageWindow(text);
					a.execute(experiment, input.getFile());
				}
			});
		}
		
		// Text
		GridData gd1 = new GridData(GridData.FILL_BOTH);
		gd1.horizontalSpan = layout.numColumns - 1;
				
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		
		text.setFont( new Font(null, "Monospace", 11, SWT.NORMAL ) );
		
		
		text.setLayoutData(gd1);
		text.setEditable(true);
		
		//Composite compositeTopButtons = new Composite(composite, SWT.NONE);
		//GridLayout layoutTopButtons = new GridLayout();
		//compositeTopButtons.setLayout(layoutTopButtons);
		// layoutTopButtons.numColumns = 2;
	
		int index = addPage(composite);
		setPageText(index, "Result");
	}
	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage0();
		// createPage1();
		createPage2();
	}
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			// sortWords();
		}
	}
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}

	void loadExperiment(ExperimentConfiguration conf, BiFunction<IExperiment, String, Boolean> callback) {
		IExperiment experiment = null;
		String extension = null;
		
		for(final IConfigurationElement c : Platform.getExtensionRegistry().getConfigurationElementsFor(NewExperimentExtension.ID)) {
			if ( ! conf.extensionID.equals(c.getDeclaringExtension().getUniqueIdentifier()) ) 
				continue;
			
			String desc = c.getAttribute("resourceType");
			if ( desc.startsWith("file:") ) {
				extension = desc.substring("file:".length());				
			} else {
				extension = "";
				System.err.println("Not supported: " + desc);
			}
			
			try {
				experiment = (IExperiment) c.createExecutableExtension("delegate");
				IFileEditorInput input = (IFileEditorInput) getEditorInput();
				experiment.setExperimentConfiguration(input.getFile());
				experiment.setMessageWindow(text);
			} catch (CoreException e) {
				text.setText(e.getMessage());
				e.printStackTrace();
				return;
			}
			break;
		}

		if ( experiment != null ) {
			callback.apply(experiment, extension);
		} else {
			text.setText("No experiment has been run. No extension point named '" + conf.extensionID + "' can be found");
		}
	}
	
	void executeExperiment() {
		final ExperimentConfiguration conf = readConfigurationFile();

		loadExperiment(conf, (experiment, extension) -> {
			Job job = new ExperimentJob(conf, experiment, extension);
			job.schedule();
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) { 
					ExperimentConfigurationEditor.this.experiment = ((ExperimentJob) event.getJob()).experiment;
				}				
			});
			return true;
		});		
	}
	
	protected ExperimentConfiguration readConfigurationFile() {
		String confText = editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();
		final ExperimentConfiguration conf = ExperimentConfigurationReader.parseFromText(confText);
		return conf;
	}
	
	private class ExperimentJob extends Job {

		private IExperiment experiment;
		private String extension;
		private ExperimentConfiguration conf;

		public ExperimentJob(ExperimentConfiguration conf, IExperiment experiment, String extension) {
			super("Experiment");
			this.conf = conf;
			this.experiment = experiment;
			this.extension = extension;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			conf.execute(extension, experiment, monitor);
			
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			experiment.printResult(new PrintStream(bos));

			Display.getDefault().asyncExec(new Runnable() {
			    public void run() {
			    	text.append(bos.toString());
			    }
			  });

	        return Status.OK_STATUS;
		}
		
	}
	
	protected void exportToExcel() {
		if ( experiment != null && experiment.canExportToExcel()) {
			FileEditorInput input = (FileEditorInput) getEditorInput();
			IPath path = input.getFile().getFullPath().removeFileExtension().addFileExtension("xlsx");
			
			IFile xlsx = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			
			try {
				experiment.exportToExcel(xlsx.getLocation().toPortableString());
				xlsx.refreshLocal(1, null);
			} catch (IOException | CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void saveData() {
		if ( experiment != null ) {
			FileEditorInput input = (FileEditorInput) getEditorInput();
			try {
				experiment.saveData(input.getFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void openData() {
		FileEditorInput input = (FileEditorInput) getEditorInput();
		if ( experiment != null ) {
			try {
				experiment.openData(input.getFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			loadExperiment(readConfigurationFile(), (exp, extension) -> {
				// Not very clean, this makes the usage different when open data is used
				this.experiment = exp;
				openData();
				return true;
			});
		}
	}

	
}
