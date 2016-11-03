/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Found In Subtype</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getOperationFoundInSubtype()
 * @model annotation="description name='Operation found in subtype' text='Operation cannot be found in an object\'s class, but found in subtype. The error may not happen depending on the program logic.'"
 *        annotation="info prec='sometimes-solver' path='yes' severity='runtime-error' when='model-dep' kind='src-typing' phase='typing' source='none'"
 *        annotation="ignorestring name='found-in-subtype'"
 * @generated
 */
public interface OperationFoundInSubtype extends OperationNotFound, FoundInSubtype, RuntimeError {

} // OperationFoundInSubtype
