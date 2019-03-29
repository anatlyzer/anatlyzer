package witness.generator;

/**
 * USE is not able to solve conditions on arbitrary constant-strings (e.g. 'John'), but 
 * it solves equalities involving constant-strings of the form 'string1', 'string2' and 
 * so on. This adapter builds a mapping between the constant-strings appearing in a set
 * of ocl expressions, and the formatted ones that USE is able to solve (e.g. {'John'->'string1'}). 
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import transML.utils.solver.use.StringAdapter;

// This basically rewrites all the original StringAdapter from the original transML,
// just to make it easier to make changes.

// In the future we won't need to do this hack and just remove StringAdapter 
public class USEStringAdapter extends StringAdapter {
	private HashMap<String,String> string_adapter;
	private int index = 1;
	
	public USEStringAdapter () {
		string_adapter = new HashMap<String,String>();
	}
	
	/**
	 * Builds a mapping between the constant-strings appearing in a list of ocl expressions,
     * and the formatted ones that USE is able to solve (e.g. {'John'->'string1'}). 
	 */
	public void addToMapping (List<String> ocl_expressions) {
		Pattern pattern = Pattern.compile("'([^']*)'");
		for (String ocl_expression : ocl_expressions) {
			Matcher matcher = pattern.matcher(ocl_expression);
			while (matcher.find()) {
				String string_adapted = matcher.group();
				if (!string_adapter.containsKey(string_adapted)) {
					string_adapter.put(string_adapted, "'string"+index+"'");
					index++;
				}
			}
		}
	}
	
	/**
	 * It adapts the ocl expression, substituting any constant-string as specified by the established mapping.
	 */
	public String adapt_ocl_expression(String ocl_expression) {
		for (Entry<String,String> adaptation : string_adapter.entrySet()) {
			// We don't use replaceAll because it takes a regular expression, and thus strings like '{john|}' will make it fail
			while ( ocl_expression.contains(adaptation.getKey())) {
				ocl_expression = ocl_expression.replace(adaptation.getKey(), adaptation.getValue());
			}
		}
		return ocl_expression;
	}
	
	/**
	 * It returns the constant-string mapped to a use-string (i.e. the opposite to adapt_ocl_expression).
	 */
	public String adapt_use_string(String use_string) {
		// by default, we return the same string
		String ocl_string = use_string;
		
		// search the ocl constant-string mapped to the use-string
		if (string_adapter.containsValue(use_string)) return use_string;
			for (Entry<String,String> adaptation : string_adapter.entrySet()) {
				if (adaptation.getValue().equals("'"+use_string+"'")) {
					ocl_string = adaptation.getKey();
					ocl_string = ocl_string.substring(1, ocl_string.length()-1);
					break;
				}
			}
			
		return ocl_string;
	}
	
	/**
	 * It returns the number of adaptations (or mappings) established.
	 * @return
	 */
	public int getNumberAdaptations() {
		return string_adapter==null? 0 : string_adapter.keySet().size(); 
	}
}
