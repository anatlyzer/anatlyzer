package anatlyzer.experiments.typing;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;

import witness.generator.ErrorMMGenerator;
import witness.generator.WitnessGeneratorMemory;
import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.witness.UseWitnessFinder;

public class StandaloneWitnessFinder extends UseWitnessFinder {

	@Override
	protected void onUSEInternalError(Exception e) {
		throw new IllegalStateException();
	}

	@Override
	protected String getTempDirectory() {
		return "/tmp";
	}

	public String getOutputFileName() {
		return "/tmp/errorSlice.ecore";
	}
	
	public String getCommand() {
		return "/tmp/witnessfinder/witnessfinder";
	}
	
	private ProblemStatus executeCommand() {
		String params = "--launcher.suppressErrors -ecore " + getOutputFileName();
		try {
			ProcessBuilder pb = new ProcessBuilder()
				.inheritIO()
	        	.command(getCommand(), "--launcher.suppressErrors", "-ecore", getOutputFileName());
			Process p = pb.start();
			
			// Process p = Runtime.getRuntime().exec(getCommand() + " " + params);
			int exitValue = p.waitFor();
			
			switch (exitValue) {
			case 1:
				return ProblemStatus.ERROR_CONFIRMED;
			case 2:
				return ProblemStatus.ERROR_DISCARDED;
			case 3:
				return ProblemStatus.USE_INTERNAL_ERROR;
			default:
				return ProblemStatus.IMPL_INTERNAL_ERROR;
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return ProblemStatus.IMPL_INTERNAL_ERROR;
		}
	}
	
	
	@Override
	protected ProblemStatus applyUSE(IDetectedProblem problem, String strConstraint, boolean forceOnceInstanceOfConcreteClasses) {
		ProblemStatus res = super.applyUSE(problem, strConstraint, forceOnceInstanceOfConcreteClasses);
		// The intermediate file is generated correctly
		if ( res == ProblemStatus.ERROR_CONFIRMED ) {
			return executeCommand();
		}
		return ProblemStatus.IMPL_INTERNAL_ERROR;
	}
	

	@Override
	protected WitnessGeneratorMemory createWitnessGenerator(
			EPackage errorSliceMM, EPackage effective, EPackage language,
			String strConstraint) {
		return new ErrorMMGenerator(errorSliceMM, effective, language, strConstraint, getOutputFileName());
	}
	

}
