/**
 */
package anatlyzer.atlext.OCL;

import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolve Temp Resolution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.ResolveTempResolution#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getResolveTempResolution()
 * @model
 * @generated
 */
public interface ResolveTempResolution extends RuleResolutionInfo {
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
