/**
 */
package anatlyzer.atl.errors.atl_recovery.util;

import anatlyzer.atl.errors.Recovery;

import anatlyzer.atl.errors.atl_recovery.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage
 * @generated
 */
public class AtlRecoveryAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AtlRecoveryPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlRecoveryAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AtlRecoveryPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AtlRecoverySwitch<Adapter> modelSwitch =
		new AtlRecoverySwitch<Adapter>() {
			@Override
			public Adapter caseFeatureFoundInSubclass(FeatureFoundInSubclass object) {
				return createFeatureFoundInSubclassAdapter();
			}
			@Override
			public Adapter caseTentativeTypeAssigned(TentativeTypeAssigned object) {
				return createTentativeTypeAssignedAdapter();
			}
			@Override
			public Adapter caseRecovery(Recovery object) {
				return createRecoveryAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass <em>Feature Found In Subclass</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass
	 * @generated
	 */
	public Adapter createFeatureFoundInSubclassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned <em>Tentative Type Assigned</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned
	 * @generated
	 */
	public Adapter createTentativeTypeAssignedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.Recovery <em>Recovery</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.Recovery
	 * @generated
	 */
	public Adapter createRecoveryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AtlRecoveryAdapterFactory
