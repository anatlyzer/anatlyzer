/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollection;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.MetaModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.OclUndefinedType;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.RefType;
import anatlyzer.atl.types.ReflectiveClass;
import anatlyzer.atl.types.ReflectiveType;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.TypesPackage;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atl.types.UnknownFeature;
import anatlyzer.atl.types.UnresolvedTypeError;

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
public class TypesPackageImpl extends EPackageImpl implements TypesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclUndefinedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass refTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unknownEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emptyCollectionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeErrorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedTypeErrorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unknownFeatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emptyCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metaModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metaclassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reflectiveTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisModuleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reflectiveClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setTypeEClass = null;

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
	 * @see anatlyzer.atl.types.TypesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypesPackageImpl() {
		super(eNS_URI, TypesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TypesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TypesPackage init() {
		if (isInited) return (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Obtain or create and register package
		TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TypesPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTypesPackage.createPackageContents();

		// Initialize created meta-data
		theTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TypesPackage.eNS_URI, theTypesPackage);
		return theTypesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getType_Multivalued() {
		return (EAttribute)typeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getType_MetamodelRef() {
		return (EAttribute)typeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_NoCastedType() {
		return (EReference)typeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclUndefinedType() {
		return oclUndefinedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveType() {
		return primitiveTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanType() {
		return booleanTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanType_KindOfTypes() {
		return (EReference)booleanTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerType() {
		return integerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringType() {
		return stringTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFloatType() {
		return floatTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTupleType() {
		return tupleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTupleType_Attributes() {
		return (EReference)tupleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMapType() {
		return mapTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapType_KeyType() {
		return (EReference)mapTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapType_ValueType() {
		return (EReference)mapTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTupleAttribute() {
		return tupleAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTupleAttribute_Name() {
		return (EAttribute)tupleAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTupleAttribute_Type() {
		return (EReference)tupleAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRefType() {
		return refTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnknown() {
		return unknownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmptyCollectionType() {
		return emptyCollectionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeError() {
		return typeErrorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedTypeError() {
		return unresolvedTypeErrorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnknownFeature() {
		return unknownFeatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnknownFeature_TheContainingClass() {
		return (EReference)unknownFeatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmptyCollection() {
		return emptyCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumType() {
		return enumTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumType_Name() {
		return (EAttribute)enumTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumType_Eenum() {
		return (EReference)enumTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetaModel() {
		return metaModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaModel_Name() {
		return (EAttribute)metaModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetaclass() {
		return metaclassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaclass_Name() {
		return (EAttribute)metaclassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaclass_ExplicitOcurrence() {
		return (EAttribute)metaclassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetaclass_Klass() {
		return (EReference)metaclassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetaclass_Model() {
		return (EReference)metaclassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReflectiveType() {
		return reflectiveTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnionType() {
		return unionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionType_PossibleTypes() {
		return (EReference)unionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisModuleType() {
		return thisModuleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReflectiveClass() {
		return reflectiveClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCollectionType() {
		return collectionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCollectionType_ContainedType() {
		return (EReference)collectionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequenceType() {
		return sequenceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSetType() {
		return setTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesFactory getTypesFactory() {
		return (TypesFactory)getEFactoryInstance();
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
		typeEClass = createEClass(TYPE);
		createEAttribute(typeEClass, TYPE__MULTIVALUED);
		createEAttribute(typeEClass, TYPE__METAMODEL_REF);
		createEReference(typeEClass, TYPE__NO_CASTED_TYPE);

		oclUndefinedTypeEClass = createEClass(OCL_UNDEFINED_TYPE);

		primitiveTypeEClass = createEClass(PRIMITIVE_TYPE);

		booleanTypeEClass = createEClass(BOOLEAN_TYPE);
		createEReference(booleanTypeEClass, BOOLEAN_TYPE__KIND_OF_TYPES);

		integerTypeEClass = createEClass(INTEGER_TYPE);

		stringTypeEClass = createEClass(STRING_TYPE);

		floatTypeEClass = createEClass(FLOAT_TYPE);

		tupleTypeEClass = createEClass(TUPLE_TYPE);
		createEReference(tupleTypeEClass, TUPLE_TYPE__ATTRIBUTES);

		mapTypeEClass = createEClass(MAP_TYPE);
		createEReference(mapTypeEClass, MAP_TYPE__KEY_TYPE);
		createEReference(mapTypeEClass, MAP_TYPE__VALUE_TYPE);

		tupleAttributeEClass = createEClass(TUPLE_ATTRIBUTE);
		createEAttribute(tupleAttributeEClass, TUPLE_ATTRIBUTE__NAME);
		createEReference(tupleAttributeEClass, TUPLE_ATTRIBUTE__TYPE);

		refTypeEClass = createEClass(REF_TYPE);

		unknownEClass = createEClass(UNKNOWN);

		emptyCollectionTypeEClass = createEClass(EMPTY_COLLECTION_TYPE);

		typeErrorEClass = createEClass(TYPE_ERROR);

		unresolvedTypeErrorEClass = createEClass(UNRESOLVED_TYPE_ERROR);

		unknownFeatureEClass = createEClass(UNKNOWN_FEATURE);
		createEReference(unknownFeatureEClass, UNKNOWN_FEATURE__THE_CONTAINING_CLASS);

		emptyCollectionEClass = createEClass(EMPTY_COLLECTION);

		enumTypeEClass = createEClass(ENUM_TYPE);
		createEAttribute(enumTypeEClass, ENUM_TYPE__NAME);
		createEReference(enumTypeEClass, ENUM_TYPE__EENUM);

		metaModelEClass = createEClass(META_MODEL);
		createEAttribute(metaModelEClass, META_MODEL__NAME);

		metaclassEClass = createEClass(METACLASS);
		createEAttribute(metaclassEClass, METACLASS__NAME);
		createEAttribute(metaclassEClass, METACLASS__EXPLICIT_OCURRENCE);
		createEReference(metaclassEClass, METACLASS__KLASS);
		createEReference(metaclassEClass, METACLASS__MODEL);

		reflectiveTypeEClass = createEClass(REFLECTIVE_TYPE);

		unionTypeEClass = createEClass(UNION_TYPE);
		createEReference(unionTypeEClass, UNION_TYPE__POSSIBLE_TYPES);

		thisModuleTypeEClass = createEClass(THIS_MODULE_TYPE);

		reflectiveClassEClass = createEClass(REFLECTIVE_CLASS);

		collectionTypeEClass = createEClass(COLLECTION_TYPE);
		createEReference(collectionTypeEClass, COLLECTION_TYPE__CONTAINED_TYPE);

		sequenceTypeEClass = createEClass(SEQUENCE_TYPE);

		setTypeEClass = createEClass(SET_TYPE);
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
		oclUndefinedTypeEClass.getESuperTypes().add(this.getType());
		primitiveTypeEClass.getESuperTypes().add(this.getType());
		booleanTypeEClass.getESuperTypes().add(this.getPrimitiveType());
		integerTypeEClass.getESuperTypes().add(this.getPrimitiveType());
		stringTypeEClass.getESuperTypes().add(this.getPrimitiveType());
		floatTypeEClass.getESuperTypes().add(this.getPrimitiveType());
		tupleTypeEClass.getESuperTypes().add(this.getType());
		mapTypeEClass.getESuperTypes().add(this.getType());
		refTypeEClass.getESuperTypes().add(this.getType());
		unknownEClass.getESuperTypes().add(this.getRefType());
		emptyCollectionTypeEClass.getESuperTypes().add(this.getType());
		typeErrorEClass.getESuperTypes().add(this.getType());
		unresolvedTypeErrorEClass.getESuperTypes().add(this.getTypeError());
		unresolvedTypeErrorEClass.getESuperTypes().add(this.getMetaclass());
		unknownFeatureEClass.getESuperTypes().add(ecorePackage.getEStructuralFeature());
		emptyCollectionEClass.getESuperTypes().add(this.getType());
		enumTypeEClass.getESuperTypes().add(this.getType());
		metaclassEClass.getESuperTypes().add(this.getRefType());
		reflectiveTypeEClass.getESuperTypes().add(this.getType());
		unionTypeEClass.getESuperTypes().add(this.getType());
		thisModuleTypeEClass.getESuperTypes().add(this.getType());
		reflectiveClassEClass.getESuperTypes().add(this.getReflectiveType());
		collectionTypeEClass.getESuperTypes().add(this.getType());
		sequenceTypeEClass.getESuperTypes().add(this.getCollectionType());
		setTypeEClass.getESuperTypes().add(this.getCollectionType());

		// Initialize classes, features, and operations; add parameters
		initEClass(typeEClass, Type.class, "Type", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getType_Multivalued(), ecorePackage.getEBoolean(), "multivalued", "false", 1, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getType_MetamodelRef(), ecorePackage.getEJavaObject(), "metamodelRef", null, 0, 1, Type.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getType_NoCastedType(), this.getType(), null, "noCastedType", null, 1, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oclUndefinedTypeEClass, OclUndefinedType.class, "OclUndefinedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeEClass, PrimitiveType.class, "PrimitiveType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanTypeEClass, BooleanType.class, "BooleanType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBooleanType_KindOfTypes(), this.getMetaclass(), null, "kindOfTypes", null, 0, -1, BooleanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerTypeEClass, IntegerType.class, "IntegerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringTypeEClass, StringType.class, "StringType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(floatTypeEClass, FloatType.class, "FloatType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tupleTypeEClass, TupleType.class, "TupleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTupleType_Attributes(), this.getTupleAttribute(), null, "attributes", null, 0, -1, TupleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mapTypeEClass, MapType.class, "MapType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMapType_KeyType(), this.getType(), null, "keyType", null, 1, 1, MapType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMapType_ValueType(), this.getType(), null, "valueType", null, 1, 1, MapType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tupleAttributeEClass, TupleAttribute.class, "TupleAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTupleAttribute_Name(), ecorePackage.getEString(), "name", null, 1, 1, TupleAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTupleAttribute_Type(), this.getType(), null, "type", null, 1, 1, TupleAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(refTypeEClass, RefType.class, "RefType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unknownEClass, Unknown.class, "Unknown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(emptyCollectionTypeEClass, EmptyCollectionType.class, "EmptyCollectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(typeErrorEClass, TypeError.class, "TypeError", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedTypeErrorEClass, UnresolvedTypeError.class, "UnresolvedTypeError", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unknownFeatureEClass, UnknownFeature.class, "UnknownFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnknownFeature_TheContainingClass(), ecorePackage.getEClass(), null, "theContainingClass", null, 1, 1, UnknownFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(emptyCollectionEClass, EmptyCollection.class, "EmptyCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(enumTypeEClass, EnumType.class, "EnumType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumType_Name(), ecorePackage.getEString(), "name", null, 1, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnumType_Eenum(), ecorePackage.getEObject(), null, "eenum", null, 1, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metaModelEClass, MetaModel.class, "MetaModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaModel_Name(), ecorePackage.getEString(), "name", null, 1, 1, MetaModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metaclassEClass, Metaclass.class, "Metaclass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaclass_Name(), ecorePackage.getEString(), "name", null, 1, 1, Metaclass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaclass_ExplicitOcurrence(), ecorePackage.getEBoolean(), "explicitOcurrence", null, 0, 1, Metaclass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetaclass_Klass(), ecorePackage.getEClass(), null, "klass", null, 1, 1, Metaclass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetaclass_Model(), this.getMetaModel(), null, "model", null, 1, 1, Metaclass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reflectiveTypeEClass, ReflectiveType.class, "ReflectiveType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unionTypeEClass, UnionType.class, "UnionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnionType_PossibleTypes(), this.getType(), null, "possibleTypes", null, 0, -1, UnionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(thisModuleTypeEClass, ThisModuleType.class, "ThisModuleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(reflectiveClassEClass, ReflectiveClass.class, "ReflectiveClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(collectionTypeEClass, CollectionType.class, "CollectionType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCollectionType_ContainedType(), this.getType(), null, "containedType", null, 1, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sequenceTypeEClass, SequenceType.class, "SequenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(setTypeEClass, SetType.class, "SetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TypesPackageImpl
