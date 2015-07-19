package anatlyzer.atl.witness;

import java.util.HashMap;

public class UseReservedWords {
	private static HashMap<String, String> reserved = new HashMap<String, String>();
	static {
		reserved.put("operations", "operations_");
		reserved.put("class", "class_");
		reserved.put("model", "model_");		
	}
	
	public static boolean isReserved(String word) {
		return reserved.containsKey(word);
	}
	
	public static String getReplacement(String word) {
		return reserved.get(word);
	}
	
}
