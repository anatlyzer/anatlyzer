/**
 */
package anatlyzer.atl.errors.atl_error;

import anatlyzer.atl.types.Type;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Not Found</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getType <em>Type</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getMetamodelName <em>Metamodel Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureNotFound()
 * @model
 * @generated
 */
public interface FeatureNotFound extends NavigationProblem {
	/**
	 * Returns the value of the '<em><b>Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Name</em>' attribute.
	 * @see #setFeatureName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureNotFound_FeatureName()
	 * @model required="true"
	 * @generated
	 */
	String getFeatureName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getFeatureName <em>Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Name</em>' attribute.
	 * @see #getFeatureName()
	 * @generated
	 */
	void setFeatureName(String value);

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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureNotFound_Type()
	 * @model required="true"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getType <em>Type</em>}' reference.
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureNotFound_ClassName()
	 * @model required="true"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getClassName <em>Class Name</em>}' attribute.
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureNotFound_MetamodelName()
	 * @model required="true"
	 * @generated
	 */
	String getMetamodelName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound#getMetamodelName <em>Metamodel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metamodel Name</em>' attribute.
	 * @see #getMetamodelName()
	 * @generated
	 */
	void setMetamodelName(String value);

} // FeatureNotFound
