/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binding Possibly Unresolved</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved#getProblematicClasses <em>Problematic Classes</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved#getProblematicClassesImplicit <em>Problematic Classes Implicit</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingPossiblyUnresolved()
 * @model annotation="description name='Binding possibly unresolved' text='A binding may be unresolved if certain conditions hold'"
 *        annotation="info prec='always-solver' path='yes' severity='warning-behaviour' when='model-dep' kind='trafo-rules' phase='analysis' source='none'"
 *        annotation="ignorestring name='unresolved-binding'"
 * @generated
 */
public interface BindingPossiblyUnresolved extends BindingProblem, BindingResolution {
	/**
	 * Returns the value of the '<em><b>Problematic Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Problematic Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Problematic Classes</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingPossiblyUnresolved_ProblematicClasses()
	 * @model required="true"
	 * @generated
	 */
	EList<EClass> getProblematicClasses();

	/**
	 * Returns the value of the '<em><b>Problematic Classes Implicit</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Problematic Classes Implicit</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Problematic Classes Implicit</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getBindingPossiblyUnresolved_ProblematicClassesImplicit()
	 * @model required="true"
	 * @generated
	 */
	EList<EClass> getProblematicClassesImplicit();

} // BindingPossiblyUnresolved
