/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterator Over No Collection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType#getIteratorName <em>Iterator Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getIteratorOverNoCollectionType()
 * @model
 * @generated
 */
public interface IteratorOverNoCollectionType extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Iterator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterator Name</em>' attribute.
	 * @see #setIteratorName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getIteratorOverNoCollectionType_IteratorName()
	 * @model required="true"
	 * @generated
	 */
	String getIteratorName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType#getIteratorName <em>Iterator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iterator Name</em>' attribute.
	 * @see #getIteratorName()
	 * @generated
	 */
	void setIteratorName(String value);

} // IteratorOverNoCollectionType
