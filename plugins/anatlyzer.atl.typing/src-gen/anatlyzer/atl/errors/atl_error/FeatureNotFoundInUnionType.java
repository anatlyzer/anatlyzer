/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Not Found In Union Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType#getFeatureName <em>Feature Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureNotFoundInUnionType()
 * @model annotation="description name='Feature not found in union type' text='Feature cannot be found in one or more of the possible types of an expression. Only applicable when an expression is deemed have several possible types.'"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='src-typing' phase='typing' source='none'"
 *        annotation="ignorestring name='not-found-in-union'"
 * @generated
 */
public interface FeatureNotFoundInUnionType extends NavigationProblem, RuntimeError {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureNotFoundInUnionType_FeatureName()
	 * @model required="true"
	 * @generated
	 */
	String getFeatureName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType#getFeatureName <em>Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Name</em>' attribute.
	 * @see #getFeatureName()
	 * @generated
	 */
	void setFeatureName(String value);

} // FeatureNotFoundInUnionType
