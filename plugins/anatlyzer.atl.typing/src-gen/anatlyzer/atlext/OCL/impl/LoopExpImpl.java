/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loop Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.LoopExpImpl#getBody <em>Body</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.LoopExpImpl#getIterators <em>Iterators</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LoopExpImpl extends PropertyCallExpImpl implements LoopExp {
	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected OclExpression body;

	/**
	 * The cached value of the '{@link #getIterators() <em>Iterators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIterators()
	 * @generated
	 * @ordered
	 */
	protected EList<Iterator> iterators;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LoopExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.LOOP_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getBody() {
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(OclExpression newBody, NotificationChain msgs) {
		OclExpression oldBody = body;
		body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OCLPackage.LOOP_EXP__BODY, oldBody, newBody);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(OclExpression newBody) {
		if (newBody != body) {
			NotificationChain msgs = null;
			if (body != null)
				msgs = ((InternalEObject)body).eInverseRemove(this, OCLPackage.OCL_EXPRESSION__LOOP_EXP, OclExpression.class, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, OCLPackage.OCL_EXPRESSION__LOOP_EXP, OclExpression.class, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.LOOP_EXP__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Iterator> getIterators() {
		if (iterators == null) {
			iterators = new EObjectContainmentWithInverseEList<Iterator>(Iterator.class, this, OCLPackage.LOOP_EXP__ITERATORS, OCLPackage.ITERATOR__LOOP_EXPR);
		}
		return iterators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OCLPackage.LOOP_EXP__BODY:
				if (body != null)
					msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OCLPackage.LOOP_EXP__BODY, null, msgs);
				return basicSetBody((OclExpression)otherEnd, msgs);
			case OCLPackage.LOOP_EXP__ITERATORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIterators()).basicAdd(otherEnd, msgs);
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
			case OCLPackage.LOOP_EXP__BODY:
				return basicSetBody(null, msgs);
			case OCLPackage.LOOP_EXP__ITERATORS:
				return ((InternalEList<?>)getIterators()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OCLPackage.LOOP_EXP__BODY:
				return getBody();
			case OCLPackage.LOOP_EXP__ITERATORS:
				return getIterators();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OCLPackage.LOOP_EXP__BODY:
				setBody((OclExpression)newValue);
				return;
			case OCLPackage.LOOP_EXP__ITERATORS:
				getIterators().clear();
				getIterators().addAll((Collection<? extends Iterator>)newValue);
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
			case OCLPackage.LOOP_EXP__BODY:
				setBody((OclExpression)null);
				return;
			case OCLPackage.LOOP_EXP__ITERATORS:
				getIterators().clear();
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
			case OCLPackage.LOOP_EXP__BODY:
				return body != null;
			case OCLPackage.LOOP_EXP__ITERATORS:
				return iterators != null && !iterators.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LoopExpImpl
