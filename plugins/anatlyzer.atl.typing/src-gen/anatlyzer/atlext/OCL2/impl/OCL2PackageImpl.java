/**
 */
package anatlyzer.atlext.OCL2.impl;

import anatlyzer.atl.types.TypesPackage;

import anatlyzer.atlext.ATL.ATLPackage;

import anatlyzer.atlext.ATL.impl.ATLPackageImpl;

import anatlyzer.atlext.OCL.OCLPackage;

import anatlyzer.atlext.OCL.impl.OCLPackageImpl;

import anatlyzer.atlext.OCL2.OCL2Factory;
import anatlyzer.atlext.OCL2.OCL2Package;
import anatlyzer.atlext.OCL2.SelectByKind;

import anatlyzer.atlext.PrimitiveTypes.PrimitiveTypesPackage;

import anatlyzer.atlext.PrimitiveTypes.impl.PrimitiveTypesPackageImpl;

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
public class OCL2PackageImpl extends EPackageImpl implements OCL2Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectByKindEClass = null;

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
	 * @see anatlyzer.atlext.OCL2.OCL2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OCL2PackageImpl() {
		super(eNS_URI, OCL2Factory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OCL2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OCL2Package init() {
		if (isInited) return (OCL2Package)EPackage.Registry.INSTANCE.getEPackage(OCL2Package.eNS_URI);

		// Obtain or create and register package
		Object registeredOCL2Package = EPackage.Registry.INSTANCE.get(eNS_URI);
		OCL2PackageImpl theOCL2Package = registeredOCL2Package instanceof OCL2PackageImpl ? (OCL2PackageImpl)registeredOCL2Package : new OCL2PackageImpl();

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ATLPackage.eNS_URI);
		ATLPackageImpl theATLPackage = (ATLPackageImpl)(registeredPackage instanceof ATLPackageImpl ? registeredPackage : ATLPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(OCLPackage.eNS_URI);
		OCLPackageImpl theOCLPackage = (OCLPackageImpl)(registeredPackage instanceof OCLPackageImpl ? registeredPackage : OCLPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI);
		PrimitiveTypesPackageImpl thePrimitiveTypesPackage = (PrimitiveTypesPackageImpl)(registeredPackage instanceof PrimitiveTypesPackageImpl ? registeredPackage : PrimitiveTypesPackage.eINSTANCE);

		// Create package meta-data objects
		theOCL2Package.createPackageContents();
		theATLPackage.createPackageContents();
		theOCLPackage.createPackageContents();
		thePrimitiveTypesPackage.createPackageContents();

		// Initialize created meta-data
		theOCL2Package.initializePackageContents();
		theATLPackage.initializePackageContents();
		theOCLPackage.initializePackageContents();
		thePrimitiveTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOCL2Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OCL2Package.eNS_URI, theOCL2Package);
		return theOCL2Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSelectByKind() {
		return selectByKindEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSelectByKind_IsExact() {
		return (EAttribute)selectByKindEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCL2Factory getOCL2Factory() {
		return (OCL2Factory)getEFactoryInstance();
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
		selectByKindEClass = createEClass(SELECT_BY_KIND);
		createEAttribute(selectByKindEClass, SELECT_BY_KIND__IS_EXACT);
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
		OCLPackage theOCLPackage = (OCLPackage)EPackage.Registry.INSTANCE.getEPackage(OCLPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		selectByKindEClass.getESuperTypes().add(theOCLPackage.getCollectionOperationCallExp());

		// Initialize classes, features, and operations; add parameters
		initEClass(selectByKindEClass, SelectByKind.class, "SelectByKind", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSelectByKind_IsExact(), ecorePackage.getEBoolean(), "isExact", "false", 1, 1, SelectByKind.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OCL2PackageImpl
