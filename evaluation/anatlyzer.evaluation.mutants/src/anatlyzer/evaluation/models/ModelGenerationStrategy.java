package anatlyzer.evaluation.models;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public abstract class ModelGenerationStrategy implements Iterator<Properties>, Iterable<Properties> {
	
	protected List<String> classes, references;
	protected Properties   properties;
	
	public enum STRATEGY { Lite, Full }
	
	protected final String PROPERTY_PREFFIX = "solver.scope.";
	
	public ModelGenerationStrategy (List<String> classes, List<String> references) {
		this.classes    = classes;
		this.references = references;
		this.properties = new Properties();
	}
	
	abstract public boolean hasNext();
	abstract public Properties next();
	
	@Override
	public Iterator<Properties> iterator() {
		return this;
	}
	
	// initialize list of parameter combinations and properties file
	protected void init (List<String> types, List<Integer> combination) {
		combination.clear();
		for (String type : types) {
			combination.add(0);
			properties.put(PROPERTY_PREFFIX + type, "0");
		}
	}
}
