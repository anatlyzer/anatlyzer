/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atl.types.TypesPackage;

import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.ActionBlock;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ExpressionStat;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.ForStat;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.IfStat;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.LibraryRef;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleCallable;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.PatternElement;
import anatlyzer.atlext.ATL.Query;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.Statement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.ATL.Unit;

import anatlyzer.atlext.OCL.OCLPackage;

import anatlyzer.atlext.OCL.impl.OCLPackageImpl;

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
public class ATLPackageImpl extends EPackageImpl implements ATLPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass locatedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass helperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticHelperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextHelperEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleCallableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleWithPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matchedRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lazyRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass calledRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass patternElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inPatternElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simpleInPatternElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outPatternElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simpleOutPatternElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forEachOutPatternElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleVariableDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libraryRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionBlockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionStatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bindingStatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ifStatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forStatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callableParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleResolutionInfoEClass = null;

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
	 * @see anatlyzer.atlext.ATL.ATLPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ATLPackageImpl() {
		super(eNS_URI, ATLFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ATLPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ATLPackage init() {
		if (isInited) return (ATLPackage)EPackage.Registry.INSTANCE.getEPackage(ATLPackage.eNS_URI);

		// Obtain or create and register package
		ATLPackageImpl theATLPackage = (ATLPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ATLPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ATLPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		OCLPackageImpl theOCLPackage = (OCLPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OCLPackage.eNS_URI) instanceof OCLPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OCLPackage.eNS_URI) : OCLPackage.eINSTANCE);
		PrimitiveTypesPackageImpl thePrimitiveTypesPackage = (PrimitiveTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI) instanceof PrimitiveTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI) : PrimitiveTypesPackage.eINSTANCE);

		// Create package meta-data objects
		theATLPackage.createPackageContents();
		theOCLPackage.createPackageContents();
		thePrimitiveTypesPackage.createPackageContents();

		// Initialize created meta-data
		theATLPackage.initializePackageContents();
		theOCLPackage.initializePackageContents();
		thePrimitiveTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theATLPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ATLPackage.eNS_URI, theATLPackage);
		return theATLPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocatedElement() {
		return locatedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocatedElement_Location() {
		return (EAttribute)locatedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocatedElement_CommentsBefore() {
		return (EAttribute)locatedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocatedElement_CommentsAfter() {
		return (EAttribute)locatedElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocatedElement_FileLocation() {
		return (EAttribute)locatedElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocatedElement_FileObject() {
		return (EAttribute)locatedElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnit() {
		return unitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnit_Libraries() {
		return (EReference)unitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnit_Name() {
		return (EAttribute)unitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLibrary() {
		return libraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLibrary_Helpers() {
		return (EReference)libraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuery() {
		return queryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuery_Body() {
		return (EReference)queryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuery_Helpers() {
		return (EReference)queryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModule() {
		return moduleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModule_IsRefining() {
		return (EAttribute)moduleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModule_InModels() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModule_OutModels() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModule_Elements() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModuleElement() {
		return moduleElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHelper() {
		return helperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHelper_Query() {
		return (EReference)helperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHelper_Library() {
		return (EReference)helperEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHelper_Definition() {
		return (EReference)helperEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHelper_HasContext() {
		return (EAttribute)helperEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHelper_IsAttribute() {
		return (EAttribute)helperEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHelper_InferredReturnType() {
		return (EReference)helperEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHelper_StaticReturnType() {
		return (EReference)helperEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticHelper() {
		return staticHelperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextHelper() {
		return contextHelperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContextHelper_ContextType() {
		return (EReference)contextHelperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContextHelper_PolymorphicCalledBy() {
		return (EReference)contextHelperEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRule() {
		return ruleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_OutPattern() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_ActionBlock() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_Variables() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRule_Name() {
		return (EAttribute)ruleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticRule() {
		return staticRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModuleCallable() {
		return moduleCallableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallable() {
		return callableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallable_CalledBy() {
		return (EReference)callableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallable_CallableParameters() {
		return (EReference)callableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuleWithPattern() {
		return ruleWithPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleWithPattern_InPattern() {
		return (EReference)ruleWithPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleWithPattern_Children() {
		return (EReference)ruleWithPatternEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleWithPattern_SuperRule() {
		return (EReference)ruleWithPatternEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleWithPattern_IsAbstract() {
		return (EAttribute)ruleWithPatternEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleWithPattern_IsRefining() {
		return (EAttribute)ruleWithPatternEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleWithPattern_IsNoDefault() {
		return (EAttribute)ruleWithPatternEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMatchedRule() {
		return matchedRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLazyRule() {
		return lazyRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLazyRule_IsUnique() {
		return (EAttribute)lazyRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCalledRule() {
		return calledRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCalledRule_Parameters() {
		return (EReference)calledRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalledRule_IsEntrypoint() {
		return (EAttribute)calledRuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalledRule_IsEndpoint() {
		return (EAttribute)calledRuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInPattern() {
		return inPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInPattern_Elements() {
		return (EReference)inPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInPattern_Filter() {
		return (EReference)inPatternEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutPattern() {
		return outPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutPattern_Rule() {
		return (EReference)outPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutPattern_Elements() {
		return (EReference)outPatternEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPatternElement() {
		return patternElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInPatternElement() {
		return inPatternElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInPatternElement_MapsTo() {
		return (EReference)inPatternElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInPatternElement_InPattern() {
		return (EReference)inPatternElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInPatternElement_Models() {
		return (EReference)inPatternElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimpleInPatternElement() {
		return simpleInPatternElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutPatternElement() {
		return outPatternElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutPatternElement_OutPattern() {
		return (EReference)outPatternElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutPatternElement_SourceElement() {
		return (EReference)outPatternElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutPatternElement_Bindings() {
		return (EReference)outPatternElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutPatternElement_Model() {
		return (EReference)outPatternElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimpleOutPatternElement() {
		return simpleOutPatternElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimpleOutPatternElement_ReverseBindings() {
		return (EReference)simpleOutPatternElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForEachOutPatternElement() {
		return forEachOutPatternElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForEachOutPatternElement_Collection() {
		return (EReference)forEachOutPatternElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForEachOutPatternElement_Iterator() {
		return (EReference)forEachOutPatternElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinding() {
		return bindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinding_Value() {
		return (EReference)bindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinding_OutPatternElement() {
		return (EReference)bindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBinding_PropertyName() {
		return (EAttribute)bindingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBinding_IsAssignment() {
		return (EAttribute)bindingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinding_WrittenFeature() {
		return (EReference)bindingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinding_ResolvedBy() {
		return (EReference)bindingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuleVariableDeclaration() {
		return ruleVariableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleVariableDeclaration_Rule() {
		return (EReference)ruleVariableDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLibraryRef() {
		return libraryRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLibraryRef_Unit() {
		return (EReference)libraryRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLibraryRef_Name() {
		return (EAttribute)libraryRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionBlock() {
		return actionBlockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionBlock_Rule() {
		return (EReference)actionBlockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionBlock_Statements() {
		return (EReference)actionBlockEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatement() {
		return statementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpressionStat() {
		return expressionStatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpressionStat_Expression() {
		return (EReference)expressionStatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBindingStat() {
		return bindingStatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBindingStat_Source() {
		return (EReference)bindingStatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBindingStat_PropertyName() {
		return (EAttribute)bindingStatEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBindingStat_IsAssignment() {
		return (EAttribute)bindingStatEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBindingStat_Value() {
		return (EReference)bindingStatEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIfStat() {
		return ifStatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStat_Condition() {
		return (EReference)ifStatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStat_ThenStatements() {
		return (EReference)ifStatEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStat_ElseStatements() {
		return (EReference)ifStatEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForStat() {
		return forStatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStat_Iterator() {
		return (EReference)forStatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStat_Collection() {
		return (EReference)forStatEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStat_Statements() {
		return (EReference)forStatEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallableParameter() {
		return callableParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCallableParameter_Name() {
		return (EAttribute)callableParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallableParameter_StaticType() {
		return (EReference)callableParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallableParameter_ParamDeclaration() {
		return (EReference)callableParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuleResolutionInfo() {
		return ruleResolutionInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleResolutionInfo_Rule() {
		return (EReference)ruleResolutionInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleResolutionInfo_AllInvolvedRules() {
		return (EReference)ruleResolutionInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATLFactory getATLFactory() {
		return (ATLFactory)getEFactoryInstance();
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
		locatedElementEClass = createEClass(LOCATED_ELEMENT);
		createEAttribute(locatedElementEClass, LOCATED_ELEMENT__LOCATION);
		createEAttribute(locatedElementEClass, LOCATED_ELEMENT__COMMENTS_BEFORE);
		createEAttribute(locatedElementEClass, LOCATED_ELEMENT__COMMENTS_AFTER);
		createEAttribute(locatedElementEClass, LOCATED_ELEMENT__FILE_LOCATION);
		createEAttribute(locatedElementEClass, LOCATED_ELEMENT__FILE_OBJECT);

		unitEClass = createEClass(UNIT);
		createEReference(unitEClass, UNIT__LIBRARIES);
		createEAttribute(unitEClass, UNIT__NAME);

		libraryEClass = createEClass(LIBRARY);
		createEReference(libraryEClass, LIBRARY__HELPERS);

		queryEClass = createEClass(QUERY);
		createEReference(queryEClass, QUERY__BODY);
		createEReference(queryEClass, QUERY__HELPERS);

		moduleEClass = createEClass(MODULE);
		createEAttribute(moduleEClass, MODULE__IS_REFINING);
		createEReference(moduleEClass, MODULE__IN_MODELS);
		createEReference(moduleEClass, MODULE__OUT_MODELS);
		createEReference(moduleEClass, MODULE__ELEMENTS);

		moduleElementEClass = createEClass(MODULE_ELEMENT);

		helperEClass = createEClass(HELPER);
		createEReference(helperEClass, HELPER__QUERY);
		createEReference(helperEClass, HELPER__LIBRARY);
		createEReference(helperEClass, HELPER__DEFINITION);
		createEAttribute(helperEClass, HELPER__HAS_CONTEXT);
		createEAttribute(helperEClass, HELPER__IS_ATTRIBUTE);
		createEReference(helperEClass, HELPER__INFERRED_RETURN_TYPE);
		createEReference(helperEClass, HELPER__STATIC_RETURN_TYPE);

		staticHelperEClass = createEClass(STATIC_HELPER);

		contextHelperEClass = createEClass(CONTEXT_HELPER);
		createEReference(contextHelperEClass, CONTEXT_HELPER__CONTEXT_TYPE);
		createEReference(contextHelperEClass, CONTEXT_HELPER__POLYMORPHIC_CALLED_BY);

		ruleEClass = createEClass(RULE);
		createEReference(ruleEClass, RULE__OUT_PATTERN);
		createEReference(ruleEClass, RULE__ACTION_BLOCK);
		createEReference(ruleEClass, RULE__VARIABLES);
		createEAttribute(ruleEClass, RULE__NAME);

		staticRuleEClass = createEClass(STATIC_RULE);

		moduleCallableEClass = createEClass(MODULE_CALLABLE);

		callableEClass = createEClass(CALLABLE);
		createEReference(callableEClass, CALLABLE__CALLED_BY);
		createEReference(callableEClass, CALLABLE__CALLABLE_PARAMETERS);

		ruleWithPatternEClass = createEClass(RULE_WITH_PATTERN);
		createEReference(ruleWithPatternEClass, RULE_WITH_PATTERN__IN_PATTERN);
		createEReference(ruleWithPatternEClass, RULE_WITH_PATTERN__CHILDREN);
		createEReference(ruleWithPatternEClass, RULE_WITH_PATTERN__SUPER_RULE);
		createEAttribute(ruleWithPatternEClass, RULE_WITH_PATTERN__IS_ABSTRACT);
		createEAttribute(ruleWithPatternEClass, RULE_WITH_PATTERN__IS_REFINING);
		createEAttribute(ruleWithPatternEClass, RULE_WITH_PATTERN__IS_NO_DEFAULT);

		matchedRuleEClass = createEClass(MATCHED_RULE);

		lazyRuleEClass = createEClass(LAZY_RULE);
		createEAttribute(lazyRuleEClass, LAZY_RULE__IS_UNIQUE);

		calledRuleEClass = createEClass(CALLED_RULE);
		createEReference(calledRuleEClass, CALLED_RULE__PARAMETERS);
		createEAttribute(calledRuleEClass, CALLED_RULE__IS_ENTRYPOINT);
		createEAttribute(calledRuleEClass, CALLED_RULE__IS_ENDPOINT);

		inPatternEClass = createEClass(IN_PATTERN);
		createEReference(inPatternEClass, IN_PATTERN__ELEMENTS);
		createEReference(inPatternEClass, IN_PATTERN__FILTER);

		outPatternEClass = createEClass(OUT_PATTERN);
		createEReference(outPatternEClass, OUT_PATTERN__RULE);
		createEReference(outPatternEClass, OUT_PATTERN__ELEMENTS);

		patternElementEClass = createEClass(PATTERN_ELEMENT);

		inPatternElementEClass = createEClass(IN_PATTERN_ELEMENT);
		createEReference(inPatternElementEClass, IN_PATTERN_ELEMENT__MAPS_TO);
		createEReference(inPatternElementEClass, IN_PATTERN_ELEMENT__IN_PATTERN);
		createEReference(inPatternElementEClass, IN_PATTERN_ELEMENT__MODELS);

		simpleInPatternElementEClass = createEClass(SIMPLE_IN_PATTERN_ELEMENT);

		outPatternElementEClass = createEClass(OUT_PATTERN_ELEMENT);
		createEReference(outPatternElementEClass, OUT_PATTERN_ELEMENT__OUT_PATTERN);
		createEReference(outPatternElementEClass, OUT_PATTERN_ELEMENT__SOURCE_ELEMENT);
		createEReference(outPatternElementEClass, OUT_PATTERN_ELEMENT__BINDINGS);
		createEReference(outPatternElementEClass, OUT_PATTERN_ELEMENT__MODEL);

		simpleOutPatternElementEClass = createEClass(SIMPLE_OUT_PATTERN_ELEMENT);
		createEReference(simpleOutPatternElementEClass, SIMPLE_OUT_PATTERN_ELEMENT__REVERSE_BINDINGS);

		forEachOutPatternElementEClass = createEClass(FOR_EACH_OUT_PATTERN_ELEMENT);
		createEReference(forEachOutPatternElementEClass, FOR_EACH_OUT_PATTERN_ELEMENT__COLLECTION);
		createEReference(forEachOutPatternElementEClass, FOR_EACH_OUT_PATTERN_ELEMENT__ITERATOR);

		bindingEClass = createEClass(BINDING);
		createEReference(bindingEClass, BINDING__VALUE);
		createEReference(bindingEClass, BINDING__OUT_PATTERN_ELEMENT);
		createEAttribute(bindingEClass, BINDING__PROPERTY_NAME);
		createEAttribute(bindingEClass, BINDING__IS_ASSIGNMENT);
		createEReference(bindingEClass, BINDING__WRITTEN_FEATURE);
		createEReference(bindingEClass, BINDING__RESOLVED_BY);

		ruleVariableDeclarationEClass = createEClass(RULE_VARIABLE_DECLARATION);
		createEReference(ruleVariableDeclarationEClass, RULE_VARIABLE_DECLARATION__RULE);

		libraryRefEClass = createEClass(LIBRARY_REF);
		createEReference(libraryRefEClass, LIBRARY_REF__UNIT);
		createEAttribute(libraryRefEClass, LIBRARY_REF__NAME);

		actionBlockEClass = createEClass(ACTION_BLOCK);
		createEReference(actionBlockEClass, ACTION_BLOCK__RULE);
		createEReference(actionBlockEClass, ACTION_BLOCK__STATEMENTS);

		statementEClass = createEClass(STATEMENT);

		expressionStatEClass = createEClass(EXPRESSION_STAT);
		createEReference(expressionStatEClass, EXPRESSION_STAT__EXPRESSION);

		bindingStatEClass = createEClass(BINDING_STAT);
		createEReference(bindingStatEClass, BINDING_STAT__SOURCE);
		createEAttribute(bindingStatEClass, BINDING_STAT__PROPERTY_NAME);
		createEAttribute(bindingStatEClass, BINDING_STAT__IS_ASSIGNMENT);
		createEReference(bindingStatEClass, BINDING_STAT__VALUE);

		ifStatEClass = createEClass(IF_STAT);
		createEReference(ifStatEClass, IF_STAT__CONDITION);
		createEReference(ifStatEClass, IF_STAT__THEN_STATEMENTS);
		createEReference(ifStatEClass, IF_STAT__ELSE_STATEMENTS);

		forStatEClass = createEClass(FOR_STAT);
		createEReference(forStatEClass, FOR_STAT__ITERATOR);
		createEReference(forStatEClass, FOR_STAT__COLLECTION);
		createEReference(forStatEClass, FOR_STAT__STATEMENTS);

		callableParameterEClass = createEClass(CALLABLE_PARAMETER);
		createEAttribute(callableParameterEClass, CALLABLE_PARAMETER__NAME);
		createEReference(callableParameterEClass, CALLABLE_PARAMETER__STATIC_TYPE);
		createEReference(callableParameterEClass, CALLABLE_PARAMETER__PARAM_DECLARATION);

		ruleResolutionInfoEClass = createEClass(RULE_RESOLUTION_INFO);
		createEReference(ruleResolutionInfoEClass, RULE_RESOLUTION_INFO__RULE);
		createEReference(ruleResolutionInfoEClass, RULE_RESOLUTION_INFO__ALL_INVOLVED_RULES);
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
		PrimitiveTypesPackage thePrimitiveTypesPackage = (PrimitiveTypesPackage)EPackage.Registry.INSTANCE.getEPackage(PrimitiveTypesPackage.eNS_URI);
		OCLPackage theOCLPackage = (OCLPackage)EPackage.Registry.INSTANCE.getEPackage(OCLPackage.eNS_URI);
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		unitEClass.getESuperTypes().add(this.getLocatedElement());
		libraryEClass.getESuperTypes().add(this.getUnit());
		queryEClass.getESuperTypes().add(this.getUnit());
		moduleEClass.getESuperTypes().add(this.getUnit());
		moduleElementEClass.getESuperTypes().add(this.getLocatedElement());
		helperEClass.getESuperTypes().add(this.getModuleElement());
		helperEClass.getESuperTypes().add(this.getCallable());
		staticHelperEClass.getESuperTypes().add(this.getHelper());
		staticHelperEClass.getESuperTypes().add(this.getModuleCallable());
		contextHelperEClass.getESuperTypes().add(this.getHelper());
		ruleEClass.getESuperTypes().add(this.getModuleElement());
		staticRuleEClass.getESuperTypes().add(this.getModuleCallable());
		staticRuleEClass.getESuperTypes().add(this.getRule());
		moduleCallableEClass.getESuperTypes().add(this.getCallable());
		ruleWithPatternEClass.getESuperTypes().add(this.getRule());
		matchedRuleEClass.getESuperTypes().add(this.getRuleWithPattern());
		lazyRuleEClass.getESuperTypes().add(this.getRuleWithPattern());
		lazyRuleEClass.getESuperTypes().add(this.getStaticRule());
		calledRuleEClass.getESuperTypes().add(this.getStaticRule());
		inPatternEClass.getESuperTypes().add(this.getLocatedElement());
		outPatternEClass.getESuperTypes().add(this.getLocatedElement());
		patternElementEClass.getESuperTypes().add(theOCLPackage.getVariableDeclaration());
		inPatternElementEClass.getESuperTypes().add(this.getPatternElement());
		simpleInPatternElementEClass.getESuperTypes().add(this.getInPatternElement());
		outPatternElementEClass.getESuperTypes().add(this.getPatternElement());
		simpleOutPatternElementEClass.getESuperTypes().add(this.getOutPatternElement());
		forEachOutPatternElementEClass.getESuperTypes().add(this.getOutPatternElement());
		bindingEClass.getESuperTypes().add(this.getLocatedElement());
		ruleVariableDeclarationEClass.getESuperTypes().add(theOCLPackage.getVariableDeclaration());
		libraryRefEClass.getESuperTypes().add(this.getLocatedElement());
		actionBlockEClass.getESuperTypes().add(this.getLocatedElement());
		statementEClass.getESuperTypes().add(this.getLocatedElement());
		expressionStatEClass.getESuperTypes().add(this.getStatement());
		bindingStatEClass.getESuperTypes().add(this.getStatement());
		ifStatEClass.getESuperTypes().add(this.getStatement());
		forStatEClass.getESuperTypes().add(this.getStatement());

		// Initialize classes, features, and operations; add parameters
		initEClass(locatedElementEClass, LocatedElement.class, "LocatedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocatedElement_Location(), thePrimitiveTypesPackage.getString(), "location", null, 0, 1, LocatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getLocatedElement_CommentsBefore(), thePrimitiveTypesPackage.getString(), "commentsBefore", null, 0, -1, LocatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocatedElement_CommentsAfter(), thePrimitiveTypesPackage.getString(), "commentsAfter", null, 0, -1, LocatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocatedElement_FileLocation(), ecorePackage.getEString(), "fileLocation", null, 0, 1, LocatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocatedElement_FileObject(), ecorePackage.getEJavaObject(), "fileObject", null, 0, 1, LocatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unitEClass, Unit.class, "Unit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnit_Libraries(), this.getLibraryRef(), this.getLibraryRef_Unit(), "libraries", null, 0, -1, Unit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getUnit_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, Unit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLibrary_Helpers(), this.getHelper(), this.getHelper_Library(), "helpers", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQuery_Body(), theOCLPackage.getOclExpression(), null, "body", null, 1, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getQuery_Helpers(), this.getHelper(), this.getHelper_Query(), "helpers", null, 0, -1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModule_IsRefining(), thePrimitiveTypesPackage.getBoolean(), "isRefining", null, 1, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModule_InModels(), theOCLPackage.getOclModel(), null, "inModels", null, 1, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_OutModels(), theOCLPackage.getOclModel(), null, "outModels", null, 1, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModule_Elements(), this.getModuleElement(), null, "elements", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleElementEClass, ModuleElement.class, "ModuleElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(helperEClass, Helper.class, "Helper", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHelper_Query(), this.getQuery(), this.getQuery_Helpers(), "query", null, 0, 1, Helper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getHelper_Library(), this.getLibrary(), this.getLibrary_Helpers(), "library", null, 0, 1, Helper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getHelper_Definition(), theOCLPackage.getOclFeatureDefinition(), null, "definition", null, 1, 1, Helper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getHelper_HasContext(), ecorePackage.getEBoolean(), "hasContext", "false", 1, 1, Helper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHelper_IsAttribute(), ecorePackage.getEBoolean(), "isAttribute", "false", 1, 1, Helper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHelper_InferredReturnType(), theTypesPackage.getType(), null, "inferredReturnType", null, 0, 1, Helper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHelper_StaticReturnType(), theTypesPackage.getType(), null, "staticReturnType", null, 0, 1, Helper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(staticHelperEClass, StaticHelper.class, "StaticHelper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(contextHelperEClass, ContextHelper.class, "ContextHelper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContextHelper_ContextType(), theTypesPackage.getType(), null, "contextType", null, 1, 1, ContextHelper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContextHelper_PolymorphicCalledBy(), theOCLPackage.getPropertyCallExp(), theOCLPackage.getPropertyCallExp_DynamicResolvers(), "polymorphicCalledBy", null, 0, -1, ContextHelper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleEClass, Rule.class, "Rule", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRule_OutPattern(), this.getOutPattern(), this.getOutPattern_Rule(), "outPattern", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRule_ActionBlock(), this.getActionBlock(), this.getActionBlock_Rule(), "actionBlock", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRule_Variables(), this.getRuleVariableDeclaration(), this.getRuleVariableDeclaration_Rule(), "variables", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRule_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(staticRuleEClass, StaticRule.class, "StaticRule", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(moduleCallableEClass, ModuleCallable.class, "ModuleCallable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(callableEClass, Callable.class, "Callable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallable_CalledBy(), theOCLPackage.getPropertyCallExp(), theOCLPackage.getPropertyCallExp_StaticResolver(), "calledBy", null, 0, -1, Callable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCallable_CallableParameters(), this.getCallableParameter(), null, "callableParameters", null, 0, -1, Callable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleWithPatternEClass, RuleWithPattern.class, "RuleWithPattern", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRuleWithPattern_InPattern(), this.getInPattern(), null, "inPattern", null, 0, 1, RuleWithPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRuleWithPattern_Children(), this.getRuleWithPattern(), this.getRuleWithPattern_SuperRule(), "children", null, 0, -1, RuleWithPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRuleWithPattern_SuperRule(), this.getRuleWithPattern(), this.getRuleWithPattern_Children(), "superRule", null, 0, 1, RuleWithPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getRuleWithPattern_IsAbstract(), thePrimitiveTypesPackage.getBoolean(), "isAbstract", null, 1, 1, RuleWithPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getRuleWithPattern_IsRefining(), thePrimitiveTypesPackage.getBoolean(), "isRefining", null, 1, 1, RuleWithPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getRuleWithPattern_IsNoDefault(), thePrimitiveTypesPackage.getBoolean(), "isNoDefault", null, 1, 1, RuleWithPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(matchedRuleEClass, MatchedRule.class, "MatchedRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(lazyRuleEClass, LazyRule.class, "LazyRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLazyRule_IsUnique(), thePrimitiveTypesPackage.getBoolean(), "isUnique", null, 1, 1, LazyRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(calledRuleEClass, CalledRule.class, "CalledRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCalledRule_Parameters(), theOCLPackage.getParameter(), null, "parameters", null, 0, -1, CalledRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCalledRule_IsEntrypoint(), thePrimitiveTypesPackage.getBoolean(), "isEntrypoint", null, 1, 1, CalledRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCalledRule_IsEndpoint(), thePrimitiveTypesPackage.getBoolean(), "isEndpoint", null, 1, 1, CalledRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(inPatternEClass, InPattern.class, "InPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInPattern_Elements(), this.getInPatternElement(), this.getInPatternElement_InPattern(), "elements", null, 1, -1, InPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInPattern_Filter(), theOCLPackage.getOclExpression(), null, "filter", null, 0, 1, InPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(outPatternEClass, OutPattern.class, "OutPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutPattern_Rule(), this.getRule(), this.getRule_OutPattern(), "rule", null, 1, 1, OutPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOutPattern_Elements(), this.getOutPatternElement(), this.getOutPatternElement_OutPattern(), "elements", null, 1, -1, OutPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(patternElementEClass, PatternElement.class, "PatternElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(inPatternElementEClass, InPatternElement.class, "InPatternElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInPatternElement_MapsTo(), this.getOutPatternElement(), this.getOutPatternElement_SourceElement(), "mapsTo", null, 1, 1, InPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInPatternElement_InPattern(), this.getInPattern(), this.getInPattern_Elements(), "inPattern", null, 1, 1, InPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInPatternElement_Models(), theOCLPackage.getOclModel(), null, "models", null, 0, -1, InPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(simpleInPatternElementEClass, SimpleInPatternElement.class, "SimpleInPatternElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outPatternElementEClass, OutPatternElement.class, "OutPatternElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutPatternElement_OutPattern(), this.getOutPattern(), this.getOutPattern_Elements(), "outPattern", null, 1, 1, OutPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOutPatternElement_SourceElement(), this.getInPatternElement(), this.getInPatternElement_MapsTo(), "sourceElement", null, 0, 1, OutPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOutPatternElement_Bindings(), this.getBinding(), this.getBinding_OutPatternElement(), "bindings", null, 0, -1, OutPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutPatternElement_Model(), theOCLPackage.getOclModel(), null, "model", null, 0, 1, OutPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(simpleOutPatternElementEClass, SimpleOutPatternElement.class, "SimpleOutPatternElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSimpleOutPatternElement_ReverseBindings(), theOCLPackage.getOclExpression(), null, "reverseBindings", null, 0, -1, SimpleOutPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(forEachOutPatternElementEClass, ForEachOutPatternElement.class, "ForEachOutPatternElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getForEachOutPatternElement_Collection(), theOCLPackage.getOclExpression(), null, "collection", null, 1, 1, ForEachOutPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getForEachOutPatternElement_Iterator(), theOCLPackage.getIterator(), null, "iterator", null, 1, 1, ForEachOutPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(bindingEClass, Binding.class, "Binding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBinding_Value(), theOCLPackage.getOclExpression(), null, "value", null, 1, 1, Binding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getBinding_OutPatternElement(), this.getOutPatternElement(), this.getOutPatternElement_Bindings(), "outPatternElement", null, 1, 1, Binding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getBinding_PropertyName(), thePrimitiveTypesPackage.getString(), "propertyName", null, 1, 1, Binding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getBinding_IsAssignment(), thePrimitiveTypesPackage.getBoolean(), "isAssignment", null, 1, 1, Binding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getBinding_WrittenFeature(), ecorePackage.getEObject(), null, "writtenFeature", null, 1, 1, Binding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinding_ResolvedBy(), this.getRuleResolutionInfo(), null, "resolvedBy", null, 0, -1, Binding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleVariableDeclarationEClass, RuleVariableDeclaration.class, "RuleVariableDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRuleVariableDeclaration_Rule(), this.getRule(), this.getRule_Variables(), "rule", null, 1, 1, RuleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(libraryRefEClass, LibraryRef.class, "LibraryRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLibraryRef_Unit(), this.getUnit(), this.getUnit_Libraries(), "unit", null, 1, 1, LibraryRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getLibraryRef_Name(), thePrimitiveTypesPackage.getString(), "name", null, 1, 1, LibraryRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(actionBlockEClass, ActionBlock.class, "ActionBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionBlock_Rule(), this.getRule(), this.getRule_ActionBlock(), "rule", null, 1, 1, ActionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getActionBlock_Statements(), this.getStatement(), null, "statements", null, 0, -1, ActionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statementEClass, Statement.class, "Statement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expressionStatEClass, ExpressionStat.class, "ExpressionStat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExpressionStat_Expression(), theOCLPackage.getOclExpression(), null, "expression", null, 1, 1, ExpressionStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(bindingStatEClass, BindingStat.class, "BindingStat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBindingStat_Source(), theOCLPackage.getOclExpression(), null, "source", null, 1, 1, BindingStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getBindingStat_PropertyName(), thePrimitiveTypesPackage.getString(), "propertyName", null, 1, 1, BindingStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getBindingStat_IsAssignment(), thePrimitiveTypesPackage.getBoolean(), "isAssignment", null, 1, 1, BindingStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getBindingStat_Value(), theOCLPackage.getOclExpression(), null, "value", null, 1, 1, BindingStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(ifStatEClass, IfStat.class, "IfStat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIfStat_Condition(), theOCLPackage.getOclExpression(), null, "condition", null, 1, 1, IfStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIfStat_ThenStatements(), this.getStatement(), null, "thenStatements", null, 0, -1, IfStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIfStat_ElseStatements(), this.getStatement(), null, "elseStatements", null, 0, -1, IfStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(forStatEClass, ForStat.class, "ForStat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getForStat_Iterator(), theOCLPackage.getIterator(), null, "iterator", null, 1, 1, ForStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getForStat_Collection(), theOCLPackage.getOclExpression(), null, "collection", null, 1, 1, ForStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getForStat_Statements(), this.getStatement(), null, "statements", null, 0, -1, ForStat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callableParameterEClass, CallableParameter.class, "CallableParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCallableParameter_Name(), ecorePackage.getEString(), "name", null, 1, 1, CallableParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCallableParameter_StaticType(), theTypesPackage.getType(), null, "staticType", null, 1, 1, CallableParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCallableParameter_ParamDeclaration(), theOCLPackage.getVariableDeclaration(), null, "paramDeclaration", null, 0, 1, CallableParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleResolutionInfoEClass, RuleResolutionInfo.class, "RuleResolutionInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRuleResolutionInfo_Rule(), this.getMatchedRule(), null, "rule", null, 1, 1, RuleResolutionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRuleResolutionInfo_AllInvolvedRules(), this.getMatchedRule(), null, "allInvolvedRules", null, 1, -1, RuleResolutionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ATLPackageImpl
