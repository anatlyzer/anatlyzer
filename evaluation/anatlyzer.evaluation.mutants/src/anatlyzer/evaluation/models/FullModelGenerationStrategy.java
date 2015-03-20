package anatlyzer.evaluation.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Full strategy for model generation => all combinations of the number of instances of each class,
 * and 0 or unbounded instances of each associations. For instance, given two classes A and B and 
 * an association L, all their possible combinations are: 
 * [A=0,B=1,L=0]; [A=0,B=1,L=-1]; 
 * [A=0;B=2,L=0]; [A=0;B=2,L=-1];  
 * [A=1;B=0,L=0]; [A=1;B=0,L=-1];  
 * [A=1;B=1,L=0]; [A=1;B=1,L=-1];  
 * [A=1;B=2,L=0]; [A=1;B=2,L=-1];  
 * [A=2;B=0,L=0]; [A=2;B=0,L=-1];  
 * [A=2;B=1,L=0]; [A=2;B=1,L=-1]; and 
 * [A=2;B=2,L=0]; [A=2;B=2,L=-1]; 
 * The combination is returned as a Properties object.
 */ 
public class FullModelGenerationStrategy extends ModelGenerationStrategy {

	protected List<Integer> combination  = new ArrayList<Integer>();
	protected ModelGenerationStrategy classesStrategy;
	protected boolean isFirst = true; // first combination (0, 0, ..., 0, 0)

	public FullModelGenerationStrategy (List<String> classes, List<String> references) {
		super (classes,    references);
		init  (references, combination);
		// strategy to combine the instances of classes
		classesStrategy = new LiteModelGenerationStrategy(classes, references);
		if (classesStrategy.hasNext()) {
			Properties classProperties = classesStrategy.next();
			properties.forEach( (key, value) -> classProperties.put(key, value) );
			properties = classProperties; // we use the same properties file as the classesStrategy
		}
	}
	
	@Override
	public boolean hasNext() {
		return classesStrategy.hasNext() || this.combination.stream().filter( p -> p != -1).count() > 0;
	}

	@Override
	public Properties next() {
		if (!hasNext()) return null;
		
		// computation of the next valid combination of references and update of properties file
		boolean end = isFirst;
		for (int i=0; i<combination.size() && end==false; i++) {
			
			// if unbounded: set to 0, and propagate to the consecutive position
			if (combination.get(i) < 0) {
				combination.set(i, 0);
				properties.put(PROPERTY_PREFFIX + references.get(i), "0");
			}
			
			// otherwise: set to -1, and return
			else {
				combination.set(i, -1);
				properties.put(PROPERTY_PREFFIX + references.get(i), "1..-1");
				end = true;
			}
		}
		isFirst = false;

		// if we have computed all combinations of references, obtain next combination of classes
		if (!end) {
			if (classesStrategy.hasNext()) {
				classesStrategy.next();
				init(references, combination);
				//isFirst = true;
				end     = true;
			}
		}
		
		// return properties if there are more valid combinations, and null otherwise
		return end? properties : null;
	}
}
