/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Error</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.types.TypeError#getProblem <em>Problem</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.types.TypesPackage#getTypeError()
 * @model
 * @generated
 */
public interface TypeError extends Type {

	/**
	 * Returns the value of the '<em><b>Problem</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Problem</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Problem</em>' reference.
	 * @see #setProblem(EObject)
	 * @see anatlyzer.atl.types.TypesPackage#getTypeError_Problem()
	 * @model
	 * @generated
	 */
	EObject getProblem();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.TypeError#getProblem <em>Problem</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Problem</em>' reference.
	 * @see #getProblem()
	 * @generated
	 */
	void setProblem(EObject value);
} // TypeError
