/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;

import anatlyzer.atl.types.Type;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Not Found</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl#getType <em>Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.FeatureNotFoundImpl#getMetamodelName <em>Metamodel Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureNotFoundImpl extends NavigationProblemImpl implements FeatureNotFound {
	/**
	 * The default value of the '{@link #getFeatureName() <em>Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureName()
	 * @generated
	 * @ordered
	 */
	protected static final String FEATURE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeatureName() <em>Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureName()
	 * @generated
	 * @ordered
	 */
	protected String featureName = FEATURE_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

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
	protected FeatureNotFoundImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.FEATURE_NOT_FOUND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFeatureName() {
		return featureName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeatureName(String newFeatureName) {
		String oldFeatureName = featureName;
		featureName = newFeatureName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.FEATURE_NOT_FOUND__FEATURE_NAME, oldFeatureName, featureName));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.FEATURE_NOT_FOUND__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.FEATURE_NOT_FOUND__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.FEATURE_NOT_FOUND__CLASS_NAME, oldClassName, className));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.FEATURE_NOT_FOUND__METAMODEL_NAME, oldMetamodelName, metamodelName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.FEATURE_NOT_FOUND__FEATURE_NAME:
				return getFeatureName();
			case AtlErrorPackage.FEATURE_NOT_FOUND__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case AtlErrorPackage.FEATURE_NOT_FOUND__CLASS_NAME:
				return getClassName();
			case AtlErrorPackage.FEATURE_NOT_FOUND__METAMODEL_NAME:
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
			case AtlErrorPackage.FEATURE_NOT_FOUND__FEATURE_NAME:
				setFeatureName((String)newValue);
				return;
			case AtlErrorPackage.FEATURE_NOT_FOUND__TYPE:
				setType((Type)newValue);
				return;
			case AtlErrorPackage.FEATURE_NOT_FOUND__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case AtlErrorPackage.FEATURE_NOT_FOUND__METAMODEL_NAME:
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
			case AtlErrorPackage.FEATURE_NOT_FOUND__FEATURE_NAME:
				setFeatureName(FEATURE_NAME_EDEFAULT);
				return;
			case AtlErrorPackage.FEATURE_NOT_FOUND__TYPE:
				setType((Type)null);
				return;
			case AtlErrorPackage.FEATURE_NOT_FOUND__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case AtlErrorPackage.FEATURE_NOT_FOUND__METAMODEL_NAME:
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
			case AtlErrorPackage.FEATURE_NOT_FOUND__FEATURE_NAME:
				return FEATURE_NAME_EDEFAULT == null ? featureName != null : !FEATURE_NAME_EDEFAULT.equals(featureName);
			case AtlErrorPackage.FEATURE_NOT_FOUND__TYPE:
				return type != null;
			case AtlErrorPackage.FEATURE_NOT_FOUND__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case AtlErrorPackage.FEATURE_NOT_FOUND__METAMODEL_NAME:
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
		result.append(" (featureName: ");
		result.append(featureName);
		result.append(", className: ");
		result.append(className);
		result.append(", metamodelName: ");
		result.append(metamodelName);
		result.append(')');
		return result.toString();
	}

} //FeatureNotFoundImpl
