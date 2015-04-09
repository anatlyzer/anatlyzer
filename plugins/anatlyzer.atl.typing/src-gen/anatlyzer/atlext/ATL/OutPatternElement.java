/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atlext.OCL.OclModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Out Pattern Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.OutPatternElement#getOutPattern <em>Out Pattern</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.OutPatternElement#getSourceElement <em>Source Element</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.OutPatternElement#getBindings <em>Bindings</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.OutPatternElement#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getOutPatternElement()
 * @model abstract="true"
 * @generated
 */
public interface OutPatternElement extends PatternElement {
	/**
	 * Returns the value of the '<em><b>Out Pattern</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.OutPattern#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Pattern</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Pattern</em>' container reference.
	 * @see #setOutPattern(OutPattern)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getOutPatternElement_OutPattern()
	 * @see anatlyzer.atlext.ATL.OutPattern#getElements
	 * @model opposite="elements" required="true" transient="false" ordered="false"
	 * @generated
	 */
	OutPattern getOutPattern();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.OutPatternElement#getOutPattern <em>Out Pattern</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Pattern</em>' container reference.
	 * @see #getOutPattern()
	 * @generated
	 */
	void setOutPattern(OutPattern value);

	/**
	 * Returns the value of the '<em><b>Source Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.InPatternElement#getMapsTo <em>Maps To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Element</em>' reference.
	 * @see #setSourceElement(InPatternElement)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getOutPatternElement_SourceElement()
	 * @see anatlyzer.atlext.ATL.InPatternElement#getMapsTo
	 * @model opposite="mapsTo" ordered="false"
	 * @generated
	 */
	InPatternElement getSourceElement();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.OutPatternElement#getSourceElement <em>Source Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Element</em>' reference.
	 * @see #getSourceElement()
	 * @generated
	 */
	void setSourceElement(InPatternElement value);

	/**
	 * Returns the value of the '<em><b>Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.Binding}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.Binding#getOutPatternElement <em>Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bindings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bindings</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getOutPatternElement_Bindings()
	 * @see anatlyzer.atlext.ATL.Binding#getOutPatternElement
	 * @model opposite="outPatternElement" containment="true"
	 * @generated
	 */
	EList<Binding> getBindings();

	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference.
	 * @see #setModel(OclModel)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getOutPatternElement_Model()
	 * @model ordered="false"
	 * @generated
	 */
	OclModel getModel();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.OutPatternElement#getModel <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(OclModel value);

} // OutPatternElement
