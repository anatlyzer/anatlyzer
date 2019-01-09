package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.BindingProblem;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ExpressionJoiner;
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

	protected boolean isSourceType(IMarker marker) {
		setErrorMarker(marker);
		BindingResolution problem;
		try {
			problem = (BindingResolution) getProblem();
			return ATLUtils.getSourceMetamodelNames(getAnalysisResult().getATLModel()).contains(problem.getRight().getMetamodelName());
		//} catch (CoreException e) {
		} catch (Exception e) {
			e.printStackTrace();
			// Normally we can apply the binding safely. If we cannot check just be dare...
			return true;
		}
	}
	
	protected QuickfixApplication generateRuleFilter(Binding b, List<MatchedRule> involvedRules, boolean selectResolvedElements) {
		// boolean selectResolvedElements = false;
		
		MatchedRule r = (MatchedRule) ATLUtils.getRule(b);
		OclExpression filter = r.getInPattern().getFilter();
		
		// Only one rule, and no filter (for the moment) => optimize!
		if ( involvedRules.size() == 1 && involvedRules.get(0).getInPattern().getFilter() == null ) {
			return generateRuleFilter_Optimized(b, involvedRules.get(0), selectResolvedElements);
		}
		
		// Normal case		
		QuickfixApplication qfa = new QuickfixApplication(this);

		Supplier<LetExp> genLet = () -> {
			OclExpression expr = (OclExpression) ATLCopier.copySingleElement(b.getValue());

			LetExp let = OCLFactory.eINSTANCE.createLetExp();
			VariableDeclaration varDcl = OCLFactory.eINSTANCE.createVariableDeclaration();
			varDcl.setVarName("_v");
			
			// It is better to be conservative and use the no casted type if possible because
			// we don't know the types of the resolving rules
			// varDcl.setType(ATLUtils.getOclType(b.getValue().getInferredType()));
			if ( b.getValue().getNoCastedType() != null ) {
				varDcl.setInferredType(b.getValue().getNoCastedType());
				varDcl.setType(ATLUtils.getOclType(b.getValue().getNoCastedType()));
			} else {
				varDcl.setInferredType(b.getValue().getInferredType());
				varDcl.setType(ATLUtils.getOclType(b.getValue().getInferredType()));
			}
			
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
	 * @param selectResolvedElements 
	 */
	protected QuickfixApplication generateRuleFilter_Optimized(Binding b, MatchedRule resolvedRule, boolean selectResolvedElements) {
		MatchedRule r = (MatchedRule) ATLUtils.getRule(b);
		OclExpression filter = r.getInPattern().getFilter();
		Metaclass type = ATLUtils.getInPatternType(resolvedRule);
		
		QuickfixApplication qfa = new QuickfixApplication(this);

		
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
				if ( ! selectResolvedElements ) { 
					andOp.setSource(ASTUtils.negate(kindOf)); 
				} else {
					andOp.setSource(kindOf); 					
				}
				
				andOp.getArguments().add(original);
			});
		} else {
			qfa.putIn(r.getInPattern(), ATLPackage.eINSTANCE.getInPattern_Filter(), () -> {
				OclExpression expr = (OclExpression) ATLCopier.copySingleElement(b.getValue());
				OperationCallExp kindOf = ASTUtils.createOclIsKindOf(type, expr);
				if ( ! selectResolvedElements ) { 
					return ASTUtils.negate(kindOf);
				} else {
					return kindOf;
				}
			});
		}

		return qfa;
	}

	protected QuickfixApplication generateBindingFilter(Binding b, List<MatchedRule> involvedRules, boolean selectResolvedElements) {

		QuickfixApplication qa = new QuickfixApplication(this);
		
		if ( b.getValue().getInferredType() instanceof CollectionType ) {
			qa.change(b.getValue(), OCLFactory.eINSTANCE::createIteratorExp, (original, select, trace) -> {
				if ( selectResolvedElements )
					select.setName("select");
				else
					select.setName("reject");
				
				Iterator it = OCLFactory.eINSTANCE.createIterator();
				it.setInferredType(original.getInferredType());
				it.setVarName(ASTUtils.getNiceIteratorName(b, original.getInferredType()));
				select.getIterators().add(it);
				select.setSource(original);
				
				OclExpression check = genCheck(it, involvedRules, true); // ASTUtils.createBooleanLiteral(true); //
				select.setBody(check);
			});	

		} else {
			qa.change(b.getValue(), OCLFactory.eINSTANCE::createLetExp, (original, let, trace) -> {
				VariableDeclaration varDcl = OCLFactory.eINSTANCE.createVariableDeclaration();
				varDcl.setVarName("_v");
				varDcl.setInferredType(original.getInferredType());
				varDcl.setType(ATLUtils.getOclType(original.getInferredType()));
				varDcl.setInitExpression(original);
				let.setVariable(varDcl);
				
				OclExpression defaultValueExpr = ASTUtils.defaultValue(original.getInferredType());
				VariableExp refToVar           = OCLFactory.eINSTANCE.createVariableExp();
				refToVar.setReferredVariable(varDcl);
				refToVar.setInferredType(varDcl.getInferredType());
				
				OclExpression check = genCheck(varDcl, involvedRules, selectResolvedElements);
				/*
				if ( ! selectResolvedElements ) {
					OperatorCallExp negate = OCLFactory.eINSTANCE.createOperatorCallExp();
					negate.setSource(check);
					negate.setOperationName("not");
					
					check = negate;
				}
				*/
				
				IfExp ifExp = OCLFactory.eINSTANCE.createIfExp();
				ifExp.setCondition(check);
				ifExp.setThenExpression(refToVar);
				ifExp.setElseExpression(defaultValueExpr);
				let.setIn_(ifExp);
			});	
		}		
		return qa;
	}
	
	/**
	 * In addition to genarate a check expression, tries to simplify it.
	 */
	protected OclExpression genCheck(VariableDeclaration bindingValueVar, List<MatchedRule> involvedRules, boolean selectResolvedElements) {
		OclExpression expr = genCheckNormal(bindingValueVar, involvedRules, selectResolvedElements);
		return ASTUtils.simplify(expr, bindingValueVar);
	}
	
	protected OclExpression genCheckNormal(VariableDeclaration bindingValueVar, List<MatchedRule> involvedRules, boolean selectResolvedElements) {
		final Map<EClass, List<MatchedRule>> rulesByType = 
			involvedRules.stream().
			collect(Collectors.groupingBy(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType)); 
	
		final List<EClass> orderedTypes = involvedRules.stream().
			map(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType).
			distinct().sorted((c1, c2) -> c1.isSuperTypeOf(c2) ? +1 : -1).
			collect(Collectors.toList());
		
		Function<EClass, OclExpression> genCondition = (EClass eClass) -> {
			List<MatchedRule> rules = rulesByType.get(eClass);
			Metaclass srcType = ATLUtils.getInPatternType(rules.get(0));
			
			OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
			op.setOperationName("oclIsKindOf");				
			op.getArguments().add( ATLUtils.getOclType( srcType ) );	
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(bindingValueVar);
			varRef.setInferredType(TypeUtils.getUnderlyingType(bindingValueVar.getInferredType()));
			op.setSource(varRef);
			return op;
		};
		
		if ( orderedTypes.size() == 1 && involvedRules.stream().allMatch(r -> r.getInPattern().getFilter() == null) ) {
			OclExpression exp = genCondition.apply(orderedTypes.get(0));
			if ( selectResolvedElements == false ) {
				exp = ASTUtils.negate(exp);
			}
			return exp;
		}

		return ASTUtils.generateNestedIfs(orderedTypes,
				// genCondition: And-concatenation of oclIsKind()
				genCondition
				,
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
						thenBranch = ASTUtils.createBooleanLiteral(selectResolvedElements ? true : false);
					} else if ( ! selectResolvedElements ) {
						OperatorCallExp or = OCLFactory.eINSTANCE.createOperatorCallExp();
						or.setOperationName("not");
						or.setSource(thenBranch);
						thenBranch = or;
					}
					return thenBranch;
				},					
				// genFinalElse: false
				() -> {
					return ASTUtils.createBooleanLiteral(selectResolvedElements ? false : true);
				});
	}	

	protected QuickfixApplication removeBinding(Binding b) {
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.remove(b);
		return qfa;
	}
	
	protected boolean isOptionalBinding(BindingProblem problem) {
		Binding b = (Binding) problem.getElement();

		// A small issue is that we can write twice a binding, so if it already written
		// we can safely remove this one
		boolean existsSimilar = b.getOutPatternElement().getBindings().stream().
			filter(existing -> existing != b).
			anyMatch(existing -> b.getWrittenFeature() == existing.getWrittenFeature());
		
		return 	b.getWrittenFeature() != null &&
				( existsSimilar ||
				  ((EStructuralFeature) b.getWrittenFeature()).getLowerBound() == 0);
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
