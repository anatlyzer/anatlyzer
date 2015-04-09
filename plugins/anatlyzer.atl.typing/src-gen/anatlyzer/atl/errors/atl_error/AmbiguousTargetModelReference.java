/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ambiguous Target Model Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference#getModelName <em>Model Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAmbiguousTargetModelReference()
 * @model annotation="description name='Ambiguous model reference' text='[TODO]: Implemented but need to find an example'"
 *        annotation="info prec='static' path='no' severity='warning-behaviour' when='model-dep' kind='navigation' phase='analysis' source='none'"
 * @generated
 */
public interface AmbiguousTargetModelReference extends LocalProblem {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getAmbiguousTargetModelReference_ModelName()
	 * @model required="true"
	 * @generated
	 */
	String getModelName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference#getModelName <em>Model Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Name</em>' attribute.
	 * @see #getModelName()
	 * @generated
	 */
	void setModelName(String value);

} // AmbiguousTargetModelReference
