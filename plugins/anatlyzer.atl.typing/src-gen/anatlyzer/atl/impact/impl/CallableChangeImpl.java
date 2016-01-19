/**
 */
package anatlyzer.atl.impact.impl;

import anatlyzer.atl.impact.CallableChange;
import anatlyzer.atl.impact.ImpactPackage;

import anatlyzer.atlext.ATL.Callable;

import anatlyzer.atlext.OCL.PropertyCallExp;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Callable Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.impact.impl.CallableChangeImpl#getCallable <em>Callable</em>}</li>
 *   <li>{@link anatlyzer.atl.impact.impl.CallableChangeImpl#getInvalidated <em>Invalidated</em>}</li>
 * </ul>
 * </p>
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
	 * The cached value of the '{@link #getInvalidated() <em>Invalidated</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvalidated()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyCallExp> invalidated;

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
	public EList<PropertyCallExp> getInvalidated() {
		if (invalidated == null) {
			invalidated = new EObjectResolvingEList<PropertyCallExp>(PropertyCallExp.class, this, ImpactPackage.CALLABLE_CHANGE__INVALIDATED);
		}
		return invalidated;
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
			case ImpactPackage.CALLABLE_CHANGE__INVALIDATED:
				return getInvalidated();
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
			case ImpactPackage.CALLABLE_CHANGE__CALLABLE:
				setCallable((Callable)newValue);
				return;
			case ImpactPackage.CALLABLE_CHANGE__INVALIDATED:
				getInvalidated().clear();
				getInvalidated().addAll((Collection<? extends PropertyCallExp>)newValue);
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
			case ImpactPackage.CALLABLE_CHANGE__INVALIDATED:
				getInvalidated().clear();
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
			case ImpactPackage.CALLABLE_CHANGE__INVALIDATED:
				return invalidated != null && !invalidated.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CallableChangeImpl
