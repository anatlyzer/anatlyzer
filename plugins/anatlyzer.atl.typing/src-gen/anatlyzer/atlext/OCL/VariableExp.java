/**
 */
package anatlyzer.atlext.OCL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.VariableExp#getReferredVariable <em>Referred Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getVariableExp()
 * @model
 * @generated
 */
public interface VariableExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Referred Variable</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.VariableDeclaration#getVariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Variable</em>' reference.
	 * @see #setReferredVariable(VariableDeclaration)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getVariableExp_ReferredVariable()
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getVariableExp
	 * @model opposite="variableExp" required="true" ordered="false"
	 * @generated
	 */
	VariableDeclaration getReferredVariable();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.VariableExp#getReferredVariable <em>Referred Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Variable</em>' reference.
	 * @see #getReferredVariable()
	 * @generated
	 */
	void setReferredVariable(VariableDeclaration value);

} // VariableExp
