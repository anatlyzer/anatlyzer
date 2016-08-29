package anatlyzer.experiments.performance;

import org.eclipse.emf.ecore.EPackage;

import witness.generator.MetaModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.witness.SourceMetamodelsData;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.experiments.performance.raw.PEStatsWitness;

public class StatsRecorder {

	private PEStatsWitness stats;

	public void compute(EPackage errorMM, MetaModel effectiveMM, MetaModel languageMM) {
		if ( stats != null ) {
			throw new IllegalStateException();
		}
		stats = new PEStatsWitness(errorMM);
	}

		
	public PEStatsWitness getStats() {
		return stats;
	}

	public void reset() {
		stats = null;
	}


	
	
}
