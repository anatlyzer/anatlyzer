/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AtlErrorFactoryImpl extends EFactoryImpl implements AtlErrorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AtlErrorFactory init() {
		try {
			AtlErrorFactory theAtlErrorFactory = (AtlErrorFactory)EPackage.Registry.INSTANCE.getEFactory(AtlErrorPackage.eNS_URI);
			if (theAtlErrorFactory != null) {
				return theAtlErrorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AtlErrorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlErrorFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AtlErrorPackage.MODEL_ELEMENT: return createModelElement();
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE: return createNoBindingForCompulsoryFeature();
			case AtlErrorPackage.INVALID_ASSIGNMENT_IMPERATIVE_BINDING: return createInvalidAssignmentImperativeBinding();
			case AtlErrorPackage.BINDING_POSSIBLY_UNRESOLVED: return createBindingPossiblyUnresolved();
			case AtlErrorPackage.BINDING_WITHOUT_RULE: return createBindingWithoutRule();
			case AtlErrorPackage.BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE: return createBindingWithResolvedByIncompatibleRule();
			case AtlErrorPackage.FEATURE_NOT_FOUND: return createFeatureNotFound();
			case AtlErrorPackage.OPERATION_NOT_FOUND: return createOperationNotFound();
			case AtlErrorPackage.OPERATION_FOUND_IN_SUBTYPE: return createOperationFoundInSubtype();
			case AtlErrorPackage.FEATURE_FOUND_IN_SUBTYPE: return createFeatureFoundInSubtype();
			case AtlErrorPackage.ACCESS_TO_UNDEFINED_VALUE: return createAccessToUndefinedValue();
			case AtlErrorPackage.ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION: return createAccessToUndefinedValue_ThroughEmptyCollection();
			case AtlErrorPackage.RULE_CONFLICTS: return createRuleConflicts();
			case AtlErrorPackage.CONFLICTING_RULE_SET: return createConflictingRuleSet();
			case AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS: return createTargetConformanceViolations();
			case AtlErrorPackage.TARGET_INVARIANT_VIOLATION: return createTargetInvariantViolation();
			case AtlErrorPackage.COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR: return createCollectionOperationOverNoCollectionError();
			case AtlErrorPackage.FEATURE_ACCESS_IN_COLLECTION: return createFeatureAccessInCollection();
			case AtlErrorPackage.OPERATION_OVER_COLLECTION_TYPE: return createOperationOverCollectionType();
			case AtlErrorPackage.OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS: return createOperationCallInvalidNumberOfParameters();
			case AtlErrorPackage.OPERATION_CALL_INVALID_PARAMETER: return createOperationCallInvalidParameter();
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE: return createOperationNotFoundInThisModule();
			case AtlErrorPackage.ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE: return createAttributeNotFoundInThisModule();
			case AtlErrorPackage.FEATURE_NOT_FOUND_IN_UNION_TYPE: return createFeatureNotFoundInUnionType();
			case AtlErrorPackage.INVALID_OPERATOR: return createInvalidOperator();
			case AtlErrorPackage.INVALID_OPERAND: return createInvalidOperand();
			case AtlErrorPackage.NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE: return createNoContainerForRefImmediateComposite();
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES: return createDifferentBranchTypes();
			case AtlErrorPackage.ASSIGNMENT_TO_READONLY_FEATURE: return createAssignmentToReadonlyFeature();
			case AtlErrorPackage.BINDING_EXPECTED_ONE_ASSIGNED_MANY: return createBindingExpectedOneAssignedMany();
			case AtlErrorPackage.PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED: return createPrimitiveBindingButObjectAssigned();
			case AtlErrorPackage.OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED: return createObjectBindingButPrimitiveAssigned();
			case AtlErrorPackage.PRIMITIVE_BINDING_INVALID_ASSIGNMENT: return createPrimitiveBindingInvalidAssignment();
			case AtlErrorPackage.RESOLVED_RULE_INFO: return createResolvedRuleInfo();
			case AtlErrorPackage.RESOLVE_TEMP_WITHOUT_RULE: return createResolveTempWithoutRule();
			case AtlErrorPackage.RESOLVE_TEMP_POSSIBLY_UNRESOLVED: return createResolveTempPossiblyUnresolved();
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND: return createResolveTempOutputPatternElementNotFound();
			case AtlErrorPackage.FLATTEN_OVER_NON_NESTED_COLLECTION: return createFlattenOverNonNestedCollection();
			case AtlErrorPackage.CHANGE_SELECT_FIRST_FOR_ANY: return createChangeSelectFirstForAny();
			case AtlErrorPackage.ITERATOR_OVER_EMPTY_SEQUENCE: return createIteratorOverEmptySequence();
			case AtlErrorPackage.INVALID_OPERATOR_USAGE: return createInvalidOperatorUsage();
			case AtlErrorPackage.READING_TARGET_MODEL: return createReadingTargetModel();
			case AtlErrorPackage.WRITING_SOURCE_MODEL: return createWritingSourceModel();
			case AtlErrorPackage.LAZY_RULE_WITH_FILTER: return createLazyRuleWithFilter();
			case AtlErrorPackage.INVALID_RULE_INHERITANCE: return createInvalidRuleInheritance();
			case AtlErrorPackage.AMBIGUOUS_TARGET_MODEL_REFERENCE: return createAmbiguousTargetModelReference();
			case AtlErrorPackage.NO_MODEL_FOUND: return createNoModelFound();
			case AtlErrorPackage.NO_ENUM_LITERAL: return createNoEnumLiteral();
			case AtlErrorPackage.ITERATOR_BODY_WRONG_TYPE: return createIteratorBodyWrongType();
			case AtlErrorPackage.MATCHED_RULE_WITHOUT_OUTPUT_PATTERN: return createMatchedRuleWithoutOutputPattern();
			case AtlErrorPackage.MATCHED_RULE_FILTER_NON_BOOLEAN: return createMatchedRuleFilterNonBoolean();
			case AtlErrorPackage.EXPECTED_COLLECTION_IN_FOR_EACH: return createExpectedCollectionInForEach();
			case AtlErrorPackage.NO_CLASS_FOUND_IN_METAMODEL: return createNoClassFoundInMetamodel();
			case AtlErrorPackage.INVALID_ARGUMENT: return createInvalidArgument();
			case AtlErrorPackage.COLLECTION_OPERATION_NOT_FOUND: return createCollectionOperationNotFound();
			case AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE: return createIteratorOverNoCollectionType();
			case AtlErrorPackage.INCOHERENT_VARIABLE_DECLARATION: return createIncoherentVariableDeclaration();
			case AtlErrorPackage.INCOHERENT_HELPER_RETURN_TYPE: return createIncoherentHelperReturnType();
			case AtlErrorPackage.BINDING_INPLACE_INVALID: return createBindingInplaceInvalid();
			case AtlErrorPackage.CANNOT_INSTANTIATE_ABSTRACT_CLASS: return createCannotInstantiateAbstractClass();
			case AtlErrorPackage.GENERIC_LOCAL_PROBLEM: return createGenericLocalProblem();
			case AtlErrorPackage.ATL_PARSE_ERROR: return createAtlParseError();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE_KIND:
				return createNoBindingForCompulsoryFeatureKindFromString(eDataType, initialValue);
			case AtlErrorPackage.INVALID_RULE_INHERITANCE_KIND:
				return createInvalidRuleInheritanceKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE_KIND:
				return convertNoBindingForCompulsoryFeatureKindToString(eDataType, instanceValue);
			case AtlErrorPackage.INVALID_RULE_INHERITANCE_KIND:
				return convertInvalidRuleInheritanceKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelElement createModelElement() {
		ModelElementImpl modelElement = new ModelElementImpl();
		return modelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConflictingRuleSet createConflictingRuleSet() {
		ConflictingRuleSetImpl conflictingRuleSet = new ConflictingRuleSetImpl();
		return conflictingRuleSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetConformanceViolations createTargetConformanceViolations() {
		TargetConformanceViolationsImpl targetConformanceViolations = new TargetConformanceViolationsImpl();
		return targetConformanceViolations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetInvariantViolation createTargetInvariantViolation() {
		TargetInvariantViolationImpl targetInvariantViolation = new TargetInvariantViolationImpl();
		return targetInvariantViolation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionOperationOverNoCollectionError createCollectionOperationOverNoCollectionError() {
		CollectionOperationOverNoCollectionErrorImpl collectionOperationOverNoCollectionError = new CollectionOperationOverNoCollectionErrorImpl();
		return collectionOperationOverNoCollectionError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureAccessInCollection createFeatureAccessInCollection() {
		FeatureAccessInCollectionImpl featureAccessInCollection = new FeatureAccessInCollectionImpl();
		return featureAccessInCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureNotFound createFeatureNotFound() {
		FeatureNotFoundImpl featureNotFound = new FeatureNotFoundImpl();
		return featureNotFound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureFoundInSubtype createFeatureFoundInSubtype() {
		FeatureFoundInSubtypeImpl featureFoundInSubtype = new FeatureFoundInSubtypeImpl();
		return featureFoundInSubtype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationNotFound createOperationNotFound() {
		OperationNotFoundImpl operationNotFound = new OperationNotFoundImpl();
		return operationNotFound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationFoundInSubtype createOperationFoundInSubtype() {
		OperationFoundInSubtypeImpl operationFoundInSubtype = new OperationFoundInSubtypeImpl();
		return operationFoundInSubtype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationCallInvalidNumberOfParameters createOperationCallInvalidNumberOfParameters() {
		OperationCallInvalidNumberOfParametersImpl operationCallInvalidNumberOfParameters = new OperationCallInvalidNumberOfParametersImpl();
		return operationCallInvalidNumberOfParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationCallInvalidParameter createOperationCallInvalidParameter() {
		OperationCallInvalidParameterImpl operationCallInvalidParameter = new OperationCallInvalidParameterImpl();
		return operationCallInvalidParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationNotFoundInThisModule createOperationNotFoundInThisModule() {
		OperationNotFoundInThisModuleImpl operationNotFoundInThisModule = new OperationNotFoundInThisModuleImpl();
		return operationNotFoundInThisModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeNotFoundInThisModule createAttributeNotFoundInThisModule() {
		AttributeNotFoundInThisModuleImpl attributeNotFoundInThisModule = new AttributeNotFoundInThisModuleImpl();
		return attributeNotFoundInThisModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureNotFoundInUnionType createFeatureNotFoundInUnionType() {
		FeatureNotFoundInUnionTypeImpl featureNotFoundInUnionType = new FeatureNotFoundInUnionTypeImpl();
		return featureNotFoundInUnionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidOperator createInvalidOperator() {
		InvalidOperatorImpl invalidOperator = new InvalidOperatorImpl();
		return invalidOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidOperand createInvalidOperand() {
		InvalidOperandImpl invalidOperand = new InvalidOperandImpl();
		return invalidOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoContainerForRefImmediateComposite createNoContainerForRefImmediateComposite() {
		NoContainerForRefImmediateCompositeImpl noContainerForRefImmediateComposite = new NoContainerForRefImmediateCompositeImpl();
		return noContainerForRefImmediateComposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DifferentBranchTypes createDifferentBranchTypes() {
		DifferentBranchTypesImpl differentBranchTypes = new DifferentBranchTypesImpl();
		return differentBranchTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentToReadonlyFeature createAssignmentToReadonlyFeature() {
		AssignmentToReadonlyFeatureImpl assignmentToReadonlyFeature = new AssignmentToReadonlyFeatureImpl();
		return assignmentToReadonlyFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoBindingForCompulsoryFeature createNoBindingForCompulsoryFeature() {
		NoBindingForCompulsoryFeatureImpl noBindingForCompulsoryFeature = new NoBindingForCompulsoryFeatureImpl();
		return noBindingForCompulsoryFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidAssignmentImperativeBinding createInvalidAssignmentImperativeBinding() {
		InvalidAssignmentImperativeBindingImpl invalidAssignmentImperativeBinding = new InvalidAssignmentImperativeBindingImpl();
		return invalidAssignmentImperativeBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingExpectedOneAssignedMany createBindingExpectedOneAssignedMany() {
		BindingExpectedOneAssignedManyImpl bindingExpectedOneAssignedMany = new BindingExpectedOneAssignedManyImpl();
		return bindingExpectedOneAssignedMany;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveBindingButObjectAssigned createPrimitiveBindingButObjectAssigned() {
		PrimitiveBindingButObjectAssignedImpl primitiveBindingButObjectAssigned = new PrimitiveBindingButObjectAssignedImpl();
		return primitiveBindingButObjectAssigned;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectBindingButPrimitiveAssigned createObjectBindingButPrimitiveAssigned() {
		ObjectBindingButPrimitiveAssignedImpl objectBindingButPrimitiveAssigned = new ObjectBindingButPrimitiveAssignedImpl();
		return objectBindingButPrimitiveAssigned;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveBindingInvalidAssignment createPrimitiveBindingInvalidAssignment() {
		PrimitiveBindingInvalidAssignmentImpl primitiveBindingInvalidAssignment = new PrimitiveBindingInvalidAssignmentImpl();
		return primitiveBindingInvalidAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingWithoutRule createBindingWithoutRule() {
		BindingWithoutRuleImpl bindingWithoutRule = new BindingWithoutRuleImpl();
		return bindingWithoutRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingWithResolvedByIncompatibleRule createBindingWithResolvedByIncompatibleRule() {
		BindingWithResolvedByIncompatibleRuleImpl bindingWithResolvedByIncompatibleRule = new BindingWithResolvedByIncompatibleRuleImpl();
		return bindingWithResolvedByIncompatibleRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingPossiblyUnresolved createBindingPossiblyUnresolved() {
		BindingPossiblyUnresolvedImpl bindingPossiblyUnresolved = new BindingPossiblyUnresolvedImpl();
		return bindingPossiblyUnresolved;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResolvedRuleInfo createResolvedRuleInfo() {
		ResolvedRuleInfoImpl resolvedRuleInfo = new ResolvedRuleInfoImpl();
		return resolvedRuleInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResolveTempWithoutRule createResolveTempWithoutRule() {
		ResolveTempWithoutRuleImpl resolveTempWithoutRule = new ResolveTempWithoutRuleImpl();
		return resolveTempWithoutRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResolveTempPossiblyUnresolved createResolveTempPossiblyUnresolved() {
		ResolveTempPossiblyUnresolvedImpl resolveTempPossiblyUnresolved = new ResolveTempPossiblyUnresolvedImpl();
		return resolveTempPossiblyUnresolved;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResolveTempOutputPatternElementNotFound createResolveTempOutputPatternElementNotFound() {
		ResolveTempOutputPatternElementNotFoundImpl resolveTempOutputPatternElementNotFound = new ResolveTempOutputPatternElementNotFoundImpl();
		return resolveTempOutputPatternElementNotFound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlattenOverNonNestedCollection createFlattenOverNonNestedCollection() {
		FlattenOverNonNestedCollectionImpl flattenOverNonNestedCollection = new FlattenOverNonNestedCollectionImpl();
		return flattenOverNonNestedCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeSelectFirstForAny createChangeSelectFirstForAny() {
		ChangeSelectFirstForAnyImpl changeSelectFirstForAny = new ChangeSelectFirstForAnyImpl();
		return changeSelectFirstForAny;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IteratorOverEmptySequence createIteratorOverEmptySequence() {
		IteratorOverEmptySequenceImpl iteratorOverEmptySequence = new IteratorOverEmptySequenceImpl();
		return iteratorOverEmptySequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidOperatorUsage createInvalidOperatorUsage() {
		InvalidOperatorUsageImpl invalidOperatorUsage = new InvalidOperatorUsageImpl();
		return invalidOperatorUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadingTargetModel createReadingTargetModel() {
		ReadingTargetModelImpl readingTargetModel = new ReadingTargetModelImpl();
		return readingTargetModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WritingSourceModel createWritingSourceModel() {
		WritingSourceModelImpl writingSourceModel = new WritingSourceModelImpl();
		return writingSourceModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LazyRuleWithFilter createLazyRuleWithFilter() {
		LazyRuleWithFilterImpl lazyRuleWithFilter = new LazyRuleWithFilterImpl();
		return lazyRuleWithFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidRuleInheritance createInvalidRuleInheritance() {
		InvalidRuleInheritanceImpl invalidRuleInheritance = new InvalidRuleInheritanceImpl();
		return invalidRuleInheritance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AmbiguousTargetModelReference createAmbiguousTargetModelReference() {
		AmbiguousTargetModelReferenceImpl ambiguousTargetModelReference = new AmbiguousTargetModelReferenceImpl();
		return ambiguousTargetModelReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoModelFound createNoModelFound() {
		NoModelFoundImpl noModelFound = new NoModelFoundImpl();
		return noModelFound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoEnumLiteral createNoEnumLiteral() {
		NoEnumLiteralImpl noEnumLiteral = new NoEnumLiteralImpl();
		return noEnumLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IteratorBodyWrongType createIteratorBodyWrongType() {
		IteratorBodyWrongTypeImpl iteratorBodyWrongType = new IteratorBodyWrongTypeImpl();
		return iteratorBodyWrongType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatchedRuleWithoutOutputPattern createMatchedRuleWithoutOutputPattern() {
		MatchedRuleWithoutOutputPatternImpl matchedRuleWithoutOutputPattern = new MatchedRuleWithoutOutputPatternImpl();
		return matchedRuleWithoutOutputPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatchedRuleFilterNonBoolean createMatchedRuleFilterNonBoolean() {
		MatchedRuleFilterNonBooleanImpl matchedRuleFilterNonBoolean = new MatchedRuleFilterNonBooleanImpl();
		return matchedRuleFilterNonBoolean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpectedCollectionInForEach createExpectedCollectionInForEach() {
		ExpectedCollectionInForEachImpl expectedCollectionInForEach = new ExpectedCollectionInForEachImpl();
		return expectedCollectionInForEach;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoClassFoundInMetamodel createNoClassFoundInMetamodel() {
		NoClassFoundInMetamodelImpl noClassFoundInMetamodel = new NoClassFoundInMetamodelImpl();
		return noClassFoundInMetamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidArgument createInvalidArgument() {
		InvalidArgumentImpl invalidArgument = new InvalidArgumentImpl();
		return invalidArgument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionOperationNotFound createCollectionOperationNotFound() {
		CollectionOperationNotFoundImpl collectionOperationNotFound = new CollectionOperationNotFoundImpl();
		return collectionOperationNotFound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IteratorOverNoCollectionType createIteratorOverNoCollectionType() {
		IteratorOverNoCollectionTypeImpl iteratorOverNoCollectionType = new IteratorOverNoCollectionTypeImpl();
		return iteratorOverNoCollectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IncoherentVariableDeclaration createIncoherentVariableDeclaration() {
		IncoherentVariableDeclarationImpl incoherentVariableDeclaration = new IncoherentVariableDeclarationImpl();
		return incoherentVariableDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IncoherentHelperReturnType createIncoherentHelperReturnType() {
		IncoherentHelperReturnTypeImpl incoherentHelperReturnType = new IncoherentHelperReturnTypeImpl();
		return incoherentHelperReturnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationOverCollectionType createOperationOverCollectionType() {
		OperationOverCollectionTypeImpl operationOverCollectionType = new OperationOverCollectionTypeImpl();
		return operationOverCollectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessToUndefinedValue createAccessToUndefinedValue() {
		AccessToUndefinedValueImpl accessToUndefinedValue = new AccessToUndefinedValueImpl();
		return accessToUndefinedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessToUndefinedValue_ThroughEmptyCollection createAccessToUndefinedValue_ThroughEmptyCollection() {
		AccessToUndefinedValue_ThroughEmptyCollectionImpl accessToUndefinedValue_ThroughEmptyCollection = new AccessToUndefinedValue_ThroughEmptyCollectionImpl();
		return accessToUndefinedValue_ThroughEmptyCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleConflicts createRuleConflicts() {
		RuleConflictsImpl ruleConflicts = new RuleConflictsImpl();
		return ruleConflicts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingInplaceInvalid createBindingInplaceInvalid() {
		BindingInplaceInvalidImpl bindingInplaceInvalid = new BindingInplaceInvalidImpl();
		return bindingInplaceInvalid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CannotInstantiateAbstractClass createCannotInstantiateAbstractClass() {
		CannotInstantiateAbstractClassImpl cannotInstantiateAbstractClass = new CannotInstantiateAbstractClassImpl();
		return cannotInstantiateAbstractClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericLocalProblem createGenericLocalProblem() {
		GenericLocalProblemImpl genericLocalProblem = new GenericLocalProblemImpl();
		return genericLocalProblem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlParseError createAtlParseError() {
		AtlParseErrorImpl atlParseError = new AtlParseErrorImpl();
		return atlParseError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoBindingForCompulsoryFeatureKind createNoBindingForCompulsoryFeatureKindFromString(EDataType eDataType, String initialValue) {
		NoBindingForCompulsoryFeatureKind result = NoBindingForCompulsoryFeatureKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNoBindingForCompulsoryFeatureKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidRuleInheritanceKind createInvalidRuleInheritanceKindFromString(EDataType eDataType, String initialValue) {
		InvalidRuleInheritanceKind result = InvalidRuleInheritanceKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInvalidRuleInheritanceKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlErrorPackage getAtlErrorPackage() {
		return (AtlErrorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AtlErrorPackage getPackage() {
		return AtlErrorPackage.eINSTANCE;
	}

} //AtlErrorFactoryImpl
