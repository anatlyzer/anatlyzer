/**
 */
package anatlyzer.atlext.OCL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.OperationCallExp#getArguments <em>Arguments</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OperationCallExp#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OperationCallExp#getResolveTempResolvedBy <em>Resolve Temp Resolved By</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getOperationCallExp()
 * @model
 * @generated
 */
public interface OperationCallExp extends PropertyCallExp {
	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.OclExpression}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.OclExpression#getParentOperation <em>Parent Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOperationCallExp_Arguments()
	 * @see anatlyzer.atlext.OCL.OclExpression#getParentOperation
	 * @model opposite="parentOperation" containment="true"
	 * @generated
	 */
	EList<OclExpression> getArguments();

	/**
	 * Returns the value of the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Name</em>' attribute.
	 * @see #setOperationName(String)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOperationCallExp_OperationName()
	 * @model unique="false" dataType="anatlyzer.atlext.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getOperationName();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OperationCallExp#getOperationName <em>Operation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Name</em>' attribute.
	 * @see #getOperationName()
	 * @generated
	 */
	void setOperationName(String value);

	/**
	 * Returns the value of the '<em><b>Resolve Temp Resolved By</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.ResolveTempResolution}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolve Temp Resolved By</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolve Temp Resolved By</em>' containment reference list.
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOperationCallExp_ResolveTempResolvedBy()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResolveTempResolution> getResolveTempResolvedBy();

} // OperationCallExp
