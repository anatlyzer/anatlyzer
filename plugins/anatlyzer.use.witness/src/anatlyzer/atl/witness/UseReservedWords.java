package anatlyzer.atl.witness;

import java.util.HashMap;

public class UseReservedWords {
	private static HashMap<String, String> reserved = new HashMap<String, String>();
	static {
		reserved.put("model", "model_");		
		reserved.put("constraints", "constraints_");		
		reserved.put("enum", "enum_");				
		reserved.put("abstract", "abstract_");				
		reserved.put("class", "class_");
		reserved.put("attributes", "attributes_");
		reserved.put("operations", "operations_");
		reserved.put("association", "association_");
		reserved.put("composition", "composition_");
		reserved.put("aggregation", "aggregation_");
		reserved.put("between", "between_");
		reserved.put("role", "role_");
		reserved.put("ordered", "ordered_");
		reserved.put("end", "end_");
		reserved.put("associationclass", "associationclass_");
		reserved.put("context", "context_");
		reserved.put("inv", "inv_");
		reserved.put("pre", "pre_");
		reserved.put("post", "post_");
		reserved.put("OrderedSet", "OrderedSet_");
		reserved.put("Sequence", "Sequence_");
		reserved.put("Bag", "Bag_");
		reserved.put("Set", "Set_");
		reserved.put("Integer", "Integer_");
		reserved.put("Real", "Real_");
		reserved.put("String", "String_");
		reserved.put("Boolean", "Boolean_");

		reserved.put("null", "null_");	
		reserved.put("if", "if_");
		reserved.put("else", "else_");
		reserved.put("then", "then_");
		reserved.put("endif", "endif_");
		
		reserved.put("in", "in_");
		reserved.put("let", "let_");
		reserved.put("not", "not_");
		reserved.put("self", "self_"); // just in case
		reservedWord("end");
		
		// from USE.g
		reservedWord("statemachines");
		reservedWord("associationClass");
		reservedWord("associationclass");
		reservedWord("subsets");
		reservedWord("union");
		reservedWord("redefines");
		reservedWord("inv");
		
		reservedWord("psm");
		reservedWord("states");
		reservedWord("transitions");
		
		reservedWord("create");
		
		reservedWord("signal");
		reservedWord("derive");
		reservedWord("derived");
		reservedWord("init");
		reservedWord("qualifier");
		
		reservedWord("implies");
		
		reservedWord("or");
		reservedWord("xor");
		reservedWord("and");
		reservedWord("div");
		
		reservedWord("iterate");
//		reservedWord("select");
//		reservedWord("reject");
//		reservedWord("collect");
//		reservedWord("exists");
//		reservedWord("forAll");
//		reservedWord("isUnique");
//		reservedWord("sortedBy");
		
		reservedWord("oclInState"); 
		
		reservedWord("true"); //not sure about this one
		reservedWord("false"); //not sure about this one
		
		reservedWord("oclEmpty"); 
		reservedWord("oclUndefined"); 
		reservedWord("Undefined"); 
		reservedWord("null"); 
		
		reservedWord("Tuple"); 
		
		// soil
		reservedWord("new"); 
		reservedWord("destroy"); 
		reservedWord("insert"); 
		reservedWord("into"); 
		reservedWord("delete"); 
		reservedWord("from"); 
		reservedWord("for"); 
		reservedWord("in"); 
		reservedWord("do"); 
		reservedWord("while"); 
		reservedWord("begin"); 
		reservedWord("end"); 
		reservedWord("declare"); 
		
		
		// This is quite special because it is a problem of the
		// properties file, which cannot have _min_min, max_max, etc.
		reserved.put("min", "minimum");
		reserved.put("max", "maximum");		
	}
	
	public static boolean isReserved(String word) {
		return reserved.containsKey(word);
	}
	
	private static void reservedWord(String string) {
		reserved.put(string, string + "_");		
	}

	public static String getReplacement(String word) {
		return reserved.get(word);
	}

	public static String replacementOrSame(String name) {
		return reserved.getOrDefault(name, name);
	}
	
}
