package anatlyzer.atl.editor.witness;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.ui.util.WorkbenchUtil;
import anatlyzer.ui.util.WorkspaceLogger;

/**
 * The hook methods {@link #onUSEInternalError(Exception)} and {@link #getTempDirectory()} runs
 * in the UI thread (via asyncExec and syncExec) to allow background jobs to use this class
 * easily.
 * 
 * @author jesus
 *
 */
public class EclipseUseWitnessFinder extends UseWitnessFinder implements IWitnessFinder {

	@Override
	protected void onUSEInternalError(Exception e) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				WorkspaceLogger.generateLogEntry(IStatus.ERROR, e.getMessage(), e);
			}
		});
	}

	private String tempDirectory = null;
	
	@Override
	protected String getTempDirectory() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				tempDirectory = WorkbenchUtil.getProjectPath();
			}
		});
		return tempDirectory;
	}

}
