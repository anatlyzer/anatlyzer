/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binding With Resolved By Incompatible Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingWithResolvedByIncompatibleRule()
 * @model annotation="description name='Binding resolved by rule with invalid target' text='A rule may resolve a binding but its first output pattern will produce a target element incompatible with the binding feature'"
 *        annotation="info prec='sometimes-solver' path='yes' severity='error-target' when='model-dep' kind='tgt-typing' phase='analysis' source='none'"
 *        annotation="ignorestring name='invalid-target'"
 * @generated
 */
public interface BindingWithResolvedByIncompatibleRule extends BindingProblem, BindingResolution {
} // BindingWithResolvedByIncompatibleRule
