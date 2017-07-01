/**
 */
package anatlyzer.example.sconstraints;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage
 * @generated
 */
public interface SimpleConstraintsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimpleConstraintsFactory eINSTANCE = anatlyzer.example.sconstraints.impl.SimpleConstraintsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Constraint Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint Model</em>'.
	 * @generated
	 */
	ConstraintModel createConstraintModel();

	/**
	 * Returns a new object of class '<em>Num Classes Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Num Classes Constraint</em>'.
	 * @generated
	 */
	NumClassesConstraint createNumClassesConstraint();

	/**
	 * Returns a new object of class '<em>Num Refs Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Num Refs Constraint</em>'.
	 * @generated
	 */
	NumRefsConstraint createNumRefsConstraint();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SimpleConstraintsPackage getSimpleConstraintsPackage();

} //SimpleConstraintsFactory
