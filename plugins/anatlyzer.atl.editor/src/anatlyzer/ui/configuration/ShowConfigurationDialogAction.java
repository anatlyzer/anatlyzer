package anatlyzer.ui.configuration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import anatlyzer.atl.index.AnalysisIndex;

public class ShowConfigurationDialogAction implements IObjectActionDelegate {

	private Shell shell;
	private IFile selectedFile;
	
	/**
	 * Constructor for Action1.
	 */
	public ShowConfigurationDialogAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if ( selectedFile != null ) {
			TransformationConfiguration conf = AnalysisIndex.getInstance().getConfiguration(selectedFile);
			if ( conf == null ) {
				conf = TransformationConfiguration.getDefault();
			}
			EditConfiguration dialog = new EditConfiguration(shell, conf);
			if ( dialog.open() == Window.OK ) {
				FileOutputStream os;
				try {
					IPath newPath = selectedFile.getLocation().removeFileExtension().addFileExtension("atlc");
					os = new FileOutputStream(newPath.toOSString());
					ConfigurationWriter.write(os, conf);
					ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(newPath).refreshLocal(-1, null);					
				} catch (IOException e ) {
					MessageDialog.openError(shell, "Error", "Cannot write file: "  + e.getMessage());
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
				
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selectedFile = null;
		if ( selection instanceof IStructuredSelection ) {
			IStructuredSelection s = (IStructuredSelection) selection;
			if (s.getFirstElement() instanceof IFile)
				this.selectedFile = (IFile) s.getFirstElement();
		}
	}

}
