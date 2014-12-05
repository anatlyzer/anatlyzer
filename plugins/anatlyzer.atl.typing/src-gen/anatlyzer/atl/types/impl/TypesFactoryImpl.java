/**
 */
package anatlyzer.atl.types.impl;

import anatlyzer.atl.types.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesFactoryImpl extends EFactoryImpl implements TypesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypesFactory init() {
		try {
			TypesFactory theTypesFactory = (TypesFactory)EPackage.Registry.INSTANCE.getEFactory(TypesPackage.eNS_URI);
			if (theTypesFactory != null) {
				return theTypesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TypesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TypesPackage.OCL_UNDEFINED_TYPE: return createOclUndefinedType();
			case TypesPackage.BOOLEAN_TYPE: return createBooleanType();
			case TypesPackage.INTEGER_TYPE: return createIntegerType();
			case TypesPackage.STRING_TYPE: return createStringType();
			case TypesPackage.FLOAT_TYPE: return createFloatType();
			case TypesPackage.TUPLE_TYPE: return createTupleType();
			case TypesPackage.MAP_TYPE: return createMapType();
			case TypesPackage.TUPLE_ATTRIBUTE: return createTupleAttribute();
			case TypesPackage.REF_TYPE: return createRefType();
			case TypesPackage.UNKNOWN: return createUnknown();
			case TypesPackage.EMPTY_COLLECTION_TYPE: return createEmptyCollectionType();
			case TypesPackage.TYPE_ERROR: return createTypeError();
			case TypesPackage.UNRESOLVED_TYPE_ERROR: return createUnresolvedTypeError();
			case TypesPackage.UNKNOWN_FEATURE: return createUnknownFeature();
			case TypesPackage.EMPTY_COLLECTION: return createEmptyCollection();
			case TypesPackage.ENUM_TYPE: return createEnumType();
			case TypesPackage.META_MODEL: return createMetaModel();
			case TypesPackage.METACLASS: return createMetaclass();
			case TypesPackage.UNION_TYPE: return createUnionType();
			case TypesPackage.THIS_MODULE_TYPE: return createThisModuleType();
			case TypesPackage.REFLECTIVE_CLASS: return createReflectiveClass();
			case TypesPackage.SEQUENCE_TYPE: return createSequenceType();
			case TypesPackage.SET_TYPE: return createSetType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclUndefinedType createOclUndefinedType() {
		OclUndefinedTypeImpl oclUndefinedType = new OclUndefinedTypeImpl();
		return oclUndefinedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType createBooleanType() {
		BooleanTypeImpl booleanType = new BooleanTypeImpl();
		return booleanType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerType createIntegerType() {
		IntegerTypeImpl integerType = new IntegerTypeImpl();
		return integerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringType createStringType() {
		StringTypeImpl stringType = new StringTypeImpl();
		return stringType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatType createFloatType() {
		FloatTypeImpl floatType = new FloatTypeImpl();
		return floatType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleType createTupleType() {
		TupleTypeImpl tupleType = new TupleTypeImpl();
		return tupleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapType createMapType() {
		MapTypeImpl mapType = new MapTypeImpl();
		return mapType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleAttribute createTupleAttribute() {
		TupleAttributeImpl tupleAttribute = new TupleAttributeImpl();
		return tupleAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefType createRefType() {
		RefTypeImpl refType = new RefTypeImpl();
		return refType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Unknown createUnknown() {
		UnknownImpl unknown = new UnknownImpl();
		return unknown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyCollectionType createEmptyCollectionType() {
		EmptyCollectionTypeImpl emptyCollectionType = new EmptyCollectionTypeImpl();
		return emptyCollectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeError createTypeError() {
		TypeErrorImpl typeError = new TypeErrorImpl();
		return typeError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedTypeError createUnresolvedTypeError() {
		UnresolvedTypeErrorImpl unresolvedTypeError = new UnresolvedTypeErrorImpl();
		return unresolvedTypeError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnknownFeature createUnknownFeature() {
		UnknownFeatureImpl unknownFeature = new UnknownFeatureImpl();
		return unknownFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyCollection createEmptyCollection() {
		EmptyCollectionImpl emptyCollection = new EmptyCollectionImpl();
		return emptyCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumType createEnumType() {
		EnumTypeImpl enumType = new EnumTypeImpl();
		return enumType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaModel createMetaModel() {
		MetaModelImpl metaModel = new MetaModelImpl();
		return metaModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Metaclass createMetaclass() {
		MetaclassImpl metaclass = new MetaclassImpl();
		return metaclass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionType createUnionType() {
		UnionTypeImpl unionType = new UnionTypeImpl();
		return unionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThisModuleType createThisModuleType() {
		ThisModuleTypeImpl thisModuleType = new ThisModuleTypeImpl();
		return thisModuleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReflectiveClass createReflectiveClass() {
		ReflectiveClassImpl reflectiveClass = new ReflectiveClassImpl();
		return reflectiveClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceType createSequenceType() {
		SequenceTypeImpl sequenceType = new SequenceTypeImpl();
		return sequenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetType createSetType() {
		SetTypeImpl setType = new SetTypeImpl();
		return setType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesPackage getTypesPackage() {
		return (TypesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TypesPackage getPackage() {
		return TypesPackage.eINSTANCE;
	}

} //TypesFactoryImpl
