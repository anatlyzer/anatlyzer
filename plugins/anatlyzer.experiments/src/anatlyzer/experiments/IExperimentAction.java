package anatlyzer.experiments;

import org.eclipse.core.resources.IFile;

import anatlyzer.experiments.extensions.IExperiment;

public interface IExperimentAction {

	void execute(IExperiment experiment, IFile confFile);

}
