/**
 */
package anatlyzer.example.sconstraints;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.example.sconstraints.ConstraintModel#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getConstraintModel()
 * @model
 * @generated
 */
public interface ConstraintModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.example.sconstraints.NumConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' containment reference list.
	 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getConstraintModel_Constraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<NumConstraint> getConstraints();

} // ConstraintModel
