package anatlyzer.experiments.typing;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;

import anatlyzer.experiments.extensions.IExperiment;

public class QuickfixEvaluationByUseCase extends QuickfixEvaluationAbstract implements IExperiment {
	
	
	
	public QuickfixEvaluationByUseCase() {
		recordAll = false;
		
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);
	}
	
	@Override
	public void projectDone(IProject project) {
		// Detect that we are in a new project, so dump the previous
		// and free memory
		if ( ! recordAll && projects.size() == 1 ) {
			Project p = projects.get(project.getName());
			createDetail(workbook, p);
			
			projects.clear(); // free memory
		}
	}
	
	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		String projectName = resource.getProject().getName();
		if ( ! projects.containsKey(projectName) ) {
			projects.put(projectName, new Project(projectName));
		}
		Project project = projects.get(projectName);
		
		evaluateQuickfixesOfFile(resource, project, monitor);
	}

}
