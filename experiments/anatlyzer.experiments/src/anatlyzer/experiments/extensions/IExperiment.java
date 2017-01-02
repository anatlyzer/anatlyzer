package anatlyzer.experiments.extensions;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.custom.StyledText;

public interface IExperiment {

	void perform(IResource resource, IProgressMonitor monitor);
	void printResult(PrintStream out);
	
	boolean canExportToExcel();
	void exportToExcel(String fileName) throws IOException;
	
	
	void setExperimentConfiguration(IFile file);
	
	/**
	 * Sets the window in which messages can be shown.
	 * @param text
	 */
	void setMessageWindow(StyledText text);
	
	void projectDone(IProject p);
	void finished();
	
	void setOptions(HashMap<String, Object> options);
	
	/**
	 * Save the data file for the experiment.
	 * @param expFile The .exp file as a reference to construct another paths.
	 * @throws IOException
	 */
	void saveData(IFile expFile) throws IOException;
	
	/**
	 * Reloads a saved data file for the experiment.
	 * @param expFile The .exp file as a reference to construct another paths.
	 * @throws IOException
	 */
	void openData(IFile expFile) throws IOException;
	
	
	
}
