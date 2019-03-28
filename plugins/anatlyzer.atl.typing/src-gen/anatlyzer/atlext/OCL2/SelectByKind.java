/**
 */
package anatlyzer.atlext.OCL2;

import anatlyzer.atlext.OCL.CollectionOperationCallExp;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select By Kind</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL2.SelectByKind#isIsExact <em>Is Exact</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atlext.OCL2.OCL2Package#getSelectByKind()
 * @model
 * @generated
 */
public interface SelectByKind extends CollectionOperationCallExp {

	/**
	 * Returns the value of the '<em><b>Is Exact</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Exact</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Exact</em>' attribute.
	 * @see #setIsExact(boolean)
	 * @see anatlyzer.atlext.OCL2.OCL2Package#getSelectByKind_IsExact()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsExact();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL2.SelectByKind#isIsExact <em>Is Exact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Exact</em>' attribute.
	 * @see #isIsExact()
	 * @generated
	 */
	void setIsExact(boolean value);
} // SelectByKind
