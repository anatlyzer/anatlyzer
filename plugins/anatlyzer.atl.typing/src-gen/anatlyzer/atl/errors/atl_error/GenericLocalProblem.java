/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Local Problem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.GenericLocalProblem#getGenericKind <em>Generic Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getGenericLocalProblem()
 * @model
 * @generated
 */
public interface GenericLocalProblem extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Generic Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generic Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic Kind</em>' attribute.
	 * @see #setGenericKind(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getGenericLocalProblem_GenericKind()
	 * @model required="true"
	 * @generated
	 */
	String getGenericKind();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.GenericLocalProblem#getGenericKind <em>Generic Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generic Kind</em>' attribute.
	 * @see #getGenericKind()
	 * @generated
	 */
	void setGenericKind(String value);

} // GenericLocalProblem
