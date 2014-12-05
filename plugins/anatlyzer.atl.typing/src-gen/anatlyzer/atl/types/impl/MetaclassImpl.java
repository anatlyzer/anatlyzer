/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.MetaModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.TypesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metaclass</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.impl.MetaclassImpl#getName <em>Name</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.MetaclassImpl#isExplicitOcurrence <em>Explicit Ocurrence</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.MetaclassImpl#getKlass <em>Klass</em>}</li>
 *   <li>{@link anatlyzer.atl.types.impl.MetaclassImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetaclassImpl extends RefTypeImpl implements Metaclass {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isExplicitOcurrence() <em>Explicit Ocurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExplicitOcurrence()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXPLICIT_OCURRENCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExplicitOcurrence() <em>Explicit Ocurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExplicitOcurrence()
	 * @generated
	 * @ordered
	 */
	protected boolean explicitOcurrence = EXPLICIT_OCURRENCE_EDEFAULT;

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
	 * The cached value of the '{@link #getModel() <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected MetaModel model;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetaclassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.METACLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.METACLASS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExplicitOcurrence() {
		return explicitOcurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExplicitOcurrence(boolean newExplicitOcurrence) {
		boolean oldExplicitOcurrence = explicitOcurrence;
		explicitOcurrence = newExplicitOcurrence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.METACLASS__EXPLICIT_OCURRENCE, oldExplicitOcurrence, explicitOcurrence));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.METACLASS__KLASS, oldKlass, klass));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.METACLASS__KLASS, oldKlass, klass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaModel getModel() {
		if (model != null && model.eIsProxy()) {
			InternalEObject oldModel = (InternalEObject)model;
			model = (MetaModel)eResolveProxy(oldModel);
			if (model != oldModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.METACLASS__MODEL, oldModel, model));
			}
		}
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaModel basicGetModel() {
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(MetaModel newModel) {
		MetaModel oldModel = model;
		model = newModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.METACLASS__MODEL, oldModel, model));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.METACLASS__NAME:
				return getName();
			case TypesPackage.METACLASS__EXPLICIT_OCURRENCE:
				return isExplicitOcurrence();
			case TypesPackage.METACLASS__KLASS:
				if (resolve) return getKlass();
				return basicGetKlass();
			case TypesPackage.METACLASS__MODEL:
				if (resolve) return getModel();
				return basicGetModel();
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
			case TypesPackage.METACLASS__NAME:
				setName((String)newValue);
				return;
			case TypesPackage.METACLASS__EXPLICIT_OCURRENCE:
				setExplicitOcurrence((Boolean)newValue);
				return;
			case TypesPackage.METACLASS__KLASS:
				setKlass((EClass)newValue);
				return;
			case TypesPackage.METACLASS__MODEL:
				setModel((MetaModel)newValue);
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
			case TypesPackage.METACLASS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TypesPackage.METACLASS__EXPLICIT_OCURRENCE:
				setExplicitOcurrence(EXPLICIT_OCURRENCE_EDEFAULT);
				return;
			case TypesPackage.METACLASS__KLASS:
				setKlass((EClass)null);
				return;
			case TypesPackage.METACLASS__MODEL:
				setModel((MetaModel)null);
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
			case TypesPackage.METACLASS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TypesPackage.METACLASS__EXPLICIT_OCURRENCE:
				return explicitOcurrence != EXPLICIT_OCURRENCE_EDEFAULT;
			case TypesPackage.METACLASS__KLASS:
				return klass != null;
			case TypesPackage.METACLASS__MODEL:
				return model != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", explicitOcurrence: ");
		result.append(explicitOcurrence);
		result.append(')');
		return result.toString();
	}

} //MetaclassImpl
