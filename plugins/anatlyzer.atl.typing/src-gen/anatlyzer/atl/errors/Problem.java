/**
 */
package anatlyzer.atl.errors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Problem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.Problem#getDependents <em>Dependents</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.Problem#getDescription <em>Description</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.Problem#getSeverity <em>Severity</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.Problem#isNeedsCSP <em>Needs CSP</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.Problem#getStatus <em>Status</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.Problem#getIgnoredByUser <em>Ignored By User</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.Problem#getData <em>Data</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem()
 * @model abstract="true"
 * @generated
 */
public interface Problem extends AnalysisInfo {
	/**
	 * Returns the value of the '<em><b>Dependents</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atl.errors.Problem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependents</em>' containment reference list.
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem_Dependents()
	 * @model containment="true"
	 * @generated
	 */
	EList<Problem> getDependents();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem_Description()
	 * @model required="true"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.Problem#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Severity</b></em>' attribute.
	 * The literals are from the enumeration {@link anatlyzer.atl.errors.SeverityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity</em>' attribute.
	 * @see anatlyzer.atl.errors.SeverityKind
	 * @see #setSeverity(SeverityKind)
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem_Severity()
	 * @model required="true"
	 * @generated
	 */
	SeverityKind getSeverity();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.Problem#getSeverity <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity</em>' attribute.
	 * @see anatlyzer.atl.errors.SeverityKind
	 * @see #getSeverity()
	 * @generated
	 */
	void setSeverity(SeverityKind value);

	/**
	 * Returns the value of the '<em><b>Needs CSP</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Needs CSP</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Needs CSP</em>' attribute.
	 * @see #setNeedsCSP(boolean)
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem_NeedsCSP()
	 * @model default="false"
	 * @generated
	 */
	boolean isNeedsCSP();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.Problem#isNeedsCSP <em>Needs CSP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Needs CSP</em>' attribute.
	 * @see #isNeedsCSP()
	 * @generated
	 */
	void setNeedsCSP(boolean value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link anatlyzer.atl.errors.ProblemStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see anatlyzer.atl.errors.ProblemStatus
	 * @see #setStatus(ProblemStatus)
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem_Status()
	 * @model required="true"
	 * @generated
	 */
	ProblemStatus getStatus();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.Problem#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see anatlyzer.atl.errors.ProblemStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(ProblemStatus value);

	/**
	 * Returns the value of the '<em><b>Ignored By User</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignored By User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignored By User</em>' attribute.
	 * @see #setIgnoredByUser(Boolean)
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem_IgnoredByUser()
	 * @model default="false" required="true"
	 * @generated
	 */
	Boolean getIgnoredByUser();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.Problem#getIgnoredByUser <em>Ignored By User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignored By User</em>' attribute.
	 * @see #getIgnoredByUser()
	 * @generated
	 */
	void setIgnoredByUser(Boolean value);

	/**
	 * Returns the value of the '<em><b>Data</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.Object},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' map.
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#getProblem_Data()
	 * @model mapType="anatlyzer.atl.errors.StringToObjectMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EJavaObject&gt;" transient="true"
	 * @generated
	 */
	EMap<String, Object> getData();

} // Problem
