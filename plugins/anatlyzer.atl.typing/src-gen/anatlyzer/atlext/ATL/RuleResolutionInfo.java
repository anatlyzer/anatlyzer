/**
 */
package anatlyzer.atlext.ATL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Resolution Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getRule <em>Rule</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getAllInvolvedRules <em>All Involved Rules</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleResolutionInfo()
 * @model
 * @generated
 */
public interface RuleResolutionInfo extends EObject {
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
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleResolutionInfo_Rule()
	 * @model required="true"
	 * @generated
	 */
	MatchedRule getRule();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getRule <em>Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule</em>' reference.
	 * @see #getRule()
	 * @generated
	 */
	void setRule(MatchedRule value);

	/**
	 * Returns the value of the '<em><b>All Involved Rules</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.MatchedRule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Involved Rules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Involved Rules</em>' reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleResolutionInfo_AllInvolvedRules()
	 * @model required="true"
	 * @generated
	 */
	EList<MatchedRule> getAllInvolvedRules();

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link anatlyzer.atlext.ATL.RuleResolutionStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see anatlyzer.atlext.ATL.RuleResolutionStatus
	 * @see #setStatus(RuleResolutionStatus)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleResolutionInfo_Status()
	 * @model required="true"
	 * @generated
	 */
	RuleResolutionStatus getStatus();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.RuleResolutionInfo#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see anatlyzer.atlext.ATL.RuleResolutionStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(RuleResolutionStatus value);

} // RuleResolutionInfo
