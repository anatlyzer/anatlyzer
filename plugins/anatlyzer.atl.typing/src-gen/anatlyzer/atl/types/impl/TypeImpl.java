/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.impl.TypeImpl#isMultivalued <em>Multivalued</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.TypeImpl#getMetamodelRef <em>Metamodel Ref</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.TypeImpl#getNoCastedType <em>No Casted Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TypeImpl extends MinimalEObjectImpl.Container implements Type {
	/**
	 * The default value of the '{@link #isMultivalued() <em>Multivalued</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultivalued()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIVALUED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultivalued() <em>Multivalued</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultivalued()
	 * @generated
	 * @ordered
	 */
	protected boolean multivalued = MULTIVALUED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMetamodelRef() <em>Metamodel Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodelRef()
	 * @generated
	 * @ordered
	 */
	protected static final Object METAMODEL_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetamodelRef() <em>Metamodel Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodelRef()
	 * @generated
	 * @ordered
	 */
	protected Object metamodelRef = METAMODEL_REF_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNoCastedType() <em>No Casted Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNoCastedType()
	 * @generated
	 * @ordered
	 */
	protected Type noCastedType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultivalued() {
		return multivalued;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultivalued(boolean newMultivalued) {
		boolean oldMultivalued = multivalued;
		multivalued = newMultivalued;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TYPE__MULTIVALUED, oldMultivalued, multivalued));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getMetamodelRef() {
		return metamodelRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetamodelRef(Object newMetamodelRef) {
		Object oldMetamodelRef = metamodelRef;
		metamodelRef = newMetamodelRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TYPE__METAMODEL_REF, oldMetamodelRef, metamodelRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getNoCastedType() {
		if (noCastedType != null && noCastedType.eIsProxy()) {
			InternalEObject oldNoCastedType = (InternalEObject)noCastedType;
			noCastedType = (Type)eResolveProxy(oldNoCastedType);
			if (noCastedType != oldNoCastedType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.TYPE__NO_CASTED_TYPE, oldNoCastedType, noCastedType));
			}
		}
		return noCastedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetNoCastedType() {
		return noCastedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNoCastedType(Type newNoCastedType) {
		Type oldNoCastedType = noCastedType;
		noCastedType = newNoCastedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TYPE__NO_CASTED_TYPE, oldNoCastedType, noCastedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.TYPE__MULTIVALUED:
				return isMultivalued();
			case TypesPackage.TYPE__METAMODEL_REF:
				return getMetamodelRef();
			case TypesPackage.TYPE__NO_CASTED_TYPE:
				if (resolve) return getNoCastedType();
				return basicGetNoCastedType();
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
			case TypesPackage.TYPE__MULTIVALUED:
				setMultivalued((Boolean)newValue);
				return;
			case TypesPackage.TYPE__METAMODEL_REF:
				setMetamodelRef(newValue);
				return;
			case TypesPackage.TYPE__NO_CASTED_TYPE:
				setNoCastedType((Type)newValue);
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
			case TypesPackage.TYPE__MULTIVALUED:
				setMultivalued(MULTIVALUED_EDEFAULT);
				return;
			case TypesPackage.TYPE__METAMODEL_REF:
				setMetamodelRef(METAMODEL_REF_EDEFAULT);
				return;
			case TypesPackage.TYPE__NO_CASTED_TYPE:
				setNoCastedType((Type)null);
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
			case TypesPackage.TYPE__MULTIVALUED:
				return multivalued != MULTIVALUED_EDEFAULT;
			case TypesPackage.TYPE__METAMODEL_REF:
				return METAMODEL_REF_EDEFAULT == null ? metamodelRef != null : !METAMODEL_REF_EDEFAULT.equals(metamodelRef);
			case TypesPackage.TYPE__NO_CASTED_TYPE:
				return noCastedType != null;
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
		result.append(" (multivalued: ");
		result.append(multivalued);
		result.append(", metamodelRef: ");
		result.append(metamodelRef);
		result.append(')');
		return result.toString();
	}

} //TypeImpl
