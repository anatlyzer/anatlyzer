package anatlyzer.atl.witness;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.index.AnalysisResult;

public interface IWitnessFinder {
	public WitnessResult find(Problem p, AnalysisResult r);
	
	public static enum WitnessResult {
		ERROR_CONFIRMED,
		ERROR_DISCARDED,
		INTERNAL_ERROR, CANNOT_DETERMINE
	}
}
