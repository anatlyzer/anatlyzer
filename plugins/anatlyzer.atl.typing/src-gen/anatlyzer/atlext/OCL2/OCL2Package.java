/**
 */
package anatlyzer.atlext.OCL2;

import anatlyzer.atlext.OCL.OCLPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see anatlyzer.atlext.OCL2.OCL2Factory
 * @model kind="package"
 * @generated
 */
public interface OCL2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "OCL2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/atl/ext/OCL2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_ext_ocl2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OCL2Package eINSTANCE = anatlyzer.atlext.OCL2.impl.OCL2PackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL2.impl.SelectByKindImpl <em>Select By Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL2.impl.SelectByKindImpl
	 * @see anatlyzer.atlext.OCL2.impl.OCL2PackageImpl#getSelectByKind()
	 * @generated
	 */
	int SELECT_BY_KIND = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__LOCATION = OCLPackage.COLLECTION_OPERATION_CALL_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__COMMENTS_BEFORE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__COMMENTS_AFTER = OCLPackage.COLLECTION_OPERATION_CALL_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__FILE_LOCATION = OCLPackage.COLLECTION_OPERATION_CALL_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__FILE_OBJECT = OCLPackage.COLLECTION_OPERATION_CALL_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__PROBLEMS = OCLPackage.COLLECTION_OPERATION_CALL_EXP__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__ANNOTATIONS = OCLPackage.COLLECTION_OPERATION_CALL_EXP__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__INFERRED_TYPE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__TYPE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__IF_EXP3 = OCLPackage.COLLECTION_OPERATION_CALL_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__APPLIED_PROPERTY = OCLPackage.COLLECTION_OPERATION_CALL_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__COLLECTION = OCLPackage.COLLECTION_OPERATION_CALL_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__LET_EXP = OCLPackage.COLLECTION_OPERATION_CALL_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__LOOP_EXP = OCLPackage.COLLECTION_OPERATION_CALL_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__PARENT_OPERATION = OCLPackage.COLLECTION_OPERATION_CALL_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__INITIALIZED_VARIABLE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__IF_EXP2 = OCLPackage.COLLECTION_OPERATION_CALL_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__OWNING_OPERATION = OCLPackage.COLLECTION_OPERATION_CALL_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__IF_EXP1 = OCLPackage.COLLECTION_OPERATION_CALL_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__OWNING_ATTRIBUTE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__IMPLICITLY_CASTED = OCLPackage.COLLECTION_OPERATION_CALL_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__NO_CASTED_TYPE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__SOURCE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__USED_FEATURE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Subtype Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__SUBTYPE_FEATURES = OCLPackage.COLLECTION_OPERATION_CALL_EXP__SUBTYPE_FEATURES;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__RECEPTOR_TYPE = OCLPackage.COLLECTION_OPERATION_CALL_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__IS_STATIC_CALL = OCLPackage.COLLECTION_OPERATION_CALL_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__STATIC_RESOLVER = OCLPackage.COLLECTION_OPERATION_CALL_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__DYNAMIC_RESOLVERS = OCLPackage.COLLECTION_OPERATION_CALL_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__ARGUMENTS = OCLPackage.COLLECTION_OPERATION_CALL_EXP__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__OPERATION_NAME = OCLPackage.COLLECTION_OPERATION_CALL_EXP__OPERATION_NAME;

	/**
	 * The feature id for the '<em><b>Resolve Temp Resolved By</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__RESOLVE_TEMP_RESOLVED_BY = OCLPackage.COLLECTION_OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY;

	/**
	 * The feature id for the '<em><b>Is Exact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND__IS_EXACT = OCLPackage.COLLECTION_OPERATION_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Select By Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND_FEATURE_COUNT = OCLPackage.COLLECTION_OPERATION_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Select By Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_BY_KIND_OPERATION_COUNT = OCLPackage.COLLECTION_OPERATION_CALL_EXP_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL2.SelectByKind <em>Select By Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Select By Kind</em>'.
	 * @see anatlyzer.atlext.OCL2.SelectByKind
	 * @generated
	 */
	EClass getSelectByKind();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL2.SelectByKind#isIsExact <em>Is Exact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Exact</em>'.
	 * @see anatlyzer.atlext.OCL2.SelectByKind#isIsExact()
	 * @see #getSelectByKind()
	 * @generated
	 */
	EAttribute getSelectByKind_IsExact();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OCL2Factory getOCL2Factory();

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
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL2.impl.SelectByKindImpl <em>Select By Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL2.impl.SelectByKindImpl
		 * @see anatlyzer.atlext.OCL2.impl.OCL2PackageImpl#getSelectByKind()
		 * @generated
		 */
		EClass SELECT_BY_KIND = eINSTANCE.getSelectByKind();
		/**
		 * The meta object literal for the '<em><b>Is Exact</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SELECT_BY_KIND__IS_EXACT = eINSTANCE.getSelectByKind_IsExact();

	}

} //OCL2Package
