/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unknown Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.UnknownFeature#getTheContainingClass <em>The Containing Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getUnknownFeature()
 * @model
 * @generated
 */
public interface UnknownFeature extends EObject, EStructuralFeature {
	/**
	 * Returns the value of the '<em><b>The Containing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>The Containing Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>The Containing Class</em>' reference.
	 * @see #setTheContainingClass(EClass)
	 * @see anatlyzer.atl.types.TypesPackage#getUnknownFeature_TheContainingClass()
	 * @model required="true"
	 * @generated
	 */
	EClass getTheContainingClass();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.UnknownFeature#getTheContainingClass <em>The Containing Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>The Containing Class</em>' reference.
	 * @see #getTheContainingClass()
	 * @generated
	 */
	void setTheContainingClass(EClass value);

} // UnknownFeature
