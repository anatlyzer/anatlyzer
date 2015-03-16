/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OclExpression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.Binding#getValue <em>Value</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Binding#getOutPatternElement <em>Out Pattern Element</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Binding#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Binding#isIsAssignment <em>Is Assignment</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Binding#getWrittenFeature <em>Written Feature</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Binding#getLeftType <em>Left Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Binding#getResolvedBy <em>Resolved By</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding()
 * @model
 * @generated
 */
public interface Binding extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(OclExpression)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding_Value()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getValue();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Binding#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Out Pattern Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.OutPatternElement#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Pattern Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Pattern Element</em>' container reference.
	 * @see #setOutPatternElement(OutPatternElement)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding_OutPatternElement()
	 * @see anatlyzer.atlext.ATL.OutPatternElement#getBindings
	 * @model opposite="bindings" required="true" transient="false" ordered="false"
	 * @generated
	 */
	OutPatternElement getOutPatternElement();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Binding#getOutPatternElement <em>Out Pattern Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Pattern Element</em>' container reference.
	 * @see #getOutPatternElement()
	 * @generated
	 */
	void setOutPatternElement(OutPatternElement value);

	/**
	 * Returns the value of the '<em><b>Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Name</em>' attribute.
	 * @see #setPropertyName(String)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding_PropertyName()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getPropertyName();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Binding#getPropertyName <em>Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Name</em>' attribute.
	 * @see #getPropertyName()
	 * @generated
	 */
	void setPropertyName(String value);

	/**
	 * Returns the value of the '<em><b>Is Assignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Assignment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Assignment</em>' attribute.
	 * @see #setIsAssignment(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding_IsAssignment()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsAssignment();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Binding#isIsAssignment <em>Is Assignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Assignment</em>' attribute.
	 * @see #isIsAssignment()
	 * @generated
	 */
	void setIsAssignment(boolean value);

	/**
	 * Returns the value of the '<em><b>Written Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Written Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Written Feature</em>' reference.
	 * @see #setWrittenFeature(EObject)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding_WrittenFeature()
	 * @model required="true"
	 * @generated
	 */
	EObject getWrittenFeature();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Binding#getWrittenFeature <em>Written Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Written Feature</em>' reference.
	 * @see #getWrittenFeature()
	 * @generated
	 */
	void setWrittenFeature(EObject value);

	/**
	 * Returns the value of the '<em><b>Left Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Type</em>' reference.
	 * @see #setLeftType(Type)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding_LeftType()
	 * @model required="true"
	 * @generated
	 */
	Type getLeftType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Binding#getLeftType <em>Left Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Type</em>' reference.
	 * @see #getLeftType()
	 * @generated
	 */
	void setLeftType(Type value);

	/**
	 * Returns the value of the '<em><b>Resolved By</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.RuleResolutionInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolved By</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolved By</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getBinding_ResolvedBy()
	 * @model containment="true"
	 * @generated
	 */
	EList<RuleResolutionInfo> getResolvedBy();

} // Binding
