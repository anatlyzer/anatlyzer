/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Atl Parse Error</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.AtlParseError#getGenericKind <em>Generic Kind</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAtlParseError()
 * @model annotation="description name='Parse error' text='' example=''"
 *        annotation="info prec='static' path='no' severity='error-load' when='always' kind='trafo-integrity' phase='parsing' source=''"
 * @generated
 */
public interface AtlParseError extends LocalProblem {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAtlParseError_GenericKind()
	 * @model required="true"
	 * @generated
	 */
	String getGenericKind();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.AtlParseError#getGenericKind <em>Generic Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generic Kind</em>' attribute.
	 * @see #getGenericKind()
	 * @generated
	 */
	void setGenericKind(String value);

} // AtlParseError
