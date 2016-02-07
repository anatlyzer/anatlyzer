/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.DifferentBranchTypes;

import anatlyzer.atl.types.Type;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Different Branch Types</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.DifferentBranchTypesImpl#getThenType <em>Then Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.DifferentBranchTypesImpl#getElseType <em>Else Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DifferentBranchTypesImpl extends NavigationProblemImpl implements DifferentBranchTypes {
	/**
	 * The cached value of the '{@link #getThenType() <em>Then Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenType()
	 * @generated
	 * @ordered
	 */
	protected Type thenType;

	/**
	 * The cached value of the '{@link #getElseType() <em>Else Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseType()
	 * @generated
	 * @ordered
	 */
	protected Type elseType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DifferentBranchTypesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.DIFFERENT_BRANCH_TYPES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getThenType() {
		if (thenType != null && thenType.eIsProxy()) {
			InternalEObject oldThenType = (InternalEObject)thenType;
			thenType = (Type)eResolveProxy(oldThenType);
			if (thenType != oldThenType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.DIFFERENT_BRANCH_TYPES__THEN_TYPE, oldThenType, thenType));
			}
		}
		return thenType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetThenType() {
		return thenType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThenType(Type newThenType) {
		Type oldThenType = thenType;
		thenType = newThenType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.DIFFERENT_BRANCH_TYPES__THEN_TYPE, oldThenType, thenType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getElseType() {
		if (elseType != null && elseType.eIsProxy()) {
			InternalEObject oldElseType = (InternalEObject)elseType;
			elseType = (Type)eResolveProxy(oldElseType);
			if (elseType != oldElseType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.DIFFERENT_BRANCH_TYPES__ELSE_TYPE, oldElseType, elseType));
			}
		}
		return elseType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetElseType() {
		return elseType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElseType(Type newElseType) {
		Type oldElseType = elseType;
		elseType = newElseType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.DIFFERENT_BRANCH_TYPES__ELSE_TYPE, oldElseType, elseType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__THEN_TYPE:
				if (resolve) return getThenType();
				return basicGetThenType();
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__ELSE_TYPE:
				if (resolve) return getElseType();
				return basicGetElseType();
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
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__THEN_TYPE:
				setThenType((Type)newValue);
				return;
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__ELSE_TYPE:
				setElseType((Type)newValue);
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
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__THEN_TYPE:
				setThenType((Type)null);
				return;
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__ELSE_TYPE:
				setElseType((Type)null);
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
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__THEN_TYPE:
				return thenType != null;
			case AtlErrorPackage.DIFFERENT_BRANCH_TYPES__ELSE_TYPE:
				return elseType != null;
		}
		return super.eIsSet(featureID);
	}

} //DifferentBranchTypesImpl
