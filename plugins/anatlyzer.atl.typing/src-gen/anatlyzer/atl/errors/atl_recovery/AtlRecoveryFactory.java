/**
 */
package anatlyzer.atl.errors.atl_recovery;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage
 * @generated
 */
public interface AtlRecoveryFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AtlRecoveryFactory eINSTANCE = anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Feature Found In Subclass</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Found In Subclass</em>'.
	 * @generated
	 */
	FeatureFoundInSubclass createFeatureFoundInSubclass();

	/**
	 * Returns a new object of class '<em>Tentative Type Assigned</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tentative Type Assigned</em>'.
	 * @generated
	 */
	TentativeTypeAssigned createTentativeTypeAssigned();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AtlRecoveryPackage getAtlRecoveryPackage();

} //AtlRecoveryFactory
