/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Invalid Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.InvalidOperand#getOperatorSymbol <em>Operator Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getInvalidOperand()
 * @model
 * @generated
 */
public interface InvalidOperand extends InvalidArgumentProblem {
	/**
	 * Returns the value of the '<em><b>Operator Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator Symbol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator Symbol</em>' attribute.
	 * @see #setOperatorSymbol(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getInvalidOperand_OperatorSymbol()
	 * @model required="true"
	 * @generated
	 */
	String getOperatorSymbol();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.InvalidOperand#getOperatorSymbol <em>Operator Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator Symbol</em>' attribute.
	 * @see #getOperatorSymbol()
	 * @generated
	 */
	void setOperatorSymbol(String value);

} // InvalidOperand
