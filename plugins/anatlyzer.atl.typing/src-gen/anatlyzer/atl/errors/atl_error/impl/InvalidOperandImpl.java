/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.InvalidOperand;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Invalid Operand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperandImpl#getOperatorSymbol <em>Operator Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InvalidOperandImpl extends InvalidArgumentProblemImpl implements InvalidOperand {
	/**
	 * The default value of the '{@link #getOperatorSymbol() <em>Operator Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperatorSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String OPERATOR_SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperatorSymbol() <em>Operator Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperatorSymbol()
	 * @generated
	 * @ordered
	 */
	protected String operatorSymbol = OPERATOR_SYMBOL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InvalidOperandImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.INVALID_OPERAND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOperatorSymbol() {
		return operatorSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperatorSymbol(String newOperatorSymbol) {
		String oldOperatorSymbol = operatorSymbol;
		operatorSymbol = newOperatorSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.INVALID_OPERAND__OPERATOR_SYMBOL, oldOperatorSymbol, operatorSymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.INVALID_OPERAND__OPERATOR_SYMBOL:
				return getOperatorSymbol();
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
			case AtlErrorPackage.INVALID_OPERAND__OPERATOR_SYMBOL:
				setOperatorSymbol((String)newValue);
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
			case AtlErrorPackage.INVALID_OPERAND__OPERATOR_SYMBOL:
				setOperatorSymbol(OPERATOR_SYMBOL_EDEFAULT);
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
			case AtlErrorPackage.INVALID_OPERAND__OPERATOR_SYMBOL:
				return OPERATOR_SYMBOL_EDEFAULT == null ? operatorSymbol != null : !OPERATOR_SYMBOL_EDEFAULT.equals(operatorSymbol);
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
		result.append(" (operatorSymbol: ");
		result.append(operatorSymbol);
		result.append(')');
		return result.toString();
	}

} //InvalidOperandImpl
