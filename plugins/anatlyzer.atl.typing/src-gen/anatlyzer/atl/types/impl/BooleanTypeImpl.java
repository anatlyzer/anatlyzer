/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.TypesPackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.impl.BooleanTypeImpl#getKindOfTypes <em>Kind Of Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanTypeImpl extends PrimitiveTypeImpl implements BooleanType {
	/**
	 * The cached value of the '{@link #getKindOfTypes() <em>Kind Of Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKindOfTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Metaclass> kindOfTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.BOOLEAN_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Metaclass> getKindOfTypes() {
		if (kindOfTypes == null) {
			kindOfTypes = new EObjectResolvingEList<Metaclass>(Metaclass.class, this, TypesPackage.BOOLEAN_TYPE__KIND_OF_TYPES);
		}
		return kindOfTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.BOOLEAN_TYPE__KIND_OF_TYPES:
				return getKindOfTypes();
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
			case TypesPackage.BOOLEAN_TYPE__KIND_OF_TYPES:
				getKindOfTypes().clear();
				getKindOfTypes().addAll((Collection<? extends Metaclass>)newValue);
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
			case TypesPackage.BOOLEAN_TYPE__KIND_OF_TYPES:
				getKindOfTypes().clear();
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
			case TypesPackage.BOOLEAN_TYPE__KIND_OF_TYPES:
				return kindOfTypes != null && !kindOfTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BooleanTypeImpl
