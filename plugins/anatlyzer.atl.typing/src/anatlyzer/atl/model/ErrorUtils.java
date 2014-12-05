package anatlyzer.atl.model;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;


public class ErrorUtils {

	public static String getErrorMessage(Problem p) {
		return p.getClass().getName();
	}
	
	public static String getShortError(LocalProblem p) {
		if ( p.getDescription() != null ) {
			// return p.getDescription().split("\n")[0];
			// return p.getDescription().replace("\n", " ");
			return p.getDescription();
		} else {
			return p.getClass().getSimpleName().replace("Impl", "") + " - "+ p.getLocation();	
		}
	}
	
}
