package anatlyzer.experiments.extensions;

import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

public interface IExperiment {

	void perform(IResource resource);
	void printResult(PrintStream out);
	
	boolean canExportToExcel();
	void exportToExcel(String fileName) throws IOException;
	void setExperimentConfiguration(IFile file);
}
