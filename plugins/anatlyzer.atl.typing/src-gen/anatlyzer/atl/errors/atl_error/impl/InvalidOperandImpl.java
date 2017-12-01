/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.InvalidOperand;

import anatlyzer.atl.types.Type;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Invalid Operand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperandImpl#getOperatorSymbol <em>Operator Symbol</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.InvalidOperandImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InvalidOperandImpl extends NavigationProblemImpl implements InvalidOperand {
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
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

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
	public Type getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Type)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.INVALID_OPERAND__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		Type oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.INVALID_OPERAND__TYPE, oldType, type));
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
			case AtlErrorPackage.INVALID_OPERAND__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case AtlErrorPackage.INVALID_OPERAND__TYPE:
				setType((Type)newValue);
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
			case AtlErrorPackage.INVALID_OPERAND__TYPE:
				setType((Type)null);
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
			case AtlErrorPackage.INVALID_OPERAND__TYPE:
				return type != null;
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
