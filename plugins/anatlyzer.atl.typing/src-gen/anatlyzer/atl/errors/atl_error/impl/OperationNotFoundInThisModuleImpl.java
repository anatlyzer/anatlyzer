/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Not Found In This Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationNotFoundInThisModuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationNotFoundInThisModuleImpl#isMaybeRule <em>Maybe Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationNotFoundInThisModuleImpl extends NavigationProblemImpl implements OperationNotFoundInThisModule {
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
	 * The default value of the '{@link #isMaybeRule() <em>Maybe Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMaybeRule()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAYBE_RULE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMaybeRule() <em>Maybe Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMaybeRule()
	 * @generated
	 * @ordered
	 */
	protected boolean maybeRule = MAYBE_RULE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationNotFoundInThisModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.OPERATION_NOT_FOUND_IN_THIS_MODULE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMaybeRule() {
		return maybeRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaybeRule(boolean newMaybeRule) {
		boolean oldMaybeRule = maybeRule;
		maybeRule = newMaybeRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__MAYBE_RULE, oldMaybeRule, maybeRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__NAME:
				return getName();
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__MAYBE_RULE:
				return isMaybeRule();
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
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__NAME:
				setName((String)newValue);
				return;
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__MAYBE_RULE:
				setMaybeRule((Boolean)newValue);
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
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__MAYBE_RULE:
				setMaybeRule(MAYBE_RULE_EDEFAULT);
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
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AtlErrorPackage.OPERATION_NOT_FOUND_IN_THIS_MODULE__MAYBE_RULE:
				return maybeRule != MAYBE_RULE_EDEFAULT;
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
		result.append(", maybeRule: ");
		result.append(maybeRule);
		result.append(')');
		return result.toString();
	}

} //OperationNotFoundInThisModuleImpl
