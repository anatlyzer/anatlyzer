/**
 */
package anatlyzer.example.sconstraints;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Num Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.example.sconstraints.NumConstraint#getNumber <em>Number</em>}</li>
 * </ul>
 *
 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getNumConstraint()
 * @model abstract="true"
 * @generated
 */
public interface NumConstraint extends EObject {
	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(int)
	 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getNumConstraint_Number()
	 * @model
	 * @generated
	 */
	int getNumber();

	/**
	 * Sets the value of the '{@link anatlyzer.example.sconstraints.NumConstraint#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(int value);

} // NumConstraint
