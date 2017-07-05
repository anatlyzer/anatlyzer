/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Access In Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FeatureAccessInCollection#getFeatureName <em>Feature Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureAccessInCollection()
 * @model annotation="description name='Feature access in collection' text='Collections do not have features. Dot-notation cannot be used over them' example='Sequence { }.length'"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='navigation' phase='typing' source='OCL spec, tests'"
 * @generated
 */
public interface FeatureAccessInCollection extends NavigationProblem, RuntimeError {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureAccessInCollection_FeatureName()
	 * @model required="true"
	 * @generated
	 */
	String getFeatureName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.FeatureAccessInCollection#getFeatureName <em>Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Name</em>' attribute.
	 * @see #getFeatureName()
	 * @generated
	 */
	void setFeatureName(String value);

} // FeatureAccessInCollection
