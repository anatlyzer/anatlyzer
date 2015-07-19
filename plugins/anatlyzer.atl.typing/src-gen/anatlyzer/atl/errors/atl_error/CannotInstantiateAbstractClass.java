/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.types.Metaclass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cannot Instantiate Abstract Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getCannotInstantiateAbstractClass()
 * @model annotation="description name='Abstract class instantiation' text='' example=''"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='trafo-integrity' phase='typing' source=''"
 * @generated
 */
public interface CannotInstantiateAbstractClass extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Metaclass)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getCannotInstantiateAbstractClass_Type()
	 * @model required="true"
	 * @generated
	 */
	Metaclass getType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Metaclass value);

} // CannotInstantiateAbstractClass
