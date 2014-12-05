/**
 */
package anatlyzer.atl.types;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.CollectionType#getContainedType <em>Contained Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getCollectionType()
 * @model abstract="true"
 * @generated
 */
public interface CollectionType extends Type {
	/**
	 * Returns the value of the '<em><b>Contained Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Type</em>' reference.
	 * @see #setContainedType(Type)
	 * @see anatlyzer.atl.types.TypesPackage#getCollectionType_ContainedType()
	 * @model required="true"
	 * @generated
	 */
	Type getContainedType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.CollectionType#getContainedType <em>Contained Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contained Type</em>' reference.
	 * @see #getContainedType()
	 * @generated
	 */
	void setContainedType(Type value);

} // CollectionType
