package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.batch.SubsumptionNode;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.witness.EclipseUseWitnessFinder;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * This quick fix modifies the filters of the rules involved in a rule conflict so that
 * the conflict is removed. Given a rule, it adds the negated filters of the other rules to it.
 * To be more precise the quick fix checks subsumption to avoid adding the negated filter
 * if that makes the rule non-applicable.
 *    
 * @qfxName Add filters to involved rules
 * @qfxError {@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet}
 * 
 * @author jesusc
 */
public class RuleConflictQuickfix_ModifyRuleFilter extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, ConflictingRuleSet.class);
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		ConflictingRuleSet conflictingRuleSet = (ConflictingRuleSet) marker.getAttribute(AnATLyzerBuilder.PROBLEM);;
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		fixRules(qfa, conflictingRuleSet, (List<? extends MatchedRule>)conflictingRuleSet.getRules());

		return qfa;
	}
	
	private void fixRules(QuickfixApplication qfa, ConflictingRuleSet rs, List<? extends MatchedRule> rules) throws CoreException {
		// OverlappingRules overlap = (OverlappingRules) conflictingRuleSet.getAnalyserInfo();
		
		if ( rules.size() == 2 ) {
			MatchedRule r1 = rules.get(0); // overlap.getRules().get(0);
			MatchedRule r2 = rules.get(1); // overlap.getRules().get(1);
			// check at least one of them have a filter?
			
			// r1 subsumes r2, then we add r2 to r1 only
			if ( r2.getInPattern().getFilter() != null && checkSubsumption(rs, r1, r2) ) {
				subsumptionReplacement(qfa, r1, r2);
				System.out.println("Subsumption! " + r1.getName() + " > " + r2.getName());
				return;
			}
			else if ( r1.getInPattern().getFilter() != null && checkSubsumption(rs, r2, r1) ) {
				subsumptionReplacement(qfa, r2, r1);
				System.out.println("Subsumption! " + r2.getName() + " > " + r1.getName());
				return;
			}
		}
		
		
		// This is the old version that works generically for n rules, but typically we use pairs of rules
		// It is used when there are more than two rules or if there is no subsumption
		
		// Compute all the filters at once, independently
		Map<MatchedRule, OclExpression> filters = rules.stream().collect(Collectors.toMap((r) -> r, (r) -> {
			List<MatchedRule> otherRules = rules.stream().
					filter(o -> o != r).
					map(o -> (MatchedRule) o).collect(Collectors.toList());
			
			VariableDeclaration var = r.getInPattern().getElements().get(0);
			return genCheck(var, otherRules, false);
		}));
		
		filters.forEach((r, newFilter) -> {
			extendRuleFilter(qfa, r, newFilter);
		});
			
			
	}

	private void subsumptionReplacement(QuickfixApplication qfa,
			MatchedRule r1, MatchedRule r2) {
		// This is an optimization
		OclExpression newFilter;
		if ( ATLUtils.getInPatternType(r1).getKlass() == ATLUtils.getInPatternType(r2).getKlass() ) {
			newFilter = ASTUtils.negate( copyFilter(r1.getInPattern().getElements().get(0), r2) );
		} else {
			newFilter = genCheck(r1.getInPattern().getElements().get(0), Collections.singletonList(r2), false);
		}
		extendRuleFilter(qfa, r1, newFilter);
	}

	private void extendRuleFilter(QuickfixApplication qfa, MatchedRule r, OclExpression newFilter) {
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
	}

	/**
	 * Checks if r1 subsumes r2
	 * 
	 * @param overlap
	 * @param r1
	 * @param r2
	 * @return
	 */
	private boolean checkSubsumption(ConflictingRuleSet rs, MatchedRule r1, MatchedRule r2) {
		OverlappingRules overlap = new OverlappingRules(rs);
		
		IAnalyserResult analyser = getAnalysisResult().getAnalyser();
		ATLModel atlModel = analyser.getATLModel();
		
		SubsumptionNode node = new SubsumptionNode(overlap.getTypes(), r1, r2);
		
		// SAME AS OVERLAPPINGRULES
		
		// Compute error slice
		ErrorSlice slice = new ErrorSlice(analyser, ATLUtils.getSourceMetamodelNames(atlModel), overlap);
		node.genErrorSlice(slice);			

		// Compute path condition, wrapped into ThisModule.allInstances... to give the transformation
		// context to the condition
		CSPModel model = new CSPModel();
		IteratorExp ctx = model.createThisModuleContext();
		model.setThisModuleVariable(ctx.getIterators().get(0));
		
		OclExpression theCondition = node.genCSP(model, null);
		ctx.setBody(theCondition);
		
		OclExpression condition = ctx;
		IDetectedProblem problem = new SubsumptionProblem(slice, condition, r1, r2);
		
		ProblemStatus result = new EclipseUseWitnessFinder().find(problem, getAnalysisResult());
		switch ( result ) {
		case ERROR_DISCARDED:
		case ERROR_DISCARDED_DUE_TO_METAMODEL:
			return true;
		case ERROR_CONFIRMED: 
		case ERROR_CONFIRMED_SPECULATIVE:
			System.out.println("Confirmed there is NO subsumption: " + r1.getName() + " /> " + r2.getName());
			return false;
		default:
			return false;
		}
	}

	@Override
	public String getDisplayString() {
		return "Add filters to involved rules";
	}
	
	public static class SubsumptionProblem implements IDetectedProblem {

		private ErrorSlice slice;
		private OclExpression condition;
		private List<MatchedRule> rules;

		public SubsumptionProblem(ErrorSlice slice, OclExpression condition, MatchedRule r1, MatchedRule r2) {
			this.slice = slice;
			this.condition = condition;
			
			this.rules = new ArrayList<MatchedRule>();
			this.rules.add(r1);
			this.rules.add(r2);
		}
		
		@Override
		public ErrorSlice getErrorSlice(IAnalyserResult result) {
			return slice;
		}

		@Override
		public OclExpression getWitnessCondition() {
			return condition;
		}

		@Override
		public List<OclExpression> getFrameConditions() {
			return Collections.emptyList();
		}
		
		@Override
		public boolean isExpressionInPath(OclExpression expr) {
			for (MatchedRule rule : rules) {
				if ( rule.getInPattern().getFilter() != null && 
					 AbstractDependencyNode.expressionInExpression(expr, rule.getInPattern().getFilter()) ) {
					return true;
				}
			}
			return false;
		}
		
	}

}
