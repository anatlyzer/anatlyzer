/**
 */
package anatlyzer.atl.impact;

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
 * @see anatlyzer.atl.impact.ImpactFactory
 * @model kind="package"
 * @generated
 */
public interface ImpactPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "impact";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/atl/impact";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_impact";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ImpactPackage eINSTANCE = anatlyzer.atl.impact.impl.ImpactPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atl.impact.impl.ChangeImpactImpl <em>Change Impact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.impact.impl.ChangeImpactImpl
	 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getChangeImpact()
	 * @generated
	 */
	int CHANGE_IMPACT = 0;

	/**
	 * The number of structural features of the '<em>Change Impact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_IMPACT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Change Impact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_IMPACT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.impact.impl.ChangeImpl <em>Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.impact.impl.ChangeImpl
	 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getChange()
	 * @generated
	 */
	int CHANGE = 1;

	/**
	 * The number of structural features of the '<em>Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.impact.impl.CallableChangeImpl <em>Callable Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.impact.impl.CallableChangeImpl
	 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getCallableChange()
	 * @generated
	 */
	int CALLABLE_CHANGE = 2;

	/**
	 * The feature id for the '<em><b>Callable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_CHANGE__CALLABLE = CHANGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Invalidated</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_CHANGE__INVALIDATED = CHANGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Callable Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_CHANGE_FEATURE_COUNT = CHANGE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Callable Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_CHANGE_OPERATION_COUNT = CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.impact.impl.ContextCallableChangeImpl <em>Context Callable Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.impact.impl.ContextCallableChangeImpl
	 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getContextCallableChange()
	 * @generated
	 */
	int CONTEXT_CALLABLE_CHANGE = 3;

	/**
	 * The feature id for the '<em><b>Callable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_CALLABLE_CHANGE__CALLABLE = CALLABLE_CHANGE__CALLABLE;

	/**
	 * The feature id for the '<em><b>Invalidated</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_CALLABLE_CHANGE__INVALIDATED = CALLABLE_CHANGE__INVALIDATED;

	/**
	 * The number of structural features of the '<em>Context Callable Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_CALLABLE_CHANGE_FEATURE_COUNT = CALLABLE_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Context Callable Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_CALLABLE_CHANGE_OPERATION_COUNT = CALLABLE_CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.impact.impl.ModuleCallableChangeImpl <em>Module Callable Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.impact.impl.ModuleCallableChangeImpl
	 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getModuleCallableChange()
	 * @generated
	 */
	int MODULE_CALLABLE_CHANGE = 4;

	/**
	 * The feature id for the '<em><b>Callable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE_CHANGE__CALLABLE = CALLABLE_CHANGE__CALLABLE;

	/**
	 * The feature id for the '<em><b>Invalidated</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE_CHANGE__INVALIDATED = CALLABLE_CHANGE__INVALIDATED;

	/**
	 * The number of structural features of the '<em>Module Callable Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE_CHANGE_FEATURE_COUNT = CALLABLE_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Module Callable Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CALLABLE_CHANGE_OPERATION_COUNT = CALLABLE_CHANGE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.impact.ChangeImpact <em>Change Impact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Impact</em>'.
	 * @see anatlyzer.atl.impact.ChangeImpact
	 * @generated
	 */
	EClass getChangeImpact();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.impact.Change <em>Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change</em>'.
	 * @see anatlyzer.atl.impact.Change
	 * @generated
	 */
	EClass getChange();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.impact.CallableChange <em>Callable Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Callable Change</em>'.
	 * @see anatlyzer.atl.impact.CallableChange
	 * @generated
	 */
	EClass getCallableChange();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.impact.CallableChange#getCallable <em>Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Callable</em>'.
	 * @see anatlyzer.atl.impact.CallableChange#getCallable()
	 * @see #getCallableChange()
	 * @generated
	 */
	EReference getCallableChange_Callable();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.impact.CallableChange#getInvalidated <em>Invalidated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Invalidated</em>'.
	 * @see anatlyzer.atl.impact.CallableChange#getInvalidated()
	 * @see #getCallableChange()
	 * @generated
	 */
	EReference getCallableChange_Invalidated();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.impact.ContextCallableChange <em>Context Callable Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context Callable Change</em>'.
	 * @see anatlyzer.atl.impact.ContextCallableChange
	 * @generated
	 */
	EClass getContextCallableChange();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.impact.ModuleCallableChange <em>Module Callable Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Callable Change</em>'.
	 * @see anatlyzer.atl.impact.ModuleCallableChange
	 * @generated
	 */
	EClass getModuleCallableChange();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ImpactFactory getImpactFactory();

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
		 * The meta object literal for the '{@link anatlyzer.atl.impact.impl.ChangeImpactImpl <em>Change Impact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.impact.impl.ChangeImpactImpl
		 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getChangeImpact()
		 * @generated
		 */
		EClass CHANGE_IMPACT = eINSTANCE.getChangeImpact();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.impact.impl.ChangeImpl <em>Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.impact.impl.ChangeImpl
		 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getChange()
		 * @generated
		 */
		EClass CHANGE = eINSTANCE.getChange();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.impact.impl.CallableChangeImpl <em>Callable Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.impact.impl.CallableChangeImpl
		 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getCallableChange()
		 * @generated
		 */
		EClass CALLABLE_CHANGE = eINSTANCE.getCallableChange();

		/**
		 * The meta object literal for the '<em><b>Callable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLABLE_CHANGE__CALLABLE = eINSTANCE.getCallableChange_Callable();

		/**
		 * The meta object literal for the '<em><b>Invalidated</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLABLE_CHANGE__INVALIDATED = eINSTANCE.getCallableChange_Invalidated();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.impact.impl.ContextCallableChangeImpl <em>Context Callable Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.impact.impl.ContextCallableChangeImpl
		 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getContextCallableChange()
		 * @generated
		 */
		EClass CONTEXT_CALLABLE_CHANGE = eINSTANCE.getContextCallableChange();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.impact.impl.ModuleCallableChangeImpl <em>Module Callable Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.impact.impl.ModuleCallableChangeImpl
		 * @see anatlyzer.atl.impact.impl.ImpactPackageImpl#getModuleCallableChange()
		 * @generated
		 */
		EClass MODULE_CALLABLE_CHANGE = eINSTANCE.getModuleCallableChange();

	}

} //ImpactPackage
