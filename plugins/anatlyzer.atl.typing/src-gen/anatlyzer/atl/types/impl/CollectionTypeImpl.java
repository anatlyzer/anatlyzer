/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.impl.CollectionTypeImpl#getContainedType <em>Contained Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CollectionTypeImpl extends TypeImpl implements CollectionType {
	/**
	 * The cached value of the '{@link #getContainedType() <em>Contained Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedType()
	 * @generated
	 * @ordered
	 */
	protected Type containedType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.COLLECTION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getContainedType() {
		if (containedType != null && containedType.eIsProxy()) {
			InternalEObject oldContainedType = (InternalEObject)containedType;
			containedType = (Type)eResolveProxy(oldContainedType);
			if (containedType != oldContainedType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.COLLECTION_TYPE__CONTAINED_TYPE, oldContainedType, containedType));
			}
		}
		return containedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetContainedType() {
		return containedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainedType(Type newContainedType) {
		Type oldContainedType = containedType;
		containedType = newContainedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.COLLECTION_TYPE__CONTAINED_TYPE, oldContainedType, containedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.COLLECTION_TYPE__CONTAINED_TYPE:
				if (resolve) return getContainedType();
				return basicGetContainedType();
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
			case TypesPackage.COLLECTION_TYPE__CONTAINED_TYPE:
				setContainedType((Type)newValue);
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
			case TypesPackage.COLLECTION_TYPE__CONTAINED_TYPE:
				setContainedType((Type)null);
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
			case TypesPackage.COLLECTION_TYPE__CONTAINED_TYPE:
				return containedType != null;
		}
		return super.eIsSet(featureID);
	}

} //CollectionTypeImpl
