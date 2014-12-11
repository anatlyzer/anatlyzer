/**
 */
package anatlyzer.atl.umlerrors.impl;

import anatlyzer.atl.errors.AnalysisResultPackage;

import anatlyzer.atl.errors.atl_error.AtlErrorPackage;

import anatlyzer.atl.umlerrors.StereotypeNotFound;
import anatlyzer.atl.umlerrors.UmlErrorsFactory;
import anatlyzer.atl.umlerrors.UmlErrorsPackage;

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
public class UmlErrorsPackageImpl extends EPackageImpl implements UmlErrorsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stereotypeNotFoundEClass = null;

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
	 * @see anatlyzer.atl.umlerrors.UmlErrorsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UmlErrorsPackageImpl() {
		super(eNS_URI, UmlErrorsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UmlErrorsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UmlErrorsPackage init() {
		if (isInited) return (UmlErrorsPackage)EPackage.Registry.INSTANCE.getEPackage(UmlErrorsPackage.eNS_URI);

		// Obtain or create and register package
		UmlErrorsPackageImpl theUmlErrorsPackage = (UmlErrorsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UmlErrorsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UmlErrorsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		AnalysisResultPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theUmlErrorsPackage.createPackageContents();

		// Initialize created meta-data
		theUmlErrorsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUmlErrorsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UmlErrorsPackage.eNS_URI, theUmlErrorsPackage);
		return theUmlErrorsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStereotypeNotFound() {
		return stereotypeNotFoundEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStereotypeNotFound_StereotypeName() {
		return (EAttribute)stereotypeNotFoundEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmlErrorsFactory getUmlErrorsFactory() {
		return (UmlErrorsFactory)getEFactoryInstance();
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
		stereotypeNotFoundEClass = createEClass(STEREOTYPE_NOT_FOUND);
		createEAttribute(stereotypeNotFoundEClass, STEREOTYPE_NOT_FOUND__STEREOTYPE_NAME);
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
		stereotypeNotFoundEClass.getESuperTypes().add(theAtlErrorPackage.getLocalProblem());

		// Initialize classes, features, and operations; add parameters
		initEClass(stereotypeNotFoundEClass, StereotypeNotFound.class, "StereotypeNotFound", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStereotypeNotFound_StereotypeName(), ecorePackage.getEString(), "stereotypeName", null, 1, 1, StereotypeNotFound.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //UmlErrorsPackageImpl
