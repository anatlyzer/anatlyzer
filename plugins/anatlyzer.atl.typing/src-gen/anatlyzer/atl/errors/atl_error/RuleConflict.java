/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.errors.Problem;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Conflict</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.RuleConflict#getConflicts <em>Conflicts</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getRuleConflict()
 * @model annotation="description name='Rule conflict' example=''"
 *        annotation="info prec='sometimes-solver' path='yes' severity='runtime-error' when='model-dep' kind='trafo-rules' phase='analysis (separate)' source=''"
 * @generated
 */
public interface RuleConflict extends Problem {
	/**
	 * Returns the value of the '<em><b>Conflicts</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conflicts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conflicts</em>' containment reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getRuleConflict_Conflicts()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConflictingRuleSet> getConflicts();

} // RuleConflict
