package anatlyzer.ui.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import anatlyzer.atl.errors.ProblemStatus;

public class ConfigurationReader {

	private TransformationConfiguration configuration;
	
	private ConfigurationReader(InputStream stream, TransformationConfiguration configuration) throws IOException {
		this.configuration = configuration;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		while( (line = reader.readLine()) != null ) {
			if ( line.startsWith("witness-finder") ) {
				checkContinuous(line);
			} else if ( line.startsWith("show-marker") ) {
				checkShowMarker(line);
			}
			
		}
		
	}

	
	/**
	 * <pre>
	 *   show-marker discarded
	 *   show-marker unknown
	 *   show-marker witness-required
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkShowMarker(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("discarded") ) configuration.addMarkerFor(ProblemStatus.ERROR_DISCARDED, ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL);
			if ( parts[1].equals("unknown") ) configuration.addMarkerFor(ProblemStatus.USE_INTERNAL_ERROR, ProblemStatus.NOT_SUPPORTED_BY_USE, ProblemStatus.IMPL_INTERNAL_ERROR);
			if ( parts[1].equals("witness-required") ) configuration.addMarkerFor(ProblemStatus.WITNESS_REQUIRED);
		}		
	}

	/**
	 * <pre>
	 *   witness-finder continuous
	 * </pre>
	 * 
	 * @param line
	 */
	private void checkContinuous(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("continuous") ) configuration.setContinousWitnessFinder(true);
		}
	}
	
	public static TransformationConfiguration read(InputStream stream) throws IOException {
		TransformationConfiguration conf = new TransformationConfiguration();
		new ConfigurationReader(stream, conf);
		return conf;
	}
	
}
