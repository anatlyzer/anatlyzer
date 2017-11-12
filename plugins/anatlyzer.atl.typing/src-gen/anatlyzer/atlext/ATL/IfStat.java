/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.OclExpression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Stat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.IfStat#getCondition <em>Condition</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.IfStat#getThenStatements <em>Then Statements</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.IfStat#getElseStatements <em>Else Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getIfStat()
 * @model
 * @generated
 */
public interface IfStat extends Statement {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(OclExpression)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getIfStat_Condition()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getCondition();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.IfStat#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Then Statements</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.Statement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Then Statements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Statements</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getIfStat_ThenStatements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getThenStatements();

	/**
	 * Returns the value of the '<em><b>Else Statements</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.Statement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Statements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Statements</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getIfStat_ElseStatements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getElseStatements();

} // IfStat
