package anatlyzer.atl.graph;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atlext.OCL.VariableDeclaration;

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
	
	boolean isVarRequiredByErrorPath(VariableDeclaration v);
	
	//String genCSP(String dependent);
}
