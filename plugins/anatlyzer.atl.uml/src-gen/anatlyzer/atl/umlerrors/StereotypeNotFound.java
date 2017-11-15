/**
 */
package anatlyzer.atl.umlerrors;

import anatlyzer.atl.errors.atl_error.LocalProblem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stereotype Not Found</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.umlerrors.StereotypeNotFound#getStereotypeName <em>Stereotype Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.umlerrors.UmlErrorsPackage#getStereotypeNotFound()
 * @model annotation="description name='UML Stereotype not found' text='' example=''"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='src-typing' phase='typing' source=''"
 * @generated
 */
public interface StereotypeNotFound extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Stereotype Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype Name</em>' attribute.
	 * @see #setStereotypeName(String)
	 * @see anatlyzer.atl.umlerrors.UmlErrorsPackage#getStereotypeNotFound_StereotypeName()
	 * @model required="true"
	 * @generated
	 */
	String getStereotypeName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.umlerrors.StereotypeNotFound#getStereotypeName <em>Stereotype Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype Name</em>' attribute.
	 * @see #getStereotypeName()
	 * @generated
	 */
	void setStereotypeName(String value);

} // StereotypeNotFound
