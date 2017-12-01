/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.errors.BatchAnalysis;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Conflicts</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.RuleConflicts#getConflicts <em>Conflicts</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getRuleConflicts()
 * @model
 * @generated
 */
public interface RuleConflicts extends BatchAnalysis {
	/**
	 * Returns the value of the '<em><b>Conflicts</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conflicts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conflicts</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getRuleConflicts_Conflicts()
	 * @model
	 * @generated
	 */
	EList<ConflictingRuleSet> getConflicts();

} // RuleConflicts
