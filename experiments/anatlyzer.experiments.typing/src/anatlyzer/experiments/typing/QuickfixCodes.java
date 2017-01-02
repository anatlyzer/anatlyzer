package anatlyzer.experiments.typing;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddIf;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_SpecificPrecondition;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ThroughEmptyCollection_AddIf;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ThroughEmptyCollection_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ThroughEmptyCollection_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.BindingExpectedOneAssignedMany_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.BindingExpectedOneAssignedMany_SelectFirst;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_ModifiyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_ModifyBindingFeature;
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
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_SpecificPrecondition;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ConvertReceptorToCollection;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.PrimitiveBindingInvalidAssignmentQuickfix_Conversion;
import anatlyzer.atl.editor.quickfix.errors.PrimitiveBindingInvalidAssignmentQuickfix_SetDefaultValue;
import anatlyzer.atl.editor.quickfix.errors.RuleConflictQuickfix_ModifyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.RuleConflictQuickfix_RemoveRule;
import anatlyzer.atl.editor.quickfix.warnings.CollectionOperationOverNoCollectionQuickfix;
import anatlyzer.atl.editor.quickfix.warnings.FlattenOverNonNestedCollectionQuickFix;
import anatlyzer.atl.editor.quickfix.warnings.OperationOverCollectionTypeQuickfix;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
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
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingButObjectAssigned;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment;
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
		
		// 3.2
		codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_ModifyBindingFeature.class, "Q3.2")  );

		// Q3.3 --> No esta todavia...
		// codes.add( QfxCode.c(BindingInvalidTargetInResolvedRule_ChangeTargetType.class,	"Q3.2")  );
		
		// Q4.1
		codes.add( QfxCode.c(NoBindingForCompulsoryFeature_ChangeMetamodel.class, 		"Q4.1")  );
		codes.add( QfxCode.c(BindingExpectedOneAssignedMany_ChangeMetamodel.class, 		"Q4.1")  );

		// Q4.2
		// Not in the table...
		// codes.add( QfxCode.c(PrimitiveBindingInvalidAssignment_Quickfix.class, 		"Q4.2")  );

		// Q5.x
		codes.add( QfxCode.c(NoBindingForCompulsoryFeature_AddBinding.class, 			"Q5.1")  );
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
		codes.add( QfxCode.c(AccessToUndefinedValue_ThroughEmptyCollection_AddIf.class,		"Q9.1")  );		
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_AddIfToExpression.class, 		"Q9.1")  );
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_AddIfToBlock.class, 			"Q9.1")  ); // marked as (b) in the original code
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_AddIfToExpression.class, 		"Q9.1")  );
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_AddIfToBlock.class, 				"Q9.1")  ); 
		// TODO: Consider features in subtype, 9.1
		// TODO: Consider features in subtype, 9.2, consider outer for access to undefined, 9.2

		// Q9.2
		codes.add( QfxCode.c(AccessToUndefinedValue_AddRuleFilter.class, 				"Q9.2")  ); 
		codes.add( QfxCode.c(AccessToUndefinedValue_ThroughEmptyCollection_AddRuleFilter.class, "Q9.2")  ); 
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_AddRuleFilter.class,		"Q9.2")  ); 
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_AddRuleFilter.class,			"Q9.2")  ); 
		
		// Q9.3
		codes.add( QfxCode.c(AccessToUndefinedValue_SpecificPrecondition.class,				"Q9.3")  ); 
		codes.add( QfxCode.c(OperationFoundInSubtypeQuickfix_SpecificPrecondition.class,	"Q9.3")  ); 
		codes.add( QfxCode.c(FeatureFoundInSubtypeQuickfix_SpecificPrecondition.class,		"Q9.3")  ); 
		
		// Q10.1
		codes.add( QfxCode.c(AccessToUndefinedValue_ChangeMetamodel.class,					"Q10.1")  ); 
		codes.add( QfxCode.c(AccessToUndefinedValue_ThroughEmptyCollection_ChangeMetamodel.class, "Q10.1")  ); 
		
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
		
		// Q12.5
		codes.add( QfxCode.c(OperationNotFoundQuickfix_ConvertReceptorToCollection.class,	"Q12.5")  ); 
		
		
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
		codes.add( QfxCode.c(PrimitiveBindingInvalidAssignmentQuickfix_SetDefaultValue.class, 						 "Q17.1")  ); 
		codes.add( QfxCode.c(PrimitiveBindingInvalidAssignmentQuickfix_Conversion.class, 						 "Q17.1")  ); 
		
		
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
		codes.add( QfxCode.c(ConflictingRuleSet.class, "E00")  );
		
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
		codes.add( QfxCode.c(OperationFoundInSubtype.class, "E11")  );
		
		// E12
		codes.add( QfxCode.c(FeatureNotFound.class, "E12")  );

		// E14
		codes.add( QfxCode.c(OperationNotFound.class, "E14")  );
		codes.add( QfxCode.c(AttributeNotFoundInThisModule.class, "E14")  );
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
				
		// Fallback - "Other"
		
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
	


}