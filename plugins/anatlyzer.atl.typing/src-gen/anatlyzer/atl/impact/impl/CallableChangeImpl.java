/**
 */
package anatlyzer.atl.impact.impl;

import anatlyzer.atl.impact.CallableChange;
import anatlyzer.atl.impact.ImpactPackage;

import anatlyzer.atlext.ATL.Callable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Callable Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.impact.impl.CallableChangeImpl#getCallable <em>Callable</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CallableChangeImpl extends ChangeImpl implements CallableChange {
	/**
	 * The cached value of the '{@link #getCallable() <em>Callable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallable()
	 * @generated
	 * @ordered
	 */
	protected Callable callable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallableChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ImpactPackage.Literals.CALLABLE_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Callable getCallable() {
		if (callable != null && callable.eIsProxy()) {
			InternalEObject oldCallable = (InternalEObject)callable;
			callable = (Callable)eResolveProxy(oldCallable);
			if (callable != oldCallable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ImpactPackage.CALLABLE_CHANGE__CALLABLE, oldCallable, callable));
			}
		}
		return callable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Callable basicGetCallable() {
		return callable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallable(Callable newCallable) {
		Callable oldCallable = callable;
		callable = newCallable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ImpactPackage.CALLABLE_CHANGE__CALLABLE, oldCallable, callable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ImpactPackage.CALLABLE_CHANGE__CALLABLE:
				if (resolve) return getCallable();
				return basicGetCallable();
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
			case ImpactPackage.CALLABLE_CHANGE__CALLABLE:
				setCallable((Callable)newValue);
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
			case ImpactPackage.CALLABLE_CHANGE__CALLABLE:
				setCallable((Callable)null);
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
			case ImpactPackage.CALLABLE_CHANGE__CALLABLE:
				return callable != null;
		}
		return super.eIsSet(featureID);
	}

} //CallableChangeImpl
