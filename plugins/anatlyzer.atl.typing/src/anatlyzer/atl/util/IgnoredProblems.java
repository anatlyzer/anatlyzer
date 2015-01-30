package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.Unit;

public class IgnoredProblems {
	public static HashMap<String, Set<String>> categories = new HashMap<String, Set<String>>();
	
	static {		
		addProblem(NoBindingForCompulsoryFeature.class,            	"MissingBinding");
		addProblem(CollectionOperationOverNoCollectionError.class, 	"CollectionOperationOverNoCollection");
		addProblem(BindingWithoutRule.class,                       	"NoRuleForBinding");
		addProblem(ResolveTempWithoutRule.class,                   	"NoRuleForResolveTemp");
		addProblem(BindingWithResolvedByIncompatibleRule.class,    	"InvalidTargetForBinding");
		addProblem(BindingPossiblyUnresolved.class,    				"BindingPossiblyUnresolved");
		
		// By category
		// => style
		addProblem(CollectionOperationOverNoCollectionError.class, "style");
		
		// => potential
		addProblem(BindingWithResolvedByIncompatibleRule.class, 	"potential");
		addProblem(BindingPossiblyUnresolved.class,    				"potential");
		
		// TODO: FeatureFoundInSubclass is stored as a recovery... 
		
		// => binding analysis
		addProblem(BindingWithoutRule.class, 						"binding");
		addProblem(ResolveTempWithoutRule.class,                   	"binding");
		addProblem(BindingWithResolvedByIncompatibleRule.class,     "binding");
		addProblem(BindingPossiblyUnresolved.class,    				"binding");
	}
	
	private static void addProblem(Class<?> class1, String category) {
		if ( ! categories.containsKey(category) ) {
			categories.put(category, new HashSet<String>());
		}
		
		categories.get(category).add(class1.getSimpleName());
	}
	
	public static List<Problem> getNonIgnoredProblems(Analyser analyser) {
		ArrayList<Problem> result = new ArrayList<Problem>( analyser.getErrors().getAnalysis().getProblems() );
		Unit unit = analyser.getATLModel().getRoot();
		List<String> ignored = findIgnoredProblems(unit);
		
		for (String ignoredCategory : ignored) {
			Set<String> problemNames = categories.get(ignoredCategory);
			if ( problemNames == null )
				continue; // user wrote an invalid category
			
			ListIterator<Problem> it = result.listIterator();
			while ( it.hasNext() ) {
				Problem p = it.next();
				if ( problemNames.contains(p.eClass().getName()) ) {
					it.remove();
				}
			}
		}
		
		return result;
	}

	private static final String DISABLE = "@disable";
	private static List<String> findIgnoredProblems(Unit unit) {
		List<String> result = new ArrayList<String>();
		for (String str : unit.getCommentsBefore()) {
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
