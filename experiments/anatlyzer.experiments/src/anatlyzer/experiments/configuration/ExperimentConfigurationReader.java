package anatlyzer.experiments.configuration;

import java.io.StringReader;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;


/**
 * Reads a configuration file for an experiment, with the following format:
 * 
 * <pre>
 * projects:
 *   my.project1.atl
 *   another.projectname
 * 
 * </pre>
 * 
 * @author jesus
 *
 */
public class ExperimentConfigurationReader {

	public ExperimentConfigurationReader() {
		
	}
	
	public static ExperimentConfiguration parseFromText(String text) {
		YamlReader reader = new YamlReader(new StringReader(text));
		ExperimentConfigurationSerializer.configure(reader.getConfig());
		try {
			return reader.read(ExperimentConfiguration.class);
		} catch (YamlException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
