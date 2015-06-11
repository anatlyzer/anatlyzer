/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.PropertyCallExp;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Callable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.Callable#getCalledBy <em>Called By</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Callable#getCallableParameters <em>Callable Parameters</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getCallable()
 * @model abstract="true"
 * @generated
 */
public interface Callable extends EObject {

	/**
	 * Returns the value of the '<em><b>Called By</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.PropertyCallExp}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called By</em>' reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCallable_CalledBy()
	 * @model
	 * @generated
	 */
	EList<PropertyCallExp> getCalledBy();

	/**
	 * Returns the value of the '<em><b>Callable Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.CallableParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Parameters</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCallable_CallableParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<CallableParameter> getCallableParameters();
} // Callable
