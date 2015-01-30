/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

import anatlyzer.atlext.OCL.ResolveTempResolution;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OperationCallExpImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OperationCallExpImpl#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OperationCallExpImpl#getResolveTempResolvedBy <em>Resolve Temp Resolved By</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationCallExpImpl extends PropertyCallExpImpl implements OperationCallExp {
	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<OclExpression> arguments;

	/**
	 * The default value of the '{@link #getOperationName() <em>Operation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationName()
	 * @generated
	 * @ordered
	 */
	protected static final String OPERATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperationName() <em>Operation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationName()
	 * @generated
	 * @ordered
	 */
	protected String operationName = OPERATION_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResolveTempResolvedBy() <em>Resolve Temp Resolved By</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveTempResolvedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<ResolveTempResolution> resolveTempResolvedBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.OPERATION_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OclExpression> getArguments() {
		if (arguments == null) {
			arguments = new EObjectContainmentWithInverseEList<OclExpression>(OclExpression.class, this, OCLPackage.OPERATION_CALL_EXP__ARGUMENTS, OCLPackage.OCL_EXPRESSION__PARENT_OPERATION);
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperationName(String newOperationName) {
		String oldOperationName = operationName;
		operationName = newOperationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OPERATION_CALL_EXP__OPERATION_NAME, oldOperationName, operationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResolveTempResolution> getResolveTempResolvedBy() {
		if (resolveTempResolvedBy == null) {
			resolveTempResolvedBy = new EObjectContainmentEList<ResolveTempResolution>(ResolveTempResolution.class, this, OCLPackage.OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY);
		}
		return resolveTempResolvedBy;
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
			case OCLPackage.OPERATION_CALL_EXP__ARGUMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getArguments()).basicAdd(otherEnd, msgs);
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
			case OCLPackage.OPERATION_CALL_EXP__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case OCLPackage.OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY:
				return ((InternalEList<?>)getResolveTempResolvedBy()).basicRemove(otherEnd, msgs);
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
			case OCLPackage.OPERATION_CALL_EXP__ARGUMENTS:
				return getArguments();
			case OCLPackage.OPERATION_CALL_EXP__OPERATION_NAME:
				return getOperationName();
			case OCLPackage.OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY:
				return getResolveTempResolvedBy();
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
			case OCLPackage.OPERATION_CALL_EXP__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends OclExpression>)newValue);
				return;
			case OCLPackage.OPERATION_CALL_EXP__OPERATION_NAME:
				setOperationName((String)newValue);
				return;
			case OCLPackage.OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY:
				getResolveTempResolvedBy().clear();
				getResolveTempResolvedBy().addAll((Collection<? extends ResolveTempResolution>)newValue);
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
			case OCLPackage.OPERATION_CALL_EXP__ARGUMENTS:
				getArguments().clear();
				return;
			case OCLPackage.OPERATION_CALL_EXP__OPERATION_NAME:
				setOperationName(OPERATION_NAME_EDEFAULT);
				return;
			case OCLPackage.OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY:
				getResolveTempResolvedBy().clear();
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
			case OCLPackage.OPERATION_CALL_EXP__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
			case OCLPackage.OPERATION_CALL_EXP__OPERATION_NAME:
				return OPERATION_NAME_EDEFAULT == null ? operationName != null : !OPERATION_NAME_EDEFAULT.equals(operationName);
			case OCLPackage.OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY:
				return resolveTempResolvedBy != null && !resolveTempResolvedBy.isEmpty();
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
		result.append(" (operationName: ");
		result.append(operationName);
		result.append(')');
		return result.toString();
	}

} //OperationCallExpImpl
