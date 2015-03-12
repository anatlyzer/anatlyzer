/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.ConstrainedType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constrained Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.impl.ConstrainedTypeImpl#getRootType <em>Root Type</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.ConstrainedTypeImpl#getIsType <em>Is Type</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.ConstrainedTypeImpl#getIsNotType <em>Is Not Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstrainedTypeImpl extends TypeImpl implements ConstrainedType {
	/**
	 * The cached value of the '{@link #getRootType() <em>Root Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootType()
	 * @generated
	 * @ordered
	 */
	protected Type rootType;

	/**
	 * The cached value of the '{@link #getIsType() <em>Is Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsType()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> isType;

	/**
	 * The cached value of the '{@link #getIsNotType() <em>Is Not Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsNotType()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> isNotType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstrainedTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.CONSTRAINED_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getRootType() {
		if (rootType != null && rootType.eIsProxy()) {
			InternalEObject oldRootType = (InternalEObject)rootType;
			rootType = (Type)eResolveProxy(oldRootType);
			if (rootType != oldRootType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.CONSTRAINED_TYPE__ROOT_TYPE, oldRootType, rootType));
			}
		}
		return rootType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetRootType() {
		return rootType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRootType(Type newRootType) {
		Type oldRootType = rootType;
		rootType = newRootType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.CONSTRAINED_TYPE__ROOT_TYPE, oldRootType, rootType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getIsType() {
		if (isType == null) {
			isType = new EObjectResolvingEList<Type>(Type.class, this, TypesPackage.CONSTRAINED_TYPE__IS_TYPE);
		}
		return isType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getIsNotType() {
		if (isNotType == null) {
			isNotType = new EObjectResolvingEList<Type>(Type.class, this, TypesPackage.CONSTRAINED_TYPE__IS_NOT_TYPE);
		}
		return isNotType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.CONSTRAINED_TYPE__ROOT_TYPE:
				if (resolve) return getRootType();
				return basicGetRootType();
			case TypesPackage.CONSTRAINED_TYPE__IS_TYPE:
				return getIsType();
			case TypesPackage.CONSTRAINED_TYPE__IS_NOT_TYPE:
				return getIsNotType();
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
			case TypesPackage.CONSTRAINED_TYPE__ROOT_TYPE:
				setRootType((Type)newValue);
				return;
			case TypesPackage.CONSTRAINED_TYPE__IS_TYPE:
				getIsType().clear();
				getIsType().addAll((Collection<? extends Type>)newValue);
				return;
			case TypesPackage.CONSTRAINED_TYPE__IS_NOT_TYPE:
				getIsNotType().clear();
				getIsNotType().addAll((Collection<? extends Type>)newValue);
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
			case TypesPackage.CONSTRAINED_TYPE__ROOT_TYPE:
				setRootType((Type)null);
				return;
			case TypesPackage.CONSTRAINED_TYPE__IS_TYPE:
				getIsType().clear();
				return;
			case TypesPackage.CONSTRAINED_TYPE__IS_NOT_TYPE:
				getIsNotType().clear();
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
			case TypesPackage.CONSTRAINED_TYPE__ROOT_TYPE:
				return rootType != null;
			case TypesPackage.CONSTRAINED_TYPE__IS_TYPE:
				return isType != null && !isType.isEmpty();
			case TypesPackage.CONSTRAINED_TYPE__IS_NOT_TYPE:
				return isNotType != null && !isNotType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConstrainedTypeImpl
