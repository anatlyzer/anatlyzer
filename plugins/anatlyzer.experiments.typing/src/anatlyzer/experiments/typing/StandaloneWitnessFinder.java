package anatlyzer.experiments.typing;

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;

import witness.generator.ErrorMMGenerator;
import witness.generator.WitnessGeneratorMemory;
import analyser.atl.problems.IDetectedProblem;
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
	
	private WitnessResult executeCommand() {
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
				return WitnessResult.ERROR_CONFIRMED;
			case 2:
				return WitnessResult.ERROR_DISCARDED;
			case 3:
				return WitnessResult.INTERNAL_ERROR;
			default:
				return WitnessResult.CANNOT_DETERMINE;
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return WitnessResult.CANNOT_DETERMINE;
		}
	}
	
	
	@Override
	protected WitnessResult applyUSE(IDetectedProblem problem, String strConstraint, boolean forceOnceInstanceOfConcreteClasses) {
		WitnessResult res = super.applyUSE(problem, strConstraint, forceOnceInstanceOfConcreteClasses);
		// The intermediate file is generated correctly
		if ( res == WitnessResult.ERROR_CONFIRMED ) {
			return executeCommand();
		}
		return WitnessResult.CANNOT_DETERMINE;
	}
	

	@Override
	protected WitnessGeneratorMemory createWitnessGenerator(
			EPackage errorSliceMM, EPackage effective, EPackage language,
			String strConstraint) {
		return new ErrorMMGenerator(errorSliceMM, effective, language, strConstraint, getOutputFileName());
	}
	

}
