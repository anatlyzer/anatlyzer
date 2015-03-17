package anatlyzer.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.m2m.atl.common.AtlNbCharFile;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

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


	public static void goToEditorLocation(String fileLocation, String location) {

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(fileLocation));
        IEditorDescriptor desc = PlatformUI.getWorkbench().
                getEditorRegistry().getDefaultEditor(file.getName());
        try {
        	IEditorPart part = page.openEditor(new FileEditorInput(file), desc.getId());
                if ( part instanceof AtlEditor ) {
                	AtlEditor atlEditor = (AtlEditor) part;
    				AtlNbCharFile help = new AtlNbCharFile(file.getContents());
    				int[] pos = help.getIndexChar(location, -1);
    				int charStart = pos[0];
    				int charEnd = pos[1];
    				atlEditor.selectAndReveal(charStart, charEnd - charStart); 
    			}
        } catch (PartInitException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
     
	}

}
