/**
 */
package anatlyzer.atl.impact;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.impact.ImpactPackage
 * @generated
 */
public interface ImpactFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ImpactFactory eINSTANCE = anatlyzer.atl.impact.impl.ImpactFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Change Impact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Impact</em>'.
	 * @generated
	 */
	ChangeImpact createChangeImpact();

	/**
	 * Returns a new object of class '<em>Context Callable Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Context Callable Change</em>'.
	 * @generated
	 */
	ContextCallableChange createContextCallableChange();

	/**
	 * Returns a new object of class '<em>Module Callable Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module Callable Change</em>'.
	 * @generated
	 */
	ModuleCallableChange createModuleCallableChange();

	/**
	 * Returns a new object of class '<em>Create Matched Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Create Matched Rule</em>'.
	 * @generated
	 */
	CreateMatchedRule createCreateMatchedRule();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ImpactPackage getImpactPackage();

} //ImpactFactory
