/**
 */
package anatlyzer.atl.errors.atl_error.util;

import anatlyzer.atl.errors.AnalysisInfo;
import anatlyzer.atl.errors.BatchAnalysis;
import anatlyzer.atl.errors.Problem;

import anatlyzer.atl.errors.atl_error.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage
 * @generated
 */
public class AtlErrorSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AtlErrorPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlErrorSwitch() {
		if (modelPackage == null) {
			modelPackage = AtlErrorPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AtlErrorPackage.LOCAL_PROBLEM: {
				LocalProblem localProblem = (LocalProblem)theEObject;
				T result = caseLocalProblem(localProblem);
				if (result == null) result = caseProblem(localProblem);
				if (result == null) result = caseAnalysisInfo(localProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.MODEL_ELEMENT: {
				ModelElement modelElement = (ModelElement)theEObject;
				T result = caseModelElement(modelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.RUNTIME_ERROR: {
				RuntimeError runtimeError = (RuntimeError)theEObject;
				T result = caseRuntimeError(runtimeError);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.WARNING: {
				Warning warning = (Warning)theEObject;
				T result = caseWarning(warning);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.STYLE_HINT: {
				StyleHint styleHint = (StyleHint)theEObject;
				T result = caseStyleHint(styleHint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.PERFORMANCE_HINT: {
				PerformanceHint performanceHint = (PerformanceHint)theEObject;
				T result = casePerformanceHint(performanceHint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OCL_COMPLIANCE: {
				OclCompliance oclCompliance = (OclCompliance)theEObject;
				T result = caseOclCompliance(oclCompliance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OCL_COMPLIANCE_PROBLEM: {
				OclComplianceProblem oclComplianceProblem = (OclComplianceProblem)theEObject;
				T result = caseOclComplianceProblem(oclComplianceProblem);
				if (result == null) result = caseLocalProblem(oclComplianceProblem);
				if (result == null) result = caseProblem(oclComplianceProblem);
				if (result == null) result = caseAnalysisInfo(oclComplianceProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.NAVIGATION_PROBLEM: {
				NavigationProblem navigationProblem = (NavigationProblem)theEObject;
				T result = caseNavigationProblem(navigationProblem);
				if (result == null) result = caseLocalProblem(navigationProblem);
				if (result == null) result = caseProblem(navigationProblem);
				if (result == null) result = caseAnalysisInfo(navigationProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INVALID_ARGUMENT_PROBLEM: {
				InvalidArgumentProblem invalidArgumentProblem = (InvalidArgumentProblem)theEObject;
				T result = caseInvalidArgumentProblem(invalidArgumentProblem);
				if (result == null) result = caseLocalProblem(invalidArgumentProblem);
				if (result == null) result = caseProblem(invalidArgumentProblem);
				if (result == null) result = caseAnalysisInfo(invalidArgumentProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.TARGET_MODEL_CONFORMANCE_PROBLEM: {
				TargetModelConformanceProblem targetModelConformanceProblem = (TargetModelConformanceProblem)theEObject;
				T result = caseTargetModelConformanceProblem(targetModelConformanceProblem);
				if (result == null) result = caseLocalProblem(targetModelConformanceProblem);
				if (result == null) result = caseProblem(targetModelConformanceProblem);
				if (result == null) result = caseAnalysisInfo(targetModelConformanceProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE: {
				NoBindingForCompulsoryFeature noBindingForCompulsoryFeature = (NoBindingForCompulsoryFeature)theEObject;
				T result = caseNoBindingForCompulsoryFeature(noBindingForCompulsoryFeature);
				if (result == null) result = caseTargetModelConformanceProblem(noBindingForCompulsoryFeature);
				if (result == null) result = caseBindingProblem(noBindingForCompulsoryFeature);
				if (result == null) result = caseLocalProblem(noBindingForCompulsoryFeature);
				if (result == null) result = caseProblem(noBindingForCompulsoryFeature);
				if (result == null) result = caseAnalysisInfo(noBindingForCompulsoryFeature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INVALID_ASSIGNMENT_IMPERATIVE_BINDING: {
				InvalidAssignmentImperativeBinding invalidAssignmentImperativeBinding = (InvalidAssignmentImperativeBinding)theEObject;
				T result = caseInvalidAssignmentImperativeBinding(invalidAssignmentImperativeBinding);
				if (result == null) result = caseBindingProblem(invalidAssignmentImperativeBinding);
				if (result == null) result = caseLocalProblem(invalidAssignmentImperativeBinding);
				if (result == null) result = caseProblem(invalidAssignmentImperativeBinding);
				if (result == null) result = caseAnalysisInfo(invalidAssignmentImperativeBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BINDING_POSSIBLY_UNRESOLVED: {
				BindingPossiblyUnresolved bindingPossiblyUnresolved = (BindingPossiblyUnresolved)theEObject;
				T result = caseBindingPossiblyUnresolved(bindingPossiblyUnresolved);
				if (result == null) result = caseBindingProblem(bindingPossiblyUnresolved);
				if (result == null) result = caseBindingResolution(bindingPossiblyUnresolved);
				if (result == null) result = caseLocalProblem(bindingPossiblyUnresolved);
				if (result == null) result = caseProblem(bindingPossiblyUnresolved);
				if (result == null) result = caseAnalysisInfo(bindingPossiblyUnresolved);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BINDING_WITHOUT_RULE: {
				BindingWithoutRule bindingWithoutRule = (BindingWithoutRule)theEObject;
				T result = caseBindingWithoutRule(bindingWithoutRule);
				if (result == null) result = caseBindingProblem(bindingWithoutRule);
				if (result == null) result = caseBindingResolution(bindingWithoutRule);
				if (result == null) result = caseLocalProblem(bindingWithoutRule);
				if (result == null) result = caseProblem(bindingWithoutRule);
				if (result == null) result = caseAnalysisInfo(bindingWithoutRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE: {
				BindingWithResolvedByIncompatibleRule bindingWithResolvedByIncompatibleRule = (BindingWithResolvedByIncompatibleRule)theEObject;
				T result = caseBindingWithResolvedByIncompatibleRule(bindingWithResolvedByIncompatibleRule);
				if (result == null) result = caseBindingProblem(bindingWithResolvedByIncompatibleRule);
				if (result == null) result = caseBindingResolution(bindingWithResolvedByIncompatibleRule);
				if (result == null) result = caseLocalProblem(bindingWithResolvedByIncompatibleRule);
				if (result == null) result = caseProblem(bindingWithResolvedByIncompatibleRule);
				if (result == null) result = caseAnalysisInfo(bindingWithResolvedByIncompatibleRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.FEATURE_NOT_FOUND: {
				FeatureNotFound featureNotFound = (FeatureNotFound)theEObject;
				T result = caseFeatureNotFound(featureNotFound);
				if (result == null) result = caseNavigationProblem(featureNotFound);
				if (result == null) result = caseRuntimeError(featureNotFound);
				if (result == null) result = caseLocalProblem(featureNotFound);
				if (result == null) result = caseProblem(featureNotFound);
				if (result == null) result = caseAnalysisInfo(featureNotFound);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OPERATION_NOT_FOUND: {
				OperationNotFound operationNotFound = (OperationNotFound)theEObject;
				T result = caseOperationNotFound(operationNotFound);
				if (result == null) result = caseOperationCallInvalid(operationNotFound);
				if (result == null) result = caseNavigationProblem(operationNotFound);
				if (result == null) result = caseRuntimeError(operationNotFound);
				if (result == null) result = caseLocalProblem(operationNotFound);
				if (result == null) result = caseProblem(operationNotFound);
				if (result == null) result = caseAnalysisInfo(operationNotFound);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OPERATION_FOUND_IN_SUBTYPE: {
				OperationFoundInSubtype operationFoundInSubtype = (OperationFoundInSubtype)theEObject;
				T result = caseOperationFoundInSubtype(operationFoundInSubtype);
				if (result == null) result = caseOperationNotFound(operationFoundInSubtype);
				if (result == null) result = caseFoundInSubtype(operationFoundInSubtype);
				if (result == null) result = caseOperationCallInvalid(operationFoundInSubtype);
				if (result == null) result = caseNavigationProblem(operationFoundInSubtype);
				if (result == null) result = caseRuntimeError(operationFoundInSubtype);
				if (result == null) result = caseLocalProblem(operationFoundInSubtype);
				if (result == null) result = caseProblem(operationFoundInSubtype);
				if (result == null) result = caseAnalysisInfo(operationFoundInSubtype);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.FOUND_IN_SUBTYPE: {
				FoundInSubtype foundInSubtype = (FoundInSubtype)theEObject;
				T result = caseFoundInSubtype(foundInSubtype);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.FEATURE_FOUND_IN_SUBTYPE: {
				FeatureFoundInSubtype featureFoundInSubtype = (FeatureFoundInSubtype)theEObject;
				T result = caseFeatureFoundInSubtype(featureFoundInSubtype);
				if (result == null) result = caseFeatureNotFound(featureFoundInSubtype);
				if (result == null) result = caseFoundInSubtype(featureFoundInSubtype);
				if (result == null) result = caseNavigationProblem(featureFoundInSubtype);
				if (result == null) result = caseRuntimeError(featureFoundInSubtype);
				if (result == null) result = caseLocalProblem(featureFoundInSubtype);
				if (result == null) result = caseProblem(featureFoundInSubtype);
				if (result == null) result = caseAnalysisInfo(featureFoundInSubtype);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ACCESS_TO_UNDEFINED_VALUE: {
				AccessToUndefinedValue accessToUndefinedValue = (AccessToUndefinedValue)theEObject;
				T result = caseAccessToUndefinedValue(accessToUndefinedValue);
				if (result == null) result = caseLocalProblem(accessToUndefinedValue);
				if (result == null) result = caseProblem(accessToUndefinedValue);
				if (result == null) result = caseAnalysisInfo(accessToUndefinedValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION: {
				AccessToUndefinedValue_ThroughEmptyCollection accessToUndefinedValue_ThroughEmptyCollection = (AccessToUndefinedValue_ThroughEmptyCollection)theEObject;
				T result = caseAccessToUndefinedValue_ThroughEmptyCollection(accessToUndefinedValue_ThroughEmptyCollection);
				if (result == null) result = caseLocalProblem(accessToUndefinedValue_ThroughEmptyCollection);
				if (result == null) result = caseProblem(accessToUndefinedValue_ThroughEmptyCollection);
				if (result == null) result = caseAnalysisInfo(accessToUndefinedValue_ThroughEmptyCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.RULE_CONFLICTS: {
				RuleConflicts ruleConflicts = (RuleConflicts)theEObject;
				T result = caseRuleConflicts(ruleConflicts);
				if (result == null) result = caseBatchAnalysis(ruleConflicts);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.CONFLICTING_RULE_SET: {
				ConflictingRuleSet conflictingRuleSet = (ConflictingRuleSet)theEObject;
				T result = caseConflictingRuleSet(conflictingRuleSet);
				if (result == null) result = caseProblem(conflictingRuleSet);
				if (result == null) result = caseAnalysisInfo(conflictingRuleSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS: {
				TargetConformanceViolations targetConformanceViolations = (TargetConformanceViolations)theEObject;
				T result = caseTargetConformanceViolations(targetConformanceViolations);
				if (result == null) result = caseBatchAnalysis(targetConformanceViolations);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BATCH_TARGET_CONFORMANCE_ISSUE: {
				BatchTargetConformanceIssue batchTargetConformanceIssue = (BatchTargetConformanceIssue)theEObject;
				T result = caseBatchTargetConformanceIssue(batchTargetConformanceIssue);
				if (result == null) result = caseProblem(batchTargetConformanceIssue);
				if (result == null) result = caseAnalysisInfo(batchTargetConformanceIssue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.TARGET_INVARIANT_VIOLATION: {
				TargetInvariantViolation targetInvariantViolation = (TargetInvariantViolation)theEObject;
				T result = caseTargetInvariantViolation(targetInvariantViolation);
				if (result == null) result = caseBatchTargetConformanceIssue(targetInvariantViolation);
				if (result == null) result = caseProblem(targetInvariantViolation);
				if (result == null) result = caseAnalysisInfo(targetInvariantViolation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR: {
				CollectionOperationOverNoCollectionError collectionOperationOverNoCollectionError = (CollectionOperationOverNoCollectionError)theEObject;
				T result = caseCollectionOperationOverNoCollectionError(collectionOperationOverNoCollectionError);
				if (result == null) result = caseNavigationProblem(collectionOperationOverNoCollectionError);
				if (result == null) result = caseRuntimeError(collectionOperationOverNoCollectionError);
				if (result == null) result = caseLocalProblem(collectionOperationOverNoCollectionError);
				if (result == null) result = caseProblem(collectionOperationOverNoCollectionError);
				if (result == null) result = caseAnalysisInfo(collectionOperationOverNoCollectionError);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.FEATURE_ACCESS_IN_COLLECTION: {
				FeatureAccessInCollection featureAccessInCollection = (FeatureAccessInCollection)theEObject;
				T result = caseFeatureAccessInCollection(featureAccessInCollection);
				if (result == null) result = caseNavigationProblem(featureAccessInCollection);
				if (result == null) result = caseRuntimeError(featureAccessInCollection);
				if (result == null) result = caseLocalProblem(featureAccessInCollection);
				if (result == null) result = caseProblem(featureAccessInCollection);
				if (result == null) result = caseAnalysisInfo(featureAccessInCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OPERATION_OVER_COLLECTION_TYPE: {
				OperationOverCollectionType operationOverCollectionType = (OperationOverCollectionType)theEObject;
				T result = caseOperationOverCollectionType(operationOverCollectionType);
				if (result == null) result = caseOclComplianceProblem(operationOverCollectionType);
				if (result == null) result = caseLocalProblem(operationOverCollectionType);
				if (result == null) result = caseProblem(operationOverCollectionType);
				if (result == null) result = caseAnalysisInfo(operationOverCollectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OPERATION_CALL_INVALID: {
				OperationCallInvalid operationCallInvalid = (OperationCallInvalid)theEObject;
				T result = caseOperationCallInvalid(operationCallInvalid);
				if (result == null) result = caseNavigationProblem(operationCallInvalid);
				if (result == null) result = caseRuntimeError(operationCallInvalid);
				if (result == null) result = caseLocalProblem(operationCallInvalid);
				if (result == null) result = caseProblem(operationCallInvalid);
				if (result == null) result = caseAnalysisInfo(operationCallInvalid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS: {
				OperationCallInvalidNumberOfParameters operationCallInvalidNumberOfParameters = (OperationCallInvalidNumberOfParameters)theEObject;
				T result = caseOperationCallInvalidNumberOfParameters(operationCallInvalidNumberOfParameters);
				if (result == null) result = caseOperationCallInvalid(operationCallInvalidNumberOfParameters);
				if (result == null) result = caseNavigationProblem(operationCallInvalidNumberOfParameters);
				if (result == null) result = caseRuntimeError(operationCallInvalidNumberOfParameters);
				if (result == null) result = caseLocalProblem(operationCallInvalidNumberOfParameters);
				if (result == null) result = caseProblem(operationCallInvalidNumberOfParameters);
				if (result == null) result = caseAnalysisInfo(operationCallInvalidNumberOfParameters);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OPERATION_CALL_INVALID_PARAMETER: {
				OperationCallInvalidParameter operationCallInvalidParameter = (OperationCallInvalidParameter)theEObject;
				T result = caseOperationCallInvalidParameter(operationCallInvalidParameter);
				if (result == null) result = caseOperationCallInvalid(operationCallInvalidParameter);
				if (result == null) result = caseNavigationProblem(operationCallInvalidParameter);
				if (result == null) result = caseRuntimeError(operationCallInvalidParameter);
				if (result == null) result = caseLocalProblem(operationCallInvalidParameter);
				if (result == null) result = caseProblem(operationCallInvalidParameter);
				if (result == null) result = caseAnalysisInfo(operationCallInvalidParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE: {
				OperationNotFoundInThisModule operationNotFoundInThisModule = (OperationNotFoundInThisModule)theEObject;
				T result = caseOperationNotFoundInThisModule(operationNotFoundInThisModule);
				if (result == null) result = caseNavigationProblem(operationNotFoundInThisModule);
				if (result == null) result = caseRuntimeError(operationNotFoundInThisModule);
				if (result == null) result = caseLocalProblem(operationNotFoundInThisModule);
				if (result == null) result = caseProblem(operationNotFoundInThisModule);
				if (result == null) result = caseAnalysisInfo(operationNotFoundInThisModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE: {
				AttributeNotFoundInThisModule attributeNotFoundInThisModule = (AttributeNotFoundInThisModule)theEObject;
				T result = caseAttributeNotFoundInThisModule(attributeNotFoundInThisModule);
				if (result == null) result = caseNavigationProblem(attributeNotFoundInThisModule);
				if (result == null) result = caseRuntimeError(attributeNotFoundInThisModule);
				if (result == null) result = caseLocalProblem(attributeNotFoundInThisModule);
				if (result == null) result = caseProblem(attributeNotFoundInThisModule);
				if (result == null) result = caseAnalysisInfo(attributeNotFoundInThisModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.FEATURE_NOT_FOUND_IN_UNION_TYPE: {
				FeatureNotFoundInUnionType featureNotFoundInUnionType = (FeatureNotFoundInUnionType)theEObject;
				T result = caseFeatureNotFoundInUnionType(featureNotFoundInUnionType);
				if (result == null) result = caseNavigationProblem(featureNotFoundInUnionType);
				if (result == null) result = caseRuntimeError(featureNotFoundInUnionType);
				if (result == null) result = caseLocalProblem(featureNotFoundInUnionType);
				if (result == null) result = caseProblem(featureNotFoundInUnionType);
				if (result == null) result = caseAnalysisInfo(featureNotFoundInUnionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INVALID_OPERATOR: {
				InvalidOperator invalidOperator = (InvalidOperator)theEObject;
				T result = caseInvalidOperator(invalidOperator);
				if (result == null) result = caseInvalidArgumentProblem(invalidOperator);
				if (result == null) result = caseRuntimeError(invalidOperator);
				if (result == null) result = caseLocalProblem(invalidOperator);
				if (result == null) result = caseProblem(invalidOperator);
				if (result == null) result = caseAnalysisInfo(invalidOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INVALID_OPERAND: {
				InvalidOperand invalidOperand = (InvalidOperand)theEObject;
				T result = caseInvalidOperand(invalidOperand);
				if (result == null) result = caseNavigationProblem(invalidOperand);
				if (result == null) result = caseRuntimeError(invalidOperand);
				if (result == null) result = caseLocalProblem(invalidOperand);
				if (result == null) result = caseProblem(invalidOperand);
				if (result == null) result = caseAnalysisInfo(invalidOperand);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE: {
				NoContainerForRefImmediateComposite noContainerForRefImmediateComposite = (NoContainerForRefImmediateComposite)theEObject;
				T result = caseNoContainerForRefImmediateComposite(noContainerForRefImmediateComposite);
				if (result == null) result = caseNavigationProblem(noContainerForRefImmediateComposite);
				if (result == null) result = caseLocalProblem(noContainerForRefImmediateComposite);
				if (result == null) result = caseProblem(noContainerForRefImmediateComposite);
				if (result == null) result = caseAnalysisInfo(noContainerForRefImmediateComposite);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES: {
				DifferentBranchTypes differentBranchTypes = (DifferentBranchTypes)theEObject;
				T result = caseDifferentBranchTypes(differentBranchTypes);
				if (result == null) result = caseNavigationProblem(differentBranchTypes);
				if (result == null) result = caseWarning(differentBranchTypes);
				if (result == null) result = caseLocalProblem(differentBranchTypes);
				if (result == null) result = caseProblem(differentBranchTypes);
				if (result == null) result = caseAnalysisInfo(differentBranchTypes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BINDING_PROBLEM: {
				BindingProblem bindingProblem = (BindingProblem)theEObject;
				T result = caseBindingProblem(bindingProblem);
				if (result == null) result = caseLocalProblem(bindingProblem);
				if (result == null) result = caseProblem(bindingProblem);
				if (result == null) result = caseAnalysisInfo(bindingProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.RESOLVE_TEMP_PROBLEM: {
				ResolveTempProblem resolveTempProblem = (ResolveTempProblem)theEObject;
				T result = caseResolveTempProblem(resolveTempProblem);
				if (result == null) result = caseLocalProblem(resolveTempProblem);
				if (result == null) result = caseProblem(resolveTempProblem);
				if (result == null) result = caseAnalysisInfo(resolveTempProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ASSIGNMENT_TO_READONLY_FEATURE: {
				AssignmentToReadonlyFeature assignmentToReadonlyFeature = (AssignmentToReadonlyFeature)theEObject;
				T result = caseAssignmentToReadonlyFeature(assignmentToReadonlyFeature);
				if (result == null) result = caseBindingProblem(assignmentToReadonlyFeature);
				if (result == null) result = caseLocalProblem(assignmentToReadonlyFeature);
				if (result == null) result = caseProblem(assignmentToReadonlyFeature);
				if (result == null) result = caseAnalysisInfo(assignmentToReadonlyFeature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BINDING_EXPECTED_ONE_ASSIGNED_MANY: {
				BindingExpectedOneAssignedMany bindingExpectedOneAssignedMany = (BindingExpectedOneAssignedMany)theEObject;
				T result = caseBindingExpectedOneAssignedMany(bindingExpectedOneAssignedMany);
				if (result == null) result = caseBindingProblem(bindingExpectedOneAssignedMany);
				if (result == null) result = caseLocalProblem(bindingExpectedOneAssignedMany);
				if (result == null) result = caseProblem(bindingExpectedOneAssignedMany);
				if (result == null) result = caseAnalysisInfo(bindingExpectedOneAssignedMany);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED: {
				PrimitiveBindingButObjectAssigned primitiveBindingButObjectAssigned = (PrimitiveBindingButObjectAssigned)theEObject;
				T result = casePrimitiveBindingButObjectAssigned(primitiveBindingButObjectAssigned);
				if (result == null) result = caseBindingProblem(primitiveBindingButObjectAssigned);
				if (result == null) result = caseLocalProblem(primitiveBindingButObjectAssigned);
				if (result == null) result = caseProblem(primitiveBindingButObjectAssigned);
				if (result == null) result = caseAnalysisInfo(primitiveBindingButObjectAssigned);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED: {
				ObjectBindingButPrimitiveAssigned objectBindingButPrimitiveAssigned = (ObjectBindingButPrimitiveAssigned)theEObject;
				T result = caseObjectBindingButPrimitiveAssigned(objectBindingButPrimitiveAssigned);
				if (result == null) result = caseBindingProblem(objectBindingButPrimitiveAssigned);
				if (result == null) result = caseLocalProblem(objectBindingButPrimitiveAssigned);
				if (result == null) result = caseProblem(objectBindingButPrimitiveAssigned);
				if (result == null) result = caseAnalysisInfo(objectBindingButPrimitiveAssigned);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.PRIMITIVE_BINDING_INVALID_ASSIGNMENT: {
				PrimitiveBindingInvalidAssignment primitiveBindingInvalidAssignment = (PrimitiveBindingInvalidAssignment)theEObject;
				T result = casePrimitiveBindingInvalidAssignment(primitiveBindingInvalidAssignment);
				if (result == null) result = caseBindingProblem(primitiveBindingInvalidAssignment);
				if (result == null) result = caseLocalProblem(primitiveBindingInvalidAssignment);
				if (result == null) result = caseProblem(primitiveBindingInvalidAssignment);
				if (result == null) result = caseAnalysisInfo(primitiveBindingInvalidAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BINDING_RESOLUTION: {
				BindingResolution bindingResolution = (BindingResolution)theEObject;
				T result = caseBindingResolution(bindingResolution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.RESOLVED_RULE_INFO: {
				ResolvedRuleInfo resolvedRuleInfo = (ResolvedRuleInfo)theEObject;
				T result = caseResolvedRuleInfo(resolvedRuleInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.RESOLVE_TEMP_WITHOUT_RULE: {
				ResolveTempWithoutRule resolveTempWithoutRule = (ResolveTempWithoutRule)theEObject;
				T result = caseResolveTempWithoutRule(resolveTempWithoutRule);
				if (result == null) result = caseResolveTempProblem(resolveTempWithoutRule);
				if (result == null) result = caseLocalProblem(resolveTempWithoutRule);
				if (result == null) result = caseProblem(resolveTempWithoutRule);
				if (result == null) result = caseAnalysisInfo(resolveTempWithoutRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.RESOLVE_TEMP_POSSIBLY_UNRESOLVED: {
				ResolveTempPossiblyUnresolved resolveTempPossiblyUnresolved = (ResolveTempPossiblyUnresolved)theEObject;
				T result = caseResolveTempPossiblyUnresolved(resolveTempPossiblyUnresolved);
				if (result == null) result = caseResolveTempProblem(resolveTempPossiblyUnresolved);
				if (result == null) result = caseBindingResolution(resolveTempPossiblyUnresolved);
				if (result == null) result = caseLocalProblem(resolveTempPossiblyUnresolved);
				if (result == null) result = caseProblem(resolveTempPossiblyUnresolved);
				if (result == null) result = caseAnalysisInfo(resolveTempPossiblyUnresolved);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND: {
				ResolveTempOutputPatternElementNotFound resolveTempOutputPatternElementNotFound = (ResolveTempOutputPatternElementNotFound)theEObject;
				T result = caseResolveTempOutputPatternElementNotFound(resolveTempOutputPatternElementNotFound);
				if (result == null) result = caseResolveTempProblem(resolveTempOutputPatternElementNotFound);
				if (result == null) result = caseLocalProblem(resolveTempOutputPatternElementNotFound);
				if (result == null) result = caseProblem(resolveTempOutputPatternElementNotFound);
				if (result == null) result = caseAnalysisInfo(resolveTempOutputPatternElementNotFound);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.FLATTEN_OVER_NON_NESTED_COLLECTION: {
				FlattenOverNonNestedCollection flattenOverNonNestedCollection = (FlattenOverNonNestedCollection)theEObject;
				T result = caseFlattenOverNonNestedCollection(flattenOverNonNestedCollection);
				if (result == null) result = caseLocalProblem(flattenOverNonNestedCollection);
				if (result == null) result = caseProblem(flattenOverNonNestedCollection);
				if (result == null) result = caseAnalysisInfo(flattenOverNonNestedCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.CHANGE_SELECT_FIRST_FOR_ANY: {
				ChangeSelectFirstForAny changeSelectFirstForAny = (ChangeSelectFirstForAny)theEObject;
				T result = caseChangeSelectFirstForAny(changeSelectFirstForAny);
				if (result == null) result = caseLocalProblem(changeSelectFirstForAny);
				if (result == null) result = caseProblem(changeSelectFirstForAny);
				if (result == null) result = caseAnalysisInfo(changeSelectFirstForAny);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ITERATOR_OVER_EMPTY_SEQUENCE: {
				IteratorOverEmptySequence iteratorOverEmptySequence = (IteratorOverEmptySequence)theEObject;
				T result = caseIteratorOverEmptySequence(iteratorOverEmptySequence);
				if (result == null) result = caseLocalProblem(iteratorOverEmptySequence);
				if (result == null) result = caseProblem(iteratorOverEmptySequence);
				if (result == null) result = caseAnalysisInfo(iteratorOverEmptySequence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INVALID_OPERATOR_USAGE: {
				InvalidOperatorUsage invalidOperatorUsage = (InvalidOperatorUsage)theEObject;
				T result = caseInvalidOperatorUsage(invalidOperatorUsage);
				if (result == null) result = caseLocalProblem(invalidOperatorUsage);
				if (result == null) result = caseProblem(invalidOperatorUsage);
				if (result == null) result = caseAnalysisInfo(invalidOperatorUsage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.READING_TARGET_MODEL: {
				ReadingTargetModel readingTargetModel = (ReadingTargetModel)theEObject;
				T result = caseReadingTargetModel(readingTargetModel);
				if (result == null) result = caseLocalProblem(readingTargetModel);
				if (result == null) result = caseProblem(readingTargetModel);
				if (result == null) result = caseAnalysisInfo(readingTargetModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.WRITING_SOURCE_MODEL: {
				WritingSourceModel writingSourceModel = (WritingSourceModel)theEObject;
				T result = caseWritingSourceModel(writingSourceModel);
				if (result == null) result = caseLocalProblem(writingSourceModel);
				if (result == null) result = caseProblem(writingSourceModel);
				if (result == null) result = caseAnalysisInfo(writingSourceModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.LAZY_RULE_WITH_FILTER: {
				LazyRuleWithFilter lazyRuleWithFilter = (LazyRuleWithFilter)theEObject;
				T result = caseLazyRuleWithFilter(lazyRuleWithFilter);
				if (result == null) result = caseLocalProblem(lazyRuleWithFilter);
				if (result == null) result = caseProblem(lazyRuleWithFilter);
				if (result == null) result = caseAnalysisInfo(lazyRuleWithFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INVALID_RULE_INHERITANCE: {
				InvalidRuleInheritance invalidRuleInheritance = (InvalidRuleInheritance)theEObject;
				T result = caseInvalidRuleInheritance(invalidRuleInheritance);
				if (result == null) result = caseLocalProblem(invalidRuleInheritance);
				if (result == null) result = caseProblem(invalidRuleInheritance);
				if (result == null) result = caseAnalysisInfo(invalidRuleInheritance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.AMBIGUOUS_TARGET_MODEL_REFERENCE: {
				AmbiguousTargetModelReference ambiguousTargetModelReference = (AmbiguousTargetModelReference)theEObject;
				T result = caseAmbiguousTargetModelReference(ambiguousTargetModelReference);
				if (result == null) result = caseLocalProblem(ambiguousTargetModelReference);
				if (result == null) result = caseProblem(ambiguousTargetModelReference);
				if (result == null) result = caseAnalysisInfo(ambiguousTargetModelReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.NO_MODEL_FOUND: {
				NoModelFound noModelFound = (NoModelFound)theEObject;
				T result = caseNoModelFound(noModelFound);
				if (result == null) result = caseLocalProblem(noModelFound);
				if (result == null) result = caseProblem(noModelFound);
				if (result == null) result = caseAnalysisInfo(noModelFound);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.NO_ENUM_LITERAL: {
				NoEnumLiteral noEnumLiteral = (NoEnumLiteral)theEObject;
				T result = caseNoEnumLiteral(noEnumLiteral);
				if (result == null) result = caseLocalProblem(noEnumLiteral);
				if (result == null) result = caseProblem(noEnumLiteral);
				if (result == null) result = caseAnalysisInfo(noEnumLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.WRONG_TYPE: {
				WrongType wrongType = (WrongType)theEObject;
				T result = caseWrongType(wrongType);
				if (result == null) result = caseLocalProblem(wrongType);
				if (result == null) result = caseProblem(wrongType);
				if (result == null) result = caseAnalysisInfo(wrongType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ITERATOR_BODY_WRONG_TYPE: {
				IteratorBodyWrongType iteratorBodyWrongType = (IteratorBodyWrongType)theEObject;
				T result = caseIteratorBodyWrongType(iteratorBodyWrongType);
				if (result == null) result = caseWrongType(iteratorBodyWrongType);
				if (result == null) result = caseLocalProblem(iteratorBodyWrongType);
				if (result == null) result = caseProblem(iteratorBodyWrongType);
				if (result == null) result = caseAnalysisInfo(iteratorBodyWrongType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.MATCHED_RULE_WITHOUT_OUTPUT_PATTERN: {
				MatchedRuleWithoutOutputPattern matchedRuleWithoutOutputPattern = (MatchedRuleWithoutOutputPattern)theEObject;
				T result = caseMatchedRuleWithoutOutputPattern(matchedRuleWithoutOutputPattern);
				if (result == null) result = caseLocalProblem(matchedRuleWithoutOutputPattern);
				if (result == null) result = caseProblem(matchedRuleWithoutOutputPattern);
				if (result == null) result = caseAnalysisInfo(matchedRuleWithoutOutputPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.MATCHED_RULE_FILTER_NON_BOOLEAN: {
				MatchedRuleFilterNonBoolean matchedRuleFilterNonBoolean = (MatchedRuleFilterNonBoolean)theEObject;
				T result = caseMatchedRuleFilterNonBoolean(matchedRuleFilterNonBoolean);
				if (result == null) result = caseLocalProblem(matchedRuleFilterNonBoolean);
				if (result == null) result = caseProblem(matchedRuleFilterNonBoolean);
				if (result == null) result = caseAnalysisInfo(matchedRuleFilterNonBoolean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.EXPECTED_COLLECTION_IN_FOR_EACH: {
				ExpectedCollectionInForEach expectedCollectionInForEach = (ExpectedCollectionInForEach)theEObject;
				T result = caseExpectedCollectionInForEach(expectedCollectionInForEach);
				if (result == null) result = caseLocalProblem(expectedCollectionInForEach);
				if (result == null) result = caseProblem(expectedCollectionInForEach);
				if (result == null) result = caseAnalysisInfo(expectedCollectionInForEach);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.NO_CLASS_FOUND_IN_METAMODEL: {
				NoClassFoundInMetamodel noClassFoundInMetamodel = (NoClassFoundInMetamodel)theEObject;
				T result = caseNoClassFoundInMetamodel(noClassFoundInMetamodel);
				if (result == null) result = caseLocalProblem(noClassFoundInMetamodel);
				if (result == null) result = caseProblem(noClassFoundInMetamodel);
				if (result == null) result = caseAnalysisInfo(noClassFoundInMetamodel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INVALID_ARGUMENT: {
				InvalidArgument invalidArgument = (InvalidArgument)theEObject;
				T result = caseInvalidArgument(invalidArgument);
				if (result == null) result = caseLocalProblem(invalidArgument);
				if (result == null) result = caseProblem(invalidArgument);
				if (result == null) result = caseAnalysisInfo(invalidArgument);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.COLLECTION_OPERATION_NOT_FOUND: {
				CollectionOperationNotFound collectionOperationNotFound = (CollectionOperationNotFound)theEObject;
				T result = caseCollectionOperationNotFound(collectionOperationNotFound);
				if (result == null) result = caseLocalProblem(collectionOperationNotFound);
				if (result == null) result = caseProblem(collectionOperationNotFound);
				if (result == null) result = caseAnalysisInfo(collectionOperationNotFound);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE: {
				IteratorOverNoCollectionType iteratorOverNoCollectionType = (IteratorOverNoCollectionType)theEObject;
				T result = caseIteratorOverNoCollectionType(iteratorOverNoCollectionType);
				if (result == null) result = caseLocalProblem(iteratorOverNoCollectionType);
				if (result == null) result = caseProblem(iteratorOverNoCollectionType);
				if (result == null) result = caseAnalysisInfo(iteratorOverNoCollectionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION: {
				AbstractIncoherentVariableDeclaration abstractIncoherentVariableDeclaration = (AbstractIncoherentVariableDeclaration)theEObject;
				T result = caseAbstractIncoherentVariableDeclaration(abstractIncoherentVariableDeclaration);
				if (result == null) result = caseLocalProblem(abstractIncoherentVariableDeclaration);
				if (result == null) result = caseOclCompliance(abstractIncoherentVariableDeclaration);
				if (result == null) result = caseProblem(abstractIncoherentVariableDeclaration);
				if (result == null) result = caseAnalysisInfo(abstractIncoherentVariableDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INCOHERENT_VARIABLE_DECLARATION: {
				IncoherentVariableDeclaration incoherentVariableDeclaration = (IncoherentVariableDeclaration)theEObject;
				T result = caseIncoherentVariableDeclaration(incoherentVariableDeclaration);
				if (result == null) result = caseAbstractIncoherentVariableDeclaration(incoherentVariableDeclaration);
				if (result == null) result = caseLocalProblem(incoherentVariableDeclaration);
				if (result == null) result = caseOclCompliance(incoherentVariableDeclaration);
				if (result == null) result = caseProblem(incoherentVariableDeclaration);
				if (result == null) result = caseAnalysisInfo(incoherentVariableDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.INCOHERENT_HELPER_RETURN_TYPE: {
				IncoherentHelperReturnType incoherentHelperReturnType = (IncoherentHelperReturnType)theEObject;
				T result = caseIncoherentHelperReturnType(incoherentHelperReturnType);
				if (result == null) result = caseAbstractIncoherentVariableDeclaration(incoherentHelperReturnType);
				if (result == null) result = caseLocalProblem(incoherentHelperReturnType);
				if (result == null) result = caseOclCompliance(incoherentHelperReturnType);
				if (result == null) result = caseProblem(incoherentHelperReturnType);
				if (result == null) result = caseAnalysisInfo(incoherentHelperReturnType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.BINDING_INPLACE_INVALID: {
				BindingInplaceInvalid bindingInplaceInvalid = (BindingInplaceInvalid)theEObject;
				T result = caseBindingInplaceInvalid(bindingInplaceInvalid);
				if (result == null) result = caseBindingProblem(bindingInplaceInvalid);
				if (result == null) result = caseLocalProblem(bindingInplaceInvalid);
				if (result == null) result = caseProblem(bindingInplaceInvalid);
				if (result == null) result = caseAnalysisInfo(bindingInplaceInvalid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.CANNOT_INSTANTIATE_ABSTRACT_CLASS: {
				CannotInstantiateAbstractClass cannotInstantiateAbstractClass = (CannotInstantiateAbstractClass)theEObject;
				T result = caseCannotInstantiateAbstractClass(cannotInstantiateAbstractClass);
				if (result == null) result = caseLocalProblem(cannotInstantiateAbstractClass);
				if (result == null) result = caseProblem(cannotInstantiateAbstractClass);
				if (result == null) result = caseAnalysisInfo(cannotInstantiateAbstractClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.GENERIC_LOCAL_PROBLEM: {
				GenericLocalProblem genericLocalProblem = (GenericLocalProblem)theEObject;
				T result = caseGenericLocalProblem(genericLocalProblem);
				if (result == null) result = caseLocalProblem(genericLocalProblem);
				if (result == null) result = caseProblem(genericLocalProblem);
				if (result == null) result = caseAnalysisInfo(genericLocalProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AtlErrorPackage.ATL_PARSE_ERROR: {
				AtlParseError atlParseError = (AtlParseError)theEObject;
				T result = caseAtlParseError(atlParseError);
				if (result == null) result = caseLocalProblem(atlParseError);
				if (result == null) result = caseProblem(atlParseError);
				if (result == null) result = caseAnalysisInfo(atlParseError);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Local Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Local Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocalProblem(LocalProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Runtime Error</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Runtime Error</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuntimeError(RuntimeError object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Warning</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Warning</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWarning(Warning object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Style Hint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Style Hint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStyleHint(StyleHint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Performance Hint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Performance Hint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePerformanceHint(PerformanceHint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationProblem(NavigationProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Argument Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Argument Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidArgumentProblem(InvalidArgumentProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Target Model Conformance Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Target Model Conformance Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTargetModelConformanceProblem(TargetModelConformanceProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conflicting Rule Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conflicting Rule Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConflictingRuleSet(ConflictingRuleSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Target Conformance Violations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Target Conformance Violations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTargetConformanceViolations(TargetConformanceViolations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Batch Target Conformance Issue</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Batch Target Conformance Issue</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBatchTargetConformanceIssue(BatchTargetConformanceIssue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Target Invariant Violation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Target Invariant Violation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTargetInvariantViolation(TargetInvariantViolation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Operation Over No Collection Error</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Operation Over No Collection Error</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionOperationOverNoCollectionError(CollectionOperationOverNoCollectionError object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Access In Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Access In Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureAccessInCollection(FeatureAccessInCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Not Found</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureNotFound(FeatureNotFound object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Found In Subtype</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Found In Subtype</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFoundInSubtype(FoundInSubtype object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Found In Subtype</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Found In Subtype</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureFoundInSubtype(FeatureFoundInSubtype object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Call Invalid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Call Invalid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationCallInvalid(OperationCallInvalid object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Not Found</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationNotFound(OperationNotFound object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Found In Subtype</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Found In Subtype</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationFoundInSubtype(OperationFoundInSubtype object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Call Invalid Number Of Parameters</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Call Invalid Number Of Parameters</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationCallInvalidNumberOfParameters(OperationCallInvalidNumberOfParameters object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Call Invalid Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Call Invalid Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationCallInvalidParameter(OperationCallInvalidParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Not Found In This Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Not Found In This Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationNotFoundInThisModule(OperationNotFoundInThisModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Not Found In This Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Not Found In This Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeNotFoundInThisModule(AttributeNotFoundInThisModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Not Found In Union Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Not Found In Union Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureNotFoundInUnionType(FeatureNotFoundInUnionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidOperator(InvalidOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Operand</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Operand</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidOperand(InvalidOperand object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Container For Ref Immediate Composite</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Container For Ref Immediate Composite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoContainerForRefImmediateComposite(NoContainerForRefImmediateComposite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Different Branch Types</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Different Branch Types</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDifferentBranchTypes(DifferentBranchTypes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingProblem(BindingProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resolve Temp Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resolve Temp Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResolveTempProblem(ResolveTempProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assignment To Readonly Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assignment To Readonly Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssignmentToReadonlyFeature(AssignmentToReadonlyFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Binding For Compulsory Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Binding For Compulsory Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoBindingForCompulsoryFeature(NoBindingForCompulsoryFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Assignment Imperative Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Assignment Imperative Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidAssignmentImperativeBinding(InvalidAssignmentImperativeBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Expected One Assigned Many</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Expected One Assigned Many</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingExpectedOneAssignedMany(BindingExpectedOneAssignedMany object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Binding But Object Assigned</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Binding But Object Assigned</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveBindingButObjectAssigned(PrimitiveBindingButObjectAssigned object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object Binding But Primitive Assigned</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Binding But Primitive Assigned</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectBindingButPrimitiveAssigned(ObjectBindingButPrimitiveAssigned object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Binding Invalid Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Binding Invalid Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveBindingInvalidAssignment(PrimitiveBindingInvalidAssignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Resolution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Resolution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingResolution(BindingResolution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Without Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Without Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingWithoutRule(BindingWithoutRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding With Resolved By Incompatible Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding With Resolved By Incompatible Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingWithResolvedByIncompatibleRule(BindingWithResolvedByIncompatibleRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Possibly Unresolved</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Possibly Unresolved</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingPossiblyUnresolved(BindingPossiblyUnresolved object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resolved Rule Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resolved Rule Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResolvedRuleInfo(ResolvedRuleInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resolve Temp Without Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resolve Temp Without Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResolveTempWithoutRule(ResolveTempWithoutRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resolve Temp Possibly Unresolved</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resolve Temp Possibly Unresolved</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResolveTempPossiblyUnresolved(ResolveTempPossiblyUnresolved object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resolve Temp Output Pattern Element Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resolve Temp Output Pattern Element Not Found</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResolveTempOutputPatternElementNotFound(ResolveTempOutputPatternElementNotFound object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Flatten Over Non Nested Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Flatten Over Non Nested Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFlattenOverNonNestedCollection(FlattenOverNonNestedCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change Select First For Any</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change Select First For Any</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeSelectFirstForAny(ChangeSelectFirstForAny object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterator Over Empty Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator Over Empty Sequence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIteratorOverEmptySequence(IteratorOverEmptySequence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Operator Usage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Operator Usage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidOperatorUsage(InvalidOperatorUsage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reading Target Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reading Target Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReadingTargetModel(ReadingTargetModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Writing Source Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Writing Source Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWritingSourceModel(WritingSourceModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lazy Rule With Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lazy Rule With Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLazyRuleWithFilter(LazyRuleWithFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Rule Inheritance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Rule Inheritance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidRuleInheritance(InvalidRuleInheritance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ambiguous Target Model Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ambiguous Target Model Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAmbiguousTargetModelReference(AmbiguousTargetModelReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Model Found</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Model Found</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoModelFound(NoModelFound object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Enum Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Enum Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoEnumLiteral(NoEnumLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrong Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrong Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrongType(WrongType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterator Body Wrong Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator Body Wrong Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIteratorBodyWrongType(IteratorBodyWrongType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matched Rule Without Output Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matched Rule Without Output Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatchedRuleWithoutOutputPattern(MatchedRuleWithoutOutputPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matched Rule Filter Non Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matched Rule Filter Non Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatchedRuleFilterNonBoolean(MatchedRuleFilterNonBoolean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expected Collection In For Each</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expected Collection In For Each</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpectedCollectionInForEach(ExpectedCollectionInForEach object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Class Found In Metamodel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Class Found In Metamodel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoClassFoundInMetamodel(NoClassFoundInMetamodel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Argument</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Argument</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidArgument(InvalidArgument object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Operation Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Operation Not Found</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionOperationNotFound(CollectionOperationNotFound object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Iterator Over No Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator Over No Collection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIteratorOverNoCollectionType(IteratorOverNoCollectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Incoherent Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Incoherent Variable Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractIncoherentVariableDeclaration(AbstractIncoherentVariableDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Compliance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Compliance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclCompliance(OclCompliance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Compliance Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Compliance Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclComplianceProblem(OclComplianceProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Incoherent Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Incoherent Variable Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncoherentVariableDeclaration(IncoherentVariableDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Incoherent Helper Return Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Incoherent Helper Return Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncoherentHelperReturnType(IncoherentHelperReturnType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Over Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Over Collection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationOverCollectionType(OperationOverCollectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Access To Undefined Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Access To Undefined Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccessToUndefinedValue(AccessToUndefinedValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Access To Undefined Value Through Empty Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Access To Undefined Value Through Empty Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccessToUndefinedValue_ThroughEmptyCollection(AccessToUndefinedValue_ThroughEmptyCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule Conflicts</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule Conflicts</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuleConflicts(RuleConflicts object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Inplace Invalid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Inplace Invalid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingInplaceInvalid(BindingInplaceInvalid object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cannot Instantiate Abstract Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cannot Instantiate Abstract Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCannotInstantiateAbstractClass(CannotInstantiateAbstractClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Local Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Local Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericLocalProblem(GenericLocalProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Atl Parse Error</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Atl Parse Error</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAtlParseError(AtlParseError object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Analysis Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Analysis Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnalysisInfo(AnalysisInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProblem(Problem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Batch Analysis</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Batch Analysis</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBatchAnalysis(BatchAnalysis object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //AtlErrorSwitch
