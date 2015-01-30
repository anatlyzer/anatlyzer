/**
 */
package anatlyzer.atl.errors.ide_error;

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
 * @see anatlyzer.atl.errors.ide_error.IdeErrorFactory
 * @model kind="package"
 * @generated
 */
public interface IdeErrorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ide_error";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://bento/analysis/atl/analysis/errors/ide";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ide_error";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IdeErrorPackage eINSTANCE = anatlyzer.atl.errors.ide_error.impl.IdeErrorPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.ide_error.impl.CouldNotLoadMetamodelImpl <em>Could Not Load Metamodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.ide_error.impl.CouldNotLoadMetamodelImpl
	 * @see anatlyzer.atl.errors.ide_error.impl.IdeErrorPackageImpl#getCouldNotLoadMetamodel()
	 * @generated
	 */
	int COULD_NOT_LOAD_METAMODEL = 0;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__DEPENDENTS = AtlErrorPackage.LOCAL_PROBLEM__DEPENDENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__DESCRIPTION = AtlErrorPackage.LOCAL_PROBLEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__SEVERITY = AtlErrorPackage.LOCAL_PROBLEM__SEVERITY;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__NEEDS_CSP = AtlErrorPackage.LOCAL_PROBLEM__NEEDS_CSP;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__LOCATION = AtlErrorPackage.LOCAL_PROBLEM__LOCATION;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__FILE_LOCATION = AtlErrorPackage.LOCAL_PROBLEM__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__ELEMENT = AtlErrorPackage.LOCAL_PROBLEM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__RECOVERY = AtlErrorPackage.LOCAL_PROBLEM__RECOVERY;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL__URI = AtlErrorPackage.LOCAL_PROBLEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Could Not Load Metamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL_FEATURE_COUNT = AtlErrorPackage.LOCAL_PROBLEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Could Not Load Metamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COULD_NOT_LOAD_METAMODEL_OPERATION_COUNT = AtlErrorPackage.LOCAL_PROBLEM_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel <em>Could Not Load Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Could Not Load Metamodel</em>'.
	 * @see anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel
	 * @generated
	 */
	EClass getCouldNotLoadMetamodel();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel#getUri()
	 * @see #getCouldNotLoadMetamodel()
	 * @generated
	 */
	EAttribute getCouldNotLoadMetamodel_Uri();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IdeErrorFactory getIdeErrorFactory();

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
		 * The meta object literal for the '{@link anatlyzer.atl.errors.ide_error.impl.CouldNotLoadMetamodelImpl <em>Could Not Load Metamodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.ide_error.impl.CouldNotLoadMetamodelImpl
		 * @see anatlyzer.atl.errors.ide_error.impl.IdeErrorPackageImpl#getCouldNotLoadMetamodel()
		 * @generated
		 */
		EClass COULD_NOT_LOAD_METAMODEL = eINSTANCE.getCouldNotLoadMetamodel();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COULD_NOT_LOAD_METAMODEL__URI = eINSTANCE.getCouldNotLoadMetamodel_Uri();

	}

} //IdeErrorPackage
