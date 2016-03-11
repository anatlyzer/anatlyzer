/**
 */
package anatlyzer.atl.errors.impl;

import anatlyzer.atl.errors.AnalysisResult;
import anatlyzer.atl.errors.AnalysisResultPackage;
import anatlyzer.atl.errors.BatchAnalysis;
import anatlyzer.atl.errors.Problem;

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
 * An implementation of the model object '<em><b>Analysis Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.impl.AnalysisResultImpl#getProblems <em>Problems</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.impl.AnalysisResultImpl#getBatchAnalysis <em>Batch Analysis</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnalysisResultImpl extends MinimalEObjectImpl.Container implements AnalysisResult {
	/**
	 * The cached value of the '{@link #getProblems() <em>Problems</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProblems()
	 * @generated
	 * @ordered
	 */
	protected EList<Problem> problems;

	/**
	 * The cached value of the '{@link #getBatchAnalysis() <em>Batch Analysis</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatchAnalysis()
	 * @generated
	 * @ordered
	 */
	protected EList<BatchAnalysis> batchAnalysis;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnalysisResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisResultPackage.Literals.ANALYSIS_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Problem> getProblems() {
		if (problems == null) {
			problems = new EObjectContainmentEList<Problem>(Problem.class, this, AnalysisResultPackage.ANALYSIS_RESULT__PROBLEMS);
		}
		return problems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BatchAnalysis> getBatchAnalysis() {
		if (batchAnalysis == null) {
			batchAnalysis = new EObjectContainmentEList<BatchAnalysis>(BatchAnalysis.class, this, AnalysisResultPackage.ANALYSIS_RESULT__BATCH_ANALYSIS);
		}
		return batchAnalysis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisResultPackage.ANALYSIS_RESULT__PROBLEMS:
				return ((InternalEList<?>)getProblems()).basicRemove(otherEnd, msgs);
			case AnalysisResultPackage.ANALYSIS_RESULT__BATCH_ANALYSIS:
				return ((InternalEList<?>)getBatchAnalysis()).basicRemove(otherEnd, msgs);
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
			case AnalysisResultPackage.ANALYSIS_RESULT__PROBLEMS:
				return getProblems();
			case AnalysisResultPackage.ANALYSIS_RESULT__BATCH_ANALYSIS:
				return getBatchAnalysis();
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
			case AnalysisResultPackage.ANALYSIS_RESULT__PROBLEMS:
				getProblems().clear();
				getProblems().addAll((Collection<? extends Problem>)newValue);
				return;
			case AnalysisResultPackage.ANALYSIS_RESULT__BATCH_ANALYSIS:
				getBatchAnalysis().clear();
				getBatchAnalysis().addAll((Collection<? extends BatchAnalysis>)newValue);
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
			case AnalysisResultPackage.ANALYSIS_RESULT__PROBLEMS:
				getProblems().clear();
				return;
			case AnalysisResultPackage.ANALYSIS_RESULT__BATCH_ANALYSIS:
				getBatchAnalysis().clear();
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
			case AnalysisResultPackage.ANALYSIS_RESULT__PROBLEMS:
				return problems != null && !problems.isEmpty();
			case AnalysisResultPackage.ANALYSIS_RESULT__BATCH_ANALYSIS:
				return batchAnalysis != null && !batchAnalysis.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AnalysisResultImpl
