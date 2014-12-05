/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.impl.MapTypeImpl#getKeyType <em>Key Type</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.MapTypeImpl#getValueType <em>Value Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MapTypeImpl extends TypeImpl implements MapType {
	/**
	 * The cached value of the '{@link #getKeyType() <em>Key Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyType()
	 * @generated
	 * @ordered
	 */
	protected Type keyType;

	/**
	 * The cached value of the '{@link #getValueType() <em>Value Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueType()
	 * @generated
	 * @ordered
	 */
	protected Type valueType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.MAP_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getKeyType() {
		if (keyType != null && keyType.eIsProxy()) {
			InternalEObject oldKeyType = (InternalEObject)keyType;
			keyType = (Type)eResolveProxy(oldKeyType);
			if (keyType != oldKeyType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.MAP_TYPE__KEY_TYPE, oldKeyType, keyType));
			}
		}
		return keyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetKeyType() {
		return keyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyType(Type newKeyType) {
		Type oldKeyType = keyType;
		keyType = newKeyType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.MAP_TYPE__KEY_TYPE, oldKeyType, keyType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getValueType() {
		if (valueType != null && valueType.eIsProxy()) {
			InternalEObject oldValueType = (InternalEObject)valueType;
			valueType = (Type)eResolveProxy(oldValueType);
			if (valueType != oldValueType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.MAP_TYPE__VALUE_TYPE, oldValueType, valueType));
			}
		}
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetValueType() {
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueType(Type newValueType) {
		Type oldValueType = valueType;
		valueType = newValueType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.MAP_TYPE__VALUE_TYPE, oldValueType, valueType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.MAP_TYPE__KEY_TYPE:
				if (resolve) return getKeyType();
				return basicGetKeyType();
			case TypesPackage.MAP_TYPE__VALUE_TYPE:
				if (resolve) return getValueType();
				return basicGetValueType();
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
			case TypesPackage.MAP_TYPE__KEY_TYPE:
				setKeyType((Type)newValue);
				return;
			case TypesPackage.MAP_TYPE__VALUE_TYPE:
				setValueType((Type)newValue);
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
			case TypesPackage.MAP_TYPE__KEY_TYPE:
				setKeyType((Type)null);
				return;
			case TypesPackage.MAP_TYPE__VALUE_TYPE:
				setValueType((Type)null);
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
			case TypesPackage.MAP_TYPE__KEY_TYPE:
				return keyType != null;
			case TypesPackage.MAP_TYPE__VALUE_TYPE:
				return valueType != null;
		}
		return super.eIsSet(featureID);
	}

} //MapTypeImpl
