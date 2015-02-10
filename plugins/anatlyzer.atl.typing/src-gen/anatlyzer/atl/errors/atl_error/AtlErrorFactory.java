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
	 * Returns a new object of class '<em>Operation Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Not Found</em>'.
	 * @generated
	 */
	OperationNotFound createOperationNotFound();

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
	 * Returns a new object of class '<em>No Binding For Compulsory Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Binding For Compulsory Feature</em>'.
	 * @generated
	 */
	NoBindingForCompulsoryFeature createNoBindingForCompulsoryFeature();

	/**
	 * Returns a new object of class '<em>Binding Expected One Assigned Many</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Expected One Assigned Many</em>'.
	 * @generated
	 */
	BindingExpectedOneAssignedMany createBindingExpectedOneAssignedMany();

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
	 * Returns a new object of class '<em>Iterator Over Empty Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator Over Empty Sequence</em>'.
	 * @generated
	 */
	IteratorOverEmptySequence createIteratorOverEmptySequence();

	/**
	 * Returns a new object of class '<em>Reading Target Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reading Target Model</em>'.
	 * @generated
	 */
	ReadingTargetModel createReadingTargetModel();

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
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AtlErrorPackage getAtlErrorPackage();

} //AtlErrorFactory
