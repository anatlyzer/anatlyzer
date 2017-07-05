/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.TargetInvariantViolation;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Target Invariant Violation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.TargetInvariantViolationImpl#getInvariantName <em>Invariant Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TargetInvariantViolationImpl extends BatchTargetConformanceIssueImpl implements TargetInvariantViolation {
	/**
	 * The default value of the '{@link #getInvariantName() <em>Invariant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvariantName()
	 * @generated
	 * @ordered
	 */
	protected static final String INVARIANT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInvariantName() <em>Invariant Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvariantName()
	 * @generated
	 * @ordered
	 */
	protected String invariantName = INVARIANT_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TargetInvariantViolationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.TARGET_INVARIANT_VIOLATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInvariantName() {
		return invariantName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvariantName(String newInvariantName) {
		String oldInvariantName = invariantName;
		invariantName = newInvariantName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.TARGET_INVARIANT_VIOLATION__INVARIANT_NAME, oldInvariantName, invariantName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.TARGET_INVARIANT_VIOLATION__INVARIANT_NAME:
				return getInvariantName();
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
			case AtlErrorPackage.TARGET_INVARIANT_VIOLATION__INVARIANT_NAME:
				setInvariantName((String)newValue);
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
			case AtlErrorPackage.TARGET_INVARIANT_VIOLATION__INVARIANT_NAME:
				setInvariantName(INVARIANT_NAME_EDEFAULT);
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
			case AtlErrorPackage.TARGET_INVARIANT_VIOLATION__INVARIANT_NAME:
				return INVARIANT_NAME_EDEFAULT == null ? invariantName != null : !INVARIANT_NAME_EDEFAULT.equals(invariantName);
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
		result.append(" (invariantName: ");
		result.append(invariantName);
		result.append(')');
		return result.toString();
	}

} //TargetInvariantViolationImpl
