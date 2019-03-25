/**
 */
package anatlyzer.atlext.OCL2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atlext.OCL2.OCL2Package
 * @generated
 */
public interface OCL2Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OCL2Factory eINSTANCE = anatlyzer.atlext.OCL2.impl.OCL2FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Select By Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Select By Kind</em>'.
	 * @generated
	 */
	SelectByKind createSelectByKind();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OCL2Package getOCL2Package();

} //OCL2Factory
