/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Call Invalid Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter#getBlamedParameterNames <em>Blamed Parameter Names</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalidParameter()
 * @model annotation="description name='Invalid actual parameter type' text='The types of the actual parameters does not match the formal parameters'"
 *        annotation="info prec='static' path='no' severity='warning-behaviour' when='model-dep' kind='src-typing' phase='typing' source='none'"
 * @generated
 */
public interface OperationCallInvalidParameter extends OperationCallInvalid, RuntimeError {
	/**
	 * Returns the value of the '<em><b>Blamed Parameter Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Blamed Parameter Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Blamed Parameter Names</em>' attribute list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationCallInvalidParameter_BlamedParameterNames()
	 * @model
	 * @generated
	 */
	EList<String> getBlamedParameterNames();

} // OperationCallInvalidParameter
