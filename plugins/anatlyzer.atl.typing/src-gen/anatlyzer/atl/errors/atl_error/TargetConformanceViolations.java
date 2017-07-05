/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.errors.BatchAnalysis;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target Conformance Violations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.TargetConformanceViolations#getTargetIssues <em>Target Issues</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getTargetConformanceViolations()
 * @model
 * @generated
 */
public interface TargetConformanceViolations extends BatchAnalysis {
	/**
	 * Returns the value of the '<em><b>Target Issues</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atl.errors.atl_error.BatchTargetConformanceIssue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Issues</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Issues</em>' containment reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getTargetConformanceViolations_TargetIssues()
	 * @model containment="true"
	 * @generated
	 */
	EList<BatchTargetConformanceIssue> getTargetIssues();

} // TargetConformanceViolations
