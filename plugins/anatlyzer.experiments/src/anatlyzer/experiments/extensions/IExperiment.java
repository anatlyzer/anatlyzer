package anatlyzer.experiments.extensions;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IExperiment {

	void perform(IResource resource, IProgressMonitor monitor);
	void printResult(PrintStream out);
	
	boolean canExportToExcel();
	void exportToExcel(String fileName) throws IOException;
	void setExperimentConfiguration(IFile file);
	void projectDone(IProject p);
	void setOptions(HashMap<String, Object> options);
	void saveData(IFile expFile) throws IOException;
}
