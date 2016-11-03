/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Found In Subtype</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFeatureFoundInSubtype()
 * @model annotation="description name='Feature found in subtype' text='Feature cannot be found in an object\'s class, but found in subtype. The error may not happen depending on the program logic.'"
 *        annotation="info prec='sometimes-solver' path='yes' severity='runtime-error' when='model-dep' kind='src-typing' phase='typing' source='none'"
 *        annotation="ignorestring name='found-in-subtype'"
 * @generated
 */
public interface FeatureFoundInSubtype extends FeatureNotFound, FoundInSubtype, RuntimeError {

} // FeatureFoundInSubtype
