/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OclFeatureDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Helper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.Helper#getQuery <em>Query</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Helper#getLibrary <em>Library</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Helper#getDefinition <em>Definition</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Helper#isHasContext <em>Has Context</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Helper#isIsAttribute <em>Is Attribute</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Helper#getInferredReturnType <em>Inferred Return Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.Helper#getStaticReturnType <em>Static Return Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper()
 * @model abstract="true"
 * @generated
 */
public interface Helper extends ModuleElement, Callable {
	/**
	 * Returns the value of the '<em><b>Query</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.Query#getHelpers <em>Helpers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query</em>' container reference.
	 * @see #setQuery(Query)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper_Query()
	 * @see anatlyzer.atlext.ATL.Query#getHelpers
	 * @model opposite="helpers" transient="false" ordered="false"
	 * @generated
	 */
	Query getQuery();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Helper#getQuery <em>Query</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' container reference.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(Query value);

	/**
	 * Returns the value of the '<em><b>Library</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.Library#getHelpers <em>Helpers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Library</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Library</em>' container reference.
	 * @see #setLibrary(Library)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper_Library()
	 * @see anatlyzer.atlext.ATL.Library#getHelpers
	 * @model opposite="helpers" transient="false" ordered="false"
	 * @generated
	 */
	Library getLibrary();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Helper#getLibrary <em>Library</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Library</em>' container reference.
	 * @see #getLibrary()
	 * @generated
	 */
	void setLibrary(Library value);

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' containment reference.
	 * @see #setDefinition(OclFeatureDefinition)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper_Definition()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclFeatureDefinition getDefinition();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Helper#getDefinition <em>Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' containment reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(OclFeatureDefinition value);

	/**
	 * Returns the value of the '<em><b>Has Context</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Context</em>' attribute.
	 * @see #setHasContext(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper_HasContext()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isHasContext();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Helper#isHasContext <em>Has Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Context</em>' attribute.
	 * @see #isHasContext()
	 * @generated
	 */
	void setHasContext(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Attribute</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Attribute</em>' attribute.
	 * @see #setIsAttribute(boolean)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper_IsAttribute()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsAttribute();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Helper#isIsAttribute <em>Is Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Attribute</em>' attribute.
	 * @see #isIsAttribute()
	 * @generated
	 */
	void setIsAttribute(boolean value);

	/**
	 * Returns the value of the '<em><b>Inferred Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inferred Return Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inferred Return Type</em>' reference.
	 * @see #setInferredReturnType(Type)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper_InferredReturnType()
	 * @model
	 * @generated
	 */
	Type getInferredReturnType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Helper#getInferredReturnType <em>Inferred Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inferred Return Type</em>' reference.
	 * @see #getInferredReturnType()
	 * @generated
	 */
	void setInferredReturnType(Type value);

	/**
	 * Returns the value of the '<em><b>Static Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Return Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Return Type</em>' reference.
	 * @see #setStaticReturnType(Type)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getHelper_StaticReturnType()
	 * @model
	 * @generated
	 */
	Type getStaticReturnType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.Helper#getStaticReturnType <em>Static Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Return Type</em>' reference.
	 * @see #getStaticReturnType()
	 * @generated
	 */
	void setStaticReturnType(Type value);

} // Helper
