/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.OCLPackage;

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
 * @see anatlyzer.atlext.ATL.ATLFactory
 * @model kind="package"
 * @generated
 */
public interface ATLPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ATL";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/atlext/ATL";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_ext_atl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ATLPackage eINSTANCE = anatlyzer.atlext.ATL.impl.ATLPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.LocatedElementImpl <em>Located Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.LocatedElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLocatedElement()
	 * @generated
	 */
	int LOCATED_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__COMMENTS_BEFORE = 1;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__COMMENTS_AFTER = 2;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__FILE_LOCATION = 3;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__FILE_OBJECT = 4;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__PROBLEMS = 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__ANNOTATIONS = 6;

	/**
	 * The number of structural features of the '<em>Located Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Located Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.UnitImpl <em>Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.UnitImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getUnit()
	 * @generated
	 */
	int UNIT = 1;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Libraries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__LIBRARIES = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__NAME = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.LibraryImpl <em>Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.LibraryImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLibrary()
	 * @generated
	 */
	int LIBRARY = 2;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__LOCATION = UNIT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__COMMENTS_BEFORE = UNIT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__COMMENTS_AFTER = UNIT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__FILE_LOCATION = UNIT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__FILE_OBJECT = UNIT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__PROBLEMS = UNIT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__ANNOTATIONS = UNIT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Libraries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__LIBRARIES = UNIT__LIBRARIES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__NAME = UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Helpers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__HELPERS = UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_FEATURE_COUNT = UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_OPERATION_COUNT = UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.QueryImpl <em>Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.QueryImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getQuery()
	 * @generated
	 */
	int QUERY = 3;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__LOCATION = UNIT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__COMMENTS_BEFORE = UNIT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__COMMENTS_AFTER = UNIT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__FILE_LOCATION = UNIT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__FILE_OBJECT = UNIT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__PROBLEMS = UNIT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__ANNOTATIONS = UNIT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Libraries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__LIBRARIES = UNIT__LIBRARIES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__NAME = UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__BODY = UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Helpers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__HELPERS = UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_FEATURE_COUNT = UNIT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_OPERATION_COUNT = UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ModuleImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getModule()
	 * @generated
	 */
	int MODULE = 4;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__LOCATION = UNIT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__COMMENTS_BEFORE = UNIT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__COMMENTS_AFTER = UNIT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__FILE_LOCATION = UNIT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__FILE_OBJECT = UNIT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__PROBLEMS = UNIT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__ANNOTATIONS = UNIT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Libraries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__LIBRARIES = UNIT__LIBRARIES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__NAME = UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Is Refining</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__IS_REFINING = UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Models</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__IN_MODELS = UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Out Models</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__OUT_MODELS = UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__ELEMENTS = UNIT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_FEATURE_COUNT = UNIT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_OPERATION_COUNT = UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ModuleElementImpl <em>Module Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ModuleElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getModuleElement()
	 * @generated
	 */
	int MODULE_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The number of structural features of the '<em>Module Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Module Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.HelperImpl <em>Helper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.HelperImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getHelper()
	 * @generated
	 */
	int HELPER = 6;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__LOCATION = MODULE_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__COMMENTS_BEFORE = MODULE_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__COMMENTS_AFTER = MODULE_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__FILE_LOCATION = MODULE_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__FILE_OBJECT = MODULE_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__PROBLEMS = MODULE_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__ANNOTATIONS = MODULE_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__CALLED_BY = MODULE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__CALLABLE_PARAMETERS = MODULE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Query</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__QUERY = MODULE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Library</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__LIBRARY = MODULE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__DEFINITION = MODULE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Has Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__HAS_CONTEXT = MODULE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Is Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__IS_ATTRIBUTE = MODULE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Inferred Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__INFERRED_RETURN_TYPE = MODULE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Static Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER__STATIC_RETURN_TYPE = MODULE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Helper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER_FEATURE_COUNT = MODULE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of operations of the '<em>Helper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELPER_OPERATION_COUNT = MODULE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.StaticHelperImpl <em>Static Helper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.StaticHelperImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStaticHelper()
	 * @generated
	 */
	int STATIC_HELPER = 7;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__LOCATION = HELPER__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__COMMENTS_BEFORE = HELPER__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__COMMENTS_AFTER = HELPER__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__FILE_LOCATION = HELPER__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__FILE_OBJECT = HELPER__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__PROBLEMS = HELPER__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__ANNOTATIONS = HELPER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__CALLED_BY = HELPER__CALLED_BY;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__CALLABLE_PARAMETERS = HELPER__CALLABLE_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Query</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__QUERY = HELPER__QUERY;

	/**
	 * The feature id for the '<em><b>Library</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__LIBRARY = HELPER__LIBRARY;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__DEFINITION = HELPER__DEFINITION;

	/**
	 * The feature id for the '<em><b>Has Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__HAS_CONTEXT = HELPER__HAS_CONTEXT;

	/**
	 * The feature id for the '<em><b>Is Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__IS_ATTRIBUTE = HELPER__IS_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Inferred Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__INFERRED_RETURN_TYPE = HELPER__INFERRED_RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Static Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER__STATIC_RETURN_TYPE = HELPER__STATIC_RETURN_TYPE;

	/**
	 * The number of structural features of the '<em>Static Helper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER_FEATURE_COUNT = HELPER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Static Helper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_HELPER_OPERATION_COUNT = HELPER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ContextHelperImpl <em>Context Helper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ContextHelperImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getContextHelper()
	 * @generated
	 */
	int CONTEXT_HELPER = 8;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__LOCATION = HELPER__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__COMMENTS_BEFORE = HELPER__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__COMMENTS_AFTER = HELPER__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__FILE_LOCATION = HELPER__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__FILE_OBJECT = HELPER__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__PROBLEMS = HELPER__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__ANNOTATIONS = HELPER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__CALLED_BY = HELPER__CALLED_BY;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__CALLABLE_PARAMETERS = HELPER__CALLABLE_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Query</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__QUERY = HELPER__QUERY;

	/**
	 * The feature id for the '<em><b>Library</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__LIBRARY = HELPER__LIBRARY;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__DEFINITION = HELPER__DEFINITION;

	/**
	 * The feature id for the '<em><b>Has Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__HAS_CONTEXT = HELPER__HAS_CONTEXT;

	/**
	 * The feature id for the '<em><b>Is Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__IS_ATTRIBUTE = HELPER__IS_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Inferred Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__INFERRED_RETURN_TYPE = HELPER__INFERRED_RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Static Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__STATIC_RETURN_TYPE = HELPER__STATIC_RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Context Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__CONTEXT_TYPE = HELPER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Polymorphic Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER__POLYMORPHIC_CALLED_BY = HELPER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Context Helper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER_FEATURE_COUNT = HELPER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Context Helper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HELPER_OPERATION_COUNT = HELPER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.RuleImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 9;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__LOCATION = MODULE_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__COMMENTS_BEFORE = MODULE_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__COMMENTS_AFTER = MODULE_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__FILE_LOCATION = MODULE_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__FILE_OBJECT = MODULE_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__PROBLEMS = MODULE_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ANNOTATIONS = MODULE_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__OUT_PATTERN = MODULE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ACTION_BLOCK = MODULE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__VARIABLES = MODULE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__NAME = MODULE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = MODULE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_OPERATION_COUNT = MODULE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.CallableImpl <em>Callable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.CallableImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getCallable()
	 * @generated
	 */
	int CALLABLE = 12;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE__CALLED_BY = 0;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE__CALLABLE_PARAMETERS = 1;

	/**
	 * The number of structural features of the '<em>Callable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Callable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ModuleCallableImpl <em>Module Callable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ModuleCallableImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getModuleCallable()
	 * @generated
	 */
	int MODULE_CALLABLE = 11;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE__CALLED_BY = CALLABLE__CALLED_BY;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE__CALLABLE_PARAMETERS = CALLABLE__CALLABLE_PARAMETERS;

	/**
	 * The number of structural features of the '<em>Module Callable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE_FEATURE_COUNT = CALLABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Module Callable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE_OPERATION_COUNT = CALLABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl <em>Static Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.StaticRuleImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStaticRule()
	 * @generated
	 */
	int STATIC_RULE = 10;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__CALLED_BY = MODULE_CALLABLE__CALLED_BY;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__CALLABLE_PARAMETERS = MODULE_CALLABLE__CALLABLE_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__LOCATION = MODULE_CALLABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__COMMENTS_BEFORE = MODULE_CALLABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__COMMENTS_AFTER = MODULE_CALLABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__FILE_LOCATION = MODULE_CALLABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__FILE_OBJECT = MODULE_CALLABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__PROBLEMS = MODULE_CALLABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__ANNOTATIONS = MODULE_CALLABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__OUT_PATTERN = MODULE_CALLABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Action Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__ACTION_BLOCK = MODULE_CALLABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__VARIABLES = MODULE_CALLABLE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE__NAME = MODULE_CALLABLE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Static Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE_FEATURE_COUNT = MODULE_CALLABLE_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>Static Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_RULE_OPERATION_COUNT = MODULE_CALLABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl <em>Rule With Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.RuleWithPatternImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleWithPattern()
	 * @generated
	 */
	int RULE_WITH_PATTERN = 13;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__LOCATION = RULE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__COMMENTS_BEFORE = RULE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__COMMENTS_AFTER = RULE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__FILE_LOCATION = RULE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__FILE_OBJECT = RULE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__PROBLEMS = RULE__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__ANNOTATIONS = RULE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__OUT_PATTERN = RULE__OUT_PATTERN;

	/**
	 * The feature id for the '<em><b>Action Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__ACTION_BLOCK = RULE__ACTION_BLOCK;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__VARIABLES = RULE__VARIABLES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__NAME = RULE__NAME;

	/**
	 * The feature id for the '<em><b>In Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__IN_PATTERN = RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__CHILDREN = RULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Super Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__SUPER_RULE = RULE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__IS_ABSTRACT = RULE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Refining</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__IS_REFINING = RULE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is No Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN__IS_NO_DEFAULT = RULE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Rule With Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN_FEATURE_COUNT = RULE_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Rule With Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_WITH_PATTERN_OPERATION_COUNT = RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.MatchedRuleImpl <em>Matched Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.MatchedRuleImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getMatchedRule()
	 * @generated
	 */
	int MATCHED_RULE = 14;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__LOCATION = RULE_WITH_PATTERN__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__COMMENTS_BEFORE = RULE_WITH_PATTERN__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__COMMENTS_AFTER = RULE_WITH_PATTERN__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__FILE_LOCATION = RULE_WITH_PATTERN__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__FILE_OBJECT = RULE_WITH_PATTERN__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__PROBLEMS = RULE_WITH_PATTERN__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__ANNOTATIONS = RULE_WITH_PATTERN__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__OUT_PATTERN = RULE_WITH_PATTERN__OUT_PATTERN;

	/**
	 * The feature id for the '<em><b>Action Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__ACTION_BLOCK = RULE_WITH_PATTERN__ACTION_BLOCK;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__VARIABLES = RULE_WITH_PATTERN__VARIABLES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__NAME = RULE_WITH_PATTERN__NAME;

	/**
	 * The feature id for the '<em><b>In Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__IN_PATTERN = RULE_WITH_PATTERN__IN_PATTERN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__CHILDREN = RULE_WITH_PATTERN__CHILDREN;

	/**
	 * The feature id for the '<em><b>Super Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__SUPER_RULE = RULE_WITH_PATTERN__SUPER_RULE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__IS_ABSTRACT = RULE_WITH_PATTERN__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Is Refining</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__IS_REFINING = RULE_WITH_PATTERN__IS_REFINING;

	/**
	 * The feature id for the '<em><b>Is No Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE__IS_NO_DEFAULT = RULE_WITH_PATTERN__IS_NO_DEFAULT;

	/**
	 * The number of structural features of the '<em>Matched Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_FEATURE_COUNT = RULE_WITH_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Matched Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHED_RULE_OPERATION_COUNT = RULE_WITH_PATTERN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.LazyRuleImpl <em>Lazy Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.LazyRuleImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLazyRule()
	 * @generated
	 */
	int LAZY_RULE = 15;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__LOCATION = RULE_WITH_PATTERN__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__COMMENTS_BEFORE = RULE_WITH_PATTERN__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__COMMENTS_AFTER = RULE_WITH_PATTERN__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__FILE_LOCATION = RULE_WITH_PATTERN__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__FILE_OBJECT = RULE_WITH_PATTERN__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__PROBLEMS = RULE_WITH_PATTERN__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__ANNOTATIONS = RULE_WITH_PATTERN__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__OUT_PATTERN = RULE_WITH_PATTERN__OUT_PATTERN;

	/**
	 * The feature id for the '<em><b>Action Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__ACTION_BLOCK = RULE_WITH_PATTERN__ACTION_BLOCK;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__VARIABLES = RULE_WITH_PATTERN__VARIABLES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__NAME = RULE_WITH_PATTERN__NAME;

	/**
	 * The feature id for the '<em><b>In Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__IN_PATTERN = RULE_WITH_PATTERN__IN_PATTERN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__CHILDREN = RULE_WITH_PATTERN__CHILDREN;

	/**
	 * The feature id for the '<em><b>Super Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__SUPER_RULE = RULE_WITH_PATTERN__SUPER_RULE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__IS_ABSTRACT = RULE_WITH_PATTERN__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Is Refining</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__IS_REFINING = RULE_WITH_PATTERN__IS_REFINING;

	/**
	 * The feature id for the '<em><b>Is No Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__IS_NO_DEFAULT = RULE_WITH_PATTERN__IS_NO_DEFAULT;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__CALLED_BY = RULE_WITH_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__CALLABLE_PARAMETERS = RULE_WITH_PATTERN_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE__IS_UNIQUE = RULE_WITH_PATTERN_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Lazy Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_FEATURE_COUNT = RULE_WITH_PATTERN_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Lazy Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAZY_RULE_OPERATION_COUNT = RULE_WITH_PATTERN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.CalledRuleImpl <em>Called Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.CalledRuleImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getCalledRule()
	 * @generated
	 */
	int CALLED_RULE = 16;

	/**
	 * The feature id for the '<em><b>Called By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__CALLED_BY = STATIC_RULE__CALLED_BY;

	/**
	 * The feature id for the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__CALLABLE_PARAMETERS = STATIC_RULE__CALLABLE_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__LOCATION = STATIC_RULE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__COMMENTS_BEFORE = STATIC_RULE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__COMMENTS_AFTER = STATIC_RULE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__FILE_LOCATION = STATIC_RULE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__FILE_OBJECT = STATIC_RULE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__PROBLEMS = STATIC_RULE__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__ANNOTATIONS = STATIC_RULE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__OUT_PATTERN = STATIC_RULE__OUT_PATTERN;

	/**
	 * The feature id for the '<em><b>Action Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__ACTION_BLOCK = STATIC_RULE__ACTION_BLOCK;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__VARIABLES = STATIC_RULE__VARIABLES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__NAME = STATIC_RULE__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__PARAMETERS = STATIC_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Entrypoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__IS_ENTRYPOINT = STATIC_RULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE__IS_ENDPOINT = STATIC_RULE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Called Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE_FEATURE_COUNT = STATIC_RULE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Called Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLED_RULE_OPERATION_COUNT = STATIC_RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.InPatternImpl <em>In Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.InPatternImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getInPattern()
	 * @generated
	 */
	int IN_PATTERN = 17;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__ELEMENTS = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN__FILTER = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>In Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>In Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.OutPatternImpl <em>Out Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.OutPatternImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getOutPattern()
	 * @generated
	 */
	int OUT_PATTERN = 18;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__RULE = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Drop Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__DROP_PATTERN = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN__ELEMENTS = LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Out Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Out Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.DropPatternImpl <em>Drop Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.DropPatternImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getDropPattern()
	 * @generated
	 */
	int DROP_PATTERN = 19;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN__OUT_PATTERN = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Drop Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Drop Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_PATTERN_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.PatternElementImpl <em>Pattern Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.PatternElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getPatternElement()
	 * @generated
	 */
	int PATTERN_ELEMENT = 20;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__LOCATION = OCLPackage.VARIABLE_DECLARATION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__COMMENTS_BEFORE = OCLPackage.VARIABLE_DECLARATION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__COMMENTS_AFTER = OCLPackage.VARIABLE_DECLARATION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__FILE_LOCATION = OCLPackage.VARIABLE_DECLARATION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__FILE_OBJECT = OCLPackage.VARIABLE_DECLARATION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__PROBLEMS = OCLPackage.VARIABLE_DECLARATION__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__ANNOTATIONS = OCLPackage.VARIABLE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__INFERRED_TYPE = OCLPackage.VARIABLE_DECLARATION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__ID = OCLPackage.VARIABLE_DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__VAR_NAME = OCLPackage.VARIABLE_DECLARATION__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__TYPE = OCLPackage.VARIABLE_DECLARATION__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__INIT_EXPRESSION = OCLPackage.VARIABLE_DECLARATION__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__LET_EXP = OCLPackage.VARIABLE_DECLARATION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__BASE_EXP = OCLPackage.VARIABLE_DECLARATION__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__VARIABLE_EXP = OCLPackage.VARIABLE_DECLARATION__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT__STATIC_TYPE = OCLPackage.VARIABLE_DECLARATION__STATIC_TYPE;

	/**
	 * The number of structural features of the '<em>Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT_FEATURE_COUNT = OCLPackage.VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATTERN_ELEMENT_OPERATION_COUNT = OCLPackage.VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.InPatternElementImpl <em>In Pattern Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.InPatternElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getInPatternElement()
	 * @generated
	 */
	int IN_PATTERN_ELEMENT = 21;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__LOCATION = PATTERN_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__COMMENTS_BEFORE = PATTERN_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__COMMENTS_AFTER = PATTERN_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__FILE_LOCATION = PATTERN_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__FILE_OBJECT = PATTERN_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__PROBLEMS = PATTERN_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__ANNOTATIONS = PATTERN_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__INFERRED_TYPE = PATTERN_ELEMENT__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__ID = PATTERN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__VAR_NAME = PATTERN_ELEMENT__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__TYPE = PATTERN_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__INIT_EXPRESSION = PATTERN_ELEMENT__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__LET_EXP = PATTERN_ELEMENT__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__BASE_EXP = PATTERN_ELEMENT__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__VARIABLE_EXP = PATTERN_ELEMENT__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__STATIC_TYPE = PATTERN_ELEMENT__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Maps To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__MAPS_TO = PATTERN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Pattern</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__IN_PATTERN = PATTERN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Models</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT__MODELS = PATTERN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>In Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT_FEATURE_COUNT = PATTERN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>In Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PATTERN_ELEMENT_OPERATION_COUNT = PATTERN_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.SimpleInPatternElementImpl <em>Simple In Pattern Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.SimpleInPatternElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getSimpleInPatternElement()
	 * @generated
	 */
	int SIMPLE_IN_PATTERN_ELEMENT = 22;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__LOCATION = IN_PATTERN_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__COMMENTS_BEFORE = IN_PATTERN_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__COMMENTS_AFTER = IN_PATTERN_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__FILE_LOCATION = IN_PATTERN_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__FILE_OBJECT = IN_PATTERN_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__PROBLEMS = IN_PATTERN_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__ANNOTATIONS = IN_PATTERN_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__INFERRED_TYPE = IN_PATTERN_ELEMENT__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__ID = IN_PATTERN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__VAR_NAME = IN_PATTERN_ELEMENT__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__TYPE = IN_PATTERN_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__INIT_EXPRESSION = IN_PATTERN_ELEMENT__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__LET_EXP = IN_PATTERN_ELEMENT__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__BASE_EXP = IN_PATTERN_ELEMENT__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__VARIABLE_EXP = IN_PATTERN_ELEMENT__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__STATIC_TYPE = IN_PATTERN_ELEMENT__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Maps To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__MAPS_TO = IN_PATTERN_ELEMENT__MAPS_TO;

	/**
	 * The feature id for the '<em><b>In Pattern</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__IN_PATTERN = IN_PATTERN_ELEMENT__IN_PATTERN;

	/**
	 * The feature id for the '<em><b>Models</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT__MODELS = IN_PATTERN_ELEMENT__MODELS;

	/**
	 * The number of structural features of the '<em>Simple In Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT_FEATURE_COUNT = IN_PATTERN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Simple In Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_IN_PATTERN_ELEMENT_OPERATION_COUNT = IN_PATTERN_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.OutPatternElementImpl <em>Out Pattern Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.OutPatternElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getOutPatternElement()
	 * @generated
	 */
	int OUT_PATTERN_ELEMENT = 23;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__LOCATION = PATTERN_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__COMMENTS_BEFORE = PATTERN_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__COMMENTS_AFTER = PATTERN_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__FILE_LOCATION = PATTERN_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__FILE_OBJECT = PATTERN_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__PROBLEMS = PATTERN_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__ANNOTATIONS = PATTERN_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__INFERRED_TYPE = PATTERN_ELEMENT__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__ID = PATTERN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__VAR_NAME = PATTERN_ELEMENT__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__TYPE = PATTERN_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__INIT_EXPRESSION = PATTERN_ELEMENT__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__LET_EXP = PATTERN_ELEMENT__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__BASE_EXP = PATTERN_ELEMENT__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__VARIABLE_EXP = PATTERN_ELEMENT__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__STATIC_TYPE = PATTERN_ELEMENT__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__OUT_PATTERN = PATTERN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__SOURCE_ELEMENT = PATTERN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__BINDINGS = PATTERN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT__MODEL = PATTERN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Out Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT_FEATURE_COUNT = PATTERN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Out Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PATTERN_ELEMENT_OPERATION_COUNT = PATTERN_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.SimpleOutPatternElementImpl <em>Simple Out Pattern Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.SimpleOutPatternElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getSimpleOutPatternElement()
	 * @generated
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT = 24;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__LOCATION = OUT_PATTERN_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__COMMENTS_BEFORE = OUT_PATTERN_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__COMMENTS_AFTER = OUT_PATTERN_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__FILE_LOCATION = OUT_PATTERN_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__FILE_OBJECT = OUT_PATTERN_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__PROBLEMS = OUT_PATTERN_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__ANNOTATIONS = OUT_PATTERN_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__INFERRED_TYPE = OUT_PATTERN_ELEMENT__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__ID = OUT_PATTERN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__VAR_NAME = OUT_PATTERN_ELEMENT__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__TYPE = OUT_PATTERN_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__INIT_EXPRESSION = OUT_PATTERN_ELEMENT__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__LET_EXP = OUT_PATTERN_ELEMENT__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__BASE_EXP = OUT_PATTERN_ELEMENT__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__VARIABLE_EXP = OUT_PATTERN_ELEMENT__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__STATIC_TYPE = OUT_PATTERN_ELEMENT__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__OUT_PATTERN = OUT_PATTERN_ELEMENT__OUT_PATTERN;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__SOURCE_ELEMENT = OUT_PATTERN_ELEMENT__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__BINDINGS = OUT_PATTERN_ELEMENT__BINDINGS;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__MODEL = OUT_PATTERN_ELEMENT__MODEL;

	/**
	 * The feature id for the '<em><b>Reverse Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT__REVERSE_BINDINGS = OUT_PATTERN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Simple Out Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT_FEATURE_COUNT = OUT_PATTERN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Simple Out Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_OUT_PATTERN_ELEMENT_OPERATION_COUNT = OUT_PATTERN_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ForEachOutPatternElementImpl <em>For Each Out Pattern Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ForEachOutPatternElementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getForEachOutPatternElement()
	 * @generated
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT = 25;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__LOCATION = OUT_PATTERN_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__COMMENTS_BEFORE = OUT_PATTERN_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__COMMENTS_AFTER = OUT_PATTERN_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__FILE_LOCATION = OUT_PATTERN_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__FILE_OBJECT = OUT_PATTERN_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__PROBLEMS = OUT_PATTERN_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__ANNOTATIONS = OUT_PATTERN_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__INFERRED_TYPE = OUT_PATTERN_ELEMENT__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__ID = OUT_PATTERN_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__VAR_NAME = OUT_PATTERN_ELEMENT__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__TYPE = OUT_PATTERN_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__INIT_EXPRESSION = OUT_PATTERN_ELEMENT__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__LET_EXP = OUT_PATTERN_ELEMENT__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__BASE_EXP = OUT_PATTERN_ELEMENT__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__VARIABLE_EXP = OUT_PATTERN_ELEMENT__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__STATIC_TYPE = OUT_PATTERN_ELEMENT__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Out Pattern</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__OUT_PATTERN = OUT_PATTERN_ELEMENT__OUT_PATTERN;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__SOURCE_ELEMENT = OUT_PATTERN_ELEMENT__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__BINDINGS = OUT_PATTERN_ELEMENT__BINDINGS;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__MODEL = OUT_PATTERN_ELEMENT__MODEL;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION = OUT_PATTERN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR = OUT_PATTERN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>For Each Out Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT_FEATURE_COUNT = OUT_PATTERN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>For Each Out Pattern Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_OUT_PATTERN_ELEMENT_OPERATION_COUNT = OUT_PATTERN_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.BindingImpl <em>Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.BindingImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getBinding()
	 * @generated
	 */
	int BINDING = 26;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__VALUE = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out Pattern Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__OUT_PATTERN_ELEMENT = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__PROPERTY_NAME = LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Assignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__IS_ASSIGNMENT = LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Written Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__WRITTEN_FEATURE = LOCATED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Left Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__LEFT_TYPE = LOCATED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Resolved By</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING__RESOLVED_BY = LOCATED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.RuleVariableDeclarationImpl <em>Rule Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.RuleVariableDeclarationImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleVariableDeclaration()
	 * @generated
	 */
	int RULE_VARIABLE_DECLARATION = 27;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__LOCATION = OCLPackage.VARIABLE_DECLARATION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__COMMENTS_BEFORE = OCLPackage.VARIABLE_DECLARATION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__COMMENTS_AFTER = OCLPackage.VARIABLE_DECLARATION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__FILE_LOCATION = OCLPackage.VARIABLE_DECLARATION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__FILE_OBJECT = OCLPackage.VARIABLE_DECLARATION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__PROBLEMS = OCLPackage.VARIABLE_DECLARATION__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__ANNOTATIONS = OCLPackage.VARIABLE_DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__INFERRED_TYPE = OCLPackage.VARIABLE_DECLARATION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__ID = OCLPackage.VARIABLE_DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__VAR_NAME = OCLPackage.VARIABLE_DECLARATION__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__TYPE = OCLPackage.VARIABLE_DECLARATION__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__INIT_EXPRESSION = OCLPackage.VARIABLE_DECLARATION__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__LET_EXP = OCLPackage.VARIABLE_DECLARATION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__BASE_EXP = OCLPackage.VARIABLE_DECLARATION__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__VARIABLE_EXP = OCLPackage.VARIABLE_DECLARATION__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__STATIC_TYPE = OCLPackage.VARIABLE_DECLARATION__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION__RULE = OCLPackage.VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rule Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION_FEATURE_COUNT = OCLPackage.VARIABLE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Rule Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_VARIABLE_DECLARATION_OPERATION_COUNT = OCLPackage.VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.LibraryRefImpl <em>Library Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.LibraryRefImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLibraryRef()
	 * @generated
	 */
	int LIBRARY_REF = 28;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__UNIT = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF__NAME = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Library Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Library Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_REF_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ActionBlockImpl <em>Action Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ActionBlockImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getActionBlock()
	 * @generated
	 */
	int ACTION_BLOCK = 29;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__RULE = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK__STATEMENTS = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Action Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BLOCK_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.StatementImpl <em>Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.StatementImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStatement()
	 * @generated
	 */
	int STATEMENT = 30;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__LOCATION = LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__COMMENTS_BEFORE = LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__COMMENTS_AFTER = LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__FILE_LOCATION = LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__FILE_OBJECT = LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__PROBLEMS = LOCATED_ELEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__ANNOTATIONS = LOCATED_ELEMENT__ANNOTATIONS;

	/**
	 * The number of structural features of the '<em>Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ExpressionStatImpl <em>Expression Stat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ExpressionStatImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getExpressionStat()
	 * @generated
	 */
	int EXPRESSION_STAT = 31;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__LOCATION = STATEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__COMMENTS_BEFORE = STATEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__COMMENTS_AFTER = STATEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__FILE_LOCATION = STATEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__FILE_OBJECT = STATEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__PROBLEMS = STATEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Expression Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Expression Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STAT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.BindingStatImpl <em>Binding Stat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.BindingStatImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getBindingStat()
	 * @generated
	 */
	int BINDING_STAT = 32;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__LOCATION = STATEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__COMMENTS_BEFORE = STATEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__COMMENTS_AFTER = STATEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__FILE_LOCATION = STATEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__FILE_OBJECT = STATEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__PROBLEMS = STATEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__SOURCE = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__PROPERTY_NAME = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Assignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__IS_ASSIGNMENT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT__VALUE = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Binding Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Binding Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_STAT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.IfStatImpl <em>If Stat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.IfStatImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getIfStat()
	 * @generated
	 */
	int IF_STAT = 33;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__LOCATION = STATEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__COMMENTS_BEFORE = STATEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__COMMENTS_AFTER = STATEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__FILE_LOCATION = STATEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__FILE_OBJECT = STATEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__PROBLEMS = STATEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__CONDITION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Then Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__THEN_STATEMENTS = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Else Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT__ELSE_STATEMENTS = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>If Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STAT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.ForStatImpl <em>For Stat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.ForStatImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getForStat()
	 * @generated
	 */
	int FOR_STAT = 34;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__LOCATION = STATEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__COMMENTS_BEFORE = STATEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__COMMENTS_AFTER = STATEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__FILE_LOCATION = STATEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__FILE_OBJECT = STATEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__PROBLEMS = STATEMENT__PROBLEMS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__ITERATOR = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__COLLECTION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT__STATEMENTS = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>For Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>For Stat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STAT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.StringToStringMapImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStringToStringMap()
	 * @generated
	 */
	int STRING_TO_STRING_MAP = 35;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To String Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To String Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.CallableParameterImpl <em>Callable Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.CallableParameterImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getCallableParameter()
	 * @generated
	 */
	int CALLABLE_PARAMETER = 36;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_PARAMETER__STATIC_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Param Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_PARAMETER__PARAM_DECLARATION = 2;

	/**
	 * The number of structural features of the '<em>Callable Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_PARAMETER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Callable Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.impl.RuleResolutionInfoImpl <em>Rule Resolution Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.impl.RuleResolutionInfoImpl
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleResolutionInfo()
	 * @generated
	 */
	int RULE_RESOLUTION_INFO = 37;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_RESOLUTION_INFO__RULE = 0;

	/**
	 * The feature id for the '<em><b>All Involved Rules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES = 1;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_RESOLUTION_INFO__STATUS = 2;

	/**
	 * The number of structural features of the '<em>Rule Resolution Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_RESOLUTION_INFO_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Rule Resolution Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_RESOLUTION_INFO_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link anatlyzer.atlext.ATL.RuleResolutionStatus <em>Rule Resolution Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.ATL.RuleResolutionStatus
	 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleResolutionStatus()
	 * @generated
	 */
	int RULE_RESOLUTION_STATUS = 38;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.LocatedElement <em>Located Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Located Element</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement
	 * @generated
	 */
	EClass getLocatedElement();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.LocatedElement#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement#getLocation()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EAttribute getLocatedElement_Location();

	/**
	 * Returns the meta object for the attribute list '{@link anatlyzer.atlext.ATL.LocatedElement#getCommentsBefore <em>Comments Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Comments Before</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement#getCommentsBefore()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EAttribute getLocatedElement_CommentsBefore();

	/**
	 * Returns the meta object for the attribute list '{@link anatlyzer.atlext.ATL.LocatedElement#getCommentsAfter <em>Comments After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Comments After</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement#getCommentsAfter()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EAttribute getLocatedElement_CommentsAfter();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.LocatedElement#getFileLocation <em>File Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Location</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement#getFileLocation()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EAttribute getLocatedElement_FileLocation();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.LocatedElement#getFileObject <em>File Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Object</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement#getFileObject()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EAttribute getLocatedElement_FileObject();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.ATL.LocatedElement#getProblems <em>Problems</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Problems</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement#getProblems()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EReference getLocatedElement_Problems();

	/**
	 * Returns the meta object for the map '{@link anatlyzer.atlext.ATL.LocatedElement#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Annotations</em>'.
	 * @see anatlyzer.atlext.ATL.LocatedElement#getAnnotations()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EReference getLocatedElement_Annotations();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit</em>'.
	 * @see anatlyzer.atlext.ATL.Unit
	 * @generated
	 */
	EClass getUnit();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Unit#getLibraries <em>Libraries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Libraries</em>'.
	 * @see anatlyzer.atlext.ATL.Unit#getLibraries()
	 * @see #getUnit()
	 * @generated
	 */
	EReference getUnit_Libraries();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.Unit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.ATL.Unit#getName()
	 * @see #getUnit()
	 * @generated
	 */
	EAttribute getUnit_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library</em>'.
	 * @see anatlyzer.atlext.ATL.Library
	 * @generated
	 */
	EClass getLibrary();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Library#getHelpers <em>Helpers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Helpers</em>'.
	 * @see anatlyzer.atlext.ATL.Library#getHelpers()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_Helpers();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Query <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query</em>'.
	 * @see anatlyzer.atlext.ATL.Query
	 * @generated
	 */
	EClass getQuery();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.Query#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see anatlyzer.atlext.ATL.Query#getBody()
	 * @see #getQuery()
	 * @generated
	 */
	EReference getQuery_Body();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Query#getHelpers <em>Helpers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Helpers</em>'.
	 * @see anatlyzer.atlext.ATL.Query#getHelpers()
	 * @see #getQuery()
	 * @generated
	 */
	EReference getQuery_Helpers();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see anatlyzer.atlext.ATL.Module
	 * @generated
	 */
	EClass getModule();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.Module#isIsRefining <em>Is Refining</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Refining</em>'.
	 * @see anatlyzer.atlext.ATL.Module#isIsRefining()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_IsRefining();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Module#getInModels <em>In Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Models</em>'.
	 * @see anatlyzer.atlext.ATL.Module#getInModels()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_InModels();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Module#getOutModels <em>Out Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Models</em>'.
	 * @see anatlyzer.atlext.ATL.Module#getOutModels()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_OutModels();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Module#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see anatlyzer.atlext.ATL.Module#getElements()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_Elements();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.ModuleElement <em>Module Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Element</em>'.
	 * @see anatlyzer.atlext.ATL.ModuleElement
	 * @generated
	 */
	EClass getModuleElement();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Helper <em>Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Helper</em>'.
	 * @see anatlyzer.atlext.ATL.Helper
	 * @generated
	 */
	EClass getHelper();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.Helper#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Query</em>'.
	 * @see anatlyzer.atlext.ATL.Helper#getQuery()
	 * @see #getHelper()
	 * @generated
	 */
	EReference getHelper_Query();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.Helper#getLibrary <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Library</em>'.
	 * @see anatlyzer.atlext.ATL.Helper#getLibrary()
	 * @see #getHelper()
	 * @generated
	 */
	EReference getHelper_Library();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.Helper#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Definition</em>'.
	 * @see anatlyzer.atlext.ATL.Helper#getDefinition()
	 * @see #getHelper()
	 * @generated
	 */
	EReference getHelper_Definition();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.Helper#isHasContext <em>Has Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Context</em>'.
	 * @see anatlyzer.atlext.ATL.Helper#isHasContext()
	 * @see #getHelper()
	 * @generated
	 */
	EAttribute getHelper_HasContext();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.Helper#isIsAttribute <em>Is Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Attribute</em>'.
	 * @see anatlyzer.atlext.ATL.Helper#isIsAttribute()
	 * @see #getHelper()
	 * @generated
	 */
	EAttribute getHelper_IsAttribute();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.Helper#getInferredReturnType <em>Inferred Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Inferred Return Type</em>'.
	 * @see anatlyzer.atlext.ATL.Helper#getInferredReturnType()
	 * @see #getHelper()
	 * @generated
	 */
	EReference getHelper_InferredReturnType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.Helper#getStaticReturnType <em>Static Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Static Return Type</em>'.
	 * @see anatlyzer.atlext.ATL.Helper#getStaticReturnType()
	 * @see #getHelper()
	 * @generated
	 */
	EReference getHelper_StaticReturnType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.StaticHelper <em>Static Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Helper</em>'.
	 * @see anatlyzer.atlext.ATL.StaticHelper
	 * @generated
	 */
	EClass getStaticHelper();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.ContextHelper <em>Context Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context Helper</em>'.
	 * @see anatlyzer.atlext.ATL.ContextHelper
	 * @generated
	 */
	EClass getContextHelper();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.ContextHelper#getContextType <em>Context Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context Type</em>'.
	 * @see anatlyzer.atlext.ATL.ContextHelper#getContextType()
	 * @see #getContextHelper()
	 * @generated
	 */
	EReference getContextHelper_ContextType();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.ATL.ContextHelper#getPolymorphicCalledBy <em>Polymorphic Called By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Polymorphic Called By</em>'.
	 * @see anatlyzer.atlext.ATL.ContextHelper#getPolymorphicCalledBy()
	 * @see #getContextHelper()
	 * @generated
	 */
	EReference getContextHelper_PolymorphicCalledBy();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see anatlyzer.atlext.ATL.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.Rule#getOutPattern <em>Out Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Out Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.Rule#getOutPattern()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_OutPattern();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.Rule#getActionBlock <em>Action Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action Block</em>'.
	 * @see anatlyzer.atlext.ATL.Rule#getActionBlock()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_ActionBlock();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Rule#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see anatlyzer.atlext.ATL.Rule#getVariables()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Variables();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.Rule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.ATL.Rule#getName()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.StaticRule <em>Static Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Rule</em>'.
	 * @see anatlyzer.atlext.ATL.StaticRule
	 * @generated
	 */
	EClass getStaticRule();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.ModuleCallable <em>Module Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Callable</em>'.
	 * @see anatlyzer.atlext.ATL.ModuleCallable
	 * @generated
	 */
	EClass getModuleCallable();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Callable <em>Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Callable</em>'.
	 * @see anatlyzer.atlext.ATL.Callable
	 * @generated
	 */
	EClass getCallable();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.ATL.Callable#getCalledBy <em>Called By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Called By</em>'.
	 * @see anatlyzer.atlext.ATL.Callable#getCalledBy()
	 * @see #getCallable()
	 * @generated
	 */
	EReference getCallable_CalledBy();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Callable#getCallableParameters <em>Callable Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Callable Parameters</em>'.
	 * @see anatlyzer.atlext.ATL.Callable#getCallableParameters()
	 * @see #getCallable()
	 * @generated
	 */
	EReference getCallable_CallableParameters();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.RuleWithPattern <em>Rule With Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule With Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern
	 * @generated
	 */
	EClass getRuleWithPattern();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.RuleWithPattern#getInPattern <em>In Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>In Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#getInPattern()
	 * @see #getRuleWithPattern()
	 * @generated
	 */
	EReference getRuleWithPattern_InPattern();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.ATL.RuleWithPattern#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#getChildren()
	 * @see #getRuleWithPattern()
	 * @generated
	 */
	EReference getRuleWithPattern_Children();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.RuleWithPattern#getSuperRule <em>Super Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Super Rule</em>'.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#getSuperRule()
	 * @see #getRuleWithPattern()
	 * @generated
	 */
	EReference getRuleWithPattern_SuperRule();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#isIsAbstract()
	 * @see #getRuleWithPattern()
	 * @generated
	 */
	EAttribute getRuleWithPattern_IsAbstract();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsRefining <em>Is Refining</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Refining</em>'.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#isIsRefining()
	 * @see #getRuleWithPattern()
	 * @generated
	 */
	EAttribute getRuleWithPattern_IsRefining();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsNoDefault <em>Is No Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is No Default</em>'.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#isIsNoDefault()
	 * @see #getRuleWithPattern()
	 * @generated
	 */
	EAttribute getRuleWithPattern_IsNoDefault();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.MatchedRule <em>Matched Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matched Rule</em>'.
	 * @see anatlyzer.atlext.ATL.MatchedRule
	 * @generated
	 */
	EClass getMatchedRule();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.LazyRule <em>Lazy Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lazy Rule</em>'.
	 * @see anatlyzer.atlext.ATL.LazyRule
	 * @generated
	 */
	EClass getLazyRule();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.LazyRule#isIsUnique <em>Is Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Unique</em>'.
	 * @see anatlyzer.atlext.ATL.LazyRule#isIsUnique()
	 * @see #getLazyRule()
	 * @generated
	 */
	EAttribute getLazyRule_IsUnique();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.CalledRule <em>Called Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Called Rule</em>'.
	 * @see anatlyzer.atlext.ATL.CalledRule
	 * @generated
	 */
	EClass getCalledRule();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.CalledRule#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see anatlyzer.atlext.ATL.CalledRule#getParameters()
	 * @see #getCalledRule()
	 * @generated
	 */
	EReference getCalledRule_Parameters();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.CalledRule#isIsEntrypoint <em>Is Entrypoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Entrypoint</em>'.
	 * @see anatlyzer.atlext.ATL.CalledRule#isIsEntrypoint()
	 * @see #getCalledRule()
	 * @generated
	 */
	EAttribute getCalledRule_IsEntrypoint();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.CalledRule#isIsEndpoint <em>Is Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Endpoint</em>'.
	 * @see anatlyzer.atlext.ATL.CalledRule#isIsEndpoint()
	 * @see #getCalledRule()
	 * @generated
	 */
	EAttribute getCalledRule_IsEndpoint();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.InPattern <em>In Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>In Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.InPattern
	 * @generated
	 */
	EClass getInPattern();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.InPattern#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see anatlyzer.atlext.ATL.InPattern#getElements()
	 * @see #getInPattern()
	 * @generated
	 */
	EReference getInPattern_Elements();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.InPattern#getFilter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Filter</em>'.
	 * @see anatlyzer.atlext.ATL.InPattern#getFilter()
	 * @see #getInPattern()
	 * @generated
	 */
	EReference getInPattern_Filter();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.OutPattern <em>Out Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Out Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.OutPattern
	 * @generated
	 */
	EClass getOutPattern();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.OutPattern#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Rule</em>'.
	 * @see anatlyzer.atlext.ATL.OutPattern#getRule()
	 * @see #getOutPattern()
	 * @generated
	 */
	EReference getOutPattern_Rule();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.OutPattern#getDropPattern <em>Drop Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Drop Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.OutPattern#getDropPattern()
	 * @see #getOutPattern()
	 * @generated
	 */
	EReference getOutPattern_DropPattern();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.OutPattern#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see anatlyzer.atlext.ATL.OutPattern#getElements()
	 * @see #getOutPattern()
	 * @generated
	 */
	EReference getOutPattern_Elements();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.DropPattern <em>Drop Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drop Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.DropPattern
	 * @generated
	 */
	EClass getDropPattern();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.DropPattern#getOutPattern <em>Out Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Out Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.DropPattern#getOutPattern()
	 * @see #getDropPattern()
	 * @generated
	 */
	EReference getDropPattern_OutPattern();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.PatternElement <em>Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pattern Element</em>'.
	 * @see anatlyzer.atlext.ATL.PatternElement
	 * @generated
	 */
	EClass getPatternElement();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.InPatternElement <em>In Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>In Pattern Element</em>'.
	 * @see anatlyzer.atlext.ATL.InPatternElement
	 * @generated
	 */
	EClass getInPatternElement();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.InPatternElement#getMapsTo <em>Maps To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Maps To</em>'.
	 * @see anatlyzer.atlext.ATL.InPatternElement#getMapsTo()
	 * @see #getInPatternElement()
	 * @generated
	 */
	EReference getInPatternElement_MapsTo();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.InPatternElement#getInPattern <em>In Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>In Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.InPatternElement#getInPattern()
	 * @see #getInPatternElement()
	 * @generated
	 */
	EReference getInPatternElement_InPattern();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.ATL.InPatternElement#getModels <em>Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Models</em>'.
	 * @see anatlyzer.atlext.ATL.InPatternElement#getModels()
	 * @see #getInPatternElement()
	 * @generated
	 */
	EReference getInPatternElement_Models();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.SimpleInPatternElement <em>Simple In Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple In Pattern Element</em>'.
	 * @see anatlyzer.atlext.ATL.SimpleInPatternElement
	 * @generated
	 */
	EClass getSimpleInPatternElement();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.OutPatternElement <em>Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Out Pattern Element</em>'.
	 * @see anatlyzer.atlext.ATL.OutPatternElement
	 * @generated
	 */
	EClass getOutPatternElement();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.OutPatternElement#getOutPattern <em>Out Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Out Pattern</em>'.
	 * @see anatlyzer.atlext.ATL.OutPatternElement#getOutPattern()
	 * @see #getOutPatternElement()
	 * @generated
	 */
	EReference getOutPatternElement_OutPattern();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.OutPatternElement#getSourceElement <em>Source Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Element</em>'.
	 * @see anatlyzer.atlext.ATL.OutPatternElement#getSourceElement()
	 * @see #getOutPatternElement()
	 * @generated
	 */
	EReference getOutPatternElement_SourceElement();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.OutPatternElement#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bindings</em>'.
	 * @see anatlyzer.atlext.ATL.OutPatternElement#getBindings()
	 * @see #getOutPatternElement()
	 * @generated
	 */
	EReference getOutPatternElement_Bindings();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.OutPatternElement#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model</em>'.
	 * @see anatlyzer.atlext.ATL.OutPatternElement#getModel()
	 * @see #getOutPatternElement()
	 * @generated
	 */
	EReference getOutPatternElement_Model();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.SimpleOutPatternElement <em>Simple Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Out Pattern Element</em>'.
	 * @see anatlyzer.atlext.ATL.SimpleOutPatternElement
	 * @generated
	 */
	EClass getSimpleOutPatternElement();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.SimpleOutPatternElement#getReverseBindings <em>Reverse Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reverse Bindings</em>'.
	 * @see anatlyzer.atlext.ATL.SimpleOutPatternElement#getReverseBindings()
	 * @see #getSimpleOutPatternElement()
	 * @generated
	 */
	EReference getSimpleOutPatternElement_ReverseBindings();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.ForEachOutPatternElement <em>For Each Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>For Each Out Pattern Element</em>'.
	 * @see anatlyzer.atlext.ATL.ForEachOutPatternElement
	 * @generated
	 */
	EClass getForEachOutPatternElement();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.ForEachOutPatternElement#getCollection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Collection</em>'.
	 * @see anatlyzer.atlext.ATL.ForEachOutPatternElement#getCollection()
	 * @see #getForEachOutPatternElement()
	 * @generated
	 */
	EReference getForEachOutPatternElement_Collection();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.ForEachOutPatternElement#getIterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Iterator</em>'.
	 * @see anatlyzer.atlext.ATL.ForEachOutPatternElement#getIterator()
	 * @see #getForEachOutPatternElement()
	 * @generated
	 */
	EReference getForEachOutPatternElement_Iterator();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Binding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding</em>'.
	 * @see anatlyzer.atlext.ATL.Binding
	 * @generated
	 */
	EClass getBinding();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.Binding#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see anatlyzer.atlext.ATL.Binding#getValue()
	 * @see #getBinding()
	 * @generated
	 */
	EReference getBinding_Value();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.Binding#getOutPatternElement <em>Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Out Pattern Element</em>'.
	 * @see anatlyzer.atlext.ATL.Binding#getOutPatternElement()
	 * @see #getBinding()
	 * @generated
	 */
	EReference getBinding_OutPatternElement();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.Binding#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property Name</em>'.
	 * @see anatlyzer.atlext.ATL.Binding#getPropertyName()
	 * @see #getBinding()
	 * @generated
	 */
	EAttribute getBinding_PropertyName();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.Binding#isIsAssignment <em>Is Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Assignment</em>'.
	 * @see anatlyzer.atlext.ATL.Binding#isIsAssignment()
	 * @see #getBinding()
	 * @generated
	 */
	EAttribute getBinding_IsAssignment();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.Binding#getWrittenFeature <em>Written Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Written Feature</em>'.
	 * @see anatlyzer.atlext.ATL.Binding#getWrittenFeature()
	 * @see #getBinding()
	 * @generated
	 */
	EReference getBinding_WrittenFeature();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.Binding#getLeftType <em>Left Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Type</em>'.
	 * @see anatlyzer.atlext.ATL.Binding#getLeftType()
	 * @see #getBinding()
	 * @generated
	 */
	EReference getBinding_LeftType();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.Binding#getResolvedBy <em>Resolved By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resolved By</em>'.
	 * @see anatlyzer.atlext.ATL.Binding#getResolvedBy()
	 * @see #getBinding()
	 * @generated
	 */
	EReference getBinding_ResolvedBy();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.RuleVariableDeclaration <em>Rule Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Variable Declaration</em>'.
	 * @see anatlyzer.atlext.ATL.RuleVariableDeclaration
	 * @generated
	 */
	EClass getRuleVariableDeclaration();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.RuleVariableDeclaration#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Rule</em>'.
	 * @see anatlyzer.atlext.ATL.RuleVariableDeclaration#getRule()
	 * @see #getRuleVariableDeclaration()
	 * @generated
	 */
	EReference getRuleVariableDeclaration_Rule();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.LibraryRef <em>Library Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library Ref</em>'.
	 * @see anatlyzer.atlext.ATL.LibraryRef
	 * @generated
	 */
	EClass getLibraryRef();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.LibraryRef#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Unit</em>'.
	 * @see anatlyzer.atlext.ATL.LibraryRef#getUnit()
	 * @see #getLibraryRef()
	 * @generated
	 */
	EReference getLibraryRef_Unit();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.LibraryRef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.ATL.LibraryRef#getName()
	 * @see #getLibraryRef()
	 * @generated
	 */
	EAttribute getLibraryRef_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.ActionBlock <em>Action Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Block</em>'.
	 * @see anatlyzer.atlext.ATL.ActionBlock
	 * @generated
	 */
	EClass getActionBlock();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.ATL.ActionBlock#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Rule</em>'.
	 * @see anatlyzer.atlext.ATL.ActionBlock#getRule()
	 * @see #getActionBlock()
	 * @generated
	 */
	EReference getActionBlock_Rule();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.ActionBlock#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statements</em>'.
	 * @see anatlyzer.atlext.ATL.ActionBlock#getStatements()
	 * @see #getActionBlock()
	 * @generated
	 */
	EReference getActionBlock_Statements();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement</em>'.
	 * @see anatlyzer.atlext.ATL.Statement
	 * @generated
	 */
	EClass getStatement();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.ExpressionStat <em>Expression Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Stat</em>'.
	 * @see anatlyzer.atlext.ATL.ExpressionStat
	 * @generated
	 */
	EClass getExpressionStat();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.ExpressionStat#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see anatlyzer.atlext.ATL.ExpressionStat#getExpression()
	 * @see #getExpressionStat()
	 * @generated
	 */
	EReference getExpressionStat_Expression();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.BindingStat <em>Binding Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Stat</em>'.
	 * @see anatlyzer.atlext.ATL.BindingStat
	 * @generated
	 */
	EClass getBindingStat();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.BindingStat#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see anatlyzer.atlext.ATL.BindingStat#getSource()
	 * @see #getBindingStat()
	 * @generated
	 */
	EReference getBindingStat_Source();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.BindingStat#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property Name</em>'.
	 * @see anatlyzer.atlext.ATL.BindingStat#getPropertyName()
	 * @see #getBindingStat()
	 * @generated
	 */
	EAttribute getBindingStat_PropertyName();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.BindingStat#isIsAssignment <em>Is Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Assignment</em>'.
	 * @see anatlyzer.atlext.ATL.BindingStat#isIsAssignment()
	 * @see #getBindingStat()
	 * @generated
	 */
	EAttribute getBindingStat_IsAssignment();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.BindingStat#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see anatlyzer.atlext.ATL.BindingStat#getValue()
	 * @see #getBindingStat()
	 * @generated
	 */
	EReference getBindingStat_Value();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.IfStat <em>If Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Stat</em>'.
	 * @see anatlyzer.atlext.ATL.IfStat
	 * @generated
	 */
	EClass getIfStat();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.IfStat#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see anatlyzer.atlext.ATL.IfStat#getCondition()
	 * @see #getIfStat()
	 * @generated
	 */
	EReference getIfStat_Condition();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.IfStat#getThenStatements <em>Then Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Then Statements</em>'.
	 * @see anatlyzer.atlext.ATL.IfStat#getThenStatements()
	 * @see #getIfStat()
	 * @generated
	 */
	EReference getIfStat_ThenStatements();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.IfStat#getElseStatements <em>Else Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Else Statements</em>'.
	 * @see anatlyzer.atlext.ATL.IfStat#getElseStatements()
	 * @see #getIfStat()
	 * @generated
	 */
	EReference getIfStat_ElseStatements();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.ForStat <em>For Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>For Stat</em>'.
	 * @see anatlyzer.atlext.ATL.ForStat
	 * @generated
	 */
	EClass getForStat();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.ForStat#getIterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Iterator</em>'.
	 * @see anatlyzer.atlext.ATL.ForStat#getIterator()
	 * @see #getForStat()
	 * @generated
	 */
	EReference getForStat_Iterator();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.ATL.ForStat#getCollection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Collection</em>'.
	 * @see anatlyzer.atlext.ATL.ForStat#getCollection()
	 * @see #getForStat()
	 * @generated
	 */
	EReference getForStat_Collection();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.ATL.ForStat#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statements</em>'.
	 * @see anatlyzer.atlext.ATL.ForStat#getStatements()
	 * @see #getForStat()
	 * @generated
	 */
	EReference getForStat_Statements();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To String Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getStringToStringMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Value();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.CallableParameter <em>Callable Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Callable Parameter</em>'.
	 * @see anatlyzer.atlext.ATL.CallableParameter
	 * @generated
	 */
	EClass getCallableParameter();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.CallableParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.ATL.CallableParameter#getName()
	 * @see #getCallableParameter()
	 * @generated
	 */
	EAttribute getCallableParameter_Name();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.CallableParameter#getStaticType <em>Static Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Static Type</em>'.
	 * @see anatlyzer.atlext.ATL.CallableParameter#getStaticType()
	 * @see #getCallableParameter()
	 * @generated
	 */
	EReference getCallableParameter_StaticType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.CallableParameter#getParamDeclaration <em>Param Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Param Declaration</em>'.
	 * @see anatlyzer.atlext.ATL.CallableParameter#getParamDeclaration()
	 * @see #getCallableParameter()
	 * @generated
	 */
	EReference getCallableParameter_ParamDeclaration();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.ATL.RuleResolutionInfo <em>Rule Resolution Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Resolution Info</em>'.
	 * @see anatlyzer.atlext.ATL.RuleResolutionInfo
	 * @generated
	 */
	EClass getRuleResolutionInfo();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rule</em>'.
	 * @see anatlyzer.atlext.ATL.RuleResolutionInfo#getRule()
	 * @see #getRuleResolutionInfo()
	 * @generated
	 */
	EReference getRuleResolutionInfo_Rule();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getAllInvolvedRules <em>All Involved Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>All Involved Rules</em>'.
	 * @see anatlyzer.atlext.ATL.RuleResolutionInfo#getAllInvolvedRules()
	 * @see #getRuleResolutionInfo()
	 * @generated
	 */
	EReference getRuleResolutionInfo_AllInvolvedRules();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see anatlyzer.atlext.ATL.RuleResolutionInfo#getStatus()
	 * @see #getRuleResolutionInfo()
	 * @generated
	 */
	EAttribute getRuleResolutionInfo_Status();

	/**
	 * Returns the meta object for enum '{@link anatlyzer.atlext.ATL.RuleResolutionStatus <em>Rule Resolution Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Rule Resolution Status</em>'.
	 * @see anatlyzer.atlext.ATL.RuleResolutionStatus
	 * @generated
	 */
	EEnum getRuleResolutionStatus();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ATLFactory getATLFactory();

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
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.LocatedElementImpl <em>Located Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.LocatedElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLocatedElement()
		 * @generated
		 */
		EClass LOCATED_ELEMENT = eINSTANCE.getLocatedElement();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATED_ELEMENT__LOCATION = eINSTANCE.getLocatedElement_Location();

		/**
		 * The meta object literal for the '<em><b>Comments Before</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATED_ELEMENT__COMMENTS_BEFORE = eINSTANCE.getLocatedElement_CommentsBefore();

		/**
		 * The meta object literal for the '<em><b>Comments After</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATED_ELEMENT__COMMENTS_AFTER = eINSTANCE.getLocatedElement_CommentsAfter();

		/**
		 * The meta object literal for the '<em><b>File Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATED_ELEMENT__FILE_LOCATION = eINSTANCE.getLocatedElement_FileLocation();

		/**
		 * The meta object literal for the '<em><b>File Object</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATED_ELEMENT__FILE_OBJECT = eINSTANCE.getLocatedElement_FileObject();

		/**
		 * The meta object literal for the '<em><b>Problems</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCATED_ELEMENT__PROBLEMS = eINSTANCE.getLocatedElement_Problems();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCATED_ELEMENT__ANNOTATIONS = eINSTANCE.getLocatedElement_Annotations();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.UnitImpl <em>Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.UnitImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getUnit()
		 * @generated
		 */
		EClass UNIT = eINSTANCE.getUnit();

		/**
		 * The meta object literal for the '<em><b>Libraries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNIT__LIBRARIES = eINSTANCE.getUnit_Libraries();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIT__NAME = eINSTANCE.getUnit_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.LibraryImpl <em>Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.LibraryImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLibrary()
		 * @generated
		 */
		EClass LIBRARY = eINSTANCE.getLibrary();

		/**
		 * The meta object literal for the '<em><b>Helpers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__HELPERS = eINSTANCE.getLibrary_Helpers();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.QueryImpl <em>Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.QueryImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getQuery()
		 * @generated
		 */
		EClass QUERY = eINSTANCE.getQuery();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUERY__BODY = eINSTANCE.getQuery_Body();

		/**
		 * The meta object literal for the '<em><b>Helpers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUERY__HELPERS = eINSTANCE.getQuery_Helpers();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ModuleImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getModule()
		 * @generated
		 */
		EClass MODULE = eINSTANCE.getModule();

		/**
		 * The meta object literal for the '<em><b>Is Refining</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__IS_REFINING = eINSTANCE.getModule_IsRefining();

		/**
		 * The meta object literal for the '<em><b>In Models</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__IN_MODELS = eINSTANCE.getModule_InModels();

		/**
		 * The meta object literal for the '<em><b>Out Models</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__OUT_MODELS = eINSTANCE.getModule_OutModels();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__ELEMENTS = eINSTANCE.getModule_Elements();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ModuleElementImpl <em>Module Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ModuleElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getModuleElement()
		 * @generated
		 */
		EClass MODULE_ELEMENT = eINSTANCE.getModuleElement();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.HelperImpl <em>Helper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.HelperImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getHelper()
		 * @generated
		 */
		EClass HELPER = eINSTANCE.getHelper();

		/**
		 * The meta object literal for the '<em><b>Query</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HELPER__QUERY = eINSTANCE.getHelper_Query();

		/**
		 * The meta object literal for the '<em><b>Library</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HELPER__LIBRARY = eINSTANCE.getHelper_Library();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HELPER__DEFINITION = eINSTANCE.getHelper_Definition();

		/**
		 * The meta object literal for the '<em><b>Has Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HELPER__HAS_CONTEXT = eINSTANCE.getHelper_HasContext();

		/**
		 * The meta object literal for the '<em><b>Is Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HELPER__IS_ATTRIBUTE = eINSTANCE.getHelper_IsAttribute();

		/**
		 * The meta object literal for the '<em><b>Inferred Return Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HELPER__INFERRED_RETURN_TYPE = eINSTANCE.getHelper_InferredReturnType();

		/**
		 * The meta object literal for the '<em><b>Static Return Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HELPER__STATIC_RETURN_TYPE = eINSTANCE.getHelper_StaticReturnType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.StaticHelperImpl <em>Static Helper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.StaticHelperImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStaticHelper()
		 * @generated
		 */
		EClass STATIC_HELPER = eINSTANCE.getStaticHelper();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ContextHelperImpl <em>Context Helper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ContextHelperImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getContextHelper()
		 * @generated
		 */
		EClass CONTEXT_HELPER = eINSTANCE.getContextHelper();

		/**
		 * The meta object literal for the '<em><b>Context Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_HELPER__CONTEXT_TYPE = eINSTANCE.getContextHelper_ContextType();

		/**
		 * The meta object literal for the '<em><b>Polymorphic Called By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_HELPER__POLYMORPHIC_CALLED_BY = eINSTANCE.getContextHelper_PolymorphicCalledBy();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.RuleImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>Out Pattern</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__OUT_PATTERN = eINSTANCE.getRule_OutPattern();

		/**
		 * The meta object literal for the '<em><b>Action Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__ACTION_BLOCK = eINSTANCE.getRule_ActionBlock();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__VARIABLES = eINSTANCE.getRule_Variables();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__NAME = eINSTANCE.getRule_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl <em>Static Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.StaticRuleImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStaticRule()
		 * @generated
		 */
		EClass STATIC_RULE = eINSTANCE.getStaticRule();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ModuleCallableImpl <em>Module Callable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ModuleCallableImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getModuleCallable()
		 * @generated
		 */
		EClass MODULE_CALLABLE = eINSTANCE.getModuleCallable();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.CallableImpl <em>Callable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.CallableImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getCallable()
		 * @generated
		 */
		EClass CALLABLE = eINSTANCE.getCallable();

		/**
		 * The meta object literal for the '<em><b>Called By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLABLE__CALLED_BY = eINSTANCE.getCallable_CalledBy();

		/**
		 * The meta object literal for the '<em><b>Callable Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLABLE__CALLABLE_PARAMETERS = eINSTANCE.getCallable_CallableParameters();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl <em>Rule With Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.RuleWithPatternImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleWithPattern()
		 * @generated
		 */
		EClass RULE_WITH_PATTERN = eINSTANCE.getRuleWithPattern();

		/**
		 * The meta object literal for the '<em><b>In Pattern</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_WITH_PATTERN__IN_PATTERN = eINSTANCE.getRuleWithPattern_InPattern();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_WITH_PATTERN__CHILDREN = eINSTANCE.getRuleWithPattern_Children();

		/**
		 * The meta object literal for the '<em><b>Super Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_WITH_PATTERN__SUPER_RULE = eINSTANCE.getRuleWithPattern_SuperRule();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_WITH_PATTERN__IS_ABSTRACT = eINSTANCE.getRuleWithPattern_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Is Refining</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_WITH_PATTERN__IS_REFINING = eINSTANCE.getRuleWithPattern_IsRefining();

		/**
		 * The meta object literal for the '<em><b>Is No Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_WITH_PATTERN__IS_NO_DEFAULT = eINSTANCE.getRuleWithPattern_IsNoDefault();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.MatchedRuleImpl <em>Matched Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.MatchedRuleImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getMatchedRule()
		 * @generated
		 */
		EClass MATCHED_RULE = eINSTANCE.getMatchedRule();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.LazyRuleImpl <em>Lazy Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.LazyRuleImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLazyRule()
		 * @generated
		 */
		EClass LAZY_RULE = eINSTANCE.getLazyRule();

		/**
		 * The meta object literal for the '<em><b>Is Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LAZY_RULE__IS_UNIQUE = eINSTANCE.getLazyRule_IsUnique();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.CalledRuleImpl <em>Called Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.CalledRuleImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getCalledRule()
		 * @generated
		 */
		EClass CALLED_RULE = eINSTANCE.getCalledRule();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLED_RULE__PARAMETERS = eINSTANCE.getCalledRule_Parameters();

		/**
		 * The meta object literal for the '<em><b>Is Entrypoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALLED_RULE__IS_ENTRYPOINT = eINSTANCE.getCalledRule_IsEntrypoint();

		/**
		 * The meta object literal for the '<em><b>Is Endpoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALLED_RULE__IS_ENDPOINT = eINSTANCE.getCalledRule_IsEndpoint();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.InPatternImpl <em>In Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.InPatternImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getInPattern()
		 * @generated
		 */
		EClass IN_PATTERN = eINSTANCE.getInPattern();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IN_PATTERN__ELEMENTS = eINSTANCE.getInPattern_Elements();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IN_PATTERN__FILTER = eINSTANCE.getInPattern_Filter();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.OutPatternImpl <em>Out Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.OutPatternImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getOutPattern()
		 * @generated
		 */
		EClass OUT_PATTERN = eINSTANCE.getOutPattern();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PATTERN__RULE = eINSTANCE.getOutPattern_Rule();

		/**
		 * The meta object literal for the '<em><b>Drop Pattern</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PATTERN__DROP_PATTERN = eINSTANCE.getOutPattern_DropPattern();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PATTERN__ELEMENTS = eINSTANCE.getOutPattern_Elements();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.DropPatternImpl <em>Drop Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.DropPatternImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getDropPattern()
		 * @generated
		 */
		EClass DROP_PATTERN = eINSTANCE.getDropPattern();

		/**
		 * The meta object literal for the '<em><b>Out Pattern</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DROP_PATTERN__OUT_PATTERN = eINSTANCE.getDropPattern_OutPattern();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.PatternElementImpl <em>Pattern Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.PatternElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getPatternElement()
		 * @generated
		 */
		EClass PATTERN_ELEMENT = eINSTANCE.getPatternElement();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.InPatternElementImpl <em>In Pattern Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.InPatternElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getInPatternElement()
		 * @generated
		 */
		EClass IN_PATTERN_ELEMENT = eINSTANCE.getInPatternElement();

		/**
		 * The meta object literal for the '<em><b>Maps To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IN_PATTERN_ELEMENT__MAPS_TO = eINSTANCE.getInPatternElement_MapsTo();

		/**
		 * The meta object literal for the '<em><b>In Pattern</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IN_PATTERN_ELEMENT__IN_PATTERN = eINSTANCE.getInPatternElement_InPattern();

		/**
		 * The meta object literal for the '<em><b>Models</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IN_PATTERN_ELEMENT__MODELS = eINSTANCE.getInPatternElement_Models();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.SimpleInPatternElementImpl <em>Simple In Pattern Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.SimpleInPatternElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getSimpleInPatternElement()
		 * @generated
		 */
		EClass SIMPLE_IN_PATTERN_ELEMENT = eINSTANCE.getSimpleInPatternElement();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.OutPatternElementImpl <em>Out Pattern Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.OutPatternElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getOutPatternElement()
		 * @generated
		 */
		EClass OUT_PATTERN_ELEMENT = eINSTANCE.getOutPatternElement();

		/**
		 * The meta object literal for the '<em><b>Out Pattern</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PATTERN_ELEMENT__OUT_PATTERN = eINSTANCE.getOutPatternElement_OutPattern();

		/**
		 * The meta object literal for the '<em><b>Source Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PATTERN_ELEMENT__SOURCE_ELEMENT = eINSTANCE.getOutPatternElement_SourceElement();

		/**
		 * The meta object literal for the '<em><b>Bindings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PATTERN_ELEMENT__BINDINGS = eINSTANCE.getOutPatternElement_Bindings();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PATTERN_ELEMENT__MODEL = eINSTANCE.getOutPatternElement_Model();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.SimpleOutPatternElementImpl <em>Simple Out Pattern Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.SimpleOutPatternElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getSimpleOutPatternElement()
		 * @generated
		 */
		EClass SIMPLE_OUT_PATTERN_ELEMENT = eINSTANCE.getSimpleOutPatternElement();

		/**
		 * The meta object literal for the '<em><b>Reverse Bindings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMPLE_OUT_PATTERN_ELEMENT__REVERSE_BINDINGS = eINSTANCE.getSimpleOutPatternElement_ReverseBindings();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ForEachOutPatternElementImpl <em>For Each Out Pattern Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ForEachOutPatternElementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getForEachOutPatternElement()
		 * @generated
		 */
		EClass FOR_EACH_OUT_PATTERN_ELEMENT = eINSTANCE.getForEachOutPatternElement();

		/**
		 * The meta object literal for the '<em><b>Collection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION = eINSTANCE.getForEachOutPatternElement_Collection();

		/**
		 * The meta object literal for the '<em><b>Iterator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR = eINSTANCE.getForEachOutPatternElement_Iterator();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.BindingImpl <em>Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.BindingImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getBinding()
		 * @generated
		 */
		EClass BINDING = eINSTANCE.getBinding();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING__VALUE = eINSTANCE.getBinding_Value();

		/**
		 * The meta object literal for the '<em><b>Out Pattern Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING__OUT_PATTERN_ELEMENT = eINSTANCE.getBinding_OutPatternElement();

		/**
		 * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINDING__PROPERTY_NAME = eINSTANCE.getBinding_PropertyName();

		/**
		 * The meta object literal for the '<em><b>Is Assignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINDING__IS_ASSIGNMENT = eINSTANCE.getBinding_IsAssignment();

		/**
		 * The meta object literal for the '<em><b>Written Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING__WRITTEN_FEATURE = eINSTANCE.getBinding_WrittenFeature();

		/**
		 * The meta object literal for the '<em><b>Left Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING__LEFT_TYPE = eINSTANCE.getBinding_LeftType();

		/**
		 * The meta object literal for the '<em><b>Resolved By</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING__RESOLVED_BY = eINSTANCE.getBinding_ResolvedBy();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.RuleVariableDeclarationImpl <em>Rule Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.RuleVariableDeclarationImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleVariableDeclaration()
		 * @generated
		 */
		EClass RULE_VARIABLE_DECLARATION = eINSTANCE.getRuleVariableDeclaration();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_VARIABLE_DECLARATION__RULE = eINSTANCE.getRuleVariableDeclaration_Rule();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.LibraryRefImpl <em>Library Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.LibraryRefImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getLibraryRef()
		 * @generated
		 */
		EClass LIBRARY_REF = eINSTANCE.getLibraryRef();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY_REF__UNIT = eINSTANCE.getLibraryRef_Unit();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY_REF__NAME = eINSTANCE.getLibraryRef_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ActionBlockImpl <em>Action Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ActionBlockImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getActionBlock()
		 * @generated
		 */
		EClass ACTION_BLOCK = eINSTANCE.getActionBlock();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_BLOCK__RULE = eINSTANCE.getActionBlock_Rule();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_BLOCK__STATEMENTS = eINSTANCE.getActionBlock_Statements();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.StatementImpl <em>Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.StatementImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStatement()
		 * @generated
		 */
		EClass STATEMENT = eINSTANCE.getStatement();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ExpressionStatImpl <em>Expression Stat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ExpressionStatImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getExpressionStat()
		 * @generated
		 */
		EClass EXPRESSION_STAT = eINSTANCE.getExpressionStat();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_STAT__EXPRESSION = eINSTANCE.getExpressionStat_Expression();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.BindingStatImpl <em>Binding Stat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.BindingStatImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getBindingStat()
		 * @generated
		 */
		EClass BINDING_STAT = eINSTANCE.getBindingStat();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_STAT__SOURCE = eINSTANCE.getBindingStat_Source();

		/**
		 * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINDING_STAT__PROPERTY_NAME = eINSTANCE.getBindingStat_PropertyName();

		/**
		 * The meta object literal for the '<em><b>Is Assignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINDING_STAT__IS_ASSIGNMENT = eINSTANCE.getBindingStat_IsAssignment();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_STAT__VALUE = eINSTANCE.getBindingStat_Value();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.IfStatImpl <em>If Stat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.IfStatImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getIfStat()
		 * @generated
		 */
		EClass IF_STAT = eINSTANCE.getIfStat();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STAT__CONDITION = eINSTANCE.getIfStat_Condition();

		/**
		 * The meta object literal for the '<em><b>Then Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STAT__THEN_STATEMENTS = eINSTANCE.getIfStat_ThenStatements();

		/**
		 * The meta object literal for the '<em><b>Else Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STAT__ELSE_STATEMENTS = eINSTANCE.getIfStat_ElseStatements();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.ForStatImpl <em>For Stat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.ForStatImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getForStat()
		 * @generated
		 */
		EClass FOR_STAT = eINSTANCE.getForStat();

		/**
		 * The meta object literal for the '<em><b>Iterator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_STAT__ITERATOR = eINSTANCE.getForStat_Iterator();

		/**
		 * The meta object literal for the '<em><b>Collection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_STAT__COLLECTION = eINSTANCE.getForStat_Collection();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_STAT__STATEMENTS = eINSTANCE.getForStat_Statements();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.StringToStringMapImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getStringToStringMap()
		 * @generated
		 */
		EClass STRING_TO_STRING_MAP = eINSTANCE.getStringToStringMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_STRING_MAP__KEY = eINSTANCE.getStringToStringMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_STRING_MAP__VALUE = eINSTANCE.getStringToStringMap_Value();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.CallableParameterImpl <em>Callable Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.CallableParameterImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getCallableParameter()
		 * @generated
		 */
		EClass CALLABLE_PARAMETER = eINSTANCE.getCallableParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALLABLE_PARAMETER__NAME = eINSTANCE.getCallableParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Static Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLABLE_PARAMETER__STATIC_TYPE = eINSTANCE.getCallableParameter_StaticType();

		/**
		 * The meta object literal for the '<em><b>Param Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLABLE_PARAMETER__PARAM_DECLARATION = eINSTANCE.getCallableParameter_ParamDeclaration();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.impl.RuleResolutionInfoImpl <em>Rule Resolution Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.impl.RuleResolutionInfoImpl
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleResolutionInfo()
		 * @generated
		 */
		EClass RULE_RESOLUTION_INFO = eINSTANCE.getRuleResolutionInfo();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_RESOLUTION_INFO__RULE = eINSTANCE.getRuleResolutionInfo_Rule();

		/**
		 * The meta object literal for the '<em><b>All Involved Rules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES = eINSTANCE.getRuleResolutionInfo_AllInvolvedRules();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_RESOLUTION_INFO__STATUS = eINSTANCE.getRuleResolutionInfo_Status();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.ATL.RuleResolutionStatus <em>Rule Resolution Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.ATL.RuleResolutionStatus
		 * @see anatlyzer.atlext.ATL.impl.ATLPackageImpl#getRuleResolutionStatus()
		 * @generated
		 */
		EEnum RULE_RESOLUTION_STATUS = eINSTANCE.getRuleResolutionStatus();

	}

} //ATLPackage
