/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Not Found In This Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#getName <em>Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#isMaybeRule <em>Maybe Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFoundInThisModule()
 * @model
 * @generated
 */
public interface OperationNotFoundInThisModule extends NavigationProblem {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFoundInThisModule_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Maybe Rule</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maybe Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maybe Rule</em>' attribute.
	 * @see #setMaybeRule(boolean)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFoundInThisModule_MaybeRule()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isMaybeRule();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule#isMaybeRule <em>Maybe Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maybe Rule</em>' attribute.
	 * @see #isMaybeRule()
	 * @generated
	 */
	void setMaybeRule(boolean value);

} // OperationNotFoundInThisModule
