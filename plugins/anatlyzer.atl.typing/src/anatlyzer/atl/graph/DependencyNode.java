package anatlyzer.atl.graph;

import anatlyzer.atl.errors.Problem;

public interface DependencyNode extends GraphNode {
	/*
	void setGraph(ProblemPath dependencyGraph);
	ProblemPath getGraph();
	*/
	
	void addDependency(DependencyNode node);
	void addDepending(DependencyNode node);
	void addConstraint(ConstraintNode constraint);
	
	public DependencyNode getDependency();
	
	void setProblem(Problem p);
	Problem getProblem();
	
	
	boolean leadsToExecution();
	void    setLeadsToExecution(boolean leadsToExecution);
	
	//String genCSP(String dependent);
}
