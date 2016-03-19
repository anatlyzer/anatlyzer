package anatlyzer.experiments.performance;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atlext.OCL.OclExpression;

public class StatsRecorder {

	long numCompulsoryFeatures;
	long numClasses;
	
	public StatsRecorder(ErrorSlice slice, OclExpression expr) {
		computeSlice(slice);
	}

	private void computeSlice(ErrorSlice slice) {
		numCompulsoryFeatures = slice.getFeatures().stream().filter(f -> f.isRequired()).count();
		numClasses = slice.getClasses().size();
	}
	
}
