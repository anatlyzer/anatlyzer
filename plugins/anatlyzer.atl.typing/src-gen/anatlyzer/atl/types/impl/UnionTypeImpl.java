/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesPackage;
import anatlyzer.atl.types.UnionType;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.impl.UnionTypeImpl#getPossibleTypes <em>Possible Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnionTypeImpl extends TypeImpl implements UnionType {
	/**
	 * The cached value of the '{@link #getPossibleTypes() <em>Possible Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPossibleTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> possibleTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.UNION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getPossibleTypes() {
		if (possibleTypes == null) {
			possibleTypes = new EObjectResolvingEList<Type>(Type.class, this, TypesPackage.UNION_TYPE__POSSIBLE_TYPES);
		}
		return possibleTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.UNION_TYPE__POSSIBLE_TYPES:
				return getPossibleTypes();
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
			case TypesPackage.UNION_TYPE__POSSIBLE_TYPES:
				getPossibleTypes().clear();
				getPossibleTypes().addAll((Collection<? extends Type>)newValue);
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
			case TypesPackage.UNION_TYPE__POSSIBLE_TYPES:
				getPossibleTypes().clear();
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
			case TypesPackage.UNION_TYPE__POSSIBLE_TYPES:
				return possibleTypes != null && !possibleTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UnionTypeImpl
