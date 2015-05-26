package anatlyzer.atl.graph;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
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
		return genPathCondition(model, (m) -> getDepending().genCSP(m));
	}

	protected OclExpression genPathCondition(CSPModel model, Function<CSPModel, OclExpression> generator) {
		Metaclass[] patternTypes = ATLUtils.getAllPatternTypes(rule);
		IteratorExp exists = null;
		IteratorExp existsOuter = null;

		HashMap<String, VariableDeclaration> mappedVars = new HashMap<String, VariableDeclaration>(); 
		
		// T::allInstances->exists(t | <? : allInstancesBody> )
		for(int i = 0; i < patternTypes.length; i++) {
			Metaclass metaclass = patternTypes[i];
			VariableDeclaration varDcl = rule.getInPattern().getElements().get(i);

			OperationCallExp allInstancesCall = model.createAllInstances(metaclass);
			IteratorExp existsInner = model.createExists(allInstancesCall, varDcl.getVarName());
			VariableDeclaration varDclMappedVar = existsInner.getIterators().get(0);
			model.addToScope(varDcl, varDclMappedVar);

			mappedVars.put(varDcl.getVarName(), varDclMappedVar);
			
			if ( exists != null ) {
				exists.setBody(existsInner);
			}
			exists = existsInner;
			
			if ( existsOuter == null ) {
				existsOuter = exists;
			}
		}
		
		
		
		
		/*
		Metaclass metaclass = null;
		VariableDeclaration varDcl = rule.getInPattern().getElements().get(0);
		
		if ( ATLUtils.isOneOneRule(rule) ) {
			metaclass = ATLUtils.getInPatternType(rule);
		}
		else {
			metaclass = ATLUtils.getAllPatternTypes(rule)[0];
		}

		// T::allInstances->exists(t | <? : allInstancesBody> )
		OperationCallExp allInstancesCall = model.createAllInstances(metaclass);
		IteratorExp exists = model.createExists(allInstancesCall, varDcl.getVarName());
		VariableDeclaration varDclMappedVar = exists.getIterators().get(0);
		model.addToScope(varDcl, varDclMappedVar);

		// If there are more that one input metaclass in the pattern, extend the exists iterator
		// with more AllInstances->exists
		if ( ! ATLUtils.isOneOneRule(rule) ) {
			Metaclass[] patternTypes = ATLUtils.getAllPatternTypes(rule);
			for(int i = 1; i < patternTypes.length; i++) {
				Metaclass m = patternTypes[i];
				
				VariableDeclaration varDcl2 = rule.getInPattern().getElements().get(i);
				OperationCallExp allInstancesCall2 = model.createAllInstances(m);
				IteratorExp exists2 = model.createExists(allInstancesCall2, varDcl2.getVarName());

				
			}

			System.err.println("MatchedRuleExecution: rules with several input types not supported yet");
			// throw new IllegalArgumentException();
			
		}
		*/

		
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
			mapSuperRuleVariables(mappedVars, (MatchedRule) rule.getSuperRule(), model);
			OclExpression whenFilterExpr = generator.apply(model); // getDepending().genCSP(model);
			ifExp.setThenExpression(whenFilterExpr);
		} else {
			// set <? : allInstancesBody>
			mapSuperRuleVariables(mappedVars, (MatchedRule) rule.getSuperRule(), model);
			OclExpression whenFilterExpr = generator.apply(model); // getDepending().genCSP(model);

			// set <? : allInstancesBody>
			if ( letUsingDeclarations  == null )
				exists.setBody(whenFilterExpr);
			else 
				letUsingDeclarationInnerLet.setIn_(whenFilterExpr);
		}
		
		return existsOuter;
	}

	private void mapSuperRuleVariables(HashMap<String, VariableDeclaration> mappedVars, MatchedRule superRule, CSPModel model) {
		if ( superRule == null )
			return;
		
		for(VariableDeclaration supVar :  superRule.getInPattern().getElements()) {
			VariableDeclaration mappedVar = mappedVars.get(supVar.getVarName());
			if ( mappedVar != null ) {
				model.addToScope(supVar, mappedVar);				
			}
		}
	
		mapSuperRuleVariables(mappedVars, (MatchedRule) superRule.getSuperRule(), model);
		
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		Metaclass[] patternTypes = ATLUtils.getAllPatternTypes(rule);
		if ( patternTypes.length > 1 ) {
			throw new UnsupportedOperationException();
		}
		
		OperationCallExp allInstancesCall = model.createAllInstances(patternTypes[0]);
		CollectionOperationCallExp isEmpty = model.createCollectionOperationCall(allInstancesCall, "isEmpty");
		
		OclExpression rest = genPathCondition(model, (m) -> getDepending().genWeakestPrecondition(m));
		
		return model.createBinaryOperator(isEmpty, model.negateExpression(rest), "or");
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
