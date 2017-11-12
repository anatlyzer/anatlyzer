/**
 */
package anatlyzer.atlext.ATL;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.VariableDeclaration;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Callable Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.CallableParameter#getName <em>Name</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.CallableParameter#getStaticType <em>Static Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.CallableParameter#getParamDeclaration <em>Param Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.ATL.ATLPackage#getCallableParameter()
 * @model
 * @generated
 */
public interface CallableParameter extends EObject {
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
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCallableParameter_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.CallableParameter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Type</em>' reference.
	 * @see #setStaticType(Type)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCallableParameter_StaticType()
	 * @model required="true"
	 * @generated
	 */
	Type getStaticType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.CallableParameter#getStaticType <em>Static Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Type</em>' reference.
	 * @see #getStaticType()
	 * @generated
	 */
	void setStaticType(Type value);

	/**
	 * Returns the value of the '<em><b>Param Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Param Declaration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Param Declaration</em>' reference.
	 * @see #setParamDeclaration(VariableDeclaration)
	 * @see anatlyzer.atlext.ATL.ATLPackage#getCallableParameter_ParamDeclaration()
	 * @model
	 * @generated
	 */
	VariableDeclaration getParamDeclaration();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.ATL.CallableParameter#getParamDeclaration <em>Param Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Param Declaration</em>' reference.
	 * @see #getParamDeclaration()
	 * @generated
	 */
	void setParamDeclaration(VariableDeclaration value);

} // CallableParameter
