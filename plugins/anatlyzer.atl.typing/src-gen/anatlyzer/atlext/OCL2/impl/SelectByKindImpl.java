/**
 */
package anatlyzer.atlext.OCL2.impl;

import anatlyzer.atlext.OCL.impl.CollectionOperationCallExpImpl;

import anatlyzer.atlext.OCL2.OCL2Package;
import anatlyzer.atlext.OCL2.SelectByKind;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Select By Kind</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL2.impl.SelectByKindImpl#isIsExact <em>Is Exact</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SelectByKindImpl extends CollectionOperationCallExpImpl implements SelectByKind {
	/**
	 * The default value of the '{@link #isIsExact() <em>Is Exact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExact()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_EXACT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsExact() <em>Is Exact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExact()
	 * @generated
	 * @ordered
	 */
	protected boolean isExact = IS_EXACT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SelectByKindImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCL2Package.Literals.SELECT_BY_KIND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsExact() {
		return isExact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsExact(boolean newIsExact) {
		boolean oldIsExact = isExact;
		isExact = newIsExact;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCL2Package.SELECT_BY_KIND__IS_EXACT, oldIsExact, isExact));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OCL2Package.SELECT_BY_KIND__IS_EXACT:
				return isIsExact();
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
			case OCL2Package.SELECT_BY_KIND__IS_EXACT:
				setIsExact((Boolean)newValue);
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
			case OCL2Package.SELECT_BY_KIND__IS_EXACT:
				setIsExact(IS_EXACT_EDEFAULT);
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
			case OCL2Package.SELECT_BY_KIND__IS_EXACT:
				return isExact != IS_EXACT_EDEFAULT;
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
		result.append(" (isExact: ");
		result.append(isExact);
		result.append(')');
		return result.toString();
	}

} //SelectByKindImpl
