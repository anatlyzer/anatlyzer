package anatlyzer.atl.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.OCL.OclExpression;

public class ProblemPath implements IDetectedProblem {
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
	
	//
	// IDetectedProblem methods
	//
	
	@Override
	public ErrorSlice getErrorSlice(IAnalyserResult analyserResult) {
		return this.errorNode.getErrorSlice(analyserResult);
	}

	@Override
	public OclExpression getWitnessCondition() {
		return CSPGenerator.generateCSPCondition(this);
	}

	
	
}
