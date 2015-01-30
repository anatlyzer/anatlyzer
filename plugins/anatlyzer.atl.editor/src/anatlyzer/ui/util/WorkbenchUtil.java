package anatlyzer.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class WorkbenchUtil {
	public static IFile getATLFile() {
		IWorkbenchWindow window= PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IEditorPart editor = page.getActiveEditor();
		IEditorInput input = editor.getEditorInput();
		if (!(input instanceof IFileEditorInput))
			throw new IllegalStateException();
		
		IFile file = ((IFileEditorInput)input).getFile();
		return file;
	}
	
	public static String getProjectPath() {
	    return getATLFile().getProject().getLocation().toOSString();
	}
}
