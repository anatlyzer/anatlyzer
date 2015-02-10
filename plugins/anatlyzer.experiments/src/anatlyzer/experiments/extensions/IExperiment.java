package anatlyzer.experiments.extensions;

import java.io.PrintStream;

import org.eclipse.core.resources.IResource;

public interface IExperiment {

	void perform(IResource resource);
	void printResult(PrintStream out);
}
