/**
 */
package anatlyzer.atlext.OCL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterator Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.IteratorExp#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getIteratorExp()
 * @model
 * @generated
 */
public interface IteratorExp extends LoopExp {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getIteratorExp_Name()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.IteratorExp#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // IteratorExp
