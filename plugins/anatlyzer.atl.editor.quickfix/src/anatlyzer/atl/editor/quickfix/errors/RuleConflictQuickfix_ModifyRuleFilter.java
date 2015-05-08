package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class RuleConflictQuickfix_ModifyRuleFilter extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, RuleConflict.class);
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		RuleConflict p = (RuleConflict) marker.getAttribute(AnATLyzerBuilder.PROBLEM);;
		
		QuickfixApplication qfa = new QuickfixApplication();
		for (ConflictingRuleSet conflictingRuleSet : p.getConflicts()) {			
			fixRules(qfa, (List<? extends MatchedRule>)conflictingRuleSet.getRules());
		}
		return qfa;
	}
	
	private void fixRules(QuickfixApplication qfa, List<? extends MatchedRule> rules) {
		// Compute all the filters at one, independently
		Map<MatchedRule, OclExpression> filters = rules.stream().collect(Collectors.toMap((r) -> r, (r) -> {
			List<MatchedRule> otherRules = rules.stream().
					filter(o -> o != r).
					map(o -> (MatchedRule) o).collect(Collectors.toList());
			
			VariableDeclaration var = r.getInPattern().getElements().get(0);
			return genCheck(var, otherRules, false);
		}));
		
		filters.forEach((r, newFilter) -> {
			OclExpression originalFilter = r.getInPattern().getFilter();

			if ( originalFilter != null ) {			
				qfa.change(originalFilter, OCLFactory.eINSTANCE::createOperatorCallExp, (original, andOp, trace) -> {
					andOp.setOperationName("and");
					andOp.setSource(newFilter);
					
					andOp.getArguments().add(original);
				});
			} else {
				qfa.putIn(r.getInPattern(), ATLPackage.eINSTANCE.getInPattern_Filter(), () -> {
					return newFilter;
				});
			}

		});
			
			
	}

	@Override
	public String getDisplayString() {
		return "Add filters to involved rules";
	}
	

}
