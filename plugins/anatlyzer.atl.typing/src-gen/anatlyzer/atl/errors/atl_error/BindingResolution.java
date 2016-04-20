/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binding Resolution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRightType <em>Right Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingResolution#getTargetType <em>Target Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRight <em>Right</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingResolution#getLeft <em>Left</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRules <em>Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingResolution()
 * @model abstract="true"
 * @generated
 */
public interface BindingResolution extends EObject {
	/**
	 * Returns the value of the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Type</em>' reference.
	 * @see #setRightType(EClass)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingResolution_RightType()
	 * @model required="true"
	 * @generated
	 */
	EClass getRightType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRightType <em>Right Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Type</em>' reference.
	 * @see #getRightType()
	 * @generated
	 */
	void setRightType(EClass value);

	/**
	 * Returns the value of the '<em><b>Target Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Type</em>' reference.
	 * @see #setTargetType(EClass)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingResolution_TargetType()
	 * @model required="true"
	 * @generated
	 */
	EClass getTargetType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getTargetType <em>Target Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Type</em>' reference.
	 * @see #getTargetType()
	 * @generated
	 */
	void setTargetType(EClass value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(ModelElement)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingResolution_Right()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ModelElement getRight();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' containment reference.
	 * @see #setLeft(ModelElement)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingResolution_Left()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ModelElement getLeft();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.BindingResolution#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingResolution_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResolvedRuleInfo> getRules();

} // BindingResolution
