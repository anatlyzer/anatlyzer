/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resolved Rule Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl#getElement <em>Element</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl#getAllInvolvedRules <em>All Involved Rules</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl#getRuleName <em>Rule Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl#getInputType <em>Input Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ResolvedRuleInfoImpl#getOutputType <em>Output Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResolvedRuleInfoImpl extends MinimalEObjectImpl.Container implements ResolvedRuleInfo {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected EObject element;

	/**
	 * The cached value of the '{@link #getAllInvolvedRules() <em>All Involved Rules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllInvolvedRules()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> allInvolvedRules;

	/**
	 * The default value of the '{@link #getRuleName() <em>Rule Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuleName()
	 * @generated
	 * @ordered
	 */
	protected static final String RULE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRuleName() <em>Rule Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuleName()
	 * @generated
	 * @ordered
	 */
	protected String ruleName = RULE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInputType() <em>Input Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputType()
	 * @generated
	 * @ordered
	 */
	protected EClass inputType;

	/**
	 * The cached value of the '{@link #getOutputType() <em>Output Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputType()
	 * @generated
	 * @ordered
	 */
	protected EClass outputType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResolvedRuleInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.RESOLVED_RULE_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.RESOLVED_RULE_INFO__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getElement() {
		if (element != null && element.eIsProxy()) {
			InternalEObject oldElement = (InternalEObject)element;
			element = eResolveProxy(oldElement);
			if (element != oldElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.RESOLVED_RULE_INFO__ELEMENT, oldElement, element));
			}
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(EObject newElement) {
		EObject oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.RESOLVED_RULE_INFO__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getAllInvolvedRules() {
		if (allInvolvedRules == null) {
			allInvolvedRules = new EObjectResolvingEList<EObject>(EObject.class, this, AtlErrorPackage.RESOLVED_RULE_INFO__ALL_INVOLVED_RULES);
		}
		return allInvolvedRules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuleName(String newRuleName) {
		String oldRuleName = ruleName;
		ruleName = newRuleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.RESOLVED_RULE_INFO__RULE_NAME, oldRuleName, ruleName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInputType() {
		if (inputType != null && inputType.eIsProxy()) {
			InternalEObject oldInputType = (InternalEObject)inputType;
			inputType = (EClass)eResolveProxy(oldInputType);
			if (inputType != oldInputType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.RESOLVED_RULE_INFO__INPUT_TYPE, oldInputType, inputType));
			}
		}
		return inputType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetInputType() {
		return inputType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputType(EClass newInputType) {
		EClass oldInputType = inputType;
		inputType = newInputType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.RESOLVED_RULE_INFO__INPUT_TYPE, oldInputType, inputType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutputType() {
		if (outputType != null && outputType.eIsProxy()) {
			InternalEObject oldOutputType = (InternalEObject)outputType;
			outputType = (EClass)eResolveProxy(oldOutputType);
			if (outputType != oldOutputType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.RESOLVED_RULE_INFO__OUTPUT_TYPE, oldOutputType, outputType));
			}
		}
		return outputType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetOutputType() {
		return outputType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputType(EClass newOutputType) {
		EClass oldOutputType = outputType;
		outputType = newOutputType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.RESOLVED_RULE_INFO__OUTPUT_TYPE, oldOutputType, outputType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.RESOLVED_RULE_INFO__LOCATION:
				return getLocation();
			case AtlErrorPackage.RESOLVED_RULE_INFO__ELEMENT:
				if (resolve) return getElement();
				return basicGetElement();
			case AtlErrorPackage.RESOLVED_RULE_INFO__ALL_INVOLVED_RULES:
				return getAllInvolvedRules();
			case AtlErrorPackage.RESOLVED_RULE_INFO__RULE_NAME:
				return getRuleName();
			case AtlErrorPackage.RESOLVED_RULE_INFO__INPUT_TYPE:
				if (resolve) return getInputType();
				return basicGetInputType();
			case AtlErrorPackage.RESOLVED_RULE_INFO__OUTPUT_TYPE:
				if (resolve) return getOutputType();
				return basicGetOutputType();
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
			case AtlErrorPackage.RESOLVED_RULE_INFO__LOCATION:
				setLocation((String)newValue);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__ELEMENT:
				setElement((EObject)newValue);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__ALL_INVOLVED_RULES:
				getAllInvolvedRules().clear();
				getAllInvolvedRules().addAll((Collection<? extends EObject>)newValue);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__RULE_NAME:
				setRuleName((String)newValue);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__INPUT_TYPE:
				setInputType((EClass)newValue);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__OUTPUT_TYPE:
				setOutputType((EClass)newValue);
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
			case AtlErrorPackage.RESOLVED_RULE_INFO__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__ELEMENT:
				setElement((EObject)null);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__ALL_INVOLVED_RULES:
				getAllInvolvedRules().clear();
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__RULE_NAME:
				setRuleName(RULE_NAME_EDEFAULT);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__INPUT_TYPE:
				setInputType((EClass)null);
				return;
			case AtlErrorPackage.RESOLVED_RULE_INFO__OUTPUT_TYPE:
				setOutputType((EClass)null);
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
			case AtlErrorPackage.RESOLVED_RULE_INFO__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case AtlErrorPackage.RESOLVED_RULE_INFO__ELEMENT:
				return element != null;
			case AtlErrorPackage.RESOLVED_RULE_INFO__ALL_INVOLVED_RULES:
				return allInvolvedRules != null && !allInvolvedRules.isEmpty();
			case AtlErrorPackage.RESOLVED_RULE_INFO__RULE_NAME:
				return RULE_NAME_EDEFAULT == null ? ruleName != null : !RULE_NAME_EDEFAULT.equals(ruleName);
			case AtlErrorPackage.RESOLVED_RULE_INFO__INPUT_TYPE:
				return inputType != null;
			case AtlErrorPackage.RESOLVED_RULE_INFO__OUTPUT_TYPE:
				return outputType != null;
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
		result.append(" (location: ");
		result.append(location);
		result.append(", ruleName: ");
		result.append(ruleName);
		result.append(')');
		return result.toString();
	}

} //ResolvedRuleInfoImpl
