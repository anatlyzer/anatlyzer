package anatlyzer.experiments.performance;

import org.eclipse.emf.ecore.EPackage;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.witness.SourceMetamodelsData;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.experiments.performance.raw.PEStatsWitness;

public class StatsRecorder {

	private PEStatsWitness stats;

	public void compute(ErrorSlice slice, OclExpression constraint, EPackage errorSliceMM, SourceMetamodelsData data) {
		if ( stats != null ) {
			throw new IllegalStateException();
		}
		stats = new PEStatsWitness(slice, constraint, errorSliceMM, data);
	}
		
	public PEStatsWitness getStats() {
		return stats;
	}

	public void reset() {
		stats = null;
	}

	
	
}
