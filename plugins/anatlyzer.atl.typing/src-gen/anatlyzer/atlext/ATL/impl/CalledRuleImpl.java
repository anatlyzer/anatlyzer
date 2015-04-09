/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.CalledRule;

import anatlyzer.atlext.OCL.Parameter;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Called Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CalledRuleImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CalledRuleImpl#isIsEntrypoint <em>Is Entrypoint</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CalledRuleImpl#isIsEndpoint <em>Is Endpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CalledRuleImpl extends StaticRuleImpl implements CalledRule {
	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameters;

	/**
	 * The default value of the '{@link #isIsEntrypoint() <em>Is Entrypoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEntrypoint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ENTRYPOINT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEntrypoint() <em>Is Entrypoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEntrypoint()
	 * @generated
	 * @ordered
	 */
	protected boolean isEntrypoint = IS_ENTRYPOINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsEndpoint() <em>Is Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEndpoint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ENDPOINT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEndpoint() <em>Is Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEndpoint()
	 * @generated
	 * @ordered
	 */
	protected boolean isEndpoint = IS_ENDPOINT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CalledRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.CALLED_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, ATLPackage.CALLED_RULE__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEntrypoint() {
		return isEntrypoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEntrypoint(boolean newIsEntrypoint) {
		boolean oldIsEntrypoint = isEntrypoint;
		isEntrypoint = newIsEntrypoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.CALLED_RULE__IS_ENTRYPOINT, oldIsEntrypoint, isEntrypoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEndpoint() {
		return isEndpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEndpoint(boolean newIsEndpoint) {
		boolean oldIsEndpoint = isEndpoint;
		isEndpoint = newIsEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.CALLED_RULE__IS_ENDPOINT, oldIsEndpoint, isEndpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.CALLED_RULE__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case ATLPackage.CALLED_RULE__PARAMETERS:
				return getParameters();
			case ATLPackage.CALLED_RULE__IS_ENTRYPOINT:
				return isIsEntrypoint();
			case ATLPackage.CALLED_RULE__IS_ENDPOINT:
				return isIsEndpoint();
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
			case ATLPackage.CALLED_RULE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case ATLPackage.CALLED_RULE__IS_ENTRYPOINT:
				setIsEntrypoint((Boolean)newValue);
				return;
			case ATLPackage.CALLED_RULE__IS_ENDPOINT:
				setIsEndpoint((Boolean)newValue);
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
			case ATLPackage.CALLED_RULE__PARAMETERS:
				getParameters().clear();
				return;
			case ATLPackage.CALLED_RULE__IS_ENTRYPOINT:
				setIsEntrypoint(IS_ENTRYPOINT_EDEFAULT);
				return;
			case ATLPackage.CALLED_RULE__IS_ENDPOINT:
				setIsEndpoint(IS_ENDPOINT_EDEFAULT);
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
			case ATLPackage.CALLED_RULE__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case ATLPackage.CALLED_RULE__IS_ENTRYPOINT:
				return isEntrypoint != IS_ENTRYPOINT_EDEFAULT;
			case ATLPackage.CALLED_RULE__IS_ENDPOINT:
				return isEndpoint != IS_ENDPOINT_EDEFAULT;
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
		result.append(" (isEntrypoint: ");
		result.append(isEntrypoint);
		result.append(", isEndpoint: ");
		result.append(isEndpoint);
		result.append(')');
		return result.toString();
	}

} //CalledRuleImpl
