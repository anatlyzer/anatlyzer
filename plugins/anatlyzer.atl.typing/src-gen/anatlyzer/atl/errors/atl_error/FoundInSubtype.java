/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Found In Subtype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FoundInSubtype#getPossibleClasses <em>Possible Classes</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.FoundInSubtype#getMissingClasses <em>Missing Classes</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFoundInSubtype()
 * @model abstract="true"
 * @generated
 */
public interface FoundInSubtype extends EObject {
	/**
	 * Returns the value of the '<em><b>Possible Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Possible Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Possible Classes</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFoundInSubtype_PossibleClasses()
	 * @model
	 * @generated
	 */
	EList<EClass> getPossibleClasses();

	/**
	 * Returns the value of the '<em><b>Missing Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Missing Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Missing Classes</em>' reference list.
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getFoundInSubtype_MissingClasses()
	 * @model
	 * @generated
	 */
	EList<EClass> getMissingClasses();

} // FoundInSubtype
