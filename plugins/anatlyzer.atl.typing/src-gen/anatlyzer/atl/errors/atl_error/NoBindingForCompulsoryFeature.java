/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>No Binding For Compulsory Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getSubrule <em>Subrule</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoBindingForCompulsoryFeature()
 * @model annotation="description name='No binding for compulsory target feature' text='Applicable to references and string attributes without default value.'"
 *        annotation="info prec='static' path='no' severity='error-target' when='model-dep' kind='tgt-typing' phase='analysis' source='none'"
 *        annotation="ignorestring name='no-binding-compulsory-feature'"
 * @generated
 */
public interface NoBindingForCompulsoryFeature extends TargetModelConformanceProblem, BindingProblem {

	/**
	 * Returns the value of the '<em><b>Subrule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subrule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subrule</em>' reference.
	 * @see #setSubrule(EObject)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoBindingForCompulsoryFeature_Subrule()
	 * @model
	 * @generated
	 */
	EObject getSubrule();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getSubrule <em>Subrule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subrule</em>' reference.
	 * @see #getSubrule()
	 * @generated
	 */
	void setSubrule(EObject value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind
	 * @see #setKind(NoBindingForCompulsoryFeatureKind)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoBindingForCompulsoryFeature_Kind()
	 * @model
	 * @generated
	 */
	NoBindingForCompulsoryFeatureKind getKind();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(NoBindingForCompulsoryFeatureKind value);
} // NoBindingForCompulsoryFeature
