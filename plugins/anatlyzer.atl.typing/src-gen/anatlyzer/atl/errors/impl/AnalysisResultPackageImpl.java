/**
 */
package anatlyzer.atl.errors.impl;

import anatlyzer.atl.errors.AnalysisInfo;
import anatlyzer.atl.errors.AnalysisResult;
import anatlyzer.atl.errors.AnalysisResultFactory;
import anatlyzer.atl.errors.AnalysisResultPackage;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.Recovery;
import anatlyzer.atl.errors.SeverityKind;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;

import anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl;

import anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage;

import anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryPackageImpl;

import anatlyzer.atl.errors.ide_error.IdeErrorPackage;
import anatlyzer.atl.errors.ide_error.impl.IdeErrorPackageImpl;
import anatlyzer.atl.types.TypesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AnalysisResultPackageImpl extends EPackageImpl implements AnalysisResultPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass problemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass recoveryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum severityKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see anatlyzer.atl.errors.AnalysisResultPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AnalysisResultPackageImpl() {
		super(eNS_URI, AnalysisResultFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AnalysisResultPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AnalysisResultPackage init() {
		if (isInited) return (AnalysisResultPackage)EPackage.Registry.INSTANCE.getEPackage(AnalysisResultPackage.eNS_URI);

		// Obtain or create and register package
		AnalysisResultPackageImpl theAnalysisResultPackage = (AnalysisResultPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AnalysisResultPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AnalysisResultPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		AtlErrorPackageImpl theAtlErrorPackage = (AtlErrorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AtlErrorPackage.eNS_URI) instanceof AtlErrorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AtlErrorPackage.eNS_URI) : AtlErrorPackage.eINSTANCE);
		AtlRecoveryPackageImpl theAtlRecoveryPackage = (AtlRecoveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AtlRecoveryPackage.eNS_URI) instanceof AtlRecoveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AtlRecoveryPackage.eNS_URI) : AtlRecoveryPackage.eINSTANCE);
		IdeErrorPackageImpl theIdeErrorPackage = (IdeErrorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IdeErrorPackage.eNS_URI) instanceof IdeErrorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IdeErrorPackage.eNS_URI) : IdeErrorPackage.eINSTANCE);

		// Create package meta-data objects
		theAnalysisResultPackage.createPackageContents();
		theAtlErrorPackage.createPackageContents();
		theAtlRecoveryPackage.createPackageContents();
		theIdeErrorPackage.createPackageContents();

		// Initialize created meta-data
		theAnalysisResultPackage.initializePackageContents();
		theAtlErrorPackage.initializePackageContents();
		theAtlRecoveryPackage.initializePackageContents();
		theIdeErrorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAnalysisResultPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AnalysisResultPackage.eNS_URI, theAnalysisResultPackage);
		return theAnalysisResultPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysisResult() {
		return analysisResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisResult_Problems() {
		return (EReference)analysisResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysisInfo() {
		return analysisInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProblem() {
		return problemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProblem_Dependents() {
		return (EReference)problemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProblem_Description() {
		return (EAttribute)problemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProblem_Severity() {
		return (EAttribute)problemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProblem_NeedsCSP() {
		return (EAttribute)problemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRecovery() {
		return recoveryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSeverityKind() {
		return severityKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalysisResultFactory getAnalysisResultFactory() {
		return (AnalysisResultFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		analysisResultEClass = createEClass(ANALYSIS_RESULT);
		createEReference(analysisResultEClass, ANALYSIS_RESULT__PROBLEMS);

		analysisInfoEClass = createEClass(ANALYSIS_INFO);

		problemEClass = createEClass(PROBLEM);
		createEReference(problemEClass, PROBLEM__DEPENDENTS);
		createEAttribute(problemEClass, PROBLEM__DESCRIPTION);
		createEAttribute(problemEClass, PROBLEM__SEVERITY);
		createEAttribute(problemEClass, PROBLEM__NEEDS_CSP);

		recoveryEClass = createEClass(RECOVERY);

		// Create enums
		severityKindEEnum = createEEnum(SEVERITY_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		AtlErrorPackage theAtlErrorPackage = (AtlErrorPackage)EPackage.Registry.INSTANCE.getEPackage(AtlErrorPackage.eNS_URI);
		AtlRecoveryPackage theAtlRecoveryPackage = (AtlRecoveryPackage)EPackage.Registry.INSTANCE.getEPackage(AtlRecoveryPackage.eNS_URI);
		IdeErrorPackage theIdeErrorPackage = (IdeErrorPackage)EPackage.Registry.INSTANCE.getEPackage(IdeErrorPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theAtlErrorPackage);
		getESubpackages().add(theAtlRecoveryPackage);
		getESubpackages().add(theIdeErrorPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		problemEClass.getESuperTypes().add(this.getAnalysisInfo());

		// Initialize classes, features, and operations; add parameters
		initEClass(analysisResultEClass, AnalysisResult.class, "AnalysisResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnalysisResult_Problems(), this.getProblem(), null, "problems", null, 0, -1, AnalysisResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(analysisInfoEClass, AnalysisInfo.class, "AnalysisInfo", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(problemEClass, Problem.class, "Problem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProblem_Dependents(), this.getProblem(), null, "dependents", null, 0, -1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProblem_Description(), ecorePackage.getEString(), "description", null, 1, 1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProblem_Severity(), this.getSeverityKind(), "severity", null, 1, 1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProblem_NeedsCSP(), ecorePackage.getEBoolean(), "needsCSP", "false", 0, 1, Problem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(recoveryEClass, Recovery.class, "Recovery", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(severityKindEEnum, SeverityKind.class, "SeverityKind");
		addEEnumLiteral(severityKindEEnum, SeverityKind.ERROR);
		addEEnumLiteral(severityKindEEnum, SeverityKind.WARNING);
		addEEnumLiteral(severityKindEEnum, SeverityKind.STYLE_SUGGESTION);
		addEEnumLiteral(severityKindEEnum, SeverityKind.PERFORMANCE_SUGGESTION);

		// Create resource
		createResource(eNS_URI);
	}

} //AnalysisResultPackageImpl
