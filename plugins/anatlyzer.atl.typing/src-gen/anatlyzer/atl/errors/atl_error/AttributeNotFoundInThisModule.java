/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Not Found In This Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAttributeNotFoundInThisModule()
 * @model annotation="description name='Attribute not found in ThisModule' text='Attribute defined in the transformation scope cannot be found (i.e., invoked with thisModule.operation()).'"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='src-typing' phase='typing' source='none'"
 * @generated
 */
public interface AttributeNotFoundInThisModule extends NavigationProblem, RuntimeError {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAttributeNotFoundInThisModule_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // AttributeNotFoundInThisModule
