/**
 */
package anatlyzer.atlext.ATL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule With Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.RuleWithPattern#getInPattern <em>In Pattern</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.RuleWithPattern#getChildren <em>Children</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.RuleWithPattern#getSuperRule <em>Super Rule</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsRefining <em>Is Refining</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsNoDefault <em>Is No Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleWithPattern()
 * @model abstract="true"
 * @generated
 */
public interface RuleWithPattern extends Rule {
	/**
	 * Returns the value of the '<em><b>In Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Pattern</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Pattern</em>' containment reference.
	 * @see #setInPattern(InPattern)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleWithPattern_InPattern()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	InPattern getInPattern();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.RuleWithPattern#getInPattern <em>In Pattern</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Pattern</em>' containment reference.
	 * @see #getInPattern()
	 * @generated
	 */
	void setInPattern(InPattern value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.RuleWithPattern}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.RuleWithPattern#getSuperRule <em>Super Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleWithPattern_Children()
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#getSuperRule
	 * @model opposite="superRule" ordered="false"
	 * @generated
	 */
	EList<RuleWithPattern> getChildren();

	/**
	 * Returns the value of the '<em><b>Super Rule</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.RuleWithPattern#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Rule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Rule</em>' reference.
	 * @see #setSuperRule(RuleWithPattern)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleWithPattern_SuperRule()
	 * @see anatlyzer.atlext.ATL.RuleWithPattern#getChildren
	 * @model opposite="children" ordered="false"
	 * @generated
	 */
	RuleWithPattern getSuperRule();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.RuleWithPattern#getSuperRule <em>Super Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Rule</em>' reference.
	 * @see #getSuperRule()
	 * @generated
	 */
	void setSuperRule(RuleWithPattern value);

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleWithPattern_IsAbstract()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Refining</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Refining</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Refining</em>' attribute.
	 * @see #setIsRefining(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleWithPattern_IsRefining()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsRefining();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsRefining <em>Is Refining</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Refining</em>' attribute.
	 * @see #isIsRefining()
	 * @generated
	 */
	void setIsRefining(boolean value);

	/**
	 * Returns the value of the '<em><b>Is No Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is No Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is No Default</em>' attribute.
	 * @see #setIsNoDefault(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getRuleWithPattern_IsNoDefault()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	boolean isIsNoDefault();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.RuleWithPattern#isIsNoDefault <em>Is No Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is No Default</em>' attribute.
	 * @see #isIsNoDefault()
	 * @generated
	 */
	void setIsNoDefault(boolean value);

} // RuleWithPattern
