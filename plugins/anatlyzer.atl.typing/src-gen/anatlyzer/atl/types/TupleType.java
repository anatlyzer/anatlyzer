/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.TupleType#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getTupleType()
 * @model
 * @generated
 */
public interface TupleType extends Type {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atl.types.TupleAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see anatlyzer.atl.types.TypesPackage#getTupleType_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<TupleAttribute> getAttributes();

} // TupleType
