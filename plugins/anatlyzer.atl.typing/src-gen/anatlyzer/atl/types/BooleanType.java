/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.BooleanType#getKindOfTypes <em>Kind Of Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getBooleanType()
 * @model
 * @generated
 */
public interface BooleanType extends PrimitiveType {
	/**
	 * Returns the value of the '<em><b>Kind Of Types</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atl.types.Metaclass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind Of Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind Of Types</em>' reference list.
	 * @see anatlyzer.atl.types.TypesPackage#getBooleanType_KindOfTypes()
	 * @model
	 * @generated
	 */
	EList<Metaclass> getKindOfTypes();

} // BooleanType
