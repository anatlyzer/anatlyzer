package anatlyzer.atl.graph;

import java.util.ArrayList;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableExp;

abstract public class MatchedRuleBase extends AbstractDependencyNode {
	protected MatchedRule	rule;

	public MatchedRuleBase(MatchedRule atlRule) {
		this.rule = atlRule;		
	}

	protected Pair<LetExp, LetExp> genLocalVarsLet(CSPModel model) {
		LetExp letUsingDeclarations  = null;
		LetExp letUsingDeclarationInnerLet  = null;
		if ( rule.getVariables().size() > 0 ) {
			for(RuleVariableDeclaration v : rule.getVariables()) {
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
		
		// For each target pattern element, a new local variable is introduced with
		// value OclUndefined. This is needed because the output pattern variable needs
		// to be in the scope when the parameter passing is done.
		// Nevertheless, this is actually only needed for those output pattern elements used as
		// parameters of helpers / lazy or called rules.
		ArrayList<OutPatternElement> passedAsParameters = new ArrayList<OutPatternElement>();
		for(OutPatternElement out : rule.getOutPattern().getElements()) {
			for(VariableExp vexp : out.getVariableExp()) {
				if ( vexp.eContainer() instanceof OperationCallExp ) {
					// This indicates and usage of the variable
					passedAsParameters.add(out);
					break;
				}
			}
		}

		if ( passedAsParameters.size() > 0 ) {
			for(OutPatternElement v : passedAsParameters) {
				OclUndefinedExp undefined = OCLFactory.eINSTANCE.createOclUndefinedExp();
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

		Pair<LetExp, LetExp> letPair = new Pair<LetExp, LetExp>(letUsingDeclarations, letUsingDeclarationInnerLet);
		return letPair;
	}

}
