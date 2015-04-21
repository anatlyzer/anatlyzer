package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
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
	
	public QuickfixApplication generateBindingFilter(Binding b, List<MatchedRule> involvedRules, boolean trueAvoidError) {
		final Map<EClass, List<MatchedRule>> rulesByType = 
				involvedRules.stream().
				collect(Collectors.groupingBy(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType)); 
		
		final List<EClass> orderedTypes = involvedRules.stream().
				map(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType).
				distinct().sorted((c1, c2) -> c1.isSuperTypeOf(c2) ? +1 : -1).
				collect(Collectors.toList());

		Function<VariableDeclaration, OclExpression> genCheck = (bindingValueVar) -> {
			return ASTUtils.generateNestedIfs(orderedTypes, 
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
			};		

		QuickfixApplication qa = new QuickfixApplication();
		
		if ( b.getValue().getInferredType() instanceof CollectionType ) {
			qa.change(b.getValue(), OCLFactory.eINSTANCE::createIteratorExp, (original, select, trace) -> {
				if ( trueAvoidError )
					select.setName("select");
				else
					select.setName("reject");
				
				Iterator it = OCLFactory.eINSTANCE.createIterator();
				it.setVarName("_v");
				select.getIterators().add(it);
				select.setSource(original);
				
				OclExpression check = genCheck.apply(it); // ASTUtils.createBooleanLiteral(true); //
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
				
				OclExpression check = genCheck.apply(varDcl);
				if ( ! trueAvoidError ) {
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
