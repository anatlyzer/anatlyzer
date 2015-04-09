/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.Parameter;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Called Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.CalledRule#getParameters <em>Parameters</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.CalledRule#isIsEntrypoint <em>Is Entrypoint</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.CalledRule#isIsEndpoint <em>Is Endpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getCalledRule()
 * @model
 * @generated
 */
public interface CalledRule extends StaticRule {
	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCalledRule_Parameters()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Is Entrypoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Entrypoint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Entrypoint</em>' attribute.
	 * @see #setIsEntrypoint(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCalledRule_IsEntrypoint()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsEntrypoint();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.CalledRule#isIsEntrypoint <em>Is Entrypoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Entrypoint</em>' attribute.
	 * @see #isIsEntrypoint()
	 * @generated
	 */
	void setIsEntrypoint(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Endpoint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Endpoint</em>' attribute.
	 * @see #setIsEndpoint(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCalledRule_IsEndpoint()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsEndpoint();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.CalledRule#isIsEndpoint <em>Is Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Endpoint</em>' attribute.
	 * @see #isIsEndpoint()
	 * @generated
	 */
	void setIsEndpoint(boolean value);

} // CalledRule
