/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.types.Type;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Invalid Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.InvalidOperand#getOperatorSymbol <em>Operator Symbol</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.InvalidOperand#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getInvalidOperand()
 * @model annotation="description name='Invalid operand' text='Operand of the wrong type.'"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='navigation' phase='typing' source='none'"
 * @generated
 */
public interface InvalidOperand extends NavigationProblem, RuntimeError {
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

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Type)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getInvalidOperand_Type()
	 * @model required="true"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.InvalidOperand#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

} // InvalidOperand
