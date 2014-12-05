/**
 */
package anatlyzer.atl.errors;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analysis Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.AnalysisResult#getProblems <em>Problems</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.AnalysisResultPackage#getAnalysisResult()
 * @model
 * @generated
 */
public interface AnalysisResult extends EObject {
	/**
	 * Returns the value of the '<em><b>Problems</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atl.errors.Problem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Problems</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Problems</em>' containment reference list.
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getAnalysisResult_Problems()
	 * @model containment="true"
	 * @generated
	 */
	EList<Problem> getProblems();

} // AnalysisResult
