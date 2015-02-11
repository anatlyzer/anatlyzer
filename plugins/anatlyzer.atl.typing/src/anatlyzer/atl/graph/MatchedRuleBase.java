package anatlyzer.atl.graph;

import java.util.ArrayList;
import java.util.HashSet;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

abstract public class MatchedRuleBase extends AbstractDependencyNode {
	protected MatchedRule	rule;

	public MatchedRuleBase(MatchedRule atlRule) {
		this.rule = atlRule;		
	}
	
	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model) {
		return genLocalVarsLet(model, true);
	}

	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model, boolean considerOutputPattern) {
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
				model.addToScope(v, let.getVariable());
				if ( letUsingDeclarations  != null ) {
					letUsingDeclarationInnerLet.setIn_(let);
				} else {
					letUsingDeclarations = let; // the first let
				}
				letUsingDeclarationInnerLet  = let;
			}
		}
		
		if ( considerOutputPattern ) {
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
					undefined.setReferredVariable(model.getThisModuleVariable());
					
					LetExp let = model.createLetScope(undefined, null, v.getVarName());
	
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
		return getDepending().isVarRequiredByErrorPath(v);
	}
	
	protected boolean isRequiredByRuleFilter(VariableDeclaration v) {
		return rule.getInPattern().getFilter() != null && 
			ATLUtils.findVariableReference(rule.getInPattern().getFilter(), v) != null;
	}

}
