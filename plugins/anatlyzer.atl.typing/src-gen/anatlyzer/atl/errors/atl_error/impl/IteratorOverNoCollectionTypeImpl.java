/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterator Over No Collection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.IteratorOverNoCollectionTypeImpl#getIteratorName <em>Iterator Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IteratorOverNoCollectionTypeImpl extends LocalProblemImpl implements IteratorOverNoCollectionType {
	/**
	 * The default value of the '{@link #getIteratorName() <em>Iterator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIteratorName()
	 * @generated
	 * @ordered
	 */
	protected static final String ITERATOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIteratorName() <em>Iterator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIteratorName()
	 * @generated
	 * @ordered
	 */
	protected String iteratorName = ITERATOR_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IteratorOverNoCollectionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.ITERATOR_OVER_NO_COLLECTION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIteratorName() {
		return iteratorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIteratorName(String newIteratorName) {
		String oldIteratorName = iteratorName;
		iteratorName = newIteratorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE__ITERATOR_NAME, oldIteratorName, iteratorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE__ITERATOR_NAME:
				return getIteratorName();
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
			case AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE__ITERATOR_NAME:
				setIteratorName((String)newValue);
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
			case AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE__ITERATOR_NAME:
				setIteratorName(ITERATOR_NAME_EDEFAULT);
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
			case AtlErrorPackage.ITERATOR_OVER_NO_COLLECTION_TYPE__ITERATOR_NAME:
				return ITERATOR_NAME_EDEFAULT == null ? iteratorName != null : !ITERATOR_NAME_EDEFAULT.equals(iteratorName);
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
		result.append(" (iteratorName: ");
		result.append(iteratorName);
		result.append(')');
		return result.toString();
	}

} //IteratorOverNoCollectionTypeImpl
