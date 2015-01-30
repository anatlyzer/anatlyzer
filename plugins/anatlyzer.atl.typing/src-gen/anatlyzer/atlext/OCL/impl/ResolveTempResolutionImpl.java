/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;

import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.ResolveTempResolution;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resolve Temp Resolution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.ResolveTempResolutionImpl#getRule <em>Rule</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.ResolveTempResolutionImpl#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResolveTempResolutionImpl extends MinimalEObjectImpl.Container implements ResolveTempResolution {
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
	 * The cached value of the '{@link #getElement() <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected OutPatternElement element;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResolveTempResolutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.RESOLVE_TEMP_RESOLUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatchedRule getRule() {
		if (rule != null && rule.eIsProxy()) {
			InternalEObject oldRule = (InternalEObject)rule;
			rule = (MatchedRule)eResolveProxy(oldRule);
			if (rule != oldRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLPackage.RESOLVE_TEMP_RESOLUTION__RULE, oldRule, rule));
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
	public void setRule(MatchedRule newRule) {
		MatchedRule oldRule = rule;
		rule = newRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.RESOLVE_TEMP_RESOLUTION__RULE, oldRule, rule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPatternElement getElement() {
		if (element != null && element.eIsProxy()) {
			InternalEObject oldElement = (InternalEObject)element;
			element = (OutPatternElement)eResolveProxy(oldElement);
			if (element != oldElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLPackage.RESOLVE_TEMP_RESOLUTION__ELEMENT, oldElement, element));
			}
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPatternElement basicGetElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(OutPatternElement newElement) {
		OutPatternElement oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.RESOLVE_TEMP_RESOLUTION__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__RULE:
				if (resolve) return getRule();
				return basicGetRule();
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__ELEMENT:
				if (resolve) return getElement();
				return basicGetElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__RULE:
				setRule((MatchedRule)newValue);
				return;
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__ELEMENT:
				setElement((OutPatternElement)newValue);
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
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__RULE:
				setRule((MatchedRule)null);
				return;
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__ELEMENT:
				setElement((OutPatternElement)null);
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
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__RULE:
				return rule != null;
			case OCLPackage.RESOLVE_TEMP_RESOLUTION__ELEMENT:
				return element != null;
		}
		return super.eIsSet(featureID);
	}

} //ResolveTempResolutionImpl
