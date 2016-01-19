/**
 */
package anatlyzer.atl.impact;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Change Impact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.impact.ChangeImpact#getChanges <em>Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.impact.ImpactPackage#getChangeImpact()
 * @model
 * @generated
 */
public interface ChangeImpact extends EObject {

	/**
	 * Returns the value of the '<em><b>Changes</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atl.impact.Change}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changes</em>' containment reference list.
	 * @see anatlyzer.atl.impact.ImpactPackage#getChangeImpact_Changes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Change> getChanges();
} // ChangeImpact
