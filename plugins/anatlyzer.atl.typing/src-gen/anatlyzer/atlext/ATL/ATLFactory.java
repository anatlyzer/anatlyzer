/**
 */
package anatlyzer.atlext.ATL;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atlext.ATL.ATLPackage
 * @generated
 */
public interface ATLFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ATLFactory eINSTANCE = anatlyzer.atlext.ATL.impl.ATLFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unit</em>'.
	 * @generated
	 */
	Unit createUnit();

	/**
	 * Returns a new object of class '<em>Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Library</em>'.
	 * @generated
	 */
	Library createLibrary();

	/**
	 * Returns a new object of class '<em>Query</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query</em>'.
	 * @generated
	 */
	Query createQuery();

	/**
	 * Returns a new object of class '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module</em>'.
	 * @generated
	 */
	Module createModule();

	/**
	 * Returns a new object of class '<em>Static Helper</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Helper</em>'.
	 * @generated
	 */
	StaticHelper createStaticHelper();

	/**
	 * Returns a new object of class '<em>Context Helper</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Context Helper</em>'.
	 * @generated
	 */
	ContextHelper createContextHelper();

	/**
	 * Returns a new object of class '<em>Matched Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Matched Rule</em>'.
	 * @generated
	 */
	MatchedRule createMatchedRule();

	/**
	 * Returns a new object of class '<em>Lazy Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lazy Rule</em>'.
	 * @generated
	 */
	LazyRule createLazyRule();

	/**
	 * Returns a new object of class '<em>Called Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Called Rule</em>'.
	 * @generated
	 */
	CalledRule createCalledRule();

	/**
	 * Returns a new object of class '<em>In Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>In Pattern</em>'.
	 * @generated
	 */
	InPattern createInPattern();

	/**
	 * Returns a new object of class '<em>Out Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Out Pattern</em>'.
	 * @generated
	 */
	OutPattern createOutPattern();

	/**
	 * Returns a new object of class '<em>Simple In Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple In Pattern Element</em>'.
	 * @generated
	 */
	SimpleInPatternElement createSimpleInPatternElement();

	/**
	 * Returns a new object of class '<em>Simple Out Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Out Pattern Element</em>'.
	 * @generated
	 */
	SimpleOutPatternElement createSimpleOutPatternElement();

	/**
	 * Returns a new object of class '<em>For Each Out Pattern Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>For Each Out Pattern Element</em>'.
	 * @generated
	 */
	ForEachOutPatternElement createForEachOutPatternElement();

	/**
	 * Returns a new object of class '<em>Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding</em>'.
	 * @generated
	 */
	Binding createBinding();

	/**
	 * Returns a new object of class '<em>Rule Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule Variable Declaration</em>'.
	 * @generated
	 */
	RuleVariableDeclaration createRuleVariableDeclaration();

	/**
	 * Returns a new object of class '<em>Library Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Library Ref</em>'.
	 * @generated
	 */
	LibraryRef createLibraryRef();

	/**
	 * Returns a new object of class '<em>Action Block</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Block</em>'.
	 * @generated
	 */
	ActionBlock createActionBlock();

	/**
	 * Returns a new object of class '<em>Expression Stat</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expression Stat</em>'.
	 * @generated
	 */
	ExpressionStat createExpressionStat();

	/**
	 * Returns a new object of class '<em>Binding Stat</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Stat</em>'.
	 * @generated
	 */
	BindingStat createBindingStat();

	/**
	 * Returns a new object of class '<em>If Stat</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>If Stat</em>'.
	 * @generated
	 */
	IfStat createIfStat();

	/**
	 * Returns a new object of class '<em>For Stat</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>For Stat</em>'.
	 * @generated
	 */
	ForStat createForStat();

	/**
	 * Returns a new object of class '<em>Callable Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Callable Parameter</em>'.
	 * @generated
	 */
	CallableParameter createCallableParameter();

	/**
	 * Returns a new object of class '<em>Rule Resolution Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule Resolution Info</em>'.
	 * @generated
	 */
	RuleResolutionInfo createRuleResolutionInfo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ATLPackage getATLPackage();

} //ATLFactory
