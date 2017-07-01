/**
 */
package anatlyzer.example.sconstraints.impl;

import anatlyzer.example.sconstraints.ConstraintModel;
import anatlyzer.example.sconstraints.NumConstraint;
import anatlyzer.example.sconstraints.SimpleConstraintsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.example.sconstraints.impl.ConstraintModelImpl#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstraintModelImpl extends MinimalEObjectImpl.Container implements ConstraintModel {
	/**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<NumConstraint> constraints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleConstraintsPackage.Literals.CONSTRAINT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NumConstraint> getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentEList<NumConstraint>(NumConstraint.class, this, SimpleConstraintsPackage.CONSTRAINT_MODEL__CONSTRAINTS);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleConstraintsPackage.CONSTRAINT_MODEL__CONSTRAINTS:
				return ((InternalEList<?>)getConstraints()).basicRemove(otherEnd, msgs);
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
			case SimpleConstraintsPackage.CONSTRAINT_MODEL__CONSTRAINTS:
				return getConstraints();
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
			case SimpleConstraintsPackage.CONSTRAINT_MODEL__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection<? extends NumConstraint>)newValue);
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
			case SimpleConstraintsPackage.CONSTRAINT_MODEL__CONSTRAINTS:
				getConstraints().clear();
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
			case SimpleConstraintsPackage.CONSTRAINT_MODEL__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConstraintModelImpl
