package anatlyzer.atl.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import anatlyzer.atl.errors.atl_error.LocalProblem;

public class ProblemPath {
	public LinkedList<ExecutionNode> rules = new LinkedList<ExecutionNode>();
	private LocalProblem problem;
	private ProblemNode errorNode;
	
	public ProblemPath(LocalProblem p, ProblemNode errorNode) {
		this.problem = p;
		this.errorNode = errorNode;
	}
	
	public ProblemNode getErrorNode() {
		return errorNode;
	}
	
	public LocalProblem getProblem() {
		return problem;
	}
	
	public void addRule(ExecutionNode ruleNode) {
		rules.add(ruleNode);
	}


	public List<ExecutionNode> getRules() {
		return Collections.unmodifiableList(rules);
	}

	public List<? extends ExecutionNode> getExecutionNodes() {
		return rules;
	}

	
	
}
