/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.ModelElement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ModelElementImpl#getKlass <em>Klass</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ModelElementImpl#getMetamodelName <em>Metamodel Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelElementImpl extends MinimalEObjectImpl.Container implements ModelElement {
	/**
	 * The cached value of the '{@link #getKlass() <em>Klass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKlass()
	 * @generated
	 * @ordered
	 */
	protected EClass klass;

	/**
	 * The default value of the '{@link #getMetamodelName() <em>Metamodel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodelName()
	 * @generated
	 * @ordered
	 */
	protected static final String METAMODEL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetamodelName() <em>Metamodel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodelName()
	 * @generated
	 * @ordered
	 */
	protected String metamodelName = METAMODEL_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.MODEL_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKlass() {
		if (klass != null && klass.eIsProxy()) {
			InternalEObject oldKlass = (InternalEObject)klass;
			klass = (EClass)eResolveProxy(oldKlass);
			if (klass != oldKlass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.MODEL_ELEMENT__KLASS, oldKlass, klass));
			}
		}
		return klass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetKlass() {
		return klass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKlass(EClass newKlass) {
		EClass oldKlass = klass;
		klass = newKlass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.MODEL_ELEMENT__KLASS, oldKlass, klass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMetamodelName() {
		return metamodelName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetamodelName(String newMetamodelName) {
		String oldMetamodelName = metamodelName;
		metamodelName = newMetamodelName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.MODEL_ELEMENT__METAMODEL_NAME, oldMetamodelName, metamodelName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.MODEL_ELEMENT__KLASS:
				if (resolve) return getKlass();
				return basicGetKlass();
			case AtlErrorPackage.MODEL_ELEMENT__METAMODEL_NAME:
				return getMetamodelName();
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
			case AtlErrorPackage.MODEL_ELEMENT__KLASS:
				setKlass((EClass)newValue);
				return;
			case AtlErrorPackage.MODEL_ELEMENT__METAMODEL_NAME:
				setMetamodelName((String)newValue);
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
			case AtlErrorPackage.MODEL_ELEMENT__KLASS:
				setKlass((EClass)null);
				return;
			case AtlErrorPackage.MODEL_ELEMENT__METAMODEL_NAME:
				setMetamodelName(METAMODEL_NAME_EDEFAULT);
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
			case AtlErrorPackage.MODEL_ELEMENT__KLASS:
				return klass != null;
			case AtlErrorPackage.MODEL_ELEMENT__METAMODEL_NAME:
				return METAMODEL_NAME_EDEFAULT == null ? metamodelName != null : !METAMODEL_NAME_EDEFAULT.equals(metamodelName);
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
		result.append(" (metamodelName: ");
		result.append(metamodelName);
		result.append(')');
		return result.toString();
	}

} //ModelElementImpl
