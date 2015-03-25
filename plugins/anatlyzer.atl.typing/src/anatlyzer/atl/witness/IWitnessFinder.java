package anatlyzer.atl.witness;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;

/**
 * An interface for witness finders, typically using a constraint solving.
 * 
 * @author jesus
 */
public interface IWitnessFinder {
	public WitnessResult find(Problem p, AnalysisResult r);
	public WitnessResult find(IDetectedProblem p, AnalysisResult r);
	
	public static enum WitnessResult {
		ERROR_CONFIRMED,
		ERROR_CONFIRMED_SPECULATIVE,
		ERROR_DISCARDED,
		ERROR_DISCARDED_DUE_TO_METAMODEL,
		INTERNAL_ERROR, 
		CANNOT_DETERMINE, 
		NOT_SUPPORTED_BY_USE 
	}

}
