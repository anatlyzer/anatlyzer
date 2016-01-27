/**
 */
package anatlyzer.atl.impact;

import anatlyzer.atlext.ATL.MatchedRule;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Create Matched Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.impact.CreateMatchedRule#getRule <em>Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.impact.ImpactPackage#getCreateMatchedRule()
 * @model
 * @generated
 */
public interface CreateMatchedRule extends Change, Create {
	/**
	 * Returns the value of the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule</em>' reference.
	 * @see #setRule(MatchedRule)
	 * @see anatlyzer.atl.impact.ImpactPackage#getCreateMatchedRule_Rule()
	 * @model required="true"
	 * @generated
	 */
	MatchedRule getRule();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.impact.CreateMatchedRule#getRule <em>Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule</em>' reference.
	 * @see #getRule()
	 * @generated
	 */
	void setRule(MatchedRule value);

} // CreateMatchedRule
