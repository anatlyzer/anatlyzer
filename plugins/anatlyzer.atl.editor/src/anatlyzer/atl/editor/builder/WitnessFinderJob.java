package anatlyzer.atl.editor.builder;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;

public class WitnessFinderJob extends Job {
	public static final String FAMILY = "anatlyzer.witnessfinder.jobfamily";
	private AnalyserData data;
	private IResource resource;
	
	public WitnessFinderJob(IResource resource, AnalyserData data) {
		super("Witness finder");
		this.data = data;
		this.resource = resource;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder(AnalysisIndex.getInstance().getConfiguration(resource));
		
		for (Problem problem : data.getNonIgnoredProblems()) {
			if ( monitor.isCanceled() )
				return Status.CANCEL_STATUS;
			
			if ( problem.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {
				ProblemStatus status = wf.catchInternalErrors(true).find(problem, data);
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
