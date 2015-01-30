/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atl.types.Type;

import anatlyzer.atlext.ATL.impl.LocatedElementImpl;

import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.TypedElement;
import anatlyzer.atlext.OCL.VariableDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ocl Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getInferredType <em>Inferred Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getType <em>Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getIfExp3 <em>If Exp3</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getAppliedProperty <em>Applied Property</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getCollection <em>Collection</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getLetExp <em>Let Exp</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getLoopExp <em>Loop Exp</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getParentOperation <em>Parent Operation</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getInitializedVariable <em>Initialized Variable</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getIfExp2 <em>If Exp2</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getOwningOperation <em>Owning Operation</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getIfExp1 <em>If Exp1</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#getOwningAttribute <em>Owning Attribute</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl#isImplicitlyCasted <em>Implicitly Casted</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OclExpressionImpl extends LocatedElementImpl implements OclExpression {
	/**
	 * The cached value of the '{@link #getInferredType() <em>Inferred Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInferredType()
	 * @generated
	 * @ordered
	 */
	protected Type inferredType;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected OclType type;

	/**
	 * The default value of the '{@link #isImplicitlyCasted() <em>Implicitly Casted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplicitlyCasted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMPLICITLY_CASTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImplicitlyCasted() <em>Implicitly Casted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplicitlyCasted()
	 * @generated
	 * @ordered
	 */
	protected boolean implicitlyCasted = IMPLICITLY_CASTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OclExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.OCL_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(OclType newType, NotificationChain msgs) {
		OclType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(OclType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, OCLPackage.OCL_TYPE__OCL_EXPRESSION, OclType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, OCLPackage.OCL_TYPE__OCL_EXPRESSION, OclType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfExp getIfExp3() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__IF_EXP3) return null;
		return (IfExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIfExp3(IfExp newIfExp3, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newIfExp3, OCLPackage.OCL_EXPRESSION__IF_EXP3, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIfExp3(IfExp newIfExp3) {
		if (newIfExp3 != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__IF_EXP3 && newIfExp3 != null)) {
			if (EcoreUtil.isAncestor(this, newIfExp3))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newIfExp3 != null)
				msgs = ((InternalEObject)newIfExp3).eInverseAdd(this, OCLPackage.IF_EXP__ELSE_EXPRESSION, IfExp.class, msgs);
			msgs = basicSetIfExp3(newIfExp3, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__IF_EXP3, newIfExp3, newIfExp3));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyCallExp getAppliedProperty() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY) return null;
		return (PropertyCallExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAppliedProperty(PropertyCallExp newAppliedProperty, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAppliedProperty, OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAppliedProperty(PropertyCallExp newAppliedProperty) {
		if (newAppliedProperty != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY && newAppliedProperty != null)) {
			if (EcoreUtil.isAncestor(this, newAppliedProperty))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAppliedProperty != null)
				msgs = ((InternalEObject)newAppliedProperty).eInverseAdd(this, OCLPackage.PROPERTY_CALL_EXP__SOURCE, PropertyCallExp.class, msgs);
			msgs = basicSetAppliedProperty(newAppliedProperty, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY, newAppliedProperty, newAppliedProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionExp getCollection() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__COLLECTION) return null;
		return (CollectionExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCollection(CollectionExp newCollection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCollection, OCLPackage.OCL_EXPRESSION__COLLECTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollection(CollectionExp newCollection) {
		if (newCollection != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__COLLECTION && newCollection != null)) {
			if (EcoreUtil.isAncestor(this, newCollection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCollection != null)
				msgs = ((InternalEObject)newCollection).eInverseAdd(this, OCLPackage.COLLECTION_EXP__ELEMENTS, CollectionExp.class, msgs);
			msgs = basicSetCollection(newCollection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__COLLECTION, newCollection, newCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LetExp getLetExp() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__LET_EXP) return null;
		return (LetExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLetExp(LetExp newLetExp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLetExp, OCLPackage.OCL_EXPRESSION__LET_EXP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLetExp(LetExp newLetExp) {
		if (newLetExp != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__LET_EXP && newLetExp != null)) {
			if (EcoreUtil.isAncestor(this, newLetExp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLetExp != null)
				msgs = ((InternalEObject)newLetExp).eInverseAdd(this, OCLPackage.LET_EXP__IN_, LetExp.class, msgs);
			msgs = basicSetLetExp(newLetExp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__LET_EXP, newLetExp, newLetExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoopExp getLoopExp() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__LOOP_EXP) return null;
		return (LoopExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopExp(LoopExp newLoopExp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLoopExp, OCLPackage.OCL_EXPRESSION__LOOP_EXP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopExp(LoopExp newLoopExp) {
		if (newLoopExp != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__LOOP_EXP && newLoopExp != null)) {
			if (EcoreUtil.isAncestor(this, newLoopExp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLoopExp != null)
				msgs = ((InternalEObject)newLoopExp).eInverseAdd(this, OCLPackage.LOOP_EXP__BODY, LoopExp.class, msgs);
			msgs = basicSetLoopExp(newLoopExp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__LOOP_EXP, newLoopExp, newLoopExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationCallExp getParentOperation() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__PARENT_OPERATION) return null;
		return (OperationCallExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentOperation(OperationCallExp newParentOperation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParentOperation, OCLPackage.OCL_EXPRESSION__PARENT_OPERATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentOperation(OperationCallExp newParentOperation) {
		if (newParentOperation != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__PARENT_OPERATION && newParentOperation != null)) {
			if (EcoreUtil.isAncestor(this, newParentOperation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentOperation != null)
				msgs = ((InternalEObject)newParentOperation).eInverseAdd(this, OCLPackage.OPERATION_CALL_EXP__ARGUMENTS, OperationCallExp.class, msgs);
			msgs = basicSetParentOperation(newParentOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__PARENT_OPERATION, newParentOperation, newParentOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration getInitializedVariable() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE) return null;
		return (VariableDeclaration)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitializedVariable(VariableDeclaration newInitializedVariable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInitializedVariable, OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitializedVariable(VariableDeclaration newInitializedVariable) {
		if (newInitializedVariable != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE && newInitializedVariable != null)) {
			if (EcoreUtil.isAncestor(this, newInitializedVariable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInitializedVariable != null)
				msgs = ((InternalEObject)newInitializedVariable).eInverseAdd(this, OCLPackage.VARIABLE_DECLARATION__INIT_EXPRESSION, VariableDeclaration.class, msgs);
			msgs = basicSetInitializedVariable(newInitializedVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE, newInitializedVariable, newInitializedVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfExp getIfExp2() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__IF_EXP2) return null;
		return (IfExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIfExp2(IfExp newIfExp2, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newIfExp2, OCLPackage.OCL_EXPRESSION__IF_EXP2, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIfExp2(IfExp newIfExp2) {
		if (newIfExp2 != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__IF_EXP2 && newIfExp2 != null)) {
			if (EcoreUtil.isAncestor(this, newIfExp2))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newIfExp2 != null)
				msgs = ((InternalEObject)newIfExp2).eInverseAdd(this, OCLPackage.IF_EXP__THEN_EXPRESSION, IfExp.class, msgs);
			msgs = basicSetIfExp2(newIfExp2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__IF_EXP2, newIfExp2, newIfExp2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOwningOperation() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__OWNING_OPERATION) return null;
		return (Operation)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningOperation(Operation newOwningOperation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningOperation, OCLPackage.OCL_EXPRESSION__OWNING_OPERATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningOperation(Operation newOwningOperation) {
		if (newOwningOperation != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__OWNING_OPERATION && newOwningOperation != null)) {
			if (EcoreUtil.isAncestor(this, newOwningOperation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningOperation != null)
				msgs = ((InternalEObject)newOwningOperation).eInverseAdd(this, OCLPackage.OPERATION__BODY, Operation.class, msgs);
			msgs = basicSetOwningOperation(newOwningOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__OWNING_OPERATION, newOwningOperation, newOwningOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfExp getIfExp1() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__IF_EXP1) return null;
		return (IfExp)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIfExp1(IfExp newIfExp1, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newIfExp1, OCLPackage.OCL_EXPRESSION__IF_EXP1, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIfExp1(IfExp newIfExp1) {
		if (newIfExp1 != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__IF_EXP1 && newIfExp1 != null)) {
			if (EcoreUtil.isAncestor(this, newIfExp1))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newIfExp1 != null)
				msgs = ((InternalEObject)newIfExp1).eInverseAdd(this, OCLPackage.IF_EXP__CONDITION, IfExp.class, msgs);
			msgs = basicSetIfExp1(newIfExp1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__IF_EXP1, newIfExp1, newIfExp1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute getOwningAttribute() {
		if (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE) return null;
		return (Attribute)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningAttribute(Attribute newOwningAttribute, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningAttribute, OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningAttribute(Attribute newOwningAttribute) {
		if (newOwningAttribute != eInternalContainer() || (eContainerFeatureID() != OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE && newOwningAttribute != null)) {
			if (EcoreUtil.isAncestor(this, newOwningAttribute))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningAttribute != null)
				msgs = ((InternalEObject)newOwningAttribute).eInverseAdd(this, OCLPackage.ATTRIBUTE__INIT_EXPRESSION, Attribute.class, msgs);
			msgs = basicSetOwningAttribute(newOwningAttribute, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE, newOwningAttribute, newOwningAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImplicitlyCasted() {
		return implicitlyCasted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplicitlyCasted(boolean newImplicitlyCasted) {
		boolean oldImplicitlyCasted = implicitlyCasted;
		implicitlyCasted = newImplicitlyCasted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__IMPLICITLY_CASTED, oldImplicitlyCasted, implicitlyCasted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getInferredType() {
		if (inferredType != null && inferredType.eIsProxy()) {
			InternalEObject oldInferredType = (InternalEObject)inferredType;
			inferredType = (Type)eResolveProxy(oldInferredType);
			if (inferredType != oldInferredType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLPackage.OCL_EXPRESSION__INFERRED_TYPE, oldInferredType, inferredType));
			}
		}
		return inferredType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetInferredType() {
		return inferredType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInferredType(Type newInferredType) {
		Type oldInferredType = inferredType;
		inferredType = newInferredType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_EXPRESSION__INFERRED_TYPE, oldInferredType, inferredType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OCLPackage.OCL_EXPRESSION__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OCLPackage.OCL_EXPRESSION__TYPE, null, msgs);
				return basicSetType((OclType)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP3:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetIfExp3((IfExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAppliedProperty((PropertyCallExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__COLLECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCollection((CollectionExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__LET_EXP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLetExp((LetExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__LOOP_EXP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLoopExp((LoopExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__PARENT_OPERATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentOperation((OperationCallExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInitializedVariable((VariableDeclaration)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP2:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetIfExp2((IfExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__OWNING_OPERATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningOperation((Operation)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP1:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetIfExp1((IfExp)otherEnd, msgs);
			case OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningAttribute((Attribute)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OCLPackage.OCL_EXPRESSION__TYPE:
				return basicSetType(null, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP3:
				return basicSetIfExp3(null, msgs);
			case OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY:
				return basicSetAppliedProperty(null, msgs);
			case OCLPackage.OCL_EXPRESSION__COLLECTION:
				return basicSetCollection(null, msgs);
			case OCLPackage.OCL_EXPRESSION__LET_EXP:
				return basicSetLetExp(null, msgs);
			case OCLPackage.OCL_EXPRESSION__LOOP_EXP:
				return basicSetLoopExp(null, msgs);
			case OCLPackage.OCL_EXPRESSION__PARENT_OPERATION:
				return basicSetParentOperation(null, msgs);
			case OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE:
				return basicSetInitializedVariable(null, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP2:
				return basicSetIfExp2(null, msgs);
			case OCLPackage.OCL_EXPRESSION__OWNING_OPERATION:
				return basicSetOwningOperation(null, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP1:
				return basicSetIfExp1(null, msgs);
			case OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE:
				return basicSetOwningAttribute(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case OCLPackage.OCL_EXPRESSION__IF_EXP3:
				return eInternalContainer().eInverseRemove(this, OCLPackage.IF_EXP__ELSE_EXPRESSION, IfExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY:
				return eInternalContainer().eInverseRemove(this, OCLPackage.PROPERTY_CALL_EXP__SOURCE, PropertyCallExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__COLLECTION:
				return eInternalContainer().eInverseRemove(this, OCLPackage.COLLECTION_EXP__ELEMENTS, CollectionExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__LET_EXP:
				return eInternalContainer().eInverseRemove(this, OCLPackage.LET_EXP__IN_, LetExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__LOOP_EXP:
				return eInternalContainer().eInverseRemove(this, OCLPackage.LOOP_EXP__BODY, LoopExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__PARENT_OPERATION:
				return eInternalContainer().eInverseRemove(this, OCLPackage.OPERATION_CALL_EXP__ARGUMENTS, OperationCallExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE:
				return eInternalContainer().eInverseRemove(this, OCLPackage.VARIABLE_DECLARATION__INIT_EXPRESSION, VariableDeclaration.class, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP2:
				return eInternalContainer().eInverseRemove(this, OCLPackage.IF_EXP__THEN_EXPRESSION, IfExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__OWNING_OPERATION:
				return eInternalContainer().eInverseRemove(this, OCLPackage.OPERATION__BODY, Operation.class, msgs);
			case OCLPackage.OCL_EXPRESSION__IF_EXP1:
				return eInternalContainer().eInverseRemove(this, OCLPackage.IF_EXP__CONDITION, IfExp.class, msgs);
			case OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE:
				return eInternalContainer().eInverseRemove(this, OCLPackage.ATTRIBUTE__INIT_EXPRESSION, Attribute.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OCLPackage.OCL_EXPRESSION__INFERRED_TYPE:
				if (resolve) return getInferredType();
				return basicGetInferredType();
			case OCLPackage.OCL_EXPRESSION__TYPE:
				return getType();
			case OCLPackage.OCL_EXPRESSION__IF_EXP3:
				return getIfExp3();
			case OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY:
				return getAppliedProperty();
			case OCLPackage.OCL_EXPRESSION__COLLECTION:
				return getCollection();
			case OCLPackage.OCL_EXPRESSION__LET_EXP:
				return getLetExp();
			case OCLPackage.OCL_EXPRESSION__LOOP_EXP:
				return getLoopExp();
			case OCLPackage.OCL_EXPRESSION__PARENT_OPERATION:
				return getParentOperation();
			case OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE:
				return getInitializedVariable();
			case OCLPackage.OCL_EXPRESSION__IF_EXP2:
				return getIfExp2();
			case OCLPackage.OCL_EXPRESSION__OWNING_OPERATION:
				return getOwningOperation();
			case OCLPackage.OCL_EXPRESSION__IF_EXP1:
				return getIfExp1();
			case OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE:
				return getOwningAttribute();
			case OCLPackage.OCL_EXPRESSION__IMPLICITLY_CASTED:
				return isImplicitlyCasted();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OCLPackage.OCL_EXPRESSION__INFERRED_TYPE:
				setInferredType((Type)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__TYPE:
				setType((OclType)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__IF_EXP3:
				setIfExp3((IfExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY:
				setAppliedProperty((PropertyCallExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__COLLECTION:
				setCollection((CollectionExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__LET_EXP:
				setLetExp((LetExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__LOOP_EXP:
				setLoopExp((LoopExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__PARENT_OPERATION:
				setParentOperation((OperationCallExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE:
				setInitializedVariable((VariableDeclaration)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__IF_EXP2:
				setIfExp2((IfExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__OWNING_OPERATION:
				setOwningOperation((Operation)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__IF_EXP1:
				setIfExp1((IfExp)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE:
				setOwningAttribute((Attribute)newValue);
				return;
			case OCLPackage.OCL_EXPRESSION__IMPLICITLY_CASTED:
				setImplicitlyCasted((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OCLPackage.OCL_EXPRESSION__INFERRED_TYPE:
				setInferredType((Type)null);
				return;
			case OCLPackage.OCL_EXPRESSION__TYPE:
				setType((OclType)null);
				return;
			case OCLPackage.OCL_EXPRESSION__IF_EXP3:
				setIfExp3((IfExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY:
				setAppliedProperty((PropertyCallExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__COLLECTION:
				setCollection((CollectionExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__LET_EXP:
				setLetExp((LetExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__LOOP_EXP:
				setLoopExp((LoopExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__PARENT_OPERATION:
				setParentOperation((OperationCallExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE:
				setInitializedVariable((VariableDeclaration)null);
				return;
			case OCLPackage.OCL_EXPRESSION__IF_EXP2:
				setIfExp2((IfExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__OWNING_OPERATION:
				setOwningOperation((Operation)null);
				return;
			case OCLPackage.OCL_EXPRESSION__IF_EXP1:
				setIfExp1((IfExp)null);
				return;
			case OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE:
				setOwningAttribute((Attribute)null);
				return;
			case OCLPackage.OCL_EXPRESSION__IMPLICITLY_CASTED:
				setImplicitlyCasted(IMPLICITLY_CASTED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OCLPackage.OCL_EXPRESSION__INFERRED_TYPE:
				return inferredType != null;
			case OCLPackage.OCL_EXPRESSION__TYPE:
				return type != null;
			case OCLPackage.OCL_EXPRESSION__IF_EXP3:
				return getIfExp3() != null;
			case OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY:
				return getAppliedProperty() != null;
			case OCLPackage.OCL_EXPRESSION__COLLECTION:
				return getCollection() != null;
			case OCLPackage.OCL_EXPRESSION__LET_EXP:
				return getLetExp() != null;
			case OCLPackage.OCL_EXPRESSION__LOOP_EXP:
				return getLoopExp() != null;
			case OCLPackage.OCL_EXPRESSION__PARENT_OPERATION:
				return getParentOperation() != null;
			case OCLPackage.OCL_EXPRESSION__INITIALIZED_VARIABLE:
				return getInitializedVariable() != null;
			case OCLPackage.OCL_EXPRESSION__IF_EXP2:
				return getIfExp2() != null;
			case OCLPackage.OCL_EXPRESSION__OWNING_OPERATION:
				return getOwningOperation() != null;
			case OCLPackage.OCL_EXPRESSION__IF_EXP1:
				return getIfExp1() != null;
			case OCLPackage.OCL_EXPRESSION__OWNING_ATTRIBUTE:
				return getOwningAttribute() != null;
			case OCLPackage.OCL_EXPRESSION__IMPLICITLY_CASTED:
				return implicitlyCasted != IMPLICITLY_CASTED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == TypedElement.class) {
			switch (derivedFeatureID) {
				case OCLPackage.OCL_EXPRESSION__INFERRED_TYPE: return OCLPackage.TYPED_ELEMENT__INFERRED_TYPE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == TypedElement.class) {
			switch (baseFeatureID) {
				case OCLPackage.TYPED_ELEMENT__INFERRED_TYPE: return OCLPackage.OCL_EXPRESSION__INFERRED_TYPE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (implicitlyCasted: ");
		result.append(implicitlyCasted);
		result.append(')');
		return result.toString();
	}

} //OclExpressionImpl
