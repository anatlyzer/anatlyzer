/**
 */
package anatlyzer.atl.errors.atl_recovery.impl;

import anatlyzer.atl.errors.atl_recovery.*;

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
public class AtlRecoveryFactoryImpl extends EFactoryImpl implements AtlRecoveryFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AtlRecoveryFactory init() {
		try {
			AtlRecoveryFactory theAtlRecoveryFactory = (AtlRecoveryFactory)EPackage.Registry.INSTANCE.getEFactory(AtlRecoveryPackage.eNS_URI);
			if (theAtlRecoveryFactory != null) {
				return theAtlRecoveryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AtlRecoveryFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlRecoveryFactoryImpl() {
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
			case AtlRecoveryPackage.FEATURE_FOUND_IN_SUBCLASS: return createFeatureFoundInSubclass();
			case AtlRecoveryPackage.TENTATIVE_TYPE_ASSIGNED: return createTentativeTypeAssigned();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureFoundInSubclass createFeatureFoundInSubclass() {
		FeatureFoundInSubclassImpl featureFoundInSubclass = new FeatureFoundInSubclassImpl();
		return featureFoundInSubclass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TentativeTypeAssigned createTentativeTypeAssigned() {
		TentativeTypeAssignedImpl tentativeTypeAssigned = new TentativeTypeAssignedImpl();
		return tentativeTypeAssigned;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlRecoveryPackage getAtlRecoveryPackage() {
		return (AtlRecoveryPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AtlRecoveryPackage getPackage() {
		return AtlRecoveryPackage.eINSTANCE;
	}

} //AtlRecoveryFactoryImpl
