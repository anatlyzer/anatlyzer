package anatlyzer.atl.graph;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.internal.app.EclipseScheduledApplication.TriggerGuard;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atl.util.TriFunction;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class MatchedRuleExecution extends MatchedRuleBase implements ExecutionNode {

	private EObject problematicElement;

	public MatchedRuleExecution(MatchedRule atlRule, EObject problematicElement) {
		super(atlRule);
		this.problematicElement = problematicElement;
	}
	
	public MatchedRuleExecution(MatchedRule atlRule) {
		this(atlRule, null);
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
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		return genPathCondition(model, (m) -> getDepending().genCSP(m, this));
	}

	protected OclExpression genRuleIteration(CSPModel model, String iteratorName, TriFunction<IteratorExp, IteratorExp, HashMap<String, VariableDeclaration>, OclExpression> generator) {
		Metaclass[] patternTypes = ATLUtils.getAllPatternTypes(rule);

		IteratorExp exists = null;
		IteratorExp existsOuter = null;

		HashMap<String, VariableDeclaration> mappedVars = new HashMap<String, VariableDeclaration>(); 
		
		// T::allInstances->exists(t | <? : allInstancesBody> )
		for(int i = 0; i < patternTypes.length; i++) {
			Metaclass metaclass = patternTypes[i];
			VariableDeclaration varDcl = rule.getInPattern().getElements().get(i);

			OperationCallExp allInstancesCall = model.createAllInstances(metaclass);
			IteratorExp existsInner = model.createIterator(allInstancesCall, iteratorName, varDcl.getVarName());
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
		
		return generator.apply(existsOuter, exists, mappedVars);
	}
	
	protected OclExpression genPathCondition(CSPModel model, Function<CSPModel, OclExpression> generator) {
		return genRuleIteration(model, "exists", (existsOuter, exists, mappedVars) -> {
		
			Pair<LetExp, LetExp> letPair = genLocalVarsLet(model);
			
			LetExp letUsingDeclarations = letPair._1;
			LetExp letUsingDeclarationInnerLet = letPair._2;
			
			if ( letUsingDeclarations != null ) {			
				exists.setBody(letUsingDeclarations);			
			}
	
			boolean isProblemWithinFilter = isProblemWithFilter();
			
			if ( !isProblemWithinFilter && rule.getInPattern().getFilter() != null ) {
				// => if ( filterCondition ) then <? : whenFilter> else false endif
				OclExpression condition = this.getConstraint().genCSP(model, this);
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
		});
	}

	public static void mapSuperRuleVariables(HashMap<String, VariableDeclaration> mappedVars, MatchedRule superRule, CSPModel model) {
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


//	/**
//	 * This version reuses the generated path condition  
//	 */
//	@Override
//	public OclExpression genWeakestPrecondition(CSPModel model) {
//		Metaclass[] patternTypes = ATLUtils.getAllPatternTypes(rule);
//		if ( patternTypes.length > 1 ) {
//			throw new UnsupportedOperationException();
//		}
//		
//		OperationCallExp allInstancesCall = model.createAllInstances(patternTypes[0]);
//		CollectionOperationCallExp isEmpty = model.createCollectionOperationCall(allInstancesCall, "isEmpty");
//		
//		OclExpression rest = genPathCondition(model, (m) -> getDepending().genWeakestPrecondition(m));
//		
//		return model.createBinaryOperator(isEmpty, model.negateExpression(rest), "or");
//	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) { 
		return genWeakestPrecondition(model, (m) -> getDepending().genWeakestPrecondition(m));
	}
	
	protected OclExpression genWeakestPrecondition(CSPModel model, Function<CSPModel, OclExpression> generator) {
		return genRuleIteration(model, "forAll", (forallOuter, forallInner, mappedVars) -> {

			// Sames as in genPathCondition
			Pair<LetExp, LetExp> letPair = genLocalVarsLet(model, true);
			
			LetExp letUsingDeclarations = letPair._1;
			LetExp letUsingDeclarationInnerLet = letPair._2;
			
			if ( letUsingDeclarations != null ) {			
				forallInner.setBody(letUsingDeclarations);			
			}

			boolean isProblemWithinFilter = isProblemWithFilter();
			
			// There are four cases:
			//  - The problem is within the filter, then we just pass to the following node
			//  - Filter but with one input pattern element => Then optimize readability the forAll generating a previous select
			//  - Filter and more than one input pattern    => We need an "if" 			
			//	- No filter => just fill the body
			if ( isProblemWithinFilter ) {
				// Same as when there is no filter 
				
				// set <? : allInstancesBody>
				mapSuperRuleVariables(mappedVars, (MatchedRule) rule.getSuperRule(), model);
				OclExpression whenFilterExpr = generator.apply(model); // getDepending().genCSP(model);
	
				// set <? : forAllBody>
				if ( letUsingDeclarations  == null )
					forallInner.setBody(whenFilterExpr);
				else 
					letUsingDeclarationInnerLet.setIn_(whenFilterExpr);
				
			} else if ( rule.getInPattern().getFilter() != null && rule.getInPattern().getElements().size() == 1 && rule.getVariables().size() == 0 ) { 
				OclExpression allInstancesPart = forallInner.getSource();
				IteratorExp select = model.createIterator(allInstancesPart, "select", forallInner.getIterators().get(0).getVarName());
				
				model.openEmptyScope();
				model.addToScope(rule.getInPattern().getElements().get(0), select.getIterators().get(0));
				OclExpression condition = this.getConstraint().genCSP(model, this);
				model.closeScope();
				select.setBody(condition);

				forallInner.setSource(select);
				
				OclExpression whenFilterExpr = generator.apply(model); 				
				forallInner.setBody(whenFilterExpr);
			}
			else if ( rule.getInPattern().getFilter() != null ) {
				
				// => if ( filterCondition ) then <? : whenFilter> else false endif
				OclExpression condition = this.getConstraint().genCSP(model, this);
				IfExp ifExp = model.createIfExpression(condition, null, model.createBooleanLiteral(false) );
				
				// set <? : forAllBody>
				if ( letUsingDeclarations == null )
					forallInner.setBody(ifExp);
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
	
				// set <? : forAllBody>
				if ( letUsingDeclarations  == null )
					forallInner.setBody(whenFilterExpr);
				else 
					letUsingDeclarationInnerLet.setIn_(whenFilterExpr);
			}
			
			return forallOuter;
		});
	}

	private boolean isProblemWithFilter() {
		boolean isProblemThroughFilter = false;
		if ( getDepending() instanceof CallExprNode ) {
			PropertyCallExp call = ((CallExprNode) getDepending()).getCall();
			isProblemThroughFilter = EcoreUtil.isAncestor(rule.getInPattern(), call);
		}
		return isProblemThroughFilter;
//		boolean isProblemWithinFilter = problematicElement != null && rule.getInPattern().getFilter() == null ? 
//				false :
//				EcoreUtil.isAncestor(rule.getInPattern().getFilter(), problematicElement);
//		return isProblemWithinFilter;
	}

		
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		slice.addRule(this.rule);
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		throw new IllegalStateException();
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
		visitor.visitAfter(this);
	}

}
