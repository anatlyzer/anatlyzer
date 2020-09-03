package anatlyzer.ide.interactive.editors;


import java.io.IOException;
import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.ide.interactive.InteractiveProcessIndex;
import anatlyzer.ide.interactive.views.ConcreteTestcaseMapping;
import anatlyzer.ide.interactive.views.InteractiveProcess;
import anatlyzer.ide.interactive.views.InteractiveProcess.ExecutedModel;
import anatlyzer.ide.interactive.views.TestModelsComposite;
import anatlyzer.ide.interactive.views.TestcaseComposite;
import anatlyzer.ide.model.IInteractiveTransformationModelChanger;
import anatlyzer.ide.model.TestCase;
import anatlyzer.testing.common.Model;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class InteractiveTransformationEditor extends MultiPageEditorPart implements IResourceChangeListener, IInteractiveTransformationModelChanger {

	/** The text editor used in page 0. */
	private TextEditor editor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;

	private TestcaseComposite testcaseComposite;

	private InteractiveProcess process;

	private TestModelsComposite testModelsComposite;

	/**
	 * Creates a multi-page editor example.
	 */
	public InteractiveTransformationEditor() {
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
		} catch (PartInitException e) {
			ErrorDialog.openError(
				getSite().getShell(),
				"Error creating nested text editor",
				null,
				e.getStatus());
		}
	}
	private void updateSpec() {
		IEditorInput input = editor.getEditorInput();
		IFile file = (IFile) ((FileEditorInput) input.getPersistable()).getFile();
		try {
			this.process = InteractiveProcessIndex.getInstance().getProcessFromSpec(file, true);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		updateTestModels();
		
		//String editorText =
		//		editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();
	}
	
	private void updateTestModels() {
		if (this.process != null)
			testModelsComposite.setAnalysis(this.process);
	}
	
	public InteractiveProcess getProcess() {
		return process;
	}
	
	/**
	 * Creates page 2 of the multi-page editor,
	 * which shows the sorted text.
	 */
	void createPage1() {
		testModelsComposite = new TestModelsComposite(getContainer(), this);
		
		int index = addPage(testModelsComposite);
		setPageText(index, "Test suite");
	}
	
	/**
	 * Creates page 1 of the multi-page editor,
	 * which allows you to change the font used in page 2.
	 */
	void createPage2() {
		testcaseComposite = new TestcaseComposite(getContainer(), this);
		int index = addPage(testcaseComposite);
		setPageText(index, "Test case");
	}
	
	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage0();
		createPage1();
		createPage2();
		updateSpec();			
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
		updateSpec();
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

	//	/**
//	 * Calculates the contents of page 2 when the it is activated.
//	 */
//	protected void pageChange(int newPageIndex) {
//		super.pageChange(newPageIndex);
//		if (newPageIndex == 2) {
//			sortWords();
//		}
//	}
	
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(() -> {
				IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
				for (int i = 0; i<pages.length; i++){
					if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
						IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
						pages[i].closeEditor(editorPart,true);
					}
				}
			});
		}
	}
	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}
	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
//	void sortWords() {
//		String editorText =
//			editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();
//
//		StringTokenizer tokenizer =
//			new StringTokenizer(editorText, " \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
//		List<String> editorWords = new ArrayList<>();
//		while (tokenizer.hasMoreTokens()) {
//			editorWords.add(tokenizer.nextToken());
//		}
//
//		Collections.sort(editorWords, Collator.getInstance());
//		StringWriter displayText = new StringWriter();
//		for (int i = 0; i < editorWords.size(); i++) {
//			displayText.write(((String) editorWords.get(i)));
//			displayText.write(System.getProperty("line.separator"));
//		}
//		text.setText(displayText.toString());
//	}
	
	public void setCurrentModel(TestCase testCase, ExecutedModel model) {
		AnalysisResult result = AnalysisIndex.getInstance().getAnalysis(model.getTransformation());
		if (result == null)
			return;
		
		ConcreteTestcaseMapping mapping = new ConcreteTestcaseMapping(result.getAnalyser(), testCase, model);
		this.testcaseComposite.setExecutionMapping(mapping);
	}
	
	// private boolean changed = true;
	
	@Override
	public void markChanged() {
		IDocument document = editor.getDocumentProvider().getDocument(editor.getEditorInput());
		document.set(process.toYamlText());
		
		
		// this.changed = true;
	}
	
//	@Override
//	public boolean isDirty() {
//		// TODO Auto-generated method stub
//		return super.isDirty();
//	}
	
}
