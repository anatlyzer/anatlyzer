/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constrained Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.ConstrainedType#getRootType <em>Root Type</em>}</li>
 *   <li>{@link anatlyzer.atl.types.ConstrainedType#getIsType <em>Is Type</em>}</li>
 *   <li>{@link anatlyzer.atl.types.ConstrainedType#getIsNotType <em>Is Not Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getConstrainedType()
 * @model
 * @generated
 */
public interface ConstrainedType extends Type {
	/**
	 * Returns the value of the '<em><b>Root Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Type</em>' reference.
	 * @see #setRootType(Type)
	 * @see anatlyzer.atl.types.TypesPackage#getConstrainedType_RootType()
	 * @model required="true"
	 * @generated
	 */
	Type getRootType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.ConstrainedType#getRootType <em>Root Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Type</em>' reference.
	 * @see #getRootType()
	 * @generated
	 */
	void setRootType(Type value);

	/**
	 * Returns the value of the '<em><b>Is Type</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atl.types.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Type</em>' reference list.
	 * @see anatlyzer.atl.types.TypesPackage#getConstrainedType_IsType()
	 * @model
	 * @generated
	 */
	EList<Type> getIsType();

	/**
	 * Returns the value of the '<em><b>Is Not Type</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atl.types.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Not Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Not Type</em>' reference list.
	 * @see anatlyzer.atl.types.TypesPackage#getConstrainedType_IsNotType()
	 * @model
	 * @generated
	 */
	EList<Type> getIsNotType();

} // ConstrainedType
