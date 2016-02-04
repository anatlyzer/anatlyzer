/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.OperationCallInvalid;

import anatlyzer.atl.types.Type;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Invalid</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl#getType <em>Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl#getMetamodelName <em>Metamodel Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidImpl#getActualParameters <em>Actual Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class OperationCallInvalidImpl extends NavigationProblemImpl implements OperationCallInvalid {
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
	 * The cached value of the '{@link #getFormalParameters() <em>Formal Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormalParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> formalParameters;

	/**
	 * The cached value of the '{@link #getActualParameters() <em>Actual Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> actualParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallInvalidImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.OPERATION_CALL_INVALID;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.OPERATION_CALL_INVALID__OPERATION_NAME, oldOperationName, operationName));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.OPERATION_CALL_INVALID__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.OPERATION_CALL_INVALID__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.OPERATION_CALL_INVALID__CLASS_NAME, oldClassName, className));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.OPERATION_CALL_INVALID__METAMODEL_NAME, oldMetamodelName, metamodelName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getFormalParameters() {
		if (formalParameters == null) {
			formalParameters = new EObjectResolvingEList<Type>(Type.class, this, AtlErrorPackage.OPERATION_CALL_INVALID__FORMAL_PARAMETERS);
		}
		return formalParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getActualParameters() {
		if (actualParameters == null) {
			actualParameters = new EObjectResolvingEList<Type>(Type.class, this, AtlErrorPackage.OPERATION_CALL_INVALID__ACTUAL_PARAMETERS);
		}
		return actualParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.OPERATION_CALL_INVALID__OPERATION_NAME:
				return getOperationName();
			case AtlErrorPackage.OPERATION_CALL_INVALID__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case AtlErrorPackage.OPERATION_CALL_INVALID__CLASS_NAME:
				return getClassName();
			case AtlErrorPackage.OPERATION_CALL_INVALID__METAMODEL_NAME:
				return getMetamodelName();
			case AtlErrorPackage.OPERATION_CALL_INVALID__FORMAL_PARAMETERS:
				return getFormalParameters();
			case AtlErrorPackage.OPERATION_CALL_INVALID__ACTUAL_PARAMETERS:
				return getActualParameters();
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
			case AtlErrorPackage.OPERATION_CALL_INVALID__OPERATION_NAME:
				setOperationName((String)newValue);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__TYPE:
				setType((Type)newValue);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__METAMODEL_NAME:
				setMetamodelName((String)newValue);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__FORMAL_PARAMETERS:
				getFormalParameters().clear();
				getFormalParameters().addAll((Collection<? extends Type>)newValue);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__ACTUAL_PARAMETERS:
				getActualParameters().clear();
				getActualParameters().addAll((Collection<? extends Type>)newValue);
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
			case AtlErrorPackage.OPERATION_CALL_INVALID__OPERATION_NAME:
				setOperationName(OPERATION_NAME_EDEFAULT);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__TYPE:
				setType((Type)null);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__METAMODEL_NAME:
				setMetamodelName(METAMODEL_NAME_EDEFAULT);
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__FORMAL_PARAMETERS:
				getFormalParameters().clear();
				return;
			case AtlErrorPackage.OPERATION_CALL_INVALID__ACTUAL_PARAMETERS:
				getActualParameters().clear();
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
			case AtlErrorPackage.OPERATION_CALL_INVALID__OPERATION_NAME:
				return OPERATION_NAME_EDEFAULT == null ? operationName != null : !OPERATION_NAME_EDEFAULT.equals(operationName);
			case AtlErrorPackage.OPERATION_CALL_INVALID__TYPE:
				return type != null;
			case AtlErrorPackage.OPERATION_CALL_INVALID__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case AtlErrorPackage.OPERATION_CALL_INVALID__METAMODEL_NAME:
				return METAMODEL_NAME_EDEFAULT == null ? metamodelName != null : !METAMODEL_NAME_EDEFAULT.equals(metamodelName);
			case AtlErrorPackage.OPERATION_CALL_INVALID__FORMAL_PARAMETERS:
				return formalParameters != null && !formalParameters.isEmpty();
			case AtlErrorPackage.OPERATION_CALL_INVALID__ACTUAL_PARAMETERS:
				return actualParameters != null && !actualParameters.isEmpty();
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
		result.append(", className: ");
		result.append(className);
		result.append(", metamodelName: ");
		result.append(metamodelName);
		result.append(')');
		return result.toString();
	}

} //OperationCallInvalidImpl
