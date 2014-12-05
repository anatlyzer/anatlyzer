/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.RuleWithPattern;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule With Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl#getInPattern <em>In Pattern</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl#getSuperRule <em>Super Rule</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl#isIsRefining <em>Is Refining</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.RuleWithPatternImpl#isIsNoDefault <em>Is No Default</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class RuleWithPatternImpl extends RuleImpl implements RuleWithPattern {
	/**
	 * The cached value of the '{@link #getInPattern() <em>In Pattern</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInPattern()
	 * @generated
	 * @ordered
	 */
	protected InPattern inPattern;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<RuleWithPattern> children;

	/**
	 * The cached value of the '{@link #getSuperRule() <em>Super Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperRule()
	 * @generated
	 * @ordered
	 */
	protected RuleWithPattern superRule;

	/**
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsRefining() <em>Is Refining</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRefining()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_REFINING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsRefining() <em>Is Refining</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRefining()
	 * @generated
	 * @ordered
	 */
	protected boolean isRefining = IS_REFINING_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNoDefault() <em>Is No Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNoDefault()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NO_DEFAULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNoDefault() <em>Is No Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNoDefault()
	 * @generated
	 * @ordered
	 */
	protected boolean isNoDefault = IS_NO_DEFAULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleWithPatternImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.RULE_WITH_PATTERN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InPattern getInPattern() {
		return inPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInPattern(InPattern newInPattern, NotificationChain msgs) {
		InPattern oldInPattern = inPattern;
		inPattern = newInPattern;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_WITH_PATTERN__IN_PATTERN, oldInPattern, newInPattern);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInPattern(InPattern newInPattern) {
		if (newInPattern != inPattern) {
			NotificationChain msgs = null;
			if (inPattern != null)
				msgs = ((InternalEObject)inPattern).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ATLPackage.RULE_WITH_PATTERN__IN_PATTERN, null, msgs);
			if (newInPattern != null)
				msgs = ((InternalEObject)newInPattern).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ATLPackage.RULE_WITH_PATTERN__IN_PATTERN, null, msgs);
			msgs = basicSetInPattern(newInPattern, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_WITH_PATTERN__IN_PATTERN, newInPattern, newInPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RuleWithPattern> getChildren() {
		if (children == null) {
			children = new EObjectWithInverseResolvingEList<RuleWithPattern>(RuleWithPattern.class, this, ATLPackage.RULE_WITH_PATTERN__CHILDREN, ATLPackage.RULE_WITH_PATTERN__SUPER_RULE);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleWithPattern getSuperRule() {
		if (superRule != null && superRule.eIsProxy()) {
			InternalEObject oldSuperRule = (InternalEObject)superRule;
			superRule = (RuleWithPattern)eResolveProxy(oldSuperRule);
			if (superRule != oldSuperRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.RULE_WITH_PATTERN__SUPER_RULE, oldSuperRule, superRule));
			}
		}
		return superRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleWithPattern basicGetSuperRule() {
		return superRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuperRule(RuleWithPattern newSuperRule, NotificationChain msgs) {
		RuleWithPattern oldSuperRule = superRule;
		superRule = newSuperRule;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_WITH_PATTERN__SUPER_RULE, oldSuperRule, newSuperRule);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperRule(RuleWithPattern newSuperRule) {
		if (newSuperRule != superRule) {
			NotificationChain msgs = null;
			if (superRule != null)
				msgs = ((InternalEObject)superRule).eInverseRemove(this, ATLPackage.RULE_WITH_PATTERN__CHILDREN, RuleWithPattern.class, msgs);
			if (newSuperRule != null)
				msgs = ((InternalEObject)newSuperRule).eInverseAdd(this, ATLPackage.RULE_WITH_PATTERN__CHILDREN, RuleWithPattern.class, msgs);
			msgs = basicSetSuperRule(newSuperRule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_WITH_PATTERN__SUPER_RULE, newSuperRule, newSuperRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_WITH_PATTERN__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsRefining() {
		return isRefining;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsRefining(boolean newIsRefining) {
		boolean oldIsRefining = isRefining;
		isRefining = newIsRefining;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_WITH_PATTERN__IS_REFINING, oldIsRefining, isRefining));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNoDefault() {
		return isNoDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNoDefault(boolean newIsNoDefault) {
		boolean oldIsNoDefault = isNoDefault;
		isNoDefault = newIsNoDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.RULE_WITH_PATTERN__IS_NO_DEFAULT, oldIsNoDefault, isNoDefault));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.RULE_WITH_PATTERN__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case ATLPackage.RULE_WITH_PATTERN__SUPER_RULE:
				if (superRule != null)
					msgs = ((InternalEObject)superRule).eInverseRemove(this, ATLPackage.RULE_WITH_PATTERN__CHILDREN, RuleWithPattern.class, msgs);
				return basicSetSuperRule((RuleWithPattern)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.RULE_WITH_PATTERN__IN_PATTERN:
				return basicSetInPattern(null, msgs);
			case ATLPackage.RULE_WITH_PATTERN__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ATLPackage.RULE_WITH_PATTERN__SUPER_RULE:
				return basicSetSuperRule(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ATLPackage.RULE_WITH_PATTERN__IN_PATTERN:
				return getInPattern();
			case ATLPackage.RULE_WITH_PATTERN__CHILDREN:
				return getChildren();
			case ATLPackage.RULE_WITH_PATTERN__SUPER_RULE:
				if (resolve) return getSuperRule();
				return basicGetSuperRule();
			case ATLPackage.RULE_WITH_PATTERN__IS_ABSTRACT:
				return isIsAbstract();
			case ATLPackage.RULE_WITH_PATTERN__IS_REFINING:
				return isIsRefining();
			case ATLPackage.RULE_WITH_PATTERN__IS_NO_DEFAULT:
				return isIsNoDefault();
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
			case ATLPackage.RULE_WITH_PATTERN__IN_PATTERN:
				setInPattern((InPattern)newValue);
				return;
			case ATLPackage.RULE_WITH_PATTERN__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends RuleWithPattern>)newValue);
				return;
			case ATLPackage.RULE_WITH_PATTERN__SUPER_RULE:
				setSuperRule((RuleWithPattern)newValue);
				return;
			case ATLPackage.RULE_WITH_PATTERN__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case ATLPackage.RULE_WITH_PATTERN__IS_REFINING:
				setIsRefining((Boolean)newValue);
				return;
			case ATLPackage.RULE_WITH_PATTERN__IS_NO_DEFAULT:
				setIsNoDefault((Boolean)newValue);
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
			case ATLPackage.RULE_WITH_PATTERN__IN_PATTERN:
				setInPattern((InPattern)null);
				return;
			case ATLPackage.RULE_WITH_PATTERN__CHILDREN:
				getChildren().clear();
				return;
			case ATLPackage.RULE_WITH_PATTERN__SUPER_RULE:
				setSuperRule((RuleWithPattern)null);
				return;
			case ATLPackage.RULE_WITH_PATTERN__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case ATLPackage.RULE_WITH_PATTERN__IS_REFINING:
				setIsRefining(IS_REFINING_EDEFAULT);
				return;
			case ATLPackage.RULE_WITH_PATTERN__IS_NO_DEFAULT:
				setIsNoDefault(IS_NO_DEFAULT_EDEFAULT);
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
			case ATLPackage.RULE_WITH_PATTERN__IN_PATTERN:
				return inPattern != null;
			case ATLPackage.RULE_WITH_PATTERN__CHILDREN:
				return children != null && !children.isEmpty();
			case ATLPackage.RULE_WITH_PATTERN__SUPER_RULE:
				return superRule != null;
			case ATLPackage.RULE_WITH_PATTERN__IS_ABSTRACT:
				return isAbstract != IS_ABSTRACT_EDEFAULT;
			case ATLPackage.RULE_WITH_PATTERN__IS_REFINING:
				return isRefining != IS_REFINING_EDEFAULT;
			case ATLPackage.RULE_WITH_PATTERN__IS_NO_DEFAULT:
				return isNoDefault != IS_NO_DEFAULT_EDEFAULT;
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isAbstract: ");
		result.append(isAbstract);
		result.append(", isRefining: ");
		result.append(isRefining);
		result.append(", isNoDefault: ");
		result.append(isNoDefault);
		result.append(')');
		return result.toString();
	}

} //RuleWithPatternImpl
