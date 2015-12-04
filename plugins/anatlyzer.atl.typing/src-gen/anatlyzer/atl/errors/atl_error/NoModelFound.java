/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>No Model Found</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.NoModelFound#getModelName <em>Model Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoModelFound()
 * @model annotation="description name='Invalid meta-model name' text='The specified model is not declared'"
 *        annotation="info prec='static' path='no' severity='error-load' when='trafo-dep' kind='src-typing' phase='typing' source='none'"
 * @generated
 */
public interface NoModelFound extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Name</em>' attribute.
	 * @see #setModelName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoModelFound_ModelName()
	 * @model required="true"
	 * @generated
	 */
	String getModelName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.NoModelFound#getModelName <em>Model Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Name</em>' attribute.
	 * @see #getModelName()
	 * @generated
	 */
	void setModelName(String value);

} // NoModelFound
