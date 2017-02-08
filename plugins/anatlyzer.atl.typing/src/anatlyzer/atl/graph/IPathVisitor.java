package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.batch.PossibleConflictingRulesNode;
import anatlyzer.atl.analyser.batch.PossibleInvariantViolationNode;
import anatlyzer.atl.analyser.batch.PossibleStealingNode;

public interface IPathVisitor {

	public boolean visit(ConditionalNode node);
	public void visitAfter(ConditionalNode node);	

	public boolean visit(CallExprNode node);
	public void visitAfter(CallExprNode node);

	public boolean visit(MatchedRuleExecution node);
	public void visitAfter(MatchedRuleExecution node);

	public boolean visit(MatchedRuleAbstract node);
	public void visitAfter(MatchedRuleAbstract node);

	public boolean visit(LoopNode node);
	public void visitAfter(LoopNode node);

	public boolean visit(LetScopeNode node);
	public void visitAfter(LetScopeNode node);

	public boolean visit(ImperativeRuleExecutionNode node);
	public void visitAfter(ImperativeRuleExecutionNode node);

	public boolean visit(HelperInvocationNode node);
	public void visitAfter(HelperInvocationNode node);

	public boolean visit(RuleResolutionNode node);
	public void visitAfter(RuleResolutionNode node);

	public boolean visitProblem(AccessToUndefinedValueNode node);

	public boolean visitProblem(AccessToUndefinedValue_ThroughEmptyCollectionNode node);
	
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

	public boolean visit(ConditionalStatNode node);

	public boolean visit(ForStatNode node);

	public boolean visitProblem(PossibleStealingNode node);

	public boolean visitProblem(PossibleInvariantViolationNode node);

}
