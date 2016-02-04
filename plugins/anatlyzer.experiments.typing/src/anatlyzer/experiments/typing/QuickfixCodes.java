package anatlyzer.experiments.typing;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddIf;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_SpecificPrecondition;
import anatlyzer.atl.editor.quickfix.errors.BindingExpectedOneAssignedMany_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.BindingExpectedOneAssignedMany_SelectFirst;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_ModifiyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_Precondition;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_Remove;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_RemoveRule;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_SpecificPrecondition;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_AddRule;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_ModifiyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_Precondition;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_Remove;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_SpecificPrecondition;
import anatlyzer.atl.editor.quickfix.errors.CollectionOperationNotFoundQuickfix;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddIfToExpression;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_SpecificPrecondition;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickFix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickFix_FindSameOperation;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_FindSameOperation;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.IncoherentDeclaredTypeQuickfix_AssignInferredType;
import anatlyzer.atl.editor.quickfix.errors.IncoherentHelperReturnTypeQuickfix_AssignInferredType;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_AddBinding;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarExpression;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarFeature;
import anatlyzer.atl.editor.quickfix.errors.NoClassFoundInMetamodelQuickFix_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoClassFoundInMetamodelQuickFix_FindSimilar;
import anatlyzer.atl.editor.quickfix.errors.NoEnumLiteral_FindSimilar;
import anatlyzer.atl.editor.quickfix.errors.NoModelFoundQuickfix_ChooseExistingOne;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_AddRule;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_RemoveBinding;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBinding_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBinding_Precondition;
import anatlyzer.atl.editor.quickfix.errors.ObjectBindingButPrimitiveAssignedQuickfix_changeBindingVariable;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_AddArguments;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_RemoveFormalParameters;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidParameterQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToExpression;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_ChangeOperationContext;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_SpecificPrecondition;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.PrimitiveBindingInvalidAssignment_Quickfix;
import anatlyzer.atl.editor.quickfix.errors.RuleConflictQuickfix_ModifyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.RuleConflictQuickfix_RemoveRule;
import anatlyzer.atl.editor.quickfix.warnings.CollectionOperationOverNoCollectionQuickfix;
import anatlyzer.atl.editor.quickfix.warnings.FlattenOverNonNestedCollectionQuickFix;
import anatlyzer.atl.editor.quickfix.warnings.OperationOverCollectionTypeQuickfix;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.FeatureAccessInCollection;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.InvalidArgument;
import anatlyzer.atl.errors.atl_error.InvalidOperand;
import anatlyzer.atl.errors.atl_error.InvalidOperator;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.errors.atl_error.NoEnumLiteral;
import anatlyzer.atl.errors.atl_error.NoModelFound;
import anatlyzer.atl.errors.atl_error.ObjectBindingButPrimitiveAssigned;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingButObjectAssigned;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.util.AnalyserUtils;

public class QuickfixCodes {
	
	public static class QfxCode {
		public final String code;
		private Class<?> clazz;
		public QfxCode(Class<?> clazz, String code) { this.clazz = clazz; this.code = code; }
		public static QfxCode c(Class<?> clazz, String code) {
			return new QfxCode(clazz, code); 
		}
	}
	
	
	private static List<QfxCode> codes = null;
	public static List<QfxCode> getQfxCodes() {
		if ( codes != null )
			return codes;
		
		codes = new ArrayList<QfxCode>();

		// Q0.1
		codes.add( QfxCode.c(RuleConflictQuickfix_ModifyRuleFilter.class, 				"Q0.1")  );
		// Q0.2 -> requires user intervention
		codes.add( QfxCode.c(RuleConflictQuickfix_RemoveRule.class, 					"Q0.2")  );
		
		// Q1.1
		codes.add( QfxCode.c(BindingPossiblyUnresolved_ModifiyRuleFilter.class, 		"Q1.1")  );
		codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_ModifiyRuleFilter.class,"Q1.1")  );

		// Q1.2
		codes.add( QfxCode.c(BindingPossiblyUnresolved_Remove.class, 					"Q1.2")  );
		codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_Remove.class, 			"Q1.2")  );
		codes.add( QfxCode.c(NoRuleForBindingQuickfix_RemoveBinding.class, 				"Q1.2")  );

		// Q1.3
		codes.add( QfxCode.c(BindingPossiblyUnresolved_FilterBinding.class, 			"Q1.3")  );
		codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_FilterBinding.class, 	"Q1.3")  );
		codes.add( QfxCode.c(NoRuleForBinding_FilterBinding.class, 						"Q1.3")  );
		
		// Q1.4
		codes.add( QfxCode.c(BindingPossiblyUnresolved_SpecificPrecondition.class, 		    "Q1.4")  );
		codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_SpecificPrecondition.class, "Q1.4")  );

		// Q1.5
		codes.add( QfxCode.c(BindingPossiblyUnresolved_Precondition.class, 				"Q1.5")  );
		codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_Precondition.class, 	"Q1.5")  );
		codes.add( QfxCode.c(NoRuleForBinding_Precondition.class,        				"Q1.5")  );
		
		// Q2.1
		codes.add( QfxCode.c(BindingPossiblyUnresolved_AddRule.class, 					"Q2.1")  );
		codes.add( QfxCode.c(NoRuleForBindingQuickfix_AddRule.class, 					"Q2.1")  );
		
		// Q3.1
		codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_RemoveRule.class, 		"Q3.1")  );

		// Q3.2 --> No esta todavia...
		// codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_ChangeTargetType.class,	"Q3.2")  );

		// Q4.1
		codes.add( QfxCode.c(NoBindingForCompulsoryFeature_ChangeMetamodel.class, 		"Q4.1")  );
		codes.add( QfxCode.c(BindingExpectedOneAssignedMany_ChangeMetamodel.class, 		"Q4.1")  );

		// Q4.2
		// Not in the table...
		// codes.add( QfxCode.c(PrimitiveBindingInvalidAssignment_Quickfix.class, 		"Q4.2")  );

		// Q5.x
		codes.add( QfxCode.c(NoBindingForCompulsoryFeature_AddBinding.class, 			"Q5.2")  );
		codes.add( QfxCode.c(NoBindingForCompulsoryFeature_FindSimilarExpression.class, "Q5.2")  );
		codes.add( QfxCode.c(NoBindingForCompulsoryFeature_FindSimilarFeature.class, 	"Q5.3")  );
		
		// Q6.1
		codes.add( QfxCode.c(BindingExpectedOneAssignedMany_SelectFirst.class, 	"Q6.1")  );

		// Q7.1
		codes.add( QfxCode.c(NoClassFoundInMetamodelQuickFix_FindSimilar.class, 	"Q7.1")  );
		codes.add( QfxCode.c(NoModelFoundQuickfix_ChooseExistingOne.class, 			"Q7.1")  ); // in some way it is similar to 7.1
		codes.add( QfxCode.c(NoEnumLiteral_FindSimilar.class, 						"Q7.1")  ); 
		
		
		// Q7.2
		codes.add( QfxCode.c(NoClassFoundInMetamodelQuickFix_ChangeMetamodel.class, 		"Q7.2")  ); 
		
		// Q8.1
		codes.add( QfxCode.c(IncoherentHelperReturnTypeQuickfix_AssignInferredType.class, 	"Q8.1")  ); 
		codes.add( QfxCode.c(IncoherentDeclaredTypeQuickfix_AssignInferredType.class, 		"Q8.1")  ); 

		// Q9.1
		codes.add( QfxCode.c(AccessToUndefinedValue_AddIf.class, 							"Q9.1")  );
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_AddIfToExpression.class, 		"Q9.1")  );
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_AddIfToBlock.class, 			"Q9.1")  ); // marked as (b) in the original code
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_AddIfToExpression.class, 		"Q9.1")  );
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_AddIfToBlock.class, 				"Q9.1")  ); 
		// TODO: Consider features in subtype, 9.1
		// TODO: Consider features in subtype, 9.2, consider outer for access to undefined, 9.2

		// Q9.2
		codes.add( QfxCode.c(AccessToUndefinedValue_AddRuleFilter.class, 				"Q9.2")  ); 
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_AddRuleFilter.class,		"Q9.2")  ); 
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_AddRuleFilter.class,			"Q9.2")  ); 
		
		// Q9.3
		codes.add( QfxCode.c(AccessToUndefinedValue_SpecificPrecondition.class,				"Q9.3")  ); 
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_SpecificPrecondition.class,	"Q9.3")  ); 
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_SpecificPrecondition.class,		"Q9.3")  ); 
		
		// Q10.1
		codes.add( QfxCode.c(AccessToUndefinedValue_ChangeMetamodel.class,					"Q9.3")  ); 
		
		// Q11.1
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_CreateHelper.class, 			"Q11.1")  ); 
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_CreateHelper.class, 				"Q11.1")  ); 
		
		// Q11.2 --> Not in the table
		// codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_ChangeOperationContext.class, 	"Q11.2")  ); 

		// Q12.1
		codes.add( QfxCode.c(OperationNotFoundInThisModuleQuickfix_ChooseExisting.class, 	"Q12.1")  ); 
		codes.add( QfxCode.c(OperationNotFoundQuickfix_ChooseExisting.class,			 	"Q12.1")  ); 
		codes.add( QfxCode.c(FeatureNotFoundQuickFix_ChooseExisting.class,				 	"Q12.1")  ); 
		codes.add( QfxCode.c(FeatureNotFoundInThisModuleQuickFix_ChooseExisting.class,	 	"Q12.1")  ); 
		codes.add( QfxCode.c(CollectionOperationNotFoundQuickfix.class,	 					"Q12.1")  ); 

		// Q12.2
		codes.add( QfxCode.c(OperationNotFoundInThisModuleQuickfix_CreateHelper.class, 	"Q12.2")  ); 
		codes.add( QfxCode.c(OperationNotFoundQuickfix_CreateHelper.class,			 	"Q12.2")  ); 
		codes.add( QfxCode.c(FeatureNotFoundInThisModuleQuickfix_CreateHelper.class,	"Q12.2")  ); 
		codes.add( QfxCode.c(FeatureNotFoundQuickfix_CreateHelper.class,	 			"Q12.2")  ); 

		// Q12.3
		codes.add( QfxCode.c(FeatureNotFoundQuickFix_ChangeMetamodel.class,	 			"Q12.3")  ); 

		// Q12.4
		codes.add( QfxCode.c(OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall.class,	"Q12.4")  ); 
		codes.add( QfxCode.c(OperationNotFoundQuickfix_ChangeToFeatureCall.class,	"Q12.4")  ); 
		codes.add( QfxCode.c(FeatureNotFoundQuickFix_FindSameOperation.class,	"Q12.4")  ); 
		codes.add( QfxCode.c(FeatureNotFoundInThisModuleQuickFix_FindSameOperation.class,	"Q12.4")  ); 
		
		// Q15.x
		codes.add( QfxCode.c(OperationCallInvalidParameterQuickfix_CreateHelper.class,	"Q15.1")  ); 
		codes.add( QfxCode.c(OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition.class, "Q15.2")  ); 
		
		// Q16.x
		codes.add( QfxCode.c(OperationCallInvalidNumberOfParametersQuickfix_AddArguments.class,  	"Q16.1")  ); 
		codes.add( QfxCode.c(OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments.class,  "Q16.1")  );
		codes.add( QfxCode.c(OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters.class, 	 "Q16.2")  ); 
		codes.add( QfxCode.c(OperationCallInvalidNumberOfParametersQuickfix_RemoveFormalParameters.class, "Q16.2")  );
		codes.add( QfxCode.c(OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation.class,  "Q16.3")  ); 

		// Q17.1
		codes.add( QfxCode.c(ObjectBindingButPrimitiveAssignedQuickfix_changeBindingVariable.class,  "Q17.1")  ); 
		codes.add( QfxCode.c(PrimitiveBindingInvalidAssignment_Quickfix.class, 						 "Q17.1")  ); 
		
		// Q18.x -> style
		codes.add( QfxCode.c(FlattenOverNonNestedCollectionQuickFix.class,		 "Q18.1")  ); 
		codes.add( QfxCode.c(OperationOverCollectionTypeQuickfix.class, 		 "Q18.2")  ); 
		codes.add( QfxCode.c(CollectionOperationOverNoCollectionQuickfix.class,  "Q18.3")  ); 
	
		// Other
		// FeatureAccessInCollection_SelectFirst
		// ResolveTempOutputPatternElementNotFound_QuickFix
		// ResolveTempWithoutRuleQuickFix_CreateRule
		
		return codes;
	}
	
	public static String getCode(AtlProblemQuickfix quickfix) {
		for (QfxCode qfxCode : getQfxCodes()) {
			if ( qfxCode.clazz.isInstance(quickfix) ) {
				return qfxCode.code;
			}
		}
		
		String cname = quickfix.getClass().getSimpleName();
		return cname;
	}
	
	
	private static List<QfxCode> errorCodes = null;	
	public static List<QfxCode> getErrorCodes() {
		if ( errorCodes != null )
			return errorCodes;
		
		ArrayList<QfxCode> codes = new ArrayList<QfxCode>();

		// E00
		codes.add( QfxCode.c(RuleConflict.class, "E00")  );
		
		// E02
		codes.add( QfxCode.c(BindingPossiblyUnresolved.class, "E02")  );
		codes.add( QfxCode.c(BindingWithoutRule.class, "E02")  );
		
		// E03
		codes.add( QfxCode.c(BindingWithResolvedByIncompatibleRule.class, "E03")  );

		// E05
		codes.add( QfxCode.c(NoBindingForCompulsoryFeature.class, "E05")  );
		
		// E06
		codes.add( QfxCode.c(BindingExpectedOneAssignedMany.class, "E06")  );
		
		// E07
		codes.add( QfxCode.c(NoClassFoundInMetamodel.class, "E07")  );
		codes.add( QfxCode.c(NoEnumLiteral.class, "E07")  );
		codes.add( QfxCode.c(NoModelFound.class, "E07")  );
		
		// E08
		codes.add( QfxCode.c(IncoherentVariableDeclaration.class, "E08")  );
		codes.add( QfxCode.c(IncoherentHelperReturnType.class, "E08")  );

		// E10
		codes.add( QfxCode.c(AccessToUndefinedValue.class, "E10")  );

		// E11
		codes.add( QfxCode.c(FeatureFoundInSubtype.class, "E11")  );

		// E12
		codes.add( QfxCode.c(FeatureNotFound.class, "E12")  );

		// E14
		codes.add( QfxCode.c(OperationNotFound.class, "E14")  );
		codes.add( QfxCode.c(OperationNotFoundInThisModule.class, "E14")  );
		codes.add( QfxCode.c(CollectionOperationNotFound.class, "E14")  );
		codes.add( QfxCode.c(InvalidOperator.class, "E14")  );
		// Removed because the quick fix is not considered
		// codes.add( QfxCode.c(FeatureAccessInCollection.class, "E14")  );

		// E15
		codes.add( QfxCode.c(InvalidArgument.class, "E15")  );
		codes.add( QfxCode.c(OperationCallInvalidParameter.class, "E15")  );
		codes.add( QfxCode.c(InvalidOperand.class, "E15")  );
		
		// E16
		codes.add( QfxCode.c(OperationCallInvalidNumberOfParameters.class, "E16")  );
		
		// E17
		codes.add( QfxCode.c(PrimitiveBindingButObjectAssigned.class, "E17")  );
		codes.add( QfxCode.c(ObjectBindingButPrimitiveAssigned.class, "E17")  );
		codes.add( QfxCode.c(PrimitiveBindingInvalidAssignment.class, "E17")  );
		
		codes.add( QfxCode.c(OperationOverCollectionType.class, "E18")  );
		codes.add( QfxCode.c(CollectionOperationOverNoCollectionError.class, "E18")  );
				
		errorCodes = codes;
		return errorCodes;
		
	}
	
	public static String getErrorCode(Problem p) {
		for (QfxCode qfxCode : getErrorCodes()) {
			if ( qfxCode.clazz.isInstance(p) ) {
				return qfxCode.code;
			}
		}
		
		String cname = "X-" + AnalyserUtils.getProblemId(p) + " " + p.getClass().getSimpleName();
		return cname;		
	}
	
	/**
	 * Returns the error code for the problem corresponding to the table of the MODELS'15 paper
	 * 
	 * @param p
	 * @return
	 */
	public static String getErrorCode_old(Problem p) {
		if ( p instanceof RuleConflict ) return "E00";
		if ( p instanceof BindingPossiblyUnresolved || p instanceof BindingWithoutRule ) return "E02";
		if ( p instanceof BindingWithResolvedByIncompatibleRule) return "E03";
		if ( p instanceof NoBindingForCompulsoryFeature ) return "E05";
		if ( p instanceof BindingExpectedOneAssignedMany ) return "E06";
		if ( p instanceof NoClassFoundInMetamodel ) return "E07";
		
//		if ( p instanceof IncoherentVariableDeclaration ) return "E08 (var)";
//		if ( p instanceof IncoherentHelperReturnType ) return "E08 (helper)";
		if ( p instanceof IncoherentVariableDeclaration ) return "E08";
		if ( p instanceof IncoherentHelperReturnType ) return "E08";
		
		if ( p instanceof AccessToUndefinedValue ) return "E10";
		if ( p instanceof FeatureFoundInSubtype ) return "E11";
		if ( p instanceof FeatureNotFound ) return "E12";

//		if ( p instanceof OperationNotFound ) return "E14 (ctx)";
//		if ( p instanceof OperationNotFoundInThisModule ) return "E14 (mod)";
//		if ( p instanceof CollectionOperationNotFound ) return "E14 (col)";
//		if ( p instanceof FeatureAccessInCollection ) return "E14 (col)";
//		if ( p instanceof InvalidOperator ) return "E14 (expr)";

		
		if ( p instanceof OperationNotFound ) return "E14";
		if ( p instanceof OperationNotFoundInThisModule ) return "E14";
		if ( p instanceof CollectionOperationNotFound ) return "E14";
		if ( p instanceof FeatureAccessInCollection ) return "E14";
		if ( p instanceof InvalidOperator ) return "E14";

//		if ( p instanceof OperationNotFound ) return "E12";
//		if ( p instanceof OperationNotFoundInThisModule ) return "E12";
//		if ( p instanceof CollectionOperationNotFound ) return "E12";
//		if ( p instanceof FeatureAccessInCollection ) return "E12";
//		if ( p instanceof InvalidOperator ) return "E12";

		
		if ( p instanceof OperationOverCollectionType ) return "E18"; // 101
		if ( p instanceof CollectionOperationOverNoCollectionError ) return "E18"; // 102
		
		if ( p instanceof PrimitiveBindingButObjectAssigned ) return "E17"; // 18
		if ( p instanceof ObjectBindingButPrimitiveAssigned ) return "E17"; // 19 
		if ( p instanceof PrimitiveBindingInvalidAssignment ) return "E17"; // 20
		
		if ( p instanceof InvalidArgument ) return "E15";
		if ( p instanceof OperationCallInvalidParameter ) return "E15";
		if ( p instanceof OperationCallInvalidNumberOfParameters ) return "E16";
		
		if ( p instanceof InvalidOperand ) return "E15";
		
//		if ( compactNotClassified ) {
//			return "Other";
//		}
					
		return "X-" + AnalyserUtils.getProblemId(p);
	}

	public static String getCode_old(AtlProblemQuickfix quickfix) {
		if ( quickfix instanceof RuleConflictQuickfix_ModifyRuleFilter ) return "Q0.0";

		if ( quickfix instanceof BindingPossiblyUnresolved_ModifiyRuleFilter ||
		     quickfix instanceof BindingInvalidTargetInResolvedRule_ModifiyRuleFilter ) return "Q1.1";
		
		if ( quickfix instanceof BindingPossiblyUnresolved_Remove ||
			 quickfix instanceof BindingInvalidTargetInResolvedRule_Remove ||
			 quickfix instanceof NoRuleForBindingQuickfix_RemoveBinding ) return "Q1.2";

		if ( quickfix instanceof BindingPossiblyUnresolved_FilterBinding ||
			 quickfix instanceof BindingInvalidTargetInResolvedRule_FilterBinding ) return "Q1.3";
			
		if ( quickfix instanceof GeneratePrecondition ) 
			return "Q1.4";
		
		if ( quickfix instanceof BindingPossiblyUnresolved_AddRule ) return "Q2.1";
		if ( quickfix instanceof NoRuleForBindingQuickfix_AddRule ) return "Q2.1";
		
		if ( quickfix instanceof BindingInvalidTargetInResolvedRule_RemoveRule ) return "Q3.1";
		
		if ( quickfix instanceof NoBindingForCompulsoryFeature_ChangeMetamodel ) return "Q4.1";
		if ( quickfix instanceof BindingExpectedOneAssignedMany_ChangeMetamodel ) return "Q4.1";
		// Also for PrimitiveBindingInvalidAssignment, etc.
		
		if ( quickfix instanceof PrimitiveBindingInvalidAssignment_Quickfix ) return "Q4.2";
		
		if ( quickfix instanceof NoBindingForCompulsoryFeature_AddBinding )  return "Q5.1"; // TODO: Is this 4.2?
		if ( quickfix instanceof NoBindingForCompulsoryFeature_FindSimilarExpression ) return "Q5.2";
		if ( quickfix instanceof NoBindingForCompulsoryFeature_FindSimilarFeature ) return "Q5.3";
		
		// TODO: 5.3, suggest a similar feature in the meta-model
		
		// This is not completelely accurate
		if ( quickfix instanceof ObjectBindingButPrimitiveAssignedQuickfix_changeBindingVariable ) return "Q5.3"; 
		
		
		if ( quickfix instanceof BindingExpectedOneAssignedMany_SelectFirst ) return "Q6.1";
		
		if ( quickfix instanceof NoClassFoundInMetamodelQuickFix_FindSimilar ) return "Q7.1";
		if ( quickfix instanceof NoModelFoundQuickfix_ChooseExistingOne ) return "Q7.1"; // in some way it is similar to 7.1
		if ( quickfix instanceof NoEnumLiteral_FindSimilar ) return "Q7.1"; // in some way it is similar to 7.1
		if ( quickfix instanceof NoClassFoundInMetamodelQuickFix_ChangeMetamodel ) return "Q7.2"; 

		if ( quickfix instanceof IncoherentHelperReturnTypeQuickfix_AssignInferredType ) return "Q8.1";
		if ( quickfix instanceof IncoherentDeclaredTypeQuickfix_AssignInferredType ) return "Q8.1";
		
		
		if ( quickfix instanceof AccessToUndefinedValue_AddIf ) return "Q9.1";
		if ( quickfix instanceof OperationFoundInSubtypeQuickfix_AddIfToExpression ) return "Q9.1";
		// TODO: Consider features in subtype, 9.1
		
		if ( quickfix instanceof OperationFoundInSubtypeQuickfix_AddIfToBlock ) return "Q9.1 (b)";
		// TODO: Consider features in subtype, 9.2, consider outer for access to undefined, 9.2

		if ( quickfix instanceof AccessToUndefinedValue_AddRuleFilter ) return "Q9.2";
		// TODO: Consider features in subtype, 9.3, consider rule filter for subyptes 9.3

		if ( quickfix instanceof AccessToUndefinedValue_ChangeMetamodel ) return "Q10.1";
		
		if ( quickfix instanceof OperationFoundInSubtypeQuickfix_CreateHelper) return "Q11.1";
		if ( quickfix instanceof OperationFoundInSubtypeQuickfix_ChangeOperationContext) return "Q11.2";
		
		if ( quickfix instanceof OperationNotFoundInThisModuleQuickfix_ChooseExisting) return "Q12.1";
		if ( quickfix instanceof OperationNotFoundQuickfix_ChooseExisting) return "Q12.1";
		if ( quickfix instanceof FeatureNotFoundQuickFix_ChooseExisting ) return "Q12.1";
		if ( quickfix instanceof FeatureNotFoundInThisModuleQuickFix_ChooseExisting ) return "Q12.1";			
		if ( quickfix instanceof CollectionOperationNotFoundQuickfix ) return "Q12.1";
		// TODO: 12.1 context operations 
					
		if ( quickfix instanceof OperationNotFoundInThisModuleQuickfix_CreateHelper ) return "Q12.2";
		if ( quickfix instanceof OperationNotFoundQuickfix_CreateHelper ) return "Q12.2";
		if ( quickfix instanceof FeatureNotFoundInThisModuleQuickfix_CreateHelper ) return "Q12.2";
		if ( quickfix instanceof FeatureNotFoundQuickfix_CreateHelper ) return "Q12.2";
		
		// TODO: 12.2 context operations 
		
		if ( quickfix instanceof FeatureNotFoundQuickFix_ChangeMetamodel ) return "Q12.3";
		
		if ( quickfix instanceof OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall ) return "Q12.4";
		if ( quickfix instanceof OperationNotFoundQuickfix_ChangeToFeatureCall ) return "Q12.4";
		if ( quickfix instanceof FeatureNotFoundQuickFix_FindSameOperation ) return "Q12.4";
		if ( quickfix instanceof FeatureNotFoundInThisModuleQuickFix_FindSameOperation ) return "Q12.4";

		// TODO: 12.4 context operations 		
		
		
		if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_AddArguments ) return "Q15.1";
		if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments ) return "Q15.1";
		if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters ) return "Q15.2";
		if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_RemoveFormalParameters ) return "Q15.2";
		if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation ) return "Q15.3";
		if ( quickfix instanceof OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition ) return "Q16.1";
		if ( quickfix instanceof OperationCallInvalidParameterQuickfix_CreateHelper ) return "Q16.2";
		
		if ( quickfix instanceof FlattenOverNonNestedCollectionQuickFix ) return "Q18.1";
		if ( quickfix instanceof OperationOverCollectionTypeQuickfix ) return "Q18.1";
		if ( quickfix instanceof CollectionOperationOverNoCollectionQuickfix ) return "Q18.1";
		
		String cname = quickfix.getClass().getSimpleName();
		/*
		int idx = cname.indexOf("_");
		if ( idx >= 0 ) {
			cname = cname.substring(idx + 1);
		}
		
		return cname.replace("_", "").substring(0, 10);
		*/
		return cname;
	}


}