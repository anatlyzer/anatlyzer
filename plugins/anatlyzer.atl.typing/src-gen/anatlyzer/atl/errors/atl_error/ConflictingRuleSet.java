/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conflicting Rule Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getRules <em>Rules</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getAnalyserInfo <em>Analyser Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getConflictingRuleSet()
 * @model
 * @generated
 */
public interface ConflictingRuleSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Rules</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getConflictingRuleSet_Rules()
	 * @model
	 * @generated
	 */
	EList<EObject> getRules();

	/**
	 * Returns the value of the '<em><b>Analyser Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Analyser Info</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analyser Info</em>' attribute.
	 * @see #setAnalyserInfo(Object)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getConflictingRuleSet_AnalyserInfo()
	 * @model
	 * @generated
	 */
	Object getAnalyserInfo();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet#getAnalyserInfo <em>Analyser Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analyser Info</em>' attribute.
	 * @see #getAnalyserInfo()
	 * @generated
	 */
	void setAnalyserInfo(Object value);

} // ConflictingRuleSet
