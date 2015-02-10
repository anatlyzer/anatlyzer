/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.*;

import org.eclipse.emf.ecore.EClass;
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
			case AtlErrorPackage.COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR: return createCollectionOperationOverNoCollectionError();
			case AtlErrorPackage.FEATURE_ACCESS_IN_COLLECTION: return createFeatureAccessInCollection();
			case AtlErrorPackage.FEATURE_NOT_FOUND: return createFeatureNotFound();
			case AtlErrorPackage.OPERATION_NOT_FOUND: return createOperationNotFound();
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE: return createOperationNotFoundInThisModule();
			case AtlErrorPackage.ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE: return createAttributeNotFoundInThisModule();
			case AtlErrorPackage.FEATURE_NOT_FOUND_IN_UNION_TYPE: return createFeatureNotFoundInUnionType();
			case AtlErrorPackage.INVALID_OPERAND: return createInvalidOperand();
			case AtlErrorPackage.NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE: return createNoContainerForRefImmediateComposite();
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES: return createDifferentBranchTypes();
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE: return createNoBindingForCompulsoryFeature();
			case AtlErrorPackage.BINDING_EXPECTED_ONE_ASSIGNED_MANY: return createBindingExpectedOneAssignedMany();
			case AtlErrorPackage.BINDING_WITHOUT_RULE: return createBindingWithoutRule();
			case AtlErrorPackage.BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE: return createBindingWithResolvedByIncompatibleRule();
			case AtlErrorPackage.BINDING_POSSIBLY_UNRESOLVED: return createBindingPossiblyUnresolved();
			case AtlErrorPackage.RESOLVED_RULE_INFO: return createResolvedRuleInfo();
			case AtlErrorPackage.RESOLVE_TEMP_WITHOUT_RULE: return createResolveTempWithoutRule();
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND: return createResolveTempOutputPatternElementNotFound();
			case AtlErrorPackage.FLATTEN_OVER_NON_NESTED_COLLECTION: return createFlattenOverNonNestedCollection();
			case AtlErrorPackage.ITERATOR_OVER_EMPTY_SEQUENCE: return createIteratorOverEmptySequence();
			case AtlErrorPackage.READING_TARGET_MODEL: return createReadingTargetModel();
			case AtlErrorPackage.AMBIGUOUS_TARGET_MODEL_REFERENCE: return createAmbiguousTargetModelReference();
			case AtlErrorPackage.NO_MODEL_FOUND: return createNoModelFound();
			case AtlErrorPackage.ITERATOR_BODY_WRONG_TYPE: return createIteratorBodyWrongType();
			case AtlErrorPackage.MATCHED_RULE_WITHOUT_OUTPUT_PATTERN: return createMatchedRuleWithoutOutputPattern();
			case AtlErrorPackage.EXPECTED_COLLECTION_IN_FOR_EACH: return createExpectedCollectionInForEach();
			case AtlErrorPackage.NO_CLASS_FOUND_IN_METAMODEL: return createNoClassFoundInMetamodel();
			case AtlErrorPackage.INVALID_ARGUMENT: return createInvalidArgument();
			case AtlErrorPackage.COLLECTION_OPERATION_NOT_FOUND: return createCollectionOperationNotFound();
			case AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE: return createIteratorOverNoCollectionType();
			case AtlErrorPackage.INCOHERENT_VARIABLE_DECLARATION: return createIncoherentVariableDeclaration();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
	public OperationNotFound createOperationNotFound() {
		OperationNotFoundImpl operationNotFound = new OperationNotFoundImpl();
		return operationNotFound;
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
	public NoBindingForCompulsoryFeature createNoBindingForCompulsoryFeature() {
		NoBindingForCompulsoryFeatureImpl noBindingForCompulsoryFeature = new NoBindingForCompulsoryFeatureImpl();
		return noBindingForCompulsoryFeature;
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
	public IteratorOverEmptySequence createIteratorOverEmptySequence() {
		IteratorOverEmptySequenceImpl iteratorOverEmptySequence = new IteratorOverEmptySequenceImpl();
		return iteratorOverEmptySequence;
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
