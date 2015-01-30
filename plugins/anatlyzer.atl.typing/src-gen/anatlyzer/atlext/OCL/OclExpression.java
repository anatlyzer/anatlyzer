/**
 */
package anatlyzer.atlext.OCL;

import anatlyzer.atlext.ATL.LocatedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getType <em>Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getIfExp3 <em>If Exp3</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getAppliedProperty <em>Applied Property</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getCollection <em>Collection</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getLetExp <em>Let Exp</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getLoopExp <em>Loop Exp</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getParentOperation <em>Parent Operation</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getInitializedVariable <em>Initialized Variable</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getIfExp2 <em>If Exp2</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getOwningOperation <em>Owning Operation</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getIfExp1 <em>If Exp1</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#getOwningAttribute <em>Owning Attribute</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.OclExpression#isImplicitlyCasted <em>Implicitly Casted</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression()
 * @model abstract="true"
 * @generated
 */
public interface OclExpression extends LocatedElement, TypedElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.OclType#getOclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(OclType)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_Type()
	 * @see anatlyzer.atlext.OCL.OclType#getOclExpression
	 * @model opposite="oclExpression" containment="true" ordered="false"
	 * @generated
	 */
	OclType getType();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(OclType value);

	/**
	 * Returns the value of the '<em><b>If Exp3</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.IfExp#getElseExpression <em>Else Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>If Exp3</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Exp3</em>' container reference.
	 * @see #setIfExp3(IfExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_IfExp3()
	 * @see anatlyzer.atlext.OCL.IfExp#getElseExpression
	 * @model opposite="elseExpression" transient="false" ordered="false"
	 * @generated
	 */
	IfExp getIfExp3();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getIfExp3 <em>If Exp3</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Exp3</em>' container reference.
	 * @see #getIfExp3()
	 * @generated
	 */
	void setIfExp3(IfExp value);

	/**
	 * Returns the value of the '<em><b>Applied Property</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.PropertyCallExp#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applied Property</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applied Property</em>' container reference.
	 * @see #setAppliedProperty(PropertyCallExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_AppliedProperty()
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#getSource
	 * @model opposite="source" transient="false" ordered="false"
	 * @generated
	 */
	PropertyCallExp getAppliedProperty();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getAppliedProperty <em>Applied Property</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applied Property</em>' container reference.
	 * @see #getAppliedProperty()
	 * @generated
	 */
	void setAppliedProperty(PropertyCallExp value);

	/**
	 * Returns the value of the '<em><b>Collection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.CollectionExp#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection</em>' container reference.
	 * @see #setCollection(CollectionExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_Collection()
	 * @see anatlyzer.atlext.OCL.CollectionExp#getElements
	 * @model opposite="elements" transient="false" ordered="false"
	 * @generated
	 */
	CollectionExp getCollection();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getCollection <em>Collection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection</em>' container reference.
	 * @see #getCollection()
	 * @generated
	 */
	void setCollection(CollectionExp value);

	/**
	 * Returns the value of the '<em><b>Let Exp</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.LetExp#getIn_ <em>In </em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Let Exp</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Let Exp</em>' container reference.
	 * @see #setLetExp(LetExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_LetExp()
	 * @see anatlyzer.atlext.OCL.LetExp#getIn_
	 * @model opposite="in_" transient="false" ordered="false"
	 * @generated
	 */
	LetExp getLetExp();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getLetExp <em>Let Exp</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Let Exp</em>' container reference.
	 * @see #getLetExp()
	 * @generated
	 */
	void setLetExp(LetExp value);

	/**
	 * Returns the value of the '<em><b>Loop Exp</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.LoopExp#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Exp</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Exp</em>' container reference.
	 * @see #setLoopExp(LoopExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_LoopExp()
	 * @see anatlyzer.atlext.OCL.LoopExp#getBody
	 * @model opposite="body" transient="false" ordered="false"
	 * @generated
	 */
	LoopExp getLoopExp();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getLoopExp <em>Loop Exp</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Exp</em>' container reference.
	 * @see #getLoopExp()
	 * @generated
	 */
	void setLoopExp(LoopExp value);

	/**
	 * Returns the value of the '<em><b>Parent Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.OperationCallExp#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Operation</em>' container reference.
	 * @see #setParentOperation(OperationCallExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_ParentOperation()
	 * @see anatlyzer.atlext.OCL.OperationCallExp#getArguments
	 * @model opposite="arguments" transient="false" ordered="false"
	 * @generated
	 */
	OperationCallExp getParentOperation();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getParentOperation <em>Parent Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Operation</em>' container reference.
	 * @see #getParentOperation()
	 * @generated
	 */
	void setParentOperation(OperationCallExp value);

	/**
	 * Returns the value of the '<em><b>Initialized Variable</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.VariableDeclaration#getInitExpression <em>Init Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initialized Variable</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initialized Variable</em>' container reference.
	 * @see #setInitializedVariable(VariableDeclaration)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_InitializedVariable()
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getInitExpression
	 * @model opposite="initExpression" transient="false" ordered="false"
	 * @generated
	 */
	VariableDeclaration getInitializedVariable();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getInitializedVariable <em>Initialized Variable</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initialized Variable</em>' container reference.
	 * @see #getInitializedVariable()
	 * @generated
	 */
	void setInitializedVariable(VariableDeclaration value);

	/**
	 * Returns the value of the '<em><b>If Exp2</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.IfExp#getThenExpression <em>Then Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>If Exp2</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Exp2</em>' container reference.
	 * @see #setIfExp2(IfExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_IfExp2()
	 * @see anatlyzer.atlext.OCL.IfExp#getThenExpression
	 * @model opposite="thenExpression" transient="false" ordered="false"
	 * @generated
	 */
	IfExp getIfExp2();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getIfExp2 <em>If Exp2</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Exp2</em>' container reference.
	 * @see #getIfExp2()
	 * @generated
	 */
	void setIfExp2(IfExp value);

	/**
	 * Returns the value of the '<em><b>Owning Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.Operation#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Operation</em>' container reference.
	 * @see #setOwningOperation(Operation)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_OwningOperation()
	 * @see anatlyzer.atlext.OCL.Operation#getBody
	 * @model opposite="body" transient="false" ordered="false"
	 * @generated
	 */
	Operation getOwningOperation();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getOwningOperation <em>Owning Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Operation</em>' container reference.
	 * @see #getOwningOperation()
	 * @generated
	 */
	void setOwningOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>If Exp1</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.IfExp#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>If Exp1</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Exp1</em>' container reference.
	 * @see #setIfExp1(IfExp)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_IfExp1()
	 * @see anatlyzer.atlext.OCL.IfExp#getCondition
	 * @model opposite="condition" transient="false" ordered="false"
	 * @generated
	 */
	IfExp getIfExp1();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getIfExp1 <em>If Exp1</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Exp1</em>' container reference.
	 * @see #getIfExp1()
	 * @generated
	 */
	void setIfExp1(IfExp value);

	/**
	 * Returns the value of the '<em><b>Owning Attribute</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.Attribute#getInitExpression <em>Init Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Attribute</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Attribute</em>' container reference.
	 * @see #setOwningAttribute(Attribute)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_OwningAttribute()
	 * @see anatlyzer.atlext.OCL.Attribute#getInitExpression
	 * @model opposite="initExpression" transient="false" ordered="false"
	 * @generated
	 */
	Attribute getOwningAttribute();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#getOwningAttribute <em>Owning Attribute</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Attribute</em>' container reference.
	 * @see #getOwningAttribute()
	 * @generated
	 */
	void setOwningAttribute(Attribute value);

	/**
	 * Returns the value of the '<em><b>Implicitly Casted</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implicitly Casted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implicitly Casted</em>' attribute.
	 * @see #setImplicitlyCasted(boolean)
	 * @see anatlyzer.atlext.OCL.OCLPackage#getOclExpression_ImplicitlyCasted()
	 * @model default="false"
	 * @generated
	 */
	boolean isImplicitlyCasted();

	/**
	 * Sets the value of the '{@link anatlyzer.atlext.OCL.OclExpression#isImplicitlyCasted <em>Implicitly Casted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implicitly Casted</em>' attribute.
	 * @see #isImplicitlyCasted()
	 * @generated
	 */
	void setImplicitlyCasted(boolean value);

} // OclExpression
