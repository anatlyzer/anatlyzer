/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resolve Temp Output Pattern Element Not Found</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempOutputPatternElementNotFoundImpl#getSourceType <em>Source Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolveTempOutputPatternElementNotFoundImpl#getRules <em>Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResolveTempOutputPatternElementNotFoundImpl extends ResolveTempProblemImpl implements ResolveTempOutputPatternElementNotFound {
	/**
	 * The cached value of the '{@link #getSourceType() <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceType()
	 * @generated
	 * @ordered
	 */
	protected EClass sourceType;

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<ResolvedRuleInfo> rules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResolveTempOutputPatternElementNotFoundImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSourceType() {
		if (sourceType != null && sourceType.eIsProxy()) {
			InternalEObject oldSourceType = (InternalEObject)sourceType;
			sourceType = (EClass)eResolveProxy(oldSourceType);
			if (sourceType != oldSourceType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE, oldSourceType, sourceType));
			}
		}
		return sourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetSourceType() {
		return sourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceType(EClass newSourceType) {
		EClass oldSourceType = sourceType;
		sourceType = newSourceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE, oldSourceType, sourceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResolvedRuleInfo> getRules() {
		if (rules == null) {
			rules = new EObjectContainmentEList<ResolvedRuleInfo>(ResolvedRuleInfo.class, this, AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES:
				return ((InternalEList<?>)getRules()).basicRemove(otherEnd, msgs);
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
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE:
				if (resolve) return getSourceType();
				return basicGetSourceType();
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES:
				return getRules();
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
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE:
				setSourceType((EClass)newValue);
				return;
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES:
				getRules().clear();
				getRules().addAll((Collection<? extends ResolvedRuleInfo>)newValue);
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
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE:
				setSourceType((EClass)null);
				return;
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES:
				getRules().clear();
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
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__SOURCE_TYPE:
				return sourceType != null;
			case AtlErrorPackage.RESOLVE_TEMP_OUTPUT_PATTERN_ELEMENT_NOT_FOUND__RULES:
				return rules != null && !rules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ResolveTempOutputPatternElementNotFoundImpl
