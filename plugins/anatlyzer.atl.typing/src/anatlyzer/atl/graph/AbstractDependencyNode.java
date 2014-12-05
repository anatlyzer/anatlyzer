package anatlyzer.atl.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.errors.Problem;

public abstract class AbstractDependencyNode implements DependencyNode {

	public LinkedList<DependencyNode> dependencies = new LinkedList<DependencyNode>();
	public LinkedList<DependencyNode> depending    = new LinkedList<DependencyNode>();
	public LinkedList<ConstraintNode> constraints = new LinkedList<ConstraintNode>();
	
	protected Problem	problem;
	protected boolean leadsToExecution = true;
	
	@Override
	public void addDependency(DependencyNode node) {
		if ( node == this )
			throw new IllegalArgumentException();
		dependencies.add(node);
		node.addDepending(this);
	}

	public DependencyNode getDependency() {
		if 		( dependencies.size() == 0 ) return null;
		else if ( dependencies.size() == 1 ) return dependencies.get(0);
		
		throw new IllegalStateException("Only one dependency per node supported");
	}

	public DependencyNode getDepending() {
		if 		( depending.size() == 0 ) return null;
		else if ( depending.size() == 1 ) return depending.get(0);
		
		throw new IllegalStateException("Only one depending node supported");
	}

	public ConstraintNode getConstraint() {
		if 		( constraints.size() == 0 ) return null;
		else if ( constraints.size() == 1 ) return constraints.get(0);
		
		throw new IllegalStateException("Only one constraint per node supported");
	}
	
	@Override
	public void addDepending(DependencyNode node) {
		depending.add(node);
	}
	
	
	
	@Override
	public void addConstraint(ConstraintNode constraint) {
		this.constraints.add(constraint);
	}
	
	protected void generatedDependencies(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			if ( n.leadsToExecution() )
				n.genErrorSlice(slice);
		}					
		for(ConstraintNode c : constraints) {
			c.genErrorSlice(slice);
		}					
		
	}

	public void setProblem(Problem p) {
		this.problem = p;
	}
	
	public Problem getProblem() {
		return problem;
	}
	
	protected List<DependencyNode> getDependencies() {
		return Collections.unmodifiableList(dependencies);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		for (DependencyNode n : dependencies) {
			n.genGraphviz(gv);
			gv.addEdge(this, n);
		}
		
		for (ConstraintNode c : constraints) {
			c.genGraphviz(gv);
			gv.addEdge(this, c);
		}
		
	}
	
	
	public boolean leadsToExecution() {
		return this.leadsToExecution;
	}
	
	public void setLeadsToExecution(boolean leadsToExecution) {
		this.leadsToExecution  = leadsToExecution;
	}
	
}
