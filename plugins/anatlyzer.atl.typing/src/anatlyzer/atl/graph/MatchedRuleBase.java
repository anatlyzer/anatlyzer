package anatlyzer.atl.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

abstract public class MatchedRuleBase extends AbstractDependencyNode {
	protected MatchedRule	rule;

	public MatchedRuleBase(MatchedRule atlRule) {
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

	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model) {
		return genLocalVarsLet(model, true, false);
	}

	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model, boolean setLetType) {
		return genLocalVarsLet(model, true, setLetType);
	}
	
	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model, boolean considerOutputPattern, boolean setLetType) {
		LetExp letUsingDeclarations  = null;
		LetExp letUsingDeclarationInnerLet  = null;
		if ( rule.getVariables().size() > 0 ) {
			HashSet<VariableDeclaration> requiredVars = new HashSet<VariableDeclaration>();

			for(int i = 0; i < rule.getVariables().size(); i++) {
				RuleVariableDeclaration v = rule.getVariables().get(i);
				if ( ! isRequiredByRuleFilter(v) && ! isRequiredByErrorPath(v) ) 
					continue;
				
				requiredVars.add(v);
				
				// Get dependendent variables
				for(int j = 0; j < i; j++) {
					RuleVariableDeclaration v2 = rule.getVariables().get(j);
					if ( requiredVars.contains(v2) )
						continue; // already added
					
					if ( ATLUtils.findVariableReference(v2.getInitExpression(), v2) != null ) {
						requiredVars.add(v2);
					}
				}
			}
			
			for(VariableDeclaration v : requiredVars) {
				LetExp let = model.createLetScope(model.gen(v.getInitExpression()), null, v.getVarName());
				if ( setLetType )
					let.getVariable().setType(ATLUtils.getOclType(v.getInitExpression().getInferredType()));
				
				model.addToScope(v, let.getVariable());
				if ( letUsingDeclarations  != null ) {
					letUsingDeclarationInnerLet.setIn_(let);
				} else {
					letUsingDeclarations = let; // the first let
				}
				letUsingDeclarationInnerLet  = let;
			}
		}
		
		if ( considerOutputPattern && rule.getOutPattern() != null ) {
			// For each target pattern element, a new local variable is introduced with
			// value OclUndefined. This is needed because the output pattern variable needs
			// to be in the scope when the parameter passing is done.
			// Nevertheless, this is actually only needed for those output pattern elements used as
			// parameters of helpers / lazy or called rules.
			ArrayList<OutPatternElement> passedAsParameters = new ArrayList<OutPatternElement>();
			for(OutPatternElement out : rule.getOutPattern().getElements()) {
				if ( isRequiredByErrorPath(out) ) {
					passedAsParameters.add(out);					
				}
				/*
				for(VariableExp vexp : out.getVariableExp()) {
					if ( vexp.eContainer() instanceof OperationCallExp ) {
						// This indicates and usage of the variable
						passedAsParameters.add(out);
						break;
					}
				}
				*/
			}
	
			if ( passedAsParameters.size() > 0 ) {
				for(OutPatternElement v : passedAsParameters) {
					// OclUndefinedExp undefined = OCLFactory.eINSTANCE.createOclUndefinedExp();
					
					// Instead of generating an OclUndefined, which is the more logical option,
					// a reference to thisModule is created just as a dummy value. This seems to
					// work better with USE.
					// The only issue is that, somehow, objects must later be filtered to ensure that they 
					// are not thisModule elements
					VariableExp undefined = OCLFactory.eINSTANCE.createVariableExp();
					undefined.setReferredVariable(model.getTargetDummyVariable());
					
					LetExp let = model.createLetScope(undefined, null, v.getVarName());
					if ( setLetType ) 
						let.setType(OCLFactory.eINSTANCE.createOclAnyType());
					
					model.addToScope(v, let.getVariable());
	
					if ( letUsingDeclarations  != null ) {
						letUsingDeclarationInnerLet.setIn_(let);
					} else {
						letUsingDeclarations = let; // the first let
					}
					letUsingDeclarationInnerLet  = let;
				}
			}
		}
		
		Pair<LetExp, LetExp> letPair = new Pair<LetExp, LetExp>(letUsingDeclarations, letUsingDeclarationInnerLet);
		return letPair;
	}

	protected boolean isRequiredByErrorPath(VariableDeclaration v) {
		if ( getDepending() == null ) 
			return false;
		return getDepending().isVarRequiredByErrorPath(v);
	}
	
	protected boolean isRequiredByRuleFilter(VariableDeclaration v) {
		return rule.getInPattern().getFilter() != null && 
			ATLUtils.findVariableReference(rule.getInPattern().getFilter(), v) != null;
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
