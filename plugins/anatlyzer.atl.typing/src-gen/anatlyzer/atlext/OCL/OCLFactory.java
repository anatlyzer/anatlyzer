/**
 */
package anatlyzer.atlext.OCL;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atlext.OCL.OCLPackage
 * @generated
 */
public interface OCLFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OCLFactory eINSTANCE = anatlyzer.atlext.OCL.impl.OCLFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Variable Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Exp</em>'.
	 * @generated
	 */
	VariableExp createVariableExp();

	/**
	 * Returns a new object of class '<em>Super Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Super Exp</em>'.
	 * @generated
	 */
	SuperExp createSuperExp();

	/**
	 * Returns a new object of class '<em>String Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Exp</em>'.
	 * @generated
	 */
	StringExp createStringExp();

	/**
	 * Returns a new object of class '<em>Boolean Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Exp</em>'.
	 * @generated
	 */
	BooleanExp createBooleanExp();

	/**
	 * Returns a new object of class '<em>Real Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Real Exp</em>'.
	 * @generated
	 */
	RealExp createRealExp();

	/**
	 * Returns a new object of class '<em>Integer Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Exp</em>'.
	 * @generated
	 */
	IntegerExp createIntegerExp();

	/**
	 * Returns a new object of class '<em>Bag Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bag Exp</em>'.
	 * @generated
	 */
	BagExp createBagExp();

	/**
	 * Returns a new object of class '<em>Ordered Set Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ordered Set Exp</em>'.
	 * @generated
	 */
	OrderedSetExp createOrderedSetExp();

	/**
	 * Returns a new object of class '<em>Sequence Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence Exp</em>'.
	 * @generated
	 */
	SequenceExp createSequenceExp();

	/**
	 * Returns a new object of class '<em>Set Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Exp</em>'.
	 * @generated
	 */
	SetExp createSetExp();

	/**
	 * Returns a new object of class '<em>Tuple Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Exp</em>'.
	 * @generated
	 */
	TupleExp createTupleExp();

	/**
	 * Returns a new object of class '<em>Tuple Part</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Part</em>'.
	 * @generated
	 */
	TuplePart createTuplePart();

	/**
	 * Returns a new object of class '<em>Map Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map Exp</em>'.
	 * @generated
	 */
	MapExp createMapExp();

	/**
	 * Returns a new object of class '<em>Map Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map Element</em>'.
	 * @generated
	 */
	MapElement createMapElement();

	/**
	 * Returns a new object of class '<em>Enum Literal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enum Literal Exp</em>'.
	 * @generated
	 */
	EnumLiteralExp createEnumLiteralExp();

	/**
	 * Returns a new object of class '<em>Ocl Undefined Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Undefined Exp</em>'.
	 * @generated
	 */
	OclUndefinedExp createOclUndefinedExp();

	/**
	 * Returns a new object of class '<em>Navigation Or Attribute Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation Or Attribute Call Exp</em>'.
	 * @generated
	 */
	NavigationOrAttributeCallExp createNavigationOrAttributeCallExp();

	/**
	 * Returns a new object of class '<em>Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Call Exp</em>'.
	 * @generated
	 */
	OperationCallExp createOperationCallExp();

	/**
	 * Returns a new object of class '<em>Operator Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operator Call Exp</em>'.
	 * @generated
	 */
	OperatorCallExp createOperatorCallExp();

	/**
	 * Returns a new object of class '<em>Collection Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Operation Call Exp</em>'.
	 * @generated
	 */
	CollectionOperationCallExp createCollectionOperationCallExp();

	/**
	 * Returns a new object of class '<em>Iterate Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterate Exp</em>'.
	 * @generated
	 */
	IterateExp createIterateExp();

	/**
	 * Returns a new object of class '<em>Iterator Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator Exp</em>'.
	 * @generated
	 */
	IteratorExp createIteratorExp();

	/**
	 * Returns a new object of class '<em>Let Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Let Exp</em>'.
	 * @generated
	 */
	LetExp createLetExp();

	/**
	 * Returns a new object of class '<em>If Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>If Exp</em>'.
	 * @generated
	 */
	IfExp createIfExp();

	/**
	 * Returns a new object of class '<em>Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Declaration</em>'.
	 * @generated
	 */
	VariableDeclaration createVariableDeclaration();

	/**
	 * Returns a new object of class '<em>Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iterator</em>'.
	 * @generated
	 */
	Iterator createIterator();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Type</em>'.
	 * @generated
	 */
	CollectionType createCollectionType();

	/**
	 * Returns a new object of class '<em>Ocl Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Type</em>'.
	 * @generated
	 */
	OclType createOclType();

	/**
	 * Returns a new object of class '<em>String Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Type</em>'.
	 * @generated
	 */
	StringType createStringType();

	/**
	 * Returns a new object of class '<em>Boolean Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Type</em>'.
	 * @generated
	 */
	BooleanType createBooleanType();

	/**
	 * Returns a new object of class '<em>Integer Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Type</em>'.
	 * @generated
	 */
	IntegerType createIntegerType();

	/**
	 * Returns a new object of class '<em>Real Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Real Type</em>'.
	 * @generated
	 */
	RealType createRealType();

	/**
	 * Returns a new object of class '<em>Bag Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bag Type</em>'.
	 * @generated
	 */
	BagType createBagType();

	/**
	 * Returns a new object of class '<em>Ordered Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ordered Set Type</em>'.
	 * @generated
	 */
	OrderedSetType createOrderedSetType();

	/**
	 * Returns a new object of class '<em>Sequence Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence Type</em>'.
	 * @generated
	 */
	SequenceType createSequenceType();

	/**
	 * Returns a new object of class '<em>Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Type</em>'.
	 * @generated
	 */
	SetType createSetType();

	/**
	 * Returns a new object of class '<em>Ocl Any Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Any Type</em>'.
	 * @generated
	 */
	OclAnyType createOclAnyType();

	/**
	 * Returns a new object of class '<em>Tuple Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Type</em>'.
	 * @generated
	 */
	TupleType createTupleType();

	/**
	 * Returns a new object of class '<em>Tuple Type Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple Type Attribute</em>'.
	 * @generated
	 */
	TupleTypeAttribute createTupleTypeAttribute();

	/**
	 * Returns a new object of class '<em>Ocl Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Model Element</em>'.
	 * @generated
	 */
	OclModelElement createOclModelElement();

	/**
	 * Returns a new object of class '<em>Map Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map Type</em>'.
	 * @generated
	 */
	MapType createMapType();

	/**
	 * Returns a new object of class '<em>Ocl Feature Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Feature Definition</em>'.
	 * @generated
	 */
	OclFeatureDefinition createOclFeatureDefinition();

	/**
	 * Returns a new object of class '<em>Ocl Context Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Context Definition</em>'.
	 * @generated
	 */
	OclContextDefinition createOclContextDefinition();

	/**
	 * Returns a new object of class '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute</em>'.
	 * @generated
	 */
	Attribute createAttribute();

	/**
	 * Returns a new object of class '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation</em>'.
	 * @generated
	 */
	Operation createOperation();

	/**
	 * Returns a new object of class '<em>Ocl Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ocl Model</em>'.
	 * @generated
	 */
	OclModel createOclModel();

	/**
	 * Returns a new object of class '<em>Resolve Temp Resolution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resolve Temp Resolution</em>'.
	 * @generated
	 */
	ResolveTempResolution createResolveTempResolution();

	/**
	 * Returns a new object of class '<em>Java Body</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Java Body</em>'.
	 * @generated
	 */
	JavaBody createJavaBody();

	/**
	 * Returns a new object of class '<em>Get Applied Stereotypes Body</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Applied Stereotypes Body</em>'.
	 * @generated
	 */
	GetAppliedStereotypesBody createGetAppliedStereotypesBody();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OCLPackage getOCLPackage();

} //OCLFactory
