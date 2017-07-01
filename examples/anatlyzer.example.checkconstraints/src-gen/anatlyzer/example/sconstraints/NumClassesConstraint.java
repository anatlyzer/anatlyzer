/**
 */
package anatlyzer.example.sconstraints;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Num Classes Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.example.sconstraints.NumClassesConstraint#getClassName <em>Class Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getNumClassesConstraint()
 * @model
 * @generated
 */
public interface NumClassesConstraint extends NumConstraint {
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
	 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#getNumClassesConstraint_ClassName()
	 * @model
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link anatlyzer.example.sconstraints.NumClassesConstraint#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

} // NumClassesConstraint
