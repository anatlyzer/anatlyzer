/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.errors.impl.BatchAnalysisImpl;
import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule Conflicts</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.RuleConflictsImpl#getConflicts <em>Conflicts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleConflictsImpl extends BatchAnalysisImpl implements RuleConflicts {
	/**
	 * The cached value of the '{@link #getConflicts() <em>Conflicts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConflicts()
	 * @generated
	 * @ordered
	 */
	protected EList<ConflictingRuleSet> conflicts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleConflictsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.RULE_CONFLICTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConflictingRuleSet> getConflicts() {
		if (conflicts == null) {
			conflicts = new EObjectResolvingEList<ConflictingRuleSet>(ConflictingRuleSet.class, this, AtlErrorPackage.RULE_CONFLICTS__CONFLICTS);
		}
		return conflicts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.RULE_CONFLICTS__CONFLICTS:
				return getConflicts();
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
			case AtlErrorPackage.RULE_CONFLICTS__CONFLICTS:
				getConflicts().clear();
				getConflicts().addAll((Collection<? extends ConflictingRuleSet>)newValue);
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
			case AtlErrorPackage.RULE_CONFLICTS__CONFLICTS:
				getConflicts().clear();
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
			case AtlErrorPackage.RULE_CONFLICTS__CONFLICTS:
				return conflicts != null && !conflicts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RuleConflictsImpl
