package anatlyzer.ui.util;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.Activator;

public class WorkspaceLogger {
	private static final ILog LOGGER = Activator.getDefault().getLog();

	private static final String PLUGIN_ID = Activator.PLUGIN_ID;

	public static void generateLogEntry(int severity, String message) {
		openLogWiew();
		writeLog(severity, message);
	}

	public static void generateLogEntry(int severity, Throwable exception) {
		generateLogEntry(severity, exception.getMessage(), exception);
	}

	public static void generateLogEntry(int severity, String message, Throwable e) {
		IStatus status = new Status(severity, PLUGIN_ID, message, e);
		openLogWiew();
		LOGGER.log(status);
	}
	
	private static void writeLog(int severity, String message) {
		IStatus status = new Status(severity, PLUGIN_ID, message);
		LOGGER.log(status);
	}

	private static void openLogWiew() {
		String messageexception;

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage()
					.showView("org.eclipse.pde.runtime.LogView");
		} catch (Exception exception) {
			// Originally was PartInitException, but this also captures
			// the NullPointerException raised if getActiveWorkbenchWindow() == null.
			// Do nothing, cannot be opened
		}
	}

	
}