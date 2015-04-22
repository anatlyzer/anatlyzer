package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.ExpressionJoiner;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public abstract class BindingProblemQuickFix  extends AbstractAtlQuickfix  {

	public QuickfixApplication generateBindingFilter(Binding b, List<MatchedRule> involvedRules) {
		return generateBindingFilter(b, involvedRules, true);
	}

	public QuickfixApplication generateRuleFilter(Binding b, List<MatchedRule> involvedRules) {
		boolean selectResolvedElements = false;
		
		MatchedRule r = (MatchedRule) ATLUtils.getRule(b);
		OclExpression filter = r.getInPattern().getFilter();
		
		// Only one rule, and no filter (for the moment) => optimize!
		if ( involvedRules.size() == 1 && involvedRules.get(0).getInPattern().getFilter() == null ) {
			return generateRuleFilter_Optimized(b, involvedRules.get(0));
		}
		
		// Normal case		
		QuickfixApplication qfa = new QuickfixApplication();

		Supplier<LetExp> genLet = () -> {
			OclExpression expr = (OclExpression) ATLCopier.copySingleElement(b.getValue());

			LetExp let = OCLFactory.eINSTANCE.createLetExp();
			VariableDeclaration varDcl = OCLFactory.eINSTANCE.createIterator();
			varDcl.setVarName("_v");
			varDcl.setType(ATLUtils.getOclType(b.getValue().getInferredType()));
			varDcl.setInitExpression(expr);
			let.setVariable(varDcl);
			return let;
		};
		
		if ( filter != null ) {			
			qfa.change(filter, OCLFactory.eINSTANCE::createOperatorCallExp, (original, andOp, trace) -> {
				LetExp let = genLet.get();
				let.setIn_(genCheck(let.getVariable(), involvedRules, selectResolvedElements));
				OclExpression condition = let;
				
				andOp.setOperationName("and");
				andOp.setSource(condition);
				
				andOp.getArguments().add(filter);
			});
		} else {
			qfa.putIn(r.getInPattern(), ATLPackage.eINSTANCE.getInPattern_Filter(), () -> {
				LetExp let = genLet.get();
				let.setIn_(genCheck(let.getVariable(), involvedRules, selectResolvedElements));
				return let;
			});
		}
		
		return qfa;
	}
	

	/** 
	 * This optimization only considers the case that resolvedRule does not have a filter.
	 * When there is a filter things are a bit more complicated because references to src object of the 
	 * resolved rule has to be changed for the whole binding expression, as many times as they appear...
	 */
	private QuickfixApplication generateRuleFilter_Optimized(Binding b, MatchedRule resolvedRule) {
		MatchedRule r = (MatchedRule) ATLUtils.getRule(b);
		OclExpression filter = r.getInPattern().getFilter();
		Metaclass type = ATLUtils.getInPatternType(resolvedRule);
		
		QuickfixApplication qfa = new QuickfixApplication();

		
		if ( filter != null ) {			
			qfa.change(filter, OCLFactory.eINSTANCE::createOperatorCallExp, (original, andOp, trace) -> {
				OclExpression expr = (OclExpression) ATLCopier.copySingleElement(b.getValue());
				OperationCallExp kindOf = ASTUtils.createOclIsKindOf(type, expr);

				/*
				OperatorCallExp impliesOp = OCLFactory.eINSTANCE.createOperatorCallExp();
				impliesOp.setSource(kindOf);
				impliesOp.getArguments().add();
				*/
				
				andOp.setOperationName("and");
				andOp.setSource(ASTUtils.negate(kindOf)); // impliesOp
				
				andOp.getArguments().add(original);
			});
		} else {
			qfa.putIn(r.getInPattern(), ATLPackage.eINSTANCE.getInPattern_Filter(), () -> {
				OclExpression expr = (OclExpression) ATLCopier.copySingleElement(b.getValue());
				OperationCallExp kindOf = ASTUtils.createOclIsKindOf(type, expr);
				return ASTUtils.negate(kindOf);
			});
		}

		return qfa;
	}

	public QuickfixApplication generateBindingFilter(Binding b, List<MatchedRule> involvedRules, boolean selectResolvedElements) {

		QuickfixApplication qa = new QuickfixApplication();
		
		if ( b.getValue().getInferredType() instanceof CollectionType ) {
			qa.change(b.getValue(), OCLFactory.eINSTANCE::createIteratorExp, (original, select, trace) -> {
				if ( selectResolvedElements )
					select.setName("select");
				else
					select.setName("reject");
				
				Iterator it = OCLFactory.eINSTANCE.createIterator();
				it.setVarName("_v");
				select.getIterators().add(it);
				select.setSource(original);
				
				OclExpression check = genCheck(it, involvedRules); // ASTUtils.createBooleanLiteral(true); //
				select.setBody(check);
			});	

		} else {
			qa.change(b.getValue(), OCLFactory.eINSTANCE::createLetExp, (original, let, trace) -> {
				VariableDeclaration varDcl = OCLFactory.eINSTANCE.createIterator();
				varDcl.setVarName("_v");
				varDcl.setType(ATLUtils.getOclType(original.getInferredType()));
				varDcl.setInitExpression(original);
				let.setVariable(varDcl);
				
				OclExpression defaultValueExpr = ASTUtils.defaultValue(original.getInferredType());
				VariableExp refToVar           = OCLFactory.eINSTANCE.createVariableExp();
				refToVar.setReferredVariable(varDcl);
				
				OclExpression check = genCheck(varDcl, involvedRules);
				if ( ! selectResolvedElements ) {
					OperatorCallExp negate = OCLFactory.eINSTANCE.createOperatorCallExp();
					negate.setSource(check);
					negate.setOperationName("not");
					
					check = negate;
				}
				
				IfExp ifExp = OCLFactory.eINSTANCE.createIfExp();
				ifExp.setCondition(check);
				ifExp.setThenExpression(refToVar);
				ifExp.setElseExpression(defaultValueExpr);
				let.setIn_(ifExp);
			});	
		}		
		return qa;
	}

	protected OclExpression genCheck(VariableDeclaration bindingValueVar, List<MatchedRule> involvedRules) {
		return genCheck(bindingValueVar, involvedRules, false);
	}
	
	protected OclExpression genCheck(VariableDeclaration bindingValueVar, List<MatchedRule> involvedRules, boolean selectResolvedElements) {
		final Map<EClass, List<MatchedRule>> rulesByType = 
			involvedRules.stream().
			collect(Collectors.groupingBy(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType)); 
	
		final List<EClass> orderedTypes = involvedRules.stream().
			map(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType).
			distinct().sorted((c1, c2) -> c1.isSuperTypeOf(c2) ? +1 : -1).
			collect(Collectors.toList());

		return ASTUtils.generateNestedIfs(orderedTypes, ! selectResolvedElements,
				// genCondition: And-concatenation of oclIsKind()
				(EClass eClass) -> {
					List<MatchedRule> rules = rulesByType.get(eClass);
					Metaclass srcType = ATLUtils.getInPatternType(rules.get(0));
					
					OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
					op.setOperationName("oclIsKindOf");				
					op.getArguments().add( ATLUtils.getOclType( srcType ) );	
					VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
					varRef.setReferredVariable(bindingValueVar);
					op.setSource(varRef);
					return op;
				},
				// genThen: OR-concatenation of all filters
				(EClass eClass) -> {
					List<MatchedRule> rules = rulesByType.get(eClass);
					OclExpression thenBranch = rules.stream().
							map(r -> copyFilter(bindingValueVar, r) ).
							filter(f -> f != null ).
							collect(ExpressionJoiner.joining(() -> {
								OperatorCallExp or = OCLFactory.eINSTANCE.createOperatorCallExp();
								or.setOperationName("or");
								return or;							
							}));
					if ( thenBranch == null ) {
						thenBranch = ASTUtils.createBooleanLiteral(true);
					}
					return thenBranch;
				},					
				// genFinalElse: false
				() -> {
					return ASTUtils.createBooleanLiteral(false);
				});
	}	

	protected OclExpression copyFilter(VariableDeclaration var, MatchedRule r) {
		OclExpression filter = r.getInPattern().getFilter();
		if ( filter == null )
			return null;
		
		return (OclExpression) 
				new ATLCopier(filter).
					bind(r.getInPattern().getElements().get(0), var).
					copy();		
	}	
}
