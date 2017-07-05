/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolve Temp Possibly Unresolved</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getProblematicClasses <em>Problematic Classes</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getProblematicClassesImplicit <em>Problematic Classes Implicit</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getResolvedExpression <em>Resolved Expression</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempPossiblyUnresolved()
 * @model annotation="description name='ResolveTemp possibly unresolved' text='A resolve temp be unresolved if certain conditions hold'"
 *        annotation="info prec='always-solver' path='yes' severity='warning-behaviour' when='model-dep' kind='trafo-rules' phase='analysis' source='none'"
 *        annotation="ignorestring name='unresolved-resolvedtemp'"
 * @generated
 */
public interface ResolveTempPossiblyUnresolved extends ResolveTempProblem, BindingResolution {
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempPossiblyUnresolved_ProblematicClasses()
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
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempPossiblyUnresolved_ProblematicClassesImplicit()
	 * @model required="true"
	 * @generated
	 */
	EList<EClass> getProblematicClassesImplicit();

	/**
	 * Returns the value of the '<em><b>Resolved Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolved Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolved Expression</em>' reference.
	 * @see #setResolvedExpression(EObject)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getResolveTempPossiblyUnresolved_ResolvedExpression()
	 * @model required="true"
	 * @generated
	 */
	EObject getResolvedExpression();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved#getResolvedExpression <em>Resolved Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolved Expression</em>' reference.
	 * @see #getResolvedExpression()
	 * @generated
	 */
	void setResolvedExpression(EObject value);

} // ResolveTempPossiblyUnresolved
