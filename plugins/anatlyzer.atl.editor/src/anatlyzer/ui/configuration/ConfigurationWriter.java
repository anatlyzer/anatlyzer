package anatlyzer.ui.configuration;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;

public class ConfigurationWriter {

	
	public static void write(OutputStream stream, TransformationConfiguration configuration) throws IOException {
		PrintStream ps = new PrintStream(stream);
		
		ps.println("# Witness");
		ps.println();
		ps.println("debug-witness-finder " + (configuration.isWitnessFinderDebugMode() ? "on" : "off"));
		ps.println();
		ps.println("witness-finder " + (configuration.isContinousWitnessFinder() ? "continuous" : "batch"));	
		ps.println();
		ps.println("check-discard-cause " + (configuration.getCheckDiscardCause() ? "on" : "off"));
		ps.println();
		ps.println("witness-recursion-unfolding " + (configuration.getDoRecursionUnfolding() ? "on" : "off"));
		if ( configuration.getWitnessGenerationGraphics() != null ) {
			ps.println();
			ps.println("witness-generation-graphics "  + configuration.getWitnessGenerationGraphics());
		}
		
		String mode = "error-path";
		switch (configuration.getWitnessMode()) {
		case ERROR_PATH: mode = "error-path"; break;
		case MANDATORY_FULL_METAMODEL: mode = "mandatory-full"; break;
		case MANDATORY_EFFECTIVE_METAMODEL: mode = "mandatory-effective"; break;
		case FULL_METAMODEL: mode = "full"; break;
		}

		ps.println();
		ps.println("witness-generation-mode " + mode);
		
		ps.close();
	}


}