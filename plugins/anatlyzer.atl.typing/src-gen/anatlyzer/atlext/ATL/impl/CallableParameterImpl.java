/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.OCL.VariableDeclaration;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Callable Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CallableParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CallableParameterImpl#getStaticType <em>Static Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.CallableParameterImpl#getParamDeclaration <em>Param Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallableParameterImpl extends MinimalEObjectImpl.Container implements CallableParameter {
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
	 * The cached value of the '{@link #getStaticType() <em>Static Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticType()
	 * @generated
	 * @ordered
	 */
	protected Type staticType;

	/**
	 * The cached value of the '{@link #getParamDeclaration() <em>Param Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParamDeclaration()
	 * @generated
	 * @ordered
	 */
	protected VariableDeclaration paramDeclaration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallableParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.CALLABLE_PARAMETER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.CALLABLE_PARAMETER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getStaticType() {
		if (staticType != null && staticType.eIsProxy()) {
			InternalEObject oldStaticType = (InternalEObject)staticType;
			staticType = (Type)eResolveProxy(oldStaticType);
			if (staticType != oldStaticType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.CALLABLE_PARAMETER__STATIC_TYPE, oldStaticType, staticType));
			}
		}
		return staticType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetStaticType() {
		return staticType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticType(Type newStaticType) {
		Type oldStaticType = staticType;
		staticType = newStaticType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.CALLABLE_PARAMETER__STATIC_TYPE, oldStaticType, staticType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration getParamDeclaration() {
		if (paramDeclaration != null && paramDeclaration.eIsProxy()) {
			InternalEObject oldParamDeclaration = (InternalEObject)paramDeclaration;
			paramDeclaration = (VariableDeclaration)eResolveProxy(oldParamDeclaration);
			if (paramDeclaration != oldParamDeclaration) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.CALLABLE_PARAMETER__PARAM_DECLARATION, oldParamDeclaration, paramDeclaration));
			}
		}
		return paramDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration basicGetParamDeclaration() {
		return paramDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParamDeclaration(VariableDeclaration newParamDeclaration) {
		VariableDeclaration oldParamDeclaration = paramDeclaration;
		paramDeclaration = newParamDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.CALLABLE_PARAMETER__PARAM_DECLARATION, oldParamDeclaration, paramDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ATLPackage.CALLABLE_PARAMETER__NAME:
				return getName();
			case ATLPackage.CALLABLE_PARAMETER__STATIC_TYPE:
				if (resolve) return getStaticType();
				return basicGetStaticType();
			case ATLPackage.CALLABLE_PARAMETER__PARAM_DECLARATION:
				if (resolve) return getParamDeclaration();
				return basicGetParamDeclaration();
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
			case ATLPackage.CALLABLE_PARAMETER__NAME:
				setName((String)newValue);
				return;
			case ATLPackage.CALLABLE_PARAMETER__STATIC_TYPE:
				setStaticType((Type)newValue);
				return;
			case ATLPackage.CALLABLE_PARAMETER__PARAM_DECLARATION:
				setParamDeclaration((VariableDeclaration)newValue);
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
			case ATLPackage.CALLABLE_PARAMETER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ATLPackage.CALLABLE_PARAMETER__STATIC_TYPE:
				setStaticType((Type)null);
				return;
			case ATLPackage.CALLABLE_PARAMETER__PARAM_DECLARATION:
				setParamDeclaration((VariableDeclaration)null);
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
			case ATLPackage.CALLABLE_PARAMETER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ATLPackage.CALLABLE_PARAMETER__STATIC_TYPE:
				return staticType != null;
			case ATLPackage.CALLABLE_PARAMETER__PARAM_DECLARATION:
				return paramDeclaration != null;
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
		result.append(')');
		return result.toString();
	}

} //CallableParameterImpl
