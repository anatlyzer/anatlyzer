/**
 */
package anatlyzer.atlext.OCL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.TupleType#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getTupleType()
 * @model
 * @generated
 */
public interface TupleType extends OclType {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.TupleTypeAttribute}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.TupleTypeAttribute#getTupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see anatlyzer.atlext.OCL.OCLPackage#getTupleType_Attributes()
	 * @see anatlyzer.atlext.OCL.TupleTypeAttribute#getTupleType
	 * @model opposite="tupleType" containment="true"
	 * @generated
	 */
	EList<TupleTypeAttribute> getAttributes();

} // TupleType
