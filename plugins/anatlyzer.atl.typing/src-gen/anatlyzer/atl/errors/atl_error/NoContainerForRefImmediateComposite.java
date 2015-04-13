/**
 */
package anatlyzer.atl.errors.atl_error;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>No Container For Ref Immediate Composite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getClassName <em>Class Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getMetamodelName <em>Metamodel Name</em>}</li>
 * </ul>
 *
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoContainerForRefImmediateComposite()
 * @model annotation="description name='Object without container' text='A call to refImmediateComposite() will *always* result in OclUndefined.' example='\n\t\tGiven a root class, e.g., MyClassModel, the expression\n\t    aClassModel.refImmediateComposite() will always return null.\t\n\t'"
 *        annotation="info prec='static' path='no' severity='runtime-error' when='model-dep' kind='src-typing' phase='typing' source='none'"
 * @generated
 */
public interface NoContainerForRefImmediateComposite extends NavigationProblem {
	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoContainerForRefImmediateComposite_ClassName()
	 * @model required="true"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Metamodel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodel Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metamodel Name</em>' attribute.
	 * @see #setMetamodelName(String)
	 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage#getNoContainerForRefImmediateComposite_MetamodelName()
	 * @model required="true"
	 * @generated
	 */
	String getMetamodelName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite#getMetamodelName <em>Metamodel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metamodel Name</em>' attribute.
	 * @see #getMetamodelName()
	 * @generated
	 */
	void setMetamodelName(String value);

} // NoContainerForRefImmediateComposite
