/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

import anatlyzer.atlext.ATL.RuleResolutionStatus;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule Resolution Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleResolutionInfoImpl#getRule <em>Rule</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleResolutionInfoImpl#getAllInvolvedRules <em>All Involved Rules</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleResolutionInfoImpl#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleResolutionInfoImpl extends MinimalEObjectImpl.Container implements RuleResolutionInfo {
	/**
	 * The cached value of the '{@link #getRule() <em>Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRule()
	 * @generated
	 * @ordered
	 */
	protected MatchedRule rule;

	/**
	 * The cached value of the '{@link #getAllInvolvedRules() <em>All Involved Rules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllInvolvedRules()
	 * @generated
	 * @ordered
	 */
	protected EList<MatchedRule> allInvolvedRules;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final RuleResolutionStatus STATUS_EDEFAULT = RuleResolutionStatus.RESOLUTION_UNKNOWN;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected RuleResolutionStatus status = STATUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleResolutionInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.RULE_RESOLUTION_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MatchedRule getRule() {
		if (rule != null && rule.eIsProxy()) {
			InternalEObject oldRule = (InternalEObject)rule;
			rule = (MatchedRule)eResolveProxy(oldRule);
			if (rule != oldRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.RULE_RESOLUTION_INFO__RULE, oldRule, rule));
			}
		}
		return rule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatchedRule basicGetRule() {
		return rule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRule(MatchedRule newRule) {
		MatchedRule oldRule = rule;
		rule = newRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_RESOLUTION_INFO__RULE, oldRule, rule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MatchedRule> getAllInvolvedRules() {
		if (allInvolvedRules == null) {
			allInvolvedRules = new EObjectResolvingEList<MatchedRule>(MatchedRule.class, this, ATLPackage.RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES);
		}
		return allInvolvedRules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RuleResolutionStatus getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStatus(RuleResolutionStatus newStatus) {
		RuleResolutionStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_RESOLUTION_INFO__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ATLPackage.RULE_RESOLUTION_INFO__RULE:
				if (resolve) return getRule();
				return basicGetRule();
			case ATLPackage.RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES:
				return getAllInvolvedRules();
			case ATLPackage.RULE_RESOLUTION_INFO__STATUS:
				return getStatus();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ATLPackage.RULE_RESOLUTION_INFO__RULE:
				setRule((MatchedRule)newValue);
				return;
			case ATLPackage.RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES:
				getAllInvolvedRules().clear();
				getAllInvolvedRules().addAll((Collection<? extends MatchedRule>)newValue);
				return;
			case ATLPackage.RULE_RESOLUTION_INFO__STATUS:
				setStatus((RuleResolutionStatus)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ATLPackage.RULE_RESOLUTION_INFO__RULE:
				setRule((MatchedRule)null);
				return;
			case ATLPackage.RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES:
				getAllInvolvedRules().clear();
				return;
			case ATLPackage.RULE_RESOLUTION_INFO__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ATLPackage.RULE_RESOLUTION_INFO__RULE:
				return rule != null;
			case ATLPackage.RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES:
				return allInvolvedRules != null && !allInvolvedRules.isEmpty();
			case ATLPackage.RULE_RESOLUTION_INFO__STATUS:
				return status != STATUS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (status: ");
		result.append(status);
		result.append(')');
		return result.toString();
	}

} //RuleResolutionInfoImpl
