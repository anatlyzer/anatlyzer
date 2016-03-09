package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.batch.PossibleConflictingRulesNode;

public interface IPathVisitor {

	public boolean visit(ConditionalNode node);

	public boolean visit(CallExprNode node);

	public boolean visit(MatchedRuleExecution node);

	public boolean visit(MatchedRuleAbstract node);

	public boolean visit(LoopNode node);

	public boolean visit(LetScopeNode node);

	public boolean visit(ImperativeRuleExecutionNode node);

	public boolean visit(HelperInvocationNode node);

	public boolean visit(RuleResolutionNode node);

	public boolean visitProblem(AccessToUndefinedValueNode node);

	public boolean visitProblem(FeatureOrOperationFoundInSubtypeNode<?> node);

	public boolean visitProblem(ResolveTempPossiblyUnresolvedNode node);

	public boolean visitProblem(BindingPossiblyUnresolvedNode node);

	public boolean visitProblem(BindingTargetFeatureNotFound node);

	public boolean visit(DelimitedExpressionNode node);

	public boolean visitProblem(BindingWithoutRuleNode node);

	public boolean visitProblem(BindingWithResolvedByIncompatibleRuleNode node);

	public boolean visitProblem(GenericErrorNode node);

	public boolean visitProblem(BindingExpectedOneAssignedManyNode node);

	public boolean visitProblem(PossibleConflictingRulesNode node);

	public boolean visit(ForEachOutPatternElementNode node);
}
