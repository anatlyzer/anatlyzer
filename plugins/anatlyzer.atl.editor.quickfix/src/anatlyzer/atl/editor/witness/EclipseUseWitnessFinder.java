package anatlyzer.atl.editor.witness;

import org.eclipse.core.runtime.IStatus;

import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.ui.util.WorkbenchUtil;
import anatlyzer.ui.util.WorkspaceLogger;

public class EclipseUseWitnessFinder extends UseWitnessFinder implements IWitnessFinder {

	@Override
	protected void onUSEInternalError(Exception e) {
		WorkspaceLogger.generateLogEntry(IStatus.ERROR, e.getMessage(), e);
	}

	@Override
	protected String getTempDirectory() {
		return WorkbenchUtil.getProjectPath();
	}

}
