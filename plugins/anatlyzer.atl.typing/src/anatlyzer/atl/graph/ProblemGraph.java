package anatlyzer.atl.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;

/**
 * This class holds a set of problem paths, which may share nodes.
 *  
 * @author jesus
 *
 */
public class ProblemGraph {
	private HashMap<Problem, ProblemPath> problemPaths = new HashMap<Problem, ProblemPath>();
	private ProblemTree tree = new ProblemTree();
	
	public void addProblemPath(ProblemPath path) {
		assert(path != null);
		this.problemPaths.put(path.getProblem(), path);

		tree.add(path);
	}
	
	public IProblemTree getProblemTree() {
		return tree;
	}
	
	public List<ProblemPath> getProblemPaths() {
		return new ArrayList<ProblemPath>(problemPaths.values());
	}
	
	public ProblemPath getPath(Problem p) {
		return problemPaths.get(p);
	}
	
	public List<ProblemPath> getSortedPathsByLocation() {
		LinkedList<ProblemPath> sorted = new LinkedList<ProblemPath>(problemPaths.values());
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
	
	public static interface IProblemTreeNode {
		public List<? extends IProblemTreeNode> getNodes();
		public Problem getProblem();
	}
	
	public static interface IProblemTree { 
		public List<? extends IProblemTreeNode> getNodes();
	}
		
	/**
	 * Represents an ordering of problems according to path dependencies. 
	 * Problems that do not depends on other problems (i.e., it path is not part of another path)
	 * are at the top of the tree.
	 */
	protected static class ProblemTree extends AbstractProblemTreeNode implements IProblemTree {

		@Override
		public Problem getProblem() {
			throw new UnsupportedOperationException("Cannot be called");
		}
	
	}
	
	protected static abstract class AbstractProblemTreeNode implements IProblemTreeNode {
		protected List<ProblemTreeNode> children = new ArrayList<ProblemTreeNode>();
		
		@Override
		public List<? extends IProblemTreeNode> getNodes() {
			return children;
		}
		
		protected void add(ProblemPath p) {
			boolean addedToChildren = false;
			
			for (ProblemTreeNode node : children) {			
				// Is the problem in the current node (p1) is within the
				// path to be added (p2), then p2 depends on p1 because
				// you cannot "execute p2" without probably making p1 fail. 
				LocalProblem problem = node.path.getProblem();				
				if ( p.isProblemInPath(problem) )   {
					node.add(p);
					addedToChildren = true;
					break;
				}
			}
			
			if ( ! addedToChildren )
				add(new ProblemTreeNode(p));
		}
		
		protected void add(ProblemTreeNode problemTreeNode) {
			children.add(problemTreeNode);
		}
	}
	
	protected static class ProblemTreeNode extends AbstractProblemTreeNode {
		private ProblemPath path;

		protected ProblemTreeNode(ProblemPath p) {
			this.path = p;
		}
		
		@Override
		public Problem getProblem() {
			return this.path.getProblem();
		}
	}
}
