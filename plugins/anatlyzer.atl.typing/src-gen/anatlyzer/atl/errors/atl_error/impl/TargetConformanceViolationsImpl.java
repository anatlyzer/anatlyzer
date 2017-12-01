/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.BatchTargetConformanceIssue;
import anatlyzer.atl.errors.atl_error.TargetConformanceViolations;

import anatlyzer.atl.errors.impl.BatchAnalysisImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Target Conformance Violations</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.TargetConformanceViolationsImpl#getTargetIssues <em>Target Issues</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TargetConformanceViolationsImpl extends BatchAnalysisImpl implements TargetConformanceViolations {
	/**
	 * The cached value of the '{@link #getTargetIssues() <em>Target Issues</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetIssues()
	 * @generated
	 * @ordered
	 */
	protected EList<BatchTargetConformanceIssue> targetIssues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TargetConformanceViolationsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.TARGET_CONFORMANCE_VIOLATIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BatchTargetConformanceIssue> getTargetIssues() {
		if (targetIssues == null) {
			targetIssues = new EObjectContainmentEList<BatchTargetConformanceIssue>(BatchTargetConformanceIssue.class, this, AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES);
		}
		return targetIssues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES:
				return ((InternalEList<?>)getTargetIssues()).basicRemove(otherEnd, msgs);
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
			case AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES:
				return getTargetIssues();
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
			case AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES:
				getTargetIssues().clear();
				getTargetIssues().addAll((Collection<? extends BatchTargetConformanceIssue>)newValue);
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
			case AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES:
				getTargetIssues().clear();
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
			case AtlErrorPackage.TARGET_CONFORMANCE_VIOLATIONS__TARGET_ISSUES:
				return targetIssues != null && !targetIssues.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TargetConformanceViolationsImpl
