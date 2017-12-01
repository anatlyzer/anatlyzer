/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.GenericLocalProblem;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Local Problem</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.GenericLocalProblemImpl#getGenericKind <em>Generic Kind</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericLocalProblemImpl extends LocalProblemImpl implements GenericLocalProblem {
	/**
	 * The default value of the '{@link #getGenericKind() <em>Generic Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenericKind()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERIC_KIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGenericKind() <em>Generic Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenericKind()
	 * @generated
	 * @ordered
	 */
	protected String genericKind = GENERIC_KIND_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericLocalProblemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.GENERIC_LOCAL_PROBLEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGenericKind() {
		return genericKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenericKind(String newGenericKind) {
		String oldGenericKind = genericKind;
		genericKind = newGenericKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.GENERIC_LOCAL_PROBLEM__GENERIC_KIND, oldGenericKind, genericKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.GENERIC_LOCAL_PROBLEM__GENERIC_KIND:
				return getGenericKind();
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
			case AtlErrorPackage.GENERIC_LOCAL_PROBLEM__GENERIC_KIND:
				setGenericKind((String)newValue);
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
			case AtlErrorPackage.GENERIC_LOCAL_PROBLEM__GENERIC_KIND:
				setGenericKind(GENERIC_KIND_EDEFAULT);
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
			case AtlErrorPackage.GENERIC_LOCAL_PROBLEM__GENERIC_KIND:
				return GENERIC_KIND_EDEFAULT == null ? genericKind != null : !GENERIC_KIND_EDEFAULT.equals(genericKind);
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
		result.append(" (genericKind: ");
		result.append(genericKind);
		result.append(')');
		return result.toString();
	}

} //GenericLocalProblemImpl
