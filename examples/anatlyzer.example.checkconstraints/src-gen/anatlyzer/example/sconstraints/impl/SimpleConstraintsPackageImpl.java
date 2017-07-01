/**
 */
package anatlyzer.example.sconstraints.impl;

import anatlyzer.example.sconstraints.ConstraintModel;
import anatlyzer.example.sconstraints.NumClassesConstraint;
import anatlyzer.example.sconstraints.NumConstraint;
import anatlyzer.example.sconstraints.NumRefsConstraint;
import anatlyzer.example.sconstraints.SimpleConstraintsFactory;
import anatlyzer.example.sconstraints.SimpleConstraintsPackage;

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
public class SimpleConstraintsPackageImpl extends EPackageImpl implements SimpleConstraintsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numClassesConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numRefsConstraintEClass = null;

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
	 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SimpleConstraintsPackageImpl() {
		super(eNS_URI, SimpleConstraintsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SimpleConstraintsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SimpleConstraintsPackage init() {
		if (isInited) return (SimpleConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(SimpleConstraintsPackage.eNS_URI);

		// Obtain or create and register package
		SimpleConstraintsPackageImpl theSimpleConstraintsPackage = (SimpleConstraintsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SimpleConstraintsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SimpleConstraintsPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theSimpleConstraintsPackage.createPackageContents();

		// Initialize created meta-data
		theSimpleConstraintsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSimpleConstraintsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SimpleConstraintsPackage.eNS_URI, theSimpleConstraintsPackage);
		return theSimpleConstraintsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintModel() {
		return constraintModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintModel_Constraints() {
		return (EReference)constraintModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumConstraint() {
		return numConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumConstraint_Number() {
		return (EAttribute)numConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumClassesConstraint() {
		return numClassesConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumClassesConstraint_ClassName() {
		return (EAttribute)numClassesConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumRefsConstraint() {
		return numRefsConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumRefsConstraint_ClassName() {
		return (EAttribute)numRefsConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumRefsConstraint_RefName() {
		return (EAttribute)numRefsConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleConstraintsFactory getSimpleConstraintsFactory() {
		return (SimpleConstraintsFactory)getEFactoryInstance();
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
		constraintModelEClass = createEClass(CONSTRAINT_MODEL);
		createEReference(constraintModelEClass, CONSTRAINT_MODEL__CONSTRAINTS);

		numConstraintEClass = createEClass(NUM_CONSTRAINT);
		createEAttribute(numConstraintEClass, NUM_CONSTRAINT__NUMBER);

		numClassesConstraintEClass = createEClass(NUM_CLASSES_CONSTRAINT);
		createEAttribute(numClassesConstraintEClass, NUM_CLASSES_CONSTRAINT__CLASS_NAME);

		numRefsConstraintEClass = createEClass(NUM_REFS_CONSTRAINT);
		createEAttribute(numRefsConstraintEClass, NUM_REFS_CONSTRAINT__CLASS_NAME);
		createEAttribute(numRefsConstraintEClass, NUM_REFS_CONSTRAINT__REF_NAME);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		numClassesConstraintEClass.getESuperTypes().add(this.getNumConstraint());
		numRefsConstraintEClass.getESuperTypes().add(this.getNumConstraint());

		// Initialize classes, features, and operations; add parameters
		initEClass(constraintModelEClass, ConstraintModel.class, "ConstraintModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstraintModel_Constraints(), this.getNumConstraint(), null, "constraints", null, 0, -1, ConstraintModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numConstraintEClass, NumConstraint.class, "NumConstraint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumConstraint_Number(), ecorePackage.getEInt(), "number", null, 0, 1, NumConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numClassesConstraintEClass, NumClassesConstraint.class, "NumClassesConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumClassesConstraint_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, NumClassesConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numRefsConstraintEClass, NumRefsConstraint.class, "NumRefsConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumRefsConstraint_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, NumRefsConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumRefsConstraint_RefName(), ecorePackage.getEString(), "refName", null, 0, 1, NumRefsConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SimpleConstraintsPackageImpl
