/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Invalid Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.OperationCallInvalidParameterImpl#getBlamedParameterNames <em>Blamed Parameter Names</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationCallInvalidParameterImpl extends OperationCallInvalidImpl implements OperationCallInvalidParameter {
	/**
	 * The cached value of the '{@link #getBlamedParameterNames() <em>Blamed Parameter Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlamedParameterNames()
	 * @generated
	 * @ordered
	 */
	protected EList<String> blamedParameterNames;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallInvalidParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.OPERATION_CALL_INVALID_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getBlamedParameterNames() {
		if (blamedParameterNames == null) {
			blamedParameterNames = new EDataTypeUniqueEList<String>(String.class, this, AtlErrorPackage.OPERATION_CALL_INVALID_PARAMETER__BLAMED_PARAMETER_NAMES);
		}
		return blamedParameterNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.OPERATION_CALL_INVALID_PARAMETER__BLAMED_PARAMETER_NAMES:
				return getBlamedParameterNames();
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
			case AtlErrorPackage.OPERATION_CALL_INVALID_PARAMETER__BLAMED_PARAMETER_NAMES:
				getBlamedParameterNames().clear();
				getBlamedParameterNames().addAll((Collection<? extends String>)newValue);
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
			case AtlErrorPackage.OPERATION_CALL_INVALID_PARAMETER__BLAMED_PARAMETER_NAMES:
				getBlamedParameterNames().clear();
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
			case AtlErrorPackage.OPERATION_CALL_INVALID_PARAMETER__BLAMED_PARAMETER_NAMES:
				return blamedParameterNames != null && !blamedParameterNames.isEmpty();
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
		result.append(" (blamedParameterNames: ");
		result.append(blamedParameterNames);
		result.append(')');
		return result.toString();
	}

} //OperationCallInvalidParameterImpl
