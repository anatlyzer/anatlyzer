/**
 */
package anatlyzer.atl.errors.ide_error;

import anatlyzer.atl.errors.atl_error.LocalProblem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Could Not Load Metamodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.ide_error.IdeErrorPackage#getCouldNotLoadMetamodel()
 * @model
 * @generated
 */
public interface CouldNotLoadMetamodel extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see anatlyzer.atl.errors.ide_error.IdeErrorPackage#getCouldNotLoadMetamodel_Uri()
	 * @model required="true"
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

} // CouldNotLoadMetamodel
