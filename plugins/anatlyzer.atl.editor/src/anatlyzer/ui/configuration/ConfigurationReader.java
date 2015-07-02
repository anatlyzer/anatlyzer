package anatlyzer.ui.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigurationReader {

	private TransformationConfiguration configuration;
	
	private ConfigurationReader(InputStream stream, TransformationConfiguration configuration) throws IOException {
		this.configuration = configuration;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		while( (line = reader.readLine()) != null ) {
			if ( line.startsWith("continuous") ) {
				checkContinous(line);
			}
		}
		
	}

	private void checkContinous(String line) {
		String[] parts = line.split("\\s+");
		if ( parts.length == 2 ) {
			if ( parts[1].equals("witness-finder") ) configuration.setContinousWitnessFinder(true);
		}
	}
	
	public static TransformationConfiguration read(InputStream stream) throws IOException {
		TransformationConfiguration conf = new TransformationConfiguration();
		new ConfigurationReader(stream, conf);
		return conf;
	}
	
}
