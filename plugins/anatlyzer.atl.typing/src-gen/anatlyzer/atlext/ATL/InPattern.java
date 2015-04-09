/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.OclExpression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>In Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.InPattern#getElements <em>Elements</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.InPattern#getFilter <em>Filter</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getInPattern()
 * @model
 * @generated
 */
public interface InPattern extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.InPatternElement}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.InPatternElement#getInPattern <em>In Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getInPattern_Elements()
	 * @see anatlyzer.atlext.ATL.InPatternElement#getInPattern
	 * @model opposite="inPattern" containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<InPatternElement> getElements();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter</em>' containment reference.
	 * @see #setFilter(OclExpression)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getInPattern_Filter()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	OclExpression getFilter();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.InPattern#getFilter <em>Filter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter</em>' containment reference.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(OclExpression value);

} // InPattern
