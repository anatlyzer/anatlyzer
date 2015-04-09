/**
 */
package anatlyzer.atlext.OCL;

import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolve Temp Resolution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.ResolveTempResolution#getRule <em>Rule</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.ResolveTempResolution#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getResolveTempResolution()
 * @model
 * @generated
 */
public interface ResolveTempResolution extends EObject {
	/**
	 * Returns the value of the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule</em>' reference.
	 * @see #setRule(MatchedRule)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getResolveTempResolution_Rule()
	 * @model required="true"
	 * @generated
	 */
	MatchedRule getRule();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.ResolveTempResolution#getRule <em>Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule</em>' reference.
	 * @see #getRule()
	 * @generated
	 */
	void setRule(MatchedRule value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(OutPatternElement)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getResolveTempResolution_Element()
	 * @model required="true"
	 * @generated
	 */
	OutPatternElement getElement();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.ResolveTempResolution#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(OutPatternElement value);

} // ResolveTempResolution
