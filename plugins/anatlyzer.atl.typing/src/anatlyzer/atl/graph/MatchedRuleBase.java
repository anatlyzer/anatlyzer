package anatlyzer.atl.graph;

import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

abstract public class MatchedRuleBase extends RuleBase {
	protected MatchedRule	rule;

	public MatchedRuleBase(MatchedRule atlRule) {
		super(atlRule);
		this.rule = atlRule;		
	}
	
	public MatchedRule getRule() {
		return rule;
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		for (VariableDeclaration var : rule.getVariables()) {
			if ( problemInExpressionCached(lp, var.getInitExpression()) ) {
				return true;
			}
		}
	
		return 	(rule.getInPattern().getFilter() != null ? 
					problemInExpressionCached(lp, rule.getInPattern().getFilter()) : false) ||
				checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		for (VariableDeclaration var : rule.getVariables()) {
			if ( expressionInExpressionCached(exp, var.getInitExpression()) ) {
				return true;
			}
		}
		return 	(rule.getInPattern().getFilter() != null ? 
					expressionInExpressionCached(exp, rule.getInPattern().getFilter()) : false) ||
				checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genIdentification(PathId id) {	
		id.next(ruleSignature(rule, id));
		followDepending(n -> n.genIdentification(id));
	}
	
	public static String ruleSignature(MatchedRule rule, PathId id) {
		String sig = rule.getInPattern().getElements().stream().map(e -> e.getType()).map(PathId::typeSig).collect(Collectors.joining("&"));
		if ( rule.getInPattern().getFilter() != null ) {
			sig += "[" + id.gen( rule.getInPattern().getFilter() ) + "]";
		}
		return sig;		
	}

}
