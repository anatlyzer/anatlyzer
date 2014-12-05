/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;

import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.OclExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Each Out Pattern Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.ForEachOutPatternElementImpl#getCollection <em>Collection</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.ForEachOutPatternElementImpl#getIterator <em>Iterator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForEachOutPatternElementImpl extends OutPatternElementImpl implements ForEachOutPatternElement {
	/**
	 * The cached value of the '{@link #getCollection() <em>Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollection()
	 * @generated
	 * @ordered
	 */
	protected OclExpression collection;

	/**
	 * The cached value of the '{@link #getIterator() <em>Iterator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIterator()
	 * @generated
	 * @ordered
	 */
	protected Iterator iterator;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForEachOutPatternElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.FOR_EACH_OUT_PATTERN_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getCollection() {
		return collection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCollection(OclExpression newCollection, NotificationChain msgs) {
		OclExpression oldCollection = collection;
		collection = newCollection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION, oldCollection, newCollection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollection(OclExpression newCollection) {
		if (newCollection != collection) {
			NotificationChain msgs = null;
			if (collection != null)
				msgs = ((InternalEObject)collection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION, null, msgs);
			if (newCollection != null)
				msgs = ((InternalEObject)newCollection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION, null, msgs);
			msgs = basicSetCollection(newCollection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION, newCollection, newCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Iterator getIterator() {
		return iterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIterator(Iterator newIterator, NotificationChain msgs) {
		Iterator oldIterator = iterator;
		iterator = newIterator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR, oldIterator, newIterator);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIterator(Iterator newIterator) {
		if (newIterator != iterator) {
			NotificationChain msgs = null;
			if (iterator != null)
				msgs = ((InternalEObject)iterator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR, null, msgs);
			if (newIterator != null)
				msgs = ((InternalEObject)newIterator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR, null, msgs);
			msgs = basicSetIterator(newIterator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR, newIterator, newIterator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION:
				return basicSetCollection(null, msgs);
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR:
				return basicSetIterator(null, msgs);
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
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION:
				return getCollection();
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR:
				return getIterator();
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
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION:
				setCollection((OclExpression)newValue);
				return;
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR:
				setIterator((Iterator)newValue);
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
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION:
				setCollection((OclExpression)null);
				return;
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR:
				setIterator((Iterator)null);
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
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION:
				return collection != null;
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR:
				return iterator != null;
		}
		return super.eIsSet(featureID);
	}

} //ForEachOutPatternElementImpl
