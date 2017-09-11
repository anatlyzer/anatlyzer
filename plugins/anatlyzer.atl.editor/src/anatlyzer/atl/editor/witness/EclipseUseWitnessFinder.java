package anatlyzer.atl.editor.witness;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.ui.preferences.AnATLyzerPreferenceInitializer;
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
				WorkspaceLogger.writeLog(IStatus.ERROR, e.getMessage(), e);
			}
		});
	}

	private String tempDirectory = null;
	
	@Override
	public String getTempDirectory() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				tempDirectory = WorkbenchUtil.getProjectPath();
			}
		});
		return tempDirectory;
	}

	@Override
	protected int getMinScope() {
		int minBounds = AnATLyzerPreferenceInitializer.getMinBounds();
		return minBounds < 0 ? super.getMinScope() : minBounds;
	}
	
	@Override
	protected int getMaxScope() {
		int maxBounds = AnATLyzerPreferenceInitializer.getMaxBounds();
		return maxBounds <= 0 ? super.getMaxScope() : maxBounds;
	}
	
}
