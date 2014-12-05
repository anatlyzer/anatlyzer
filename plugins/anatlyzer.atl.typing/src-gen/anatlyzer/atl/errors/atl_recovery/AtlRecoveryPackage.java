/**
 */
package anatlyzer.atl.errors.atl_recovery;

import anatlyzer.atl.errors.AnalysisResultPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see anatlyzer.atl.errors.atl_recovery.AtlRecoveryFactory
 * @model kind="package"
 * @generated
 */
public interface AtlRecoveryPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "atl_recovery";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://bento/analysis/atl/analysis/recovery";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_recovery";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AtlRecoveryPackage eINSTANCE = anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_recovery.impl.FeatureFoundInSubclassImpl <em>Feature Found In Subclass</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_recovery.impl.FeatureFoundInSubclassImpl
	 * @see anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryPackageImpl#getFeatureFoundInSubclass()
	 * @generated
	 */
	int FEATURE_FOUND_IN_SUBCLASS = 0;

	/**
	 * The feature id for the '<em><b>Subclass Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBCLASS__SUBCLASS_NAME = AnalysisResultPackage.RECOVERY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subclass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBCLASS__SUBCLASS = AnalysisResultPackage.RECOVERY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Feature Found In Subclass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBCLASS_FEATURE_COUNT = AnalysisResultPackage.RECOVERY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Feature Found In Subclass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FOUND_IN_SUBCLASS_OPERATION_COUNT = AnalysisResultPackage.RECOVERY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.atl_recovery.impl.TentativeTypeAssignedImpl <em>Tentative Type Assigned</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.atl_recovery.impl.TentativeTypeAssignedImpl
	 * @see anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryPackageImpl#getTentativeTypeAssigned()
	 * @generated
	 */
	int TENTATIVE_TYPE_ASSIGNED = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TENTATIVE_TYPE_ASSIGNED__TYPE = AnalysisResultPackage.RECOVERY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tentative Type Assigned</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TENTATIVE_TYPE_ASSIGNED_FEATURE_COUNT = AnalysisResultPackage.RECOVERY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tentative Type Assigned</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TENTATIVE_TYPE_ASSIGNED_OPERATION_COUNT = AnalysisResultPackage.RECOVERY_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass <em>Feature Found In Subclass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Found In Subclass</em>'.
	 * @see anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass
	 * @generated
	 */
	EClass getFeatureFoundInSubclass();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclassName <em>Subclass Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subclass Name</em>'.
	 * @see anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclassName()
	 * @see #getFeatureFoundInSubclass()
	 * @generated
	 */
	EAttribute getFeatureFoundInSubclass_SubclassName();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclass <em>Subclass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subclass</em>'.
	 * @see anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclass()
	 * @see #getFeatureFoundInSubclass()
	 * @generated
	 */
	EReference getFeatureFoundInSubclass_Subclass();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned <em>Tentative Type Assigned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tentative Type Assigned</em>'.
	 * @see anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned
	 * @generated
	 */
	EClass getTentativeTypeAssigned();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned#getType()
	 * @see #getTentativeTypeAssigned()
	 * @generated
	 */
	EReference getTentativeTypeAssigned_Type();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AtlRecoveryFactory getAtlRecoveryFactory();

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
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_recovery.impl.FeatureFoundInSubclassImpl <em>Feature Found In Subclass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_recovery.impl.FeatureFoundInSubclassImpl
		 * @see anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryPackageImpl#getFeatureFoundInSubclass()
		 * @generated
		 */
		EClass FEATURE_FOUND_IN_SUBCLASS = eINSTANCE.getFeatureFoundInSubclass();

		/**
		 * The meta object literal for the '<em><b>Subclass Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_FOUND_IN_SUBCLASS__SUBCLASS_NAME = eINSTANCE.getFeatureFoundInSubclass_SubclassName();

		/**
		 * The meta object literal for the '<em><b>Subclass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_FOUND_IN_SUBCLASS__SUBCLASS = eINSTANCE.getFeatureFoundInSubclass_Subclass();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.atl_recovery.impl.TentativeTypeAssignedImpl <em>Tentative Type Assigned</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.atl_recovery.impl.TentativeTypeAssignedImpl
		 * @see anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryPackageImpl#getTentativeTypeAssigned()
		 * @generated
		 */
		EClass TENTATIVE_TYPE_ASSIGNED = eINSTANCE.getTentativeTypeAssigned();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TENTATIVE_TYPE_ASSIGNED__TYPE = eINSTANCE.getTentativeTypeAssigned_Type();

	}

} //AtlRecoveryPackage
