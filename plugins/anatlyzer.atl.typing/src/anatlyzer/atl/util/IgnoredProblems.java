package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atlext.ATL.Module;

public class IgnoredProblems {

	public static HashMap<Class<?>, String> problemsToName = new HashMap<Class<?>, String>();
	static {
		problemsToName.put(NoBindingForCompulsoryFeature.class, "MissingBinding");
		problemsToName.put(CollectionOperationOverNoCollectionError.class, "CollectionOperationOverNoCollection");
		
		// By category
		// => style
		problemsToName.put(CollectionOperationOverNoCollectionError.class, "style");
	}
	
	
	public static List<Problem> getNonIgnoredProblems(Analyser analyser) {
		ArrayList<Problem> result = new ArrayList<Problem>();
		Module module = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		List<String> ignored = findIgnoredProblems(module);
		
		EList<Problem> problems = analyser.getErrors().getAnalysis().getProblems();
		
		for (Problem problem : problems) {
			boolean ignore = false;
			for(Entry<Class<?>, String> pair : problemsToName.entrySet()) {
				if ( pair.getKey().isInstance(problem) && ignored.contains(pair.getValue()) ) {
					ignore = true;
					break;
				}
			}
			
			if ( ! ignore ) {
				result.add(problem);
			}
		}
		
		return result;
	}

	private static final String DISABLE = "@disable";
	private static List<String> findIgnoredProblems(Module module) {
		List<String> result = new ArrayList<String>();
		for (String str : module.getCommentsBefore()) {
			String line = str.replaceAll("--", "").trim();
			int index   = line.indexOf(DISABLE);
			if ( index != -1 ) {
				line = line.substring(index + DISABLE.length());
				for(String s : line.split(",")) {
					result.add(s.trim());
				}
			}			
		}

		return result;
	}

}
