/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Operation Over No Collection Error</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getCollectionOperationOverNoCollectionError()
 * @model annotation="description name='Collection operation over no collection (``$\\rightarrow$\'\' vs. ``.\'\')' example='aClassInstance-&gt;select(...)'"
 *        annotation="info prec='static' path='no' severity='warning-style' when='model-dep' kind='navigation' phase='typing' source='OCL spec, tests'"
 * @generated
 */
public interface CollectionOperationOverNoCollectionError extends NavigationProblem, RuntimeError {
} // CollectionOperationOverNoCollectionError
