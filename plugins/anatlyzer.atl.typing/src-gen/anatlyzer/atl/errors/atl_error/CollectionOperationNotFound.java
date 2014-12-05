/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Operation Not Found</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.CollectionOperationNotFound#getOperationName <em>Operation Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getCollectionOperationNotFound()
 * @model
 * @generated
 */
public interface CollectionOperationNotFound extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Name</em>' attribute.
	 * @see #setOperationName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getCollectionOperationNotFound_OperationName()
	 * @model required="true"
	 * @generated
	 */
	String getOperationName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.CollectionOperationNotFound#getOperationName <em>Operation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Name</em>' attribute.
	 * @see #getOperationName()
	 * @generated
	 */
	void setOperationName(String value);

} // CollectionOperationNotFound
