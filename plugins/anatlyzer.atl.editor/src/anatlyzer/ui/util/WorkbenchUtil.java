package anatlyzer.ui.util;

import java.io.ByteArrayInputStream;
import java.util.function.Supplier;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.IDocument;
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

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.LocatedElement;

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

	public static IResource getResource(String location) {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(location));		
	}

	public static IFolder getOrCreateFolder(IPath folderPath) throws CoreException {
		IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(folderPath);
		if ( ! folder.exists() ) {
			folder.create(true, true, null);
			folder.refreshLocal(1, null);
		}
		return folder;
	}
	
	public static <T> T logExecutionTime(Supplier<T> s) {
	     long start = System.nanoTime();
	     T v = s.get();
	     long end = System.nanoTime();
	     // return end-start;
	     return v;
	}
	
	public static <T> T logSecondsTime(Supplier<T> s) {
	     long start = System.nanoTime();
	     T v = s.get();
	     long end = System.nanoTime();
	     // return end-start;
	     System.out.println((end-start) / 1000000000d);
	     return v;
	}

	public static LocatedElement getElementFromOffset(int offset, ATLModel model, IDocument document) {
		AtlNbCharFile helper = new AtlNbCharFile(new ByteArrayInputStream(document.get().getBytes()));
		
		// Poor's man way of obtaining the element associated to the offset...
		int closest = Integer.MAX_VALUE;
		LocatedElement found = null;
		for (LocatedElement e : model.allObjectsOf(LocatedElement.class)) {
			if ( e.getLocation() != null ) {
				int indexChar[] = helper.getIndexChar(e.getLocation());
				if ( indexChar[0] <= offset && indexChar[1] >= offset ) {
					int dist = (offset - indexChar[0]) + (indexChar[1] - offset);
					if ( dist < closest ) {
						found   = e;
						closest = dist;
					}
				}
			}
		}
		return found;
	}
}
