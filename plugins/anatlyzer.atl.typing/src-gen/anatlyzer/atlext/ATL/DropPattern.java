/**
 */
package anatlyzer.atlext.ATL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Drop Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.DropPattern#getOutPattern <em>Out Pattern</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getDropPattern()
 * @model
 * @generated
 */
public interface DropPattern extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Out Pattern</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.OutPattern#getDropPattern <em>Drop Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Pattern</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Pattern</em>' container reference.
	 * @see #setOutPattern(OutPattern)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getDropPattern_OutPattern()
	 * @see anatlyzer.atlext.ATL.OutPattern#getDropPattern
	 * @model opposite="dropPattern" required="true" transient="false" ordered="false"
	 * @generated
	 */
	OutPattern getOutPattern();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.DropPattern#getOutPattern <em>Out Pattern</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Pattern</em>' container reference.
	 * @see #getOutPattern()
	 * @generated
	 */
	void setOutPattern(OutPattern value);

} // DropPattern
