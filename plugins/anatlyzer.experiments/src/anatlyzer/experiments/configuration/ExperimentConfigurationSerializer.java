package anatlyzer.experiments.configuration;

import java.io.FileWriter;
import java.io.IOException;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlWriter;


public class ExperimentConfigurationSerializer {

	public ExperimentConfigurationSerializer() {
		
	}
	public void write(ExperimentConfiguration conf, String filename) throws IOException {
		YamlWriter writer = new YamlWriter(new FileWriter(filename));
		
		configure(writer.getConfig());
		
		try {
			writer.write(conf);
		} catch (YamlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.close();
	}

	public static void configure(YamlConfig config) {
		config.setPropertyElementType(ExperimentConfiguration.class, "projects", Project.class);
				
	}
	
	
	// Simple test to understand YAML
	public static void main(String[] args) throws IOException {
		ExperimentConfiguration conf = new ExperimentConfiguration();
		conf.name = "test";
		Project project1 = new Project();
		project1.name = "xx.yy.zz";
		Project project2 = new Project();
		project2.name = "aaa.ee.zz";
		
		project2.ignoredFiles.add("aFileToBeIgnored");
		
		conf.projects.add(project1);
		conf.projects.add(project2);
		
		
		new ExperimentConfigurationSerializer().write(conf, "/tmp/salida.exp");
	}
	
}
