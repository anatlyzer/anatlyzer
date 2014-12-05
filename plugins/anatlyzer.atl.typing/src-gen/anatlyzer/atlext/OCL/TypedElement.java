/**
 */
package anatlyzer.atlext.OCL;

import anatlyzer.atl.types.Type;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.TypedElement#getInferredType <em>Inferred Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getTypedElement()
 * @model abstract="true"
 * @generated
 */
public interface TypedElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inferred Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inferred Type</em>' reference.
	 * @see #setInferredType(Type)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getTypedElement_InferredType()
	 * @model
	 * @generated
	 */
	Type getInferredType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.TypedElement#getInferredType <em>Inferred Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inferred Type</em>' reference.
	 * @see #getInferredType()
	 * @generated
	 */
	void setInferredType(Type value);

} // TypedElement
