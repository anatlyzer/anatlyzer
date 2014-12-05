/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.OclExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Stat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.ExpressionStat#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getExpressionStat()
 * @model
 * @generated
 */
public interface ExpressionStat extends Statement {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(OclExpression)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getExpressionStat_Expression()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getExpression();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.ExpressionStat#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(OclExpression value);

} // ExpressionStat
