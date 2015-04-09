/**
 */
package anatlyzer.atlext.ATL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.LibraryRef#getUnit <em>Unit</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.LibraryRef#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getLibraryRef()
 * @model
 * @generated
 */
public interface LibraryRef extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Unit</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.Unit#getLibraries <em>Libraries</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' container reference.
	 * @see #setUnit(Unit)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLibraryRef_Unit()
	 * @see anatlyzer.atlext.ATL.Unit#getLibraries
	 * @model opposite="libraries" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Unit getUnit();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.LibraryRef#getUnit <em>Unit</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' container reference.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(Unit value);

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
	 * @see anatlyzer.atlext.ATL.ATLPackage#getLibraryRef_Name()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.LibraryRef#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // LibraryRef
