package anatlyzer.ui.actions;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import anatlyzer.atl.editor.builder.AnATLyzerBuilder;

public class CleanAllMarkers implements IEditorActionDelegate {

	@Override
	public void run(IAction action) {
		
		try {
			final ArrayList<IFile> atlFiles = new ArrayList<IFile>();
			ResourcesPlugin.getWorkspace().getRoot().accept(new IResourceVisitor() {

				@Override
				public boolean visit(IResource resource) throws CoreException {
					if ( resource instanceof IFile && resource.getFileExtension().equals("atl")) {
						atlFiles.add((IFile) resource);
						return false;
					}
					return true;
				}
			});
			
			for (IFile file : atlFiles) {
				file.deleteMarkers(AnATLyzerBuilder.MARKER_TYPE, false, IResource.DEPTH_ZERO);
			}
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// TODO Auto-generated method stub

	}

}
