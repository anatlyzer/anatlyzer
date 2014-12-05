/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Callable;

import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.PropertyCallExp;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Callable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CallableImpl#getCalledBy <em>Called By</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CallableImpl#getCallableParameters <em>Callable Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CallableImpl extends MinimalEObjectImpl.Container implements Callable {
	/**
	 * The cached value of the '{@link #getCalledBy() <em>Called By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledBy()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyCallExp> calledBy;

	/**
	 * The cached value of the '{@link #getCallableParameters() <em>Callable Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<CallableParameter> callableParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.CALLABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyCallExp> getCalledBy() {
		if (calledBy == null) {
			calledBy = new EObjectWithInverseResolvingEList<PropertyCallExp>(PropertyCallExp.class, this, ATLPackage.CALLABLE__CALLED_BY, OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER);
		}
		return calledBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CallableParameter> getCallableParameters() {
		if (callableParameters == null) {
			callableParameters = new EObjectContainmentEList<CallableParameter>(CallableParameter.class, this, ATLPackage.CALLABLE__CALLABLE_PARAMETERS);
		}
		return callableParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.CALLABLE__CALLED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCalledBy()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.CALLABLE__CALLED_BY:
				return ((InternalEList<?>)getCalledBy()).basicRemove(otherEnd, msgs);
			case ATLPackage.CALLABLE__CALLABLE_PARAMETERS:
				return ((InternalEList<?>)getCallableParameters()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ATLPackage.CALLABLE__CALLED_BY:
				return getCalledBy();
			case ATLPackage.CALLABLE__CALLABLE_PARAMETERS:
				return getCallableParameters();
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
			case ATLPackage.CALLABLE__CALLED_BY:
				getCalledBy().clear();
				getCalledBy().addAll((Collection<? extends PropertyCallExp>)newValue);
				return;
			case ATLPackage.CALLABLE__CALLABLE_PARAMETERS:
				getCallableParameters().clear();
				getCallableParameters().addAll((Collection<? extends CallableParameter>)newValue);
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
			case ATLPackage.CALLABLE__CALLED_BY:
				getCalledBy().clear();
				return;
			case ATLPackage.CALLABLE__CALLABLE_PARAMETERS:
				getCallableParameters().clear();
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
			case ATLPackage.CALLABLE__CALLED_BY:
				return calledBy != null && !calledBy.isEmpty();
			case ATLPackage.CALLABLE__CALLABLE_PARAMETERS:
				return callableParameters != null && !callableParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CallableImpl
