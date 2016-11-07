/**
 */
package anatlyzer.atl.errors.ide_error;

import anatlyzer.atl.errors.atl_error.LocalProblem;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Precondition Parse Error</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.ide_error.PreconditionParseError#getMessages <em>Messages</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.ide_error.IdeErrorPackage#getPreconditionParseError()
 * @model
 * @generated
 */
public interface PreconditionParseError extends LocalProblem {
	/**
	 * Returns the value of the '<em><b>Messages</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Messages</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Messages</em>' attribute list.
	 * @see anatlyzer.atl.errors.ide_error.IdeErrorPackage#getPreconditionParseError_Messages()
	 * @model
	 * @generated
	 */
	EList<String> getMessages();

} // PreconditionParseError
