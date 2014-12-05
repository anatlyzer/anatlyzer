/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.OclModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.Module#isIsRefining <em>Is Refining</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Module#getInModels <em>In Models</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Module#getOutModels <em>Out Models</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Module#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getModule()
 * @model
 * @generated
 */
public interface Module extends Unit {
	/**
	 * Returns the value of the '<em><b>Is Refining</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Refining</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Refining</em>' attribute.
	 * @see #setIsRefining(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getModule_IsRefining()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsRefining();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Module#isIsRefining <em>Is Refining</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Refining</em>' attribute.
	 * @see #isIsRefining()
	 * @generated
	 */
	void setIsRefining(boolean value);

	/**
	 * Returns the value of the '<em><b>In Models</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.OclModel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Models</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Models</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getModule_InModels()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<OclModel> getInModels();

	/**
	 * Returns the value of the '<em><b>Out Models</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.OclModel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Models</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Models</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getModule_OutModels()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<OclModel> getOutModels();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.ModuleElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getModule_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ModuleElement> getElements();

} // Module
