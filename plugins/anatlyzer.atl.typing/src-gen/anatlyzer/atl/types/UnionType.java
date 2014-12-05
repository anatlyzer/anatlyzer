/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.UnionType#getPossibleTypes <em>Possible Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getUnionType()
 * @model
 * @generated
 */
public interface UnionType extends Type {
	/**
	 * Returns the value of the '<em><b>Possible Types</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atl.types.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Possible Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Possible Types</em>' reference list.
	 * @see anatlyzer.atl.types.TypesPackage#getUnionType_PossibleTypes()
	 * @model
	 * @generated
	 */
	EList<Type> getPossibleTypes();

} // UnionType
