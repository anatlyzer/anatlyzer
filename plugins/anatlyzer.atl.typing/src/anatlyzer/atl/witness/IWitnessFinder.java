package anatlyzer.atl.witness;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;

/**
 * An interface for witness finders, typically using a constraint solving.
 * 
 * @author jesus
 */
public interface IWitnessFinder {
	public ProblemStatus find(Problem p, AnalysisResult r);
	public ProblemStatus find(IDetectedProblem p, AnalysisResult r);
	
	public IWitnessFinder checkDiscardCause(boolean b);
	public IWitnessFinder checkProblemsInPath(boolean b);
	public IWitnessFinder checkPreconditions(boolean b);
	public IWitnessFinder catchInternalErrors(boolean b);
	public IWitnessFinder setDebugMode(boolean b);
	
	
//	public static enum WitnessResult {
//		ERROR_CONFIRMED,
//		ERROR_CONFIRMED_SPECULATIVE,
//		ERROR_DISCARDED,
//		ERROR_DISCARDED_DUE_TO_METAMODEL,
//		INTERNAL_ERROR, 
//		CANNOT_DETERMINE, 
//		NOT_SUPPORTED_BY_USE,
//		PROBLEMS_IN_PATH
//	}

}
