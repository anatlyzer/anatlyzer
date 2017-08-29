package anatlyzer.atl.editor.builder;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;

public class WitnessFinderJob extends Job {
	public static final String FAMILY = "anatlyzer.witnessfinder.jobfamily";
	private AnalysisResult data;
	private IResource resource;
	
	public WitnessFinderJob(IResource resource, AnalysisResult data) {
		super("Checking problems with model finder for " + resource.getName());
		this.data = data;
		this.resource = resource;
	}

	protected List<? extends Problem> getProblems() {
		return data.getNonIgnoredProblems();
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder(AnalysisIndex.getInstance().getConfiguration(resource));
		
		int total = getProblems().size();
		int i = 0;
		for (Problem problem : getProblems()) {
			if ( monitor.isCanceled() )
				return Status.CANCEL_STATUS;
			
			i++;
			String description = problem.getDescription().replaceAll("\n", "");
			description = description.length() < 50 ? description : description.substring(0, 50) + "...";
			
			monitor.setTaskName("" + i + "/" + total + ": " + description);
			
			if ( problem.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {
				ProblemStatus status = wf.catchInternalErrors(true).find(problem, data);
				AnalyserUtils.setProblemWitnessModel(problem, wf.getFoundWitnessModel());
				AnalysisIndex.getInstance().changeStatus(resource, problem, status);
			}			
		}		
		
		return Status.OK_STATUS;		
	}

	@Override
	public boolean belongsTo(Object family) {
		return family == FAMILY || family == resource;
	}
}
