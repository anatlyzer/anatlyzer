package anatlyzer.atl.editor.quickfix;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.m2m.atl.common.AtlNbCharFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.ATL.LocatedElement;

public abstract class AbstractAtlQuickfix implements AtlProblemQuickfix {

	protected IMarker marker;
	
	public void setErrorMarker(IMarker marker) {		
		this.marker = marker;
	}

	protected int getProblemStartOffset() throws CoreException {
		return (Integer) marker.getAttribute(IMarker.CHAR_START);
	}
	
	protected int getProblemEndOffset() throws CoreException {
		return (Integer) marker.getAttribute(IMarker.CHAR_END);
	}
	
	protected LocalProblem getProblem() throws CoreException {
		LocalProblem problem = (LocalProblem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
		return problem;
	}

	protected int[] getElementOffset(LocatedElement obj, IDocument document) {
		AtlNbCharFile help = new AtlNbCharFile(new ByteArrayInputStream(document.get().getBytes()));
		return help.getIndexChar(obj.getLocation());
	}
	
	
	public IFile getATLFile() {
		IWorkbenchWindow window= PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IEditorPart editor = page.getActiveEditor();
		IEditorInput input = editor.getEditorInput();
		if (!(input instanceof IFileEditorInput))
			throw new IllegalStateException();
		
		IFile file = ((IFileEditorInput)input).getFile();
		return file;
	}
	
	protected String getProjectPath() {
	    return getATLFile().getProject().getLocation().toOSString();
	}


}
