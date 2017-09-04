package anatlyzer.atl.analyser.generators;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.invariants.LazyRulePathVisitor;
import anatlyzer.atl.analyser.generators.CSPModel.CSPModelScope;
import anatlyzer.atl.analyser.generators.OclGeneratorAST.LazyRuleCallTransformationStrategy;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.UnsupportedTranslation;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class CSPModel2 extends CSPModel {

	protected IAnalyserResult result;

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

	public CSPModel2(IAnalyserResult result) {
		this.result = result;
		this.generator = new OclGeneratorAST2(atlModel, result);
		this.generator.setLazyRuleStrategy(new LazyRuleToParameter());
	}
	
	
	public void addToScope(VariableDeclaration varDcl, VariableDeclaration newVar, VariableDeclaration disambiguation) {
		((CSPModelScope2) scope).put(varDcl, newVar, disambiguation);
	}

	public void addToScope(VariableDeclaration varDcl, Supplier<OclExpression> mapping) {
		((CSPModelScope2) scope).put(varDcl, mapping);
	}

// This makes no sense... delete...
//	public VariableDeclaration getInverseVar(VariableDeclaration tgtVar) {
//		ArrayList<VariableDeclaration> results = new ArrayList<VariableDeclaration>();
//		for (Entry<VariableDeclaration, VariableDeclaration> entry : ((CSPModelScope2) scope).entrySet()) {
//			if ( entry.getValue() == tgtVar ) {
//				results.add(entry.getKey());
//			}
//		}		
//		
//		if ( results.size() == 0 ) {
//			throw new IllegalStateException("No binding for target var " + tgtVar);			
//		} else if ( results.size() > 1 ) {
//			throw new IllegalStateException("Too many bindings for target var " + tgtVar);
//		}
//		return results.get(0);
//	}

	
	
	public static class CSPModelScope2 extends CSPModelScope {
		public CSPModelScope2(VariableDeclaration thisModuleVar, CSPModelScope2 cspModelScope2) {
			super(thisModuleVar, cspModelScope2);
			disambiguations.putAll(cspModelScope2.disambiguations);
			exprMapping.putAll(cspModelScope2.exprMapping);
		}

		public CSPModelScope2(VariableDeclaration thisModuleVar) {
			super(thisModuleVar);
		}

		HashMap<VariableDeclaration, CSPModelScope> disambiguations = new HashMap<>();
		HashMap<VariableDeclaration, Supplier<OclExpression>> exprMapping = new HashMap<>();

		@Override
		public CSPModelScope derive() {
			CSPModelScope r = new CSPModelScope2(thisModule, this);
			return r;
		}
		
		public void put(VariableDeclaration varDcl, Supplier<OclExpression> mapping) {
			if ( exprMapping.containsKey(varDcl) ) {
				throw new IllegalStateException("Expression mapping already bound for " + varDcl.getVarName() + " " + varDcl.getLocation() );
			}
			exprMapping.put(varDcl, mapping);
		}

		public OclExpression getExprOrVarExp(VariableDeclaration varDcl) {
			if ( exprMapping.containsKey(varDcl) ) {
				return exprMapping.get(varDcl).get();
			}
			
			VariableDeclaration newVar = getVar(varDcl);
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(newVar);
			return varRef;
		}
		
		public void put(VariableDeclaration varDcl, VariableDeclaration newVar, VariableDeclaration disambiguation) {			
			this.put(varDcl, newVar);

// Things used to work with this bug... the equivalent code ommiting to the bugged code is as follows:
			// Essentially, we allow rebindings for disambiguations
			disambiguations.put(disambiguation,  new CSPModelScope2(this.getThisModuleVar(), this));
			
// Original code:
//			// TODO: This is a bug!!!
//			CSPModelScope savedScope = disambiguations.get(disambiguations);
//			// CSPModelScope savedScope = disambiguations.get(disambiguation);
//			if ( savedScope != null && savedScope != this ) {
//				throw new IllegalStateException("Rebound disambiguation: " + disambiguation.getVarName() + " : " + disambiguation.getLocation());
//			}
//			
//			// If the scope is already saved (savedScope == this), the we allow the rebound. 
//			// This is to handle the case: T.allInstances(), and then "rule (from x:X, y:Y) (to t:T)
//			// because the could want to call "put" twice, once for x and once for y for the same disambiguation variable.
//			disambiguations.put(disambiguation,  new CSPModelScope2(this.getThisModuleVar(), this));
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
			if ( opCall.getArguments().size() > 1 ) {
				// See LazyRulePathVisitor, which implements a similar strategy
				// throw new UnsupportedTranslation("Cannot translate lazy rule calls with size(args) > 1", opCall);
			
				// A tuple is generated to gather each parameter
				TupleExp tuple = OCLFactory.eINSTANCE.createTupleExp();
				LazyRule lazy = (LazyRule) opCall.getStaticResolver();
				
				for (int i = 0; i < lazy.getInPattern().getElements().size(); i++) {
					InPatternElement e = lazy.getInPattern().getElements().get(i);
					OclExpression arg  = opCall.getArguments().get(i);

					TuplePart part = OCLFactory.eINSTANCE.createTuplePart();
					part.setVarName(e.getVarName());
					part.setInitExpression( generator.apply(arg) );
					
					tuple.getTuplePart().add(part);
				}
				
				tuple.getAnnotations().put(LazyRulePathVisitor.TUPLE_FOR_LAZY_CALL, lazy.getName());
				
				return tuple;
			} else {
				OclExpression sourceElem = opCall.getArguments().get(0);
				return generator.apply(sourceElem);			
			}
		}
	}


	
}
