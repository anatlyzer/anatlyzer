/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atl.types.TypesPackage;

import anatlyzer.atlext.ATL.ATLPackage;

import anatlyzer.atlext.ATL.impl.ATLPackageImpl;

import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BagExp;
import anatlyzer.atlext.OCL.BagType;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.BooleanType;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.CollectionType;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.GetAppliedStereotypesBody;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IntegerType;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.JavaBody;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.MapElement;
import anatlyzer.atlext.OCL.MapExp;
import anatlyzer.atlext.OCL.MapType;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.NumericExp;
import anatlyzer.atlext.OCL.NumericType;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclAnyType;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.OrderedSetExp;
import anatlyzer.atlext.OCL.OrderedSetType;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.Primitive;
import anatlyzer.atlext.OCL.PrimitiveExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.RealType;
import anatlyzer.atlext.OCL.ResolveTempResolution;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SequenceType;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.SetType;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.StringType;
import anatlyzer.atlext.OCL.SuperExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.TupleType;
import anatlyzer.atlext.OCL.TupleTypeAttribute;
import anatlyzer.atlext.OCL.TypedElement;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

import anatlyzer.atlext.PrimitiveTypes.PrimitiveTypesPackage;

import anatlyzer.atlext.PrimitiveTypes.impl.PrimitiveTypesPackageImpl;

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
public class OCLPackageImpl extends EPackageImpl implements OCLPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass superExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass realExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bagExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orderedSetExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tuplePartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclUndefinedExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationOrAttributeCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operatorCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionOperationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loopExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iterateExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iteratorExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass letExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ifExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iteratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

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
	private EClass oclTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveEClass = null;

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
	private EClass booleanTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericTypeEClass = null;

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
	private EClass realTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bagTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orderedSetTypeEClass = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclAnyTypeEClass = null;

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
	private EClass tupleTypeAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclModelElementEClass = null;

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
	private EClass oclFeatureDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclContextDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclFeatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resolveTempResolutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaBodyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getAppliedStereotypesBodyEClass = null;

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
	 * @see anatlyzer.atlext.OCL.OCLPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OCLPackageImpl() {
		super(eNS_URI, OCLFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OCLPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OCLPackage init() {
		if (isInited) return (OCLPackage)EPackage.Registry.INSTANCE.getEPackage(OCLPackage.eNS_URI);

		// Obtain or create and register package
		OCLPackageImpl theOCLPackage = (OCLPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OCLPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OCLPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ATLPackageImpl theATLPackage = (ATLPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ATLPackage.eNS_URI) instanceof ATLPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ATLPackage.eNS_URI) : ATLPackage.eINSTANCE);
		PrimitiveTypesPackageImpl thePrimitiveTypesPackage = (PrimitiveTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI) instanceof PrimitiveTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI) : PrimitiveTypesPackage.eINSTANCE);

		// Create package meta-data objects
		theOCLPackage.createPackageContents();
		theATLPackage.createPackageContents();
		thePrimitiveTypesPackage.createPackageContents();

		// Initialize created meta-data
		theOCLPackage.initializePackageContents();
		theATLPackage.initializePackageContents();
		thePrimitiveTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOCLPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OCLPackage.eNS_URI, theOCLPackage);
		return theOCLPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclExpression() {
		return oclExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_Type() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_IfExp3() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_AppliedProperty() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_Collection() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_LetExp() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_LoopExp() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_ParentOperation() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_InitializedVariable() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_IfExp2() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_OwningOperation() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_IfExp1() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclExpression_OwningAttribute() {
		return (EReference)oclExpressionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOclExpression_ImplicitlyCasted() {
		return (EAttribute)oclExpressionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableExp() {
		return variableExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableExp_ReferredVariable() {
		return (EReference)variableExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperExp() {
		return superExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveExp() {
		return primitiveExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringExp() {
		return stringExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringExp_StringSymbol() {
		return (EAttribute)stringExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanExp() {
		return booleanExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanExp_BooleanSymbol() {
		return (EAttribute)booleanExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumericExp() {
		return numericExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRealExp() {
		return realExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRealExp_RealSymbol() {
		return (EAttribute)realExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerExp() {
		return integerExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerExp_IntegerSymbol() {
		return (EAttribute)integerExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCollectionExp() {
		return collectionExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCollectionExp_Elements() {
		return (EReference)collectionExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBagExp() {
		return bagExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrderedSetExp() {
		return orderedSetExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequenceExp() {
		return sequenceExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSetExp() {
		return setExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTupleExp() {
		return tupleExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTupleExp_TuplePart() {
		return (EReference)tupleExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTuplePart() {
		return tuplePartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTuplePart_Tuple() {
		return (EReference)tuplePartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMapExp() {
		return mapExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapExp_Elements() {
		return (EReference)mapExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMapElement() {
		return mapElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapElement_Map() {
		return (EReference)mapElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapElement_Key() {
		return (EReference)mapElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapElement_Value() {
		return (EReference)mapElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumLiteralExp() {
		return enumLiteralExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumLiteralExp_Name() {
		return (EAttribute)enumLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclUndefinedExp() {
		return oclUndefinedExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyCallExp() {
		return propertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyCallExp_Source() {
		return (EReference)propertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyCallExp_UsedFeature() {
		return (EReference)propertyCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyCallExp_ReceptorType() {
		return (EReference)propertyCallExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyCallExp_IsStaticCall() {
		return (EAttribute)propertyCallExpEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyCallExp_StaticResolver() {
		return (EReference)propertyCallExpEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyCallExp_DynamicResolvers() {
		return (EReference)propertyCallExpEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigationOrAttributeCallExp() {
		return navigationOrAttributeCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNavigationOrAttributeCallExp_Name() {
		return (EAttribute)navigationOrAttributeCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationCallExp() {
		return operationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationCallExp_Arguments() {
		return (EReference)operationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationCallExp_OperationName() {
		return (EAttribute)operationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationCallExp_ResolveTempResolvedBy() {
		return (EReference)operationCallExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperatorCallExp() {
		return operatorCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCollectionOperationCallExp() {
		return collectionOperationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLoopExp() {
		return loopExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLoopExp_Body() {
		return (EReference)loopExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLoopExp_Iterators() {
		return (EReference)loopExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIterateExp() {
		return iterateExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIterateExp_Result() {
		return (EReference)iterateExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIteratorExp() {
		return iteratorExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIteratorExp_Name() {
		return (EAttribute)iteratorExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLetExp() {
		return letExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLetExp_Variable() {
		return (EReference)letExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLetExp_In_() {
		return (EReference)letExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIfExp() {
		return ifExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfExp_ThenExpression() {
		return (EReference)ifExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfExp_Condition() {
		return (EReference)ifExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfExp_ElseExpression() {
		return (EReference)ifExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclaration() {
		return variableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableDeclaration_Id() {
		return (EAttribute)variableDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableDeclaration_VarName() {
		return (EAttribute)variableDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_Type() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_InitExpression() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_LetExp() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_BaseExp() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_VariableExp() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_StaticType() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIterator() {
		return iteratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIterator_LoopExpr() {
		return (EReference)iteratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
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
	public EReference getCollectionType_ElementType() {
		return (EReference)collectionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclType() {
		return oclTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOclType_Name() {
		return (EAttribute)oclTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_Definitions() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_OclExpression() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_Operation() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_MapType2() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_Attribute() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_MapType() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_CollectionTypes() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_TupleTypeAttribute() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclType_VariableDeclaration() {
		return (EReference)oclTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitive() {
		return primitiveEClass;
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
	public EClass getBooleanType() {
		return booleanTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumericType() {
		return numericTypeEClass;
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
	public EClass getRealType() {
		return realTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBagType() {
		return bagTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrderedSetType() {
		return orderedSetTypeEClass;
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
	public EClass getOclAnyType() {
		return oclAnyTypeEClass;
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
	public EClass getTupleTypeAttribute() {
		return tupleTypeAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTupleTypeAttribute_Type() {
		return (EReference)tupleTypeAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTupleTypeAttribute_TupleType() {
		return (EReference)tupleTypeAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTupleTypeAttribute_Name() {
		return (EAttribute)tupleTypeAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclModelElement() {
		return oclModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclModelElement_Model() {
		return (EReference)oclModelElementEClass.getEStructuralFeatures().get(0);
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
	public EReference getMapType_ValueType() {
		return (EReference)mapTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapType_KeyType() {
		return (EReference)mapTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclFeatureDefinition() {
		return oclFeatureDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclFeatureDefinition_Feature() {
		return (EReference)oclFeatureDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclFeatureDefinition_Context_() {
		return (EReference)oclFeatureDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclContextDefinition() {
		return oclContextDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclContextDefinition_Definition() {
		return (EReference)oclContextDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclContextDefinition_Context_() {
		return (EReference)oclContextDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclFeature() {
		return oclFeatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclFeature_Definition() {
		return (EReference)oclFeatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Name() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttribute_InitExpression() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttribute_Type() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Name() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_Parameters() {
		return (EReference)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_ReturnType() {
		return (EReference)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_Body() {
		return (EReference)operationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclModel() {
		return oclModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOclModel_Name() {
		return (EAttribute)oclModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclModel_Metamodel() {
		return (EReference)oclModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclModel_Elements() {
		return (EReference)oclModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclModel_Model() {
		return (EReference)oclModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypedElement() {
		return typedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypedElement_InferredType() {
		return (EReference)typedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResolveTempResolution() {
		return resolveTempResolutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResolveTempResolution_Rule() {
		return (EReference)resolveTempResolutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResolveTempResolution_Element() {
		return (EReference)resolveTempResolutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavaBody() {
		return javaBodyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGetAppliedStereotypesBody() {
		return getAppliedStereotypesBodyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLFactory getOCLFactory() {
		return (OCLFactory)getEFactoryInstance();
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
		oclExpressionEClass = createEClass(OCL_EXPRESSION);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__TYPE);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__IF_EXP3);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__APPLIED_PROPERTY);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__COLLECTION);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__LET_EXP);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__LOOP_EXP);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__PARENT_OPERATION);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__INITIALIZED_VARIABLE);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__IF_EXP2);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__OWNING_OPERATION);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__IF_EXP1);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__OWNING_ATTRIBUTE);
		createEAttribute(oclExpressionEClass, OCL_EXPRESSION__IMPLICITLY_CASTED);

		variableExpEClass = createEClass(VARIABLE_EXP);
		createEReference(variableExpEClass, VARIABLE_EXP__REFERRED_VARIABLE);

		superExpEClass = createEClass(SUPER_EXP);

		primitiveExpEClass = createEClass(PRIMITIVE_EXP);

		stringExpEClass = createEClass(STRING_EXP);
		createEAttribute(stringExpEClass, STRING_EXP__STRING_SYMBOL);

		booleanExpEClass = createEClass(BOOLEAN_EXP);
		createEAttribute(booleanExpEClass, BOOLEAN_EXP__BOOLEAN_SYMBOL);

		numericExpEClass = createEClass(NUMERIC_EXP);

		realExpEClass = createEClass(REAL_EXP);
		createEAttribute(realExpEClass, REAL_EXP__REAL_SYMBOL);

		integerExpEClass = createEClass(INTEGER_EXP);
		createEAttribute(integerExpEClass, INTEGER_EXP__INTEGER_SYMBOL);

		collectionExpEClass = createEClass(COLLECTION_EXP);
		createEReference(collectionExpEClass, COLLECTION_EXP__ELEMENTS);

		bagExpEClass = createEClass(BAG_EXP);

		orderedSetExpEClass = createEClass(ORDERED_SET_EXP);

		sequenceExpEClass = createEClass(SEQUENCE_EXP);

		setExpEClass = createEClass(SET_EXP);

		tupleExpEClass = createEClass(TUPLE_EXP);
		createEReference(tupleExpEClass, TUPLE_EXP__TUPLE_PART);

		tuplePartEClass = createEClass(TUPLE_PART);
		createEReference(tuplePartEClass, TUPLE_PART__TUPLE);

		mapExpEClass = createEClass(MAP_EXP);
		createEReference(mapExpEClass, MAP_EXP__ELEMENTS);

		mapElementEClass = createEClass(MAP_ELEMENT);
		createEReference(mapElementEClass, MAP_ELEMENT__MAP);
		createEReference(mapElementEClass, MAP_ELEMENT__KEY);
		createEReference(mapElementEClass, MAP_ELEMENT__VALUE);

		enumLiteralExpEClass = createEClass(ENUM_LITERAL_EXP);
		createEAttribute(enumLiteralExpEClass, ENUM_LITERAL_EXP__NAME);

		oclUndefinedExpEClass = createEClass(OCL_UNDEFINED_EXP);

		propertyCallExpEClass = createEClass(PROPERTY_CALL_EXP);
		createEReference(propertyCallExpEClass, PROPERTY_CALL_EXP__SOURCE);
		createEReference(propertyCallExpEClass, PROPERTY_CALL_EXP__USED_FEATURE);
		createEReference(propertyCallExpEClass, PROPERTY_CALL_EXP__RECEPTOR_TYPE);
		createEAttribute(propertyCallExpEClass, PROPERTY_CALL_EXP__IS_STATIC_CALL);
		createEReference(propertyCallExpEClass, PROPERTY_CALL_EXP__STATIC_RESOLVER);
		createEReference(propertyCallExpEClass, PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS);

		navigationOrAttributeCallExpEClass = createEClass(NAVIGATION_OR_ATTRIBUTE_CALL_EXP);
		createEAttribute(navigationOrAttributeCallExpEClass, NAVIGATION_OR_ATTRIBUTE_CALL_EXP__NAME);

		operationCallExpEClass = createEClass(OPERATION_CALL_EXP);
		createEReference(operationCallExpEClass, OPERATION_CALL_EXP__ARGUMENTS);
		createEAttribute(operationCallExpEClass, OPERATION_CALL_EXP__OPERATION_NAME);
		createEReference(operationCallExpEClass, OPERATION_CALL_EXP__RESOLVE_TEMP_RESOLVED_BY);

		operatorCallExpEClass = createEClass(OPERATOR_CALL_EXP);

		collectionOperationCallExpEClass = createEClass(COLLECTION_OPERATION_CALL_EXP);

		loopExpEClass = createEClass(LOOP_EXP);
		createEReference(loopExpEClass, LOOP_EXP__BODY);
		createEReference(loopExpEClass, LOOP_EXP__ITERATORS);

		iterateExpEClass = createEClass(ITERATE_EXP);
		createEReference(iterateExpEClass, ITERATE_EXP__RESULT);

		iteratorExpEClass = createEClass(ITERATOR_EXP);
		createEAttribute(iteratorExpEClass, ITERATOR_EXP__NAME);

		letExpEClass = createEClass(LET_EXP);
		createEReference(letExpEClass, LET_EXP__VARIABLE);
		createEReference(letExpEClass, LET_EXP__IN_);

		ifExpEClass = createEClass(IF_EXP);
		createEReference(ifExpEClass, IF_EXP__THEN_EXPRESSION);
		createEReference(ifExpEClass, IF_EXP__CONDITION);
		createEReference(ifExpEClass, IF_EXP__ELSE_EXPRESSION);

		variableDeclarationEClass = createEClass(VARIABLE_DECLARATION);
		createEAttribute(variableDeclarationEClass, VARIABLE_DECLARATION__ID);
		createEAttribute(variableDeclarationEClass, VARIABLE_DECLARATION__VAR_NAME);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__TYPE);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__INIT_EXPRESSION);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__LET_EXP);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__BASE_EXP);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__VARIABLE_EXP);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__STATIC_TYPE);

		iteratorEClass = createEClass(ITERATOR);
		createEReference(iteratorEClass, ITERATOR__LOOP_EXPR);

		parameterEClass = createEClass(PARAMETER);

		collectionTypeEClass = createEClass(COLLECTION_TYPE);
		createEReference(collectionTypeEClass, COLLECTION_TYPE__ELEMENT_TYPE);

		oclTypeEClass = createEClass(OCL_TYPE);
		createEAttribute(oclTypeEClass, OCL_TYPE__NAME);
		createEReference(oclTypeEClass, OCL_TYPE__DEFINITIONS);
		createEReference(oclTypeEClass, OCL_TYPE__OCL_EXPRESSION);
		createEReference(oclTypeEClass, OCL_TYPE__OPERATION);
		createEReference(oclTypeEClass, OCL_TYPE__MAP_TYPE2);
		createEReference(oclTypeEClass, OCL_TYPE__ATTRIBUTE);
		createEReference(oclTypeEClass, OCL_TYPE__MAP_TYPE);
		createEReference(oclTypeEClass, OCL_TYPE__COLLECTION_TYPES);
		createEReference(oclTypeEClass, OCL_TYPE__TUPLE_TYPE_ATTRIBUTE);
		createEReference(oclTypeEClass, OCL_TYPE__VARIABLE_DECLARATION);

		primitiveEClass = createEClass(PRIMITIVE);

		stringTypeEClass = createEClass(STRING_TYPE);

		booleanTypeEClass = createEClass(BOOLEAN_TYPE);

		numericTypeEClass = createEClass(NUMERIC_TYPE);

		integerTypeEClass = createEClass(INTEGER_TYPE);

		realTypeEClass = createEClass(REAL_TYPE);

		bagTypeEClass = createEClass(BAG_TYPE);

		orderedSetTypeEClass = createEClass(ORDERED_SET_TYPE);

		sequenceTypeEClass = createEClass(SEQUENCE_TYPE);

		setTypeEClass = createEClass(SET_TYPE);

		oclAnyTypeEClass = createEClass(OCL_ANY_TYPE);

		tupleTypeEClass = createEClass(TUPLE_TYPE);
		createEReference(tupleTypeEClass, TUPLE_TYPE__ATTRIBUTES);

		tupleTypeAttributeEClass = createEClass(TUPLE_TYPE_ATTRIBUTE);
		createEReference(tupleTypeAttributeEClass, TUPLE_TYPE_ATTRIBUTE__TYPE);
		createEReference(tupleTypeAttributeEClass, TUPLE_TYPE_ATTRIBUTE__TUPLE_TYPE);
		createEAttribute(tupleTypeAttributeEClass, TUPLE_TYPE_ATTRIBUTE__NAME);

		oclModelElementEClass = createEClass(OCL_MODEL_ELEMENT);
		createEReference(oclModelElementEClass, OCL_MODEL_ELEMENT__MODEL);

		mapTypeEClass = createEClass(MAP_TYPE);
		createEReference(mapTypeEClass, MAP_TYPE__VALUE_TYPE);
		createEReference(mapTypeEClass, MAP_TYPE__KEY_TYPE);

		oclFeatureDefinitionEClass = createEClass(OCL_FEATURE_DEFINITION);
		createEReference(oclFeatureDefinitionEClass, OCL_FEATURE_DEFINITION__FEATURE);
		createEReference(oclFeatureDefinitionEClass, OCL_FEATURE_DEFINITION__CONTEXT_);

		oclContextDefinitionEClass = createEClass(OCL_CONTEXT_DEFINITION);
		createEReference(oclContextDefinitionEClass, OCL_CONTEXT_DEFINITION__DEFINITION);
		createEReference(oclContextDefinitionEClass, OCL_CONTEXT_DEFINITION__CONTEXT_);

		oclFeatureEClass = createEClass(OCL_FEATURE);
		createEReference(oclFeatureEClass, OCL_FEATURE__DEFINITION);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__NAME);
		createEReference(attributeEClass, ATTRIBUTE__INIT_EXPRESSION);
		createEReference(attributeEClass, ATTRIBUTE__TYPE);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__NAME);
		createEReference(operationEClass, OPERATION__PARAMETERS);
		createEReference(operationEClass, OPERATION__RETURN_TYPE);
		createEReference(operationEClass, OPERATION__BODY);

		oclModelEClass = createEClass(OCL_MODEL);
		createEAttribute(oclModelEClass, OCL_MODEL__NAME);
		createEReference(oclModelEClass, OCL_MODEL__METAMODEL);
		createEReference(oclModelEClass, OCL_MODEL__ELEMENTS);
		createEReference(oclModelEClass, OCL_MODEL__MODEL);

		typedElementEClass = createEClass(TYPED_ELEMENT);
		createEReference(typedElementEClass, TYPED_ELEMENT__INFERRED_TYPE);

		resolveTempResolutionEClass = createEClass(RESOLVE_TEMP_RESOLUTION);
		createEReference(resolveTempResolutionEClass, RESOLVE_TEMP_RESOLUTION__RULE);
		createEReference(resolveTempResolutionEClass, RESOLVE_TEMP_RESOLUTION__ELEMENT);

		javaBodyEClass = createEClass(JAVA_BODY);

		getAppliedStereotypesBodyEClass = createEClass(GET_APPLIED_STEREOTYPES_BODY);
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
		ATLPackage theATLPackage = (ATLPackage)EPackage.Registry.INSTANCE.getEPackage(ATLPackage.eNS_URI);
		PrimitiveTypesPackage thePrimitiveTypesPackage = (PrimitiveTypesPackage)EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI);
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		oclExpressionEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		oclExpressionEClass.getESuperTypes().add(this.getTypedElement());
		variableExpEClass.getESuperTypes().add(this.getOclExpression());
		superExpEClass.getESuperTypes().add(this.getOclExpression());
		primitiveExpEClass.getESuperTypes().add(this.getOclExpression());
		stringExpEClass.getESuperTypes().add(this.getPrimitiveExp());
		booleanExpEClass.getESuperTypes().add(this.getPrimitiveExp());
		numericExpEClass.getESuperTypes().add(this.getPrimitiveExp());
		realExpEClass.getESuperTypes().add(this.getNumericExp());
		integerExpEClass.getESuperTypes().add(this.getNumericExp());
		collectionExpEClass.getESuperTypes().add(this.getOclExpression());
		bagExpEClass.getESuperTypes().add(this.getCollectionExp());
		orderedSetExpEClass.getESuperTypes().add(this.getCollectionExp());
		sequenceExpEClass.getESuperTypes().add(this.getCollectionExp());
		setExpEClass.getESuperTypes().add(this.getCollectionExp());
		tupleExpEClass.getESuperTypes().add(this.getOclExpression());
		tuplePartEClass.getESuperTypes().add(this.getVariableDeclaration());
		mapExpEClass.getESuperTypes().add(this.getOclExpression());
		mapElementEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		enumLiteralExpEClass.getESuperTypes().add(this.getOclExpression());
		oclUndefinedExpEClass.getESuperTypes().add(this.getOclExpression());
		propertyCallExpEClass.getESuperTypes().add(this.getOclExpression());
		navigationOrAttributeCallExpEClass.getESuperTypes().add(this.getPropertyCallExp());
		operationCallExpEClass.getESuperTypes().add(this.getPropertyCallExp());
		operatorCallExpEClass.getESuperTypes().add(this.getOperationCallExp());
		collectionOperationCallExpEClass.getESuperTypes().add(this.getOperationCallExp());
		loopExpEClass.getESuperTypes().add(this.getPropertyCallExp());
		iterateExpEClass.getESuperTypes().add(this.getLoopExp());
		iteratorExpEClass.getESuperTypes().add(this.getLoopExp());
		letExpEClass.getESuperTypes().add(this.getOclExpression());
		ifExpEClass.getESuperTypes().add(this.getOclExpression());
		variableDeclarationEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		variableDeclarationEClass.getESuperTypes().add(this.getTypedElement());
		iteratorEClass.getESuperTypes().add(this.getVariableDeclaration());
		parameterEClass.getESuperTypes().add(this.getVariableDeclaration());
		collectionTypeEClass.getESuperTypes().add(this.getOclType());
		oclTypeEClass.getESuperTypes().add(this.getOclExpression());
		primitiveEClass.getESuperTypes().add(this.getOclType());
		stringTypeEClass.getESuperTypes().add(this.getPrimitive());
		booleanTypeEClass.getESuperTypes().add(this.getPrimitive());
		numericTypeEClass.getESuperTypes().add(this.getPrimitive());
		integerTypeEClass.getESuperTypes().add(this.getNumericType());
		realTypeEClass.getESuperTypes().add(this.getNumericType());
		bagTypeEClass.getESuperTypes().add(this.getCollectionType());
		orderedSetTypeEClass.getESuperTypes().add(this.getCollectionType());
		sequenceTypeEClass.getESuperTypes().add(this.getCollectionType());
		setTypeEClass.getESuperTypes().add(this.getCollectionType());
		oclAnyTypeEClass.getESuperTypes().add(this.getOclType());
		tupleTypeEClass.getESuperTypes().add(this.getOclType());
		tupleTypeAttributeEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		oclModelElementEClass.getESuperTypes().add(this.getOclType());
		mapTypeEClass.getESuperTypes().add(this.getOclType());
		oclFeatureDefinitionEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		oclContextDefinitionEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		oclFeatureEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		attributeEClass.getESuperTypes().add(this.getOclFeature());
		operationEClass.getESuperTypes().add(this.getOclFeature());
		oclModelEClass.getESuperTypes().add(theATLPackage.getLocatedElement());
		javaBodyEClass.getESuperTypes().add(this.getOclExpression());
		getAppliedStereotypesBodyEClass.getESuperTypes().add(this.getJavaBody());

		// Initialize classes, features, and operations; add parameters
		initEClass(oclExpressionEClass, OclExpression.class, "OclExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOclExpression_Type(), this.getOclType(), this.getOclType_OclExpression(), "type", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_IfExp3(), this.getIfExp(), this.getIfExp_ElseExpression(), "ifExp3", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_AppliedProperty(), this.getPropertyCallExp(), this.getPropertyCallExp_Source(), "appliedProperty", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_Collection(), this.getCollectionExp(), this.getCollectionExp_Elements(), "collection", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_LetExp(), this.getLetExp(), this.getLetExp_In_(), "letExp", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_LoopExp(), this.getLoopExp(), this.getLoopExp_Body(), "loopExp", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_ParentOperation(), this.getOperationCallExp(), this.getOperationCallExp_Arguments(), "parentOperation", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_InitializedVariable(), this.getVariableDeclaration(), this.getVariableDeclaration_InitExpression(), "initializedVariable", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_IfExp2(), this.getIfExp(), this.getIfExp_ThenExpression(), "ifExp2", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_OwningOperation(), this.getOperation(), this.getOperation_Body(), "owningOperation", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_IfExp1(), this.getIfExp(), this.getIfExp_Condition(), "ifExp1", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclExpression_OwningAttribute(), this.getAttribute(), this.getAttribute_InitExpression(), "owningAttribute", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getOclExpression_ImplicitlyCasted(), ecorePackage.getEBoolean(), "implicitlyCasted", "false", 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableExpEClass, VariableExp.class, "VariableExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariableExp_ReferredVariable(), this.getVariableDeclaration(), this.getVariableDeclaration_VariableExp(), "referredVariable", null, 1, 1, VariableExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(superExpEClass, SuperExp.class, "SuperExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveExpEClass, PrimitiveExp.class, "PrimitiveExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringExpEClass, StringExp.class, "StringExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringExp_StringSymbol(), thePrimitiveTypesPackage.getString(), "stringSymbol", null, 1, 1, StringExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(booleanExpEClass, BooleanExp.class, "BooleanExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanExp_BooleanSymbol(), thePrimitiveTypesPackage.getBoolean(), "booleanSymbol", null, 1, 1, BooleanExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(numericExpEClass, NumericExp.class, "NumericExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(realExpEClass, RealExp.class, "RealExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRealExp_RealSymbol(), thePrimitiveTypesPackage.getDouble(), "realSymbol", null, 1, 1, RealExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(integerExpEClass, IntegerExp.class, "IntegerExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerExp_IntegerSymbol(), thePrimitiveTypesPackage.getInteger(), "integerSymbol", null, 1, 1, IntegerExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(collectionExpEClass, CollectionExp.class, "CollectionExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCollectionExp_Elements(), this.getOclExpression(), this.getOclExpression_Collection(), "elements", null, 0, -1, CollectionExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bagExpEClass, BagExp.class, "BagExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(orderedSetExpEClass, OrderedSetExp.class, "OrderedSetExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sequenceExpEClass, SequenceExp.class, "SequenceExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(setExpEClass, SetExp.class, "SetExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tupleExpEClass, TupleExp.class, "TupleExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTupleExp_TuplePart(), this.getTuplePart(), this.getTuplePart_Tuple(), "tuplePart", null, 0, -1, TupleExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tuplePartEClass, TuplePart.class, "TuplePart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTuplePart_Tuple(), this.getTupleExp(), this.getTupleExp_TuplePart(), "tuple", null, 1, 1, TuplePart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(mapExpEClass, MapExp.class, "MapExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMapExp_Elements(), this.getMapElement(), this.getMapElement_Map(), "elements", null, 0, -1, MapExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mapElementEClass, MapElement.class, "MapElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMapElement_Map(), this.getMapExp(), this.getMapExp_Elements(), "map", null, 1, 1, MapElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMapElement_Key(), this.getOclExpression(), null, "key", null, 1, 1, MapElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMapElement_Value(), this.getOclExpression(), null, "value", null, 1, 1, MapElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(enumLiteralExpEClass, EnumLiteralExp.class, "EnumLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumLiteralExp_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, EnumLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(oclUndefinedExpEClass, OclUndefinedExp.class, "OclUndefinedExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyCallExpEClass, PropertyCallExp.class, "PropertyCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyCallExp_Source(), this.getOclExpression(), this.getOclExpression_AppliedProperty(), "source", null, 1, 1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPropertyCallExp_UsedFeature(), ecorePackage.getEObject(), null, "usedFeature", null, 0, 1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyCallExp_ReceptorType(), ecorePackage.getEObject(), null, "receptorType", null, 0, 1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyCallExp_IsStaticCall(), ecorePackage.getEBoolean(), "isStaticCall", "false", 1, 1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyCallExp_StaticResolver(), theATLPackage.getCallable(), theATLPackage.getCallable_CalledBy(), "staticResolver", null, 1, 1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyCallExp_DynamicResolvers(), theATLPackage.getContextHelper(), theATLPackage.getContextHelper_PolymorphicCalledBy(), "dynamicResolvers", null, 0, -1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigationOrAttributeCallExpEClass, NavigationOrAttributeCallExp.class, "NavigationOrAttributeCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNavigationOrAttributeCallExp_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, NavigationOrAttributeCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(operationCallExpEClass, OperationCallExp.class, "OperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationCallExp_Arguments(), this.getOclExpression(), this.getOclExpression_ParentOperation(), "arguments", null, 0, -1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperationCallExp_OperationName(), thePrimitiveTypesPackage.getString(), "operationName", null, 1, 1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOperationCallExp_ResolveTempResolvedBy(), this.getResolveTempResolution(), null, "resolveTempResolvedBy", null, 0, -1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operatorCallExpEClass, OperatorCallExp.class, "OperatorCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(collectionOperationCallExpEClass, CollectionOperationCallExp.class, "CollectionOperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(loopExpEClass, LoopExp.class, "LoopExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLoopExp_Body(), this.getOclExpression(), this.getOclExpression_LoopExp(), "body", null, 1, 1, LoopExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getLoopExp_Iterators(), this.getIterator(), this.getIterator_LoopExpr(), "iterators", null, 1, -1, LoopExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iterateExpEClass, IterateExp.class, "IterateExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIterateExp_Result(), this.getVariableDeclaration(), this.getVariableDeclaration_BaseExp(), "result", null, 1, 1, IterateExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iteratorExpEClass, IteratorExp.class, "IteratorExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIteratorExp_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, IteratorExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(letExpEClass, LetExp.class, "LetExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLetExp_Variable(), this.getVariableDeclaration(), this.getVariableDeclaration_LetExp(), "variable", null, 1, 1, LetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getLetExp_In_(), this.getOclExpression(), this.getOclExpression_LetExp(), "in_", null, 1, 1, LetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(ifExpEClass, IfExp.class, "IfExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIfExp_ThenExpression(), this.getOclExpression(), this.getOclExpression_IfExp2(), "thenExpression", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIfExp_Condition(), this.getOclExpression(), this.getOclExpression_IfExp1(), "condition", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIfExp_ElseExpression(), this.getOclExpression(), this.getOclExpression_IfExp3(), "elseExpression", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(variableDeclarationEClass, VariableDeclaration.class, "VariableDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableDeclaration_Id(), thePrimitiveTypesPackage.getString(), "id", null, 0, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getVariableDeclaration_VarName(), thePrimitiveTypesPackage.getString(), "varName", null, 1, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_Type(), this.getOclType(), this.getOclType_VariableDeclaration(), "type", null, 0, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_InitExpression(), this.getOclExpression(), this.getOclExpression_InitializedVariable(), "initExpression", null, 0, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_LetExp(), this.getLetExp(), this.getLetExp_Variable(), "letExp", null, 0, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_BaseExp(), this.getIterateExp(), this.getIterateExp_Result(), "baseExp", null, 0, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_VariableExp(), this.getVariableExp(), this.getVariableExp_ReferredVariable(), "variableExp", null, 0, -1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_StaticType(), theTypesPackage.getType(), null, "staticType", null, 0, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iteratorEClass, Iterator.class, "Iterator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIterator_LoopExpr(), this.getLoopExp(), this.getLoopExp_Iterators(), "loopExpr", null, 0, 1, Iterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(collectionTypeEClass, CollectionType.class, "CollectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCollectionType_ElementType(), this.getOclType(), this.getOclType_CollectionTypes(), "elementType", null, 1, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(oclTypeEClass, OclType.class, "OclType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOclType_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_Definitions(), this.getOclContextDefinition(), this.getOclContextDefinition_Context_(), "definitions", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_OclExpression(), this.getOclExpression(), this.getOclExpression_Type(), "oclExpression", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_Operation(), this.getOperation(), this.getOperation_ReturnType(), "operation", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_MapType2(), this.getMapType(), this.getMapType_ValueType(), "mapType2", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_Attribute(), this.getAttribute(), this.getAttribute_Type(), "attribute", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_MapType(), this.getMapType(), this.getMapType_KeyType(), "mapType", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_CollectionTypes(), this.getCollectionType(), this.getCollectionType_ElementType(), "collectionTypes", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_TupleTypeAttribute(), this.getTupleTypeAttribute(), this.getTupleTypeAttribute_Type(), "tupleTypeAttribute", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclType_VariableDeclaration(), this.getVariableDeclaration(), this.getVariableDeclaration_Type(), "variableDeclaration", null, 0, 1, OclType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(primitiveEClass, Primitive.class, "Primitive", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringTypeEClass, StringType.class, "StringType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanTypeEClass, BooleanType.class, "BooleanType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(numericTypeEClass, NumericType.class, "NumericType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(integerTypeEClass, IntegerType.class, "IntegerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(realTypeEClass, RealType.class, "RealType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(bagTypeEClass, BagType.class, "BagType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(orderedSetTypeEClass, OrderedSetType.class, "OrderedSetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sequenceTypeEClass, SequenceType.class, "SequenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(setTypeEClass, SetType.class, "SetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(oclAnyTypeEClass, OclAnyType.class, "OclAnyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tupleTypeEClass, TupleType.class, "TupleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTupleType_Attributes(), this.getTupleTypeAttribute(), this.getTupleTypeAttribute_TupleType(), "attributes", null, 0, -1, TupleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tupleTypeAttributeEClass, TupleTypeAttribute.class, "TupleTypeAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTupleTypeAttribute_Type(), this.getOclType(), this.getOclType_TupleTypeAttribute(), "type", null, 1, 1, TupleTypeAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTupleTypeAttribute_TupleType(), this.getTupleType(), this.getTupleType_Attributes(), "tupleType", null, 1, 1, TupleTypeAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getTupleTypeAttribute_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, TupleTypeAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(oclModelElementEClass, OclModelElement.class, "OclModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOclModelElement_Model(), this.getOclModel(), this.getOclModel_Elements(), "model", null, 1, 1, OclModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(mapTypeEClass, MapType.class, "MapType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMapType_ValueType(), this.getOclType(), this.getOclType_MapType2(), "valueType", null, 1, 1, MapType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMapType_KeyType(), this.getOclType(), this.getOclType_MapType(), "keyType", null, 1, 1, MapType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(oclFeatureDefinitionEClass, OclFeatureDefinition.class, "OclFeatureDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOclFeatureDefinition_Feature(), this.getOclFeature(), this.getOclFeature_Definition(), "feature", null, 1, 1, OclFeatureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclFeatureDefinition_Context_(), this.getOclContextDefinition(), this.getOclContextDefinition_Definition(), "context_", null, 0, 1, OclFeatureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(oclContextDefinitionEClass, OclContextDefinition.class, "OclContextDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOclContextDefinition_Definition(), this.getOclFeatureDefinition(), this.getOclFeatureDefinition_Context_(), "definition", null, 1, 1, OclContextDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclContextDefinition_Context_(), this.getOclType(), this.getOclType_Definitions(), "context_", null, 1, 1, OclContextDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(oclFeatureEClass, OclFeature.class, "OclFeature", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOclFeature_Definition(), this.getOclFeatureDefinition(), this.getOclFeatureDefinition_Feature(), "definition", null, 0, 1, OclFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAttribute_InitExpression(), this.getOclExpression(), this.getOclExpression_OwningAttribute(), "initExpression", null, 1, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAttribute_Type(), this.getOclType(), this.getOclType_Attribute(), "type", null, 1, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOperation_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_ReturnType(), this.getOclType(), this.getOclType_Operation(), "returnType", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOperation_Body(), this.getOclExpression(), this.getOclExpression_OwningOperation(), "body", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(oclModelEClass, OclModel.class, "OclModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOclModel_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, OclModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclModel_Metamodel(), this.getOclModel(), this.getOclModel_Model(), "metamodel", null, 1, 1, OclModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclModel_Elements(), this.getOclModelElement(), this.getOclModelElement_Model(), "elements", null, 0, -1, OclModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOclModel_Model(), this.getOclModel(), this.getOclModel_Metamodel(), "model", null, 0, -1, OclModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(typedElementEClass, TypedElement.class, "TypedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypedElement_InferredType(), theTypesPackage.getType(), null, "inferredType", null, 0, 1, TypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resolveTempResolutionEClass, ResolveTempResolution.class, "ResolveTempResolution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResolveTempResolution_Rule(), theATLPackage.getMatchedRule(), null, "rule", null, 1, 1, ResolveTempResolution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResolveTempResolution_Element(), theATLPackage.getOutPatternElement(), null, "element", null, 1, 1, ResolveTempResolution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaBodyEClass, JavaBody.class, "JavaBody", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(getAppliedStereotypesBodyEClass, GetAppliedStereotypesBody.class, "GetAppliedStereotypesBody", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //OCLPackageImpl
