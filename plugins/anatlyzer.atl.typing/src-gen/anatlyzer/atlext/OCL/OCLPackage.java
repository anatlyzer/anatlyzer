/**
 */
package anatlyzer.atlext.OCL;

import anatlyzer.atlext.ATL.ATLPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see anatlyzer.atlext.OCL.OCLFactory
 * @model kind="package"
 * @generated
 */
public interface OCLPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "OCL";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/atl/ext/OCL";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "atl_ext_ocl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OCLPackage eINSTANCE = anatlyzer.atlext.OCL.impl.OCLPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl <em>Ocl Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclExpressionImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclExpression()
	 * @generated
	 */
	int OCL_EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__INFERRED_TYPE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__TYPE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__IF_EXP3 = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__APPLIED_PROPERTY = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__COLLECTION = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__LET_EXP = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__LOOP_EXP = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__PARENT_OPERATION = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__INITIALIZED_VARIABLE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__IF_EXP2 = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__OWNING_OPERATION = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__IF_EXP1 = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__OWNING_ATTRIBUTE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION__IMPLICITLY_CASTED = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Ocl Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The number of operations of the '<em>Ocl Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_EXPRESSION_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.VariableExpImpl <em>Variable Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.VariableExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getVariableExp()
	 * @generated
	 */
	int VARIABLE_EXP = 1;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Referred Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP__REFERRED_VARIABLE = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Variable Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.SuperExpImpl <em>Super Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.SuperExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSuperExp()
	 * @generated
	 */
	int SUPER_EXP = 2;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The number of structural features of the '<em>Super Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Super Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.PrimitiveExpImpl <em>Primitive Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.PrimitiveExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getPrimitiveExp()
	 * @generated
	 */
	int PRIMITIVE_EXP = 3;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The number of structural features of the '<em>Primitive Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.StringExpImpl <em>String Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.StringExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getStringExp()
	 * @generated
	 */
	int STRING_EXP = 4;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__LOCATION = PRIMITIVE_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__COMMENTS_BEFORE = PRIMITIVE_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__COMMENTS_AFTER = PRIMITIVE_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__FILE_LOCATION = PRIMITIVE_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__FILE_OBJECT = PRIMITIVE_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__INFERRED_TYPE = PRIMITIVE_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__TYPE = PRIMITIVE_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__IF_EXP3 = PRIMITIVE_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__APPLIED_PROPERTY = PRIMITIVE_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__COLLECTION = PRIMITIVE_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__LET_EXP = PRIMITIVE_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__LOOP_EXP = PRIMITIVE_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__PARENT_OPERATION = PRIMITIVE_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__INITIALIZED_VARIABLE = PRIMITIVE_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__IF_EXP2 = PRIMITIVE_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__OWNING_OPERATION = PRIMITIVE_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__IF_EXP1 = PRIMITIVE_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__OWNING_ATTRIBUTE = PRIMITIVE_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__IMPLICITLY_CASTED = PRIMITIVE_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>String Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP__STRING_SYMBOL = PRIMITIVE_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP_FEATURE_COUNT = PRIMITIVE_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXP_OPERATION_COUNT = PRIMITIVE_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.BooleanExpImpl <em>Boolean Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.BooleanExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBooleanExp()
	 * @generated
	 */
	int BOOLEAN_EXP = 5;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__LOCATION = PRIMITIVE_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__COMMENTS_BEFORE = PRIMITIVE_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__COMMENTS_AFTER = PRIMITIVE_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__FILE_LOCATION = PRIMITIVE_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__FILE_OBJECT = PRIMITIVE_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__INFERRED_TYPE = PRIMITIVE_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__TYPE = PRIMITIVE_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__IF_EXP3 = PRIMITIVE_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__APPLIED_PROPERTY = PRIMITIVE_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__COLLECTION = PRIMITIVE_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__LET_EXP = PRIMITIVE_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__LOOP_EXP = PRIMITIVE_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__PARENT_OPERATION = PRIMITIVE_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__INITIALIZED_VARIABLE = PRIMITIVE_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__IF_EXP2 = PRIMITIVE_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__OWNING_OPERATION = PRIMITIVE_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__IF_EXP1 = PRIMITIVE_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__OWNING_ATTRIBUTE = PRIMITIVE_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__IMPLICITLY_CASTED = PRIMITIVE_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Boolean Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP__BOOLEAN_SYMBOL = PRIMITIVE_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP_FEATURE_COUNT = PRIMITIVE_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXP_OPERATION_COUNT = PRIMITIVE_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.NumericExpImpl <em>Numeric Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.NumericExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getNumericExp()
	 * @generated
	 */
	int NUMERIC_EXP = 6;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__LOCATION = PRIMITIVE_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__COMMENTS_BEFORE = PRIMITIVE_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__COMMENTS_AFTER = PRIMITIVE_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__FILE_LOCATION = PRIMITIVE_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__FILE_OBJECT = PRIMITIVE_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__INFERRED_TYPE = PRIMITIVE_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__TYPE = PRIMITIVE_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__IF_EXP3 = PRIMITIVE_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__APPLIED_PROPERTY = PRIMITIVE_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__COLLECTION = PRIMITIVE_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__LET_EXP = PRIMITIVE_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__LOOP_EXP = PRIMITIVE_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__PARENT_OPERATION = PRIMITIVE_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__INITIALIZED_VARIABLE = PRIMITIVE_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__IF_EXP2 = PRIMITIVE_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__OWNING_OPERATION = PRIMITIVE_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__IF_EXP1 = PRIMITIVE_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__OWNING_ATTRIBUTE = PRIMITIVE_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP__IMPLICITLY_CASTED = PRIMITIVE_EXP__IMPLICITLY_CASTED;

	/**
	 * The number of structural features of the '<em>Numeric Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP_FEATURE_COUNT = PRIMITIVE_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Numeric Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_EXP_OPERATION_COUNT = PRIMITIVE_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.RealExpImpl <em>Real Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.RealExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getRealExp()
	 * @generated
	 */
	int REAL_EXP = 7;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__LOCATION = NUMERIC_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__COMMENTS_BEFORE = NUMERIC_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__COMMENTS_AFTER = NUMERIC_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__FILE_LOCATION = NUMERIC_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__FILE_OBJECT = NUMERIC_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__INFERRED_TYPE = NUMERIC_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__TYPE = NUMERIC_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__IF_EXP3 = NUMERIC_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__APPLIED_PROPERTY = NUMERIC_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__COLLECTION = NUMERIC_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__LET_EXP = NUMERIC_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__LOOP_EXP = NUMERIC_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__PARENT_OPERATION = NUMERIC_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__INITIALIZED_VARIABLE = NUMERIC_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__IF_EXP2 = NUMERIC_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__OWNING_OPERATION = NUMERIC_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__IF_EXP1 = NUMERIC_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__OWNING_ATTRIBUTE = NUMERIC_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__IMPLICITLY_CASTED = NUMERIC_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Real Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP__REAL_SYMBOL = NUMERIC_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Real Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP_FEATURE_COUNT = NUMERIC_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Real Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_EXP_OPERATION_COUNT = NUMERIC_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.IntegerExpImpl <em>Integer Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.IntegerExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIntegerExp()
	 * @generated
	 */
	int INTEGER_EXP = 8;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__LOCATION = NUMERIC_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__COMMENTS_BEFORE = NUMERIC_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__COMMENTS_AFTER = NUMERIC_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__FILE_LOCATION = NUMERIC_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__FILE_OBJECT = NUMERIC_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__INFERRED_TYPE = NUMERIC_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__TYPE = NUMERIC_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__IF_EXP3 = NUMERIC_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__APPLIED_PROPERTY = NUMERIC_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__COLLECTION = NUMERIC_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__LET_EXP = NUMERIC_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__LOOP_EXP = NUMERIC_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__PARENT_OPERATION = NUMERIC_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__INITIALIZED_VARIABLE = NUMERIC_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__IF_EXP2 = NUMERIC_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__OWNING_OPERATION = NUMERIC_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__IF_EXP1 = NUMERIC_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__OWNING_ATTRIBUTE = NUMERIC_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__IMPLICITLY_CASTED = NUMERIC_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Integer Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP__INTEGER_SYMBOL = NUMERIC_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP_FEATURE_COUNT = NUMERIC_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_EXP_OPERATION_COUNT = NUMERIC_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.CollectionExpImpl <em>Collection Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.CollectionExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getCollectionExp()
	 * @generated
	 */
	int COLLECTION_EXP = 9;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP__ELEMENTS = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collection Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Collection Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.BagExpImpl <em>Bag Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.BagExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBagExp()
	 * @generated
	 */
	int BAG_EXP = 10;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__LOCATION = COLLECTION_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__COMMENTS_BEFORE = COLLECTION_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__COMMENTS_AFTER = COLLECTION_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__FILE_LOCATION = COLLECTION_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__FILE_OBJECT = COLLECTION_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__INFERRED_TYPE = COLLECTION_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__TYPE = COLLECTION_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__IF_EXP3 = COLLECTION_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__APPLIED_PROPERTY = COLLECTION_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__COLLECTION = COLLECTION_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__LET_EXP = COLLECTION_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__LOOP_EXP = COLLECTION_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__PARENT_OPERATION = COLLECTION_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__INITIALIZED_VARIABLE = COLLECTION_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__IF_EXP2 = COLLECTION_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__OWNING_OPERATION = COLLECTION_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__IF_EXP1 = COLLECTION_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__OWNING_ATTRIBUTE = COLLECTION_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__IMPLICITLY_CASTED = COLLECTION_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP__ELEMENTS = COLLECTION_EXP__ELEMENTS;

	/**
	 * The number of structural features of the '<em>Bag Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP_FEATURE_COUNT = COLLECTION_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Bag Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_EXP_OPERATION_COUNT = COLLECTION_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OrderedSetExpImpl <em>Ordered Set Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OrderedSetExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOrderedSetExp()
	 * @generated
	 */
	int ORDERED_SET_EXP = 11;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__LOCATION = COLLECTION_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__COMMENTS_BEFORE = COLLECTION_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__COMMENTS_AFTER = COLLECTION_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__FILE_LOCATION = COLLECTION_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__FILE_OBJECT = COLLECTION_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__INFERRED_TYPE = COLLECTION_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__TYPE = COLLECTION_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__IF_EXP3 = COLLECTION_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__APPLIED_PROPERTY = COLLECTION_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__COLLECTION = COLLECTION_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__LET_EXP = COLLECTION_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__LOOP_EXP = COLLECTION_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__PARENT_OPERATION = COLLECTION_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__INITIALIZED_VARIABLE = COLLECTION_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__IF_EXP2 = COLLECTION_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__OWNING_OPERATION = COLLECTION_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__IF_EXP1 = COLLECTION_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__OWNING_ATTRIBUTE = COLLECTION_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__IMPLICITLY_CASTED = COLLECTION_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP__ELEMENTS = COLLECTION_EXP__ELEMENTS;

	/**
	 * The number of structural features of the '<em>Ordered Set Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP_FEATURE_COUNT = COLLECTION_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Ordered Set Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_EXP_OPERATION_COUNT = COLLECTION_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.SequenceExpImpl <em>Sequence Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.SequenceExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSequenceExp()
	 * @generated
	 */
	int SEQUENCE_EXP = 12;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__LOCATION = COLLECTION_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__COMMENTS_BEFORE = COLLECTION_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__COMMENTS_AFTER = COLLECTION_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__FILE_LOCATION = COLLECTION_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__FILE_OBJECT = COLLECTION_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__INFERRED_TYPE = COLLECTION_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__TYPE = COLLECTION_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__IF_EXP3 = COLLECTION_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__APPLIED_PROPERTY = COLLECTION_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__COLLECTION = COLLECTION_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__LET_EXP = COLLECTION_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__LOOP_EXP = COLLECTION_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__PARENT_OPERATION = COLLECTION_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__INITIALIZED_VARIABLE = COLLECTION_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__IF_EXP2 = COLLECTION_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__OWNING_OPERATION = COLLECTION_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__IF_EXP1 = COLLECTION_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__OWNING_ATTRIBUTE = COLLECTION_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__IMPLICITLY_CASTED = COLLECTION_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP__ELEMENTS = COLLECTION_EXP__ELEMENTS;

	/**
	 * The number of structural features of the '<em>Sequence Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP_FEATURE_COUNT = COLLECTION_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sequence Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_EXP_OPERATION_COUNT = COLLECTION_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.SetExpImpl <em>Set Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.SetExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSetExp()
	 * @generated
	 */
	int SET_EXP = 13;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__LOCATION = COLLECTION_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__COMMENTS_BEFORE = COLLECTION_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__COMMENTS_AFTER = COLLECTION_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__FILE_LOCATION = COLLECTION_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__FILE_OBJECT = COLLECTION_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__INFERRED_TYPE = COLLECTION_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__TYPE = COLLECTION_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__IF_EXP3 = COLLECTION_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__APPLIED_PROPERTY = COLLECTION_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__COLLECTION = COLLECTION_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__LET_EXP = COLLECTION_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__LOOP_EXP = COLLECTION_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__PARENT_OPERATION = COLLECTION_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__INITIALIZED_VARIABLE = COLLECTION_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__IF_EXP2 = COLLECTION_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__OWNING_OPERATION = COLLECTION_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__IF_EXP1 = COLLECTION_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__OWNING_ATTRIBUTE = COLLECTION_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__IMPLICITLY_CASTED = COLLECTION_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP__ELEMENTS = COLLECTION_EXP__ELEMENTS;

	/**
	 * The number of structural features of the '<em>Set Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP_FEATURE_COUNT = COLLECTION_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Set Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_EXP_OPERATION_COUNT = COLLECTION_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.TupleExpImpl <em>Tuple Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.TupleExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTupleExp()
	 * @generated
	 */
	int TUPLE_EXP = 14;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Tuple Part</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP__TUPLE_PART = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tuple Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tuple Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.VariableDeclarationImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getVariableDeclaration()
	 * @generated
	 */
	int VARIABLE_DECLARATION = 30;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__INFERRED_TYPE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__ID = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__VAR_NAME = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__TYPE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__INIT_EXPRESSION = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__LET_EXP = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__BASE_EXP = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__VARIABLE_EXP = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__STATIC_TYPE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of operations of the '<em>Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.TuplePartImpl <em>Tuple Part</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.TuplePartImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTuplePart()
	 * @generated
	 */
	int TUPLE_PART = 15;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__LOCATION = VARIABLE_DECLARATION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__COMMENTS_BEFORE = VARIABLE_DECLARATION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__COMMENTS_AFTER = VARIABLE_DECLARATION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__FILE_LOCATION = VARIABLE_DECLARATION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__FILE_OBJECT = VARIABLE_DECLARATION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__INFERRED_TYPE = VARIABLE_DECLARATION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__ID = VARIABLE_DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__VAR_NAME = VARIABLE_DECLARATION__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__TYPE = VARIABLE_DECLARATION__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__INIT_EXPRESSION = VARIABLE_DECLARATION__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__LET_EXP = VARIABLE_DECLARATION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__BASE_EXP = VARIABLE_DECLARATION__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__VARIABLE_EXP = VARIABLE_DECLARATION__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__STATIC_TYPE = VARIABLE_DECLARATION__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Tuple</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART__TUPLE = VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tuple Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART_FEATURE_COUNT = VARIABLE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tuple Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_PART_OPERATION_COUNT = VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.MapExpImpl <em>Map Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.MapExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getMapExp()
	 * @generated
	 */
	int MAP_EXP = 16;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP__ELEMENTS = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Map Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.MapElementImpl <em>Map Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.MapElementImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getMapElement()
	 * @generated
	 */
	int MAP_ELEMENT = 17;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Map</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__MAP = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__KEY = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT__VALUE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Map Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Map Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ELEMENT_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.EnumLiteralExpImpl <em>Enum Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.EnumLiteralExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getEnumLiteralExp()
	 * @generated
	 */
	int ENUM_LITERAL_EXP = 18;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP__NAME = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Enum Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Enum Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclUndefinedExpImpl <em>Ocl Undefined Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclUndefinedExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclUndefinedExp()
	 * @generated
	 */
	int OCL_UNDEFINED_EXP = 19;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The number of structural features of the '<em>Ocl Undefined Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Ocl Undefined Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_UNDEFINED_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl <em>Property Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.PropertyCallExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getPropertyCallExp()
	 * @generated
	 */
	int PROPERTY_CALL_EXP = 20;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__SOURCE = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__USED_FEATURE = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__RECEPTOR_TYPE = OCL_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__IS_STATIC_CALL = OCL_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__STATIC_RESOLVER = OCL_EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS = OCL_EXPRESSION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Property Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Property Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CALL_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.NavigationOrAttributeCallExpImpl <em>Navigation Or Attribute Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.NavigationOrAttributeCallExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getNavigationOrAttributeCallExp()
	 * @generated
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP = 21;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__LOCATION = PROPERTY_CALL_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__COMMENTS_BEFORE = PROPERTY_CALL_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__COMMENTS_AFTER = PROPERTY_CALL_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__FILE_LOCATION = PROPERTY_CALL_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__FILE_OBJECT = PROPERTY_CALL_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__INFERRED_TYPE = PROPERTY_CALL_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__TYPE = PROPERTY_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__IF_EXP3 = PROPERTY_CALL_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__APPLIED_PROPERTY = PROPERTY_CALL_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__COLLECTION = PROPERTY_CALL_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__LET_EXP = PROPERTY_CALL_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__LOOP_EXP = PROPERTY_CALL_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__PARENT_OPERATION = PROPERTY_CALL_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__INITIALIZED_VARIABLE = PROPERTY_CALL_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__IF_EXP2 = PROPERTY_CALL_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__OWNING_OPERATION = PROPERTY_CALL_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__IF_EXP1 = PROPERTY_CALL_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__OWNING_ATTRIBUTE = PROPERTY_CALL_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__IMPLICITLY_CASTED = PROPERTY_CALL_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__SOURCE = PROPERTY_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__USED_FEATURE = PROPERTY_CALL_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__RECEPTOR_TYPE = PROPERTY_CALL_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__IS_STATIC_CALL = PROPERTY_CALL_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__STATIC_RESOLVER = PROPERTY_CALL_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__DYNAMIC_RESOLVERS = PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP__NAME = PROPERTY_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Navigation Or Attribute Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP_FEATURE_COUNT = PROPERTY_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Navigation Or Attribute Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_OR_ATTRIBUTE_CALL_EXP_OPERATION_COUNT = PROPERTY_CALL_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OperationCallExpImpl <em>Operation Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OperationCallExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOperationCallExp()
	 * @generated
	 */
	int OPERATION_CALL_EXP = 22;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__LOCATION = PROPERTY_CALL_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__COMMENTS_BEFORE = PROPERTY_CALL_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__COMMENTS_AFTER = PROPERTY_CALL_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__FILE_LOCATION = PROPERTY_CALL_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__FILE_OBJECT = PROPERTY_CALL_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__INFERRED_TYPE = PROPERTY_CALL_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__TYPE = PROPERTY_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__IF_EXP3 = PROPERTY_CALL_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__APPLIED_PROPERTY = PROPERTY_CALL_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__COLLECTION = PROPERTY_CALL_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__LET_EXP = PROPERTY_CALL_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__LOOP_EXP = PROPERTY_CALL_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__PARENT_OPERATION = PROPERTY_CALL_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__INITIALIZED_VARIABLE = PROPERTY_CALL_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__IF_EXP2 = PROPERTY_CALL_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__OWNING_OPERATION = PROPERTY_CALL_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__IF_EXP1 = PROPERTY_CALL_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__OWNING_ATTRIBUTE = PROPERTY_CALL_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__IMPLICITLY_CASTED = PROPERTY_CALL_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__SOURCE = PROPERTY_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__USED_FEATURE = PROPERTY_CALL_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__RECEPTOR_TYPE = PROPERTY_CALL_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__IS_STATIC_CALL = PROPERTY_CALL_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__STATIC_RESOLVER = PROPERTY_CALL_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__DYNAMIC_RESOLVERS = PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__ARGUMENTS = PROPERTY_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__OPERATION_NAME = PROPERTY_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resolve Temp Resolved By</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY = PROPERTY_CALL_EXP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP_FEATURE_COUNT = PROPERTY_CALL_EXP_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_EXP_OPERATION_COUNT = PROPERTY_CALL_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OperatorCallExpImpl <em>Operator Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OperatorCallExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOperatorCallExp()
	 * @generated
	 */
	int OPERATOR_CALL_EXP = 23;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__LOCATION = OPERATION_CALL_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__COMMENTS_BEFORE = OPERATION_CALL_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__COMMENTS_AFTER = OPERATION_CALL_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__FILE_LOCATION = OPERATION_CALL_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__FILE_OBJECT = OPERATION_CALL_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__INFERRED_TYPE = OPERATION_CALL_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__TYPE = OPERATION_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__IF_EXP3 = OPERATION_CALL_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__APPLIED_PROPERTY = OPERATION_CALL_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__COLLECTION = OPERATION_CALL_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__LET_EXP = OPERATION_CALL_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__LOOP_EXP = OPERATION_CALL_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__PARENT_OPERATION = OPERATION_CALL_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__INITIALIZED_VARIABLE = OPERATION_CALL_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__IF_EXP2 = OPERATION_CALL_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__OWNING_OPERATION = OPERATION_CALL_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__IF_EXP1 = OPERATION_CALL_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__OWNING_ATTRIBUTE = OPERATION_CALL_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__IMPLICITLY_CASTED = OPERATION_CALL_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__SOURCE = OPERATION_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__USED_FEATURE = OPERATION_CALL_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__RECEPTOR_TYPE = OPERATION_CALL_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__IS_STATIC_CALL = OPERATION_CALL_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__STATIC_RESOLVER = OPERATION_CALL_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__DYNAMIC_RESOLVERS = OPERATION_CALL_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__ARGUMENTS = OPERATION_CALL_EXP__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__OPERATION_NAME = OPERATION_CALL_EXP__OPERATION_NAME;

	/**
	 * The feature id for the '<em><b>Resolve Temp Resolved By</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY = OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY;

	/**
	 * The number of structural features of the '<em>Operator Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP_FEATURE_COUNT = OPERATION_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Operator Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CALL_EXP_OPERATION_COUNT = OPERATION_CALL_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.CollectionOperationCallExpImpl <em>Collection Operation Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.CollectionOperationCallExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getCollectionOperationCallExp()
	 * @generated
	 */
	int COLLECTION_OPERATION_CALL_EXP = 24;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__LOCATION = OPERATION_CALL_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__COMMENTS_BEFORE = OPERATION_CALL_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__COMMENTS_AFTER = OPERATION_CALL_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__FILE_LOCATION = OPERATION_CALL_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__FILE_OBJECT = OPERATION_CALL_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__INFERRED_TYPE = OPERATION_CALL_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__TYPE = OPERATION_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__IF_EXP3 = OPERATION_CALL_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__APPLIED_PROPERTY = OPERATION_CALL_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__COLLECTION = OPERATION_CALL_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__LET_EXP = OPERATION_CALL_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__LOOP_EXP = OPERATION_CALL_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__PARENT_OPERATION = OPERATION_CALL_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__INITIALIZED_VARIABLE = OPERATION_CALL_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__IF_EXP2 = OPERATION_CALL_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__OWNING_OPERATION = OPERATION_CALL_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__IF_EXP1 = OPERATION_CALL_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__OWNING_ATTRIBUTE = OPERATION_CALL_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__IMPLICITLY_CASTED = OPERATION_CALL_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__SOURCE = OPERATION_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__USED_FEATURE = OPERATION_CALL_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__RECEPTOR_TYPE = OPERATION_CALL_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__IS_STATIC_CALL = OPERATION_CALL_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__STATIC_RESOLVER = OPERATION_CALL_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__DYNAMIC_RESOLVERS = OPERATION_CALL_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__ARGUMENTS = OPERATION_CALL_EXP__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Operation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__OPERATION_NAME = OPERATION_CALL_EXP__OPERATION_NAME;

	/**
	 * The feature id for the '<em><b>Resolve Temp Resolved By</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY = OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY;

	/**
	 * The number of structural features of the '<em>Collection Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP_FEATURE_COUNT = OPERATION_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Collection Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OPERATION_CALL_EXP_OPERATION_COUNT = OPERATION_CALL_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.LoopExpImpl <em>Loop Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.LoopExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getLoopExp()
	 * @generated
	 */
	int LOOP_EXP = 25;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__LOCATION = PROPERTY_CALL_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__COMMENTS_BEFORE = PROPERTY_CALL_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__COMMENTS_AFTER = PROPERTY_CALL_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__FILE_LOCATION = PROPERTY_CALL_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__FILE_OBJECT = PROPERTY_CALL_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__INFERRED_TYPE = PROPERTY_CALL_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__TYPE = PROPERTY_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__IF_EXP3 = PROPERTY_CALL_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__APPLIED_PROPERTY = PROPERTY_CALL_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__COLLECTION = PROPERTY_CALL_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__LET_EXP = PROPERTY_CALL_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__LOOP_EXP = PROPERTY_CALL_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__PARENT_OPERATION = PROPERTY_CALL_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__INITIALIZED_VARIABLE = PROPERTY_CALL_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__IF_EXP2 = PROPERTY_CALL_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__OWNING_OPERATION = PROPERTY_CALL_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__IF_EXP1 = PROPERTY_CALL_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__OWNING_ATTRIBUTE = PROPERTY_CALL_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__IMPLICITLY_CASTED = PROPERTY_CALL_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__SOURCE = PROPERTY_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__USED_FEATURE = PROPERTY_CALL_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__RECEPTOR_TYPE = PROPERTY_CALL_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__IS_STATIC_CALL = PROPERTY_CALL_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__STATIC_RESOLVER = PROPERTY_CALL_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__DYNAMIC_RESOLVERS = PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__BODY = PROPERTY_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Iterators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP__ITERATORS = PROPERTY_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Loop Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP_FEATURE_COUNT = PROPERTY_CALL_EXP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Loop Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOP_EXP_OPERATION_COUNT = PROPERTY_CALL_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.IterateExpImpl <em>Iterate Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.IterateExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIterateExp()
	 * @generated
	 */
	int ITERATE_EXP = 26;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__LOCATION = LOOP_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__COMMENTS_BEFORE = LOOP_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__COMMENTS_AFTER = LOOP_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__FILE_LOCATION = LOOP_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__FILE_OBJECT = LOOP_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__INFERRED_TYPE = LOOP_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__TYPE = LOOP_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__IF_EXP3 = LOOP_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__APPLIED_PROPERTY = LOOP_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__COLLECTION = LOOP_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__LET_EXP = LOOP_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__LOOP_EXP = LOOP_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__PARENT_OPERATION = LOOP_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__INITIALIZED_VARIABLE = LOOP_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__IF_EXP2 = LOOP_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__OWNING_OPERATION = LOOP_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__IF_EXP1 = LOOP_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__OWNING_ATTRIBUTE = LOOP_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__IMPLICITLY_CASTED = LOOP_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__SOURCE = LOOP_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__USED_FEATURE = LOOP_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__RECEPTOR_TYPE = LOOP_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__IS_STATIC_CALL = LOOP_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__STATIC_RESOLVER = LOOP_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__DYNAMIC_RESOLVERS = LOOP_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__BODY = LOOP_EXP__BODY;

	/**
	 * The feature id for the '<em><b>Iterators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__ITERATORS = LOOP_EXP__ITERATORS;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP__RESULT = LOOP_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Iterate Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP_FEATURE_COUNT = LOOP_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Iterate Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATE_EXP_OPERATION_COUNT = LOOP_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.IteratorExpImpl <em>Iterator Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.IteratorExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIteratorExp()
	 * @generated
	 */
	int ITERATOR_EXP = 27;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__LOCATION = LOOP_EXP__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__COMMENTS_BEFORE = LOOP_EXP__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__COMMENTS_AFTER = LOOP_EXP__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__FILE_LOCATION = LOOP_EXP__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__FILE_OBJECT = LOOP_EXP__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__INFERRED_TYPE = LOOP_EXP__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__TYPE = LOOP_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__IF_EXP3 = LOOP_EXP__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__APPLIED_PROPERTY = LOOP_EXP__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__COLLECTION = LOOP_EXP__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__LET_EXP = LOOP_EXP__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__LOOP_EXP = LOOP_EXP__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__PARENT_OPERATION = LOOP_EXP__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__INITIALIZED_VARIABLE = LOOP_EXP__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__IF_EXP2 = LOOP_EXP__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__OWNING_OPERATION = LOOP_EXP__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__IF_EXP1 = LOOP_EXP__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__OWNING_ATTRIBUTE = LOOP_EXP__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__IMPLICITLY_CASTED = LOOP_EXP__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__SOURCE = LOOP_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Used Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__USED_FEATURE = LOOP_EXP__USED_FEATURE;

	/**
	 * The feature id for the '<em><b>Receptor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__RECEPTOR_TYPE = LOOP_EXP__RECEPTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Is Static Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__IS_STATIC_CALL = LOOP_EXP__IS_STATIC_CALL;

	/**
	 * The feature id for the '<em><b>Static Resolver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__STATIC_RESOLVER = LOOP_EXP__STATIC_RESOLVER;

	/**
	 * The feature id for the '<em><b>Dynamic Resolvers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__DYNAMIC_RESOLVERS = LOOP_EXP__DYNAMIC_RESOLVERS;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__BODY = LOOP_EXP__BODY;

	/**
	 * The feature id for the '<em><b>Iterators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__ITERATORS = LOOP_EXP__ITERATORS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP__NAME = LOOP_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Iterator Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP_FEATURE_COUNT = LOOP_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Iterator Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_EXP_OPERATION_COUNT = LOOP_EXP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.LetExpImpl <em>Let Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.LetExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getLetExp()
	 * @generated
	 */
	int LET_EXP = 28;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__VARIABLE = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In </b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP__IN_ = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Let Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Let Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LET_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.IfExpImpl <em>If Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.IfExpImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIfExp()
	 * @generated
	 */
	int IF_EXP = 29;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Then Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__THEN_EXPRESSION = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__CONDITION = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Else Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP__ELSE_EXPRESSION = OCL_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>If Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXP_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.IteratorImpl <em>Iterator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.IteratorImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIterator()
	 * @generated
	 */
	int ITERATOR = 31;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__LOCATION = VARIABLE_DECLARATION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__COMMENTS_BEFORE = VARIABLE_DECLARATION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__COMMENTS_AFTER = VARIABLE_DECLARATION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__FILE_LOCATION = VARIABLE_DECLARATION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__FILE_OBJECT = VARIABLE_DECLARATION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__INFERRED_TYPE = VARIABLE_DECLARATION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__ID = VARIABLE_DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__VAR_NAME = VARIABLE_DECLARATION__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__TYPE = VARIABLE_DECLARATION__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__INIT_EXPRESSION = VARIABLE_DECLARATION__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__LET_EXP = VARIABLE_DECLARATION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__BASE_EXP = VARIABLE_DECLARATION__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__VARIABLE_EXP = VARIABLE_DECLARATION__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__STATIC_TYPE = VARIABLE_DECLARATION__STATIC_TYPE;

	/**
	 * The feature id for the '<em><b>Loop Expr</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__LOOP_EXPR = VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_FEATURE_COUNT = VARIABLE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OPERATION_COUNT = VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.ParameterImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 32;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__LOCATION = VARIABLE_DECLARATION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__COMMENTS_BEFORE = VARIABLE_DECLARATION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__COMMENTS_AFTER = VARIABLE_DECLARATION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__FILE_LOCATION = VARIABLE_DECLARATION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__FILE_OBJECT = VARIABLE_DECLARATION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__INFERRED_TYPE = VARIABLE_DECLARATION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__ID = VARIABLE_DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__VAR_NAME = VARIABLE_DECLARATION__VAR_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = VARIABLE_DECLARATION__TYPE;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__INIT_EXPRESSION = VARIABLE_DECLARATION__INIT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__LET_EXP = VARIABLE_DECLARATION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Base Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__BASE_EXP = VARIABLE_DECLARATION__BASE_EXP;

	/**
	 * The feature id for the '<em><b>Variable Exp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__VARIABLE_EXP = VARIABLE_DECLARATION__VARIABLE_EXP;

	/**
	 * The feature id for the '<em><b>Static Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__STATIC_TYPE = VARIABLE_DECLARATION__STATIC_TYPE;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclTypeImpl <em>Ocl Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclType()
	 * @generated
	 */
	int OCL_TYPE = 34;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__NAME = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__DEFINITIONS = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__OCL_EXPRESSION = OCL_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__OPERATION = OCL_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__MAP_TYPE2 = OCL_EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__ATTRIBUTE = OCL_EXPRESSION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__MAP_TYPE = OCL_EXPRESSION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__COLLECTION_TYPES = OCL_EXPRESSION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__TUPLE_TYPE_ATTRIBUTE = OCL_EXPRESSION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE__VARIABLE_DECLARATION = OCL_EXPRESSION_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Ocl Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Ocl Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_TYPE_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.CollectionTypeImpl <em>Collection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.CollectionTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getCollectionType()
	 * @generated
	 */
	int COLLECTION_TYPE = 33;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__LOCATION = OCL_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__COMMENTS_BEFORE = OCL_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__COMMENTS_AFTER = OCL_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__FILE_LOCATION = OCL_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__FILE_OBJECT = OCL_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__INFERRED_TYPE = OCL_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__TYPE = OCL_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__IF_EXP3 = OCL_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__APPLIED_PROPERTY = OCL_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__COLLECTION = OCL_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__LET_EXP = OCL_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__LOOP_EXP = OCL_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__PARENT_OPERATION = OCL_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__INITIALIZED_VARIABLE = OCL_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__IF_EXP2 = OCL_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__OWNING_OPERATION = OCL_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__IF_EXP1 = OCL_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__OWNING_ATTRIBUTE = OCL_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__IMPLICITLY_CASTED = OCL_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__NAME = OCL_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__DEFINITIONS = OCL_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__OCL_EXPRESSION = OCL_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__OPERATION = OCL_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__MAP_TYPE2 = OCL_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__ATTRIBUTE = OCL_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__MAP_TYPE = OCL_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__COLLECTION_TYPES = OCL_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__TUPLE_TYPE_ATTRIBUTE = OCL_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__VARIABLE_DECLARATION = OCL_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE__ELEMENT_TYPE = OCL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_FEATURE_COUNT = OCL_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_OPERATION_COUNT = OCL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.PrimitiveImpl <em>Primitive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.PrimitiveImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getPrimitive()
	 * @generated
	 */
	int PRIMITIVE = 35;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__LOCATION = OCL_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__COMMENTS_BEFORE = OCL_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__COMMENTS_AFTER = OCL_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__FILE_LOCATION = OCL_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__FILE_OBJECT = OCL_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__INFERRED_TYPE = OCL_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__TYPE = OCL_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__IF_EXP3 = OCL_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__APPLIED_PROPERTY = OCL_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__COLLECTION = OCL_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__LET_EXP = OCL_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__LOOP_EXP = OCL_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__PARENT_OPERATION = OCL_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__INITIALIZED_VARIABLE = OCL_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__IF_EXP2 = OCL_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__OWNING_OPERATION = OCL_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__IF_EXP1 = OCL_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__OWNING_ATTRIBUTE = OCL_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__IMPLICITLY_CASTED = OCL_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__NAME = OCL_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__DEFINITIONS = OCL_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__OCL_EXPRESSION = OCL_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__OPERATION = OCL_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__MAP_TYPE2 = OCL_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__ATTRIBUTE = OCL_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__MAP_TYPE = OCL_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__COLLECTION_TYPES = OCL_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__TUPLE_TYPE_ATTRIBUTE = OCL_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE__VARIABLE_DECLARATION = OCL_TYPE__VARIABLE_DECLARATION;

	/**
	 * The number of structural features of the '<em>Primitive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_FEATURE_COUNT = OCL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_OPERATION_COUNT = OCL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.StringTypeImpl <em>String Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.StringTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getStringType()
	 * @generated
	 */
	int STRING_TYPE = 36;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__LOCATION = PRIMITIVE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__COMMENTS_BEFORE = PRIMITIVE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__COMMENTS_AFTER = PRIMITIVE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__FILE_LOCATION = PRIMITIVE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__FILE_OBJECT = PRIMITIVE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__INFERRED_TYPE = PRIMITIVE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__TYPE = PRIMITIVE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__IF_EXP3 = PRIMITIVE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__APPLIED_PROPERTY = PRIMITIVE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__COLLECTION = PRIMITIVE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__LET_EXP = PRIMITIVE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__LOOP_EXP = PRIMITIVE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__PARENT_OPERATION = PRIMITIVE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__INITIALIZED_VARIABLE = PRIMITIVE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__IF_EXP2 = PRIMITIVE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__OWNING_OPERATION = PRIMITIVE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__IF_EXP1 = PRIMITIVE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__OWNING_ATTRIBUTE = PRIMITIVE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__IMPLICITLY_CASTED = PRIMITIVE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__NAME = PRIMITIVE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__DEFINITIONS = PRIMITIVE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__OCL_EXPRESSION = PRIMITIVE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__OPERATION = PRIMITIVE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__MAP_TYPE2 = PRIMITIVE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__ATTRIBUTE = PRIMITIVE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__MAP_TYPE = PRIMITIVE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__COLLECTION_TYPES = PRIMITIVE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__TUPLE_TYPE_ATTRIBUTE = PRIMITIVE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__VARIABLE_DECLARATION = PRIMITIVE__VARIABLE_DECLARATION;

	/**
	 * The number of structural features of the '<em>String Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE_FEATURE_COUNT = PRIMITIVE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>String Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE_OPERATION_COUNT = PRIMITIVE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.BooleanTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBooleanType()
	 * @generated
	 */
	int BOOLEAN_TYPE = 37;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__LOCATION = PRIMITIVE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__COMMENTS_BEFORE = PRIMITIVE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__COMMENTS_AFTER = PRIMITIVE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__FILE_LOCATION = PRIMITIVE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__FILE_OBJECT = PRIMITIVE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__INFERRED_TYPE = PRIMITIVE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__TYPE = PRIMITIVE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__IF_EXP3 = PRIMITIVE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__APPLIED_PROPERTY = PRIMITIVE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__COLLECTION = PRIMITIVE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__LET_EXP = PRIMITIVE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__LOOP_EXP = PRIMITIVE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__PARENT_OPERATION = PRIMITIVE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__INITIALIZED_VARIABLE = PRIMITIVE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__IF_EXP2 = PRIMITIVE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__OWNING_OPERATION = PRIMITIVE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__IF_EXP1 = PRIMITIVE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__OWNING_ATTRIBUTE = PRIMITIVE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__IMPLICITLY_CASTED = PRIMITIVE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__NAME = PRIMITIVE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__DEFINITIONS = PRIMITIVE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__OCL_EXPRESSION = PRIMITIVE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__OPERATION = PRIMITIVE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__MAP_TYPE2 = PRIMITIVE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__ATTRIBUTE = PRIMITIVE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__MAP_TYPE = PRIMITIVE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__COLLECTION_TYPES = PRIMITIVE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__TUPLE_TYPE_ATTRIBUTE = PRIMITIVE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE__VARIABLE_DECLARATION = PRIMITIVE__VARIABLE_DECLARATION;

	/**
	 * The number of structural features of the '<em>Boolean Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE_FEATURE_COUNT = PRIMITIVE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Boolean Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_TYPE_OPERATION_COUNT = PRIMITIVE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.NumericTypeImpl <em>Numeric Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.NumericTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getNumericType()
	 * @generated
	 */
	int NUMERIC_TYPE = 38;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__LOCATION = PRIMITIVE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__COMMENTS_BEFORE = PRIMITIVE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__COMMENTS_AFTER = PRIMITIVE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__FILE_LOCATION = PRIMITIVE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__FILE_OBJECT = PRIMITIVE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__INFERRED_TYPE = PRIMITIVE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__TYPE = PRIMITIVE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__IF_EXP3 = PRIMITIVE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__APPLIED_PROPERTY = PRIMITIVE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__COLLECTION = PRIMITIVE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__LET_EXP = PRIMITIVE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__LOOP_EXP = PRIMITIVE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__PARENT_OPERATION = PRIMITIVE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__INITIALIZED_VARIABLE = PRIMITIVE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__IF_EXP2 = PRIMITIVE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__OWNING_OPERATION = PRIMITIVE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__IF_EXP1 = PRIMITIVE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__OWNING_ATTRIBUTE = PRIMITIVE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__IMPLICITLY_CASTED = PRIMITIVE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__NAME = PRIMITIVE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__DEFINITIONS = PRIMITIVE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__OCL_EXPRESSION = PRIMITIVE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__OPERATION = PRIMITIVE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__MAP_TYPE2 = PRIMITIVE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__ATTRIBUTE = PRIMITIVE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__MAP_TYPE = PRIMITIVE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__COLLECTION_TYPES = PRIMITIVE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__TUPLE_TYPE_ATTRIBUTE = PRIMITIVE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE__VARIABLE_DECLARATION = PRIMITIVE__VARIABLE_DECLARATION;

	/**
	 * The number of structural features of the '<em>Numeric Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE_FEATURE_COUNT = PRIMITIVE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Numeric Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_TYPE_OPERATION_COUNT = PRIMITIVE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.IntegerTypeImpl <em>Integer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.IntegerTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIntegerType()
	 * @generated
	 */
	int INTEGER_TYPE = 39;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__LOCATION = NUMERIC_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__COMMENTS_BEFORE = NUMERIC_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__COMMENTS_AFTER = NUMERIC_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__FILE_LOCATION = NUMERIC_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__FILE_OBJECT = NUMERIC_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__INFERRED_TYPE = NUMERIC_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__TYPE = NUMERIC_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__IF_EXP3 = NUMERIC_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__APPLIED_PROPERTY = NUMERIC_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__COLLECTION = NUMERIC_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__LET_EXP = NUMERIC_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__LOOP_EXP = NUMERIC_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__PARENT_OPERATION = NUMERIC_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__INITIALIZED_VARIABLE = NUMERIC_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__IF_EXP2 = NUMERIC_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__OWNING_OPERATION = NUMERIC_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__IF_EXP1 = NUMERIC_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__OWNING_ATTRIBUTE = NUMERIC_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__IMPLICITLY_CASTED = NUMERIC_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__NAME = NUMERIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__DEFINITIONS = NUMERIC_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__OCL_EXPRESSION = NUMERIC_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__OPERATION = NUMERIC_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__MAP_TYPE2 = NUMERIC_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__ATTRIBUTE = NUMERIC_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__MAP_TYPE = NUMERIC_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__COLLECTION_TYPES = NUMERIC_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__TUPLE_TYPE_ATTRIBUTE = NUMERIC_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE__VARIABLE_DECLARATION = NUMERIC_TYPE__VARIABLE_DECLARATION;

	/**
	 * The number of structural features of the '<em>Integer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE_FEATURE_COUNT = NUMERIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Integer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TYPE_OPERATION_COUNT = NUMERIC_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.RealTypeImpl <em>Real Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.RealTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getRealType()
	 * @generated
	 */
	int REAL_TYPE = 40;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__LOCATION = NUMERIC_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__COMMENTS_BEFORE = NUMERIC_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__COMMENTS_AFTER = NUMERIC_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__FILE_LOCATION = NUMERIC_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__FILE_OBJECT = NUMERIC_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__INFERRED_TYPE = NUMERIC_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__TYPE = NUMERIC_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__IF_EXP3 = NUMERIC_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__APPLIED_PROPERTY = NUMERIC_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__COLLECTION = NUMERIC_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__LET_EXP = NUMERIC_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__LOOP_EXP = NUMERIC_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__PARENT_OPERATION = NUMERIC_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__INITIALIZED_VARIABLE = NUMERIC_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__IF_EXP2 = NUMERIC_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__OWNING_OPERATION = NUMERIC_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__IF_EXP1 = NUMERIC_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__OWNING_ATTRIBUTE = NUMERIC_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__IMPLICITLY_CASTED = NUMERIC_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__NAME = NUMERIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__DEFINITIONS = NUMERIC_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__OCL_EXPRESSION = NUMERIC_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__OPERATION = NUMERIC_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__MAP_TYPE2 = NUMERIC_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__ATTRIBUTE = NUMERIC_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__MAP_TYPE = NUMERIC_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__COLLECTION_TYPES = NUMERIC_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__TUPLE_TYPE_ATTRIBUTE = NUMERIC_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE__VARIABLE_DECLARATION = NUMERIC_TYPE__VARIABLE_DECLARATION;

	/**
	 * The number of structural features of the '<em>Real Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE_FEATURE_COUNT = NUMERIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Real Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_TYPE_OPERATION_COUNT = NUMERIC_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.BagTypeImpl <em>Bag Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.BagTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBagType()
	 * @generated
	 */
	int BAG_TYPE = 41;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__LOCATION = COLLECTION_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__COMMENTS_BEFORE = COLLECTION_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__COMMENTS_AFTER = COLLECTION_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__FILE_LOCATION = COLLECTION_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__FILE_OBJECT = COLLECTION_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__INFERRED_TYPE = COLLECTION_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__TYPE = COLLECTION_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__IF_EXP3 = COLLECTION_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__APPLIED_PROPERTY = COLLECTION_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__COLLECTION = COLLECTION_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__LET_EXP = COLLECTION_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__LOOP_EXP = COLLECTION_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__PARENT_OPERATION = COLLECTION_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__INITIALIZED_VARIABLE = COLLECTION_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__IF_EXP2 = COLLECTION_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__OWNING_OPERATION = COLLECTION_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__IF_EXP1 = COLLECTION_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__OWNING_ATTRIBUTE = COLLECTION_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__IMPLICITLY_CASTED = COLLECTION_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__DEFINITIONS = COLLECTION_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__OCL_EXPRESSION = COLLECTION_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__OPERATION = COLLECTION_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__MAP_TYPE2 = COLLECTION_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__ATTRIBUTE = COLLECTION_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__MAP_TYPE = COLLECTION_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__COLLECTION_TYPES = COLLECTION_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__TUPLE_TYPE_ATTRIBUTE = COLLECTION_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__VARIABLE_DECLARATION = COLLECTION_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

	/**
	 * The number of structural features of the '<em>Bag Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Bag Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_TYPE_OPERATION_COUNT = COLLECTION_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OrderedSetTypeImpl <em>Ordered Set Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OrderedSetTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOrderedSetType()
	 * @generated
	 */
	int ORDERED_SET_TYPE = 42;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__LOCATION = COLLECTION_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__COMMENTS_BEFORE = COLLECTION_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__COMMENTS_AFTER = COLLECTION_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__FILE_LOCATION = COLLECTION_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__FILE_OBJECT = COLLECTION_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__INFERRED_TYPE = COLLECTION_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__TYPE = COLLECTION_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__IF_EXP3 = COLLECTION_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__APPLIED_PROPERTY = COLLECTION_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__COLLECTION = COLLECTION_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__LET_EXP = COLLECTION_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__LOOP_EXP = COLLECTION_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__PARENT_OPERATION = COLLECTION_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__INITIALIZED_VARIABLE = COLLECTION_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__IF_EXP2 = COLLECTION_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__OWNING_OPERATION = COLLECTION_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__IF_EXP1 = COLLECTION_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__OWNING_ATTRIBUTE = COLLECTION_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__IMPLICITLY_CASTED = COLLECTION_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__DEFINITIONS = COLLECTION_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__OCL_EXPRESSION = COLLECTION_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__OPERATION = COLLECTION_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__MAP_TYPE2 = COLLECTION_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__ATTRIBUTE = COLLECTION_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__MAP_TYPE = COLLECTION_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__COLLECTION_TYPES = COLLECTION_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__TUPLE_TYPE_ATTRIBUTE = COLLECTION_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__VARIABLE_DECLARATION = COLLECTION_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

	/**
	 * The number of structural features of the '<em>Ordered Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Ordered Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_TYPE_OPERATION_COUNT = COLLECTION_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.SequenceTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSequenceType()
	 * @generated
	 */
	int SEQUENCE_TYPE = 43;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__LOCATION = COLLECTION_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__COMMENTS_BEFORE = COLLECTION_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__COMMENTS_AFTER = COLLECTION_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__FILE_LOCATION = COLLECTION_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__FILE_OBJECT = COLLECTION_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__INFERRED_TYPE = COLLECTION_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__TYPE = COLLECTION_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__IF_EXP3 = COLLECTION_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__APPLIED_PROPERTY = COLLECTION_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__COLLECTION = COLLECTION_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__LET_EXP = COLLECTION_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__LOOP_EXP = COLLECTION_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__PARENT_OPERATION = COLLECTION_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__INITIALIZED_VARIABLE = COLLECTION_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__IF_EXP2 = COLLECTION_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__OWNING_OPERATION = COLLECTION_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__IF_EXP1 = COLLECTION_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__OWNING_ATTRIBUTE = COLLECTION_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__IMPLICITLY_CASTED = COLLECTION_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__DEFINITIONS = COLLECTION_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__OCL_EXPRESSION = COLLECTION_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__OPERATION = COLLECTION_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__MAP_TYPE2 = COLLECTION_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__ATTRIBUTE = COLLECTION_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__MAP_TYPE = COLLECTION_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__COLLECTION_TYPES = COLLECTION_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__TUPLE_TYPE_ATTRIBUTE = COLLECTION_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__VARIABLE_DECLARATION = COLLECTION_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

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
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.SetTypeImpl <em>Set Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.SetTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSetType()
	 * @generated
	 */
	int SET_TYPE = 44;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__LOCATION = COLLECTION_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__COMMENTS_BEFORE = COLLECTION_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__COMMENTS_AFTER = COLLECTION_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__FILE_LOCATION = COLLECTION_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__FILE_OBJECT = COLLECTION_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__INFERRED_TYPE = COLLECTION_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__TYPE = COLLECTION_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__IF_EXP3 = COLLECTION_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__APPLIED_PROPERTY = COLLECTION_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__COLLECTION = COLLECTION_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__LET_EXP = COLLECTION_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__LOOP_EXP = COLLECTION_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__PARENT_OPERATION = COLLECTION_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__INITIALIZED_VARIABLE = COLLECTION_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__IF_EXP2 = COLLECTION_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__OWNING_OPERATION = COLLECTION_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__IF_EXP1 = COLLECTION_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__OWNING_ATTRIBUTE = COLLECTION_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__IMPLICITLY_CASTED = COLLECTION_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__DEFINITIONS = COLLECTION_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__OCL_EXPRESSION = COLLECTION_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__OPERATION = COLLECTION_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__MAP_TYPE2 = COLLECTION_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__ATTRIBUTE = COLLECTION_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__MAP_TYPE = COLLECTION_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__COLLECTION_TYPES = COLLECTION_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__TUPLE_TYPE_ATTRIBUTE = COLLECTION_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__VARIABLE_DECLARATION = COLLECTION_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

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
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclAnyTypeImpl <em>Ocl Any Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclAnyTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclAnyType()
	 * @generated
	 */
	int OCL_ANY_TYPE = 45;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__LOCATION = OCL_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__COMMENTS_BEFORE = OCL_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__COMMENTS_AFTER = OCL_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__FILE_LOCATION = OCL_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__FILE_OBJECT = OCL_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__INFERRED_TYPE = OCL_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__TYPE = OCL_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__IF_EXP3 = OCL_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__APPLIED_PROPERTY = OCL_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__COLLECTION = OCL_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__LET_EXP = OCL_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__LOOP_EXP = OCL_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__PARENT_OPERATION = OCL_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__INITIALIZED_VARIABLE = OCL_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__IF_EXP2 = OCL_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__OWNING_OPERATION = OCL_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__IF_EXP1 = OCL_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__OWNING_ATTRIBUTE = OCL_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__IMPLICITLY_CASTED = OCL_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__NAME = OCL_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__DEFINITIONS = OCL_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__OCL_EXPRESSION = OCL_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__OPERATION = OCL_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__MAP_TYPE2 = OCL_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__ATTRIBUTE = OCL_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__MAP_TYPE = OCL_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__COLLECTION_TYPES = OCL_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__TUPLE_TYPE_ATTRIBUTE = OCL_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE__VARIABLE_DECLARATION = OCL_TYPE__VARIABLE_DECLARATION;

	/**
	 * The number of structural features of the '<em>Ocl Any Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE_FEATURE_COUNT = OCL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Ocl Any Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_ANY_TYPE_OPERATION_COUNT = OCL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.TupleTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTupleType()
	 * @generated
	 */
	int TUPLE_TYPE = 46;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__LOCATION = OCL_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__COMMENTS_BEFORE = OCL_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__COMMENTS_AFTER = OCL_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__FILE_LOCATION = OCL_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__FILE_OBJECT = OCL_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__INFERRED_TYPE = OCL_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__TYPE = OCL_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__IF_EXP3 = OCL_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__APPLIED_PROPERTY = OCL_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__COLLECTION = OCL_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__LET_EXP = OCL_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__LOOP_EXP = OCL_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__PARENT_OPERATION = OCL_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__INITIALIZED_VARIABLE = OCL_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__IF_EXP2 = OCL_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__OWNING_OPERATION = OCL_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__IF_EXP1 = OCL_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__OWNING_ATTRIBUTE = OCL_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__IMPLICITLY_CASTED = OCL_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__NAME = OCL_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__DEFINITIONS = OCL_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__OCL_EXPRESSION = OCL_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__OPERATION = OCL_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__MAP_TYPE2 = OCL_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__ATTRIBUTE = OCL_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__MAP_TYPE = OCL_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__COLLECTION_TYPES = OCL_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__TUPLE_TYPE_ATTRIBUTE = OCL_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__VARIABLE_DECLARATION = OCL_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE__ATTRIBUTES = OCL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tuple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_FEATURE_COUNT = OCL_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tuple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_OPERATION_COUNT = OCL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.TupleTypeAttributeImpl <em>Tuple Type Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.TupleTypeAttributeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTupleTypeAttribute()
	 * @generated
	 */
	int TUPLE_TYPE_ATTRIBUTE = 47;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__TYPE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tuple Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE__NAME = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tuple Type Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Tuple Type Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_TYPE_ATTRIBUTE_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclModelElementImpl <em>Ocl Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclModelElementImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclModelElement()
	 * @generated
	 */
	int OCL_MODEL_ELEMENT = 48;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__LOCATION = OCL_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__COMMENTS_BEFORE = OCL_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__COMMENTS_AFTER = OCL_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__FILE_LOCATION = OCL_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__FILE_OBJECT = OCL_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__INFERRED_TYPE = OCL_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__TYPE = OCL_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__IF_EXP3 = OCL_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__APPLIED_PROPERTY = OCL_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__COLLECTION = OCL_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__LET_EXP = OCL_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__LOOP_EXP = OCL_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__PARENT_OPERATION = OCL_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__INITIALIZED_VARIABLE = OCL_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__IF_EXP2 = OCL_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__OWNING_OPERATION = OCL_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__IF_EXP1 = OCL_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__OWNING_ATTRIBUTE = OCL_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__IMPLICITLY_CASTED = OCL_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__NAME = OCL_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__DEFINITIONS = OCL_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__OCL_EXPRESSION = OCL_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__OPERATION = OCL_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__MAP_TYPE2 = OCL_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__ATTRIBUTE = OCL_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__MAP_TYPE = OCL_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__COLLECTION_TYPES = OCL_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__TUPLE_TYPE_ATTRIBUTE = OCL_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__VARIABLE_DECLARATION = OCL_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT__MODEL = OCL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ocl Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT_FEATURE_COUNT = OCL_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Ocl Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_ELEMENT_OPERATION_COUNT = OCL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.MapTypeImpl <em>Map Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.MapTypeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getMapType()
	 * @generated
	 */
	int MAP_TYPE = 49;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__LOCATION = OCL_TYPE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__COMMENTS_BEFORE = OCL_TYPE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__COMMENTS_AFTER = OCL_TYPE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__FILE_LOCATION = OCL_TYPE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__FILE_OBJECT = OCL_TYPE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__INFERRED_TYPE = OCL_TYPE__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__TYPE = OCL_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__IF_EXP3 = OCL_TYPE__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__APPLIED_PROPERTY = OCL_TYPE__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__COLLECTION = OCL_TYPE__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__LET_EXP = OCL_TYPE__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__LOOP_EXP = OCL_TYPE__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__PARENT_OPERATION = OCL_TYPE__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__INITIALIZED_VARIABLE = OCL_TYPE__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__IF_EXP2 = OCL_TYPE__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__OWNING_OPERATION = OCL_TYPE__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__IF_EXP1 = OCL_TYPE__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__OWNING_ATTRIBUTE = OCL_TYPE__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__IMPLICITLY_CASTED = OCL_TYPE__IMPLICITLY_CASTED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__NAME = OCL_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__DEFINITIONS = OCL_TYPE__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__OCL_EXPRESSION = OCL_TYPE__OCL_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__OPERATION = OCL_TYPE__OPERATION;

	/**
	 * The feature id for the '<em><b>Map Type2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__MAP_TYPE2 = OCL_TYPE__MAP_TYPE2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__ATTRIBUTE = OCL_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Map Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__MAP_TYPE = OCL_TYPE__MAP_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Types</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__COLLECTION_TYPES = OCL_TYPE__COLLECTION_TYPES;

	/**
	 * The feature id for the '<em><b>Tuple Type Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__TUPLE_TYPE_ATTRIBUTE = OCL_TYPE__TUPLE_TYPE_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Variable Declaration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__VARIABLE_DECLARATION = OCL_TYPE__VARIABLE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Value Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__VALUE_TYPE = OCL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE__KEY_TYPE = OCL_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Map Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE_FEATURE_COUNT = OCL_TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Map Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_TYPE_OPERATION_COUNT = OCL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclFeatureDefinitionImpl <em>Ocl Feature Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclFeatureDefinitionImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclFeatureDefinition()
	 * @generated
	 */
	int OCL_FEATURE_DEFINITION = 50;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION__FEATURE = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context </b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION__CONTEXT_ = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Ocl Feature Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Ocl Feature Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_DEFINITION_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclContextDefinitionImpl <em>Ocl Context Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclContextDefinitionImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclContextDefinition()
	 * @generated
	 */
	int OCL_CONTEXT_DEFINITION = 51;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION__DEFINITION = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context </b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION__CONTEXT_ = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Ocl Context Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Ocl Context Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_CONTEXT_DEFINITION_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclFeatureImpl <em>Ocl Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclFeatureImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclFeature()
	 * @generated
	 */
	int OCL_FEATURE = 52;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE__DEFINITION = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ocl Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Ocl Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_FEATURE_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.AttributeImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 53;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__LOCATION = OCL_FEATURE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__COMMENTS_BEFORE = OCL_FEATURE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__COMMENTS_AFTER = OCL_FEATURE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__FILE_LOCATION = OCL_FEATURE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__FILE_OBJECT = OCL_FEATURE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DEFINITION = OCL_FEATURE__DEFINITION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = OCL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__INIT_EXPRESSION = OCL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TYPE = OCL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = OCL_FEATURE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = OCL_FEATURE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OperationImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 54;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__LOCATION = OCL_FEATURE__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__COMMENTS_BEFORE = OCL_FEATURE__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__COMMENTS_AFTER = OCL_FEATURE__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__FILE_LOCATION = OCL_FEATURE__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__FILE_OBJECT = OCL_FEATURE__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__DEFINITION = OCL_FEATURE__DEFINITION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = OCL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETERS = OCL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__RETURN_TYPE = OCL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__BODY = OCL_FEATURE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = OCL_FEATURE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = OCL_FEATURE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.OclModelImpl <em>Ocl Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.OclModelImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclModel()
	 * @generated
	 */
	int OCL_MODEL = 55;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__LOCATION = ATLPackage.LOCATED_ELEMENT__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__COMMENTS_BEFORE = ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__COMMENTS_AFTER = ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__FILE_LOCATION = ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__FILE_OBJECT = ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__NAME = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Metamodel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__METAMODEL = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__ELEMENTS = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL__MODEL = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Ocl Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_FEATURE_COUNT = ATLPackage.LOCATED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Ocl Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_MODEL_OPERATION_COUNT = ATLPackage.LOCATED_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.TypedElementImpl <em>Typed Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.TypedElementImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTypedElement()
	 * @generated
	 */
	int TYPED_ELEMENT = 56;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__INFERRED_TYPE = 0;

	/**
	 * The number of structural features of the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.ResolveTempResolutionImpl <em>Resolve Temp Resolution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.ResolveTempResolutionImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getResolveTempResolution()
	 * @generated
	 */
	int RESOLVE_TEMP_RESOLUTION = 57;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_RESOLUTION__RULE = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_RESOLUTION__ELEMENT = 1;

	/**
	 * The number of structural features of the '<em>Resolve Temp Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_RESOLUTION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Resolve Temp Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_TEMP_RESOLUTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.JavaBodyImpl <em>Java Body</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.JavaBodyImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getJavaBody()
	 * @generated
	 */
	int JAVA_BODY = 58;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__LOCATION = OCL_EXPRESSION__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__COMMENTS_BEFORE = OCL_EXPRESSION__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__COMMENTS_AFTER = OCL_EXPRESSION__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__FILE_LOCATION = OCL_EXPRESSION__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__FILE_OBJECT = OCL_EXPRESSION__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__INFERRED_TYPE = OCL_EXPRESSION__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__IF_EXP3 = OCL_EXPRESSION__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__APPLIED_PROPERTY = OCL_EXPRESSION__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__COLLECTION = OCL_EXPRESSION__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__LET_EXP = OCL_EXPRESSION__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__LOOP_EXP = OCL_EXPRESSION__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__PARENT_OPERATION = OCL_EXPRESSION__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__INITIALIZED_VARIABLE = OCL_EXPRESSION__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__IF_EXP2 = OCL_EXPRESSION__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__OWNING_OPERATION = OCL_EXPRESSION__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__IF_EXP1 = OCL_EXPRESSION__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__OWNING_ATTRIBUTE = OCL_EXPRESSION__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY__IMPLICITLY_CASTED = OCL_EXPRESSION__IMPLICITLY_CASTED;

	/**
	 * The number of structural features of the '<em>Java Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY_FEATURE_COUNT = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Java Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_BODY_OPERATION_COUNT = OCL_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.atlext.OCL.impl.GetAppliedStereotypesBodyImpl <em>Get Applied Stereotypes Body</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.atlext.OCL.impl.GetAppliedStereotypesBodyImpl
	 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getGetAppliedStereotypesBody()
	 * @generated
	 */
	int GET_APPLIED_STEREOTYPES_BODY = 59;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__LOCATION = JAVA_BODY__LOCATION;

	/**
	 * The feature id for the '<em><b>Comments Before</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__COMMENTS_BEFORE = JAVA_BODY__COMMENTS_BEFORE;

	/**
	 * The feature id for the '<em><b>Comments After</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__COMMENTS_AFTER = JAVA_BODY__COMMENTS_AFTER;

	/**
	 * The feature id for the '<em><b>File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__FILE_LOCATION = JAVA_BODY__FILE_LOCATION;

	/**
	 * The feature id for the '<em><b>File Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__FILE_OBJECT = JAVA_BODY__FILE_OBJECT;

	/**
	 * The feature id for the '<em><b>Inferred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__INFERRED_TYPE = JAVA_BODY__INFERRED_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__TYPE = JAVA_BODY__TYPE;

	/**
	 * The feature id for the '<em><b>If Exp3</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__IF_EXP3 = JAVA_BODY__IF_EXP3;

	/**
	 * The feature id for the '<em><b>Applied Property</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__APPLIED_PROPERTY = JAVA_BODY__APPLIED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__COLLECTION = JAVA_BODY__COLLECTION;

	/**
	 * The feature id for the '<em><b>Let Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__LET_EXP = JAVA_BODY__LET_EXP;

	/**
	 * The feature id for the '<em><b>Loop Exp</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__LOOP_EXP = JAVA_BODY__LOOP_EXP;

	/**
	 * The feature id for the '<em><b>Parent Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__PARENT_OPERATION = JAVA_BODY__PARENT_OPERATION;

	/**
	 * The feature id for the '<em><b>Initialized Variable</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__INITIALIZED_VARIABLE = JAVA_BODY__INITIALIZED_VARIABLE;

	/**
	 * The feature id for the '<em><b>If Exp2</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__IF_EXP2 = JAVA_BODY__IF_EXP2;

	/**
	 * The feature id for the '<em><b>Owning Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__OWNING_OPERATION = JAVA_BODY__OWNING_OPERATION;

	/**
	 * The feature id for the '<em><b>If Exp1</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__IF_EXP1 = JAVA_BODY__IF_EXP1;

	/**
	 * The feature id for the '<em><b>Owning Attribute</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__OWNING_ATTRIBUTE = JAVA_BODY__OWNING_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Implicitly Casted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY__IMPLICITLY_CASTED = JAVA_BODY__IMPLICITLY_CASTED;

	/**
	 * The number of structural features of the '<em>Get Applied Stereotypes Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY_FEATURE_COUNT = JAVA_BODY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Get Applied Stereotypes Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_APPLIED_STEREOTYPES_BODY_OPERATION_COUNT = JAVA_BODY_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Expression</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression
	 * @generated
	 */
	EClass getOclExpression();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.OclExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getType()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_Type();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getIfExp3 <em>If Exp3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>If Exp3</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getIfExp3()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_IfExp3();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getAppliedProperty <em>Applied Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Applied Property</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getAppliedProperty()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_AppliedProperty();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getCollection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Collection</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getCollection()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_Collection();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getLetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Let Exp</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getLetExp()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_LetExp();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getLoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Loop Exp</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getLoopExp()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_LoopExp();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getParentOperation <em>Parent Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Operation</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getParentOperation()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_ParentOperation();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getInitializedVariable <em>Initialized Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Initialized Variable</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getInitializedVariable()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_InitializedVariable();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getIfExp2 <em>If Exp2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>If Exp2</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getIfExp2()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_IfExp2();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getOwningOperation <em>Owning Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Operation</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getOwningOperation()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_OwningOperation();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getIfExp1 <em>If Exp1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>If Exp1</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getIfExp1()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_IfExp1();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclExpression#getOwningAttribute <em>Owning Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Attribute</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#getOwningAttribute()
	 * @see #getOclExpression()
	 * @generated
	 */
	EReference getOclExpression_OwningAttribute();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.OclExpression#isImplicitlyCasted <em>Implicitly Casted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implicitly Casted</em>'.
	 * @see anatlyzer.atlext.OCL.OclExpression#isImplicitlyCasted()
	 * @see #getOclExpression()
	 * @generated
	 */
	EAttribute getOclExpression_ImplicitlyCasted();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.VariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Exp</em>'.
	 * @see anatlyzer.atlext.OCL.VariableExp
	 * @generated
	 */
	EClass getVariableExp();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.VariableExp#getReferredVariable <em>Referred Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Variable</em>'.
	 * @see anatlyzer.atlext.OCL.VariableExp#getReferredVariable()
	 * @see #getVariableExp()
	 * @generated
	 */
	EReference getVariableExp_ReferredVariable();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.SuperExp <em>Super Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Super Exp</em>'.
	 * @see anatlyzer.atlext.OCL.SuperExp
	 * @generated
	 */
	EClass getSuperExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.PrimitiveExp <em>Primitive Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Exp</em>'.
	 * @see anatlyzer.atlext.OCL.PrimitiveExp
	 * @generated
	 */
	EClass getPrimitiveExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.StringExp <em>String Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Exp</em>'.
	 * @see anatlyzer.atlext.OCL.StringExp
	 * @generated
	 */
	EClass getStringExp();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.StringExp#getStringSymbol <em>String Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Symbol</em>'.
	 * @see anatlyzer.atlext.OCL.StringExp#getStringSymbol()
	 * @see #getStringExp()
	 * @generated
	 */
	EAttribute getStringExp_StringSymbol();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.BooleanExp <em>Boolean Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Exp</em>'.
	 * @see anatlyzer.atlext.OCL.BooleanExp
	 * @generated
	 */
	EClass getBooleanExp();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.BooleanExp#isBooleanSymbol <em>Boolean Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Symbol</em>'.
	 * @see anatlyzer.atlext.OCL.BooleanExp#isBooleanSymbol()
	 * @see #getBooleanExp()
	 * @generated
	 */
	EAttribute getBooleanExp_BooleanSymbol();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.NumericExp <em>Numeric Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numeric Exp</em>'.
	 * @see anatlyzer.atlext.OCL.NumericExp
	 * @generated
	 */
	EClass getNumericExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.RealExp <em>Real Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Real Exp</em>'.
	 * @see anatlyzer.atlext.OCL.RealExp
	 * @generated
	 */
	EClass getRealExp();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.RealExp#getRealSymbol <em>Real Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Real Symbol</em>'.
	 * @see anatlyzer.atlext.OCL.RealExp#getRealSymbol()
	 * @see #getRealExp()
	 * @generated
	 */
	EAttribute getRealExp_RealSymbol();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.IntegerExp <em>Integer Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Exp</em>'.
	 * @see anatlyzer.atlext.OCL.IntegerExp
	 * @generated
	 */
	EClass getIntegerExp();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.IntegerExp#getIntegerSymbol <em>Integer Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Symbol</em>'.
	 * @see anatlyzer.atlext.OCL.IntegerExp#getIntegerSymbol()
	 * @see #getIntegerExp()
	 * @generated
	 */
	EAttribute getIntegerExp_IntegerSymbol();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.CollectionExp <em>Collection Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Exp</em>'.
	 * @see anatlyzer.atlext.OCL.CollectionExp
	 * @generated
	 */
	EClass getCollectionExp();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.CollectionExp#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see anatlyzer.atlext.OCL.CollectionExp#getElements()
	 * @see #getCollectionExp()
	 * @generated
	 */
	EReference getCollectionExp_Elements();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.BagExp <em>Bag Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bag Exp</em>'.
	 * @see anatlyzer.atlext.OCL.BagExp
	 * @generated
	 */
	EClass getBagExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OrderedSetExp <em>Ordered Set Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Set Exp</em>'.
	 * @see anatlyzer.atlext.OCL.OrderedSetExp
	 * @generated
	 */
	EClass getOrderedSetExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.SequenceExp <em>Sequence Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Exp</em>'.
	 * @see anatlyzer.atlext.OCL.SequenceExp
	 * @generated
	 */
	EClass getSequenceExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.SetExp <em>Set Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Exp</em>'.
	 * @see anatlyzer.atlext.OCL.SetExp
	 * @generated
	 */
	EClass getSetExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.TupleExp <em>Tuple Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Exp</em>'.
	 * @see anatlyzer.atlext.OCL.TupleExp
	 * @generated
	 */
	EClass getTupleExp();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.TupleExp#getTuplePart <em>Tuple Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tuple Part</em>'.
	 * @see anatlyzer.atlext.OCL.TupleExp#getTuplePart()
	 * @see #getTupleExp()
	 * @generated
	 */
	EReference getTupleExp_TuplePart();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.TuplePart <em>Tuple Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Part</em>'.
	 * @see anatlyzer.atlext.OCL.TuplePart
	 * @generated
	 */
	EClass getTuplePart();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.TuplePart#getTuple <em>Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tuple</em>'.
	 * @see anatlyzer.atlext.OCL.TuplePart#getTuple()
	 * @see #getTuplePart()
	 * @generated
	 */
	EReference getTuplePart_Tuple();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.MapExp <em>Map Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Exp</em>'.
	 * @see anatlyzer.atlext.OCL.MapExp
	 * @generated
	 */
	EClass getMapExp();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.MapExp#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see anatlyzer.atlext.OCL.MapExp#getElements()
	 * @see #getMapExp()
	 * @generated
	 */
	EReference getMapExp_Elements();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.MapElement <em>Map Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Element</em>'.
	 * @see anatlyzer.atlext.OCL.MapElement
	 * @generated
	 */
	EClass getMapElement();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.MapElement#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Map</em>'.
	 * @see anatlyzer.atlext.OCL.MapElement#getMap()
	 * @see #getMapElement()
	 * @generated
	 */
	EReference getMapElement_Map();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.MapElement#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see anatlyzer.atlext.OCL.MapElement#getKey()
	 * @see #getMapElement()
	 * @generated
	 */
	EReference getMapElement_Key();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.MapElement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see anatlyzer.atlext.OCL.MapElement#getValue()
	 * @see #getMapElement()
	 * @generated
	 */
	EReference getMapElement_Value();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.EnumLiteralExp <em>Enum Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Literal Exp</em>'.
	 * @see anatlyzer.atlext.OCL.EnumLiteralExp
	 * @generated
	 */
	EClass getEnumLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.EnumLiteralExp#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.EnumLiteralExp#getName()
	 * @see #getEnumLiteralExp()
	 * @generated
	 */
	EAttribute getEnumLiteralExp_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclUndefinedExp <em>Ocl Undefined Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Undefined Exp</em>'.
	 * @see anatlyzer.atlext.OCL.OclUndefinedExp
	 * @generated
	 */
	EClass getOclUndefinedExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.PropertyCallExp <em>Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Call Exp</em>'.
	 * @see anatlyzer.atlext.OCL.PropertyCallExp
	 * @generated
	 */
	EClass getPropertyCallExp();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.PropertyCallExp#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#getSource()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	EReference getPropertyCallExp_Source();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.PropertyCallExp#getUsedFeature <em>Used Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Used Feature</em>'.
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#getUsedFeature()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	EReference getPropertyCallExp_UsedFeature();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.PropertyCallExp#getReceptorType <em>Receptor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Receptor Type</em>'.
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#getReceptorType()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	EReference getPropertyCallExp_ReceptorType();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.PropertyCallExp#isIsStaticCall <em>Is Static Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Static Call</em>'.
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#isIsStaticCall()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	EAttribute getPropertyCallExp_IsStaticCall();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.PropertyCallExp#getStaticResolver <em>Static Resolver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Static Resolver</em>'.
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#getStaticResolver()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	EReference getPropertyCallExp_StaticResolver();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.OCL.PropertyCallExp#getDynamicResolvers <em>Dynamic Resolvers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dynamic Resolvers</em>'.
	 * @see anatlyzer.atlext.OCL.PropertyCallExp#getDynamicResolvers()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	EReference getPropertyCallExp_DynamicResolvers();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.NavigationOrAttributeCallExp <em>Navigation Or Attribute Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Or Attribute Call Exp</em>'.
	 * @see anatlyzer.atlext.OCL.NavigationOrAttributeCallExp
	 * @generated
	 */
	EClass getNavigationOrAttributeCallExp();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.NavigationOrAttributeCallExp#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.NavigationOrAttributeCallExp#getName()
	 * @see #getNavigationOrAttributeCallExp()
	 * @generated
	 */
	EAttribute getNavigationOrAttributeCallExp_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OperationCallExp <em>Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Call Exp</em>'.
	 * @see anatlyzer.atlext.OCL.OperationCallExp
	 * @generated
	 */
	EClass getOperationCallExp();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.OperationCallExp#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see anatlyzer.atlext.OCL.OperationCallExp#getArguments()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	EReference getOperationCallExp_Arguments();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.OperationCallExp#getOperationName <em>Operation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Name</em>'.
	 * @see anatlyzer.atlext.OCL.OperationCallExp#getOperationName()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	EAttribute getOperationCallExp_OperationName();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.OperationCallExp#getResolveTempResolvedBy <em>Resolve Temp Resolved By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resolve Temp Resolved By</em>'.
	 * @see anatlyzer.atlext.OCL.OperationCallExp#getResolveTempResolvedBy()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	EReference getOperationCallExp_ResolveTempResolvedBy();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OperatorCallExp <em>Operator Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operator Call Exp</em>'.
	 * @see anatlyzer.atlext.OCL.OperatorCallExp
	 * @generated
	 */
	EClass getOperatorCallExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.CollectionOperationCallExp <em>Collection Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Operation Call Exp</em>'.
	 * @see anatlyzer.atlext.OCL.CollectionOperationCallExp
	 * @generated
	 */
	EClass getCollectionOperationCallExp();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.LoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loop Exp</em>'.
	 * @see anatlyzer.atlext.OCL.LoopExp
	 * @generated
	 */
	EClass getLoopExp();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.LoopExp#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see anatlyzer.atlext.OCL.LoopExp#getBody()
	 * @see #getLoopExp()
	 * @generated
	 */
	EReference getLoopExp_Body();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.LoopExp#getIterators <em>Iterators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Iterators</em>'.
	 * @see anatlyzer.atlext.OCL.LoopExp#getIterators()
	 * @see #getLoopExp()
	 * @generated
	 */
	EReference getLoopExp_Iterators();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.IterateExp <em>Iterate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterate Exp</em>'.
	 * @see anatlyzer.atlext.OCL.IterateExp
	 * @generated
	 */
	EClass getIterateExp();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.IterateExp#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result</em>'.
	 * @see anatlyzer.atlext.OCL.IterateExp#getResult()
	 * @see #getIterateExp()
	 * @generated
	 */
	EReference getIterateExp_Result();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.IteratorExp <em>Iterator Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator Exp</em>'.
	 * @see anatlyzer.atlext.OCL.IteratorExp
	 * @generated
	 */
	EClass getIteratorExp();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.IteratorExp#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.IteratorExp#getName()
	 * @see #getIteratorExp()
	 * @generated
	 */
	EAttribute getIteratorExp_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.LetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Let Exp</em>'.
	 * @see anatlyzer.atlext.OCL.LetExp
	 * @generated
	 */
	EClass getLetExp();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.LetExp#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variable</em>'.
	 * @see anatlyzer.atlext.OCL.LetExp#getVariable()
	 * @see #getLetExp()
	 * @generated
	 */
	EReference getLetExp_Variable();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.LetExp#getIn_ <em>In </em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>In </em>'.
	 * @see anatlyzer.atlext.OCL.LetExp#getIn_()
	 * @see #getLetExp()
	 * @generated
	 */
	EReference getLetExp_In_();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.IfExp <em>If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Exp</em>'.
	 * @see anatlyzer.atlext.OCL.IfExp
	 * @generated
	 */
	EClass getIfExp();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.IfExp#getThenExpression <em>Then Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Expression</em>'.
	 * @see anatlyzer.atlext.OCL.IfExp#getThenExpression()
	 * @see #getIfExp()
	 * @generated
	 */
	EReference getIfExp_ThenExpression();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.IfExp#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see anatlyzer.atlext.OCL.IfExp#getCondition()
	 * @see #getIfExp()
	 * @generated
	 */
	EReference getIfExp_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.IfExp#getElseExpression <em>Else Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Expression</em>'.
	 * @see anatlyzer.atlext.OCL.IfExp#getElseExpression()
	 * @see #getIfExp()
	 * @generated
	 */
	EReference getIfExp_ElseExpression();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration
	 * @generated
	 */
	EClass getVariableDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.VariableDeclaration#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getId()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EAttribute getVariableDeclaration_Id();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.VariableDeclaration#getVarName <em>Var Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Var Name</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getVarName()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EAttribute getVariableDeclaration_VarName();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.VariableDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getType()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_Type();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.VariableDeclaration#getInitExpression <em>Init Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Init Expression</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getInitExpression()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_InitExpression();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.VariableDeclaration#getLetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Let Exp</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getLetExp()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_LetExp();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.VariableDeclaration#getBaseExp <em>Base Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Base Exp</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getBaseExp()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_BaseExp();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.OCL.VariableDeclaration#getVariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variable Exp</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getVariableExp()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_VariableExp();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.VariableDeclaration#getStaticType <em>Static Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Static Type</em>'.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration#getStaticType()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_StaticType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.Iterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator</em>'.
	 * @see anatlyzer.atlext.OCL.Iterator
	 * @generated
	 */
	EClass getIterator();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.Iterator#getLoopExpr <em>Loop Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Loop Expr</em>'.
	 * @see anatlyzer.atlext.OCL.Iterator#getLoopExpr()
	 * @see #getIterator()
	 * @generated
	 */
	EReference getIterator_LoopExpr();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see anatlyzer.atlext.OCL.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.CollectionType <em>Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Type</em>'.
	 * @see anatlyzer.atlext.OCL.CollectionType
	 * @generated
	 */
	EClass getCollectionType();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.CollectionType#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element Type</em>'.
	 * @see anatlyzer.atlext.OCL.CollectionType#getElementType()
	 * @see #getCollectionType()
	 * @generated
	 */
	EReference getCollectionType_ElementType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclType <em>Ocl Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Type</em>'.
	 * @see anatlyzer.atlext.OCL.OclType
	 * @generated
	 */
	EClass getOclType();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.OclType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getName()
	 * @see #getOclType()
	 * @generated
	 */
	EAttribute getOclType_Name();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getDefinitions <em>Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Definitions</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getDefinitions()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_Definitions();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getOclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Ocl Expression</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getOclExpression()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_OclExpression();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Operation</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getOperation()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_Operation();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getMapType2 <em>Map Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Map Type2</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getMapType2()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_MapType2();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Attribute</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getAttribute()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_Attribute();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getMapType <em>Map Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Map Type</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getMapType()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_MapType();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getCollectionTypes <em>Collection Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Collection Types</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getCollectionTypes()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_CollectionTypes();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getTupleTypeAttribute <em>Tuple Type Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tuple Type Attribute</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getTupleTypeAttribute()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_TupleTypeAttribute();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclType#getVariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Variable Declaration</em>'.
	 * @see anatlyzer.atlext.OCL.OclType#getVariableDeclaration()
	 * @see #getOclType()
	 * @generated
	 */
	EReference getOclType_VariableDeclaration();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.Primitive <em>Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive</em>'.
	 * @see anatlyzer.atlext.OCL.Primitive
	 * @generated
	 */
	EClass getPrimitive();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.StringType <em>String Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Type</em>'.
	 * @see anatlyzer.atlext.OCL.StringType
	 * @generated
	 */
	EClass getStringType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.BooleanType <em>Boolean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Type</em>'.
	 * @see anatlyzer.atlext.OCL.BooleanType
	 * @generated
	 */
	EClass getBooleanType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.NumericType <em>Numeric Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numeric Type</em>'.
	 * @see anatlyzer.atlext.OCL.NumericType
	 * @generated
	 */
	EClass getNumericType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.IntegerType <em>Integer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Type</em>'.
	 * @see anatlyzer.atlext.OCL.IntegerType
	 * @generated
	 */
	EClass getIntegerType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.RealType <em>Real Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Real Type</em>'.
	 * @see anatlyzer.atlext.OCL.RealType
	 * @generated
	 */
	EClass getRealType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.BagType <em>Bag Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bag Type</em>'.
	 * @see anatlyzer.atlext.OCL.BagType
	 * @generated
	 */
	EClass getBagType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OrderedSetType <em>Ordered Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Set Type</em>'.
	 * @see anatlyzer.atlext.OCL.OrderedSetType
	 * @generated
	 */
	EClass getOrderedSetType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Type</em>'.
	 * @see anatlyzer.atlext.OCL.SequenceType
	 * @generated
	 */
	EClass getSequenceType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.SetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Type</em>'.
	 * @see anatlyzer.atlext.OCL.SetType
	 * @generated
	 */
	EClass getSetType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclAnyType <em>Ocl Any Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Any Type</em>'.
	 * @see anatlyzer.atlext.OCL.OclAnyType
	 * @generated
	 */
	EClass getOclAnyType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.TupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Type</em>'.
	 * @see anatlyzer.atlext.OCL.TupleType
	 * @generated
	 */
	EClass getTupleType();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.TupleType#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see anatlyzer.atlext.OCL.TupleType#getAttributes()
	 * @see #getTupleType()
	 * @generated
	 */
	EReference getTupleType_Attributes();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.TupleTypeAttribute <em>Tuple Type Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Type Attribute</em>'.
	 * @see anatlyzer.atlext.OCL.TupleTypeAttribute
	 * @generated
	 */
	EClass getTupleTypeAttribute();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.TupleTypeAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see anatlyzer.atlext.OCL.TupleTypeAttribute#getType()
	 * @see #getTupleTypeAttribute()
	 * @generated
	 */
	EReference getTupleTypeAttribute_Type();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.TupleTypeAttribute#getTupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tuple Type</em>'.
	 * @see anatlyzer.atlext.OCL.TupleTypeAttribute#getTupleType()
	 * @see #getTupleTypeAttribute()
	 * @generated
	 */
	EReference getTupleTypeAttribute_TupleType();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.TupleTypeAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.TupleTypeAttribute#getName()
	 * @see #getTupleTypeAttribute()
	 * @generated
	 */
	EAttribute getTupleTypeAttribute_Name();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclModelElement <em>Ocl Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Model Element</em>'.
	 * @see anatlyzer.atlext.OCL.OclModelElement
	 * @generated
	 */
	EClass getOclModelElement();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.OclModelElement#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model</em>'.
	 * @see anatlyzer.atlext.OCL.OclModelElement#getModel()
	 * @see #getOclModelElement()
	 * @generated
	 */
	EReference getOclModelElement_Model();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.MapType <em>Map Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Type</em>'.
	 * @see anatlyzer.atlext.OCL.MapType
	 * @generated
	 */
	EClass getMapType();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.MapType#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Type</em>'.
	 * @see anatlyzer.atlext.OCL.MapType#getValueType()
	 * @see #getMapType()
	 * @generated
	 */
	EReference getMapType_ValueType();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.MapType#getKeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key Type</em>'.
	 * @see anatlyzer.atlext.OCL.MapType#getKeyType()
	 * @see #getMapType()
	 * @generated
	 */
	EReference getMapType_KeyType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclFeatureDefinition <em>Ocl Feature Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Feature Definition</em>'.
	 * @see anatlyzer.atlext.OCL.OclFeatureDefinition
	 * @generated
	 */
	EClass getOclFeatureDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.OclFeatureDefinition#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Feature</em>'.
	 * @see anatlyzer.atlext.OCL.OclFeatureDefinition#getFeature()
	 * @see #getOclFeatureDefinition()
	 * @generated
	 */
	EReference getOclFeatureDefinition_Feature();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.OclFeatureDefinition#getContext_ <em>Context </em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Context </em>'.
	 * @see anatlyzer.atlext.OCL.OclFeatureDefinition#getContext_()
	 * @see #getOclFeatureDefinition()
	 * @generated
	 */
	EReference getOclFeatureDefinition_Context_();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclContextDefinition <em>Ocl Context Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Context Definition</em>'.
	 * @see anatlyzer.atlext.OCL.OclContextDefinition
	 * @generated
	 */
	EClass getOclContextDefinition();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclContextDefinition#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Definition</em>'.
	 * @see anatlyzer.atlext.OCL.OclContextDefinition#getDefinition()
	 * @see #getOclContextDefinition()
	 * @generated
	 */
	EReference getOclContextDefinition_Definition();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.OclContextDefinition#getContext_ <em>Context </em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Context </em>'.
	 * @see anatlyzer.atlext.OCL.OclContextDefinition#getContext_()
	 * @see #getOclContextDefinition()
	 * @generated
	 */
	EReference getOclContextDefinition_Context_();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclFeature <em>Ocl Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Feature</em>'.
	 * @see anatlyzer.atlext.OCL.OclFeature
	 * @generated
	 */
	EClass getOclFeature();

	/**
	 * Returns the meta object for the container reference '{@link anatlyzer.atlext.OCL.OclFeature#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Definition</em>'.
	 * @see anatlyzer.atlext.OCL.OclFeature#getDefinition()
	 * @see #getOclFeature()
	 * @generated
	 */
	EReference getOclFeature_Definition();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see anatlyzer.atlext.OCL.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.Attribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.Attribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.Attribute#getInitExpression <em>Init Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Init Expression</em>'.
	 * @see anatlyzer.atlext.OCL.Attribute#getInitExpression()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_InitExpression();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.Attribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see anatlyzer.atlext.OCL.Attribute#getType()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Type();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see anatlyzer.atlext.OCL.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.Operation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.Operation#getName()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.atlext.OCL.Operation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see anatlyzer.atlext.OCL.Operation#getParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Parameters();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.Operation#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return Type</em>'.
	 * @see anatlyzer.atlext.OCL.Operation#getReturnType()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_ReturnType();

	/**
	 * Returns the meta object for the containment reference '{@link anatlyzer.atlext.OCL.Operation#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see anatlyzer.atlext.OCL.Operation#getBody()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Body();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.OclModel <em>Ocl Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Model</em>'.
	 * @see anatlyzer.atlext.OCL.OclModel
	 * @generated
	 */
	EClass getOclModel();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.atlext.OCL.OclModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see anatlyzer.atlext.OCL.OclModel#getName()
	 * @see #getOclModel()
	 * @generated
	 */
	EAttribute getOclModel_Name();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.OclModel#getMetamodel <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Metamodel</em>'.
	 * @see anatlyzer.atlext.OCL.OclModel#getMetamodel()
	 * @see #getOclModel()
	 * @generated
	 */
	EReference getOclModel_Metamodel();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.OCL.OclModel#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see anatlyzer.atlext.OCL.OclModel#getElements()
	 * @see #getOclModel()
	 * @generated
	 */
	EReference getOclModel_Elements();

	/**
	 * Returns the meta object for the reference list '{@link anatlyzer.atlext.OCL.OclModel#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Model</em>'.
	 * @see anatlyzer.atlext.OCL.OclModel#getModel()
	 * @see #getOclModel()
	 * @generated
	 */
	EReference getOclModel_Model();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Element</em>'.
	 * @see anatlyzer.atlext.OCL.TypedElement
	 * @generated
	 */
	EClass getTypedElement();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.TypedElement#getInferredType <em>Inferred Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Inferred Type</em>'.
	 * @see anatlyzer.atlext.OCL.TypedElement#getInferredType()
	 * @see #getTypedElement()
	 * @generated
	 */
	EReference getTypedElement_InferredType();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.ResolveTempResolution <em>Resolve Temp Resolution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolve Temp Resolution</em>'.
	 * @see anatlyzer.atlext.OCL.ResolveTempResolution
	 * @generated
	 */
	EClass getResolveTempResolution();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.ResolveTempResolution#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rule</em>'.
	 * @see anatlyzer.atlext.OCL.ResolveTempResolution#getRule()
	 * @see #getResolveTempResolution()
	 * @generated
	 */
	EReference getResolveTempResolution_Rule();

	/**
	 * Returns the meta object for the reference '{@link anatlyzer.atlext.OCL.ResolveTempResolution#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see anatlyzer.atlext.OCL.ResolveTempResolution#getElement()
	 * @see #getResolveTempResolution()
	 * @generated
	 */
	EReference getResolveTempResolution_Element();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.JavaBody <em>Java Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Body</em>'.
	 * @see anatlyzer.atlext.OCL.JavaBody
	 * @generated
	 */
	EClass getJavaBody();

	/**
	 * Returns the meta object for class '{@link anatlyzer.atlext.OCL.GetAppliedStereotypesBody <em>Get Applied Stereotypes Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get Applied Stereotypes Body</em>'.
	 * @see anatlyzer.atlext.OCL.GetAppliedStereotypesBody
	 * @generated
	 */
	EClass getGetAppliedStereotypesBody();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OCLFactory getOCLFactory();

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
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclExpressionImpl <em>Ocl Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclExpressionImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclExpression()
		 * @generated
		 */
		EClass OCL_EXPRESSION = eINSTANCE.getOclExpression();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__TYPE = eINSTANCE.getOclExpression_Type();

		/**
		 * The meta object literal for the '<em><b>If Exp3</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__IF_EXP3 = eINSTANCE.getOclExpression_IfExp3();

		/**
		 * The meta object literal for the '<em><b>Applied Property</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__APPLIED_PROPERTY = eINSTANCE.getOclExpression_AppliedProperty();

		/**
		 * The meta object literal for the '<em><b>Collection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__COLLECTION = eINSTANCE.getOclExpression_Collection();

		/**
		 * The meta object literal for the '<em><b>Let Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__LET_EXP = eINSTANCE.getOclExpression_LetExp();

		/**
		 * The meta object literal for the '<em><b>Loop Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__LOOP_EXP = eINSTANCE.getOclExpression_LoopExp();

		/**
		 * The meta object literal for the '<em><b>Parent Operation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__PARENT_OPERATION = eINSTANCE.getOclExpression_ParentOperation();

		/**
		 * The meta object literal for the '<em><b>Initialized Variable</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__INITIALIZED_VARIABLE = eINSTANCE.getOclExpression_InitializedVariable();

		/**
		 * The meta object literal for the '<em><b>If Exp2</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__IF_EXP2 = eINSTANCE.getOclExpression_IfExp2();

		/**
		 * The meta object literal for the '<em><b>Owning Operation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__OWNING_OPERATION = eINSTANCE.getOclExpression_OwningOperation();

		/**
		 * The meta object literal for the '<em><b>If Exp1</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__IF_EXP1 = eINSTANCE.getOclExpression_IfExp1();

		/**
		 * The meta object literal for the '<em><b>Owning Attribute</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__OWNING_ATTRIBUTE = eINSTANCE.getOclExpression_OwningAttribute();

		/**
		 * The meta object literal for the '<em><b>Implicitly Casted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCL_EXPRESSION__IMPLICITLY_CASTED = eINSTANCE.getOclExpression_ImplicitlyCasted();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.VariableExpImpl <em>Variable Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.VariableExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getVariableExp()
		 * @generated
		 */
		EClass VARIABLE_EXP = eINSTANCE.getVariableExp();

		/**
		 * The meta object literal for the '<em><b>Referred Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_EXP__REFERRED_VARIABLE = eINSTANCE.getVariableExp_ReferredVariable();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.SuperExpImpl <em>Super Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.SuperExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSuperExp()
		 * @generated
		 */
		EClass SUPER_EXP = eINSTANCE.getSuperExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.PrimitiveExpImpl <em>Primitive Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.PrimitiveExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getPrimitiveExp()
		 * @generated
		 */
		EClass PRIMITIVE_EXP = eINSTANCE.getPrimitiveExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.StringExpImpl <em>String Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.StringExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getStringExp()
		 * @generated
		 */
		EClass STRING_EXP = eINSTANCE.getStringExp();

		/**
		 * The meta object literal for the '<em><b>String Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_EXP__STRING_SYMBOL = eINSTANCE.getStringExp_StringSymbol();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.BooleanExpImpl <em>Boolean Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.BooleanExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBooleanExp()
		 * @generated
		 */
		EClass BOOLEAN_EXP = eINSTANCE.getBooleanExp();

		/**
		 * The meta object literal for the '<em><b>Boolean Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_EXP__BOOLEAN_SYMBOL = eINSTANCE.getBooleanExp_BooleanSymbol();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.NumericExpImpl <em>Numeric Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.NumericExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getNumericExp()
		 * @generated
		 */
		EClass NUMERIC_EXP = eINSTANCE.getNumericExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.RealExpImpl <em>Real Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.RealExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getRealExp()
		 * @generated
		 */
		EClass REAL_EXP = eINSTANCE.getRealExp();

		/**
		 * The meta object literal for the '<em><b>Real Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REAL_EXP__REAL_SYMBOL = eINSTANCE.getRealExp_RealSymbol();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.IntegerExpImpl <em>Integer Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.IntegerExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIntegerExp()
		 * @generated
		 */
		EClass INTEGER_EXP = eINSTANCE.getIntegerExp();

		/**
		 * The meta object literal for the '<em><b>Integer Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_EXP__INTEGER_SYMBOL = eINSTANCE.getIntegerExp_IntegerSymbol();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.CollectionExpImpl <em>Collection Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.CollectionExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getCollectionExp()
		 * @generated
		 */
		EClass COLLECTION_EXP = eINSTANCE.getCollectionExp();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_EXP__ELEMENTS = eINSTANCE.getCollectionExp_Elements();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.BagExpImpl <em>Bag Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.BagExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBagExp()
		 * @generated
		 */
		EClass BAG_EXP = eINSTANCE.getBagExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OrderedSetExpImpl <em>Ordered Set Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OrderedSetExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOrderedSetExp()
		 * @generated
		 */
		EClass ORDERED_SET_EXP = eINSTANCE.getOrderedSetExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.SequenceExpImpl <em>Sequence Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.SequenceExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSequenceExp()
		 * @generated
		 */
		EClass SEQUENCE_EXP = eINSTANCE.getSequenceExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.SetExpImpl <em>Set Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.SetExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSetExp()
		 * @generated
		 */
		EClass SET_EXP = eINSTANCE.getSetExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.TupleExpImpl <em>Tuple Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.TupleExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTupleExp()
		 * @generated
		 */
		EClass TUPLE_EXP = eINSTANCE.getTupleExp();

		/**
		 * The meta object literal for the '<em><b>Tuple Part</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_EXP__TUPLE_PART = eINSTANCE.getTupleExp_TuplePart();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.TuplePartImpl <em>Tuple Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.TuplePartImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTuplePart()
		 * @generated
		 */
		EClass TUPLE_PART = eINSTANCE.getTuplePart();

		/**
		 * The meta object literal for the '<em><b>Tuple</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_PART__TUPLE = eINSTANCE.getTuplePart_Tuple();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.MapExpImpl <em>Map Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.MapExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getMapExp()
		 * @generated
		 */
		EClass MAP_EXP = eINSTANCE.getMapExp();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_EXP__ELEMENTS = eINSTANCE.getMapExp_Elements();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.MapElementImpl <em>Map Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.MapElementImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getMapElement()
		 * @generated
		 */
		EClass MAP_ELEMENT = eINSTANCE.getMapElement();

		/**
		 * The meta object literal for the '<em><b>Map</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_ELEMENT__MAP = eINSTANCE.getMapElement_Map();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_ELEMENT__KEY = eINSTANCE.getMapElement_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_ELEMENT__VALUE = eINSTANCE.getMapElement_Value();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.EnumLiteralExpImpl <em>Enum Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.EnumLiteralExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getEnumLiteralExp()
		 * @generated
		 */
		EClass ENUM_LITERAL_EXP = eINSTANCE.getEnumLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUM_LITERAL_EXP__NAME = eINSTANCE.getEnumLiteralExp_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclUndefinedExpImpl <em>Ocl Undefined Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclUndefinedExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclUndefinedExp()
		 * @generated
		 */
		EClass OCL_UNDEFINED_EXP = eINSTANCE.getOclUndefinedExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl <em>Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.PropertyCallExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getPropertyCallExp()
		 * @generated
		 */
		EClass PROPERTY_CALL_EXP = eINSTANCE.getPropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_CALL_EXP__SOURCE = eINSTANCE.getPropertyCallExp_Source();

		/**
		 * The meta object literal for the '<em><b>Used Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_CALL_EXP__USED_FEATURE = eINSTANCE.getPropertyCallExp_UsedFeature();

		/**
		 * The meta object literal for the '<em><b>Receptor Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_CALL_EXP__RECEPTOR_TYPE = eINSTANCE.getPropertyCallExp_ReceptorType();

		/**
		 * The meta object literal for the '<em><b>Is Static Call</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_CALL_EXP__IS_STATIC_CALL = eINSTANCE.getPropertyCallExp_IsStaticCall();

		/**
		 * The meta object literal for the '<em><b>Static Resolver</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_CALL_EXP__STATIC_RESOLVER = eINSTANCE.getPropertyCallExp_StaticResolver();

		/**
		 * The meta object literal for the '<em><b>Dynamic Resolvers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS = eINSTANCE.getPropertyCallExp_DynamicResolvers();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.NavigationOrAttributeCallExpImpl <em>Navigation Or Attribute Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.NavigationOrAttributeCallExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getNavigationOrAttributeCallExp()
		 * @generated
		 */
		EClass NAVIGATION_OR_ATTRIBUTE_CALL_EXP = eINSTANCE.getNavigationOrAttributeCallExp();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_OR_ATTRIBUTE_CALL_EXP__NAME = eINSTANCE.getNavigationOrAttributeCallExp_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OperationCallExpImpl <em>Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OperationCallExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOperationCallExp()
		 * @generated
		 */
		EClass OPERATION_CALL_EXP = eINSTANCE.getOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_EXP__ARGUMENTS = eINSTANCE.getOperationCallExp_Arguments();

		/**
		 * The meta object literal for the '<em><b>Operation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_CALL_EXP__OPERATION_NAME = eINSTANCE.getOperationCallExp_OperationName();

		/**
		 * The meta object literal for the '<em><b>Resolve Temp Resolved By</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY = eINSTANCE.getOperationCallExp_ResolveTempResolvedBy();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OperatorCallExpImpl <em>Operator Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OperatorCallExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOperatorCallExp()
		 * @generated
		 */
		EClass OPERATOR_CALL_EXP = eINSTANCE.getOperatorCallExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.CollectionOperationCallExpImpl <em>Collection Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.CollectionOperationCallExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getCollectionOperationCallExp()
		 * @generated
		 */
		EClass COLLECTION_OPERATION_CALL_EXP = eINSTANCE.getCollectionOperationCallExp();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.LoopExpImpl <em>Loop Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.LoopExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getLoopExp()
		 * @generated
		 */
		EClass LOOP_EXP = eINSTANCE.getLoopExp();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_EXP__BODY = eINSTANCE.getLoopExp_Body();

		/**
		 * The meta object literal for the '<em><b>Iterators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_EXP__ITERATORS = eINSTANCE.getLoopExp_Iterators();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.IterateExpImpl <em>Iterate Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.IterateExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIterateExp()
		 * @generated
		 */
		EClass ITERATE_EXP = eINSTANCE.getIterateExp();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITERATE_EXP__RESULT = eINSTANCE.getIterateExp_Result();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.IteratorExpImpl <em>Iterator Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.IteratorExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIteratorExp()
		 * @generated
		 */
		EClass ITERATOR_EXP = eINSTANCE.getIteratorExp();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITERATOR_EXP__NAME = eINSTANCE.getIteratorExp_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.LetExpImpl <em>Let Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.LetExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getLetExp()
		 * @generated
		 */
		EClass LET_EXP = eINSTANCE.getLetExp();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LET_EXP__VARIABLE = eINSTANCE.getLetExp_Variable();

		/**
		 * The meta object literal for the '<em><b>In </b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LET_EXP__IN_ = eINSTANCE.getLetExp_In_();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.IfExpImpl <em>If Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.IfExpImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIfExp()
		 * @generated
		 */
		EClass IF_EXP = eINSTANCE.getIfExp();

		/**
		 * The meta object literal for the '<em><b>Then Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXP__THEN_EXPRESSION = eINSTANCE.getIfExp_ThenExpression();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXP__CONDITION = eINSTANCE.getIfExp_Condition();

		/**
		 * The meta object literal for the '<em><b>Else Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXP__ELSE_EXPRESSION = eINSTANCE.getIfExp_ElseExpression();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.VariableDeclarationImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getVariableDeclaration()
		 * @generated
		 */
		EClass VARIABLE_DECLARATION = eINSTANCE.getVariableDeclaration();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_DECLARATION__ID = eINSTANCE.getVariableDeclaration_Id();

		/**
		 * The meta object literal for the '<em><b>Var Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_DECLARATION__VAR_NAME = eINSTANCE.getVariableDeclaration_VarName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__TYPE = eINSTANCE.getVariableDeclaration_Type();

		/**
		 * The meta object literal for the '<em><b>Init Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__INIT_EXPRESSION = eINSTANCE.getVariableDeclaration_InitExpression();

		/**
		 * The meta object literal for the '<em><b>Let Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__LET_EXP = eINSTANCE.getVariableDeclaration_LetExp();

		/**
		 * The meta object literal for the '<em><b>Base Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__BASE_EXP = eINSTANCE.getVariableDeclaration_BaseExp();

		/**
		 * The meta object literal for the '<em><b>Variable Exp</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__VARIABLE_EXP = eINSTANCE.getVariableDeclaration_VariableExp();

		/**
		 * The meta object literal for the '<em><b>Static Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__STATIC_TYPE = eINSTANCE.getVariableDeclaration_StaticType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.IteratorImpl <em>Iterator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.IteratorImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIterator()
		 * @generated
		 */
		EClass ITERATOR = eINSTANCE.getIterator();

		/**
		 * The meta object literal for the '<em><b>Loop Expr</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITERATOR__LOOP_EXPR = eINSTANCE.getIterator_LoopExpr();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.ParameterImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.CollectionTypeImpl <em>Collection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.CollectionTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getCollectionType()
		 * @generated
		 */
		EClass COLLECTION_TYPE = eINSTANCE.getCollectionType();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_TYPE__ELEMENT_TYPE = eINSTANCE.getCollectionType_ElementType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclTypeImpl <em>Ocl Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclType()
		 * @generated
		 */
		EClass OCL_TYPE = eINSTANCE.getOclType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCL_TYPE__NAME = eINSTANCE.getOclType_Name();

		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__DEFINITIONS = eINSTANCE.getOclType_Definitions();

		/**
		 * The meta object literal for the '<em><b>Ocl Expression</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__OCL_EXPRESSION = eINSTANCE.getOclType_OclExpression();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__OPERATION = eINSTANCE.getOclType_Operation();

		/**
		 * The meta object literal for the '<em><b>Map Type2</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__MAP_TYPE2 = eINSTANCE.getOclType_MapType2();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__ATTRIBUTE = eINSTANCE.getOclType_Attribute();

		/**
		 * The meta object literal for the '<em><b>Map Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__MAP_TYPE = eINSTANCE.getOclType_MapType();

		/**
		 * The meta object literal for the '<em><b>Collection Types</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__COLLECTION_TYPES = eINSTANCE.getOclType_CollectionTypes();

		/**
		 * The meta object literal for the '<em><b>Tuple Type Attribute</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__TUPLE_TYPE_ATTRIBUTE = eINSTANCE.getOclType_TupleTypeAttribute();

		/**
		 * The meta object literal for the '<em><b>Variable Declaration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_TYPE__VARIABLE_DECLARATION = eINSTANCE.getOclType_VariableDeclaration();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.PrimitiveImpl <em>Primitive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.PrimitiveImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getPrimitive()
		 * @generated
		 */
		EClass PRIMITIVE = eINSTANCE.getPrimitive();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.StringTypeImpl <em>String Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.StringTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getStringType()
		 * @generated
		 */
		EClass STRING_TYPE = eINSTANCE.getStringType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.BooleanTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBooleanType()
		 * @generated
		 */
		EClass BOOLEAN_TYPE = eINSTANCE.getBooleanType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.NumericTypeImpl <em>Numeric Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.NumericTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getNumericType()
		 * @generated
		 */
		EClass NUMERIC_TYPE = eINSTANCE.getNumericType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.IntegerTypeImpl <em>Integer Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.IntegerTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getIntegerType()
		 * @generated
		 */
		EClass INTEGER_TYPE = eINSTANCE.getIntegerType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.RealTypeImpl <em>Real Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.RealTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getRealType()
		 * @generated
		 */
		EClass REAL_TYPE = eINSTANCE.getRealType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.BagTypeImpl <em>Bag Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.BagTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getBagType()
		 * @generated
		 */
		EClass BAG_TYPE = eINSTANCE.getBagType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OrderedSetTypeImpl <em>Ordered Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OrderedSetTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOrderedSetType()
		 * @generated
		 */
		EClass ORDERED_SET_TYPE = eINSTANCE.getOrderedSetType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.SequenceTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSequenceType()
		 * @generated
		 */
		EClass SEQUENCE_TYPE = eINSTANCE.getSequenceType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.SetTypeImpl <em>Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.SetTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getSetType()
		 * @generated
		 */
		EClass SET_TYPE = eINSTANCE.getSetType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclAnyTypeImpl <em>Ocl Any Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclAnyTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclAnyType()
		 * @generated
		 */
		EClass OCL_ANY_TYPE = eINSTANCE.getOclAnyType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.TupleTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTupleType()
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
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.TupleTypeAttributeImpl <em>Tuple Type Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.TupleTypeAttributeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTupleTypeAttribute()
		 * @generated
		 */
		EClass TUPLE_TYPE_ATTRIBUTE = eINSTANCE.getTupleTypeAttribute();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_TYPE_ATTRIBUTE__TYPE = eINSTANCE.getTupleTypeAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Tuple Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE = eINSTANCE.getTupleTypeAttribute_TupleType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUPLE_TYPE_ATTRIBUTE__NAME = eINSTANCE.getTupleTypeAttribute_Name();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclModelElementImpl <em>Ocl Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclModelElementImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclModelElement()
		 * @generated
		 */
		EClass OCL_MODEL_ELEMENT = eINSTANCE.getOclModelElement();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_MODEL_ELEMENT__MODEL = eINSTANCE.getOclModelElement_Model();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.MapTypeImpl <em>Map Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.MapTypeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getMapType()
		 * @generated
		 */
		EClass MAP_TYPE = eINSTANCE.getMapType();

		/**
		 * The meta object literal for the '<em><b>Value Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_TYPE__VALUE_TYPE = eINSTANCE.getMapType_ValueType();

		/**
		 * The meta object literal for the '<em><b>Key Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_TYPE__KEY_TYPE = eINSTANCE.getMapType_KeyType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclFeatureDefinitionImpl <em>Ocl Feature Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclFeatureDefinitionImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclFeatureDefinition()
		 * @generated
		 */
		EClass OCL_FEATURE_DEFINITION = eINSTANCE.getOclFeatureDefinition();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_FEATURE_DEFINITION__FEATURE = eINSTANCE.getOclFeatureDefinition_Feature();

		/**
		 * The meta object literal for the '<em><b>Context </b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_FEATURE_DEFINITION__CONTEXT_ = eINSTANCE.getOclFeatureDefinition_Context_();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclContextDefinitionImpl <em>Ocl Context Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclContextDefinitionImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclContextDefinition()
		 * @generated
		 */
		EClass OCL_CONTEXT_DEFINITION = eINSTANCE.getOclContextDefinition();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_CONTEXT_DEFINITION__DEFINITION = eINSTANCE.getOclContextDefinition_Definition();

		/**
		 * The meta object literal for the '<em><b>Context </b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_CONTEXT_DEFINITION__CONTEXT_ = eINSTANCE.getOclContextDefinition_Context_();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclFeatureImpl <em>Ocl Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclFeatureImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclFeature()
		 * @generated
		 */
		EClass OCL_FEATURE = eINSTANCE.getOclFeature();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_FEATURE__DEFINITION = eINSTANCE.getOclFeature_Definition();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.AttributeImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Init Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE__INIT_EXPRESSION = eINSTANCE.getAttribute_InitExpression();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE__TYPE = eINSTANCE.getAttribute_Type();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OperationImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__NAME = eINSTANCE.getOperation_Name();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__RETURN_TYPE = eINSTANCE.getOperation_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__BODY = eINSTANCE.getOperation_Body();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.OclModelImpl <em>Ocl Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.OclModelImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getOclModel()
		 * @generated
		 */
		EClass OCL_MODEL = eINSTANCE.getOclModel();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCL_MODEL__NAME = eINSTANCE.getOclModel_Name();

		/**
		 * The meta object literal for the '<em><b>Metamodel</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_MODEL__METAMODEL = eINSTANCE.getOclModel_Metamodel();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_MODEL__ELEMENTS = eINSTANCE.getOclModel_Elements();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_MODEL__MODEL = eINSTANCE.getOclModel_Model();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.TypedElementImpl <em>Typed Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.TypedElementImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getTypedElement()
		 * @generated
		 */
		EClass TYPED_ELEMENT = eINSTANCE.getTypedElement();

		/**
		 * The meta object literal for the '<em><b>Inferred Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPED_ELEMENT__INFERRED_TYPE = eINSTANCE.getTypedElement_InferredType();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.ResolveTempResolutionImpl <em>Resolve Temp Resolution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.ResolveTempResolutionImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getResolveTempResolution()
		 * @generated
		 */
		EClass RESOLVE_TEMP_RESOLUTION = eINSTANCE.getResolveTempResolution();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_RESOLUTION__RULE = eINSTANCE.getResolveTempResolution_Rule();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVE_TEMP_RESOLUTION__ELEMENT = eINSTANCE.getResolveTempResolution_Element();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.JavaBodyImpl <em>Java Body</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.JavaBodyImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getJavaBody()
		 * @generated
		 */
		EClass JAVA_BODY = eINSTANCE.getJavaBody();

		/**
		 * The meta object literal for the '{@link anatlyzer.atlext.OCL.impl.GetAppliedStereotypesBodyImpl <em>Get Applied Stereotypes Body</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.atlext.OCL.impl.GetAppliedStereotypesBodyImpl
		 * @see anatlyzer.atlext.OCL.impl.OCLPackageImpl#getGetAppliedStereotypesBody()
		 * @generated
		 */
		EClass GET_APPLIED_STEREOTYPES_BODY = eINSTANCE.getGetAppliedStereotypesBody();

	}

} //OCLPackage
