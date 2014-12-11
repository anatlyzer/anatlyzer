/**
 */
package anatlyzer.atl.umlerrors;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.umlerrors.UmlErrorsPackage
 * @generated
 */
public interface UmlErrorsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UmlErrorsFactory eINSTANCE = anatlyzer.atl.umlerrors.impl.UmlErrorsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Stereotype Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stereotype Not Found</em>'.
	 * @generated
	 */
	StereotypeNotFound createStereotypeNotFound();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UmlErrorsPackage getUmlErrorsPackage();

} //UmlErrorsFactory
