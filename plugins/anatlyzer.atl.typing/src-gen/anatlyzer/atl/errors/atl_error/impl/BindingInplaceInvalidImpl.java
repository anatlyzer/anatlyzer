/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.BindingInplaceInvalid;

import anatlyzer.atl.types.Type;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binding Inplace Invalid</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.BindingInplaceInvalidImpl#getRightType <em>Right Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BindingInplaceInvalidImpl extends BindingProblemImpl implements BindingInplaceInvalid {
	/**
	 * The cached value of the '{@link #getRightType() <em>Right Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightType()
	 * @generated
	 * @ordered
	 */
	protected Type rightType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BindingInplaceInvalidImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.BINDING_INPLACE_INVALID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getRightType() {
		if (rightType != null && rightType.eIsProxy()) {
			InternalEObject oldRightType = (InternalEObject)rightType;
			rightType = (Type)eResolveProxy(oldRightType);
			if (rightType != oldRightType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.BINDING_INPLACE_INVALID__RIGHT_TYPE, oldRightType, rightType));
			}
		}
		return rightType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetRightType() {
		return rightType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightType(Type newRightType) {
		Type oldRightType = rightType;
		rightType = newRightType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.BINDING_INPLACE_INVALID__RIGHT_TYPE, oldRightType, rightType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.BINDING_INPLACE_INVALID__RIGHT_TYPE:
				if (resolve) return getRightType();
				return basicGetRightType();
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
			case AtlErrorPackage.BINDING_INPLACE_INVALID__RIGHT_TYPE:
				setRightType((Type)newValue);
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
			case AtlErrorPackage.BINDING_INPLACE_INVALID__RIGHT_TYPE:
				setRightType((Type)null);
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
			case AtlErrorPackage.BINDING_INPLACE_INVALID__RIGHT_TYPE:
				return rightType != null;
		}
		return super.eIsSet(featureID);
	}

} //BindingInplaceInvalidImpl
