/**
 */
package anatlyzer.example.sconstraints.impl;

import anatlyzer.example.sconstraints.NumRefsConstraint;
import anatlyzer.example.sconstraints.SimpleConstraintsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Num Refs Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.example.sconstraints.impl.NumRefsConstraintImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.example.sconstraints.impl.NumRefsConstraintImpl#getRefName <em>Ref Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NumRefsConstraintImpl extends NumConstraintImpl implements NumRefsConstraint {
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
	 * The default value of the '{@link #getRefName() <em>Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRefName() <em>Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefName()
	 * @generated
	 * @ordered
	 */
	protected String refName = REF_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NumRefsConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleConstraintsPackage.Literals.NUM_REFS_CONSTRAINT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRefName() {
		return refName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefName(String newRefName) {
		String oldRefName = refName;
		refName = newRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__REF_NAME, oldRefName, refName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__CLASS_NAME:
				return getClassName();
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__REF_NAME:
				return getRefName();
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
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__REF_NAME:
				setRefName((String)newValue);
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
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__REF_NAME:
				setRefName(REF_NAME_EDEFAULT);
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
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT__REF_NAME:
				return REF_NAME_EDEFAULT == null ? refName != null : !REF_NAME_EDEFAULT.equals(refName);
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
		result.append(" (className: ");
		result.append(className);
		result.append(", refName: ");
		result.append(refName);
		result.append(')');
		return result.toString();
	}

} //NumRefsConstraintImpl
