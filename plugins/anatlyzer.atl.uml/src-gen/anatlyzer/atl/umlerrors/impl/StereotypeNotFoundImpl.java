/**
 */
package anatlyzer.atl.umlerrors.impl;

import anatlyzer.atl.errors.atl_error.impl.LocalProblemImpl;

import anatlyzer.atl.umlerrors.StereotypeNotFound;
import anatlyzer.atl.umlerrors.UmlErrorsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stereotype Not Found</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.umlerrors.impl.StereotypeNotFoundImpl#getStereotypeName <em>Stereotype Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StereotypeNotFoundImpl extends LocalProblemImpl implements StereotypeNotFound {
	/**
	 * The default value of the '{@link #getStereotypeName() <em>Stereotype Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStereotypeName() <em>Stereotype Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotypeName()
	 * @generated
	 * @ordered
	 */
	protected String stereotypeName = STEREOTYPE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StereotypeNotFoundImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmlErrorsPackage.Literals.STEREOTYPE_NOT_FOUND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStereotypeName() {
		return stereotypeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStereotypeName(String newStereotypeName) {
		String oldStereotypeName = stereotypeName;
		stereotypeName = newStereotypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmlErrorsPackage.STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME, oldStereotypeName, stereotypeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UmlErrorsPackage.STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME:
				return getStereotypeName();
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
			case UmlErrorsPackage.STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME:
				setStereotypeName((String)newValue);
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
			case UmlErrorsPackage.STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME:
				setStereotypeName(STEREOTYPE_NAME_EDEFAULT);
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
			case UmlErrorsPackage.STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME:
				return STEREOTYPE_NAME_EDEFAULT == null ? stereotypeName != null : !STEREOTYPE_NAME_EDEFAULT.equals(stereotypeName);
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
		result.append(" (stereotypeName: ");
		result.append(stereotypeName);
		result.append(')');
		return result.toString();
	}

} //StereotypeNotFoundImpl
