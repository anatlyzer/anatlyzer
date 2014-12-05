/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.EnumType#getName <em>Name</em>}</li>
 *   <li>{@link anatlyzer.atl.types.EnumType#getEenum <em>Eenum</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getEnumType()
 * @model
 * @generated
 */
public interface EnumType extends Type {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see anatlyzer.atl.types.TypesPackage#getEnumType_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.EnumType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Eenum</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eenum</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eenum</em>' reference.
	 * @see #setEenum(EObject)
	 * @see anatlyzer.atl.types.TypesPackage#getEnumType_Eenum()
	 * @model required="true"
	 * @generated
	 */
	EObject getEenum();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.EnumType#getEenum <em>Eenum</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eenum</em>' reference.
	 * @see #getEenum()
	 * @generated
	 */
	void setEenum(EObject value);

} // EnumType
