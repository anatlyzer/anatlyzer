/**
 */
package anatlyzer.atl.errors.atl_error;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage
 * @generated
 */
public interface AtlErrorFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AtlErrorFactory eINSTANCE = anatlyzer.atl.errors.atl_error.impl.AtlErrorFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Element</em>'.
	 * @generated
	 */
	ModelElement createModelElement();

	/**
	 * Returns a new object of class '<em>Conflicting Rule Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conflicting Rule Set</em>'.
	 * @generated
	 */
	ConflictingRuleSet createConflictingRuleSet();

	/**
	 * Returns a new object of class '<em>Target Conformance Violations</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Target Conformance Violations</em>'.
	 * @generated
	 */
	TargetConformanceViolations createTargetConformanceViolations();

	/**
	 * Returns a new object of class '<em>Target Invariant Violation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Target Invariant Violation</em>'.
	 * @generated
	 */
	TargetInvariantViolation createTargetInvariantViolation();

	/**
	 * Returns a new object of class '<em>Collection Operation Over No Collection Error</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Operation Over No Collection Error</em>'.
	 * @generated
	 */
	CollectionOperationOverNoCollectionError createCollectionOperationOverNoCollectionError();

	/**
	 * Returns a new object of class '<em>Feature Access In Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Access In Collection</em>'.
	 * @generated
	 */
	FeatureAccessInCollection createFeatureAccessInCollection();

	/**
	 * Returns a new object of class '<em>Feature Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Not Found</em>'.
	 * @generated
	 */
	FeatureNotFound createFeatureNotFound();

	/**
	 * Returns a new object of class '<em>Feature Found In Subtype</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Found In Subtype</em>'.
	 * @generated
	 */
	FeatureFoundInSubtype createFeatureFoundInSubtype();

	/**
	 * Returns a new object of class '<em>Operation Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Not Found</em>'.
	 * @generated
	 */
	OperationNotFound createOperationNotFound();

	/**
	 * Returns a new object of class '<em>Operation Found In Subtype</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Found In Subtype</em>'.
	 * @generated
	 */
	OperationFoundInSubtype createOperationFoundInSubtype();

	/**
	 * Returns a new object of class '<em>Operation Call Invalid Number Of Parameters</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Call Invalid Number Of Parameters</em>'.
	 * @generated
	 */
	OperationCallInvalidNumberOfParameters createOperationCallInvalidNumberOfParameters();

	/**
	 * Returns a new object of class '<em>Operation Call Invalid Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Call Invalid Parameter</em>'.
	 * @generated
	 */
	OperationCallInvalidParameter createOperationCallInvalidParameter();

	/**
	 * Returns a new object of class '<em>Operation Not Found In This Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Not Found In This Module</em>'.
	 * @generated
	 */
	OperationNotFoundInThisModule createOperationNotFoundInThisModule();

	/**
	 * Returns a new object of class '<em>Attribute Not Found In This Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Not Found In This Module</em>'.
	 * @generated
	 */
	AttributeNotFoundInThisModule createAttributeNotFoundInThisModule();

	/**
	 * Returns a new object of class '<em>Feature Not Found In Union Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Not Found In Union Type</em>'.
	 * @generated
	 */
	FeatureNotFoundInUnionType createFeatureNotFoundInUnionType();

	/**
	 * Returns a new object of class '<em>Invalid Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Operator</em>'.
	 * @generated
	 */
	InvalidOperator createInvalidOperator();

	/**
	 * Returns a new object of class '<em>Invalid Operand</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Operand</em>'.
	 * @generated
	 */
	InvalidOperand createInvalidOperand();

	/**
	 * Returns a new object of class '<em>No Container For Ref Immediate Composite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Container For Ref Immediate Composite</em>'.
	 * @generated
	 */
	NoContainerForRefImmediateComposite createNoContainerForRefImmediateComposite();

	/**
	 * Returns a new object of class '<em>Different Branch Types</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Different Branch Types</em>'.
	 * @generated
	 */
	DifferentBranchTypes createDifferentBranchTypes();

	/**
	 * Returns a new object of class '<em>Assignment To Readonly Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assignment To Readonly Feature</em>'.
	 * @generated
	 */
	AssignmentToReadonlyFeature createAssignmentToReadonlyFeature();

	/**
	 * Returns a new object of class '<em>No Binding For Compulsory Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Binding For Compulsory Feature</em>'.
	 * @generated
	 */
	NoBindingForCompulsoryFeature createNoBindingForCompulsoryFeature();

	/**
	 * Returns a new object of class '<em>Invalid Assignment Imperative Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Assignment Imperative Binding</em>'.
	 * @generated
	 */
	InvalidAssignmentImperativeBinding createInvalidAssignmentImperativeBinding();

	/**
	 * Returns a new object of class '<em>Binding Expected One Assigned Many</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Expected One Assigned Many</em>'.
	 * @generated
	 */
	BindingExpectedOneAssignedMany createBindingExpectedOneAssignedMany();

	/**
	 * Returns a new object of class '<em>Primitive Binding But Object Assigned</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Binding But Object Assigned</em>'.
	 * @generated
	 */
	PrimitiveBindingButObjectAssigned createPrimitiveBindingButObjectAssigned();

	/**
	 * Returns a new object of class '<em>Object Binding But Primitive Assigned</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Object Binding But Primitive Assigned</em>'.
	 * @generated
	 */
	ObjectBindingButPrimitiveAssigned createObjectBindingButPrimitiveAssigned();

	/**
	 * Returns a new object of class '<em>Primitive Binding Invalid Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Binding Invalid Assignment</em>'.
	 * @generated
	 */
	PrimitiveBindingInvalidAssignment createPrimitiveBindingInvalidAssignment();

	/**
	 * Returns a new object of class '<em>Binding Without Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Without Rule</em>'.
	 * @generated
	 */
	BindingWithoutRule createBindingWithoutRule();

	/**
	 * Returns a new object of class '<em>Binding With Resolved By Incompatible Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding With Resolved By Incompatible Rule</em>'.
	 * @generated
	 */
	BindingWithResolvedByIncompatibleRule createBindingWithResolvedByIncompatibleRule();

	/**
	 * Returns a new object of class '<em>Binding Possibly Unresolved</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Possibly Unresolved</em>'.
	 * @generated
	 */
	BindingPossiblyUnresolved createBindingPossiblyUnresolved();

	/**
	 * Returns a new object of class '<em>Resolved Rule Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resolved Rule Info</em>'.
	 * @generated
	 */
	ResolvedRuleInfo createResolvedRuleInfo();

	/**
	 * Returns a new object of class '<em>Resolve Temp Without Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resolve Temp Without Rule</em>'.
	 * @generated
	 */
	ResolveTempWithoutRule createResolveTempWithoutRule();

	/**
	 * Returns a new object of class '<em>Resolve Temp Possibly Unresolved</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resolve Temp Possibly Unresolved</em>'.
	 * @generated
	 */
	ResolveTempPossiblyUnresolved createResolveTempPossiblyUnresolved();

	/**
	 * Returns a new object of class '<em>Resolve Temp Output Pattern Element Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resolve Temp Output Pattern Element Not Found</em>'.
	 * @generated
	 */
	ResolveTempOutputPatternElementNotFound createResolveTempOutputPatternElementNotFound();

	/**
	 * Returns a new object of class '<em>Flatten Over Non Nested Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Flatten Over Non Nested Collection</em>'.
	 * @generated
	 */
	FlattenOverNonNestedCollection createFlattenOverNonNestedCollection();

	/**
	 * Returns a new object of class '<em>Change Select First For Any</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Select First For Any</em>'.
	 * @generated
	 */
	ChangeSelectFirstForAny createChangeSelectFirstForAny();

	/**
	 * Returns a new object of class '<em>Iterator Over Empty Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator Over Empty Sequence</em>'.
	 * @generated
	 */
	IteratorOverEmptySequence createIteratorOverEmptySequence();

	/**
	 * Returns a new object of class '<em>Invalid Operator Usage</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Operator Usage</em>'.
	 * @generated
	 */
	InvalidOperatorUsage createInvalidOperatorUsage();

	/**
	 * Returns a new object of class '<em>Reading Target Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reading Target Model</em>'.
	 * @generated
	 */
	ReadingTargetModel createReadingTargetModel();

	/**
	 * Returns a new object of class '<em>Writing Source Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Writing Source Model</em>'.
	 * @generated
	 */
	WritingSourceModel createWritingSourceModel();

	/**
	 * Returns a new object of class '<em>Lazy Rule With Filter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lazy Rule With Filter</em>'.
	 * @generated
	 */
	LazyRuleWithFilter createLazyRuleWithFilter();

	/**
	 * Returns a new object of class '<em>Invalid Rule Inheritance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Rule Inheritance</em>'.
	 * @generated
	 */
	InvalidRuleInheritance createInvalidRuleInheritance();

	/**
	 * Returns a new object of class '<em>Ambiguous Target Model Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ambiguous Target Model Reference</em>'.
	 * @generated
	 */
	AmbiguousTargetModelReference createAmbiguousTargetModelReference();

	/**
	 * Returns a new object of class '<em>No Model Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Model Found</em>'.
	 * @generated
	 */
	NoModelFound createNoModelFound();

	/**
	 * Returns a new object of class '<em>No Enum Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Enum Literal</em>'.
	 * @generated
	 */
	NoEnumLiteral createNoEnumLiteral();

	/**
	 * Returns a new object of class '<em>Iterator Body Wrong Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator Body Wrong Type</em>'.
	 * @generated
	 */
	IteratorBodyWrongType createIteratorBodyWrongType();

	/**
	 * Returns a new object of class '<em>Matched Rule Without Output Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Matched Rule Without Output Pattern</em>'.
	 * @generated
	 */
	MatchedRuleWithoutOutputPattern createMatchedRuleWithoutOutputPattern();

	/**
	 * Returns a new object of class '<em>Matched Rule Filter Non Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Matched Rule Filter Non Boolean</em>'.
	 * @generated
	 */
	MatchedRuleFilterNonBoolean createMatchedRuleFilterNonBoolean();

	/**
	 * Returns a new object of class '<em>Expected Collection In For Each</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expected Collection In For Each</em>'.
	 * @generated
	 */
	ExpectedCollectionInForEach createExpectedCollectionInForEach();

	/**
	 * Returns a new object of class '<em>No Class Found In Metamodel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Class Found In Metamodel</em>'.
	 * @generated
	 */
	NoClassFoundInMetamodel createNoClassFoundInMetamodel();

	/**
	 * Returns a new object of class '<em>Invalid Argument</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invalid Argument</em>'.
	 * @generated
	 */
	InvalidArgument createInvalidArgument();

	/**
	 * Returns a new object of class '<em>Collection Operation Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Operation Not Found</em>'.
	 * @generated
	 */
	CollectionOperationNotFound createCollectionOperationNotFound();

	/**
	 * Returns a new object of class '<em>Iterator Over No Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator Over No Collection Type</em>'.
	 * @generated
	 */
	IteratorOverNoCollectionType createIteratorOverNoCollectionType();

	/**
	 * Returns a new object of class '<em>Incoherent Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Incoherent Variable Declaration</em>'.
	 * @generated
	 */
	IncoherentVariableDeclaration createIncoherentVariableDeclaration();

	/**
	 * Returns a new object of class '<em>Incoherent Helper Return Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Incoherent Helper Return Type</em>'.
	 * @generated
	 */
	IncoherentHelperReturnType createIncoherentHelperReturnType();

	/**
	 * Returns a new object of class '<em>Operation Over Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Over Collection Type</em>'.
	 * @generated
	 */
	OperationOverCollectionType createOperationOverCollectionType();

	/**
	 * Returns a new object of class '<em>Access To Undefined Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Access To Undefined Value</em>'.
	 * @generated
	 */
	AccessToUndefinedValue createAccessToUndefinedValue();

	/**
	 * Returns a new object of class '<em>Access To Undefined Value Through Empty Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Access To Undefined Value Through Empty Collection</em>'.
	 * @generated
	 */
	AccessToUndefinedValue_ThroughEmptyCollection createAccessToUndefinedValue_ThroughEmptyCollection();

	/**
	 * Returns a new object of class '<em>Rule Conflicts</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule Conflicts</em>'.
	 * @generated
	 */
	RuleConflicts createRuleConflicts();

	/**
	 * Returns a new object of class '<em>Binding Inplace Invalid</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Inplace Invalid</em>'.
	 * @generated
	 */
	BindingInplaceInvalid createBindingInplaceInvalid();

	/**
	 * Returns a new object of class '<em>Cannot Instantiate Abstract Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cannot Instantiate Abstract Class</em>'.
	 * @generated
	 */
	CannotInstantiateAbstractClass createCannotInstantiateAbstractClass();

	/**
	 * Returns a new object of class '<em>Generic Local Problem</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Local Problem</em>'.
	 * @generated
	 */
	GenericLocalProblem createGenericLocalProblem();

	/**
	 * Returns a new object of class '<em>Atl Parse Error</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Atl Parse Error</em>'.
	 * @generated
	 */
	AtlParseError createAtlParseError();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AtlErrorPackage getAtlErrorPackage();

} //AtlErrorFactory
