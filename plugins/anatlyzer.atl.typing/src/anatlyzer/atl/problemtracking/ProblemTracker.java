package anatlyzer.atl.problemtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
	
	public Map<Problem, PathId> computeIds(AnalysisResult r) {
		HashMap<Problem, PathId> problemToPath = new HashMap<Problem, PathId>();
		
		ProblemGraph g = new ErrorPathGenerator(r.getAnalyser()).perform();
		for (ProblemPath path : g.getProblemPaths()) {
			problemToPath.put(path.getProblem(), computePathId(path));
		}
		
		return problemToPath;
	}
	
	public PathId computePathId(ProblemPath path) {
		PathId pathId = new PathId();
		for (ExecutionNode node : path.getExecutionNodes()) {
			pathId.startPath();
			node.genIdentification(pathId);
		}
		return pathId;
	}

	
	
}
