/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Each Out Pattern Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.ForEachOutPatternElement#getCollection <em>Collection</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.ForEachOutPatternElement#getIterator <em>Iterator</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getForEachOutPatternElement()
 * @model
 * @generated
 */
public interface ForEachOutPatternElement extends OutPatternElement {
	/**
	 * Returns the value of the '<em><b>Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection</em>' containment reference.
	 * @see #setCollection(OclExpression)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getForEachOutPatternElement_Collection()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getCollection();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.ForEachOutPatternElement#getCollection <em>Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection</em>' containment reference.
	 * @see #getCollection()
	 * @generated
	 */
	void setCollection(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterator</em>' containment reference.
	 * @see #setIterator(Iterator)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getForEachOutPatternElement_Iterator()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Iterator getIterator();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.ForEachOutPatternElement#getIterator <em>Iterator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iterator</em>' containment reference.
	 * @see #getIterator()
	 * @generated
	 */
	void setIterator(Iterator value);

} // ForEachOutPatternElement
