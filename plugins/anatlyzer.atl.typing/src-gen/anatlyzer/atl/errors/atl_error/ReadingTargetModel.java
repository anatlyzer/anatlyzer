/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reading Target Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ReadingTargetModel#getModelName <em>Model Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getReadingTargetModel()
 * @model annotation="description name='Read access to target model' text='[DISABLE?] False positives when the same meta-model is used as source and target'"
 *        annotation="info prec='static' path='no' severity='warning-behaviour' when='model-dep' kind='trafo-integrity' phase='typing' source='none'"
 * @generated
 */
public interface ReadingTargetModel extends LocalProblem {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getReadingTargetModel_ModelName()
	 * @model required="true"
	 * @generated
	 */
	String getModelName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.ReadingTargetModel#getModelName <em>Model Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Name</em>' attribute.
	 * @see #getModelName()
	 * @generated
	 */
	void setModelName(String value);

} // ReadingTargetModel
