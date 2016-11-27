/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.types.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binding Inplace Invalid</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingInplaceInvalid#getRightType <em>Right Type</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingInplaceInvalid()
 * @model annotation="description name='Invalid in-place binding assignment' text='' example=''"
 *        annotation="info prec='static' path='yes' severity='error-target' when='model-dep' kind='tgt-typing' phase='analysis' source=''"
 * @generated
 */
public interface BindingInplaceInvalid extends BindingProblem {
	/**
	 * Returns the value of the '<em><b>Right Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Type</em>' reference.
	 * @see #setRightType(Type)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingInplaceInvalid_RightType()
	 * @model required="true"
	 * @generated
	 */
	Type getRightType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.BindingInplaceInvalid#getRightType <em>Right Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Type</em>' reference.
	 * @see #getRightType()
	 * @generated
	 */
	void setRightType(Type value);

} // BindingInplaceInvalid
