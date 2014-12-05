package anatlyzer.atl.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import anatlyzer.atl.errors.atl_error.LocalProblem;


public class ProblemGraph {
	private ArrayList<ProblemPath> problemPaths = new ArrayList<ProblemPath>();
	
	public void addProblemPath(ProblemPath path) {
		assert(path != null);
		this.problemPaths.add(path);
	}
	
	public ArrayList<ProblemPath> getProblemPaths() {
		return problemPaths;
	}
	
	public List<ProblemPath> getSortedPaths() {
		LinkedList<ProblemPath> sorted = new LinkedList<ProblemPath>(problemPaths);
		Collections.sort(sorted, new Comparator<ProblemPath>() {
			@Override
			public int compare(ProblemPath arg0, ProblemPath arg1) {
				LocalProblem lp1 = (LocalProblem) arg0.getProblem();
				LocalProblem lp2 = (LocalProblem) arg1.getProblem();
				return lp1.getLocation().compareTo(lp2.getLocation());
			}
		});
		
		return sorted;
	}
	
}
