/**
 */
package anatlyzer.atl.errors.ide_error.impl;

import anatlyzer.atl.errors.AnalysisResultPackage;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;

import anatlyzer.atl.errors.atl_error.impl.AtlErrorPackageImpl;

import anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage;

import anatlyzer.atl.errors.atl_recovery.impl.AtlRecoveryPackageImpl;

import anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel;
import anatlyzer.atl.errors.ide_error.IdeErrorFactory;
import anatlyzer.atl.errors.ide_error.IdeErrorPackage;

import anatlyzer.atl.errors.impl.AnalysisResultPackageImpl;

import anatlyzer.atl.types.TypesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdeErrorPackageImpl extends EPackageImpl implements IdeErrorPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass couldNotLoadMetamodelEClass = null;

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
	 * @see anatlyzer.atl.errors.ide_error.IdeErrorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IdeErrorPackageImpl() {
		super(eNS_URI, IdeErrorFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link IdeErrorPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IdeErrorPackage init() {
		if (isInited) return (IdeErrorPackage)EPackage.Registry.INSTANCE.getEPackage(IdeErrorPackage.eNS_URI);

		// Obtain or create and register package
		IdeErrorPackageImpl theIdeErrorPackage = (IdeErrorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof IdeErrorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new IdeErrorPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		AnalysisResultPackageImpl theAnalysisResultPackage = (AnalysisResultPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AnalysisResultPackage.eNS_URI) instanceof AnalysisResultPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AnalysisResultPackage.eNS_URI) : AnalysisResultPackage.eINSTANCE);
		AtlErrorPackageImpl theAtlErrorPackage = (AtlErrorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AtlErrorPackage.eNS_URI) instanceof AtlErrorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AtlErrorPackage.eNS_URI) : AtlErrorPackage.eINSTANCE);
		AtlRecoveryPackageImpl theAtlRecoveryPackage = (AtlRecoveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AtlRecoveryPackage.eNS_URI) instanceof AtlRecoveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AtlRecoveryPackage.eNS_URI) : AtlRecoveryPackage.eINSTANCE);

		// Create package meta-data objects
		theIdeErrorPackage.createPackageContents();
		theAnalysisResultPackage.createPackageContents();
		theAtlErrorPackage.createPackageContents();
		theAtlRecoveryPackage.createPackageContents();

		// Initialize created meta-data
		theIdeErrorPackage.initializePackageContents();
		theAnalysisResultPackage.initializePackageContents();
		theAtlErrorPackage.initializePackageContents();
		theAtlRecoveryPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIdeErrorPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IdeErrorPackage.eNS_URI, theIdeErrorPackage);
		return theIdeErrorPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCouldNotLoadMetamodel() {
		return couldNotLoadMetamodelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCouldNotLoadMetamodel_Uri() {
		return (EAttribute)couldNotLoadMetamodelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdeErrorFactory getIdeErrorFactory() {
		return (IdeErrorFactory)getEFactoryInstance();
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
		couldNotLoadMetamodelEClass = createEClass(COULD_NOT_LOAD_METAMODEL);
		createEAttribute(couldNotLoadMetamodelEClass, COULD_NOT_LOAD_METAMODEL__URI);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		couldNotLoadMetamodelEClass.getESuperTypes().add(theAtlErrorPackage.getLocalProblem());

		// Initialize classes, features, and operations; add parameters
		initEClass(couldNotLoadMetamodelEClass, CouldNotLoadMetamodel.class, "CouldNotLoadMetamodel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCouldNotLoadMetamodel_Uri(), ecorePackage.getEString(), "uri", null, 1, 1, CouldNotLoadMetamodel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //IdeErrorPackageImpl
