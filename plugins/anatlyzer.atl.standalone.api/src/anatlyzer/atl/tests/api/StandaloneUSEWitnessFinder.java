package anatlyzer.atl.tests.api;

import java.io.File;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.witness.UseWitnessFinder;

public class StandaloneUSEWitnessFinder extends UseWitnessFinder {

	public static ProblemStatus confirmOrDiscard(Problem p, AnalysisResult r) {
		StandaloneUSEWitnessFinder finder = new StandaloneUSEWitnessFinder();
		ProblemStatus newStatus = finder.find(p, r);
		p.setStatus(newStatus);
		return newStatus;
	}

	@Override
	protected void onUSEInternalError(Exception e) {
		e.printStackTrace();
	}

	/**
	 * This will be removed in future versions...
	 */
	@Override
	protected String getTempDirectory() {
		return new File(".").getAbsolutePath().replace("/.", "");
	}

}
