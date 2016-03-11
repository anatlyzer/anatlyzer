/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Invalid Rule Inheritance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritance#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getInvalidRuleInheritance()
 * @model annotation="description name='Invalid rule inheritance' text='Problems with rule inheritance of diverse kind'"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='trafo-integrity' phase='typing' source='none'"
 * @generated
 */
public interface InvalidRuleInheritance extends LocalProblem {

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind
	 * @see #setKind(InvalidRuleInheritanceKind)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getInvalidRuleInheritance_Kind()
	 * @model required="true"
	 * @generated
	 */
	InvalidRuleInheritanceKind getKind();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.InvalidRuleInheritance#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(InvalidRuleInheritanceKind value);
} // InvalidRuleInheritance
