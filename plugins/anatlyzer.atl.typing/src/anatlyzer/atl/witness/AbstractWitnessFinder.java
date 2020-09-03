package anatlyzer.atl.witness;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.util.AnalyserUtils;

public abstract class AbstractWitnessFinder implements IWitnessFinder {

	private boolean catchInternalErrors = false;
	protected boolean checkProblemsInPath;
	protected IFinderStatsCollector statsCollector = new IFinderStatsCollector.NullStatsCollector();

	@Override
	public IWitnessFinder checkProblemsInPath(boolean b) {
		this.checkProblemsInPath  = b;
		return this;
	}
	
	@Override
	public IWitnessFinder catchInternalErrors(boolean b) {
		this.catchInternalErrors  = b;
		return this;
	}

	@Override
	public IWitnessFinder setStatsCollector(IFinderStatsCollector collector) {
		this.statsCollector = collector;
		return this;
	}
	
	@Override
	public ProblemStatus find(Problem problem, AnalysisResult r) {
		ProblemPath path = AnalyserUtils.computeProblemPath((LocalProblem) problem, r.getAnalyser(), checkProblemsInPath);
		if ( path == null ) {
			return ProblemStatus.PROBLEMS_IN_PATH;
		}
		return find(path, r);	
	}
	

	@Override
	public ProblemStatus find(IDetectedProblem problem, AnalysisResult r) {
		if ( catchInternalErrors ) {
			try {
				return findAux(problem, r);
			} catch ( Throwable e ) {
				e.printStackTrace();
				return ProblemStatus.IMPL_INTERNAL_ERROR;
			}
		} else {
			return findAux(problem, r);
		}		
	}
	
	protected abstract ProblemStatus findAux(IDetectedProblem problem, AnalysisResult r);
	
}
