package anatlyzer.evaluation.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Lite strategy for model generation => all combinations of the number of instances of each class.
 * For instance, given two classes A and B, all their possible combinations are [A=0,B=1]; [A=0;B=2]; 
 * [A=1;B=0]; [A=1;B=1]; [A=1;B=2]; [A=2;B=0]; [A=2;B=1]; and [A=2;B=2]. The combination is
 * returned as a Properties object.
 */
public class LiteModelGenerationStrategy extends ModelGenerationStrategy {
	
	protected List<Integer> combination = new ArrayList<Integer>();

	protected int MAX_OBJECT_SCOPE = 2;  // maximum number of objects/links of each class/association
	
	public LiteModelGenerationStrategy (List<String> classes, List<String> references) {
		super (classes, references);
		init  (classes, combination);
	}
	
	public LiteModelGenerationStrategy (List<String> classes, List<String> references, int maxObjectScope) {
		this (classes, references);
		if (maxObjectScope >= 0) 
			MAX_OBJECT_SCOPE = maxObjectScope;
	}
	
	@Override
	public boolean hasNext() {
		return this.combination.stream().filter( p -> p < MAX_OBJECT_SCOPE).count() > 0;
	}

	@Override
	public Properties next() {
		if (!hasNext()) return null;
		
		// computation of the next valid combination and update of properties file
		boolean end = false;
		for (int i=0; i<combination.size() && end==false; i++) {
			
			// if max_scope reached: add 1, and propagate to the consecutive position
			if (combination.get(i)+1 > this.MAX_OBJECT_SCOPE) {
				combination.set(i, 0);
				properties.put(PROPERTY_PREFFIX + classes.get(i), "0");
			}
			
			// otherwise: add 1, and return
			else {
				int newValue = combination.get(i) + 1;
				combination.set(i, newValue);
				properties.put(PROPERTY_PREFFIX + classes.get(i), "" + newValue);
				end = true;
			}
		}

		// return properties if there are more valid combinations, and null otherwise
		return end? properties : null;
	}
}
