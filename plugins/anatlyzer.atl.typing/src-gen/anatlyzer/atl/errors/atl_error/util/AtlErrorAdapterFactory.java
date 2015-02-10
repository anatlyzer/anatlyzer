/**
 */
package anatlyzer.atl.errors.atl_error.util;

import anatlyzer.atl.errors.AnalysisInfo;
import anatlyzer.atl.errors.Problem;

import anatlyzer.atl.errors.atl_error.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.atl_error.AtlErrorPackage
 * @generated
 */
public class AtlErrorAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AtlErrorPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtlErrorAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AtlErrorPackage.eINSTANCE;
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
	protected AtlErrorSwitch<Adapter> modelSwitch =
		new AtlErrorSwitch<Adapter>() {
			@Override
			public Adapter caseLocalProblem(LocalProblem object) {
				return createLocalProblemAdapter();
			}
			@Override
			public Adapter caseModelElement(ModelElement object) {
				return createModelElementAdapter();
			}
			@Override
			public Adapter caseNavigationProblem(NavigationProblem object) {
				return createNavigationProblemAdapter();
			}
			@Override
			public Adapter caseInvalidArgumentProblem(InvalidArgumentProblem object) {
				return createInvalidArgumentProblemAdapter();
			}
			@Override
			public Adapter caseTargetModelConformanceProblem(TargetModelConformanceProblem object) {
				return createTargetModelConformanceProblemAdapter();
			}
			@Override
			public Adapter caseCollectionOperationOverNoCollectionError(CollectionOperationOverNoCollectionError object) {
				return createCollectionOperationOverNoCollectionErrorAdapter();
			}
			@Override
			public Adapter caseFeatureAccessInCollection(FeatureAccessInCollection object) {
				return createFeatureAccessInCollectionAdapter();
			}
			@Override
			public Adapter caseFeatureNotFound(FeatureNotFound object) {
				return createFeatureNotFoundAdapter();
			}
			@Override
			public Adapter caseOperationNotFound(OperationNotFound object) {
				return createOperationNotFoundAdapter();
			}
			@Override
			public Adapter caseOperationNotFoundInThisModule(OperationNotFoundInThisModule object) {
				return createOperationNotFoundInThisModuleAdapter();
			}
			@Override
			public Adapter caseAttributeNotFoundInThisModule(AttributeNotFoundInThisModule object) {
				return createAttributeNotFoundInThisModuleAdapter();
			}
			@Override
			public Adapter caseFeatureNotFoundInUnionType(FeatureNotFoundInUnionType object) {
				return createFeatureNotFoundInUnionTypeAdapter();
			}
			@Override
			public Adapter caseInvalidOperand(InvalidOperand object) {
				return createInvalidOperandAdapter();
			}
			@Override
			public Adapter caseNoContainerForRefImmediateComposite(NoContainerForRefImmediateComposite object) {
				return createNoContainerForRefImmediateCompositeAdapter();
			}
			@Override
			public Adapter caseDifferentBranchTypes(DifferentBranchTypes object) {
				return createDifferentBranchTypesAdapter();
			}
			@Override
			public Adapter caseBindingProblem(BindingProblem object) {
				return createBindingProblemAdapter();
			}
			@Override
			public Adapter caseResolveTempProblem(ResolveTempProblem object) {
				return createResolveTempProblemAdapter();
			}
			@Override
			public Adapter caseNoBindingForCompulsoryFeature(NoBindingForCompulsoryFeature object) {
				return createNoBindingForCompulsoryFeatureAdapter();
			}
			@Override
			public Adapter caseBindingExpectedOneAssignedMany(BindingExpectedOneAssignedMany object) {
				return createBindingExpectedOneAssignedManyAdapter();
			}
			@Override
			public Adapter caseBindingResolution(BindingResolution object) {
				return createBindingResolutionAdapter();
			}
			@Override
			public Adapter caseBindingWithoutRule(BindingWithoutRule object) {
				return createBindingWithoutRuleAdapter();
			}
			@Override
			public Adapter caseBindingWithResolvedByIncompatibleRule(BindingWithResolvedByIncompatibleRule object) {
				return createBindingWithResolvedByIncompatibleRuleAdapter();
			}
			@Override
			public Adapter caseBindingPossiblyUnresolved(BindingPossiblyUnresolved object) {
				return createBindingPossiblyUnresolvedAdapter();
			}
			@Override
			public Adapter caseResolvedRuleInfo(ResolvedRuleInfo object) {
				return createResolvedRuleInfoAdapter();
			}
			@Override
			public Adapter caseResolveTempWithoutRule(ResolveTempWithoutRule object) {
				return createResolveTempWithoutRuleAdapter();
			}
			@Override
			public Adapter caseResolveTempOutputPatternElementNotFound(ResolveTempOutputPatternElementNotFound object) {
				return createResolveTempOutputPatternElementNotFoundAdapter();
			}
			@Override
			public Adapter caseFlattenOverNonNestedCollection(FlattenOverNonNestedCollection object) {
				return createFlattenOverNonNestedCollectionAdapter();
			}
			@Override
			public Adapter caseIteratorOverEmptySequence(IteratorOverEmptySequence object) {
				return createIteratorOverEmptySequenceAdapter();
			}
			@Override
			public Adapter caseReadingTargetModel(ReadingTargetModel object) {
				return createReadingTargetModelAdapter();
			}
			@Override
			public Adapter caseAmbiguousTargetModelReference(AmbiguousTargetModelReference object) {
				return createAmbiguousTargetModelReferenceAdapter();
			}
			@Override
			public Adapter caseNoModelFound(NoModelFound object) {
				return createNoModelFoundAdapter();
			}
			@Override
			public Adapter caseWrongType(WrongType object) {
				return createWrongTypeAdapter();
			}
			@Override
			public Adapter caseIteratorBodyWrongType(IteratorBodyWrongType object) {
				return createIteratorBodyWrongTypeAdapter();
			}
			@Override
			public Adapter caseMatchedRuleWithoutOutputPattern(MatchedRuleWithoutOutputPattern object) {
				return createMatchedRuleWithoutOutputPatternAdapter();
			}
			@Override
			public Adapter caseExpectedCollectionInForEach(ExpectedCollectionInForEach object) {
				return createExpectedCollectionInForEachAdapter();
			}
			@Override
			public Adapter caseNoClassFoundInMetamodel(NoClassFoundInMetamodel object) {
				return createNoClassFoundInMetamodelAdapter();
			}
			@Override
			public Adapter caseInvalidArgument(InvalidArgument object) {
				return createInvalidArgumentAdapter();
			}
			@Override
			public Adapter caseCollectionOperationNotFound(CollectionOperationNotFound object) {
				return createCollectionOperationNotFoundAdapter();
			}
			@Override
			public Adapter caseIteratorOverNoCollectionType(IteratorOverNoCollectionType object) {
				return createIteratorOverNoCollectionTypeAdapter();
			}
			@Override
			public Adapter caseOclCompliance(OclCompliance object) {
				return createOclComplianceAdapter();
			}
			@Override
			public Adapter caseIncoherentVariableDeclaration(IncoherentVariableDeclaration object) {
				return createIncoherentVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseAnalysisInfo(AnalysisInfo object) {
				return createAnalysisInfoAdapter();
			}
			@Override
			public Adapter caseProblem(Problem object) {
				return createProblemAdapter();
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
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.LocalProblem <em>Local Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.LocalProblem
	 * @generated
	 */
	public Adapter createLocalProblemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.ModelElement
	 * @generated
	 */
	public Adapter createModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.NavigationProblem <em>Navigation Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.NavigationProblem
	 * @generated
	 */
	public Adapter createNavigationProblemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.InvalidArgumentProblem <em>Invalid Argument Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.InvalidArgumentProblem
	 * @generated
	 */
	public Adapter createInvalidArgumentProblemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.TargetModelConformanceProblem <em>Target Model Conformance Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.TargetModelConformanceProblem
	 * @generated
	 */
	public Adapter createTargetModelConformanceProblemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError <em>Collection Operation Over No Collection Error</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError
	 * @generated
	 */
	public Adapter createCollectionOperationOverNoCollectionErrorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.FeatureAccessInCollection <em>Feature Access In Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.FeatureAccessInCollection
	 * @generated
	 */
	public Adapter createFeatureAccessInCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.FeatureNotFound <em>Feature Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFound
	 * @generated
	 */
	public Adapter createFeatureNotFoundAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.OperationNotFound <em>Operation Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.OperationNotFound
	 * @generated
	 */
	public Adapter createOperationNotFoundAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule <em>Operation Not Found In This Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule
	 * @generated
	 */
	public Adapter createOperationNotFoundInThisModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule <em>Attribute Not Found In This Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule
	 * @generated
	 */
	public Adapter createAttributeNotFoundInThisModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType <em>Feature Not Found In Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType
	 * @generated
	 */
	public Adapter createFeatureNotFoundInUnionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.InvalidOperand <em>Invalid Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.InvalidOperand
	 * @generated
	 */
	public Adapter createInvalidOperandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite <em>No Container For Ref Immediate Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite
	 * @generated
	 */
	public Adapter createNoContainerForRefImmediateCompositeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.DifferentBranchTypes <em>Different Branch Types</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.DifferentBranchTypes
	 * @generated
	 */
	public Adapter createDifferentBranchTypesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.BindingProblem <em>Binding Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.BindingProblem
	 * @generated
	 */
	public Adapter createBindingProblemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.ResolveTempProblem <em>Resolve Temp Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempProblem
	 * @generated
	 */
	public Adapter createResolveTempProblemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature <em>No Binding For Compulsory Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature
	 * @generated
	 */
	public Adapter createNoBindingForCompulsoryFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany <em>Binding Expected One Assigned Many</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany
	 * @generated
	 */
	public Adapter createBindingExpectedOneAssignedManyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.BindingResolution <em>Binding Resolution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.BindingResolution
	 * @generated
	 */
	public Adapter createBindingResolutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.BindingWithoutRule <em>Binding Without Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.BindingWithoutRule
	 * @generated
	 */
	public Adapter createBindingWithoutRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule <em>Binding With Resolved By Incompatible Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule
	 * @generated
	 */
	public Adapter createBindingWithResolvedByIncompatibleRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved <em>Binding Possibly Unresolved</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved
	 * @generated
	 */
	public Adapter createBindingPossiblyUnresolvedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.ResolvedRuleInfo <em>Resolved Rule Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.ResolvedRuleInfo
	 * @generated
	 */
	public Adapter createResolvedRuleInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule <em>Resolve Temp Without Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule
	 * @generated
	 */
	public Adapter createResolveTempWithoutRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound <em>Resolve Temp Output Pattern Element Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound
	 * @generated
	 */
	public Adapter createResolveTempOutputPatternElementNotFoundAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection <em>Flatten Over Non Nested Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection
	 * @generated
	 */
	public Adapter createFlattenOverNonNestedCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.IteratorOverEmptySequence <em>Iterator Over Empty Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.IteratorOverEmptySequence
	 * @generated
	 */
	public Adapter createIteratorOverEmptySequenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.ReadingTargetModel <em>Reading Target Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.ReadingTargetModel
	 * @generated
	 */
	public Adapter createReadingTargetModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference <em>Ambiguous Target Model Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference
	 * @generated
	 */
	public Adapter createAmbiguousTargetModelReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.NoModelFound <em>No Model Found</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.NoModelFound
	 * @generated
	 */
	public Adapter createNoModelFoundAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.WrongType <em>Wrong Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.WrongType
	 * @generated
	 */
	public Adapter createWrongTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.IteratorBodyWrongType <em>Iterator Body Wrong Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.IteratorBodyWrongType
	 * @generated
	 */
	public Adapter createIteratorBodyWrongTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.MatchedRuleWithoutOutputPattern <em>Matched Rule Without Output Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.MatchedRuleWithoutOutputPattern
	 * @generated
	 */
	public Adapter createMatchedRuleWithoutOutputPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.ExpectedCollectionInForEach <em>Expected Collection In For Each</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.ExpectedCollectionInForEach
	 * @generated
	 */
	public Adapter createExpectedCollectionInForEachAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel <em>No Class Found In Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel
	 * @generated
	 */
	public Adapter createNoClassFoundInMetamodelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.InvalidArgument <em>Invalid Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.InvalidArgument
	 * @generated
	 */
	public Adapter createInvalidArgumentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.CollectionOperationNotFound <em>Collection Operation Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.CollectionOperationNotFound
	 * @generated
	 */
	public Adapter createCollectionOperationNotFoundAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType <em>Iterator Over No Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType
	 * @generated
	 */
	public Adapter createIteratorOverNoCollectionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.OclCompliance <em>Ocl Compliance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.OclCompliance
	 * @generated
	 */
	public Adapter createOclComplianceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration <em>Incoherent Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration
	 * @generated
	 */
	public Adapter createIncoherentVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.AnalysisInfo <em>Analysis Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.AnalysisInfo
	 * @generated
	 */
	public Adapter createAnalysisInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.atl.errors.Problem <em>Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.atl.errors.Problem
	 * @generated
	 */
	public Adapter createProblemAdapter() {
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

} //AtlErrorAdapterFactory
