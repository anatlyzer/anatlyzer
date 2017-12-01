/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target Invariant Violation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.TargetInvariantViolation#getInvariantName <em>Invariant Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getTargetInvariantViolation()
 * @model
 * @generated
 */
public interface TargetInvariantViolation extends BatchTargetConformanceIssue {
	/**
	 * Returns the value of the '<em><b>Invariant Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invariant Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invariant Name</em>' attribute.
	 * @see #setInvariantName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getTargetInvariantViolation_InvariantName()
	 * @model required="true"
	 * @generated
	 */
	String getInvariantName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.TargetInvariantViolation#getInvariantName <em>Invariant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invariant Name</em>' attribute.
	 * @see #getInvariantName()
	 * @generated
	 */
	void setInvariantName(String value);

} // TargetInvariantViolation
