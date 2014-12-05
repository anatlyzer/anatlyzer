/**
 */
package anatlyzer.atl.errors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.AnalysisResultFactory
 * @model kind="package"
 * @generated
 */
public interface AnalysisResultPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "errors";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/atl/errors";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_errors";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AnalysisResultPackage eINSTANCE = anatlyzer.atl.errors.impl.AnalysisResultPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.impl.AnalysisResultImpl <em>Analysis Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.impl.AnalysisResultImpl
	 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getAnalysisResult()
	 * @generated
	 */
	int ANALYSIS_RESULT = 0;

	/**
	 * The feature id for the '<em><b>Problems</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT__PROBLEMS = 0;

	/**
	 * The number of structural features of the '<em>Analysis Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Analysis Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.impl.AnalysisInfoImpl <em>Analysis Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.impl.AnalysisInfoImpl
	 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getAnalysisInfo()
	 * @generated
	 */
	int ANALYSIS_INFO = 1;

	/**
	 * The number of structural features of the '<em>Analysis Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_INFO_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Analysis Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_INFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.impl.ProblemImpl <em>Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.impl.ProblemImpl
	 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getProblem()
	 * @generated
	 */
	int PROBLEM = 2;

	/**
	 * The feature id for the '<em><b>Dependents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBLEM__DEPENDENTS = ANALYSIS_INFO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBLEM__DESCRIPTION = ANALYSIS_INFO_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBLEM__SEVERITY = ANALYSIS_INFO_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Needs CSP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBLEM__NEEDS_CSP = ANALYSIS_INFO_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBLEM_FEATURE_COUNT = ANALYSIS_INFO_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBLEM_OPERATION_COUNT = ANALYSIS_INFO_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.impl.RecoveryImpl <em>Recovery</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.impl.RecoveryImpl
	 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getRecovery()
	 * @generated
	 */
	int RECOVERY = 3;

	/**
	 * The number of structural features of the '<em>Recovery</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECOVERY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Recovery</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECOVERY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.errors.SeverityKind <em>Severity Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.errors.SeverityKind
	 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getSeverityKind()
	 * @generated
	 */
	int SEVERITY_KIND = 4;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.AnalysisResult <em>Analysis Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Analysis Result</em>'.
	 * @see anatlyzer.atl.errors.AnalysisResult
	 * @generated
	 */
	EClass getAnalysisResult();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atl.errors.AnalysisResult#getProblems <em>Problems</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Problems</em>'.
	 * @see anatlyzer.atl.errors.AnalysisResult#getProblems()
	 * @see #getAnalysisResult()
	 * @generated
	 */
	EReference getAnalysisResult_Problems();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.AnalysisInfo <em>Analysis Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Analysis Info</em>'.
	 * @see anatlyzer.atl.errors.AnalysisInfo
	 * @generated
	 */
	EClass getAnalysisInfo();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.Problem <em>Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Problem</em>'.
	 * @see anatlyzer.atl.errors.Problem
	 * @generated
	 */
	EClass getProblem();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atl.errors.Problem#getDependents <em>Dependents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dependents</em>'.
	 * @see anatlyzer.atl.errors.Problem#getDependents()
	 * @see #getProblem()
	 * @generated
	 */
	EReference getProblem_Dependents();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.Problem#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see anatlyzer.atl.errors.Problem#getDescription()
	 * @see #getProblem()
	 * @generated
	 */
	EAttribute getProblem_Description();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.Problem#getSeverity <em>Severity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Severity</em>'.
	 * @see anatlyzer.atl.errors.Problem#getSeverity()
	 * @see #getProblem()
	 * @generated
	 */
	EAttribute getProblem_Severity();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.errors.Problem#isNeedsCSP <em>Needs CSP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Needs CSP</em>'.
	 * @see anatlyzer.atl.errors.Problem#isNeedsCSP()
	 * @see #getProblem()
	 * @generated
	 */
	EAttribute getProblem_NeedsCSP();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.errors.Recovery <em>Recovery</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Recovery</em>'.
	 * @see anatlyzer.atl.errors.Recovery
	 * @generated
	 */
	EClass getRecovery();

	/**
	 * Returns the meta object for enum '{@link anatlyzer.atl.errors.SeverityKind <em>Severity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Severity Kind</em>'.
	 * @see anatlyzer.atl.errors.SeverityKind
	 * @generated
	 */
	EEnum getSeverityKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AnalysisResultFactory getAnalysisResultFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.impl.AnalysisResultImpl <em>Analysis Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.impl.AnalysisResultImpl
		 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getAnalysisResult()
		 * @generated
		 */
		EClass ANALYSIS_RESULT = eINSTANCE.getAnalysisResult();

		/**
		 * The meta object literal for the '<em><b>Problems</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_RESULT__PROBLEMS = eINSTANCE.getAnalysisResult_Problems();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.impl.AnalysisInfoImpl <em>Analysis Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.impl.AnalysisInfoImpl
		 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getAnalysisInfo()
		 * @generated
		 */
		EClass ANALYSIS_INFO = eINSTANCE.getAnalysisInfo();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.impl.ProblemImpl <em>Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.impl.ProblemImpl
		 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getProblem()
		 * @generated
		 */
		EClass PROBLEM = eINSTANCE.getProblem();

		/**
		 * The meta object literal for the '<em><b>Dependents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROBLEM__DEPENDENTS = eINSTANCE.getProblem_Dependents();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROBLEM__DESCRIPTION = eINSTANCE.getProblem_Description();

		/**
		 * The meta object literal for the '<em><b>Severity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROBLEM__SEVERITY = eINSTANCE.getProblem_Severity();

		/**
		 * The meta object literal for the '<em><b>Needs CSP</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROBLEM__NEEDS_CSP = eINSTANCE.getProblem_NeedsCSP();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.impl.RecoveryImpl <em>Recovery</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.impl.RecoveryImpl
		 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getRecovery()
		 * @generated
		 */
		EClass RECOVERY = eINSTANCE.getRecovery();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.errors.SeverityKind <em>Severity Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.errors.SeverityKind
		 * @see anatlyzer.atl.errors.impl.AnalysisResultPackageImpl#getSeverityKind()
		 * @generated
		 */
		EEnum SEVERITY_KIND = eINSTANCE.getSeverityKind();

	}

} //AnalysisResultPackage
