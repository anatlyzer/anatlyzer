/**
 */
package anatlyzer.example.sconstraints;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Num Refs Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.example.sconstraints.NumRefsConstraint#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.example.sconstraints.NumRefsConstraint#getRefName <em>Ref Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getNumRefsConstraint()
 * @model
 * @generated
 */
public interface NumRefsConstraint extends NumConstraint {
	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getNumRefsConstraint_ClassName()
	 * @model
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link anatlyzer.example.sconstraints.NumRefsConstraint#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Name</em>' attribute.
	 * @see #setRefName(String)
	 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getNumRefsConstraint_RefName()
	 * @model
	 * @generated
	 */
	String getRefName();

	/**
	 * Sets the value of the '{@link anatlyzer.example.sconstraints.NumRefsConstraint#getRefName <em>Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Name</em>' attribute.
	 * @see #getRefName()
	 * @generated
	 */
	void setRefName(String value);

} // NumRefsConstraint
