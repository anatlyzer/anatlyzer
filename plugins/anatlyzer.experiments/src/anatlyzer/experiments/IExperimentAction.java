package anatlyzer.experiments;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.custom.StyledText;

import anatlyzer.experiments.extensions.IExperiment;

public interface IExperimentAction {

	void execute(IExperiment experiment, IFile confFile);

	public void setMessageWindow(StyledText text);
}
