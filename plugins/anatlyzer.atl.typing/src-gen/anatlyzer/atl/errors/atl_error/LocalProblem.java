/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.Recovery;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local Problem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.LocalProblem#getLocation <em>Location</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.LocalProblem#getFileLocation <em>File Location</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.LocalProblem#getElement <em>Element</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.LocalProblem#getRecovery <em>Recovery</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getLocalProblem()
 * @model abstract="true"
 * @generated
 */
public interface LocalProblem extends Problem {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getLocalProblem_Location()
	 * @model required="true"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Location</em>' attribute.
	 * @see #setFileLocation(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getLocalProblem_FileLocation()
	 * @model
	 * @generated
	 */
	String getFileLocation();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getFileLocation <em>File Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Location</em>' attribute.
	 * @see #getFileLocation()
	 * @generated
	 */
	void setFileLocation(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(EObject)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getLocalProblem_Element()
	 * @model required="true"
	 * @generated
	 */
	EObject getElement();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EObject value);

	/**
	 * Returns the value of the '<em><b>Recovery</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recovery</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recovery</em>' containment reference.
	 * @see #setRecovery(Recovery)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getLocalProblem_Recovery()
	 * @model containment="true"
	 * @generated
	 */
	Recovery getRecovery();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.LocalProblem#getRecovery <em>Recovery</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recovery</em>' containment reference.
	 * @see #getRecovery()
	 * @generated
	 */
	void setRecovery(Recovery value);

} // LocalProblem
