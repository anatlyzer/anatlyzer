/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>No Class Found In Metamodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel#getClassName <em>Class Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoClassFoundInMetamodel()
 * @model annotation="description name='Invalid meta-class name' text='The meta-class name cannot be found in the meta-model'"
 *        annotation="info prec='static' path='no' severity='error-load' when='trafo-dep' kind='src-typing' phase='typing' source='none'"
 * @generated
 */
public interface NoClassFoundInMetamodel extends LocalProblem {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoClassFoundInMetamodel_ClassName()
	 * @model required="true"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

} // NoClassFoundInMetamodel
