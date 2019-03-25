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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see anatlyzer.atlext.ATL.ATLPackage
 * @generated
 */
public class ATLSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ATLPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATLSwitch() {
		if (modelPackage == null) {
			modelPackage = ATLPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ATLPackage.LOCATED_ELEMENT: {
				LocatedElement locatedElement = (LocatedElement)theEObject;
				T result = caseLocatedElement(locatedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.UNIT: {
				Unit unit = (Unit)theEObject;
				T result = caseUnit(unit);
				if (result == null) result = caseLocatedElement(unit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.LIBRARY: {
				Library library = (Library)theEObject;
				T result = caseLibrary(library);
				if (result == null) result = caseUnit(library);
				if (result == null) result = caseLocatedElement(library);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.QUERY: {
				Query query = (Query)theEObject;
				T result = caseQuery(query);
				if (result == null) result = caseUnit(query);
				if (result == null) result = caseLocatedElement(query);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.MODULE: {
				anatlyzer.atlext.ATL.Module module = (anatlyzer.atlext.ATL.Module)theEObject;
				T result = caseModule(module);
				if (result == null) result = caseUnit(module);
				if (result == null) result = caseLocatedElement(module);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.MODULE_ELEMENT: {
				ModuleElement moduleElement = (ModuleElement)theEObject;
				T result = caseModuleElement(moduleElement);
				if (result == null) result = caseLocatedElement(moduleElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.HELPER: {
				Helper helper = (Helper)theEObject;
				T result = caseHelper(helper);
				if (result == null) result = caseModuleElement(helper);
				if (result == null) result = caseCallable(helper);
				if (result == null) result = caseLocatedElement(helper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.STATIC_HELPER: {
				StaticHelper staticHelper = (StaticHelper)theEObject;
				T result = caseStaticHelper(staticHelper);
				if (result == null) result = caseHelper(staticHelper);
				if (result == null) result = caseModuleCallable(staticHelper);
				if (result == null) result = caseModuleElement(staticHelper);
				if (result == null) result = caseCallable(staticHelper);
				if (result == null) result = caseLocatedElement(staticHelper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.CONTEXT_HELPER: {
				ContextHelper contextHelper = (ContextHelper)theEObject;
				T result = caseContextHelper(contextHelper);
				if (result == null) result = caseHelper(contextHelper);
				if (result == null) result = caseModuleElement(contextHelper);
				if (result == null) result = caseCallable(contextHelper);
				if (result == null) result = caseLocatedElement(contextHelper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.RULE: {
				Rule rule = (Rule)theEObject;
				T result = caseRule(rule);
				if (result == null) result = caseModuleElement(rule);
				if (result == null) result = caseLocatedElement(rule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.STATIC_RULE: {
				StaticRule staticRule = (StaticRule)theEObject;
				T result = caseStaticRule(staticRule);
				if (result == null) result = caseModuleCallable(staticRule);
				if (result == null) result = caseRule(staticRule);
				if (result == null) result = caseCallable(staticRule);
				if (result == null) result = caseModuleElement(staticRule);
				if (result == null) result = caseLocatedElement(staticRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.MODULE_CALLABLE: {
				ModuleCallable moduleCallable = (ModuleCallable)theEObject;
				T result = caseModuleCallable(moduleCallable);
				if (result == null) result = caseCallable(moduleCallable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.CALLABLE: {
				Callable callable = (Callable)theEObject;
				T result = caseCallable(callable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.RULE_WITH_PATTERN: {
				RuleWithPattern ruleWithPattern = (RuleWithPattern)theEObject;
				T result = caseRuleWithPattern(ruleWithPattern);
				if (result == null) result = caseRule(ruleWithPattern);
				if (result == null) result = caseModuleElement(ruleWithPattern);
				if (result == null) result = caseLocatedElement(ruleWithPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.MATCHED_RULE: {
				MatchedRule matchedRule = (MatchedRule)theEObject;
				T result = caseMatchedRule(matchedRule);
				if (result == null) result = caseRuleWithPattern(matchedRule);
				if (result == null) result = caseRule(matchedRule);
				if (result == null) result = caseModuleElement(matchedRule);
				if (result == null) result = caseLocatedElement(matchedRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.LAZY_RULE: {
				LazyRule lazyRule = (LazyRule)theEObject;
				T result = caseLazyRule(lazyRule);
				if (result == null) result = caseRuleWithPattern(lazyRule);
				if (result == null) result = caseStaticRule(lazyRule);
				if (result == null) result = caseRule(lazyRule);
				if (result == null) result = caseModuleCallable(lazyRule);
				if (result == null) result = caseModuleElement(lazyRule);
				if (result == null) result = caseCallable(lazyRule);
				if (result == null) result = caseLocatedElement(lazyRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.CALLED_RULE: {
				CalledRule calledRule = (CalledRule)theEObject;
				T result = caseCalledRule(calledRule);
				if (result == null) result = caseStaticRule(calledRule);
				if (result == null) result = caseModuleCallable(calledRule);
				if (result == null) result = caseRule(calledRule);
				if (result == null) result = caseCallable(calledRule);
				if (result == null) result = caseModuleElement(calledRule);
				if (result == null) result = caseLocatedElement(calledRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.IN_PATTERN: {
				InPattern inPattern = (InPattern)theEObject;
				T result = caseInPattern(inPattern);
				if (result == null) result = caseLocatedElement(inPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.OUT_PATTERN: {
				OutPattern outPattern = (OutPattern)theEObject;
				T result = caseOutPattern(outPattern);
				if (result == null) result = caseLocatedElement(outPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.DROP_PATTERN: {
				DropPattern dropPattern = (DropPattern)theEObject;
				T result = caseDropPattern(dropPattern);
				if (result == null) result = caseLocatedElement(dropPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.PATTERN_ELEMENT: {
				PatternElement patternElement = (PatternElement)theEObject;
				T result = casePatternElement(patternElement);
				if (result == null) result = caseVariableDeclaration(patternElement);
				if (result == null) result = caseLocatedElement(patternElement);
				if (result == null) result = caseTypedElement(patternElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.IN_PATTERN_ELEMENT: {
				InPatternElement inPatternElement = (InPatternElement)theEObject;
				T result = caseInPatternElement(inPatternElement);
				if (result == null) result = casePatternElement(inPatternElement);
				if (result == null) result = caseVariableDeclaration(inPatternElement);
				if (result == null) result = caseLocatedElement(inPatternElement);
				if (result == null) result = caseTypedElement(inPatternElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.SIMPLE_IN_PATTERN_ELEMENT: {
				SimpleInPatternElement simpleInPatternElement = (SimpleInPatternElement)theEObject;
				T result = caseSimpleInPatternElement(simpleInPatternElement);
				if (result == null) result = caseInPatternElement(simpleInPatternElement);
				if (result == null) result = casePatternElement(simpleInPatternElement);
				if (result == null) result = caseVariableDeclaration(simpleInPatternElement);
				if (result == null) result = caseLocatedElement(simpleInPatternElement);
				if (result == null) result = caseTypedElement(simpleInPatternElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.OUT_PATTERN_ELEMENT: {
				OutPatternElement outPatternElement = (OutPatternElement)theEObject;
				T result = caseOutPatternElement(outPatternElement);
				if (result == null) result = casePatternElement(outPatternElement);
				if (result == null) result = caseVariableDeclaration(outPatternElement);
				if (result == null) result = caseLocatedElement(outPatternElement);
				if (result == null) result = caseTypedElement(outPatternElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.SIMPLE_OUT_PATTERN_ELEMENT: {
				SimpleOutPatternElement simpleOutPatternElement = (SimpleOutPatternElement)theEObject;
				T result = caseSimpleOutPatternElement(simpleOutPatternElement);
				if (result == null) result = caseOutPatternElement(simpleOutPatternElement);
				if (result == null) result = casePatternElement(simpleOutPatternElement);
				if (result == null) result = caseVariableDeclaration(simpleOutPatternElement);
				if (result == null) result = caseLocatedElement(simpleOutPatternElement);
				if (result == null) result = caseTypedElement(simpleOutPatternElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.FOR_EACH_OUT_PATTERN_ELEMENT: {
				ForEachOutPatternElement forEachOutPatternElement = (ForEachOutPatternElement)theEObject;
				T result = caseForEachOutPatternElement(forEachOutPatternElement);
				if (result == null) result = caseOutPatternElement(forEachOutPatternElement);
				if (result == null) result = casePatternElement(forEachOutPatternElement);
				if (result == null) result = caseVariableDeclaration(forEachOutPatternElement);
				if (result == null) result = caseLocatedElement(forEachOutPatternElement);
				if (result == null) result = caseTypedElement(forEachOutPatternElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.BINDING: {
				Binding binding = (Binding)theEObject;
				T result = caseBinding(binding);
				if (result == null) result = caseLocatedElement(binding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.RULE_VARIABLE_DECLARATION: {
				RuleVariableDeclaration ruleVariableDeclaration = (RuleVariableDeclaration)theEObject;
				T result = caseRuleVariableDeclaration(ruleVariableDeclaration);
				if (result == null) result = caseVariableDeclaration(ruleVariableDeclaration);
				if (result == null) result = caseLocatedElement(ruleVariableDeclaration);
				if (result == null) result = caseTypedElement(ruleVariableDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.LIBRARY_REF: {
				LibraryRef libraryRef = (LibraryRef)theEObject;
				T result = caseLibraryRef(libraryRef);
				if (result == null) result = caseLocatedElement(libraryRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.ACTION_BLOCK: {
				ActionBlock actionBlock = (ActionBlock)theEObject;
				T result = caseActionBlock(actionBlock);
				if (result == null) result = caseLocatedElement(actionBlock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.STATEMENT: {
				Statement statement = (Statement)theEObject;
				T result = caseStatement(statement);
				if (result == null) result = caseLocatedElement(statement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.EXPRESSION_STAT: {
				ExpressionStat expressionStat = (ExpressionStat)theEObject;
				T result = caseExpressionStat(expressionStat);
				if (result == null) result = caseStatement(expressionStat);
				if (result == null) result = caseLocatedElement(expressionStat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.BINDING_STAT: {
				BindingStat bindingStat = (BindingStat)theEObject;
				T result = caseBindingStat(bindingStat);
				if (result == null) result = caseStatement(bindingStat);
				if (result == null) result = caseLocatedElement(bindingStat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.IF_STAT: {
				IfStat ifStat = (IfStat)theEObject;
				T result = caseIfStat(ifStat);
				if (result == null) result = caseStatement(ifStat);
				if (result == null) result = caseLocatedElement(ifStat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.FOR_STAT: {
				ForStat forStat = (ForStat)theEObject;
				T result = caseForStat(forStat);
				if (result == null) result = caseStatement(forStat);
				if (result == null) result = caseLocatedElement(forStat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.STRING_TO_STRING_MAP: {
				@SuppressWarnings("unchecked") Map.Entry<String, String> stringToStringMap = (Map.Entry<String, String>)theEObject;
				T result = caseStringToStringMap(stringToStringMap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.CALLABLE_PARAMETER: {
				CallableParameter callableParameter = (CallableParameter)theEObject;
				T result = caseCallableParameter(callableParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ATLPackage.RULE_RESOLUTION_INFO: {
				RuleResolutionInfo ruleResolutionInfo = (RuleResolutionInfo)theEObject;
				T result = caseRuleResolutionInfo(ruleResolutionInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Located Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Located Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocatedElement(LocatedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnit(Unit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Library</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Library</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLibrary(Library object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuery(Query object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModule(anatlyzer.atlext.ATL.Module object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModuleElement(ModuleElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Helper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Helper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHelper(Helper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Helper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Helper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticHelper(StaticHelper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Context Helper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context Helper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContextHelper(ContextHelper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRule(Rule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticRule(StaticRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Callable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Callable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModuleCallable(ModuleCallable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Callable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Callable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallable(Callable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule With Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule With Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuleWithPattern(RuleWithPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matched Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matched Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatchedRule(MatchedRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lazy Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lazy Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLazyRule(LazyRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Called Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Called Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCalledRule(CalledRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>In Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>In Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInPattern(InPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Out Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Out Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutPattern(OutPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Drop Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Drop Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDropPattern(DropPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pattern Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePatternElement(PatternElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>In Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>In Pattern Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInPatternElement(InPatternElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple In Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple In Pattern Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimpleInPatternElement(SimpleInPatternElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Out Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Out Pattern Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutPatternElement(OutPatternElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Out Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Out Pattern Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimpleOutPatternElement(SimpleOutPatternElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>For Each Out Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>For Each Out Pattern Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseForEachOutPatternElement(ForEachOutPatternElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBinding(Binding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule Variable Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuleVariableDeclaration(RuleVariableDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Library Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Library Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLibraryRef(LibraryRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionBlock(ActionBlock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatement(Statement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression Stat</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression Stat</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpressionStat(ExpressionStat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Stat</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Stat</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingStat(BindingStat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>If Stat</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>If Stat</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIfStat(IfStat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>For Stat</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>For Stat</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseForStat(ForStat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To String Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To String Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToStringMap(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Callable Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Callable Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallableParameter(CallableParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule Resolution Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule Resolution Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuleResolutionInfo(RuleResolutionInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedElement(TypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableDeclaration(VariableDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //ATLSwitch
