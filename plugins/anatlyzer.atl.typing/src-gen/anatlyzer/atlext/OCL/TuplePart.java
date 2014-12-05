/**
 */
package anatlyzer.atlext.OCL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.TuplePart#getTuple <em>Tuple</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getTuplePart()
 * @model
 * @generated
 */
public interface TuplePart extends VariableDeclaration {
	/**
	 * Returns the value of the '<em><b>Tuple</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.TupleExp#getTuplePart <em>Tuple Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuple</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuple</em>' container reference.
	 * @see #setTuple(TupleExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getTuplePart_Tuple()
	 * @see anatlyzer.atlext.OCL.TupleExp#getTuplePart
	 * @model opposite="tuplePart" required="true" transient="false" ordered="false"
	 * @generated
	 */
	TupleExp getTuple();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.TuplePart#getTuple <em>Tuple</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuple</em>' container reference.
	 * @see #getTuple()
	 * @generated
	 */
	void setTuple(TupleExp value);

} // TuplePart
