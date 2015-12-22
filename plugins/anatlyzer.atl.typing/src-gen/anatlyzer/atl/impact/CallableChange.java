/**
 */
package anatlyzer.atl.impact;

import anatlyzer.atlext.ATL.Callable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Callable Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.impact.CallableChange#getCallable <em>Callable</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.impact.ImpactPackage#getCallableChange()
 * @model abstract="true"
 * @generated
 */
public interface CallableChange extends Change {
	/**
	 * Returns the value of the '<em><b>Callable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable</em>' reference.
	 * @see #setCallable(Callable)
	 * @see anatlyzer.atl.impact.ImpactPackage#getCallableChange_Callable()
	 * @model required="true"
	 * @generated
	 */
	Callable getCallable();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.impact.CallableChange#getCallable <em>Callable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable</em>' reference.
	 * @see #getCallable()
	 * @generated
	 */
	void setCallable(Callable value);

} // CallableChange
