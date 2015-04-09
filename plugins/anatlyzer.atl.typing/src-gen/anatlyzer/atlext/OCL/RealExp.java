/**
 */
package anatlyzer.atlext.OCL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Real Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.RealExp#getRealSymbol <em>Real Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getRealExp()
 * @model
 * @generated
 */
public interface RealExp extends NumericExp {
	/**
	 * Returns the value of the '<em><b>Real Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Real Symbol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Real Symbol</em>' attribute.
	 * @see #setRealSymbol(double)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getRealExp_RealSymbol()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Double" required="true" ordered="false"
	 * @generated
	 */
	double getRealSymbol();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.RealExp#getRealSymbol <em>Real Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Real Symbol</em>' attribute.
	 * @see #getRealSymbol()
	 * @generated
	 */
	void setRealSymbol(double value);

} // RealExp
