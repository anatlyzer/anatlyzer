package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class MatchedRuleExecution extends MatchedRuleBase implements ExecutionNode {

	public MatchedRuleExecution(MatchedRule atlRule) {
		super(atlRule);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(Metaclass m : ATLUtils.getAllPatternTypes(rule) ) {
			slice.addExplicitMetaclass(m);
		}

		// TODO: Slice only the required ones!
		if ( rule.getVariables().size() > 0 ) {
			for (RuleVariableDeclaration v : rule.getVariables()) {
				OclSlice.slice(slice, v.getInitExpression());
			}
		}
		
		generatedDependencies(slice);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);

		VariableDeclaration varDcl = rule.getInPattern().getElements().get(0);
		gv.addNode(this, rule.getName() + "\\nfrom: " + varDcl.getType().getName(), leadsToExecution ); //+ "\\n" + OclGenerator.gen(constraint) );
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		Metaclass metaclass = null;
		VariableDeclaration varDcl = rule.getInPattern().getElements().get(0);
		
		if ( ATLUtils.isOneOneRule(rule) ) {
			metaclass = ATLUtils.getInPatternType(rule);
		}
		else {
			metaclass = ATLUtils.getAllPatternTypes(rule)[0];
		}

		if ( ! ATLUtils.isOneOneRule(rule) ) {
			System.err.println("MatchedRuleExecution: rules with several input types not supported yet");
			// throw new IllegalArgumentException();
			
			/*
			for(int i = 1; i < ((MatchedRuleManyAnn) rule).getInPatternTypes().size(); i++) {
				metaclass = ((MatchedRuleManyAnn) rule).getInPatternTypes().get(i);
				varDcl    = atlRule.getInPattern().getElements().get(i);

				String s = metaclass.getName() + ".allInstances()"; 
				buf.generateLoop(s, "exists", varDcl.getVarName());
			}
			*/
		}

		// T::allInstances->exists(t | <? : allInstancesBody> )
		OperationCallExp allInstancesCall = model.createAllInstances(metaclass);
		IteratorExp exists = model.createExists(allInstancesCall, varDcl.getVarName());
		
		VariableDeclaration varDclMappedVar = exists.getIterators().get(0);
		model.addToScope(varDcl, varDclMappedVar);
			
		Pair<LetExp, LetExp> letPair = genLocalVarsLet(model);
		
		LetExp letUsingDeclarations = letPair._1;
		LetExp letUsingDeclarationInnerLet = letPair._2;
		
		if ( letUsingDeclarations != null ) {			
			exists.setBody(letUsingDeclarations);			
		}

		
		if ( rule.getInPattern().getFilter() != null ) {
			// => if ( filterCondition ) then <? : whenFilter> else false endif
			OclExpression condition = this.getConstraint().genCSP(model);
			IfExp ifExp = model.createIfExpression(condition, null, model.createBooleanLiteral(false) );
			
			// set <? : allInstancesBody>
			if ( letUsingDeclarations == null )
				exists.setBody(ifExp);
			else 
				letUsingDeclarationInnerLet.setIn_(ifExp);
			
			// => set <? : whenFilter>
			mapSuperRuleVariables(varDclMappedVar, (MatchedRule) rule.getSuperRule(), model);
			OclExpression whenFilterExpr = getDepending().genCSP(model);
			ifExp.setThenExpression(whenFilterExpr);
		} else {
			// set <? : allInstancesBody>
			mapSuperRuleVariables(varDclMappedVar, (MatchedRule) rule.getSuperRule(), model);
			OclExpression whenFilterExpr = getDepending().genCSP(model);

			// set <? : allInstancesBody>
			if ( letUsingDeclarations  == null )
				exists.setBody(whenFilterExpr);
			else 
				letUsingDeclarationInnerLet.setIn_(whenFilterExpr);
		}
		
		return exists;
	}

	private void mapSuperRuleVariables(VariableDeclaration varDclMappedVar, MatchedRule superRule, CSPModel model) {
		if ( superRule == null )
			return;
		
		if ( superRule.getInPattern().getElements().size() > 1 )
			throw new UnsupportedOperationException("Only super rules with one input elements supported");
		
		
		SimpleInPatternElement e = (SimpleInPatternElement) superRule.getInPattern().getElements().get(0);
		model.addToScope(e, varDclMappedVar);
		
		mapSuperRuleVariables(varDclMappedVar, (MatchedRule) superRule.getSuperRule(), model);
		
	}
		
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		slice.addRule(this.rule);
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		throw new IllegalStateException();
	}

}
