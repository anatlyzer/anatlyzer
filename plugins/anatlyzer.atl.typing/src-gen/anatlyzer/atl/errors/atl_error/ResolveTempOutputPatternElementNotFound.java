/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolve Temp Output Pattern Element Not Found</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound#getSourceType <em>Source Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound#getRules <em>Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempOutputPatternElementNotFound()
 * @model
 * @generated
 */
public interface ResolveTempOutputPatternElementNotFound extends ResolveTempProblem {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempOutputPatternElementNotFound_SourceType()
	 * @model
	 * @generated
	 */
	EClass getSourceType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound#getSourceType <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Type</em>' reference.
	 * @see #getSourceType()
	 * @generated
	 */
	void setSourceType(EClass value);

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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempOutputPatternElementNotFound_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResolvedRuleInfo> getRules();

} // ResolveTempOutputPatternElementNotFound
