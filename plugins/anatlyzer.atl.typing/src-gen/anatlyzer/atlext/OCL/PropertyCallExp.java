/**
 */
package anatlyzer.atlext.OCL;

import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.ContextHelper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.PropertyCallExp#getSource <em>Source</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.PropertyCallExp#getUsedFeature <em>Used Feature</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.PropertyCallExp#getReceptorType <em>Receptor Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.PropertyCallExp#isIsStaticCall <em>Is Static Call</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.PropertyCallExp#getStaticResolver <em>Static Resolver</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.PropertyCallExp#getDynamicResolvers <em>Dynamic Resolvers</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getPropertyCallExp()
 * @model abstract="true"
 * @generated
 */
public interface PropertyCallExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.OclExpression#getAppliedProperty <em>Applied Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(OclExpression)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getPropertyCallExp_Source()
	 * @see anatlyzer.atlext.OCL.OclExpression#getAppliedProperty
	 * @model opposite="appliedProperty" containment="true" required="true" ordered="false"
	 * @generated
	 */
	OclExpression getSource();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.PropertyCallExp#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OclExpression value);

	/**
	 * Returns the value of the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Feature</em>' reference.
	 * @see #setUsedFeature(EObject)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getPropertyCallExp_UsedFeature()
	 * @model
	 * @generated
	 */
	EObject getUsedFeature();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.PropertyCallExp#getUsedFeature <em>Used Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used Feature</em>' reference.
	 * @see #getUsedFeature()
	 * @generated
	 */
	void setUsedFeature(EObject value);

	/**
	 * Returns the value of the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receptor Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Receptor Type</em>' reference.
	 * @see #setReceptorType(EObject)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getPropertyCallExp_ReceptorType()
	 * @model
	 * @generated
	 */
	EObject getReceptorType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.PropertyCallExp#getReceptorType <em>Receptor Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receptor Type</em>' reference.
	 * @see #getReceptorType()
	 * @generated
	 */
	void setReceptorType(EObject value);

	/**
	 * Returns the value of the '<em><b>Is Static Call</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Static Call</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Static Call</em>' attribute.
	 * @see #setIsStaticCall(boolean)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getPropertyCallExp_IsStaticCall()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIsStaticCall();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.PropertyCallExp#isIsStaticCall <em>Is Static Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Static Call</em>' attribute.
	 * @see #isIsStaticCall()
	 * @generated
	 */
	void setIsStaticCall(boolean value);

	/**
	 * Returns the value of the '<em><b>Static Resolver</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.Callable#getCalledBy <em>Called By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Resolver</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Resolver</em>' reference.
	 * @see #setStaticResolver(Callable)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getPropertyCallExp_StaticResolver()
	 * @see anatlyzer.atlext.ATL.Callable#getCalledBy
	 * @model opposite="calledBy" required="true"
	 * @generated
	 */
	Callable getStaticResolver();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.PropertyCallExp#getStaticResolver <em>Static Resolver</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Resolver</em>' reference.
	 * @see #getStaticResolver()
	 * @generated
	 */
	void setStaticResolver(Callable value);

	/**
	 * Returns the value of the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * The list contents are of type {@link anatlyzer.atlext.ATL.ContextHelper}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.ATL.ContextHelper#getPolymorphicCalledBy <em>Polymorphic Called By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Resolvers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Resolvers</em>' reference list.
	 * @see anatlyzer.atlext.OCL.OCLPackage#getPropertyCallExp_DynamicResolvers()
	 * @see anatlyzer.atlext.ATL.ContextHelper#getPolymorphicCalledBy
	 * @model opposite="polymorphicCalledBy"
	 * @generated
	 */
	EList<ContextHelper> getDynamicResolvers();

} // PropertyCallExp
