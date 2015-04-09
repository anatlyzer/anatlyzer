/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuple Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.TupleExpImpl#getTuplePart <em>Tuple Part</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleExpImpl extends OclExpressionImpl implements TupleExp {
	/**
	 * The cached value of the '{@link #getTuplePart() <em>Tuple Part</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTuplePart()
	 * @generated
	 * @ordered
	 */
	protected EList<TuplePart> tuplePart;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.TUPLE_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TuplePart> getTuplePart() {
		if (tuplePart == null) {
			tuplePart = new EObjectContainmentWithInverseEList<TuplePart>(TuplePart.class, this, OCLPackage.TUPLE_EXP__TUPLE_PART, OCLPackage.TUPLE_PART__TUPLE);
		}
		return tuplePart;
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
			case OCLPackage.TUPLE_EXP__TUPLE_PART:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTuplePart()).basicAdd(otherEnd, msgs);
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
			case OCLPackage.TUPLE_EXP__TUPLE_PART:
				return ((InternalEList<?>)getTuplePart()).basicRemove(otherEnd, msgs);
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
			case OCLPackage.TUPLE_EXP__TUPLE_PART:
				return getTuplePart();
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
			case OCLPackage.TUPLE_EXP__TUPLE_PART:
				getTuplePart().clear();
				getTuplePart().addAll((Collection<? extends TuplePart>)newValue);
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
			case OCLPackage.TUPLE_EXP__TUPLE_PART:
				getTuplePart().clear();
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
			case OCLPackage.TUPLE_EXP__TUPLE_PART:
				return tuplePart != null && !tuplePart.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TupleExpImpl
