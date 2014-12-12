/**
 */
package anatlyzer.atl.errors.atl_recovery.impl;

import anatlyzer.atl.errors.AnalysisResultPackage;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;

import anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl;

import anatlyzer.atl.errors.atl_recovery.AtlRecoveryFactory;
import anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage;
import anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass;
import anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned;

import anatlyzer.atl.errors.ide_error.IdeErrorPackage;
import anatlyzer.atl.errors.ide_error.impl.IdeErrorPackageImpl;
import anatlyzer.atl.errors.impl.AnalysisResultPackageImpl;

import anatlyzer.atl.types.TypesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AtlRecoveryPackageImpl extends EPackageImpl implements AtlRecoveryPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureFoundInSubclassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tentativeTypeAssignedEClass = null;

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
	 * @see anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AtlRecoveryPackageImpl() {
		super(eNS_URI, AtlRecoveryFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AtlRecoveryPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AtlRecoveryPackage init() {
		if (isInited) return (AtlRecoveryPackage)EPackage.Registry.INSTANCE.getEPackage(AtlRecoveryPackage.eNS_URI);

		// Obtain or create and register package
		AtlRecoveryPackageImpl theAtlRecoveryPackage = (AtlRecoveryPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AtlRecoveryPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AtlRecoveryPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		AnalysisResultPackageImpl theAnalysisResultPackage = (AnalysisResultPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AnalysisResultPackage.eNS_URI) instanceof AnalysisResultPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AnalysisResultPackage.eNS_URI) : AnalysisResultPackage.eINSTANCE);
		AtlErrorPackageImpl theAtlErrorPackage = (AtlErrorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AtlErrorPackage.eNS_URI) instanceof AtlErrorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AtlErrorPackage.eNS_URI) : AtlErrorPackage.eINSTANCE);
		IdeErrorPackageImpl theIdeErrorPackage = (IdeErrorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IdeErrorPackage.eNS_URI) instanceof IdeErrorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IdeErrorPackage.eNS_URI) : IdeErrorPackage.eINSTANCE);

		// Create package meta-data objects
		theAtlRecoveryPackage.createPackageContents();
		theAnalysisResultPackage.createPackageContents();
		theAtlErrorPackage.createPackageContents();
		theIdeErrorPackage.createPackageContents();

		// Initialize created meta-data
		theAtlRecoveryPackage.initializePackageContents();
		theAnalysisResultPackage.initializePackageContents();
		theAtlErrorPackage.initializePackageContents();
		theIdeErrorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAtlRecoveryPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AtlRecoveryPackage.eNS_URI, theAtlRecoveryPackage);
		return theAtlRecoveryPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeatureFoundInSubclass() {
		return featureFoundInSubclassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureFoundInSubclass_SubclassName() {
		return (EAttribute)featureFoundInSubclassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeatureFoundInSubclass_Subclass() {
		return (EReference)featureFoundInSubclassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTentativeTypeAssigned() {
		return tentativeTypeAssignedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTentativeTypeAssigned_Type() {
		return (EReference)tentativeTypeAssignedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlRecoveryFactory getAtlRecoveryFactory() {
		return (AtlRecoveryFactory)getEFactoryInstance();
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
		featureFoundInSubclassEClass = createEClass(FEATURE_FOUND_IN_SUBCLASS);
		createEAttribute(featureFoundInSubclassEClass, FEATURE_FOUND_IN_SUBCLASS__SUBCLASS_NAME);
		createEReference(featureFoundInSubclassEClass, FEATURE_FOUND_IN_SUBCLASS__SUBCLASS);

		tentativeTypeAssignedEClass = createEClass(TENTATIVE_TYPE_ASSIGNED);
		createEReference(tentativeTypeAssignedEClass, TENTATIVE_TYPE_ASSIGNED__TYPE);
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
		AnalysisResultPackage theAnalysisResultPackage = (AnalysisResultPackage)EPackage.Registry.INSTANCE.getEPackage(AnalysisResultPackage.eNS_URI);
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		featureFoundInSubclassEClass.getESuperTypes().add(theAnalysisResultPackage.getRecovery());
		tentativeTypeAssignedEClass.getESuperTypes().add(theAnalysisResultPackage.getRecovery());

		// Initialize classes, features, and operations; add parameters
		initEClass(featureFoundInSubclassEClass, FeatureFoundInSubclass.class, "FeatureFoundInSubclass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureFoundInSubclass_SubclassName(), ecorePackage.getEString(), "subclassName", null, 1, 1, FeatureFoundInSubclass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureFoundInSubclass_Subclass(), ecorePackage.getEClass(), null, "subclass", null, 1, 1, FeatureFoundInSubclass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tentativeTypeAssignedEClass, TentativeTypeAssigned.class, "TentativeTypeAssigned", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTentativeTypeAssigned_Type(), theTypesPackage.getType(), null, "type", null, 1, 1, TentativeTypeAssigned.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //AtlRecoveryPackageImpl
