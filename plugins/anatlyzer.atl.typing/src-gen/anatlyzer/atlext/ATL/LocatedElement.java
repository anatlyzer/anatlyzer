/**
 */
package anatlyzer.atlext.ATL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Located Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.LocatedElement#getLocation <em>Location</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.LocatedElement#getCommentsBefore <em>Comments Before</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.LocatedElement#getCommentsAfter <em>Comments After</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.LocatedElement#getFileLocation <em>File Location</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.LocatedElement#getFileObject <em>File Object</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.LocatedElement#getProblems <em>Problems</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.LocatedElement#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement()
 * @model abstract="true"
 * @generated
 */
public interface LocatedElement extends EObject {
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
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement_Location()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.String" ordered="false"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.LocatedElement#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Comments Before</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments Before</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments Before</em>' attribute list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement_CommentsBefore()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.String"
	 * @generated
	 */
	EList<String> getCommentsBefore();

	/**
	 * Returns the value of the '<em><b>Comments After</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments After</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments After</em>' attribute list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement_CommentsAfter()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.String"
	 * @generated
	 */
	EList<String> getCommentsAfter();

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
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement_FileLocation()
	 * @model
	 * @generated
	 */
	String getFileLocation();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.LocatedElement#getFileLocation <em>File Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Location</em>' attribute.
	 * @see #getFileLocation()
	 * @generated
	 */
	void setFileLocation(String value);

	/**
	 * Returns the value of the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Object</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Object</em>' attribute.
	 * @see #setFileObject(Object)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement_FileObject()
	 * @model
	 * @generated
	 */
	Object getFileObject();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.LocatedElement#getFileObject <em>File Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Object</em>' attribute.
	 * @see #getFileObject()
	 * @generated
	 */
	void setFileObject(Object value);

	/**
	 * Returns the value of the '<em><b>Problems</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Problems</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Problems</em>' reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement_Problems()
	 * @model
	 * @generated
	 */
	EList<EObject> getProblems();

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' map.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLocatedElement_Annotations()
	 * @model mapType="anatlyzer.atlext.ATL.StringToStringMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getAnnotations();

} // LocatedElement
