/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.types.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Incoherent Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getInferred <em>Inferred</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getDeclared <em>Declared</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAbstractIncoherentVariableDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface AbstractIncoherentVariableDeclaration extends LocalProblem, OclCompliance {
	/**
	 * Returns the value of the '<em><b>Inferred</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inferred</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inferred</em>' reference.
	 * @see #setInferred(Type)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAbstractIncoherentVariableDeclaration_Inferred()
	 * @model required="true"
	 * @generated
	 */
	Type getInferred();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getInferred <em>Inferred</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inferred</em>' reference.
	 * @see #getInferred()
	 * @generated
	 */
	void setInferred(Type value);

	/**
	 * Returns the value of the '<em><b>Declared</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared</em>' reference.
	 * @see #setDeclared(Type)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAbstractIncoherentVariableDeclaration_Declared()
	 * @model required="true"
	 * @generated
	 */
	Type getDeclared();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration#getDeclared <em>Declared</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared</em>' reference.
	 * @see #getDeclared()
	 * @generated
	 */
	void setDeclared(Type value);

} // AbstractIncoherentVariableDeclaration
