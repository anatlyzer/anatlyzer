/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.OclExpression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Stat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.ForStat#getIterator <em>Iterator</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.ForStat#getCollection <em>Collection</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.ForStat#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getForStat()
 * @model
 * @generated
 */
public interface ForStat extends Statement {
	/**
	 * Returns the value of the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterator</em>' containment reference.
	 * @see #setIterator(Iterator)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getForStat_Iterator()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Iterator getIterator();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.ForStat#getIterator <em>Iterator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iterator</em>' containment reference.
	 * @see #getIterator()
	 * @generated
	 */
	void setIterator(Iterator value);

	/**
	 * Returns the value of the '<em><b>Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection</em>' containment reference.
	 * @see #setCollection(OclExpression)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getForStat_Collection()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getCollection();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.ForStat#getCollection <em>Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection</em>' containment reference.
	 * @see #getCollection()
	 * @generated
	 */
	void setCollection(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.Statement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statements</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getForStat_Statements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getStatements();

} // ForStat
