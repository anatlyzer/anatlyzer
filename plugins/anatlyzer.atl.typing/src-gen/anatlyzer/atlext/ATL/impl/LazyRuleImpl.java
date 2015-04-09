/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.LazyRule;

import anatlyzer.atlext.ATL.ModuleCallable;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.PropertyCallExp;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lazy Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LazyRuleImpl#getCalledBy <em>Called By</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LazyRuleImpl#getCallableParameters <em>Callable Parameters</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LazyRuleImpl#isIsUnique <em>Is Unique</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LazyRuleImpl extends RuleWithPatternImpl implements LazyRule {
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
	 * The default value of the '{@link #isIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_UNIQUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean isUnique = IS_UNIQUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LazyRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.LAZY_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyCallExp> getCalledBy() {
		if (calledBy == null) {
			calledBy = new EObjectWithInverseResolvingEList<PropertyCallExp>(PropertyCallExp.class, this, ATLPackage.LAZY_RULE__CALLED_BY, OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER);
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
			callableParameters = new EObjectContainmentEList<CallableParameter>(CallableParameter.class, this, ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS);
		}
		return callableParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsUnique() {
		return isUnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsUnique(boolean newIsUnique) {
		boolean oldIsUnique = isUnique;
		isUnique = newIsUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.LAZY_RULE__IS_UNIQUE, oldIsUnique, isUnique));
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
			case ATLPackage.LAZY_RULE__CALLED_BY:
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
			case ATLPackage.LAZY_RULE__CALLED_BY:
				return ((InternalEList<?>)getCalledBy()).basicRemove(otherEnd, msgs);
			case ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS:
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
			case ATLPackage.LAZY_RULE__CALLED_BY:
				return getCalledBy();
			case ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS:
				return getCallableParameters();
			case ATLPackage.LAZY_RULE__IS_UNIQUE:
				return isIsUnique();
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
			case ATLPackage.LAZY_RULE__CALLED_BY:
				getCalledBy().clear();
				getCalledBy().addAll((Collection<? extends PropertyCallExp>)newValue);
				return;
			case ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS:
				getCallableParameters().clear();
				getCallableParameters().addAll((Collection<? extends CallableParameter>)newValue);
				return;
			case ATLPackage.LAZY_RULE__IS_UNIQUE:
				setIsUnique((Boolean)newValue);
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
			case ATLPackage.LAZY_RULE__CALLED_BY:
				getCalledBy().clear();
				return;
			case ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS:
				getCallableParameters().clear();
				return;
			case ATLPackage.LAZY_RULE__IS_UNIQUE:
				setIsUnique(IS_UNIQUE_EDEFAULT);
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
			case ATLPackage.LAZY_RULE__CALLED_BY:
				return calledBy != null && !calledBy.isEmpty();
			case ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS:
				return callableParameters != null && !callableParameters.isEmpty();
			case ATLPackage.LAZY_RULE__IS_UNIQUE:
				return isUnique != IS_UNIQUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Callable.class) {
			switch (derivedFeatureID) {
				case ATLPackage.LAZY_RULE__CALLED_BY: return ATLPackage.CALLABLE__CALLED_BY;
				case ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS: return ATLPackage.CALLABLE__CALLABLE_PARAMETERS;
				default: return -1;
			}
		}
		if (baseClass == ModuleCallable.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == StaticRule.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Callable.class) {
			switch (baseFeatureID) {
				case ATLPackage.CALLABLE__CALLED_BY: return ATLPackage.LAZY_RULE__CALLED_BY;
				case ATLPackage.CALLABLE__CALLABLE_PARAMETERS: return ATLPackage.LAZY_RULE__CALLABLE_PARAMETERS;
				default: return -1;
			}
		}
		if (baseClass == ModuleCallable.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == StaticRule.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (isUnique: ");
		result.append(isUnique);
		result.append(')');
		return result.toString();
	}

} //LazyRuleImpl
