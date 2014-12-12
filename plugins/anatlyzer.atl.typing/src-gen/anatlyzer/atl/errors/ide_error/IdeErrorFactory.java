/**
 */
package anatlyzer.atl.errors.ide_error;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.ide_error.IdeErrorPackage
 * @generated
 */
public interface IdeErrorFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IdeErrorFactory eINSTANCE = anatlyzer.atl.errors.ide_error.impl.IdeErrorFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Could Not Load Metamodel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Could Not Load Metamodel</em>'.
	 * @generated
	 */
	CouldNotLoadMetamodel createCouldNotLoadMetamodel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IdeErrorPackage getIdeErrorPackage();

} //IdeErrorFactory
