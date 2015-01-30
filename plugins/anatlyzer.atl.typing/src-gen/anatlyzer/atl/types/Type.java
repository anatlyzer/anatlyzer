/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.types.Type#isMultivalued <em>Multivalued</em>}</li>
 *   <li>{@link anatlyzer.atl.types.Type#getMetamodelRef <em>Metamodel Ref</em>}</li>
 *   <li>{@link anatlyzer.atl.types.Type#getNoCastedType <em>No Casted Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.types.TypesPackage#getType()
 * @model abstract="true"
 * @generated
 */
public interface Type extends EObject {
	/**
	 * Returns the value of the '<em><b>Multivalued</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multivalued</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multivalued</em>' attribute.
	 * @see #setMultivalued(boolean)
	 * @see anatlyzer.atl.types.TypesPackage#getType_Multivalued()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isMultivalued();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.Type#isMultivalued <em>Multivalued</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multivalued</em>' attribute.
	 * @see #isMultivalued()
	 * @generated
	 */
	void setMultivalued(boolean value);

	/**
	 * Returns the value of the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodel Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metamodel Ref</em>' attribute.
	 * @see #setMetamodelRef(Object)
	 * @see anatlyzer.atl.types.TypesPackage#getType_MetamodelRef()
	 * @model transient="true"
	 * @generated
	 */
	Object getMetamodelRef();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.Type#getMetamodelRef <em>Metamodel Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metamodel Ref</em>' attribute.
	 * @see #getMetamodelRef()
	 * @generated
	 */
	void setMetamodelRef(Object value);

	/**
	 * Returns the value of the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No Casted Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No Casted Type</em>' reference.
	 * @see #setNoCastedType(Type)
	 * @see anatlyzer.atl.types.TypesPackage#getType_NoCastedType()
	 * @model required="true"
	 * @generated
	 */
	Type getNoCastedType();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.types.Type#getNoCastedType <em>No Casted Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Casted Type</em>' reference.
	 * @see #getNoCastedType()
	 * @generated
	 */
	void setNoCastedType(Type value);

} // Type
