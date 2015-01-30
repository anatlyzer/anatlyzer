/**
 */
package anatlyzer.atl.types;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

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
 * @see anatlyzer.atl.types.TypesFactory
 * @model kind="package"
 * @generated
 */
public interface TypesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "types";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/atl/types";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_types";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypesPackage eINSTANCE = anatlyzer.atl.types.impl.TypesPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.TypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 0;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__MULTIVALUED = 0;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__METAMODEL_REF = 1;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__NO_CASTED_TYPE = 2;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.OclUndefinedTypeImpl <em>Ocl Undefined Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.OclUndefinedTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getOclUndefinedType()
	 * @generated
	 */
	int OCL_UNDEFINED_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Ocl Undefined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Ocl Undefined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.PrimitiveTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getPrimitiveType()
	 * @generated
	 */
	int PRIMITIVE_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.BooleanTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getBooleanType()
	 * @generated
	 */
	int BOOLEAN_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__MULTIVALUED = PRIMITIVE_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__METAMODEL_REF = PRIMITIVE_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__NO_CASTED_TYPE = PRIMITIVE_TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Kind Of Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__KIND_OF_TYPES = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE_OPERATION_COUNT = PRIMITIVE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.IntegerTypeImpl <em>Integer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.IntegerTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getIntegerType()
	 * @generated
	 */
	int INTEGER_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__MULTIVALUED = PRIMITIVE_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__METAMODEL_REF = PRIMITIVE_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__NO_CASTED_TYPE = PRIMITIVE_TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Integer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Integer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE_OPERATION_COUNT = PRIMITIVE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.StringTypeImpl <em>String Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.StringTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getStringType()
	 * @generated
	 */
	int STRING_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__MULTIVALUED = PRIMITIVE_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__METAMODEL_REF = PRIMITIVE_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__NO_CASTED_TYPE = PRIMITIVE_TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>String Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>String Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE_OPERATION_COUNT = PRIMITIVE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.FloatTypeImpl <em>Float Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.FloatTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getFloatType()
	 * @generated
	 */
	int FLOAT_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_TYPE__MULTIVALUED = PRIMITIVE_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_TYPE__METAMODEL_REF = PRIMITIVE_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_TYPE__NO_CASTED_TYPE = PRIMITIVE_TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Float Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_TYPE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Float Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_TYPE_OPERATION_COUNT = PRIMITIVE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.TupleTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getTupleType()
	 * @generated
	 */
	int TUPLE_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__ATTRIBUTES = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tuple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tuple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.MapTypeImpl <em>Map Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.MapTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getMapType()
	 * @generated
	 */
	int MAP_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Key Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__KEY_TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__VALUE_TYPE = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Map Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Map Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.TupleAttributeImpl <em>Tuple Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.TupleAttributeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getTupleAttribute()
	 * @generated
	 */
	int TUPLE_ATTRIBUTE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_ATTRIBUTE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_ATTRIBUTE__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Tuple Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_ATTRIBUTE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tuple Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.RefTypeImpl <em>Ref Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.RefTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getRefType()
	 * @generated
	 */
	int REF_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Ref Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Ref Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.UnknownImpl <em>Unknown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.UnknownImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnknown()
	 * @generated
	 */
	int UNKNOWN = 11;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__MULTIVALUED = REF_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__METAMODEL_REF = REF_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__NO_CASTED_TYPE = REF_TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Unknown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE_COUNT = REF_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Unknown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_OPERATION_COUNT = REF_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.EmptyCollectionTypeImpl <em>Empty Collection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.EmptyCollectionTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getEmptyCollectionType()
	 * @generated
	 */
	int EMPTY_COLLECTION_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Empty Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Empty Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.TypeErrorImpl <em>Type Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.TypeErrorImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getTypeError()
	 * @generated
	 */
	int TYPE_ERROR = 13;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ERROR__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ERROR__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ERROR__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Type Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ERROR_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Type Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ERROR_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.UnresolvedTypeErrorImpl <em>Unresolved Type Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.UnresolvedTypeErrorImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnresolvedTypeError()
	 * @generated
	 */
	int UNRESOLVED_TYPE_ERROR = 14;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR__MULTIVALUED = TYPE_ERROR__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR__METAMODEL_REF = TYPE_ERROR__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR__NO_CASTED_TYPE = TYPE_ERROR__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR__NAME = TYPE_ERROR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Explicit Ocurrence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR__EXPLICIT_OCURRENCE = TYPE_ERROR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Klass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR__KLASS = TYPE_ERROR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR__MODEL = TYPE_ERROR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Unresolved Type Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR_FEATURE_COUNT = TYPE_ERROR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Unresolved Type Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_TYPE_ERROR_OPERATION_COUNT = TYPE_ERROR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.UnknownFeatureImpl <em>Unknown Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.UnknownFeatureImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnknownFeature()
	 * @generated
	 */
	int UNKNOWN_FEATURE = 15;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__EANNOTATIONS = EcorePackage.ESTRUCTURAL_FEATURE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__NAME = EcorePackage.ESTRUCTURAL_FEATURE__NAME;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__ORDERED = EcorePackage.ESTRUCTURAL_FEATURE__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__UNIQUE = EcorePackage.ESTRUCTURAL_FEATURE__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__LOWER_BOUND = EcorePackage.ESTRUCTURAL_FEATURE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__UPPER_BOUND = EcorePackage.ESTRUCTURAL_FEATURE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__MANY = EcorePackage.ESTRUCTURAL_FEATURE__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__REQUIRED = EcorePackage.ESTRUCTURAL_FEATURE__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__ETYPE = EcorePackage.ESTRUCTURAL_FEATURE__ETYPE;

	/**
	 * The feature id for the '<em><b>EGeneric Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__EGENERIC_TYPE = EcorePackage.ESTRUCTURAL_FEATURE__EGENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__CHANGEABLE = EcorePackage.ESTRUCTURAL_FEATURE__CHANGEABLE;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__VOLATILE = EcorePackage.ESTRUCTURAL_FEATURE__VOLATILE;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__TRANSIENT = EcorePackage.ESTRUCTURAL_FEATURE__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Default Value Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__DEFAULT_VALUE_LITERAL = EcorePackage.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__DEFAULT_VALUE = EcorePackage.ESTRUCTURAL_FEATURE__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Unsettable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__UNSETTABLE = EcorePackage.ESTRUCTURAL_FEATURE__UNSETTABLE;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__DERIVED = EcorePackage.ESTRUCTURAL_FEATURE__DERIVED;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__ECONTAINING_CLASS = EcorePackage.ESTRUCTURAL_FEATURE__ECONTAINING_CLASS;

	/**
	 * The feature id for the '<em><b>The Containing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE__THE_CONTAINING_CLASS = EcorePackage.ESTRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unknown Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE_FEATURE_COUNT = EcorePackage.ESTRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get EAnnotation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE___GET_EANNOTATION__STRING = EcorePackage.ESTRUCTURAL_FEATURE___GET_EANNOTATION__STRING;

	/**
	 * The operation id for the '<em>Get Feature ID</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE___GET_FEATURE_ID = EcorePackage.ESTRUCTURAL_FEATURE___GET_FEATURE_ID;

	/**
	 * The operation id for the '<em>Get Container Class</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE___GET_CONTAINER_CLASS = EcorePackage.ESTRUCTURAL_FEATURE___GET_CONTAINER_CLASS;

	/**
	 * The number of operations of the '<em>Unknown Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE_OPERATION_COUNT = EcorePackage.ESTRUCTURAL_FEATURE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.EmptyCollectionImpl <em>Empty Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.EmptyCollectionImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getEmptyCollection()
	 * @generated
	 */
	int EMPTY_COLLECTION = 16;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Empty Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Empty Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_COLLECTION_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.EnumTypeImpl <em>Enum Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.EnumTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getEnumType()
	 * @generated
	 */
	int ENUM_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__NAME = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Eenum</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__EENUM = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Enum Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Enum Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.MetaModelImpl <em>Meta Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.MetaModelImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getMetaModel()
	 * @generated
	 */
	int META_MODEL = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_MODEL__NAME = 0;

	/**
	 * The number of structural features of the '<em>Meta Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Meta Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.MetaclassImpl <em>Metaclass</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.MetaclassImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getMetaclass()
	 * @generated
	 */
	int METACLASS = 19;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS__MULTIVALUED = REF_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS__METAMODEL_REF = REF_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS__NO_CASTED_TYPE = REF_TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS__NAME = REF_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Explicit Ocurrence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS__EXPLICIT_OCURRENCE = REF_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Klass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS__KLASS = REF_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS__MODEL = REF_TYPE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Metaclass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS_FEATURE_COUNT = REF_TYPE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Metaclass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METACLASS_OPERATION_COUNT = REF_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.ReflectiveTypeImpl <em>Reflective Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.ReflectiveTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getReflectiveType()
	 * @generated
	 */
	int REFLECTIVE_TYPE = 20;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Reflective Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Reflective Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.UnionTypeImpl <em>Union Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.UnionTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnionType()
	 * @generated
	 */
	int UNION_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Possible Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__POSSIBLE_TYPES = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Union Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Union Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.ThisModuleTypeImpl <em>This Module Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.ThisModuleTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getThisModuleType()
	 * @generated
	 */
	int THIS_MODULE_TYPE = 22;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_MODULE_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_MODULE_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_MODULE_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>This Module Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_MODULE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>This Module Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_MODULE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.ReflectiveClassImpl <em>Reflective Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.ReflectiveClassImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getReflectiveClass()
	 * @generated
	 */
	int REFLECTIVE_CLASS = 23;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_CLASS__MULTIVALUED = REFLECTIVE_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_CLASS__METAMODEL_REF = REFLECTIVE_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_CLASS__NO_CASTED_TYPE = REFLECTIVE_TYPE__NO_CASTED_TYPE;

	/**
	 * The number of structural features of the '<em>Reflective Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_CLASS_FEATURE_COUNT = REFLECTIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Reflective Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFLECTIVE_CLASS_OPERATION_COUNT = REFLECTIVE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.CollectionTypeImpl <em>Collection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.CollectionTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getCollectionType()
	 * @generated
	 */
	int COLLECTION_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__MULTIVALUED = TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__METAMODEL_REF = TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__NO_CASTED_TYPE = TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__CONTAINED_TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.SequenceTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getSequenceType()
	 * @generated
	 */
	int SEQUENCE_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__MULTIVALUED = COLLECTION_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__METAMODEL_REF = COLLECTION_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__NO_CASTED_TYPE = COLLECTION_TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__CONTAINED_TYPE = COLLECTION_TYPE__CONTAINED_TYPE;

	/**
	 * The number of structural features of the '<em>Sequence Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sequence Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE_OPERATION_COUNT = COLLECTION_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atl.types.impl.SetTypeImpl <em>Set Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atl.types.impl.SetTypeImpl
	 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getSetType()
	 * @generated
	 */
	int SET_TYPE = 26;

	/**
	 * The feature id for the '<em><b>Multivalued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__MULTIVALUED = COLLECTION_TYPE__MULTIVALUED;

	/**
	 * The feature id for the '<em><b>Metamodel Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__METAMODEL_REF = COLLECTION_TYPE__METAMODEL_REF;

	/**
	 * The feature id for the '<em><b>No Casted Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__NO_CASTED_TYPE = COLLECTION_TYPE__NO_CASTED_TYPE;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__CONTAINED_TYPE = COLLECTION_TYPE__CONTAINED_TYPE;

	/**
	 * The number of structural features of the '<em>Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE_OPERATION_COUNT = COLLECTION_TYPE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see anatlyzer.atl.types.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.types.Type#isMultivalued <em>Multivalued</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multivalued</em>'.
	 * @see anatlyzer.atl.types.Type#isMultivalued()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_Multivalued();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.types.Type#getMetamodelRef <em>Metamodel Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metamodel Ref</em>'.
	 * @see anatlyzer.atl.types.Type#getMetamodelRef()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_MetamodelRef();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.Type#getNoCastedType <em>No Casted Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>No Casted Type</em>'.
	 * @see anatlyzer.atl.types.Type#getNoCastedType()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_NoCastedType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.OclUndefinedType <em>Ocl Undefined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Undefined Type</em>'.
	 * @see anatlyzer.atl.types.OclUndefinedType
	 * @generated
	 */
	EClass getOclUndefinedType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type</em>'.
	 * @see anatlyzer.atl.types.PrimitiveType
	 * @generated
	 */
	EClass getPrimitiveType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.BooleanType <em>Boolean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Type</em>'.
	 * @see anatlyzer.atl.types.BooleanType
	 * @generated
	 */
	EClass getBooleanType();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.types.BooleanType#getKindOfTypes <em>Kind Of Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Kind Of Types</em>'.
	 * @see anatlyzer.atl.types.BooleanType#getKindOfTypes()
	 * @see #getBooleanType()
	 * @generated
	 */
	EReference getBooleanType_KindOfTypes();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.IntegerType <em>Integer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Type</em>'.
	 * @see anatlyzer.atl.types.IntegerType
	 * @generated
	 */
	EClass getIntegerType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.StringType <em>String Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Type</em>'.
	 * @see anatlyzer.atl.types.StringType
	 * @generated
	 */
	EClass getStringType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.FloatType <em>Float Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Type</em>'.
	 * @see anatlyzer.atl.types.FloatType
	 * @generated
	 */
	EClass getFloatType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.TupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Type</em>'.
	 * @see anatlyzer.atl.types.TupleType
	 * @generated
	 */
	EClass getTupleType();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atl.types.TupleType#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see anatlyzer.atl.types.TupleType#getAttributes()
	 * @see #getTupleType()
	 * @generated
	 */
	EReference getTupleType_Attributes();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.MapType <em>Map Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Type</em>'.
	 * @see anatlyzer.atl.types.MapType
	 * @generated
	 */
	EClass getMapType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.MapType#getKeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key Type</em>'.
	 * @see anatlyzer.atl.types.MapType#getKeyType()
	 * @see #getMapType()
	 * @generated
	 */
	EReference getMapType_KeyType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.MapType#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value Type</em>'.
	 * @see anatlyzer.atl.types.MapType#getValueType()
	 * @see #getMapType()
	 * @generated
	 */
	EReference getMapType_ValueType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.TupleAttribute <em>Tuple Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Attribute</em>'.
	 * @see anatlyzer.atl.types.TupleAttribute
	 * @generated
	 */
	EClass getTupleAttribute();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.types.TupleAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atl.types.TupleAttribute#getName()
	 * @see #getTupleAttribute()
	 * @generated
	 */
	EAttribute getTupleAttribute_Name();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.TupleAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see anatlyzer.atl.types.TupleAttribute#getType()
	 * @see #getTupleAttribute()
	 * @generated
	 */
	EReference getTupleAttribute_Type();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.RefType <em>Ref Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ref Type</em>'.
	 * @see anatlyzer.atl.types.RefType
	 * @generated
	 */
	EClass getRefType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.Unknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unknown</em>'.
	 * @see anatlyzer.atl.types.Unknown
	 * @generated
	 */
	EClass getUnknown();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.EmptyCollectionType <em>Empty Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Empty Collection Type</em>'.
	 * @see anatlyzer.atl.types.EmptyCollectionType
	 * @generated
	 */
	EClass getEmptyCollectionType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.TypeError <em>Type Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Error</em>'.
	 * @see anatlyzer.atl.types.TypeError
	 * @generated
	 */
	EClass getTypeError();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.UnresolvedTypeError <em>Unresolved Type Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unresolved Type Error</em>'.
	 * @see anatlyzer.atl.types.UnresolvedTypeError
	 * @generated
	 */
	EClass getUnresolvedTypeError();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.UnknownFeature <em>Unknown Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unknown Feature</em>'.
	 * @see anatlyzer.atl.types.UnknownFeature
	 * @generated
	 */
	EClass getUnknownFeature();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.UnknownFeature#getTheContainingClass <em>The Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>The Containing Class</em>'.
	 * @see anatlyzer.atl.types.UnknownFeature#getTheContainingClass()
	 * @see #getUnknownFeature()
	 * @generated
	 */
	EReference getUnknownFeature_TheContainingClass();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.EmptyCollection <em>Empty Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Empty Collection</em>'.
	 * @see anatlyzer.atl.types.EmptyCollection
	 * @generated
	 */
	EClass getEmptyCollection();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.EnumType <em>Enum Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Type</em>'.
	 * @see anatlyzer.atl.types.EnumType
	 * @generated
	 */
	EClass getEnumType();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.types.EnumType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atl.types.EnumType#getName()
	 * @see #getEnumType()
	 * @generated
	 */
	EAttribute getEnumType_Name();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.EnumType#getEenum <em>Eenum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Eenum</em>'.
	 * @see anatlyzer.atl.types.EnumType#getEenum()
	 * @see #getEnumType()
	 * @generated
	 */
	EReference getEnumType_Eenum();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.MetaModel <em>Meta Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Meta Model</em>'.
	 * @see anatlyzer.atl.types.MetaModel
	 * @generated
	 */
	EClass getMetaModel();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.types.MetaModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atl.types.MetaModel#getName()
	 * @see #getMetaModel()
	 * @generated
	 */
	EAttribute getMetaModel_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.Metaclass <em>Metaclass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metaclass</em>'.
	 * @see anatlyzer.atl.types.Metaclass
	 * @generated
	 */
	EClass getMetaclass();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.types.Metaclass#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atl.types.Metaclass#getName()
	 * @see #getMetaclass()
	 * @generated
	 */
	EAttribute getMetaclass_Name();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atl.types.Metaclass#isExplicitOcurrence <em>Explicit Ocurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Explicit Ocurrence</em>'.
	 * @see anatlyzer.atl.types.Metaclass#isExplicitOcurrence()
	 * @see #getMetaclass()
	 * @generated
	 */
	EAttribute getMetaclass_ExplicitOcurrence();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.Metaclass#getKlass <em>Klass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Klass</em>'.
	 * @see anatlyzer.atl.types.Metaclass#getKlass()
	 * @see #getMetaclass()
	 * @generated
	 */
	EReference getMetaclass_Klass();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.Metaclass#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model</em>'.
	 * @see anatlyzer.atl.types.Metaclass#getModel()
	 * @see #getMetaclass()
	 * @generated
	 */
	EReference getMetaclass_Model();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.ReflectiveType <em>Reflective Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reflective Type</em>'.
	 * @see anatlyzer.atl.types.ReflectiveType
	 * @generated
	 */
	EClass getReflectiveType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.UnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Type</em>'.
	 * @see anatlyzer.atl.types.UnionType
	 * @generated
	 */
	EClass getUnionType();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atl.types.UnionType#getPossibleTypes <em>Possible Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Possible Types</em>'.
	 * @see anatlyzer.atl.types.UnionType#getPossibleTypes()
	 * @see #getUnionType()
	 * @generated
	 */
	EReference getUnionType_PossibleTypes();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.ThisModuleType <em>This Module Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>This Module Type</em>'.
	 * @see anatlyzer.atl.types.ThisModuleType
	 * @generated
	 */
	EClass getThisModuleType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.ReflectiveClass <em>Reflective Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reflective Class</em>'.
	 * @see anatlyzer.atl.types.ReflectiveClass
	 * @generated
	 */
	EClass getReflectiveClass();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.CollectionType <em>Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Type</em>'.
	 * @see anatlyzer.atl.types.CollectionType
	 * @generated
	 */
	EClass getCollectionType();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atl.types.CollectionType#getContainedType <em>Contained Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contained Type</em>'.
	 * @see anatlyzer.atl.types.CollectionType#getContainedType()
	 * @see #getCollectionType()
	 * @generated
	 */
	EReference getCollectionType_ContainedType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Type</em>'.
	 * @see anatlyzer.atl.types.SequenceType
	 * @generated
	 */
	EClass getSequenceType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atl.types.SetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Type</em>'.
	 * @see anatlyzer.atl.types.SetType
	 * @generated
	 */
	EClass getSetType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypesFactory getTypesFactory();

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
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.TypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '<em><b>Multivalued</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE__MULTIVALUED = eINSTANCE.getType_Multivalued();

		/**
		 * The meta object literal for the '<em><b>Metamodel Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE__METAMODEL_REF = eINSTANCE.getType_MetamodelRef();

		/**
		 * The meta object literal for the '<em><b>No Casted Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE__NO_CASTED_TYPE = eINSTANCE.getType_NoCastedType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.OclUndefinedTypeImpl <em>Ocl Undefined Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.OclUndefinedTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getOclUndefinedType()
		 * @generated
		 */
		EClass OCL_UNDEFINED_TYPE = eINSTANCE.getOclUndefinedType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.PrimitiveTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.BooleanTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getBooleanType()
		 * @generated
		 */
		EClass BOOLEAN_TYPE = eINSTANCE.getBooleanType();

		/**
		 * The meta object literal for the '<em><b>Kind Of Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_TYPE__KIND_OF_TYPES = eINSTANCE.getBooleanType_KindOfTypes();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.IntegerTypeImpl <em>Integer Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.IntegerTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getIntegerType()
		 * @generated
		 */
		EClass INTEGER_TYPE = eINSTANCE.getIntegerType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.StringTypeImpl <em>String Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.StringTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getStringType()
		 * @generated
		 */
		EClass STRING_TYPE = eINSTANCE.getStringType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.FloatTypeImpl <em>Float Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.FloatTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getFloatType()
		 * @generated
		 */
		EClass FLOAT_TYPE = eINSTANCE.getFloatType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.TupleTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getTupleType()
		 * @generated
		 */
		EClass TUPLE_TYPE = eINSTANCE.getTupleType();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_TYPE__ATTRIBUTES = eINSTANCE.getTupleType_Attributes();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.MapTypeImpl <em>Map Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.MapTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getMapType()
		 * @generated
		 */
		EClass MAP_TYPE = eINSTANCE.getMapType();

		/**
		 * The meta object literal for the '<em><b>Key Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_TYPE__KEY_TYPE = eINSTANCE.getMapType_KeyType();

		/**
		 * The meta object literal for the '<em><b>Value Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_TYPE__VALUE_TYPE = eINSTANCE.getMapType_ValueType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.TupleAttributeImpl <em>Tuple Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.TupleAttributeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getTupleAttribute()
		 * @generated
		 */
		EClass TUPLE_ATTRIBUTE = eINSTANCE.getTupleAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUPLE_ATTRIBUTE__NAME = eINSTANCE.getTupleAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_ATTRIBUTE__TYPE = eINSTANCE.getTupleAttribute_Type();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.RefTypeImpl <em>Ref Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.RefTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getRefType()
		 * @generated
		 */
		EClass REF_TYPE = eINSTANCE.getRefType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.UnknownImpl <em>Unknown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.UnknownImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnknown()
		 * @generated
		 */
		EClass UNKNOWN = eINSTANCE.getUnknown();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.EmptyCollectionTypeImpl <em>Empty Collection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.EmptyCollectionTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getEmptyCollectionType()
		 * @generated
		 */
		EClass EMPTY_COLLECTION_TYPE = eINSTANCE.getEmptyCollectionType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.TypeErrorImpl <em>Type Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.TypeErrorImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getTypeError()
		 * @generated
		 */
		EClass TYPE_ERROR = eINSTANCE.getTypeError();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.UnresolvedTypeErrorImpl <em>Unresolved Type Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.UnresolvedTypeErrorImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnresolvedTypeError()
		 * @generated
		 */
		EClass UNRESOLVED_TYPE_ERROR = eINSTANCE.getUnresolvedTypeError();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.UnknownFeatureImpl <em>Unknown Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.UnknownFeatureImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnknownFeature()
		 * @generated
		 */
		EClass UNKNOWN_FEATURE = eINSTANCE.getUnknownFeature();

		/**
		 * The meta object literal for the '<em><b>The Containing Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNKNOWN_FEATURE__THE_CONTAINING_CLASS = eINSTANCE.getUnknownFeature_TheContainingClass();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.EmptyCollectionImpl <em>Empty Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.EmptyCollectionImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getEmptyCollection()
		 * @generated
		 */
		EClass EMPTY_COLLECTION = eINSTANCE.getEmptyCollection();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.EnumTypeImpl <em>Enum Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.EnumTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getEnumType()
		 * @generated
		 */
		EClass ENUM_TYPE = eINSTANCE.getEnumType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUM_TYPE__NAME = eINSTANCE.getEnumType_Name();

		/**
		 * The meta object literal for the '<em><b>Eenum</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_TYPE__EENUM = eINSTANCE.getEnumType_Eenum();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.MetaModelImpl <em>Meta Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.MetaModelImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getMetaModel()
		 * @generated
		 */
		EClass META_MODEL = eINSTANCE.getMetaModel();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute META_MODEL__NAME = eINSTANCE.getMetaModel_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.MetaclassImpl <em>Metaclass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.MetaclassImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getMetaclass()
		 * @generated
		 */
		EClass METACLASS = eINSTANCE.getMetaclass();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METACLASS__NAME = eINSTANCE.getMetaclass_Name();

		/**
		 * The meta object literal for the '<em><b>Explicit Ocurrence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METACLASS__EXPLICIT_OCURRENCE = eINSTANCE.getMetaclass_ExplicitOcurrence();

		/**
		 * The meta object literal for the '<em><b>Klass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METACLASS__KLASS = eINSTANCE.getMetaclass_Klass();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METACLASS__MODEL = eINSTANCE.getMetaclass_Model();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.ReflectiveTypeImpl <em>Reflective Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.ReflectiveTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getReflectiveType()
		 * @generated
		 */
		EClass REFLECTIVE_TYPE = eINSTANCE.getReflectiveType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.UnionTypeImpl <em>Union Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.UnionTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getUnionType()
		 * @generated
		 */
		EClass UNION_TYPE = eINSTANCE.getUnionType();

		/**
		 * The meta object literal for the '<em><b>Possible Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_TYPE__POSSIBLE_TYPES = eINSTANCE.getUnionType_PossibleTypes();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.ThisModuleTypeImpl <em>This Module Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.ThisModuleTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getThisModuleType()
		 * @generated
		 */
		EClass THIS_MODULE_TYPE = eINSTANCE.getThisModuleType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.ReflectiveClassImpl <em>Reflective Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.ReflectiveClassImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getReflectiveClass()
		 * @generated
		 */
		EClass REFLECTIVE_CLASS = eINSTANCE.getReflectiveClass();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.CollectionTypeImpl <em>Collection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.CollectionTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getCollectionType()
		 * @generated
		 */
		EClass COLLECTION_TYPE = eINSTANCE.getCollectionType();

		/**
		 * The meta object literal for the '<em><b>Contained Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_TYPE__CONTAINED_TYPE = eINSTANCE.getCollectionType_ContainedType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.SequenceTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getSequenceType()
		 * @generated
		 */
		EClass SEQUENCE_TYPE = eINSTANCE.getSequenceType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atl.types.impl.SetTypeImpl <em>Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atl.types.impl.SetTypeImpl
		 * @see anatlyzer.atl.types.impl.TypesPackageImpl#getSetType()
		 * @generated
		 */
		EClass SET_TYPE = eINSTANCE.getSetType();

	}

} //TypesPackage
