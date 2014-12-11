/**
 */
package anatlyzer.atl.umlerrors;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;

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
 * @see anatlyzer.atl.umlerrors.UmlErrorsFactory
 * @model kind="package"
 * @generated
 */
public interface UmlErrorsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "umlerrors";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/atl/uml_errors";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "uml_errors";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UmlErrorsPackage eINSTANCE = anatlyzer.atl.umlerrors.impl.UmlErrorsPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atl.umlerrors.impl.StereotypeNotFoundImpl <em>Stereotype Not Found</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.umlerrors.impl.StereotypeNotFoundImpl
	 * @see anatlyzer.atl.umlerrors.impl.UmlErrorsPackageImpl#getStereotypeNotFound()
	 * @generated
	 */
	int STEREOTYPE_NOT_FOUND = 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__DEPENDENTS = AtlErrorPackage.LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__DESCRIPTION = AtlErrorPackage.LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__SEVERITY = AtlErrorPackage.LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__NEEDS_CSP = AtlErrorPackage.LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__LOCATION = AtlErrorPackage.LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__ELEMENT = AtlErrorPackage.LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__RECOVERY = AtlErrorPackage.LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Stereotype Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME = AtlErrorPackage.LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stereotype Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND_FEATURE_COUNT = AtlErrorPackage.LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Stereotype Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_NOT_FOUND_OPERATION_COUNT = AtlErrorPackage.LOCAL_PROBLEM_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.umlerrors.StereotypeNotFound <em>Stereotype Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stereotype Not Found</em>'.
	 * @see anatlyzer.atl.umlerrors.StereotypeNotFound
	 * @generated
	 */
	EClass getStereotypeNotFound();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.umlerrors.StereotypeNotFound#getStereotypeName <em>Stereotype Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stereotype Name</em>'.
	 * @see anatlyzer.atl.umlerrors.StereotypeNotFound#getStereotypeName()
	 * @see #getStereotypeNotFound()
	 * @generated
	 */
	EAttribute getStereotypeNotFound_StereotypeName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UmlErrorsFactory getUmlErrorsFactory();

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
		 * The meta object literal for the '{@link anatlyzer.atl.umlerrors.impl.StereotypeNotFoundImpl <em>Stereotype Not Found</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.umlerrors.impl.StereotypeNotFoundImpl
		 * @see anatlyzer.atl.umlerrors.impl.UmlErrorsPackageImpl#getStereotypeNotFound()
		 * @generated
		 */
		EClass STEREOTYPE_NOT_FOUND = eINSTANCE.getStereotypeNotFound();

		/**
		 * The meta object literal for the '<em><b>Stereotype Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME = eINSTANCE.getStereotypeNotFound_StereotypeName();

	}

} //UmlErrorsPackage
