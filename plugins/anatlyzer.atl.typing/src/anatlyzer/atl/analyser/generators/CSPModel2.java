package anatlyzer.atl.analyser.generators;
import java.util.HashMap;
import java.util.function.Function;

import anatlyzer.atl.analyser.generators.OclGeneratorAST.LazyRuleCallTransformationStrategy;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.UnsupportedTranslation;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class CSPModel2 extends CSPModel {

//	HashMap<VariableDeclaration, HashMap<VariableDeclaration, CSPModelScope>> disambiguations = new HashMap<>();
//	
//	public void addToScope(VariableDeclaration varDcl, VariableDeclaration newVar, VariableDeclaration disambiguation) {
//		if ( ! disambiguations.containsKey(varDcl) ) {
//			disambiguations.put(varDcl, new HashMap<VariableDeclaration, CSPModel.CSPModelScope>());
//		}
//
//		scope.put(varDcl, newVar);
//		disambiguations.get(varDcl).put(disambiguation,  new CSPModelScope(scope.getThisModuleVar(), scope));
//	}

	public CSPModel2() {
		this.generator.setLazyRuleStrategy(new LazyRuleToParameter());
	}
	
	
	public void addToScope(VariableDeclaration varDcl, VariableDeclaration newVar, VariableDeclaration disambiguation) {
		((CSPModelScope2) scope).put(varDcl, newVar, disambiguation);
	}

	public static class CSPModelScope2 extends CSPModelScope {
		public CSPModelScope2(VariableDeclaration thisModuleVar,
				CSPModelScope2 cspModelScope2) {
			super(thisModuleVar, cspModelScope2);
			disambiguations.putAll(cspModelScope2.disambiguations);
		}

		public CSPModelScope2(VariableDeclaration thisModuleVar) {
			super(thisModuleVar);
		}

		HashMap<VariableDeclaration, CSPModelScope> disambiguations = new HashMap<>();

		public void put(VariableDeclaration varDcl, VariableDeclaration newVar, VariableDeclaration disambiguation) {			
			this.put(varDcl, newVar);
			
			CSPModelScope savedScope = disambiguations.get(disambiguations);
			if ( savedScope != null && savedScope != this ) {
				throw new IllegalStateException("Rebound disambiguation: " + disambiguation.getVarName() + " : " + disambiguation.getLocation());
			}
			
			// If the scope is already saved (savedScope == this), the we allow the rebound. 
			// This is to handle the case: T.allInstances(), and then "rule (from x:X, y:Y) (to t:T)
			// because the could will call "put" twice, once for x and once for y for the same disambiguation variable.
			disambiguations.put(disambiguation,  new CSPModelScope2(this.getThisModuleVar(), this));
		}
		
		public CSPModelScope getDisambiguation(VariableDeclaration vd) {
			if ( ! disambiguations.containsKey(vd) ) {
				throw new IllegalStateException("No variable " + vd.getVarName() + " : " + vd.getLocation() + " in disambiguations");
			}

			return disambiguations.get(vd);
		}
	}
	
	public void openEmptyScope() {
		previousScopes.push(scope);
		scope = new CSPModelScope2(scope.getThisModuleVar());
	}
	
	@Override
	protected CSPModelScope createModelScope(VariableDeclaration thisModule) {
		return new CSPModelScope2(thisModule);
	}
	
	public void copyScope() {
		previousScopes.push(scope);
		scope = new CSPModelScope2(scope.getThisModuleVar(), (CSPModelScope2) scope);
	}

	public void closeScope() {
		super.closeScope();
	}


	public OclExpression gen2(OclExpression expr, VariableDeclaration vd) {
		return generator.gen(expr, ((CSPModelScope2) scope).getDisambiguation(vd));
	}

	public static class LazyRuleToParameter extends LazyRuleCallTransformationStrategy {
		@Override
		public OclExpression gen(ATLModel model, OperationCallExp opCall, Function<OclExpression, OclExpression> generator) {
			if ( opCall.getArguments().size() > 1 )  
				throw new UnsupportedTranslation("Cannot translate lazy rule calls with size(args) > 1", opCall);
			
			OclExpression sourceElem = opCall.getArguments().get(0);
			return generator.apply(sourceElem);			
		}
	}
	
}
