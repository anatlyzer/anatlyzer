/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolve Temp Without Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule#getSourceType <em>Source Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempWithoutRule()
 * @model
 * @generated
 */
public interface ResolveTempWithoutRule extends ResolveTempProblem {
	/**
	 * Returns the value of the '<em><b>Source Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Type</em>' reference.
	 * @see #setSourceType(EClass)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempWithoutRule_SourceType()
	 * @model
	 * @generated
	 */
	EClass getSourceType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule#getSourceType <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Type</em>' reference.
	 * @see #getSourceType()
	 * @generated
	 */
	void setSourceType(EClass value);

} // ResolveTempWithoutRule
