/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.*;

import java.util.Map;
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
public class ATLFactoryImpl extends EFactoryImpl implements ATLFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ATLFactory init() {
		try {
			ATLFactory theATLFactory = (ATLFactory)EPackage.Registry.INSTANCE.getEFactory(ATLPackage.eNS_URI);
			if (theATLFactory != null) {
				return theATLFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ATLFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATLFactoryImpl() {
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
			case ATLPackage.UNIT: return createUnit();
			case ATLPackage.LIBRARY: return createLibrary();
			case ATLPackage.QUERY: return createQuery();
			case ATLPackage.MODULE: return createModule();
			case ATLPackage.STATIC_HELPER: return createStaticHelper();
			case ATLPackage.CONTEXT_HELPER: return createContextHelper();
			case ATLPackage.MATCHED_RULE: return createMatchedRule();
			case ATLPackage.LAZY_RULE: return createLazyRule();
			case ATLPackage.CALLED_RULE: return createCalledRule();
			case ATLPackage.IN_PATTERN: return createInPattern();
			case ATLPackage.OUT_PATTERN: return createOutPattern();
			case ATLPackage.DROP_PATTERN: return createDropPattern();
			case ATLPackage.SIMPLE_IN_PATTERN_ELEMENT: return createSimpleInPatternElement();
			case ATLPackage.SIMPLE_OUT_PATTERN_ELEMENT: return createSimpleOutPatternElement();
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT: return createForEachOutPatternElement();
			case ATLPackage.BINDING: return createBinding();
			case ATLPackage.RULE_VARIABLE_DECLARATION: return createRuleVariableDeclaration();
			case ATLPackage.LIBRARY_REF: return createLibraryRef();
			case ATLPackage.ACTION_BLOCK: return createActionBlock();
			case ATLPackage.EXPRESSION_STAT: return createExpressionStat();
			case ATLPackage.BINDING_STAT: return createBindingStat();
			case ATLPackage.IF_STAT: return createIfStat();
			case ATLPackage.FOR_STAT: return createForStat();
			case ATLPackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
			case ATLPackage.CALLABLE_PARAMETER: return createCallableParameter();
			case ATLPackage.RULE_RESOLUTION_INFO: return createRuleResolutionInfo();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Unit createUnit() {
		UnitImpl unit = new UnitImpl();
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library createLibrary() {
		LibraryImpl library = new LibraryImpl();
		return library;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Query createQuery() {
		QueryImpl query = new QueryImpl();
		return query;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Module createModule() {
		ModuleImpl module = new ModuleImpl();
		return module;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticHelper createStaticHelper() {
		StaticHelperImpl staticHelper = new StaticHelperImpl();
		return staticHelper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContextHelper createContextHelper() {
		ContextHelperImpl contextHelper = new ContextHelperImpl();
		return contextHelper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatchedRule createMatchedRule() {
		MatchedRuleImpl matchedRule = new MatchedRuleImpl();
		return matchedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LazyRule createLazyRule() {
		LazyRuleImpl lazyRule = new LazyRuleImpl();
		return lazyRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalledRule createCalledRule() {
		CalledRuleImpl calledRule = new CalledRuleImpl();
		return calledRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InPattern createInPattern() {
		InPatternImpl inPattern = new InPatternImpl();
		return inPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPattern createOutPattern() {
		OutPatternImpl outPattern = new OutPatternImpl();
		return outPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DropPattern createDropPattern() {
		DropPatternImpl dropPattern = new DropPatternImpl();
		return dropPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleInPatternElement createSimpleInPatternElement() {
		SimpleInPatternElementImpl simpleInPatternElement = new SimpleInPatternElementImpl();
		return simpleInPatternElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleOutPatternElement createSimpleOutPatternElement() {
		SimpleOutPatternElementImpl simpleOutPatternElement = new SimpleOutPatternElementImpl();
		return simpleOutPatternElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForEachOutPatternElement createForEachOutPatternElement() {
		ForEachOutPatternElementImpl forEachOutPatternElement = new ForEachOutPatternElementImpl();
		return forEachOutPatternElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Binding createBinding() {
		BindingImpl binding = new BindingImpl();
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleVariableDeclaration createRuleVariableDeclaration() {
		RuleVariableDeclarationImpl ruleVariableDeclaration = new RuleVariableDeclarationImpl();
		return ruleVariableDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryRef createLibraryRef() {
		LibraryRefImpl libraryRef = new LibraryRefImpl();
		return libraryRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionBlock createActionBlock() {
		ActionBlockImpl actionBlock = new ActionBlockImpl();
		return actionBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionStat createExpressionStat() {
		ExpressionStatImpl expressionStat = new ExpressionStatImpl();
		return expressionStat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingStat createBindingStat() {
		BindingStatImpl bindingStat = new BindingStatImpl();
		return bindingStat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfStat createIfStat() {
		IfStatImpl ifStat = new IfStatImpl();
		return ifStat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForStat createForStat() {
		ForStatImpl forStat = new ForStatImpl();
		return forStat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createStringToStringMap() {
		StringToStringMapImpl stringToStringMap = new StringToStringMapImpl();
		return stringToStringMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallableParameter createCallableParameter() {
		CallableParameterImpl callableParameter = new CallableParameterImpl();
		return callableParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleResolutionInfo createRuleResolutionInfo() {
		RuleResolutionInfoImpl ruleResolutionInfo = new RuleResolutionInfoImpl();
		return ruleResolutionInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATLPackage getATLPackage() {
		return (ATLPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ATLPackage getPackage() {
		return ATLPackage.eINSTANCE;
	}

} //ATLFactoryImpl
