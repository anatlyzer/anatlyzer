/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.BindingProblem;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;

import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>No Binding For Compulsory Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl#getSubrule <em>Subrule</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.NoBindingForCompulsoryFeatureImpl#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NoBindingForCompulsoryFeatureImpl extends TargetModelConformanceProblemImpl implements NoBindingForCompulsoryFeature {
	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature feature;

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
	 * The cached value of the '{@link #getSubrule() <em>Subrule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubrule()
	 * @generated
	 * @ordered
	 */
	protected EObject subrule;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final NoBindingForCompulsoryFeatureKind KIND_EDEFAULT = NoBindingForCompulsoryFeatureKind.IN_NORMAL_RULE;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected NoBindingForCompulsoryFeatureKind kind = KIND_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NoBindingForCompulsoryFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.NO_BINDING_FOR_COMPULSORY_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getFeature() {
		if (feature != null && feature.eIsProxy()) {
			InternalEObject oldFeature = (InternalEObject)feature;
			feature = (EStructuralFeature)eResolveProxy(oldFeature);
			if (feature != oldFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE, oldFeature, feature));
			}
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetFeature() {
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeature(EStructuralFeature newFeature) {
		EStructuralFeature oldFeature = feature;
		feature = newFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE, oldFeature, feature));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME, oldFeatureName, featureName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getSubrule() {
		if (subrule != null && subrule.eIsProxy()) {
			InternalEObject oldSubrule = (InternalEObject)subrule;
			subrule = eResolveProxy(oldSubrule);
			if (subrule != oldSubrule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE, oldSubrule, subrule));
			}
		}
		return subrule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetSubrule() {
		return subrule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubrule(EObject newSubrule) {
		EObject oldSubrule = subrule;
		subrule = newSubrule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE, oldSubrule, subrule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoBindingForCompulsoryFeatureKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(NoBindingForCompulsoryFeatureKind newKind) {
		NoBindingForCompulsoryFeatureKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE:
				if (resolve) return getFeature();
				return basicGetFeature();
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME:
				return getFeatureName();
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE:
				if (resolve) return getSubrule();
				return basicGetSubrule();
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__KIND:
				return getKind();
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
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE:
				setFeature((EStructuralFeature)newValue);
				return;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME:
				setFeatureName((String)newValue);
				return;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE:
				setSubrule((EObject)newValue);
				return;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__KIND:
				setKind((NoBindingForCompulsoryFeatureKind)newValue);
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
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE:
				setFeature((EStructuralFeature)null);
				return;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME:
				setFeatureName(FEATURE_NAME_EDEFAULT);
				return;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE:
				setSubrule((EObject)null);
				return;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__KIND:
				setKind(KIND_EDEFAULT);
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
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE:
				return feature != null;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME:
				return FEATURE_NAME_EDEFAULT == null ? featureName != null : !FEATURE_NAME_EDEFAULT.equals(featureName);
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__SUBRULE:
				return subrule != null;
			case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__KIND:
				return kind != KIND_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == BindingProblem.class) {
			switch (derivedFeatureID) {
				case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE: return AtlErrorPackage.BINDING_PROBLEM__FEATURE;
				case AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME: return AtlErrorPackage.BINDING_PROBLEM__FEATURE_NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == BindingProblem.class) {
			switch (baseFeatureID) {
				case AtlErrorPackage.BINDING_PROBLEM__FEATURE: return AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE;
				case AtlErrorPackage.BINDING_PROBLEM__FEATURE_NAME: return AtlErrorPackage.NO_BINDING_FOR_COMPULSORY_FEATURE__FEATURE_NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", kind: ");
		result.append(kind);
		result.append(')');
		return result.toString();
	}

} //NoBindingForCompulsoryFeatureImpl
