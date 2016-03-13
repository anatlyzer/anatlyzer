package anatlyzer.atl.graph;

import java.util.ArrayList;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public abstract class RuleBase extends AbstractDependencyNode {
	private Rule rule;
	
	public RuleBase(Rule rule) {
		this.rule = rule;
	}
	
	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model) {
		return genLocalVarsLet(model, true, false);
	}

	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model, boolean setLetType) {
		return genLocalVarsLet(model, true, setLetType);
	}

	/**
	 * Method factorised here so that it can be used both in MatchedRuleBase and in MatchedRuleExecution
	 * 
	 * @param rule
	 * @param model
	 * @param considerOutputPattern
	 * @param setLetType
	 * @return
	 */
	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model, boolean considerOutputPattern, boolean setLetType) {
		LetExp letUsingDeclarations  = null;
		LetExp letUsingDeclarationInnerLet  = null;
		if ( rule.getVariables().size() > 0 ) {

			// We mark the variables as required or not (instead of adding them to a list)
			// to keep the order in subsequent loops
			boolean requiredVars[] = new boolean[rule.getVariables().size()];
			
			for(int i = 0; i < rule.getVariables().size(); i++) {
				RuleVariableDeclaration v = rule.getVariables().get(i);
				if ( ! isRequiredByRuleFilter(v) && ! isRequiredByErrorPath(v) ) 
					continue;

				// Variable i-th is required
				requiredVars[i] = true;
				
				// Get dependendent variables
				for(int j = 0; j < rule.getVariables().size(); j++) {
					if ( i == j ) 
						continue;
					
					RuleVariableDeclaration v2 = rule.getVariables().get(j);
					if ( requiredVars[j] )
						continue; // already added
					
					if ( ATLUtils.findVariableReference(v.getInitExpression(), v2) != null ) {
						requiredVars[j] = true;
					}
				}
			}

			// Now we have to make sure they are in the same order as they are declared
			
			for(int i = 0; i < rule.getVariables().size(); i++) {
				if ( ! requiredVars[i] )
					continue;
				
				RuleVariableDeclaration v = rule.getVariables().get(i);

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
		if ( rule instanceof RuleWithPattern ) {
			InPattern inPattern = ((RuleWithPattern) rule).getInPattern();
			return inPattern.getFilter() != null && 
					ATLUtils.findVariableReference(inPattern.getFilter(), v) != null;
		} else {
			return false;
		}
	}

	
}
