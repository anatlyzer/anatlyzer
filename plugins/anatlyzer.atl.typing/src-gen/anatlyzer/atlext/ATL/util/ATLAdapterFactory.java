/**
 */
package anatlyzer.atlext.ATL.util;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.ActionBlock;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.DropPattern;
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
import anatlyzer.atlext.OCL.TypedElement;
import anatlyzer.atlext.OCL.VariableDeclaration;

import java.util.Map;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atlext.ATL.ATLPackage
 * @generated
 */
public class ATLAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ATLPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATLAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ATLPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ATLSwitch<Adapter> modelSwitch =
		new ATLSwitch<Adapter>() {
			@Override
			public Adapter caseLocatedElement(LocatedElement object) {
				return createLocatedElementAdapter();
			}
			@Override
			public Adapter caseUnit(Unit object) {
				return createUnitAdapter();
			}
			@Override
			public Adapter caseLibrary(Library object) {
				return createLibraryAdapter();
			}
			@Override
			public Adapter caseQuery(Query object) {
				return createQueryAdapter();
			}
			@Override
			public Adapter caseModule(anatlyzer.atlext.ATL.Module object) {
				return createModuleAdapter();
			}
			@Override
			public Adapter caseModuleElement(ModuleElement object) {
				return createModuleElementAdapter();
			}
			@Override
			public Adapter caseHelper(Helper object) {
				return createHelperAdapter();
			}
			@Override
			public Adapter caseStaticHelper(StaticHelper object) {
				return createStaticHelperAdapter();
			}
			@Override
			public Adapter caseContextHelper(ContextHelper object) {
				return createContextHelperAdapter();
			}
			@Override
			public Adapter caseRule(Rule object) {
				return createRuleAdapter();
			}
			@Override
			public Adapter caseStaticRule(StaticRule object) {
				return createStaticRuleAdapter();
			}
			@Override
			public Adapter caseModuleCallable(ModuleCallable object) {
				return createModuleCallableAdapter();
			}
			@Override
			public Adapter caseCallable(Callable object) {
				return createCallableAdapter();
			}
			@Override
			public Adapter caseRuleWithPattern(RuleWithPattern object) {
				return createRuleWithPatternAdapter();
			}
			@Override
			public Adapter caseMatchedRule(MatchedRule object) {
				return createMatchedRuleAdapter();
			}
			@Override
			public Adapter caseLazyRule(LazyRule object) {
				return createLazyRuleAdapter();
			}
			@Override
			public Adapter caseCalledRule(CalledRule object) {
				return createCalledRuleAdapter();
			}
			@Override
			public Adapter caseInPattern(InPattern object) {
				return createInPatternAdapter();
			}
			@Override
			public Adapter caseOutPattern(OutPattern object) {
				return createOutPatternAdapter();
			}
			@Override
			public Adapter caseDropPattern(DropPattern object) {
				return createDropPatternAdapter();
			}
			@Override
			public Adapter casePatternElement(PatternElement object) {
				return createPatternElementAdapter();
			}
			@Override
			public Adapter caseInPatternElement(InPatternElement object) {
				return createInPatternElementAdapter();
			}
			@Override
			public Adapter caseSimpleInPatternElement(SimpleInPatternElement object) {
				return createSimpleInPatternElementAdapter();
			}
			@Override
			public Adapter caseOutPatternElement(OutPatternElement object) {
				return createOutPatternElementAdapter();
			}
			@Override
			public Adapter caseSimpleOutPatternElement(SimpleOutPatternElement object) {
				return createSimpleOutPatternElementAdapter();
			}
			@Override
			public Adapter caseForEachOutPatternElement(ForEachOutPatternElement object) {
				return createForEachOutPatternElementAdapter();
			}
			@Override
			public Adapter caseBinding(Binding object) {
				return createBindingAdapter();
			}
			@Override
			public Adapter caseRuleVariableDeclaration(RuleVariableDeclaration object) {
				return createRuleVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseLibraryRef(LibraryRef object) {
				return createLibraryRefAdapter();
			}
			@Override
			public Adapter caseActionBlock(ActionBlock object) {
				return createActionBlockAdapter();
			}
			@Override
			public Adapter caseStatement(Statement object) {
				return createStatementAdapter();
			}
			@Override
			public Adapter caseExpressionStat(ExpressionStat object) {
				return createExpressionStatAdapter();
			}
			@Override
			public Adapter caseBindingStat(BindingStat object) {
				return createBindingStatAdapter();
			}
			@Override
			public Adapter caseIfStat(IfStat object) {
				return createIfStatAdapter();
			}
			@Override
			public Adapter caseForStat(ForStat object) {
				return createForStatAdapter();
			}
			@Override
			public Adapter caseStringToStringMap(Map.Entry<String, String> object) {
				return createStringToStringMapAdapter();
			}
			@Override
			public Adapter caseCallableParameter(CallableParameter object) {
				return createCallableParameterAdapter();
			}
			@Override
			public Adapter caseRuleResolutionInfo(RuleResolutionInfo object) {
				return createRuleResolutionInfoAdapter();
			}
			@Override
			public Adapter caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			@Override
			public Adapter caseVariableDeclaration(VariableDeclaration object) {
				return createVariableDeclarationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.LocatedElement <em>Located Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.LocatedElement
	 * @generated
	 */
	public Adapter createLocatedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Unit
	 * @generated
	 */
	public Adapter createUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Library
	 * @generated
	 */
	public Adapter createLibraryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Query <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Query
	 * @generated
	 */
	public Adapter createQueryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Module
	 * @generated
	 */
	public Adapter createModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.ModuleElement <em>Module Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.ModuleElement
	 * @generated
	 */
	public Adapter createModuleElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Helper <em>Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Helper
	 * @generated
	 */
	public Adapter createHelperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.StaticHelper <em>Static Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.StaticHelper
	 * @generated
	 */
	public Adapter createStaticHelperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.ContextHelper <em>Context Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.ContextHelper
	 * @generated
	 */
	public Adapter createContextHelperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Rule
	 * @generated
	 */
	public Adapter createRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.StaticRule <em>Static Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.StaticRule
	 * @generated
	 */
	public Adapter createStaticRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.ModuleCallable <em>Module Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.ModuleCallable
	 * @generated
	 */
	public Adapter createModuleCallableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Callable <em>Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Callable
	 * @generated
	 */
	public Adapter createCallableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.RuleWithPattern <em>Rule With Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.RuleWithPattern
	 * @generated
	 */
	public Adapter createRuleWithPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.MatchedRule <em>Matched Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.MatchedRule
	 * @generated
	 */
	public Adapter createMatchedRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.LazyRule <em>Lazy Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.LazyRule
	 * @generated
	 */
	public Adapter createLazyRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.CalledRule <em>Called Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.CalledRule
	 * @generated
	 */
	public Adapter createCalledRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.InPattern <em>In Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.InPattern
	 * @generated
	 */
	public Adapter createInPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.OutPattern <em>Out Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.OutPattern
	 * @generated
	 */
	public Adapter createOutPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.DropPattern <em>Drop Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.DropPattern
	 * @generated
	 */
	public Adapter createDropPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.PatternElement <em>Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.PatternElement
	 * @generated
	 */
	public Adapter createPatternElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.InPatternElement <em>In Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.InPatternElement
	 * @generated
	 */
	public Adapter createInPatternElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.SimpleInPatternElement <em>Simple In Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.SimpleInPatternElement
	 * @generated
	 */
	public Adapter createSimpleInPatternElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.OutPatternElement <em>Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.OutPatternElement
	 * @generated
	 */
	public Adapter createOutPatternElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.SimpleOutPatternElement <em>Simple Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.SimpleOutPatternElement
	 * @generated
	 */
	public Adapter createSimpleOutPatternElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.ForEachOutPatternElement <em>For Each Out Pattern Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.ForEachOutPatternElement
	 * @generated
	 */
	public Adapter createForEachOutPatternElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Binding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Binding
	 * @generated
	 */
	public Adapter createBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.RuleVariableDeclaration <em>Rule Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.RuleVariableDeclaration
	 * @generated
	 */
	public Adapter createRuleVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.LibraryRef <em>Library Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.LibraryRef
	 * @generated
	 */
	public Adapter createLibraryRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.ActionBlock <em>Action Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.ActionBlock
	 * @generated
	 */
	public Adapter createActionBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.Statement
	 * @generated
	 */
	public Adapter createStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.ExpressionStat <em>Expression Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.ExpressionStat
	 * @generated
	 */
	public Adapter createExpressionStatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.BindingStat <em>Binding Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.BindingStat
	 * @generated
	 */
	public Adapter createBindingStatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.IfStat <em>If Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.IfStat
	 * @generated
	 */
	public Adapter createIfStatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.ForStat <em>For Stat</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.ForStat
	 * @generated
	 */
	public Adapter createForStatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToStringMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.CallableParameter <em>Callable Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.CallableParameter
	 * @generated
	 */
	public Adapter createCallableParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.ATL.RuleResolutionInfo <em>Rule Resolution Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.ATL.RuleResolutionInfo
	 * @generated
	 */
	public Adapter createRuleResolutionInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.OCL.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.OCL.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atlext.OCL.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atlext.OCL.VariableDeclaration
	 * @generated
	 */
	public Adapter createVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ATLAdapterFactory
