package anatlyzer.atl.analyser.generators;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;


public class PathId {

	private int currentPath = -1;
	private ArrayList<String> paths = new ArrayList<String>();
	
	public PathId(String problemTypeName) {
		// The first element is the type of the problem, to disambiguate
		// problems of different kind, but with the same error path 
		paths.add(problemTypeName);
	}
	
	public void startPath() {
		currentPath++;
		paths.add("");
	}
	
	public void next(String s) {
		if ( currentPath < 0 )
			throw new IllegalStateException();
		paths.set(currentPath, paths.get(currentPath) + s + ";");
	}

	/**
	 * Generate a signature for the expression, but also
	 * gathers the involved calls.
	 * 
	 * @param expr
	 * @return
	 */
	public String gen(OclExpression expr) {
		String s = PathIdStringVisitor.serialize(expr, this);
		s.replaceAll("\n\t", "");
		return s;
	}


	// Utility methods
	
	public static String typeSig(OclType t) {
		if ( t instanceof OclModelElement ) 
			return ((OclModelElement) t).getModel().getName() + "!" + t.getName();
		// It is a primitive type
		return t.eClass().getName();
	}	
		
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof PathId ) {
			PathId pathId = (PathId) obj;
			if ( paths.size() == pathId.paths.size() ) {
				for(int i = 0; i < paths.size(); i++) {
					if ( ! paths.get(i).equals(pathId.paths.get(i)) )  {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public static void sortRules(List<MatchedRule> allRules) {
		allRules.sort((r1, r2) -> {
			return r1.getName().compareTo(r2.getName());
		});
	}
}
