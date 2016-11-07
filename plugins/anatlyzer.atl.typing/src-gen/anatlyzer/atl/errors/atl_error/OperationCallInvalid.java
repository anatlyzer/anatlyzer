/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.types.Type;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Call Invalid</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getType <em>Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getMetamodelName <em>Metamodel Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getActualParameters <em>Actual Parameters</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalid()
 * @model abstract="true"
 * @generated
 */
public interface OperationCallInvalid extends NavigationProblem, RuntimeError {
	/**
	 * Returns the value of the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Name</em>' attribute.
	 * @see #setOperationName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalid_OperationName()
	 * @model required="true"
	 * @generated
	 */
	String getOperationName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getOperationName <em>Operation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Name</em>' attribute.
	 * @see #getOperationName()
	 * @generated
	 */
	void setOperationName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Type)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalid_Type()
	 * @model required="true"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalid_ClassName()
	 * @model required="true"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodel Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metamodel Name</em>' attribute.
	 * @see #setMetamodelName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalid_MetamodelName()
	 * @model required="true"
	 * @generated
	 */
	String getMetamodelName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationCallInvalid#getMetamodelName <em>Metamodel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metamodel Name</em>' attribute.
	 * @see #getMetamodelName()
	 * @generated
	 */
	void setMetamodelName(String value);

	/**
	 * Returns the value of the '<em><b>Formal Parameters</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atl.types.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameters</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalid_FormalParameters()
	 * @model
	 * @generated
	 */
	EList<Type> getFormalParameters();

	/**
	 * Returns the value of the '<em><b>Actual Parameters</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atl.types.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameters</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalid_ActualParameters()
	 * @model
	 * @generated
	 */
	EList<Type> getActualParameters();

} // OperationCallInvalid
