/**
 */
package anatlyzer.atlext.ATL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.Library#getHelpers <em>Helpers</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library extends Unit {
	/**
	 * Returns the value of the '<em><b>Helpers</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.Helper}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.Helper#getLibrary <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Helpers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Helpers</em>' containment reference list.
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLibrary_Helpers()
	 * @see anatlyzer.atlext.ATL.Helper#getLibrary
	 * @model opposite="library" containment="true"
	 * @generated
	 */
	EList<Helper> getHelpers();

} // Library
