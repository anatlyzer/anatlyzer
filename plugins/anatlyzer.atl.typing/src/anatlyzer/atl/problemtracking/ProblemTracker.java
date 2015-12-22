package anatlyzer.atl.problemtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ExecutionNode;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;

/**
 * Represents a mapping between problems in one analysis
 * and problem in a subsequent analysis.
 * 
 * @author jesus
 *
 */
public class ProblemTracker {
	private AnalysisResult first;
	private AnalysisResult second;
	private Map<Problem, PathId> firstIds;
	private Map<Problem, PathId> secondIds;

	public ProblemTracker(AnalysisResult first, AnalysisResult second) {
		this.first = first;
		this.second = second;
		this.firstIds  = computeIds(first);
		this.secondIds = computeIds(second); 
	}
	
	public void copyStatus() {
		HashSet<Problem> originalProblemSet = new HashSet<Problem>(firstIds.keySet());
		secondIds.forEach((problem2, id2) -> {
			Problem found = null;
			for (Problem problem1 : originalProblemSet) {
				PathId id1 = firstIds.get(problem1);
				if ( id1.equals(id2) ) {
					found = problem1;
					break;
				}
			}
			
			if ( found != null ) {
				problem2.setStatus(found.getStatus());
				originalProblemSet.remove(found);				
			}
		});
	}

	/**
	 * Return the list of fixed problems of the first transformation according
	 * to the problems identified in the second analysis.
	 * 
	 * Example:
	 * <pre>
	 *  Transformation 1 (T1)
	 * 	helper A.helper1
	 *  
	 *  objA.helper2	=>	P1
	 *  objA.helper2	=> 	P2
	 *  objA.helper1
	 *  anotherProblem	=> 	P3
	 *  
	 *      ||  Change helper1 to helper2 at the definition place
	 *      \/
	 *      
	 *      
	 *  Transformation 2 (T2)
	 * 	helper A.helper2   
	 *  
	 *  objA.helper2	=>	Fixed
	 *  objA.helper2	=> 	Fixed
	 *  objA.helper1	=> 	P1
	 *  anotherProblem	=> 	P2
	 * </pre>
	 * 
	 * Every problem in T1 is checked to have a correspondence in T2.
	 * If it does not have a correspondence, then it means that the problem
	 * has been fixed.
	 * 
	 */
	public Set<Problem> fixedProblems() {
		HashSet<Problem> fixedProblems = new HashSet<Problem>();
		firstIds.forEach((problem1, id1) -> {			
			Problem found = null;
			for (Problem problem2 : secondIds.keySet()) {
				PathId id2 = secondIds.get(problem2);
				if ( id1.equals(id2) ) {
					found = problem1;
					break;
				}
			}
			
			if ( found == null ) {
				fixedProblems.add(problem1);
			}
		});	
		return fixedProblems;
	}

	
	public Map<Problem, PathId> computeIds(AnalysisResult r) {
		HashMap<Problem, PathId> problemToPath = new HashMap<Problem, PathId>();
		
		ProblemGraph g = new ErrorPathGenerator(r.getAnalyser()).perform();
		for (ProblemPath path : g.getProblemPaths()) {
			problemToPath.put(path.getProblem(), computePathId(path));
		}
		
		return problemToPath;
	}
	
	public PathId computePathId(ProblemPath path) {
		PathId pathId = new PathId(path.getProblem().eClass().getName());
		for (ExecutionNode node : path.getExecutionNodes()) {
			pathId.startPath();
			node.genIdentification(pathId);
		}
		
		return pathId;
	}
	
	
}
