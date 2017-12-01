/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.errors.AnalysisResultPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_error.AtlErrorFactory
 * @model kind="package"
 * @generated
 */
public interface AtlErrorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "atl_error";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://bento/analysis/atl/analysis/errors";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_error";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AtlErrorPackage eINSTANCE = anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.LocalProblemImpl <em>Local Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.LocalProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getLocalProblem()
	 * @generated
	 */
	int LOCAL_PROBLEM = 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__DEPENDENTS = AnalysisResultPackage.PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__DESCRIPTION = AnalysisResultPackage.PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__SEVERITY = AnalysisResultPackage.PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__NEEDS_CSP = AnalysisResultPackage.PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__STATUS = AnalysisResultPackage.PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__IGNORED_BY_USER = AnalysisResultPackage.PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__DATA = AnalysisResultPackage.PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__LOCATION = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__FILE_LOCATION = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__ELEMENT = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__DISPLAYED_ELEMENT = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__MISSING = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM__RECOVERY = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Local Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM_FEATURE_COUNT = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Local Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_PROBLEM_OPERATION_COUNT = AnalysisResultPackage.PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ModelElementImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Klass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__KLASS = 0;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__METAMODEL_NAME = 1;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.RuntimeErrorImpl <em>Runtime Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.RuntimeErrorImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getRuntimeError()
	 * @generated
	 */
	int RUNTIME_ERROR = 2;

	/**
	 * The number of structural features of the '<em>Runtime Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_ERROR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Runtime Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_ERROR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.WarningImpl <em>Warning</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.WarningImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getWarning()
	 * @generated
	 */
	int WARNING = 3;

	/**
	 * The number of structural features of the '<em>Warning</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WARNING_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Warning</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WARNING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.StyleHintImpl <em>Style Hint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.StyleHintImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getStyleHint()
	 * @generated
	 */
	int STYLE_HINT = 4;

	/**
	 * The number of structural features of the '<em>Style Hint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_HINT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Style Hint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_HINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.PerformanceHintImpl <em>Performance Hint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.PerformanceHintImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getPerformanceHint()
	 * @generated
	 */
	int PERFORMANCE_HINT = 5;

	/**
	 * The number of structural features of the '<em>Performance Hint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_HINT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Performance Hint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_HINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.NavigationProblemImpl <em>Navigation Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.NavigationProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNavigationProblem()
	 * @generated
	 */
	int NAVIGATION_PROBLEM = 8;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidArgumentProblemImpl <em>Invalid Argument Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.InvalidArgumentProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidArgumentProblem()
	 * @generated
	 */
	int INVALID_ARGUMENT_PROBLEM = 9;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.TargetModelConformanceProblemImpl <em>Target Model Conformance Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.TargetModelConformanceProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getTargetModelConformanceProblem()
	 * @generated
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM = 10;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.CollectionOperationOverNoCollectionErrorImpl <em>Collection Operation Over No Collection Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.CollectionOperationOverNoCollectionErrorImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getCollectionOperationOverNoCollectionError()
	 * @generated
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR = 28;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureAccessInCollectionImpl <em>Feature Access In Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.FeatureAccessInCollectionImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureAccessInCollection()
	 * @generated
	 */
	int FEATURE_ACCESS_IN_COLLECTION = 29;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl <em>Feature Not Found</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureNotFound()
	 * @generated
	 */
	int FEATURE_NOT_FOUND = 16;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationNotFoundImpl <em>Operation Not Found</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OperationNotFoundImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationNotFound()
	 * @generated
	 */
	int OPERATION_NOT_FOUND = 17;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationNotFoundInThisModuleImpl <em>Operation Not Found In This Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OperationNotFoundInThisModuleImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationNotFoundInThisModule()
	 * @generated
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE = 34;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.AttributeNotFoundInThisModuleImpl <em>Attribute Not Found In This Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.AttributeNotFoundInThisModuleImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAttributeNotFoundInThisModule()
	 * @generated
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE = 35;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundInUnionTypeImpl <em>Feature Not Found In Union Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundInUnionTypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureNotFoundInUnionType()
	 * @generated
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE = 36;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperandImpl <em>Invalid Operand</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.InvalidOperandImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidOperand()
	 * @generated
	 */
	int INVALID_OPERAND = 38;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.NoContainerForRefImmediateCompositeImpl <em>No Container For Ref Immediate Composite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.NoContainerForRefImmediateCompositeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoContainerForRefImmediateComposite()
	 * @generated
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE = 39;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.DifferentBranchTypesImpl <em>Different Branch Types</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.DifferentBranchTypesImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getDifferentBranchTypes()
	 * @generated
	 */
	int DIFFERENT_BRANCH_TYPES = 40;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingProblemImpl <em>Binding Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BindingProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingProblem()
	 * @generated
	 */
	int BINDING_PROBLEM = 41;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempProblemImpl <em>Resolve Temp Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempProblem()
	 * @generated
	 */
	int RESOLVE_TEMP_PROBLEM = 42;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl <em>No Binding For Compulsory Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoBindingForCompulsoryFeature()
	 * @generated
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE = 11;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingExpectedOneAssignedManyImpl <em>Binding Expected One Assigned Many</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BindingExpectedOneAssignedManyImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingExpectedOneAssignedMany()
	 * @generated
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY = 44;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingResolutionImpl <em>Binding Resolution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BindingResolutionImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingResolution()
	 * @generated
	 */
	int BINDING_RESOLUTION = 48;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingWithoutRuleImpl <em>Binding Without Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BindingWithoutRuleImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingWithoutRule()
	 * @generated
	 */
	int BINDING_WITHOUT_RULE = 14;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingWithResolvedByIncompatibleRuleImpl <em>Binding With Resolved By Incompatible Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BindingWithResolvedByIncompatibleRuleImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingWithResolvedByIncompatibleRule()
	 * @generated
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE = 15;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingPossiblyUnresolvedImpl <em>Binding Possibly Unresolved</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BindingPossiblyUnresolvedImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingPossiblyUnresolved()
	 * @generated
	 */
	int BINDING_POSSIBLY_UNRESOLVED = 13;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl <em>Resolved Rule Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolvedRuleInfo()
	 * @generated
	 */
	int RESOLVED_RULE_INFO = 49;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempWithoutRuleImpl <em>Resolve Temp Without Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempWithoutRuleImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempWithoutRule()
	 * @generated
	 */
	int RESOLVE_TEMP_WITHOUT_RULE = 50;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempOutputPatternElementNotFoundImpl <em>Resolve Temp Output Pattern Element Not Found</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempOutputPatternElementNotFoundImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempOutputPatternElementNotFound()
	 * @generated
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND = 52;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.FlattenOverNonNestedCollectionImpl <em>Flatten Over Non Nested Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.FlattenOverNonNestedCollectionImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFlattenOverNonNestedCollection()
	 * @generated
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION = 53;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.IteratorOverEmptySequenceImpl <em>Iterator Over Empty Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.IteratorOverEmptySequenceImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIteratorOverEmptySequence()
	 * @generated
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE = 55;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ReadingTargetModelImpl <em>Reading Target Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ReadingTargetModelImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getReadingTargetModel()
	 * @generated
	 */
	int READING_TARGET_MODEL = 57;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.AmbiguousTargetModelReferenceImpl <em>Ambiguous Target Model Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.AmbiguousTargetModelReferenceImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAmbiguousTargetModelReference()
	 * @generated
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE = 61;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.NoModelFoundImpl <em>No Model Found</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.NoModelFoundImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoModelFound()
	 * @generated
	 */
	int NO_MODEL_FOUND = 62;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.WrongTypeImpl <em>Wrong Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.WrongTypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getWrongType()
	 * @generated
	 */
	int WRONG_TYPE = 64;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.IteratorBodyWrongTypeImpl <em>Iterator Body Wrong Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.IteratorBodyWrongTypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIteratorBodyWrongType()
	 * @generated
	 */
	int ITERATOR_BODY_WRONG_TYPE = 65;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.MatchedRuleWithoutOutputPatternImpl <em>Matched Rule Without Output Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.MatchedRuleWithoutOutputPatternImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getMatchedRuleWithoutOutputPattern()
	 * @generated
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN = 66;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ExpectedCollectionInForEachImpl <em>Expected Collection In For Each</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ExpectedCollectionInForEachImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getExpectedCollectionInForEach()
	 * @generated
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH = 68;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.NoClassFoundInMetamodelImpl <em>No Class Found In Metamodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.NoClassFoundInMetamodelImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoClassFoundInMetamodel()
	 * @generated
	 */
	int NO_CLASS_FOUND_IN_METAMODEL = 69;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidArgumentImpl <em>Invalid Argument</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.InvalidArgumentImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidArgument()
	 * @generated
	 */
	int INVALID_ARGUMENT = 70;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.CollectionOperationNotFoundImpl <em>Collection Operation Not Found</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.CollectionOperationNotFoundImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getCollectionOperationNotFound()
	 * @generated
	 */
	int COLLECTION_OPERATION_NOT_FOUND = 71;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.IteratorOverNoCollectionTypeImpl <em>Iterator Over No Collection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.IteratorOverNoCollectionTypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIteratorOverNoCollectionType()
	 * @generated
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE = 72;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OclComplianceImpl <em>Ocl Compliance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OclComplianceImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOclCompliance()
	 * @generated
	 */
	int OCL_COMPLIANCE = 6;

	/**
	 * The number of structural features of the '<em>Ocl Compliance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Ocl Compliance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OclComplianceProblemImpl <em>Ocl Compliance Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OclComplianceProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOclComplianceProblem()
	 * @generated
	 */
	int OCL_COMPLIANCE_PROBLEM = 7;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Ocl Compliance Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Ocl Compliance Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_COMPLIANCE_PROBLEM_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Navigation Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Navigation Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PROBLEM_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Invalid Argument Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Invalid Argument Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_PROBLEM_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Target Model Conformance Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Target Model Conformance Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_MODEL_CONFORMANCE_PROBLEM_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ConflictingRuleSetImpl <em>Conflicting Rule Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ConflictingRuleSetImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getConflictingRuleSet()
	 * @generated
	 */
	int CONFLICTING_RULE_SET = 24;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureFoundInSubtypeImpl <em>Feature Found In Subtype</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.FeatureFoundInSubtypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureFoundInSubtype()
	 * @generated
	 */
	int FEATURE_FOUND_IN_SUBTYPE = 20;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl <em>Operation Call Invalid</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationCallInvalid()
	 * @generated
	 */
	int OPERATION_CALL_INVALID = 31;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationFoundInSubtypeImpl <em>Operation Found In Subtype</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OperationFoundInSubtypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationFoundInSubtype()
	 * @generated
	 */
	int OPERATION_FOUND_IN_SUBTYPE = 18;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidNumberOfParametersImpl <em>Operation Call Invalid Number Of Parameters</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidNumberOfParametersImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationCallInvalidNumberOfParameters()
	 * @generated
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS = 32;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidParameterImpl <em>Operation Call Invalid Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidParameterImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationCallInvalidParameter()
	 * @generated
	 */
	int OPERATION_CALL_INVALID_PARAMETER = 33;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperatorImpl <em>Invalid Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.InvalidOperatorImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidOperator()
	 * @generated
	 */
	int INVALID_OPERATOR = 37;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingButObjectAssignedImpl <em>Primitive Binding But Object Assigned</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingButObjectAssignedImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getPrimitiveBindingButObjectAssigned()
	 * @generated
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED = 45;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ObjectBindingButPrimitiveAssignedImpl <em>Object Binding But Primitive Assigned</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ObjectBindingButPrimitiveAssignedImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getObjectBindingButPrimitiveAssigned()
	 * @generated
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED = 46;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingInvalidAssignmentImpl <em>Primitive Binding Invalid Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingInvalidAssignmentImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getPrimitiveBindingInvalidAssignment()
	 * @generated
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT = 47;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.LazyRuleWithFilterImpl <em>Lazy Rule With Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.LazyRuleWithFilterImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getLazyRuleWithFilter()
	 * @generated
	 */
	int LAZY_RULE_WITH_FILTER = 59;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.AbstractIncoherentVariableDeclarationImpl <em>Abstract Incoherent Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.AbstractIncoherentVariableDeclarationImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAbstractIncoherentVariableDeclaration()
	 * @generated
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION = 73;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.IncoherentVariableDeclarationImpl <em>Incoherent Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.IncoherentVariableDeclarationImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIncoherentVariableDeclaration()
	 * @generated
	 */
	int INCOHERENT_VARIABLE_DECLARATION = 74;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.IncoherentHelperReturnTypeImpl <em>Incoherent Helper Return Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.IncoherentHelperReturnTypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIncoherentHelperReturnType()
	 * @generated
	 */
	int INCOHERENT_HELPER_RETURN_TYPE = 75;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationOverCollectionTypeImpl <em>Operation Over Collection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.OperationOverCollectionTypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationOverCollectionType()
	 * @generated
	 */
	int OPERATION_OVER_COLLECTION_TYPE = 30;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.FoundInSubtypeImpl <em>Found In Subtype</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.FoundInSubtypeImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFoundInSubtype()
	 * @generated
	 */
	int FOUND_IN_SUBTYPE = 19;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__DEPENDENTS = TARGET_MODEL_CONFORMANCE_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__DESCRIPTION = TARGET_MODEL_CONFORMANCE_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__SEVERITY = TARGET_MODEL_CONFORMANCE_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__NEEDS_CSP = TARGET_MODEL_CONFORMANCE_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__STATUS = TARGET_MODEL_CONFORMANCE_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__IGNORED_BY_USER = TARGET_MODEL_CONFORMANCE_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__DATA = TARGET_MODEL_CONFORMANCE_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__LOCATION = TARGET_MODEL_CONFORMANCE_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__FILE_LOCATION = TARGET_MODEL_CONFORMANCE_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__ELEMENT = TARGET_MODEL_CONFORMANCE_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__DISPLAYED_ELEMENT = TARGET_MODEL_CONFORMANCE_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__MISSING = TARGET_MODEL_CONFORMANCE_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__RECOVERY = TARGET_MODEL_CONFORMANCE_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE = TARGET_MODEL_CONFORMANCE_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME = TARGET_MODEL_CONFORMANCE_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subrule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE = TARGET_MODEL_CONFORMANCE_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE__KIND = TARGET_MODEL_CONFORMANCE_PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>No Binding For Compulsory Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE_FEATURE_COUNT = TARGET_MODEL_CONFORMANCE_PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>No Binding For Compulsory Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE_OPERATION_COUNT = TARGET_MODEL_CONFORMANCE_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__FEATURE = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM__FEATURE_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Binding Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Binding Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_PROBLEM_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidAssignmentImperativeBindingImpl <em>Invalid Assignment Imperative Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.InvalidAssignmentImperativeBindingImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidAssignmentImperativeBinding()
	 * @generated
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING = 12;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The number of structural features of the '<em>Invalid Assignment Imperative Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Invalid Assignment Imperative Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ASSIGNMENT_IMPERATIVE_BINDING_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The feature id for the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__RIGHT_TYPE = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__TARGET_TYPE = BINDING_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__RIGHT = BINDING_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__LEFT = BINDING_PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__RULES = BINDING_PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Problematic Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES = BINDING_PROBLEM_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Problematic Classes Implicit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES_IMPLICIT = BINDING_PROBLEM_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Binding Possibly Unresolved</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Binding Possibly Unresolved</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_POSSIBLY_UNRESOLVED_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The feature id for the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__RIGHT_TYPE = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__TARGET_TYPE = BINDING_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__RIGHT = BINDING_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__LEFT = BINDING_PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE__RULES = BINDING_PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Binding Without Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Binding Without Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITHOUT_RULE_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The feature id for the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__RIGHT_TYPE = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__TARGET_TYPE = BINDING_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__RIGHT = BINDING_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__LEFT = BINDING_PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE__RULES = BINDING_PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Binding With Resolved By Incompatible Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Binding With Resolved By Incompatible Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__FEATURE_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__TYPE = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__CLASS_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND__METAMODEL_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Feature Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Feature Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__OPERATION_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__TYPE = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__CLASS_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__METAMODEL_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__FORMAL_PARAMETERS = NAVIGATION_PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID__ACTUAL_PARAMETERS = NAVIGATION_PROBLEM_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Operation Call Invalid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Operation Call Invalid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__DEPENDENTS = OPERATION_CALL_INVALID__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__DESCRIPTION = OPERATION_CALL_INVALID__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__SEVERITY = OPERATION_CALL_INVALID__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__NEEDS_CSP = OPERATION_CALL_INVALID__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__STATUS = OPERATION_CALL_INVALID__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__IGNORED_BY_USER = OPERATION_CALL_INVALID__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__DATA = OPERATION_CALL_INVALID__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__LOCATION = OPERATION_CALL_INVALID__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__FILE_LOCATION = OPERATION_CALL_INVALID__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__ELEMENT = OPERATION_CALL_INVALID__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__DISPLAYED_ELEMENT = OPERATION_CALL_INVALID__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__MISSING = OPERATION_CALL_INVALID__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__RECOVERY = OPERATION_CALL_INVALID__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__OPERATION_NAME = OPERATION_CALL_INVALID__OPERATION_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__TYPE = OPERATION_CALL_INVALID__TYPE;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__CLASS_NAME = OPERATION_CALL_INVALID__CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__METAMODEL_NAME = OPERATION_CALL_INVALID__METAMODEL_NAME;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__FORMAL_PARAMETERS = OPERATION_CALL_INVALID__FORMAL_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND__ACTUAL_PARAMETERS = OPERATION_CALL_INVALID__ACTUAL_PARAMETERS;

	/**
	 * The number of structural features of the '<em>Operation Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_FEATURE_COUNT = OPERATION_CALL_INVALID_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Operation Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_OPERATION_COUNT = OPERATION_CALL_INVALID_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__DEPENDENTS = OPERATION_NOT_FOUND__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__DESCRIPTION = OPERATION_NOT_FOUND__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__SEVERITY = OPERATION_NOT_FOUND__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__NEEDS_CSP = OPERATION_NOT_FOUND__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__STATUS = OPERATION_NOT_FOUND__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__IGNORED_BY_USER = OPERATION_NOT_FOUND__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__DATA = OPERATION_NOT_FOUND__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__LOCATION = OPERATION_NOT_FOUND__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__FILE_LOCATION = OPERATION_NOT_FOUND__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__ELEMENT = OPERATION_NOT_FOUND__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__DISPLAYED_ELEMENT = OPERATION_NOT_FOUND__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__MISSING = OPERATION_NOT_FOUND__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__RECOVERY = OPERATION_NOT_FOUND__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__OPERATION_NAME = OPERATION_NOT_FOUND__OPERATION_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__TYPE = OPERATION_NOT_FOUND__TYPE;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__CLASS_NAME = OPERATION_NOT_FOUND__CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__METAMODEL_NAME = OPERATION_NOT_FOUND__METAMODEL_NAME;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__FORMAL_PARAMETERS = OPERATION_NOT_FOUND__FORMAL_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__ACTUAL_PARAMETERS = OPERATION_NOT_FOUND__ACTUAL_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Possible Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__POSSIBLE_CLASSES = OPERATION_NOT_FOUND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Missing Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE__MISSING_CLASSES = OPERATION_NOT_FOUND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Found In Subtype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE_FEATURE_COUNT = OPERATION_NOT_FOUND_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Operation Found In Subtype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FOUND_IN_SUBTYPE_OPERATION_COUNT = OPERATION_NOT_FOUND_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Possible Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOUND_IN_SUBTYPE__POSSIBLE_CLASSES = 0;

	/**
	 * The feature id for the '<em><b>Missing Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOUND_IN_SUBTYPE__MISSING_CLASSES = 1;

	/**
	 * The number of structural features of the '<em>Found In Subtype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOUND_IN_SUBTYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Found In Subtype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOUND_IN_SUBTYPE_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__DEPENDENTS = FEATURE_NOT_FOUND__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__DESCRIPTION = FEATURE_NOT_FOUND__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__SEVERITY = FEATURE_NOT_FOUND__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__NEEDS_CSP = FEATURE_NOT_FOUND__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__STATUS = FEATURE_NOT_FOUND__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__IGNORED_BY_USER = FEATURE_NOT_FOUND__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__DATA = FEATURE_NOT_FOUND__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__LOCATION = FEATURE_NOT_FOUND__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__FILE_LOCATION = FEATURE_NOT_FOUND__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__ELEMENT = FEATURE_NOT_FOUND__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__DISPLAYED_ELEMENT = FEATURE_NOT_FOUND__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__MISSING = FEATURE_NOT_FOUND__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__RECOVERY = FEATURE_NOT_FOUND__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__FEATURE_NAME = FEATURE_NOT_FOUND__FEATURE_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__TYPE = FEATURE_NOT_FOUND__TYPE;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__CLASS_NAME = FEATURE_NOT_FOUND__CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__METAMODEL_NAME = FEATURE_NOT_FOUND__METAMODEL_NAME;

	/**
	 * The feature id for the '<em><b>Possible Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__POSSIBLE_CLASSES = FEATURE_NOT_FOUND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Missing Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE__MISSING_CLASSES = FEATURE_NOT_FOUND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Feature Found In Subtype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE_FEATURE_COUNT = FEATURE_NOT_FOUND_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Feature Found In Subtype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBTYPE_OPERATION_COUNT = FEATURE_NOT_FOUND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ChangeSelectFirstForAnyImpl <em>Change Select First For Any</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ChangeSelectFirstForAnyImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getChangeSelectFirstForAny()
	 * @generated
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY = 54;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.NoEnumLiteralImpl <em>No Enum Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.NoEnumLiteralImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoEnumLiteral()
	 * @generated
	 */
	int NO_ENUM_LITERAL = 63;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValueImpl <em>Access To Undefined Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValueImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAccessToUndefinedValue()
	 * @generated
	 */
	int ACCESS_TO_UNDEFINED_VALUE = 21;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Access To Undefined Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Access To Undefined Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValue_ThroughEmptyCollectionImpl <em>Access To Undefined Value Through Empty Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValue_ThroughEmptyCollectionImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAccessToUndefinedValue_ThroughEmptyCollection()
	 * @generated
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION = 22;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Access To Undefined Value Through Empty Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Access To Undefined Value Through Empty Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.RuleConflictsImpl <em>Rule Conflicts</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.RuleConflictsImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getRuleConflicts()
	 * @generated
	 */
	int RULE_CONFLICTS = 23;

	/**
	 * The feature id for the '<em><b>Conflicts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CONFLICTS__CONFLICTS = AnalysisResultPackage.BATCH_ANALYSIS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rule Conflicts</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CONFLICTS_FEATURE_COUNT = AnalysisResultPackage.BATCH_ANALYSIS_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Rule Conflicts</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CONFLICTS_OPERATION_COUNT = AnalysisResultPackage.BATCH_ANALYSIS_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__DEPENDENTS = AnalysisResultPackage.PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__DESCRIPTION = AnalysisResultPackage.PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__SEVERITY = AnalysisResultPackage.PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__NEEDS_CSP = AnalysisResultPackage.PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__STATUS = AnalysisResultPackage.PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__IGNORED_BY_USER = AnalysisResultPackage.PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__DATA = AnalysisResultPackage.PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__TYPES = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__RULES = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Analyser Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET__ANALYSER_INFO = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Conflicting Rule Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET_FEATURE_COUNT = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Conflicting Rule Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTING_RULE_SET_OPERATION_COUNT = AnalysisResultPackage.PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.TargetConformanceViolationsImpl <em>Target Conformance Violations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.TargetConformanceViolationsImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getTargetConformanceViolations()
	 * @generated
	 */
	int TARGET_CONFORMANCE_VIOLATIONS = 25;

	/**
	 * The feature id for the '<em><b>Target Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES = AnalysisResultPackage.BATCH_ANALYSIS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Target Conformance Violations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_CONFORMANCE_VIOLATIONS_FEATURE_COUNT = AnalysisResultPackage.BATCH_ANALYSIS_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Target Conformance Violations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_CONFORMANCE_VIOLATIONS_OPERATION_COUNT = AnalysisResultPackage.BATCH_ANALYSIS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BatchTargetConformanceIssueImpl <em>Batch Target Conformance Issue</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BatchTargetConformanceIssueImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBatchTargetConformanceIssue()
	 * @generated
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE = 26;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE__DEPENDENTS = AnalysisResultPackage.PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE__DESCRIPTION = AnalysisResultPackage.PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE__SEVERITY = AnalysisResultPackage.PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE__NEEDS_CSP = AnalysisResultPackage.PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE__STATUS = AnalysisResultPackage.PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE__IGNORED_BY_USER = AnalysisResultPackage.PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE__DATA = AnalysisResultPackage.PROBLEM__DATA;

	/**
	 * The number of structural features of the '<em>Batch Target Conformance Issue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE_FEATURE_COUNT = AnalysisResultPackage.PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Batch Target Conformance Issue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_TARGET_CONFORMANCE_ISSUE_OPERATION_COUNT = AnalysisResultPackage.PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.TargetInvariantViolationImpl <em>Target Invariant Violation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.TargetInvariantViolationImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getTargetInvariantViolation()
	 * @generated
	 */
	int TARGET_INVARIANT_VIOLATION = 27;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__DEPENDENTS = BATCH_TARGET_CONFORMANCE_ISSUE__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__DESCRIPTION = BATCH_TARGET_CONFORMANCE_ISSUE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__SEVERITY = BATCH_TARGET_CONFORMANCE_ISSUE__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__NEEDS_CSP = BATCH_TARGET_CONFORMANCE_ISSUE__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__STATUS = BATCH_TARGET_CONFORMANCE_ISSUE__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__IGNORED_BY_USER = BATCH_TARGET_CONFORMANCE_ISSUE__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__DATA = BATCH_TARGET_CONFORMANCE_ISSUE__DATA;

	/**
	 * The feature id for the '<em><b>Invariant Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION__INVARIANT_NAME = BATCH_TARGET_CONFORMANCE_ISSUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Target Invariant Violation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION_FEATURE_COUNT = BATCH_TARGET_CONFORMANCE_ISSUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Target Invariant Violation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_INVARIANT_VIOLATION_OPERATION_COUNT = BATCH_TARGET_CONFORMANCE_ISSUE_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Collection Operation Over No Collection Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Collection Operation Over No Collection Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION__FEATURE_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature Access In Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Feature Access In Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_ACCESS_IN_COLLECTION_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__DEPENDENTS = OCL_COMPLIANCE_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__DESCRIPTION = OCL_COMPLIANCE_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__SEVERITY = OCL_COMPLIANCE_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__NEEDS_CSP = OCL_COMPLIANCE_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__STATUS = OCL_COMPLIANCE_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__IGNORED_BY_USER = OCL_COMPLIANCE_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__DATA = OCL_COMPLIANCE_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__LOCATION = OCL_COMPLIANCE_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__FILE_LOCATION = OCL_COMPLIANCE_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__ELEMENT = OCL_COMPLIANCE_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__DISPLAYED_ELEMENT = OCL_COMPLIANCE_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__MISSING = OCL_COMPLIANCE_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE__RECOVERY = OCL_COMPLIANCE_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Operation Over Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE_FEATURE_COUNT = OCL_COMPLIANCE_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Operation Over Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OVER_COLLECTION_TYPE_OPERATION_COUNT = OCL_COMPLIANCE_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__DEPENDENTS = OPERATION_CALL_INVALID__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__DESCRIPTION = OPERATION_CALL_INVALID__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__SEVERITY = OPERATION_CALL_INVALID__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__NEEDS_CSP = OPERATION_CALL_INVALID__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__STATUS = OPERATION_CALL_INVALID__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__IGNORED_BY_USER = OPERATION_CALL_INVALID__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__DATA = OPERATION_CALL_INVALID__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__LOCATION = OPERATION_CALL_INVALID__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__FILE_LOCATION = OPERATION_CALL_INVALID__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__ELEMENT = OPERATION_CALL_INVALID__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__DISPLAYED_ELEMENT = OPERATION_CALL_INVALID__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__MISSING = OPERATION_CALL_INVALID__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__RECOVERY = OPERATION_CALL_INVALID__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__OPERATION_NAME = OPERATION_CALL_INVALID__OPERATION_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__TYPE = OPERATION_CALL_INVALID__TYPE;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__CLASS_NAME = OPERATION_CALL_INVALID__CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__METAMODEL_NAME = OPERATION_CALL_INVALID__METAMODEL_NAME;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__FORMAL_PARAMETERS = OPERATION_CALL_INVALID__FORMAL_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS__ACTUAL_PARAMETERS = OPERATION_CALL_INVALID__ACTUAL_PARAMETERS;

	/**
	 * The number of structural features of the '<em>Operation Call Invalid Number Of Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS_FEATURE_COUNT = OPERATION_CALL_INVALID_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Operation Call Invalid Number Of Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS_OPERATION_COUNT = OPERATION_CALL_INVALID_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__DEPENDENTS = OPERATION_CALL_INVALID__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__DESCRIPTION = OPERATION_CALL_INVALID__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__SEVERITY = OPERATION_CALL_INVALID__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__NEEDS_CSP = OPERATION_CALL_INVALID__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__STATUS = OPERATION_CALL_INVALID__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__IGNORED_BY_USER = OPERATION_CALL_INVALID__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__DATA = OPERATION_CALL_INVALID__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__LOCATION = OPERATION_CALL_INVALID__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__FILE_LOCATION = OPERATION_CALL_INVALID__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__ELEMENT = OPERATION_CALL_INVALID__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__DISPLAYED_ELEMENT = OPERATION_CALL_INVALID__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__MISSING = OPERATION_CALL_INVALID__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__RECOVERY = OPERATION_CALL_INVALID__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__OPERATION_NAME = OPERATION_CALL_INVALID__OPERATION_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__TYPE = OPERATION_CALL_INVALID__TYPE;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__CLASS_NAME = OPERATION_CALL_INVALID__CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__METAMODEL_NAME = OPERATION_CALL_INVALID__METAMODEL_NAME;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__FORMAL_PARAMETERS = OPERATION_CALL_INVALID__FORMAL_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__ACTUAL_PARAMETERS = OPERATION_CALL_INVALID__ACTUAL_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Blamed Parameter Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER__BLAMED_PARAMETER_NAMES = OPERATION_CALL_INVALID_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Call Invalid Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER_FEATURE_COUNT = OPERATION_CALL_INVALID_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Operation Call Invalid Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_INVALID_PARAMETER_OPERATION_COUNT = OPERATION_CALL_INVALID_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Maybe Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE__MAYBE_RULE = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Not Found In This Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Operation Not Found In This Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NOT_FOUND_IN_THIS_MODULE_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute Not Found In This Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute Not Found In This Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE__FEATURE_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature Not Found In Union Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Feature Not Found In Union Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NOT_FOUND_IN_UNION_TYPE_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__DEPENDENTS = INVALID_ARGUMENT_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__DESCRIPTION = INVALID_ARGUMENT_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__SEVERITY = INVALID_ARGUMENT_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__NEEDS_CSP = INVALID_ARGUMENT_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__STATUS = INVALID_ARGUMENT_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__IGNORED_BY_USER = INVALID_ARGUMENT_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__DATA = INVALID_ARGUMENT_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__LOCATION = INVALID_ARGUMENT_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__FILE_LOCATION = INVALID_ARGUMENT_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__ELEMENT = INVALID_ARGUMENT_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__DISPLAYED_ELEMENT = INVALID_ARGUMENT_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__MISSING = INVALID_ARGUMENT_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__RECOVERY = INVALID_ARGUMENT_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operator Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__OPERATOR_SYMBOL = INVALID_ARGUMENT_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR__TYPE = INVALID_ARGUMENT_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Invalid Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_FEATURE_COUNT = INVALID_ARGUMENT_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Invalid Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_OPERATION_COUNT = INVALID_ARGUMENT_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operator Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__OPERATOR_SYMBOL = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND__TYPE = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Invalid Operand</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Invalid Operand</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERAND_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__CLASS_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__METAMODEL_NAME = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>No Container For Ref Immediate Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>No Container For Ref Immediate Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__DEPENDENTS = NAVIGATION_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__DESCRIPTION = NAVIGATION_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__SEVERITY = NAVIGATION_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__NEEDS_CSP = NAVIGATION_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__STATUS = NAVIGATION_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__IGNORED_BY_USER = NAVIGATION_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__DATA = NAVIGATION_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__LOCATION = NAVIGATION_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__FILE_LOCATION = NAVIGATION_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__ELEMENT = NAVIGATION_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__DISPLAYED_ELEMENT = NAVIGATION_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__MISSING = NAVIGATION_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__RECOVERY = NAVIGATION_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Then Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__THEN_TYPE = NAVIGATION_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Else Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES__ELSE_TYPE = NAVIGATION_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Different Branch Types</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES_FEATURE_COUNT = NAVIGATION_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Different Branch Types</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIFFERENT_BRANCH_TYPES_OPERATION_COUNT = NAVIGATION_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Resolve Temp Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Resolve Temp Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_PROBLEM_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.AssignmentToReadonlyFeatureImpl <em>Assignment To Readonly Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.AssignmentToReadonlyFeatureImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAssignmentToReadonlyFeature()
	 * @generated
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE = 43;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The number of structural features of the '<em>Assignment To Readonly Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Assignment To Readonly Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TO_READONLY_FEATURE_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The number of structural features of the '<em>Binding Expected One Assigned Many</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Binding Expected One Assigned Many</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_EXPECTED_ONE_ASSIGNED_MANY_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The number of structural features of the '<em>Primitive Binding But Object Assigned</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive Binding But Object Assigned</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The number of structural features of the '<em>Object Binding But Primitive Assigned</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Object Binding But Primitive Assigned</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The number of structural features of the '<em>Primitive Binding Invalid Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive Binding Invalid Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_INVALID_ASSIGNMENT_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_RESOLUTION__RIGHT_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Target Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_RESOLUTION__TARGET_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_RESOLUTION__RIGHT = 2;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_RESOLUTION__LEFT = 3;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_RESOLUTION__RULES = 4;

	/**
	 * The number of structural features of the '<em>Binding Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_RESOLUTION_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Binding Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_RESOLUTION_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO__ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>All Involved Rules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO__ALL_INVOLVED_RULES = 2;

	/**
	 * The feature id for the '<em><b>Rule Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO__RULE_NAME = 3;

	/**
	 * The feature id for the '<em><b>Input Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO__INPUT_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Output Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO__OUTPUT_TYPE = 5;

	/**
	 * The number of structural features of the '<em>Resolved Rule Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Resolved Rule Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_RULE_INFO_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__DEPENDENTS = RESOLVE_TEMP_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__DESCRIPTION = RESOLVE_TEMP_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__SEVERITY = RESOLVE_TEMP_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__NEEDS_CSP = RESOLVE_TEMP_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__STATUS = RESOLVE_TEMP_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__IGNORED_BY_USER = RESOLVE_TEMP_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__DATA = RESOLVE_TEMP_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__LOCATION = RESOLVE_TEMP_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__FILE_LOCATION = RESOLVE_TEMP_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__ELEMENT = RESOLVE_TEMP_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__DISPLAYED_ELEMENT = RESOLVE_TEMP_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__MISSING = RESOLVE_TEMP_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__RECOVERY = RESOLVE_TEMP_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Source Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE__SOURCE_TYPE = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resolve Temp Without Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE_FEATURE_COUNT = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Resolve Temp Without Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_WITHOUT_RULE_OPERATION_COUNT = RESOLVE_TEMP_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempPossiblyUnresolvedImpl <em>Resolve Temp Possibly Unresolved</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempPossiblyUnresolvedImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempPossiblyUnresolved()
	 * @generated
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED = 51;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__DEPENDENTS = RESOLVE_TEMP_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__DESCRIPTION = RESOLVE_TEMP_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__SEVERITY = RESOLVE_TEMP_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__NEEDS_CSP = RESOLVE_TEMP_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__STATUS = RESOLVE_TEMP_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__IGNORED_BY_USER = RESOLVE_TEMP_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__DATA = RESOLVE_TEMP_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__LOCATION = RESOLVE_TEMP_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__FILE_LOCATION = RESOLVE_TEMP_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__ELEMENT = RESOLVE_TEMP_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__DISPLAYED_ELEMENT = RESOLVE_TEMP_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__MISSING = RESOLVE_TEMP_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__RECOVERY = RESOLVE_TEMP_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__RIGHT_TYPE = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__TARGET_TYPE = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__RIGHT = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__LEFT = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__RULES = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Problematic Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Problematic Classes Implicit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES_IMPLICIT = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Resolved Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED__RESOLVED_EXPRESSION = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Resolve Temp Possibly Unresolved</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED_FEATURE_COUNT = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Resolve Temp Possibly Unresolved</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_POSSIBLY_UNRESOLVED_OPERATION_COUNT = RESOLVE_TEMP_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__DEPENDENTS = RESOLVE_TEMP_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__DESCRIPTION = RESOLVE_TEMP_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SEVERITY = RESOLVE_TEMP_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__NEEDS_CSP = RESOLVE_TEMP_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__STATUS = RESOLVE_TEMP_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__IGNORED_BY_USER = RESOLVE_TEMP_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__DATA = RESOLVE_TEMP_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__LOCATION = RESOLVE_TEMP_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__FILE_LOCATION = RESOLVE_TEMP_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__ELEMENT = RESOLVE_TEMP_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__DISPLAYED_ELEMENT = RESOLVE_TEMP_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__MISSING = RESOLVE_TEMP_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RECOVERY = RESOLVE_TEMP_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Source Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resolve Temp Output Pattern Element Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND_FEATURE_COUNT = RESOLVE_TEMP_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Resolve Temp Output Pattern Element Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND_OPERATION_COUNT = RESOLVE_TEMP_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Flatten Over Non Nested Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Flatten Over Non Nested Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLATTEN_OVER_NON_NESTED_COLLECTION_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Change Select First For Any</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Change Select First For Any</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_SELECT_FIRST_FOR_ANY_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Iterator Over Empty Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Iterator Over Empty Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_EMPTY_SEQUENCE_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperatorUsageImpl <em>Invalid Operator Usage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.InvalidOperatorUsageImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidOperatorUsage()
	 * @generated
	 */
	int INVALID_OPERATOR_USAGE = 56;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Invalid Operator Usage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Invalid Operator Usage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_OPERATOR_USAGE_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL__MODEL_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reading Target Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Reading Target Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READING_TARGET_MODEL_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.WritingSourceModelImpl <em>Writing Source Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.WritingSourceModelImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getWritingSourceModel()
	 * @generated
	 */
	int WRITING_SOURCE_MODEL = 58;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL__MODEL_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Writing Source Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Writing Source Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITING_SOURCE_MODEL_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Lazy Rule With Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Lazy Rule With Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_WITH_FILTER_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidRuleInheritanceImpl <em>Invalid Rule Inheritance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.InvalidRuleInheritanceImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidRuleInheritance()
	 * @generated
	 */
	int INVALID_RULE_INHERITANCE = 60;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE__KIND = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Invalid Rule Inheritance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Invalid Rule Inheritance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_RULE_INHERITANCE_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE__MODEL_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ambiguous Target Model Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Ambiguous Target Model Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AMBIGUOUS_TARGET_MODEL_REFERENCE_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND__MODEL_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>No Model Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>No Model Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_MODEL_FOUND_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL__LITERAL = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>No Enum Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>No Enum Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_ENUM_LITERAL_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Wrong Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Wrong Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRONG_TYPE_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__DEPENDENTS = WRONG_TYPE__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__DESCRIPTION = WRONG_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__SEVERITY = WRONG_TYPE__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__NEEDS_CSP = WRONG_TYPE__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__STATUS = WRONG_TYPE__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__IGNORED_BY_USER = WRONG_TYPE__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__DATA = WRONG_TYPE__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__LOCATION = WRONG_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__FILE_LOCATION = WRONG_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__ELEMENT = WRONG_TYPE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__DISPLAYED_ELEMENT = WRONG_TYPE__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__MISSING = WRONG_TYPE__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE__RECOVERY = WRONG_TYPE__RECOVERY;

	/**
	 * The number of structural features of the '<em>Iterator Body Wrong Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE_FEATURE_COUNT = WRONG_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Iterator Body Wrong Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_BODY_WRONG_TYPE_OPERATION_COUNT = WRONG_TYPE_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Matched Rule Without Output Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Matched Rule Without Output Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_WITHOUT_OUTPUT_PATTERN_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.MatchedRuleFilterNonBooleanImpl <em>Matched Rule Filter Non Boolean</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.MatchedRuleFilterNonBooleanImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getMatchedRuleFilterNonBoolean()
	 * @generated
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN = 67;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Matched Rule Filter Non Boolean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Matched Rule Filter Non Boolean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FILTER_NON_BOOLEAN_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Expected Collection In For Each</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Expected Collection In For Each</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPECTED_COLLECTION_IN_FOR_EACH_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL__CLASS_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>No Class Found In Metamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>No Class Found In Metamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CLASS_FOUND_IN_METAMODEL_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The number of structural features of the '<em>Invalid Argument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Invalid Argument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_ARGUMENT_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND__OPERATION_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collection Operation Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Collection Operation Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_NOT_FOUND_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Iterator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE__ITERATOR_NAME = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Iterator Over No Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Iterator Over No Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OVER_NO_COLLECTION_TYPE_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Inferred</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Declared</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Incoherent Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Abstract Incoherent Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INCOHERENT_VARIABLE_DECLARATION_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__DEPENDENTS = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__DESCRIPTION = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__SEVERITY = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__NEEDS_CSP = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__STATUS = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__IGNORED_BY_USER = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__DATA = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__LOCATION = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__FILE_LOCATION = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__ELEMENT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__DISPLAYED_ELEMENT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__MISSING = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__RECOVERY = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__RECOVERY;

	/**
	 * The feature id for the '<em><b>Inferred</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__INFERRED = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED;

	/**
	 * The feature id for the '<em><b>Declared</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION__DECLARED = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED;

	/**
	 * The number of structural features of the '<em>Incoherent Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION_FEATURE_COUNT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Incoherent Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_VARIABLE_DECLARATION_OPERATION_COUNT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__DEPENDENTS = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__DESCRIPTION = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__SEVERITY = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__NEEDS_CSP = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__STATUS = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__IGNORED_BY_USER = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__DATA = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__LOCATION = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__FILE_LOCATION = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__ELEMENT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__DISPLAYED_ELEMENT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__MISSING = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__RECOVERY = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__RECOVERY;

	/**
	 * The feature id for the '<em><b>Inferred</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__INFERRED = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED;

	/**
	 * The feature id for the '<em><b>Declared</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE__DECLARED = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED;

	/**
	 * The number of structural features of the '<em>Incoherent Helper Return Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE_FEATURE_COUNT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Incoherent Helper Return Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCOHERENT_HELPER_RETURN_TYPE_OPERATION_COUNT = ABSTRACT_INCOHERENT_VARIABLE_DECLARATION_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingInplaceInvalidImpl <em>Binding Inplace Invalid</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.BindingInplaceInvalidImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingInplaceInvalid()
	 * @generated
	 */
	int BINDING_INPLACE_INVALID = 76;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__DEPENDENTS = BINDING_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__DESCRIPTION = BINDING_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__SEVERITY = BINDING_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__NEEDS_CSP = BINDING_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__STATUS = BINDING_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__IGNORED_BY_USER = BINDING_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__DATA = BINDING_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__LOCATION = BINDING_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__FILE_LOCATION = BINDING_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__ELEMENT = BINDING_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__DISPLAYED_ELEMENT = BINDING_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__MISSING = BINDING_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__RECOVERY = BINDING_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__FEATURE = BINDING_PROBLEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__FEATURE_NAME = BINDING_PROBLEM__FEATURE_NAME;

	/**
	 * The feature id for the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID__RIGHT_TYPE = BINDING_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Binding Inplace Invalid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID_FEATURE_COUNT = BINDING_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Binding Inplace Invalid</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_INPLACE_INVALID_OPERATION_COUNT = BINDING_PROBLEM_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.CannotInstantiateAbstractClassImpl <em>Cannot Instantiate Abstract Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.CannotInstantiateAbstractClassImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getCannotInstantiateAbstractClass()
	 * @generated
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS = 77;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS__TYPE = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Cannot Instantiate Abstract Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Cannot Instantiate Abstract Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANNOT_INSTANTIATE_ABSTRACT_CLASS_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.GenericLocalProblemImpl <em>Generic Local Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.GenericLocalProblemImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getGenericLocalProblem()
	 * @generated
	 */
	int GENERIC_LOCAL_PROBLEM = 78;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Generic Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM__GENERIC_KIND = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Local Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Local Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LOCAL_PROBLEM_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.impl.AtlParseErrorImpl <em>Atl Parse Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlParseErrorImpl
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAtlParseError()
	 * @generated
	 */
	int ATL_PARSE_ERROR = 79;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__DEPENDENTS = LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__DESCRIPTION = LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__SEVERITY = LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__NEEDS_CSP = LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__STATUS = LOCAL_PROBLEM__STATUS;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__IGNORED_BY_USER = LOCAL_PROBLEM__IGNORED_BY_USER;

	/**
	 * The feature id for the '<em><b>Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__DATA = LOCAL_PROBLEM__DATA;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__LOCATION = LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__FILE_LOCATION = LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__ELEMENT = LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Displayed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__DISPLAYED_ELEMENT = LOCAL_PROBLEM__DISPLAYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Missing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__MISSING = LOCAL_PROBLEM__MISSING;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__RECOVERY = LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Generic Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR__GENERIC_KIND = LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Atl Parse Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR_FEATURE_COUNT = LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Atl Parse Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATL_PARSE_ERROR_OPERATION_COUNT = LOCAL_PROBLEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind <em>No Binding For Compulsory Feature Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoBindingForCompulsoryFeatureKind()
	 * @generated
	 */
	int NO_BINDING_FOR_COMPULSORY_FEATURE_KIND = 80;


	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind <em>Invalid Rule Inheritance Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind
	 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidRuleInheritanceKind()
	 * @generated
	 */
	int INVALID_RULE_INHERITANCE_KIND = 81;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.LocalProblem <em>Local Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem
	 * @generated
	 */
	EClass getLocalProblem();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem#getLocation()
	 * @see #getLocalProblem()
	 * @generated
	 */
	EAttribute getLocalProblem_Location();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getFileLocation <em>File Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Location</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem#getFileLocation()
	 * @see #getLocalProblem()
	 * @generated
	 */
	EAttribute getLocalProblem_FileLocation();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem#getElement()
	 * @see #getLocalProblem()
	 * @generated
	 */
	EReference getLocalProblem_Element();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getDisplayedElement <em>Displayed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Displayed Element</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem#getDisplayedElement()
	 * @see #getLocalProblem()
	 * @generated
	 */
	EReference getLocalProblem_DisplayedElement();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getMissing <em>Missing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Missing</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem#getMissing()
	 * @see #getLocalProblem()
	 * @generated
	 */
	EAttribute getLocalProblem_Missing();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getRecovery <em>Recovery</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Recovery</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem#getRecovery()
	 * @see #getLocalProblem()
	 * @generated
	 */
	EReference getLocalProblem_Recovery();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.ModelElement#getKlass <em>Klass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Klass</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ModelElement#getKlass()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Klass();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.ModelElement#getMetamodelName <em>Metamodel Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metamodel Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ModelElement#getMetamodelName()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_MetamodelName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.RuntimeError <em>Runtime Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Error</em>'.
	 * @see anatlyzer.atl.errors.atl_error.RuntimeError
	 * @generated
	 */
	EClass getRuntimeError();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.Warning <em>Warning</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warning</em>'.
	 * @see anatlyzer.atl.errors.atl_error.Warning
	 * @generated
	 */
	EClass getWarning();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.StyleHint <em>Style Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Style Hint</em>'.
	 * @see anatlyzer.atl.errors.atl_error.StyleHint
	 * @generated
	 */
	EClass getStyleHint();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.PerformanceHint <em>Performance Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Performance Hint</em>'.
	 * @see anatlyzer.atl.errors.atl_error.PerformanceHint
	 * @generated
	 */
	EClass getPerformanceHint();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.NavigationProblem <em>Navigation Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NavigationProblem
	 * @generated
	 */
	EClass getNavigationProblem();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.InvalidArgumentProblem <em>Invalid Argument Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Argument Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidArgumentProblem
	 * @generated
	 */
	EClass getInvalidArgumentProblem();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.TargetModelConformanceProblem <em>Target Model Conformance Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Target Model Conformance Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.TargetModelConformanceProblem
	 * @generated
	 */
	EClass getTargetModelConformanceProblem();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet <em>Conflicting Rule Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conflicting Rule Set</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ConflictingRuleSet
	 * @generated
	 */
	EClass getConflictingRuleSet();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Types</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getTypes()
	 * @see #getConflictingRuleSet()
	 * @generated
	 */
	EReference getConflictingRuleSet_Types();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Rules</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getRules()
	 * @see #getConflictingRuleSet()
	 * @generated
	 */
	EReference getConflictingRuleSet_Rules();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getAnalyserInfo <em>Analyser Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Analyser Info</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getAnalyserInfo()
	 * @see #getConflictingRuleSet()
	 * @generated
	 */
	EAttribute getConflictingRuleSet_AnalyserInfo();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.TargetConformanceViolations <em>Target Conformance Violations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Target Conformance Violations</em>'.
	 * @see anatlyzer.atl.errors.atl_error.TargetConformanceViolations
	 * @generated
	 */
	EClass getTargetConformanceViolations();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atl.errors.atl_error.TargetConformanceViolations#getTargetIssues <em>Target Issues</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Target Issues</em>'.
	 * @see anatlyzer.atl.errors.atl_error.TargetConformanceViolations#getTargetIssues()
	 * @see #getTargetConformanceViolations()
	 * @generated
	 */
	EReference getTargetConformanceViolations_TargetIssues();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BatchTargetConformanceIssue <em>Batch Target Conformance Issue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Batch Target Conformance Issue</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BatchTargetConformanceIssue
	 * @generated
	 */
	EClass getBatchTargetConformanceIssue();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.TargetInvariantViolation <em>Target Invariant Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Target Invariant Violation</em>'.
	 * @see anatlyzer.atl.errors.atl_error.TargetInvariantViolation
	 * @generated
	 */
	EClass getTargetInvariantViolation();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.TargetInvariantViolation#getInvariantName <em>Invariant Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Invariant Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.TargetInvariantViolation#getInvariantName()
	 * @see #getTargetInvariantViolation()
	 * @generated
	 */
	EAttribute getTargetInvariantViolation_InvariantName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError <em>Collection Operation Over No Collection Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Operation Over No Collection Error</em>'.
	 * @see anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError
	 * @generated
	 */
	EClass getCollectionOperationOverNoCollectionError();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.FeatureAccessInCollection <em>Feature Access In Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Access In Collection</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureAccessInCollection
	 * @generated
	 */
	EClass getFeatureAccessInCollection();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.FeatureAccessInCollection#getFeatureName <em>Feature Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureAccessInCollection#getFeatureName()
	 * @see #getFeatureAccessInCollection()
	 * @generated
	 */
	EAttribute getFeatureAccessInCollection_FeatureName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound <em>Feature Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Not Found</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFound
	 * @generated
	 */
	EClass getFeatureNotFound();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getFeatureName <em>Feature Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFound#getFeatureName()
	 * @see #getFeatureNotFound()
	 * @generated
	 */
	EAttribute getFeatureNotFound_FeatureName();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFound#getType()
	 * @see #getFeatureNotFound()
	 * @generated
	 */
	EReference getFeatureNotFound_Type();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFound#getClassName()
	 * @see #getFeatureNotFound()
	 * @generated
	 */
	EAttribute getFeatureNotFound_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getMetamodelName <em>Metamodel Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metamodel Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFound#getMetamodelName()
	 * @see #getFeatureNotFound()
	 * @generated
	 */
	EAttribute getFeatureNotFound_MetamodelName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.FoundInSubtype <em>Found In Subtype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Found In Subtype</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FoundInSubtype
	 * @generated
	 */
	EClass getFoundInSubtype();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.FoundInSubtype#getPossibleClasses <em>Possible Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Possible Classes</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FoundInSubtype#getPossibleClasses()
	 * @see #getFoundInSubtype()
	 * @generated
	 */
	EReference getFoundInSubtype_PossibleClasses();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.FoundInSubtype#getMissingClasses <em>Missing Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Missing Classes</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FoundInSubtype#getMissingClasses()
	 * @see #getFoundInSubtype()
	 * @generated
	 */
	EReference getFoundInSubtype_MissingClasses();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype <em>Feature Found In Subtype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Found In Subtype</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype
	 * @generated
	 */
	EClass getFeatureFoundInSubtype();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid <em>Operation Call Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Call Invalid</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalid
	 * @generated
	 */
	EClass getOperationCallInvalid();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getOperationName <em>Operation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalid#getOperationName()
	 * @see #getOperationCallInvalid()
	 * @generated
	 */
	EAttribute getOperationCallInvalid_OperationName();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalid#getType()
	 * @see #getOperationCallInvalid()
	 * @generated
	 */
	EReference getOperationCallInvalid_Type();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalid#getClassName()
	 * @see #getOperationCallInvalid()
	 * @generated
	 */
	EAttribute getOperationCallInvalid_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getMetamodelName <em>Metamodel Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metamodel Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalid#getMetamodelName()
	 * @see #getOperationCallInvalid()
	 * @generated
	 */
	EAttribute getOperationCallInvalid_MetamodelName();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getFormalParameters <em>Formal Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Formal Parameters</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalid#getFormalParameters()
	 * @see #getOperationCallInvalid()
	 * @generated
	 */
	EReference getOperationCallInvalid_FormalParameters();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getActualParameters <em>Actual Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Actual Parameters</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalid#getActualParameters()
	 * @see #getOperationCallInvalid()
	 * @generated
	 */
	EReference getOperationCallInvalid_ActualParameters();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OperationNotFound <em>Operation Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Not Found</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationNotFound
	 * @generated
	 */
	EClass getOperationNotFound();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OperationFoundInSubtype <em>Operation Found In Subtype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Found In Subtype</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationFoundInSubtype
	 * @generated
	 */
	EClass getOperationFoundInSubtype();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters <em>Operation Call Invalid Number Of Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Call Invalid Number Of Parameters</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters
	 * @generated
	 */
	EClass getOperationCallInvalidNumberOfParameters();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter <em>Operation Call Invalid Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Call Invalid Parameter</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter
	 * @generated
	 */
	EClass getOperationCallInvalidParameter();

	/**
	 * Returns the meta object for the attribute list '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter#getBlamedParameterNames <em>Blamed Parameter Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Blamed Parameter Names</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter#getBlamedParameterNames()
	 * @see #getOperationCallInvalidParameter()
	 * @generated
	 */
	EAttribute getOperationCallInvalidParameter_BlamedParameterNames();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule <em>Operation Not Found In This Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Not Found In This Module</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule
	 * @generated
	 */
	EClass getOperationNotFoundInThisModule();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#getName()
	 * @see #getOperationNotFoundInThisModule()
	 * @generated
	 */
	EAttribute getOperationNotFoundInThisModule_Name();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#isMaybeRule <em>Maybe Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maybe Rule</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#isMaybeRule()
	 * @see #getOperationNotFoundInThisModule()
	 * @generated
	 */
	EAttribute getOperationNotFoundInThisModule_MaybeRule();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule <em>Attribute Not Found In This Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Not Found In This Module</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule
	 * @generated
	 */
	EClass getAttributeNotFoundInThisModule();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule#getName()
	 * @see #getAttributeNotFoundInThisModule()
	 * @generated
	 */
	EAttribute getAttributeNotFoundInThisModule_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType <em>Feature Not Found In Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Not Found In Union Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType
	 * @generated
	 */
	EClass getFeatureNotFoundInUnionType();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType#getFeatureName <em>Feature Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType#getFeatureName()
	 * @see #getFeatureNotFoundInUnionType()
	 * @generated
	 */
	EAttribute getFeatureNotFoundInUnionType_FeatureName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.InvalidOperator <em>Invalid Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Operator</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperator
	 * @generated
	 */
	EClass getInvalidOperator();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.InvalidOperator#getOperatorSymbol <em>Operator Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator Symbol</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperator#getOperatorSymbol()
	 * @see #getInvalidOperator()
	 * @generated
	 */
	EAttribute getInvalidOperator_OperatorSymbol();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.InvalidOperator#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperator#getType()
	 * @see #getInvalidOperator()
	 * @generated
	 */
	EReference getInvalidOperator_Type();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.InvalidOperand <em>Invalid Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Operand</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperand
	 * @generated
	 */
	EClass getInvalidOperand();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.InvalidOperand#getOperatorSymbol <em>Operator Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator Symbol</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperand#getOperatorSymbol()
	 * @see #getInvalidOperand()
	 * @generated
	 */
	EAttribute getInvalidOperand_OperatorSymbol();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.InvalidOperand#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperand#getType()
	 * @see #getInvalidOperand()
	 * @generated
	 */
	EReference getInvalidOperand_Type();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite <em>No Container For Ref Immediate Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Container For Ref Immediate Composite</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite
	 * @generated
	 */
	EClass getNoContainerForRefImmediateComposite();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getClassName()
	 * @see #getNoContainerForRefImmediateComposite()
	 * @generated
	 */
	EAttribute getNoContainerForRefImmediateComposite_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getMetamodelName <em>Metamodel Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metamodel Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getMetamodelName()
	 * @see #getNoContainerForRefImmediateComposite()
	 * @generated
	 */
	EAttribute getNoContainerForRefImmediateComposite_MetamodelName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.DifferentBranchTypes <em>Different Branch Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Different Branch Types</em>'.
	 * @see anatlyzer.atl.errors.atl_error.DifferentBranchTypes
	 * @generated
	 */
	EClass getDifferentBranchTypes();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.DifferentBranchTypes#getThenType <em>Then Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Then Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.DifferentBranchTypes#getThenType()
	 * @see #getDifferentBranchTypes()
	 * @generated
	 */
	EReference getDifferentBranchTypes_ThenType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.DifferentBranchTypes#getElseType <em>Else Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Else Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.DifferentBranchTypes#getElseType()
	 * @see #getDifferentBranchTypes()
	 * @generated
	 */
	EReference getDifferentBranchTypes_ElseType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BindingProblem <em>Binding Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingProblem
	 * @generated
	 */
	EClass getBindingProblem();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.BindingProblem#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Feature</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingProblem#getFeature()
	 * @see #getBindingProblem()
	 * @generated
	 */
	EReference getBindingProblem_Feature();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.BindingProblem#getFeatureName <em>Feature Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingProblem#getFeatureName()
	 * @see #getBindingProblem()
	 * @generated
	 */
	EAttribute getBindingProblem_FeatureName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ResolveTempProblem <em>Resolve Temp Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolve Temp Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempProblem
	 * @generated
	 */
	EClass getResolveTempProblem();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.AssignmentToReadonlyFeature <em>Assignment To Readonly Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment To Readonly Feature</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AssignmentToReadonlyFeature
	 * @generated
	 */
	EClass getAssignmentToReadonlyFeature();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature <em>No Binding For Compulsory Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Binding For Compulsory Feature</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature
	 * @generated
	 */
	EClass getNoBindingForCompulsoryFeature();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getSubrule <em>Subrule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subrule</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getSubrule()
	 * @see #getNoBindingForCompulsoryFeature()
	 * @generated
	 */
	EReference getNoBindingForCompulsoryFeature_Subrule();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getKind()
	 * @see #getNoBindingForCompulsoryFeature()
	 * @generated
	 */
	EAttribute getNoBindingForCompulsoryFeature_Kind();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.InvalidAssignmentImperativeBinding <em>Invalid Assignment Imperative Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Assignment Imperative Binding</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidAssignmentImperativeBinding
	 * @generated
	 */
	EClass getInvalidAssignmentImperativeBinding();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany <em>Binding Expected One Assigned Many</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Expected One Assigned Many</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany
	 * @generated
	 */
	EClass getBindingExpectedOneAssignedMany();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.PrimitiveBindingButObjectAssigned <em>Primitive Binding But Object Assigned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Binding But Object Assigned</em>'.
	 * @see anatlyzer.atl.errors.atl_error.PrimitiveBindingButObjectAssigned
	 * @generated
	 */
	EClass getPrimitiveBindingButObjectAssigned();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ObjectBindingButPrimitiveAssigned <em>Object Binding But Primitive Assigned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Binding But Primitive Assigned</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ObjectBindingButPrimitiveAssigned
	 * @generated
	 */
	EClass getObjectBindingButPrimitiveAssigned();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment <em>Primitive Binding Invalid Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Binding Invalid Assignment</em>'.
	 * @see anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment
	 * @generated
	 */
	EClass getPrimitiveBindingInvalidAssignment();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BindingResolution <em>Binding Resolution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Resolution</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingResolution
	 * @generated
	 */
	EClass getBindingResolution();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRightType <em>Right Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingResolution#getRightType()
	 * @see #getBindingResolution()
	 * @generated
	 */
	EReference getBindingResolution_RightType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getTargetType <em>Target Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingResolution#getTargetType()
	 * @see #getBindingResolution()
	 * @generated
	 */
	EReference getBindingResolution_TargetType();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingResolution#getRight()
	 * @see #getBindingResolution()
	 * @generated
	 */
	EReference getBindingResolution_Right();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingResolution#getLeft()
	 * @see #getBindingResolution()
	 * @generated
	 */
	EReference getBindingResolution_Left();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingResolution#getRules()
	 * @see #getBindingResolution()
	 * @generated
	 */
	EReference getBindingResolution_Rules();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BindingWithoutRule <em>Binding Without Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Without Rule</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingWithoutRule
	 * @generated
	 */
	EClass getBindingWithoutRule();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule <em>Binding With Resolved By Incompatible Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding With Resolved By Incompatible Rule</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule
	 * @generated
	 */
	EClass getBindingWithResolvedByIncompatibleRule();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved <em>Binding Possibly Unresolved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Possibly Unresolved</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved
	 * @generated
	 */
	EClass getBindingPossiblyUnresolved();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved#getProblematicClasses <em>Problematic Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Problematic Classes</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved#getProblematicClasses()
	 * @see #getBindingPossiblyUnresolved()
	 * @generated
	 */
	EReference getBindingPossiblyUnresolved_ProblematicClasses();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved#getProblematicClassesImplicit <em>Problematic Classes Implicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Problematic Classes Implicit</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved#getProblematicClassesImplicit()
	 * @see #getBindingPossiblyUnresolved()
	 * @generated
	 */
	EReference getBindingPossiblyUnresolved_ProblematicClassesImplicit();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo <em>Resolved Rule Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolved Rule Info</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo
	 * @generated
	 */
	EClass getResolvedRuleInfo();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getLocation()
	 * @see #getResolvedRuleInfo()
	 * @generated
	 */
	EAttribute getResolvedRuleInfo_Location();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getElement()
	 * @see #getResolvedRuleInfo()
	 * @generated
	 */
	EReference getResolvedRuleInfo_Element();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getAllInvolvedRules <em>All Involved Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>All Involved Rules</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getAllInvolvedRules()
	 * @see #getResolvedRuleInfo()
	 * @generated
	 */
	EReference getResolvedRuleInfo_AllInvolvedRules();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getRuleName <em>Rule Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getRuleName()
	 * @see #getResolvedRuleInfo()
	 * @generated
	 */
	EAttribute getResolvedRuleInfo_RuleName();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getInputType <em>Input Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Input Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getInputType()
	 * @see #getResolvedRuleInfo()
	 * @generated
	 */
	EReference getResolvedRuleInfo_InputType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getOutputType <em>Output Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Output Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo#getOutputType()
	 * @see #getResolvedRuleInfo()
	 * @generated
	 */
	EReference getResolvedRuleInfo_OutputType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule <em>Resolve Temp Without Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolve Temp Without Rule</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule
	 * @generated
	 */
	EClass getResolveTempWithoutRule();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule#getSourceType <em>Source Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule#getSourceType()
	 * @see #getResolveTempWithoutRule()
	 * @generated
	 */
	EReference getResolveTempWithoutRule_SourceType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved <em>Resolve Temp Possibly Unresolved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolve Temp Possibly Unresolved</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved
	 * @generated
	 */
	EClass getResolveTempPossiblyUnresolved();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getProblematicClasses <em>Problematic Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Problematic Classes</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getProblematicClasses()
	 * @see #getResolveTempPossiblyUnresolved()
	 * @generated
	 */
	EReference getResolveTempPossiblyUnresolved_ProblematicClasses();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getProblematicClassesImplicit <em>Problematic Classes Implicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Problematic Classes Implicit</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getProblematicClassesImplicit()
	 * @see #getResolveTempPossiblyUnresolved()
	 * @generated
	 */
	EReference getResolveTempPossiblyUnresolved_ProblematicClassesImplicit();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getResolvedExpression <em>Resolved Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resolved Expression</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getResolvedExpression()
	 * @see #getResolveTempPossiblyUnresolved()
	 * @generated
	 */
	EReference getResolveTempPossiblyUnresolved_ResolvedExpression();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound <em>Resolve Temp Output Pattern Element Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolve Temp Output Pattern Element Not Found</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound
	 * @generated
	 */
	EClass getResolveTempOutputPatternElementNotFound();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound#getSourceType <em>Source Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound#getSourceType()
	 * @see #getResolveTempOutputPatternElementNotFound()
	 * @generated
	 */
	EReference getResolveTempOutputPatternElementNotFound_SourceType();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound#getRules()
	 * @see #getResolveTempOutputPatternElementNotFound()
	 * @generated
	 */
	EReference getResolveTempOutputPatternElementNotFound_Rules();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection <em>Flatten Over Non Nested Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flatten Over Non Nested Collection</em>'.
	 * @see anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection
	 * @generated
	 */
	EClass getFlattenOverNonNestedCollection();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ChangeSelectFirstForAny <em>Change Select First For Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Select First For Any</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ChangeSelectFirstForAny
	 * @generated
	 */
	EClass getChangeSelectFirstForAny();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.IteratorOverEmptySequence <em>Iterator Over Empty Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator Over Empty Sequence</em>'.
	 * @see anatlyzer.atl.errors.atl_error.IteratorOverEmptySequence
	 * @generated
	 */
	EClass getIteratorOverEmptySequence();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.InvalidOperatorUsage <em>Invalid Operator Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Operator Usage</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperatorUsage
	 * @generated
	 */
	EClass getInvalidOperatorUsage();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ReadingTargetModel <em>Reading Target Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reading Target Model</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ReadingTargetModel
	 * @generated
	 */
	EClass getReadingTargetModel();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.ReadingTargetModel#getModelName <em>Model Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ReadingTargetModel#getModelName()
	 * @see #getReadingTargetModel()
	 * @generated
	 */
	EAttribute getReadingTargetModel_ModelName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.WritingSourceModel <em>Writing Source Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Writing Source Model</em>'.
	 * @see anatlyzer.atl.errors.atl_error.WritingSourceModel
	 * @generated
	 */
	EClass getWritingSourceModel();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.WritingSourceModel#getModelName <em>Model Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.WritingSourceModel#getModelName()
	 * @see #getWritingSourceModel()
	 * @generated
	 */
	EAttribute getWritingSourceModel_ModelName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.LazyRuleWithFilter <em>Lazy Rule With Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lazy Rule With Filter</em>'.
	 * @see anatlyzer.atl.errors.atl_error.LazyRuleWithFilter
	 * @generated
	 */
	EClass getLazyRuleWithFilter();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritance <em>Invalid Rule Inheritance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Rule Inheritance</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidRuleInheritance
	 * @generated
	 */
	EClass getInvalidRuleInheritance();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritance#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidRuleInheritance#getKind()
	 * @see #getInvalidRuleInheritance()
	 * @generated
	 */
	EAttribute getInvalidRuleInheritance_Kind();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference <em>Ambiguous Target Model Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ambiguous Target Model Reference</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference
	 * @generated
	 */
	EClass getAmbiguousTargetModelReference();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference#getModelName <em>Model Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference#getModelName()
	 * @see #getAmbiguousTargetModelReference()
	 * @generated
	 */
	EAttribute getAmbiguousTargetModelReference_ModelName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.NoModelFound <em>No Model Found</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Model Found</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoModelFound
	 * @generated
	 */
	EClass getNoModelFound();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.NoModelFound#getModelName <em>Model Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoModelFound#getModelName()
	 * @see #getNoModelFound()
	 * @generated
	 */
	EAttribute getNoModelFound_ModelName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.NoEnumLiteral <em>No Enum Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Enum Literal</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoEnumLiteral
	 * @generated
	 */
	EClass getNoEnumLiteral();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.NoEnumLiteral#getLiteral <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Literal</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoEnumLiteral#getLiteral()
	 * @see #getNoEnumLiteral()
	 * @generated
	 */
	EAttribute getNoEnumLiteral_Literal();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.WrongType <em>Wrong Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrong Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.WrongType
	 * @generated
	 */
	EClass getWrongType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.IteratorBodyWrongType <em>Iterator Body Wrong Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator Body Wrong Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.IteratorBodyWrongType
	 * @generated
	 */
	EClass getIteratorBodyWrongType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.MatchedRuleWithoutOutputPattern <em>Matched Rule Without Output Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matched Rule Without Output Pattern</em>'.
	 * @see anatlyzer.atl.errors.atl_error.MatchedRuleWithoutOutputPattern
	 * @generated
	 */
	EClass getMatchedRuleWithoutOutputPattern();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.MatchedRuleFilterNonBoolean <em>Matched Rule Filter Non Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matched Rule Filter Non Boolean</em>'.
	 * @see anatlyzer.atl.errors.atl_error.MatchedRuleFilterNonBoolean
	 * @generated
	 */
	EClass getMatchedRuleFilterNonBoolean();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.ExpectedCollectionInForEach <em>Expected Collection In For Each</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expected Collection In For Each</em>'.
	 * @see anatlyzer.atl.errors.atl_error.ExpectedCollectionInForEach
	 * @generated
	 */
	EClass getExpectedCollectionInForEach();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel <em>No Class Found In Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Class Found In Metamodel</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel
	 * @generated
	 */
	EClass getNoClassFoundInMetamodel();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel#getClassName()
	 * @see #getNoClassFoundInMetamodel()
	 * @generated
	 */
	EAttribute getNoClassFoundInMetamodel_ClassName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.InvalidArgument <em>Invalid Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Argument</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidArgument
	 * @generated
	 */
	EClass getInvalidArgument();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.CollectionOperationNotFound <em>Collection Operation Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Operation Not Found</em>'.
	 * @see anatlyzer.atl.errors.atl_error.CollectionOperationNotFound
	 * @generated
	 */
	EClass getCollectionOperationNotFound();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.CollectionOperationNotFound#getOperationName <em>Operation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.CollectionOperationNotFound#getOperationName()
	 * @see #getCollectionOperationNotFound()
	 * @generated
	 */
	EAttribute getCollectionOperationNotFound_OperationName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType <em>Iterator Over No Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator Over No Collection Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType
	 * @generated
	 */
	EClass getIteratorOverNoCollectionType();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType#getIteratorName <em>Iterator Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Iterator Name</em>'.
	 * @see anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType#getIteratorName()
	 * @see #getIteratorOverNoCollectionType()
	 * @generated
	 */
	EAttribute getIteratorOverNoCollectionType_IteratorName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration <em>Abstract Incoherent Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Incoherent Variable Declaration</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration
	 * @generated
	 */
	EClass getAbstractIncoherentVariableDeclaration();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getInferred <em>Inferred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Inferred</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getInferred()
	 * @see #getAbstractIncoherentVariableDeclaration()
	 * @generated
	 */
	EReference getAbstractIncoherentVariableDeclaration_Inferred();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getDeclared <em>Declared</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Declared</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getDeclared()
	 * @see #getAbstractIncoherentVariableDeclaration()
	 * @generated
	 */
	EReference getAbstractIncoherentVariableDeclaration_Declared();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OclCompliance <em>Ocl Compliance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Compliance</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OclCompliance
	 * @generated
	 */
	EClass getOclCompliance();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OclComplianceProblem <em>Ocl Compliance Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Compliance Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OclComplianceProblem
	 * @generated
	 */
	EClass getOclComplianceProblem();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration <em>Incoherent Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Incoherent Variable Declaration</em>'.
	 * @see anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration
	 * @generated
	 */
	EClass getIncoherentVariableDeclaration();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType <em>Incoherent Helper Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Incoherent Helper Return Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType
	 * @generated
	 */
	EClass getIncoherentHelperReturnType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.OperationOverCollectionType <em>Operation Over Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Over Collection Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.OperationOverCollectionType
	 * @generated
	 */
	EClass getOperationOverCollectionType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue <em>Access To Undefined Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Access To Undefined Value</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AccessToUndefinedValue
	 * @generated
	 */
	EClass getAccessToUndefinedValue();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection <em>Access To Undefined Value Through Empty Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Access To Undefined Value Through Empty Collection</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection
	 * @generated
	 */
	EClass getAccessToUndefinedValue_ThroughEmptyCollection();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.RuleConflicts <em>Rule Conflicts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Conflicts</em>'.
	 * @see anatlyzer.atl.errors.atl_error.RuleConflicts
	 * @generated
	 */
	EClass getRuleConflicts();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.errors.atl_error.RuleConflicts#getConflicts <em>Conflicts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Conflicts</em>'.
	 * @see anatlyzer.atl.errors.atl_error.RuleConflicts#getConflicts()
	 * @see #getRuleConflicts()
	 * @generated
	 */
	EReference getRuleConflicts_Conflicts();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.BindingInplaceInvalid <em>Binding Inplace Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Inplace Invalid</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingInplaceInvalid
	 * @generated
	 */
	EClass getBindingInplaceInvalid();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.BindingInplaceInvalid#getRightType <em>Right Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.BindingInplaceInvalid#getRightType()
	 * @see #getBindingInplaceInvalid()
	 * @generated
	 */
	EReference getBindingInplaceInvalid_RightType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass <em>Cannot Instantiate Abstract Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cannot Instantiate Abstract Class</em>'.
	 * @see anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass
	 * @generated
	 */
	EClass getCannotInstantiateAbstractClass();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass#getType()
	 * @see #getCannotInstantiateAbstractClass()
	 * @generated
	 */
	EReference getCannotInstantiateAbstractClass_Type();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.GenericLocalProblem <em>Generic Local Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Local Problem</em>'.
	 * @see anatlyzer.atl.errors.atl_error.GenericLocalProblem
	 * @generated
	 */
	EClass getGenericLocalProblem();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.GenericLocalProblem#getGenericKind <em>Generic Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generic Kind</em>'.
	 * @see anatlyzer.atl.errors.atl_error.GenericLocalProblem#getGenericKind()
	 * @see #getGenericLocalProblem()
	 * @generated
	 */
	EAttribute getGenericLocalProblem_GenericKind();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_error.AtlParseError <em>Atl Parse Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Atl Parse Error</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AtlParseError
	 * @generated
	 */
	EClass getAtlParseError();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_error.AtlParseError#getGenericKind <em>Generic Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generic Kind</em>'.
	 * @see anatlyzer.atl.errors.atl_error.AtlParseError#getGenericKind()
	 * @see #getAtlParseError()
	 * @generated
	 */
	EAttribute getAtlParseError_GenericKind();

	/**
	 * Returns the meta object for enum '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind <em>No Binding For Compulsory Feature Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>No Binding For Compulsory Feature Kind</em>'.
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind
	 * @generated
	 */
	EEnum getNoBindingForCompulsoryFeatureKind();

	/**
	 * Returns the meta object for enum '{@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind <em>Invalid Rule Inheritance Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Invalid Rule Inheritance Kind</em>'.
	 * @see anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind
	 * @generated
	 */
	EEnum getInvalidRuleInheritanceKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AtlErrorFactory getAtlErrorFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.LocalProblemImpl <em>Local Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.LocalProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getLocalProblem()
		 * @generated
		 */
		EClass LOCAL_PROBLEM = eINSTANCE.getLocalProblem();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_PROBLEM__LOCATION = eINSTANCE.getLocalProblem_Location();

		/**
		 * The meta object literal for the '<em><b>File Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_PROBLEM__FILE_LOCATION = eINSTANCE.getLocalProblem_FileLocation();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCAL_PROBLEM__ELEMENT = eINSTANCE.getLocalProblem_Element();

		/**
		 * The meta object literal for the '<em><b>Displayed Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCAL_PROBLEM__DISPLAYED_ELEMENT = eINSTANCE.getLocalProblem_DisplayedElement();

		/**
		 * The meta object literal for the '<em><b>Missing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_PROBLEM__MISSING = eINSTANCE.getLocalProblem_Missing();

		/**
		 * The meta object literal for the '<em><b>Recovery</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCAL_PROBLEM__RECOVERY = eINSTANCE.getLocalProblem_Recovery();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ModelElementImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '<em><b>Klass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__KLASS = eINSTANCE.getModelElement_Klass();

		/**
		 * The meta object literal for the '<em><b>Metamodel Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__METAMODEL_NAME = eINSTANCE.getModelElement_MetamodelName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.RuntimeErrorImpl <em>Runtime Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.RuntimeErrorImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getRuntimeError()
		 * @generated
		 */
		EClass RUNTIME_ERROR = eINSTANCE.getRuntimeError();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.WarningImpl <em>Warning</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.WarningImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getWarning()
		 * @generated
		 */
		EClass WARNING = eINSTANCE.getWarning();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.StyleHintImpl <em>Style Hint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.StyleHintImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getStyleHint()
		 * @generated
		 */
		EClass STYLE_HINT = eINSTANCE.getStyleHint();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.PerformanceHintImpl <em>Performance Hint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.PerformanceHintImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getPerformanceHint()
		 * @generated
		 */
		EClass PERFORMANCE_HINT = eINSTANCE.getPerformanceHint();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.NavigationProblemImpl <em>Navigation Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.NavigationProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNavigationProblem()
		 * @generated
		 */
		EClass NAVIGATION_PROBLEM = eINSTANCE.getNavigationProblem();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidArgumentProblemImpl <em>Invalid Argument Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.InvalidArgumentProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidArgumentProblem()
		 * @generated
		 */
		EClass INVALID_ARGUMENT_PROBLEM = eINSTANCE.getInvalidArgumentProblem();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.TargetModelConformanceProblemImpl <em>Target Model Conformance Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.TargetModelConformanceProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getTargetModelConformanceProblem()
		 * @generated
		 */
		EClass TARGET_MODEL_CONFORMANCE_PROBLEM = eINSTANCE.getTargetModelConformanceProblem();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ConflictingRuleSetImpl <em>Conflicting Rule Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ConflictingRuleSetImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getConflictingRuleSet()
		 * @generated
		 */
		EClass CONFLICTING_RULE_SET = eINSTANCE.getConflictingRuleSet();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFLICTING_RULE_SET__TYPES = eINSTANCE.getConflictingRuleSet_Types();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFLICTING_RULE_SET__RULES = eINSTANCE.getConflictingRuleSet_Rules();

		/**
		 * The meta object literal for the '<em><b>Analyser Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFLICTING_RULE_SET__ANALYSER_INFO = eINSTANCE.getConflictingRuleSet_AnalyserInfo();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.TargetConformanceViolationsImpl <em>Target Conformance Violations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.TargetConformanceViolationsImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getTargetConformanceViolations()
		 * @generated
		 */
		EClass TARGET_CONFORMANCE_VIOLATIONS = eINSTANCE.getTargetConformanceViolations();

		/**
		 * The meta object literal for the '<em><b>Target Issues</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES = eINSTANCE.getTargetConformanceViolations_TargetIssues();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BatchTargetConformanceIssueImpl <em>Batch Target Conformance Issue</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BatchTargetConformanceIssueImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBatchTargetConformanceIssue()
		 * @generated
		 */
		EClass BATCH_TARGET_CONFORMANCE_ISSUE = eINSTANCE.getBatchTargetConformanceIssue();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.TargetInvariantViolationImpl <em>Target Invariant Violation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.TargetInvariantViolationImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getTargetInvariantViolation()
		 * @generated
		 */
		EClass TARGET_INVARIANT_VIOLATION = eINSTANCE.getTargetInvariantViolation();

		/**
		 * The meta object literal for the '<em><b>Invariant Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TARGET_INVARIANT_VIOLATION__INVARIANT_NAME = eINSTANCE.getTargetInvariantViolation_InvariantName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.CollectionOperationOverNoCollectionErrorImpl <em>Collection Operation Over No Collection Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.CollectionOperationOverNoCollectionErrorImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getCollectionOperationOverNoCollectionError()
		 * @generated
		 */
		EClass COLLECTION_OPERATION_OVER_NO_COLLECTION_ERROR = eINSTANCE.getCollectionOperationOverNoCollectionError();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureAccessInCollectionImpl <em>Feature Access In Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.FeatureAccessInCollectionImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureAccessInCollection()
		 * @generated
		 */
		EClass FEATURE_ACCESS_IN_COLLECTION = eINSTANCE.getFeatureAccessInCollection();

		/**
		 * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_ACCESS_IN_COLLECTION__FEATURE_NAME = eINSTANCE.getFeatureAccessInCollection_FeatureName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl <em>Feature Not Found</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureNotFound()
		 * @generated
		 */
		EClass FEATURE_NOT_FOUND = eINSTANCE.getFeatureNotFound();

		/**
		 * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_NOT_FOUND__FEATURE_NAME = eINSTANCE.getFeatureNotFound_FeatureName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_NOT_FOUND__TYPE = eINSTANCE.getFeatureNotFound_Type();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_NOT_FOUND__CLASS_NAME = eINSTANCE.getFeatureNotFound_ClassName();

		/**
		 * The meta object literal for the '<em><b>Metamodel Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_NOT_FOUND__METAMODEL_NAME = eINSTANCE.getFeatureNotFound_MetamodelName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.FoundInSubtypeImpl <em>Found In Subtype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.FoundInSubtypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFoundInSubtype()
		 * @generated
		 */
		EClass FOUND_IN_SUBTYPE = eINSTANCE.getFoundInSubtype();

		/**
		 * The meta object literal for the '<em><b>Possible Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOUND_IN_SUBTYPE__POSSIBLE_CLASSES = eINSTANCE.getFoundInSubtype_PossibleClasses();

		/**
		 * The meta object literal for the '<em><b>Missing Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOUND_IN_SUBTYPE__MISSING_CLASSES = eINSTANCE.getFoundInSubtype_MissingClasses();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureFoundInSubtypeImpl <em>Feature Found In Subtype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.FeatureFoundInSubtypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureFoundInSubtype()
		 * @generated
		 */
		EClass FEATURE_FOUND_IN_SUBTYPE = eINSTANCE.getFeatureFoundInSubtype();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl <em>Operation Call Invalid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationCallInvalid()
		 * @generated
		 */
		EClass OPERATION_CALL_INVALID = eINSTANCE.getOperationCallInvalid();

		/**
		 * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_CALL_INVALID__OPERATION_NAME = eINSTANCE.getOperationCallInvalid_OperationName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_INVALID__TYPE = eINSTANCE.getOperationCallInvalid_Type();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_CALL_INVALID__CLASS_NAME = eINSTANCE.getOperationCallInvalid_ClassName();

		/**
		 * The meta object literal for the '<em><b>Metamodel Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_CALL_INVALID__METAMODEL_NAME = eINSTANCE.getOperationCallInvalid_MetamodelName();

		/**
		 * The meta object literal for the '<em><b>Formal Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_INVALID__FORMAL_PARAMETERS = eINSTANCE.getOperationCallInvalid_FormalParameters();

		/**
		 * The meta object literal for the '<em><b>Actual Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_INVALID__ACTUAL_PARAMETERS = eINSTANCE.getOperationCallInvalid_ActualParameters();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationNotFoundImpl <em>Operation Not Found</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OperationNotFoundImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationNotFound()
		 * @generated
		 */
		EClass OPERATION_NOT_FOUND = eINSTANCE.getOperationNotFound();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationFoundInSubtypeImpl <em>Operation Found In Subtype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OperationFoundInSubtypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationFoundInSubtype()
		 * @generated
		 */
		EClass OPERATION_FOUND_IN_SUBTYPE = eINSTANCE.getOperationFoundInSubtype();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidNumberOfParametersImpl <em>Operation Call Invalid Number Of Parameters</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidNumberOfParametersImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationCallInvalidNumberOfParameters()
		 * @generated
		 */
		EClass OPERATION_CALL_INVALID_NUMBER_OF_PARAMETERS = eINSTANCE.getOperationCallInvalidNumberOfParameters();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidParameterImpl <em>Operation Call Invalid Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidParameterImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationCallInvalidParameter()
		 * @generated
		 */
		EClass OPERATION_CALL_INVALID_PARAMETER = eINSTANCE.getOperationCallInvalidParameter();

		/**
		 * The meta object literal for the '<em><b>Blamed Parameter Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_CALL_INVALID_PARAMETER__BLAMED_PARAMETER_NAMES = eINSTANCE.getOperationCallInvalidParameter_BlamedParameterNames();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationNotFoundInThisModuleImpl <em>Operation Not Found In This Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OperationNotFoundInThisModuleImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationNotFoundInThisModule()
		 * @generated
		 */
		EClass OPERATION_NOT_FOUND_IN_THIS_MODULE = eINSTANCE.getOperationNotFoundInThisModule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_NOT_FOUND_IN_THIS_MODULE__NAME = eINSTANCE.getOperationNotFoundInThisModule_Name();

		/**
		 * The meta object literal for the '<em><b>Maybe Rule</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_NOT_FOUND_IN_THIS_MODULE__MAYBE_RULE = eINSTANCE.getOperationNotFoundInThisModule_MaybeRule();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.AttributeNotFoundInThisModuleImpl <em>Attribute Not Found In This Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.AttributeNotFoundInThisModuleImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAttributeNotFoundInThisModule()
		 * @generated
		 */
		EClass ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE = eINSTANCE.getAttributeNotFoundInThisModule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_NOT_FOUND_IN_THIS_MODULE__NAME = eINSTANCE.getAttributeNotFoundInThisModule_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundInUnionTypeImpl <em>Feature Not Found In Union Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundInUnionTypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFeatureNotFoundInUnionType()
		 * @generated
		 */
		EClass FEATURE_NOT_FOUND_IN_UNION_TYPE = eINSTANCE.getFeatureNotFoundInUnionType();

		/**
		 * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_NOT_FOUND_IN_UNION_TYPE__FEATURE_NAME = eINSTANCE.getFeatureNotFoundInUnionType_FeatureName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperatorImpl <em>Invalid Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.InvalidOperatorImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidOperator()
		 * @generated
		 */
		EClass INVALID_OPERATOR = eINSTANCE.getInvalidOperator();

		/**
		 * The meta object literal for the '<em><b>Operator Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVALID_OPERATOR__OPERATOR_SYMBOL = eINSTANCE.getInvalidOperator_OperatorSymbol();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INVALID_OPERATOR__TYPE = eINSTANCE.getInvalidOperator_Type();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperandImpl <em>Invalid Operand</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.InvalidOperandImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidOperand()
		 * @generated
		 */
		EClass INVALID_OPERAND = eINSTANCE.getInvalidOperand();

		/**
		 * The meta object literal for the '<em><b>Operator Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVALID_OPERAND__OPERATOR_SYMBOL = eINSTANCE.getInvalidOperand_OperatorSymbol();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INVALID_OPERAND__TYPE = eINSTANCE.getInvalidOperand_Type();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.NoContainerForRefImmediateCompositeImpl <em>No Container For Ref Immediate Composite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.NoContainerForRefImmediateCompositeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoContainerForRefImmediateComposite()
		 * @generated
		 */
		EClass NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE = eINSTANCE.getNoContainerForRefImmediateComposite();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__CLASS_NAME = eINSTANCE.getNoContainerForRefImmediateComposite_ClassName();

		/**
		 * The meta object literal for the '<em><b>Metamodel Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO_CONTAINER_FOR_REF_IMMEDIATE_COMPOSITE__METAMODEL_NAME = eINSTANCE.getNoContainerForRefImmediateComposite_MetamodelName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.DifferentBranchTypesImpl <em>Different Branch Types</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.DifferentBranchTypesImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getDifferentBranchTypes()
		 * @generated
		 */
		EClass DIFFERENT_BRANCH_TYPES = eINSTANCE.getDifferentBranchTypes();

		/**
		 * The meta object literal for the '<em><b>Then Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIFFERENT_BRANCH_TYPES__THEN_TYPE = eINSTANCE.getDifferentBranchTypes_ThenType();

		/**
		 * The meta object literal for the '<em><b>Else Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIFFERENT_BRANCH_TYPES__ELSE_TYPE = eINSTANCE.getDifferentBranchTypes_ElseType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingProblemImpl <em>Binding Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BindingProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingProblem()
		 * @generated
		 */
		EClass BINDING_PROBLEM = eINSTANCE.getBindingProblem();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_PROBLEM__FEATURE = eINSTANCE.getBindingProblem_Feature();

		/**
		 * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINDING_PROBLEM__FEATURE_NAME = eINSTANCE.getBindingProblem_FeatureName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempProblemImpl <em>Resolve Temp Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempProblem()
		 * @generated
		 */
		EClass RESOLVE_TEMP_PROBLEM = eINSTANCE.getResolveTempProblem();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.AssignmentToReadonlyFeatureImpl <em>Assignment To Readonly Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.AssignmentToReadonlyFeatureImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAssignmentToReadonlyFeature()
		 * @generated
		 */
		EClass ASSIGNMENT_TO_READONLY_FEATURE = eINSTANCE.getAssignmentToReadonlyFeature();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl <em>No Binding For Compulsory Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoBindingForCompulsoryFeature()
		 * @generated
		 */
		EClass NO_BINDING_FOR_COMPULSORY_FEATURE = eINSTANCE.getNoBindingForCompulsoryFeature();

		/**
		 * The meta object literal for the '<em><b>Subrule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE = eINSTANCE.getNoBindingForCompulsoryFeature_Subrule();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO_BINDING_FOR_COMPULSORY_FEATURE__KIND = eINSTANCE.getNoBindingForCompulsoryFeature_Kind();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidAssignmentImperativeBindingImpl <em>Invalid Assignment Imperative Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.InvalidAssignmentImperativeBindingImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidAssignmentImperativeBinding()
		 * @generated
		 */
		EClass INVALID_ASSIGNMENT_IMPERATIVE_BINDING = eINSTANCE.getInvalidAssignmentImperativeBinding();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingExpectedOneAssignedManyImpl <em>Binding Expected One Assigned Many</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BindingExpectedOneAssignedManyImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingExpectedOneAssignedMany()
		 * @generated
		 */
		EClass BINDING_EXPECTED_ONE_ASSIGNED_MANY = eINSTANCE.getBindingExpectedOneAssignedMany();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingButObjectAssignedImpl <em>Primitive Binding But Object Assigned</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingButObjectAssignedImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getPrimitiveBindingButObjectAssigned()
		 * @generated
		 */
		EClass PRIMITIVE_BINDING_BUT_OBJECT_ASSIGNED = eINSTANCE.getPrimitiveBindingButObjectAssigned();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ObjectBindingButPrimitiveAssignedImpl <em>Object Binding But Primitive Assigned</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ObjectBindingButPrimitiveAssignedImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getObjectBindingButPrimitiveAssigned()
		 * @generated
		 */
		EClass OBJECT_BINDING_BUT_PRIMITIVE_ASSIGNED = eINSTANCE.getObjectBindingButPrimitiveAssigned();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingInvalidAssignmentImpl <em>Primitive Binding Invalid Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.PrimitiveBindingInvalidAssignmentImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getPrimitiveBindingInvalidAssignment()
		 * @generated
		 */
		EClass PRIMITIVE_BINDING_INVALID_ASSIGNMENT = eINSTANCE.getPrimitiveBindingInvalidAssignment();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingResolutionImpl <em>Binding Resolution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BindingResolutionImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingResolution()
		 * @generated
		 */
		EClass BINDING_RESOLUTION = eINSTANCE.getBindingResolution();

		/**
		 * The meta object literal for the '<em><b>Right Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_RESOLUTION__RIGHT_TYPE = eINSTANCE.getBindingResolution_RightType();

		/**
		 * The meta object literal for the '<em><b>Target Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_RESOLUTION__TARGET_TYPE = eINSTANCE.getBindingResolution_TargetType();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_RESOLUTION__RIGHT = eINSTANCE.getBindingResolution_Right();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_RESOLUTION__LEFT = eINSTANCE.getBindingResolution_Left();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_RESOLUTION__RULES = eINSTANCE.getBindingResolution_Rules();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingWithoutRuleImpl <em>Binding Without Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BindingWithoutRuleImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingWithoutRule()
		 * @generated
		 */
		EClass BINDING_WITHOUT_RULE = eINSTANCE.getBindingWithoutRule();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingWithResolvedByIncompatibleRuleImpl <em>Binding With Resolved By Incompatible Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BindingWithResolvedByIncompatibleRuleImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingWithResolvedByIncompatibleRule()
		 * @generated
		 */
		EClass BINDING_WITH_RESOLVED_BY_INCOMPATIBLE_RULE = eINSTANCE.getBindingWithResolvedByIncompatibleRule();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingPossiblyUnresolvedImpl <em>Binding Possibly Unresolved</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BindingPossiblyUnresolvedImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingPossiblyUnresolved()
		 * @generated
		 */
		EClass BINDING_POSSIBLY_UNRESOLVED = eINSTANCE.getBindingPossiblyUnresolved();

		/**
		 * The meta object literal for the '<em><b>Problematic Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES = eINSTANCE.getBindingPossiblyUnresolved_ProblematicClasses();

		/**
		 * The meta object literal for the '<em><b>Problematic Classes Implicit</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES_IMPLICIT = eINSTANCE.getBindingPossiblyUnresolved_ProblematicClassesImplicit();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl <em>Resolved Rule Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolvedRuleInfo()
		 * @generated
		 */
		EClass RESOLVED_RULE_INFO = eINSTANCE.getResolvedRuleInfo();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOLVED_RULE_INFO__LOCATION = eINSTANCE.getResolvedRuleInfo_Location();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVED_RULE_INFO__ELEMENT = eINSTANCE.getResolvedRuleInfo_Element();

		/**
		 * The meta object literal for the '<em><b>All Involved Rules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVED_RULE_INFO__ALL_INVOLVED_RULES = eINSTANCE.getResolvedRuleInfo_AllInvolvedRules();

		/**
		 * The meta object literal for the '<em><b>Rule Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOLVED_RULE_INFO__RULE_NAME = eINSTANCE.getResolvedRuleInfo_RuleName();

		/**
		 * The meta object literal for the '<em><b>Input Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVED_RULE_INFO__INPUT_TYPE = eINSTANCE.getResolvedRuleInfo_InputType();

		/**
		 * The meta object literal for the '<em><b>Output Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVED_RULE_INFO__OUTPUT_TYPE = eINSTANCE.getResolvedRuleInfo_OutputType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempWithoutRuleImpl <em>Resolve Temp Without Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempWithoutRuleImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempWithoutRule()
		 * @generated
		 */
		EClass RESOLVE_TEMP_WITHOUT_RULE = eINSTANCE.getResolveTempWithoutRule();

		/**
		 * The meta object literal for the '<em><b>Source Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_WITHOUT_RULE__SOURCE_TYPE = eINSTANCE.getResolveTempWithoutRule_SourceType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempPossiblyUnresolvedImpl <em>Resolve Temp Possibly Unresolved</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempPossiblyUnresolvedImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempPossiblyUnresolved()
		 * @generated
		 */
		EClass RESOLVE_TEMP_POSSIBLY_UNRESOLVED = eINSTANCE.getResolveTempPossiblyUnresolved();

		/**
		 * The meta object literal for the '<em><b>Problematic Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES = eINSTANCE.getResolveTempPossiblyUnresolved_ProblematicClasses();

		/**
		 * The meta object literal for the '<em><b>Problematic Classes Implicit</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_POSSIBLY_UNRESOLVED__PROBLEMATIC_CLASSES_IMPLICIT = eINSTANCE.getResolveTempPossiblyUnresolved_ProblematicClassesImplicit();

		/**
		 * The meta object literal for the '<em><b>Resolved Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_POSSIBLY_UNRESOLVED__RESOLVED_EXPRESSION = eINSTANCE.getResolveTempPossiblyUnresolved_ResolvedExpression();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempOutputPatternElementNotFoundImpl <em>Resolve Temp Output Pattern Element Not Found</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ResolveTempOutputPatternElementNotFoundImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getResolveTempOutputPatternElementNotFound()
		 * @generated
		 */
		EClass RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND = eINSTANCE.getResolveTempOutputPatternElementNotFound();

		/**
		 * The meta object literal for the '<em><b>Source Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE = eINSTANCE.getResolveTempOutputPatternElementNotFound_SourceType();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES = eINSTANCE.getResolveTempOutputPatternElementNotFound_Rules();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.FlattenOverNonNestedCollectionImpl <em>Flatten Over Non Nested Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.FlattenOverNonNestedCollectionImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getFlattenOverNonNestedCollection()
		 * @generated
		 */
		EClass FLATTEN_OVER_NON_NESTED_COLLECTION = eINSTANCE.getFlattenOverNonNestedCollection();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ChangeSelectFirstForAnyImpl <em>Change Select First For Any</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ChangeSelectFirstForAnyImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getChangeSelectFirstForAny()
		 * @generated
		 */
		EClass CHANGE_SELECT_FIRST_FOR_ANY = eINSTANCE.getChangeSelectFirstForAny();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.IteratorOverEmptySequenceImpl <em>Iterator Over Empty Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.IteratorOverEmptySequenceImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIteratorOverEmptySequence()
		 * @generated
		 */
		EClass ITERATOR_OVER_EMPTY_SEQUENCE = eINSTANCE.getIteratorOverEmptySequence();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperatorUsageImpl <em>Invalid Operator Usage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.InvalidOperatorUsageImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidOperatorUsage()
		 * @generated
		 */
		EClass INVALID_OPERATOR_USAGE = eINSTANCE.getInvalidOperatorUsage();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ReadingTargetModelImpl <em>Reading Target Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ReadingTargetModelImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getReadingTargetModel()
		 * @generated
		 */
		EClass READING_TARGET_MODEL = eINSTANCE.getReadingTargetModel();

		/**
		 * The meta object literal for the '<em><b>Model Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute READING_TARGET_MODEL__MODEL_NAME = eINSTANCE.getReadingTargetModel_ModelName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.WritingSourceModelImpl <em>Writing Source Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.WritingSourceModelImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getWritingSourceModel()
		 * @generated
		 */
		EClass WRITING_SOURCE_MODEL = eINSTANCE.getWritingSourceModel();

		/**
		 * The meta object literal for the '<em><b>Model Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WRITING_SOURCE_MODEL__MODEL_NAME = eINSTANCE.getWritingSourceModel_ModelName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.LazyRuleWithFilterImpl <em>Lazy Rule With Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.LazyRuleWithFilterImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getLazyRuleWithFilter()
		 * @generated
		 */
		EClass LAZY_RULE_WITH_FILTER = eINSTANCE.getLazyRuleWithFilter();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidRuleInheritanceImpl <em>Invalid Rule Inheritance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.InvalidRuleInheritanceImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidRuleInheritance()
		 * @generated
		 */
		EClass INVALID_RULE_INHERITANCE = eINSTANCE.getInvalidRuleInheritance();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVALID_RULE_INHERITANCE__KIND = eINSTANCE.getInvalidRuleInheritance_Kind();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.AmbiguousTargetModelReferenceImpl <em>Ambiguous Target Model Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.AmbiguousTargetModelReferenceImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAmbiguousTargetModelReference()
		 * @generated
		 */
		EClass AMBIGUOUS_TARGET_MODEL_REFERENCE = eINSTANCE.getAmbiguousTargetModelReference();

		/**
		 * The meta object literal for the '<em><b>Model Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AMBIGUOUS_TARGET_MODEL_REFERENCE__MODEL_NAME = eINSTANCE.getAmbiguousTargetModelReference_ModelName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.NoModelFoundImpl <em>No Model Found</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.NoModelFoundImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoModelFound()
		 * @generated
		 */
		EClass NO_MODEL_FOUND = eINSTANCE.getNoModelFound();

		/**
		 * The meta object literal for the '<em><b>Model Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO_MODEL_FOUND__MODEL_NAME = eINSTANCE.getNoModelFound_ModelName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.NoEnumLiteralImpl <em>No Enum Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.NoEnumLiteralImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoEnumLiteral()
		 * @generated
		 */
		EClass NO_ENUM_LITERAL = eINSTANCE.getNoEnumLiteral();

		/**
		 * The meta object literal for the '<em><b>Literal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO_ENUM_LITERAL__LITERAL = eINSTANCE.getNoEnumLiteral_Literal();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.WrongTypeImpl <em>Wrong Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.WrongTypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getWrongType()
		 * @generated
		 */
		EClass WRONG_TYPE = eINSTANCE.getWrongType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.IteratorBodyWrongTypeImpl <em>Iterator Body Wrong Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.IteratorBodyWrongTypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIteratorBodyWrongType()
		 * @generated
		 */
		EClass ITERATOR_BODY_WRONG_TYPE = eINSTANCE.getIteratorBodyWrongType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.MatchedRuleWithoutOutputPatternImpl <em>Matched Rule Without Output Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.MatchedRuleWithoutOutputPatternImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getMatchedRuleWithoutOutputPattern()
		 * @generated
		 */
		EClass MATCHED_RULE_WITHOUT_OUTPUT_PATTERN = eINSTANCE.getMatchedRuleWithoutOutputPattern();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.MatchedRuleFilterNonBooleanImpl <em>Matched Rule Filter Non Boolean</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.MatchedRuleFilterNonBooleanImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getMatchedRuleFilterNonBoolean()
		 * @generated
		 */
		EClass MATCHED_RULE_FILTER_NON_BOOLEAN = eINSTANCE.getMatchedRuleFilterNonBoolean();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.ExpectedCollectionInForEachImpl <em>Expected Collection In For Each</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.ExpectedCollectionInForEachImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getExpectedCollectionInForEach()
		 * @generated
		 */
		EClass EXPECTED_COLLECTION_IN_FOR_EACH = eINSTANCE.getExpectedCollectionInForEach();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.NoClassFoundInMetamodelImpl <em>No Class Found In Metamodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.NoClassFoundInMetamodelImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoClassFoundInMetamodel()
		 * @generated
		 */
		EClass NO_CLASS_FOUND_IN_METAMODEL = eINSTANCE.getNoClassFoundInMetamodel();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO_CLASS_FOUND_IN_METAMODEL__CLASS_NAME = eINSTANCE.getNoClassFoundInMetamodel_ClassName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.InvalidArgumentImpl <em>Invalid Argument</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.InvalidArgumentImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidArgument()
		 * @generated
		 */
		EClass INVALID_ARGUMENT = eINSTANCE.getInvalidArgument();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.CollectionOperationNotFoundImpl <em>Collection Operation Not Found</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.CollectionOperationNotFoundImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getCollectionOperationNotFound()
		 * @generated
		 */
		EClass COLLECTION_OPERATION_NOT_FOUND = eINSTANCE.getCollectionOperationNotFound();

		/**
		 * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_OPERATION_NOT_FOUND__OPERATION_NAME = eINSTANCE.getCollectionOperationNotFound_OperationName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.IteratorOverNoCollectionTypeImpl <em>Iterator Over No Collection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.IteratorOverNoCollectionTypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIteratorOverNoCollectionType()
		 * @generated
		 */
		EClass ITERATOR_OVER_NO_COLLECTION_TYPE = eINSTANCE.getIteratorOverNoCollectionType();

		/**
		 * The meta object literal for the '<em><b>Iterator Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITERATOR_OVER_NO_COLLECTION_TYPE__ITERATOR_NAME = eINSTANCE.getIteratorOverNoCollectionType_IteratorName();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.AbstractIncoherentVariableDeclarationImpl <em>Abstract Incoherent Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.AbstractIncoherentVariableDeclarationImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAbstractIncoherentVariableDeclaration()
		 * @generated
		 */
		EClass ABSTRACT_INCOHERENT_VARIABLE_DECLARATION = eINSTANCE.getAbstractIncoherentVariableDeclaration();

		/**
		 * The meta object literal for the '<em><b>Inferred</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED = eINSTANCE.getAbstractIncoherentVariableDeclaration_Inferred();

		/**
		 * The meta object literal for the '<em><b>Declared</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED = eINSTANCE.getAbstractIncoherentVariableDeclaration_Declared();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OclComplianceImpl <em>Ocl Compliance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OclComplianceImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOclCompliance()
		 * @generated
		 */
		EClass OCL_COMPLIANCE = eINSTANCE.getOclCompliance();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OclComplianceProblemImpl <em>Ocl Compliance Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OclComplianceProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOclComplianceProblem()
		 * @generated
		 */
		EClass OCL_COMPLIANCE_PROBLEM = eINSTANCE.getOclComplianceProblem();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.IncoherentVariableDeclarationImpl <em>Incoherent Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.IncoherentVariableDeclarationImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIncoherentVariableDeclaration()
		 * @generated
		 */
		EClass INCOHERENT_VARIABLE_DECLARATION = eINSTANCE.getIncoherentVariableDeclaration();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.IncoherentHelperReturnTypeImpl <em>Incoherent Helper Return Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.IncoherentHelperReturnTypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getIncoherentHelperReturnType()
		 * @generated
		 */
		EClass INCOHERENT_HELPER_RETURN_TYPE = eINSTANCE.getIncoherentHelperReturnType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.OperationOverCollectionTypeImpl <em>Operation Over Collection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.OperationOverCollectionTypeImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getOperationOverCollectionType()
		 * @generated
		 */
		EClass OPERATION_OVER_COLLECTION_TYPE = eINSTANCE.getOperationOverCollectionType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValueImpl <em>Access To Undefined Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValueImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAccessToUndefinedValue()
		 * @generated
		 */
		EClass ACCESS_TO_UNDEFINED_VALUE = eINSTANCE.getAccessToUndefinedValue();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValue_ThroughEmptyCollectionImpl <em>Access To Undefined Value Through Empty Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValue_ThroughEmptyCollectionImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAccessToUndefinedValue_ThroughEmptyCollection()
		 * @generated
		 */
		EClass ACCESS_TO_UNDEFINED_VALUE_THROUGH_EMPTY_COLLECTION = eINSTANCE.getAccessToUndefinedValue_ThroughEmptyCollection();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.RuleConflictsImpl <em>Rule Conflicts</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.RuleConflictsImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getRuleConflicts()
		 * @generated
		 */
		EClass RULE_CONFLICTS = eINSTANCE.getRuleConflicts();

		/**
		 * The meta object literal for the '<em><b>Conflicts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_CONFLICTS__CONFLICTS = eINSTANCE.getRuleConflicts_Conflicts();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.BindingInplaceInvalidImpl <em>Binding Inplace Invalid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.BindingInplaceInvalidImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getBindingInplaceInvalid()
		 * @generated
		 */
		EClass BINDING_INPLACE_INVALID = eINSTANCE.getBindingInplaceInvalid();

		/**
		 * The meta object literal for the '<em><b>Right Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_INPLACE_INVALID__RIGHT_TYPE = eINSTANCE.getBindingInplaceInvalid_RightType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.CannotInstantiateAbstractClassImpl <em>Cannot Instantiate Abstract Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.CannotInstantiateAbstractClassImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getCannotInstantiateAbstractClass()
		 * @generated
		 */
		EClass CANNOT_INSTANTIATE_ABSTRACT_CLASS = eINSTANCE.getCannotInstantiateAbstractClass();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CANNOT_INSTANTIATE_ABSTRACT_CLASS__TYPE = eINSTANCE.getCannotInstantiateAbstractClass_Type();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.GenericLocalProblemImpl <em>Generic Local Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.GenericLocalProblemImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getGenericLocalProblem()
		 * @generated
		 */
		EClass GENERIC_LOCAL_PROBLEM = eINSTANCE.getGenericLocalProblem();

		/**
		 * The meta object literal for the '<em><b>Generic Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_LOCAL_PROBLEM__GENERIC_KIND = eINSTANCE.getGenericLocalProblem_GenericKind();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.impl.AtlParseErrorImpl <em>Atl Parse Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlParseErrorImpl
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getAtlParseError()
		 * @generated
		 */
		EClass ATL_PARSE_ERROR = eINSTANCE.getAtlParseError();

		/**
		 * The meta object literal for the '<em><b>Generic Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATL_PARSE_ERROR__GENERIC_KIND = eINSTANCE.getAtlParseError_GenericKind();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind <em>No Binding For Compulsory Feature Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getNoBindingForCompulsoryFeatureKind()
		 * @generated
		 */
		EEnum NO_BINDING_FOR_COMPULSORY_FEATURE_KIND = eINSTANCE.getNoBindingForCompulsoryFeatureKind();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind <em>Invalid Rule Inheritance Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind
		 * @see anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl#getInvalidRuleInheritanceKind()
		 * @generated
		 */
		EEnum INVALID_RULE_INHERITANCE_KIND = eINSTANCE.getInvalidRuleInheritanceKind();

	}

} //AtlErrorPackage
