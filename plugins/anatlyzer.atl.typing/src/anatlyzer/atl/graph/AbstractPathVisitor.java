package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.batch.PossibleConflictingRulesNode;

public abstract class AbstractPathVisitor implements IPathVisitor {

	protected ProblemPath path;

	public void bottomUp(ProblemPath path) {
		this.path = path;
		for (ExecutionNode node : path.getExecutionNodes()) {
			node.bottomUp(this);
		}		
	}
	
	@Override
	public boolean visit(ConditionalNode node) {
		return true;
	}

	@Override
	public boolean visit(CallExprNode node) {
		return true;
	}

	@Override
	public boolean visit(MatchedRuleExecution node) {
		return true;
	}

	@Override
	public boolean visit(MatchedRuleAbstract node) {
		return true;
	}

	@Override
	public boolean visit(LoopNode node) {
		return true;
	}

	@Override
	public boolean visit(LetScopeNode node) {
		return true;
	}

	@Override
	public boolean visit(ImperativeRuleExecutionNode node) {
		return true;
	}

	@Override
	public boolean visit(HelperInvocationNode node) {
		return true;
	}

	@Override
	public boolean visit(RuleResolutionNode node) {
		return true;
	}
	
	@Override
	public boolean visitProblem(
			BindingPossiblyUnresolvedNode node) {
		return true;
	}

	@Override
	public boolean visitProblem(ResolveTempPossiblyUnresolvedNode node) {
		return true;
	}
	
	@Override
	public boolean visitProblem(
			BindingTargetFeatureNotFound node) {
		return true;
	}

	@Override
	public boolean visit(DelimitedExpressionNode node) {
		return true;
	}

	@Override
	public boolean visitProblem(BindingWithoutRuleNode node) {
		return true;
	}

	@Override
	public boolean visitProblem(
			BindingWithResolvedByIncompatibleRuleNode node) {
		return true;
	}

	@Override
	public boolean visitProblem(GenericErrorNode node) {
		return true;
	}

	@Override
	public boolean visitProblem(
			BindingExpectedOneAssignedManyNode node) {
		return true;
	}

	@Override
	public boolean visitProblem(
			PossibleConflictingRulesNode node) {
		return true;
	}
	
	@Override
	public boolean visitProblem(AccessToUndefinedValueNode node) {
		return true;
	}
	
	@Override
	public boolean visitProblem(FeatureOrOperationFoundInSubtypeNode<?> node) {
		return true;
	}
}
