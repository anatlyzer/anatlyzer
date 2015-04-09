/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Found In Subtype</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.FeatureFoundInSubtypeImpl#getPossibleClasses <em>Possible Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureFoundInSubtypeImpl extends FeatureNotFoundImpl implements FeatureFoundInSubtype {
	/**
	 * The cached value of the '{@link #getPossibleClasses() <em>Possible Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPossibleClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> possibleClasses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureFoundInSubtypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.FEATURE_FOUND_IN_SUBTYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getPossibleClasses() {
		if (possibleClasses == null) {
			possibleClasses = new EObjectResolvingEList<EClass>(EClass.class, this, AtlErrorPackage.FEATURE_FOUND_IN_SUBTYPE__POSSIBLE_CLASSES);
		}
		return possibleClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.FEATURE_FOUND_IN_SUBTYPE__POSSIBLE_CLASSES:
				return getPossibleClasses();
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
			case AtlErrorPackage.FEATURE_FOUND_IN_SUBTYPE__POSSIBLE_CLASSES:
				getPossibleClasses().clear();
				getPossibleClasses().addAll((Collection<? extends EClass>)newValue);
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
			case AtlErrorPackage.FEATURE_FOUND_IN_SUBTYPE__POSSIBLE_CLASSES:
				getPossibleClasses().clear();
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
			case AtlErrorPackage.FEATURE_FOUND_IN_SUBTYPE__POSSIBLE_CLASSES:
				return possibleClasses != null && !possibleClasses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FeatureFoundInSubtypeImpl
