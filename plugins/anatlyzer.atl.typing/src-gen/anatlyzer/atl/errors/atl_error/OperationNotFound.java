/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.types.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Not Found</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getType <em>Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getMetamodelName <em>Metamodel Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFound()
 * @model
 * @generated
 */
public interface OperationNotFound extends NavigationProblem {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFound_OperationName()
	 * @model required="true"
	 * @generated
	 */
	String getOperationName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getOperationName <em>Operation Name</em>}' attribute.
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFound_Type()
	 * @model required="true"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getType <em>Type</em>}' reference.
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFound_ClassName()
	 * @model required="true"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getClassName <em>Class Name</em>}' attribute.
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationNotFound_MetamodelName()
	 * @model required="true"
	 * @generated
	 */
	String getMetamodelName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.OperationNotFound#getMetamodelName <em>Metamodel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metamodel Name</em>' attribute.
	 * @see #getMetamodelName()
	 * @generated
	 */
	void setMetamodelName(String value);

} // OperationNotFound
