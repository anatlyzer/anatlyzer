/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.impl.ProblemImpl;
import anatlyzer.atl.types.Metaclass;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conflicting Rule Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ConflictingRuleSetImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ConflictingRuleSetImpl#getRules <em>Rules</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.ConflictingRuleSetImpl#getAnalyserInfo <em>Analyser Info</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConflictingRuleSetImpl extends ProblemImpl implements ConflictingRuleSet {
	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Metaclass> types;

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> rules;

	/**
	 * The default value of the '{@link #getAnalyserInfo() <em>Analyser Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalyserInfo()
	 * @generated
	 * @ordered
	 */
	protected static final Object ANALYSER_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAnalyserInfo() <em>Analyser Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalyserInfo()
	 * @generated
	 * @ordered
	 */
	protected Object analyserInfo = ANALYSER_INFO_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConflictingRuleSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.CONFLICTING_RULE_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Metaclass> getTypes() {
		if (types == null) {
			types = new EObjectResolvingEList<Metaclass>(Metaclass.class, this, AtlErrorPackage.CONFLICTING_RULE_SET__TYPES);
		}
		return types;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getRules() {
		if (rules == null) {
			rules = new EObjectResolvingEList<EObject>(EObject.class, this, AtlErrorPackage.CONFLICTING_RULE_SET__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getAnalyserInfo() {
		return analyserInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnalyserInfo(Object newAnalyserInfo) {
		Object oldAnalyserInfo = analyserInfo;
		analyserInfo = newAnalyserInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.CONFLICTING_RULE_SET__ANALYSER_INFO, oldAnalyserInfo, analyserInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.CONFLICTING_RULE_SET__TYPES:
				return getTypes();
			case AtlErrorPackage.CONFLICTING_RULE_SET__RULES:
				return getRules();
			case AtlErrorPackage.CONFLICTING_RULE_SET__ANALYSER_INFO:
				return getAnalyserInfo();
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
			case AtlErrorPackage.CONFLICTING_RULE_SET__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends Metaclass>)newValue);
				return;
			case AtlErrorPackage.CONFLICTING_RULE_SET__RULES:
				getRules().clear();
				getRules().addAll((Collection<? extends EObject>)newValue);
				return;
			case AtlErrorPackage.CONFLICTING_RULE_SET__ANALYSER_INFO:
				setAnalyserInfo(newValue);
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
			case AtlErrorPackage.CONFLICTING_RULE_SET__TYPES:
				getTypes().clear();
				return;
			case AtlErrorPackage.CONFLICTING_RULE_SET__RULES:
				getRules().clear();
				return;
			case AtlErrorPackage.CONFLICTING_RULE_SET__ANALYSER_INFO:
				setAnalyserInfo(ANALYSER_INFO_EDEFAULT);
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
			case AtlErrorPackage.CONFLICTING_RULE_SET__TYPES:
				return types != null && !types.isEmpty();
			case AtlErrorPackage.CONFLICTING_RULE_SET__RULES:
				return rules != null && !rules.isEmpty();
			case AtlErrorPackage.CONFLICTING_RULE_SET__ANALYSER_INFO:
				return ANALYSER_INFO_EDEFAULT == null ? analyserInfo != null : !ANALYSER_INFO_EDEFAULT.equals(analyserInfo);
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
		result.append(" (analyserInfo: ");
		result.append(analyserInfo);
		result.append(')');
		return result.toString();
	}

} //ConflictingRuleSetImpl
