/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.PropertyCallExp;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context Helper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.ContextHelper#getContextType <em>Context Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.ContextHelper#getPolymorphicCalledBy <em>Polymorphic Called By</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getContextHelper()
 * @model
 * @generated
 */
public interface ContextHelper extends Helper {

	/**
	 * Returns the value of the '<em><b>Context Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context Type</em>' reference.
	 * @see #setContextType(Type)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getContextHelper_ContextType()
	 * @model required="true"
	 * @generated
	 */
	Type getContextType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.ContextHelper#getContextType <em>Context Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context Type</em>' reference.
	 * @see #getContextType()
	 * @generated
	 */
	void setContextType(Type value);

	/**
	 * Returns the value of the '<em><b>Polymorphic Called By</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.PropertyCallExp}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.PropertyCallExp#getDynamicResolvers <em>Dynamic Resolvers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Polymorphic Called By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Polymorphic Called By</em>' reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getContextHelper_PolymorphicCalledBy()
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#getDynamicResolvers
	 * @model opposite="dynamicResolvers"
	 * @generated
	 */
	EList<PropertyCallExp> getPolymorphicCalledBy();
} // ContextHelper
