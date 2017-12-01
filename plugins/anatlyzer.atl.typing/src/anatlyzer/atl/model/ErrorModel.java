package anatlyzer.atl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analyser.namespaces.TypeErrorNamespace;
import anatlyzer.atl.analyser.recovery.IRecoveryAction;
import anatlyzer.atl.errors.AnalysisResult;
import anatlyzer.atl.errors.AnalysisResultFactory;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.Recovery;
import anatlyzer.atl.errors.SeverityKind;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection;
import anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference;
import anatlyzer.atl.errors.atl_error.AssignmentToReadonlyFeature;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.AtlParseError;
import anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingInplaceInvalid;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass;
import anatlyzer.atl.errors.atl_error.ChangeSelectFirstForAny;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.DifferentBranchTypes;
import anatlyzer.atl.errors.atl_error.ExpectedCollectionInForEach;
import anatlyzer.atl.errors.atl_error.FeatureAccessInCollection;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atl.errors.atl_error.GenericLocalProblem;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.InvalidArgument;
import anatlyzer.atl.errors.atl_error.InvalidAssignmentImperativeBinding;
import anatlyzer.atl.errors.atl_error.InvalidOperand;
import anatlyzer.atl.errors.atl_error.InvalidOperator;
import anatlyzer.atl.errors.atl_error.InvalidOperatorUsage;
import anatlyzer.atl.errors.atl_error.InvalidRuleInheritance;
import anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind;
import anatlyzer.atl.errors.atl_error.IteratorBodyWrongType;
import anatlyzer.atl.errors.atl_error.IteratorOverEmptySequence;
import anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType;
import anatlyzer.atl.errors.atl_error.LazyRuleWithFilter;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.MatchedRuleFilterNonBoolean;
import anatlyzer.atl.errors.atl_error.MatchedRuleWithoutOutputPattern;
import anatlyzer.atl.errors.atl_error.ModelElement;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite;
import anatlyzer.atl.errors.atl_error.NoEnumLiteral;
import anatlyzer.atl.errors.atl_error.NoModelFound;
import anatlyzer.atl.errors.atl_error.ObjectBindingButPrimitiveAssigned;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingButObjectAssigned;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment;
import anatlyzer.atl.errors.atl_error.ReadingTargetModel;
import anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound;
import anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.errors.atl_error.WritingSourceModel;
import anatlyzer.atl.errors.atl_recovery.AtlRecoveryFactory;
import anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.ForStat;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;

/**
 * 
 * Implementation note: internal methods like signalError and initProblems
 * are declared as public to allow extensions to easily contribute new errors.
 * 
 * @author jesus
 *
 */
public class ErrorModel {
	
	protected Resource resource;
	protected AnalysisResult	result;
	protected List<Problem> discardedProblems = new ArrayList<Problem>();
		
	public ErrorModel(Resource resource) {
		this.resource = resource;
		for (EObject eObject : resource.getContents()) {
			if ( eObject instanceof AnalysisResult ) {
				result = (AnalysisResult) eObject;
				break;
			}
		}
		
		if ( result == null ) {
			result = AnalysisResultFactory.eINSTANCE.createAnalysisResult();
			this.resource.getContents().add(result);
		}
	}

	public AnalysisResult getAnalysis() {
		return result;
	}

	public List<Problem> getProblems() {
		return new ArrayList<Problem>(result.getProblems());
	}

	public void removeRuleConflicts() {
		this.result.getBatchAnalysis().removeIf(b -> b instanceof RuleConflicts);
		this.result.getProblems().removeIf(p -> p instanceof ConflictingRuleSet);
	}

	public void addRuleConflicts(RuleConflicts rc) {
		this.result.getBatchAnalysis().add(rc);
		this.result.getProblems().addAll(rc.getConflicts());
	}

	
	public List<LocalProblem> getLocalProblems() {
		ArrayList<LocalProblem> locals = new ArrayList<LocalProblem>();
		for (Problem p : getProblems()) {
			if ( p instanceof LocalProblem ) {
				locals.add((LocalProblem) p);
			}
		}
		return locals;
	}
	
	public void signalNoModel(String name, LocatedElement element) {
		NoModelFound error = AtlErrorFactory.eINSTANCE.createNoModelFound();
		initProblem(error, element);
		
		signalError(error, "Invalid meta-model: " + name, element);
	}

	public Type signalNoClass(String name, MetamodelNamespace mmspace, LocatedElement element) {
		NoClassFoundInMetamodel error = AtlErrorFactory.eINSTANCE.createNoClassFoundInMetamodel();
		initProblem(error, element);
		
		error.setClassName(name);
		
		signalError(error, "No class " + name + " found in meta-model " + mmspace.getName(), element);
		return AnalyserContext.getTypingModel().newUnresolvedType(error);
	}

	public void signalLazyRuleWithFilter(LazyRule element) {
		LazyRuleWithFilter error = AtlErrorFactory.eINSTANCE.createLazyRuleWithFilter();
		initProblem(error, element);
		
		signalError(error, "Lazy rule's filter is ignored at runtime, in rule " + element.getName(), element);
	}
	
	public void signalInvalidRuleInheritance(InPattern inPattern, InvalidRuleInheritanceKind kind, String explanation) {
		InvalidRuleInheritance error = AtlErrorFactory.eINSTANCE.createInvalidRuleInheritance();
		initProblem(error, inPattern);
		
		error.setKind(kind);
		
		signalError(error, "Invalid rule inheritance. " + explanation, inPattern);	
	}

	
	public Type signalNoFeature(EClass c, String featureName, LocatedElement element) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, element);
		
		error.setFeatureName(featureName);
		
		signalError(error, "No feature " + c.getName() + "." + featureName + " found", element);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalNoFeatureInOclAny(String featureName, LocatedElement element) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, element);
		
		signalError(error, "No feature " + "OclAny" + "." + featureName + " found", element);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}
	
	public Type signalNoFeatureInOclUndefined(String featureName, LocatedElement element) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, element);
		
		signalError(error, "No feature " + "OclUndefined" + "." + featureName + " found", element);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalNoTupleFeature(TupleType tuple, String featureName, LocatedElement element) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, element);
		
		signalError(error, "No feature " + "Tuple" + "." + featureName + " found", element);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public boolean isErrorType(Type t) {
		return t instanceof TypeError;
	}
	
	public Type createDependentError(Problem p, Type t) {
		TypeError error = (TypeError) t;
		Problem parent = ((TypeErrorNamespace) error.getMetamodelRef()).getProblem();
		parent.getDependents().add(p);
		return AnalyserContext.getTypingModel().newTypeErrorType(p, error);
	}
	
	public Type signalCollectionOperationOverNoCollectionType(Type receptorType, LocatedElement element, IRecoveryAction ra) {
		CollectionOperationOverNoCollectionError error = AtlErrorFactory.eINSTANCE.createCollectionOperationOverNoCollectionError();
		initProblem(error, element);

		if ( isErrorType(receptorType) )
			return createDependentError(error, receptorType);
			
		
		
		Type result = ra.recover(this, error);
		if ( result != null ) {
			signalWarning(error, "Collection operation over " + TypeUtils.typeToString(receptorType), element);
			return result;
		}

		signalError(error, "Collection operation over " + TypeUtils.typeToString(receptorType), element);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalDifferentBranchTypes(Type thenPart, Type elsePart, LocatedElement node, IRecoveryAction ra) {
		DifferentBranchTypes error = AtlErrorFactory.eINSTANCE.createDifferentBranchTypes();
		initProblem(error, node);
		
		Type result = ra.recover(this, error);
		if ( result != null ) {
			signalWarning(error, "Different types in if/else branches: " + TypeUtils.typeToString(thenPart) + " - " + TypeUtils.typeToString(elsePart), node);	
			return result;
		}
		
		signalNoRecoverableError("Different types in if/else branches: " + TypeUtils.typeToString(thenPart) + " - " + TypeUtils.typeToString(elsePart), node);	
		return null;
	}

	public Type signalNoOperationFound(Type receptorType, String operationName, LocatedElement node, IRecoveryAction ra) {
		OperationNotFound error = AtlErrorFactory.eINSTANCE.createOperationNotFound();
		initProblem(error, node);

		error.setOperationName(operationName);
		error.setType(receptorType);
		
		if ( ra != null ) {
			Type result = ra.recover(this, error);
			if ( result != null ) {
				signalWarning(error, "No operation " + TypeUtils.typeToString(receptorType) + "." + operationName, node);	
				return result;
			}
		}
		
		signalError(error, "No operation " + TypeUtils.typeToString(receptorType) + "." + operationName, node);	
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalNoFeatureFound(Type receptorType, String featureName, LocatedElement node) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, node);

		error.setFeatureName(featureName);
		error.setType(receptorType);
		
		signalError(error, "No feature " + TypeUtils.typeToString(receptorType) + "." + featureName, node);	
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	
	public void warningFeatureFoundInSubType(Metaclass type, Collection<EClass> foundClasses, Collection<EClass> missingClasses, String featureName, LocatedElement node) {
		FeatureFoundInSubtype error = AtlErrorFactory.eINSTANCE.createFeatureFoundInSubtype();
		initProblem(error, node);

		error.setFeatureName(featureName);
		error.setClassName(type.getName());
		error.setMetamodelName(((IClassNamespace) type.getMetamodelRef()).getMetamodelName());

		for (EClass metaclass : foundClasses) {
			error.getPossibleClasses().add(metaclass);
		}
		
		for (EClass metaclass : missingClasses) {
			error.getMissingClasses().add(metaclass);
		}
		
		signalError(error, "Feature " + featureName + " expected in " + type.getName() + ". Missing in " + TypeUtils.classifiersToString(missingClasses) + ", but found in subtypes " + TypeUtils.classifiersToString(foundClasses), node);
	}

	public void warningOperationFoundInSubType(Metaclass type, Collection<EClass> foundClasses, Collection<EClass> missingClasses, String operationName, LocatedElement node) {
		OperationFoundInSubtype error = AtlErrorFactory.eINSTANCE.createOperationFoundInSubtype();
		initProblem(error, node);

		error.setOperationName(operationName);
		error.setClassName(type.getName());
		error.setMetamodelName(((IClassNamespace) type.getMetamodelRef()).getMetamodelName());
		
		for (EClass metaclass : foundClasses) {
			error.getPossibleClasses().add(metaclass);
		}

		for (EClass metaclass : missingClasses) {
			error.getMissingClasses().add(metaclass);
		}

		signalError(error, "Operation " + operationName + " expected in " + type.getName() + ". Missing in " + TypeUtils.classifiersToString(missingClasses) + ", but found in subtypes " + TypeUtils.classifiersToString(foundClasses), node);

		// System.err.println("WARNING: Feature " + operationName + " expected in " + type.getName() + " but found in subtype " + subtype.getName() + ". " + node.getLocation() );		
	} 

	public Type signalInvalidOperand(String operatorSymbol, LocatedElement node, IRecoveryAction ra) {
		InvalidOperand error = AtlErrorFactory.eINSTANCE.createInvalidOperand();
		initProblem(error, node);
		
		if ( ra != null ) {
			Type result = ra.recover(this, error);
			if ( result != null ) {
				signalError(error, "Invalid operand " + operatorSymbol, node);
				return result;
			}
		}

		signalError(error, "Invalid operand " + operatorSymbol, node);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalInvalidOperator(String operatorSymbol, Type t, LocatedElement node) {
		InvalidOperator error = AtlErrorFactory.eINSTANCE.createInvalidOperator();
		initProblem(error, node);
	
		String postix = "";
		if ( t != null )
			postix = "by " + TypeUtils.typeToString(t);
		
		signalError(error, "Operator " + operatorSymbol + " not supported " + postix, node);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}


	public void signalInvalidOperatorUsage(String message, OperatorCallExp node) {
		InvalidOperatorUsage error = AtlErrorFactory.eINSTANCE.createInvalidOperatorUsage();
		initProblem(error, node);
	
		signalError(error, message, node);
	}
	
	public void initProblem(LocalProblem p, LocatedElement element) {
		initProblem(p, element, ProblemStatus.STATICALLY_CONFIRMED, true);
	}
	
	public void initProblem(LocalProblem p, LocatedElement element, ProblemStatus status, boolean addToProblems) {
		p.setLocation(element.getLocation());
		p.setElement(element);
		p.setFileLocation(element.getFileLocation());
		p.setStatus(status);
		
		if ( addToProblems ) {
			result.getProblems().add(p);			
			element.getProblems().add(p);
		}
	}

	public Type signalIteratorOverNoCollectionType(Type receptorType, LocatedElement node) {
		IteratorOverNoCollectionType error = AtlErrorFactory.eINSTANCE.createIteratorOverNoCollectionType();
		initProblem(error, node);
		
//		if ( isErrorType(receptorType) )
//			return createDependentError(error, receptorType);

		signalError(error, "Iterator operation over non-collection expression, with type: " + TypeUtils.typeToString(receptorType), node);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalNoThisModuleOperation(String operationName, LocatedElement node) {
		OperationNotFoundInThisModule error = AtlErrorFactory.eINSTANCE.createOperationNotFoundInThisModule();
		initProblem(error, node);
		error.setName(operationName);
		
		signalError(error, "No operation " + operationName + " in thisModule", node);
		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalNoThisModuleFeature(String featureName, LocatedElement node) {
		AttributeNotFoundInThisModule error = AtlErrorFactory.eINSTANCE.createAttributeNotFoundInThisModule();
		initProblem(error, node);
		error.setName(featureName);
		
		signalError(error, "No feature " + featureName + " in thisModule", node);
	
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public void warningVarDclIncoherentTypes(Type exprType, Type declared, LocatedElement node) {
		IncoherentVariableDeclaration error = AtlErrorFactory.eINSTANCE.createIncoherentVariableDeclaration();
		initProblem(error, node);
		error.setDeclared(declared);
		error.setInferred(exprType);
		signalError(error, "Incoherent declared type " + TypeUtils.typeToString(declared) + " and inferred " + TypeUtils.typeToString(exprType) + "" , node);
	}
	
	public void warningIncoherentHelperReturnType(OclFeature feature, Type inferred, Type declared) {
		IncoherentHelperReturnType error = AtlErrorFactory.eINSTANCE.createIncoherentHelperReturnType();
		initProblem(error, feature);
		error.setDeclared(declared);
		error.setInferred(inferred);
		signalError(error, "Incoherent return type. Declared type " + TypeUtils.typeToString(declared) + " and inferred " + TypeUtils.typeToString(inferred) + "" , feature);		
		
	}
	
	public void signalNoRecoverableError(String msg, LocatedElement l) {
		throw new NoRecoverableError(msg + ". " + l.getLocation());
	}

	public void signalError(Problem p, String msg, LocatedElement l) {
		p.setDescription(msg);
		p.setSeverity(SeverityKind.ERROR);
		
		if ( AnalyserContext.debugMode() )
			System.out.println("ERROR: " + msg + ". " + l.getLocation());		
	}
	
	public void signalWarning(Problem p, String msg, LocatedElement l) {
		p.setDescription(msg);
		p.setSeverity(SeverityKind.WARNING);

		if ( AnalyserContext.debugMode() )
			System.out.println("WARNING: " + msg + ". " + l.getLocation());		
	}

	public void signalWarning_WITHOUTERROR_TODO( String msg, LocatedElement l) {
		if ( AnalyserContext.debugMode() )
			System.out.println("WARNING: " + msg + ". " + l.getLocation());		
	}

	
	/** Recovery methods **/ 
	
	public Recovery recoveryTentativeTypeAssigned(Type t) {
		TentativeTypeAssigned rec = AtlRecoveryFactory.eINSTANCE.createTentativeTypeAssigned();
		rec.setType(t);
		return rec;
	}

	public Type signalNoContainerForRefImmediateComposite(Metaclass clazz, LocatedElement node) {
		NoContainerForRefImmediateComposite error = AtlErrorFactory.eINSTANCE.createNoContainerForRefImmediateComposite();
		initProblem(error, node);
		error.setClassName(clazz.getName());
		error.setMetamodelName(((IClassNamespace) clazz.getMetamodelRef()).getMetamodelName());
		
		signalError(error,  "No container is possible for class " + clazz.getName(), node);
		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalNoFeatureInUnionType(UnionType type, String featureName, LocatedElement node) {
		FeatureNotFoundInUnionType error = AtlErrorFactory.eINSTANCE.createFeatureNotFoundInUnionType();
		initProblem(error, node);
		error.setFeatureName(featureName);
		
		signalError(error, "No feature " + featureName + " for " + TypeUtils.typeToString(type), node);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	// Binding problems
	
	public void signalNoBindingForCompulsoryFeature(EStructuralFeature unboundCompulsoryFeature, OutPatternElement pe, NoBindingForCompulsoryFeatureKind kind, RuleWithPattern subrule) {
		NoBindingForCompulsoryFeature error = AtlErrorFactory.eINSTANCE.createNoBindingForCompulsoryFeature();
		initProblem(error, pe);
		error.setDisplayedElement(pe.getType());
		error.setFeature(unboundCompulsoryFeature);
		error.setFeatureName(unboundCompulsoryFeature.getName());
		error.setKind(kind);
		error.setMissing(unboundCompulsoryFeature);
		
		String initialText = "In rule " + pe.getOutPattern().getRule().getName();
		if ( subrule != null ) {
			error.setSubrule(subrule);
			initialText = "In subrule " + subrule.getName();
		}
		
		signalWarning(error, "No binding for compulsory feature " + unboundCompulsoryFeature.getEContainingClass().getName() + "." + unboundCompulsoryFeature.getName() + ". " + initialText, pe);						
	}

	
	public void signalBindingExpectedOneAssignedMany(Binding binding, Metaclass targetVar, String propertyName) {
		BindingExpectedOneAssignedMany error = AtlErrorFactory.eINSTANCE.createBindingExpectedOneAssignedMany();
		initProblem(error, binding);
		error.setFeatureName(propertyName);
		signalWarning(error, "In binding " + targetVar.getName() + "." + propertyName + ", expected mono-valued, received collection", binding);
	}

	public void signalIteratorOverEmptyCollection(IteratorExp node) {
		IteratorOverEmptySequence error = AtlErrorFactory.eINSTANCE.createIteratorOverEmptySequence();
		initProblem(error, node);
		signalWarning(error, "Iterator over empty sequence", node);
	}

	public void signalFlattenOverNonNestedCollection(Type nestedType, LocatedElement node) {
		FlattenOverNonNestedCollection error = AtlErrorFactory.eINSTANCE.createFlattenOverNonNestedCollection();
		initProblem(error, node);
		
		signalWarning(error, "Flatten over non-nested collection", node);
	}


	public void signalSelectFirstAny(CollectionOperationCallExp node) {
		ChangeSelectFirstForAny error = AtlErrorFactory.eINSTANCE.createChangeSelectFirstForAny();
		initProblem(error, node);
		
		signalWarning(error, "Select-first should be any", node);		
	}

	public void signalBindingWithoutRule(Binding b, ModelElement right, ModelElement left) {
		BindingWithoutRule error = AtlErrorFactory.eINSTANCE.createBindingWithoutRule();
		initProblem(error, b);
		
		error.setRightType(right.getKlass());
		error.setTargetType(left.getKlass());
		error.setRight(right);		
		error.setLeft(left);		
		
		error.setFeatureName(b.getPropertyName());
		

		signalError(error, "No rule for binding: " + right.getKlass().getName(), b);		
	}
	
	public Type signalResolveTempWithoutRule(OperationCallExp resolveTempOperation, Type sourceType) {
		ResolveTempWithoutRule error = AtlErrorFactory.eINSTANCE.createResolveTempWithoutRule();
		initProblem(error, resolveTempOperation);
		
		if ( sourceType instanceof Metaclass) {
			error.setSourceType(((Metaclass) sourceType).getKlass());
		}

		signalError(error, "No rule for resolveTemp", resolveTempOperation);				
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	
	public void signalResolveTempPossiblyUnresolved(OperationCallExp resolveTempOperation, OclExpression resolvedObj, EClass sourceType, List<EClass> problematicClasses, List<EClass> problematicClassesImplicit) {
		ResolveTempPossiblyUnresolved error = AtlErrorFactory.eINSTANCE.createResolveTempPossiblyUnresolved();
		initProblem(error, resolveTempOperation, ProblemStatus.WITNESS_REQUIRED, true);

		error.setRightType(sourceType);
		// error.setTargetType(targetType);
		// error.setFeatureName(b.getPropertyName());
		
		error.getProblematicClasses().addAll(problematicClasses);
		error.getProblematicClassesImplicit().addAll(problematicClassesImplicit);
		
		error.setResolvedExpression(resolvedObj);
		
		String s = "";
		for (EClass eClass : problematicClasses) {
			s += ", " + eClass.getName();
		}
		s = s.replaceFirst(",", "");
		
		signalWarning(error, "Unresolved resolveTemp (" + sourceType.getName() + "): "  + s, resolveTempOperation);
	}


	public Type signalResolveTempOutputPatternElementNotFound(
			OperationCallExp resolveTempOperation, Type type_, String expectedVarName,
			List<MatchedRule> compatibleRules) {

		ResolveTempOutputPatternElementNotFound error = AtlErrorFactory.eINSTANCE.createResolveTempOutputPatternElementNotFound();
		initProblem(error, resolveTempOperation);
		
		for (MatchedRule r : compatibleRules) {
			ResolvedRuleInfo rinfo = AtlErrorFactory.eINSTANCE.createResolvedRuleInfo();
			rinfo.setLocation(r.getLocation());
			rinfo.setElement(r);
			rinfo.setRuleName(r.getName());

			error.getRules().add(rinfo);
		}

		String s = "In resolveTemp - no output pattern " + expectedVarName + " in rule(s): \n";
		for (ResolvedRuleInfo rinfo : error.getRules()) {
			s += "\t" + rinfo.getRuleName() + " " + rinfo.getLocation() + "\n";
		}

		signalError(error, s, resolveTempOperation);				
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public void signalResolveTempGetsDifferentTargetTypes(String message, LocatedElement node) {
		System.err.println("TODO: Check if this needs to go to the constraint solver");
		System.err.println(message);
	}
	
	public void signalBindingWithResolvedByIncompatibleRule(Binding b, EClass rightType, EClass targetType,
			List<MatchedRule> problematicRules, List<EClass> sourceClasses, List<EClass> targetClasses) {
			
		BindingWithResolvedByIncompatibleRule error = AtlErrorFactory.eINSTANCE.createBindingWithResolvedByIncompatibleRule();
		initProblem(error, b, ProblemStatus.WITNESS_REQUIRED, true);

		error.setRightType(rightType);
		error.setTargetType(targetType);
		error.setFeatureName(b.getPropertyName());
		// error.setFeature(); // TODO: Set the EStructuralFeature
		
		int i = 0;
		for (MatchedRule r : problematicRules) {
			ResolvedRuleInfo rinfo = AtlErrorFactory.eINSTANCE.createResolvedRuleInfo();
			rinfo.setLocation(r.getLocation());
			rinfo.setElement(r);
			rinfo.setRuleName(r.getName());
			rinfo.setInputType(sourceClasses.get(i));
			rinfo.setOutputType(targetClasses.get(i));

			rinfo.getAllInvolvedRules().add(r);
			for(MatchedRule sup : ATLUtils.allSuperRules(r)) {
				rinfo.getAllInvolvedRules().add(sup);
			}
			
			i++;
			error.getRules().add(rinfo);
		}
		
		String s = "Binding resolved by rule with invalid target type (src : " +  rightType.getName() + "). " + b.getLocation() + "\n";
		for (ResolvedRuleInfo rinfo : error.getRules()) {
			s += "\t" + rinfo.getRuleName() + " " + rinfo.getLocation() + "\n";
		}
		
		signalWarning(error, s, b);		
	}
	

	public void signalBindingInplaceInvalid(Binding b, Type rightType, EReference f) {
		BindingInplaceInvalid error = AtlErrorFactory.eINSTANCE.createBindingInplaceInvalid();
		initProblem(error, b);
		
		Type leftType = b.getLeftType();
		error.setRightType(rightType);
		error.setFeatureName(b.getPropertyName());

		signalError(error, "Invalid binding assignment, " + TypeUtils.typeToString(leftType) + " <- " + TypeUtils.typeToString(rightType), b);
	}

	
	public void signalBindingPossiblyUnresolved(Binding b, Metaclass rightMetaclass, EClass targetType, List<EClass> problematicClasses, List<EClass> problematicClassesImplicit) {
		BindingPossiblyUnresolved error = AtlErrorFactory.eINSTANCE.createBindingPossiblyUnresolved();
		initProblem(error, b, ProblemStatus.WITNESS_REQUIRED, true);

		error.setRight(this.newElement(rightMetaclass)); 
		String mmName = ((OclModelElement) b.getOutPatternElement().getType()).getModel().getName();
		error.setLeft(this.newElement(mmName, targetType));	
		
		error.setRightType(rightMetaclass.getKlass());
		error.setTargetType(targetType);
		error.setFeatureName(b.getPropertyName());
		
		error.getProblematicClasses().addAll(problematicClasses);
		error.getProblematicClassesImplicit().addAll(problematicClassesImplicit);

		// Fill "rules" feature with the same information that the binding has since these are
		// the involved rules
		for (RuleResolutionInfo rb : b.getResolvedBy()) {
			MatchedRule r = rb.getRule();			
			ResolvedRuleInfo rinfo = AtlErrorFactory.eINSTANCE.createResolvedRuleInfo();
			rinfo.setLocation(r.getLocation());
			rinfo.setElement(r);
			rinfo.setRuleName(r.getName());
			rinfo.setInputType(ATLUtils.getInPatternType(r).getKlass());
			
			Metaclass tgtMetaclass = (Metaclass) r.getOutPattern().getElements().get(0).getInferredType();
			rinfo.setOutputType(tgtMetaclass.getKlass());

			for (MatchedRule involvedRule : rb.getAllInvolvedRules()) {
				rinfo.getAllInvolvedRules().add(involvedRule);				
			}
			
			error.getRules().add(rinfo);
		}
		
		String s = "";
		for (EClass eClass : problematicClasses) {
			s += ", " + eClass.getName();
		}
		s = s.replaceFirst(",", "");
		
		signalWarning(error, "Unresolved binding (" + rightMetaclass.getName() + "): "  + s, b);
	}
	
	public void signalInvalidAssignmentInBindingStatement(Type left, Type right, BindingStat b) {
		InvalidAssignmentImperativeBinding error = AtlErrorFactory.eINSTANCE.createInvalidAssignmentImperativeBinding();
		initProblem(error, b);
		
		signalError(error, "Invalid assignment in binding statement. Left: " + 
				TypeUtils.typeToString(left) + ". Right: " + TypeUtils.typeToString(right), b);		
	}
	// End-of binding problems


	public void signalIteratorBodyWrongType(IteratorExp node, Type bodyType) {
		// Do not signal in this case, to avoid cluttering of errors
		if ( bodyType instanceof TypeError )
			return;
		
		IteratorBodyWrongType error = AtlErrorFactory.eINSTANCE.createIteratorBodyWrongType();
		initProblem(error, node);
		
		signalError(error, "Wrong iterator body type " + TypeUtils.typeToString(bodyType), node);
	}
	public void signalReadingTargetModel(OclModelElement model) {
		ReadingTargetModel error = AtlErrorFactory.eINSTANCE.createReadingTargetModel();
		initProblem(error, model);
		error.setModelName(model.getName());
		
		signalError(error, "Reading target model " + model.getModel().getName() + "!" + model.getName(), model);
	}
	
	public void signalWritingSourceModel(OclModelElement model) {
		WritingSourceModel error = AtlErrorFactory.eINSTANCE.createWritingSourceModel();
		initProblem(error, model);
		error.setModelName(model.getName());
		
		signalError(error, "Writing source model " + model.getModel().getName() + "!" + model.getName(), model);
	}
	
	public void signalAmbiguousTargetModelReference(OclModelElement model) {
		AmbiguousTargetModelReference error = AtlErrorFactory.eINSTANCE.createAmbiguousTargetModelReference();
		initProblem(error, model);
		error.setModelName(model.getName());
		
		signalError(error, "Ambiguous target model reference " + model.getModel().getName() + "!" + model.getName(), model);	
	}

	public void signalMatchedRuleWithoutOutputPattern(MatchedRule rule) {
		MatchedRuleWithoutOutputPattern error = AtlErrorFactory.eINSTANCE.createMatchedRuleWithoutOutputPattern();
		initProblem(error, rule);
		
		signalWarning(error, "Matched rule without output pattern: " + rule.getName(), rule);		
	}
	

	public void signalMatchedRuleWithNonBooleanFilter(Type t, MatchedRule rule) {
		MatchedRuleFilterNonBoolean error = AtlErrorFactory.eINSTANCE.createMatchedRuleFilterNonBoolean();
		initProblem(error, rule);
		
		signalWarning(error, "Matched rule " + rule.getName() + " with non-boolean filter: " + TypeUtils.typeToString(t), rule);				
	}
	
	public Type signalNoEnumLiteral(String name, LocatedElement node) {
		NoEnumLiteral error = AtlErrorFactory.eINSTANCE.createNoEnumLiteral();
		initProblem(error, node);
		
		signalError(error, "No enum literal " + name, node);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public void warningMissingFeatureInUnionType(List<Type> noFeatureTypes, String featureName, LocatedElement node) {
		String strTypes = "";
		for (Type type : noFeatureTypes) {
			strTypes += TypeUtils.typeToString(type) + " ";
		}
		// signalWarning_WITHOUTERROR_TODO("Missing feature in these types: [" + strTypes + "]", node);

		FeatureNotFoundInUnionType error = AtlErrorFactory.eINSTANCE.createFeatureNotFoundInUnionType();
		initProblem(error, node);
		error.setFeatureName(featureName);
		
		signalError(error, "No feature " + featureName + " for { " + strTypes + "}", node);
	}

	

	public void signalBindingPrimitiveExpectedButObjectAssigned(Binding binding, Metaclass targetVar, String propertyName) {
		PrimitiveBindingButObjectAssigned error = AtlErrorFactory.eINSTANCE.createPrimitiveBindingButObjectAssigned();
		initProblem(error, binding);

		signalError(error, "In binding " + targetVar.getName() + "." + propertyName + " : " + TypeUtils.typeToString(binding.getLeftType())+ ", expected primitive type, received objects", binding);		
	}

	public void signalBindingObjectExpectedButPrimitiveAssigned(Binding binding, Metaclass targetVar, String propertyName) {
		ObjectBindingButPrimitiveAssigned error = AtlErrorFactory.eINSTANCE.createObjectBindingButPrimitiveAssigned();
		initProblem(error, binding);
		
		signalError(error, "In binding " + targetVar.getName() + "." + propertyName + " : " + TypeUtils.typeToString(binding.getLeftType()) + ", expected object type, received primitive value", binding);				
	}

	public void signalPrimitiveBindingInvalidAssignement(Binding binding,  Metaclass targetVar, String propertyName) {
		PrimitiveBindingInvalidAssignment error = AtlErrorFactory.eINSTANCE.createPrimitiveBindingInvalidAssignment();
		initProblem(error, binding);
		
		signalError(error, "In binding " + targetVar.getName() + "." + propertyName + " : " + TypeUtils.typeToString(binding.getLeftType()) + ", but received " + TypeUtils.typeToString(binding.getValue().getInferredType()), binding);						
	}
	
	public void signalAssignmentToReadonlyFeature(EStructuralFeature f, Binding binding) {
		AssignmentToReadonlyFeature error = AtlErrorFactory.eINSTANCE.createAssignmentToReadonlyFeature();
		initProblem(error, binding);
		
		signalError(error, "Feature " + f.getName() + " is readonly (isDerived = true)", binding);		
	}

	public void signalWarningInvalidMapKeyType(LocatedElement node) {
		signalWarning_WITHOUTERROR_TODO("Invalid type for key in Map ", node);								
	}

	public void signalOperationOverCollectionType(OperationCallExp op) {
		OperationOverCollectionType error = AtlErrorFactory.eINSTANCE.createOperationOverCollectionType();
		initProblem(error, op);

		signalError(error, "Operation " + op.getOperationName() + " over collection, perhaps you want to use \"->\"", op);		
	}

	public static class NoRecoverableError extends RuntimeException {
		private static final long	serialVersionUID	= 4507844062130557035L;

		public NoRecoverableError(String msg) { super(msg); }
	}

	public Type signalExpectedCollectionInForEachOutputPattern(ForEachOutPatternElement e) {
		ExpectedCollectionInForEach error = AtlErrorFactory.eINSTANCE.createExpectedCollectionInForEach();
		initProblem(error, e);
		
		signalError(error, "Expected collection in ForEachOutputPattern", e);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalExpectedCollectionInForStat(ForStat fs) {
		ExpectedCollectionInForEach error = AtlErrorFactory.eINSTANCE.createExpectedCollectionInForEach();
		initProblem(error, fs);
		
		signalError(error, "Expected collection in ForStat", fs);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalInvalidArgument(String operationName, String explanation, LocatedElement node) {
		InvalidArgument error = AtlErrorFactory.eINSTANCE.createInvalidArgument();
		initProblem(error, node);

		signalError(error, "Invalid argument for " + operationName + ". " + explanation, node);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalOperationCallInvalidNumberOfParameters(String operationName, Type[] formalArguments, Type[] arguments, LocatedElement node) {
		OperationCallInvalidNumberOfParameters error = AtlErrorFactory.eINSTANCE.createOperationCallInvalidNumberOfParameters();
		initProblem(error, node);
		
		for (Type type : arguments)       { error.getActualParameters().add(type); }
		for (Type type : formalArguments) { error.getFormalParameters().add(type); }
		
		signalError(error, "Invalid number of arguments, " + arguments.length + ", expected " + formalArguments.length + ". Operation: " + operationName, node);
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}
	

	public void signalAccessToUndefinedValue(PropertyCallExp node) {
		Pair<AccessToUndefinedValue, String> error = createAccessToUndefinedValue(node, ProblemStatus.STATICALLY_CONFIRMED, true);
		signalError(error._1, error._2, node);	
	}
	
	public void signalAccessToUndefinedValue_ThroughEmptyCollection(PropertyCallExp node) {
		AccessToUndefinedValue_ThroughEmptyCollection error = AtlErrorFactory.eINSTANCE.createAccessToUndefinedValue_ThroughEmptyCollection();
		initProblem(error, node, ProblemStatus.WITNESS_REQUIRED, true);
		
		signalError(error, "Access to undefined value due to empty collection: " + ATLSerializer.serialize(node.getSource()), node);
	}

	
	public void discardedAccessToUndefinedValue(PropertyCallExp node) {
		Pair<AccessToUndefinedValue, String> error = createAccessToUndefinedValue(node, ProblemStatus.INITIALLY_DISCARDED, false);
		discardedProblems.add(error._1);
	}
	
	private Pair<AccessToUndefinedValue, String> createAccessToUndefinedValue(PropertyCallExp node, ProblemStatus status, boolean addToProblems) {
		AccessToUndefinedValue error = AtlErrorFactory.eINSTANCE.createAccessToUndefinedValue();
		initProblem(error, node, status, addToProblems);
		
		String featName = "";
		if ( node instanceof NavigationOrAttributeCallExp ) {
			featName = ((NavigationOrAttributeCallExp) node).getName();
		} else if ( node instanceof OperationCallExp ) {
			featName = ((OperationCallExp) node).getOperationName();
		}
		
		error.setDescription("Access to undefined value: " + featName);
		error.setSeverity(SeverityKind.ERROR);

		return new Pair<AccessToUndefinedValue, String>(error, error.getDescription());
	}
	
	public void signalOperationCallInvalidParameter(String operationName, Type[] formalArguments, Type[] arguments, List<String> blamedParameters, LocatedElement node) {
		OperationCallInvalidParameter error = AtlErrorFactory.eINSTANCE.createOperationCallInvalidParameter();
		initProblem(error, node);
		
		for (Type type : arguments)       { error.getActualParameters().add(type); }
		for (Type type : formalArguments) { error.getFormalParameters().add(type); }
		
		String s1 = blamedParameters.get(0);
		for(int i = 1; i < blamedParameters.size(); i++) s1 += ", " + blamedParameters.get(i);
		
		String s2 = TypeUtils.typeToString(arguments[0]);
		for(int i = 1; i < arguments.length; i++) s2 += ", " + TypeUtils.typeToString(arguments[i]);
		
		String s3 = TypeUtils.typeToString(formalArguments[0]);
		for(int i = 1; i < formalArguments.length; i++) s3 += ", " + TypeUtils.typeToString(formalArguments[i]);
		
		signalError(error, "Invalid parameter types: " + s1 + ". Expected " + operationName + "(" + s3 +"), given " + operationName + "(" + s2 + ")", node);
	}

	
	public Type signalFeatureAccessInCollection(String featureName, LocatedElement node) {
		FeatureAccessInCollection error = AtlErrorFactory.eINSTANCE.createFeatureAccessInCollection();
		initProblem(error, node);
		
		error.setFeatureName(featureName);

		signalError(error, "Feature access in collection, " + featureName, node);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public void signalParseError(String fileName, String message, String location) {
		AtlParseError error = AtlErrorFactory.eINSTANCE.createAtlParseError();
		error.setLocation(location);
		error.setElement(null);
		error.setFileLocation(fileName);
		error.setStatus(ProblemStatus.ERROR_CONFIRMED);
		
		result.getProblems().add(error);
		
		error.setDescription(message);
	}

	
	public void signalCannonInstantiateAbstractClass(Metaclass m, LocatedElement node) {
		CannotInstantiateAbstractClass error = AtlErrorFactory.eINSTANCE.createCannotInstantiateAbstractClass();
		initProblem(error, node);
		
		error.setType(m);

		signalError(error, "Abstract classes cannot be instantiated: " + m.getName(), node);	
	}
	
	public Type signalCollectionOperationNotFound(String operationName, LocatedElement node) {
		CollectionOperationNotFound error = AtlErrorFactory.eINSTANCE.createCollectionOperationNotFound();
		initProblem(error, node);
		
		signalError(error, "Collection operation " + operationName + " not supported", node);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public void signalGenericProblem(String message, String kind, LocatedElement element) {
		GenericLocalProblem error = AtlErrorFactory.eINSTANCE.createGenericLocalProblem();
		initProblem(error, element);
		
		error.setGenericKind(kind);
		signalError(error, message, element);
	}
	
	public ModelElement newElement(Metaclass metaclass) {
		ModelElement me = AtlErrorFactory.eINSTANCE.createModelElement();
		me.setKlass(metaclass.getKlass());
		me.setMetamodelName(metaclass.getModel().getName());
		return me;
	}

	public ModelElement newElement(String mmName, EClass klass) {
		ModelElement me = AtlErrorFactory.eINSTANCE.createModelElement();
		me.setKlass(klass);
		me.setMetamodelName(mmName);
		return me;
	}

	public void clear() {
		EcoreUtil.delete(result);
		
		List<EObject> toBeRemoved = resource.getContents().stream().filter(e -> e instanceof Problem).collect(Collectors.toList());
		for (EObject eObject : toBeRemoved) {
			EcoreUtil.delete(eObject, true);
		}
		
		// This does not work because the resource is shared
		// while ( ! r.getContents().isEmpty() ) 
		// 	EcoreUtil.delete(r.getContents().get(0), true);		
		result = AnalysisResultFactory.eINSTANCE.createAnalysisResult();
		resource.getContents().add(result);
	}

	public List<Problem> getDiscardedProblems() {
		return new ArrayList<Problem>(this.discardedProblems);
	}

	public void moveDiscardedToWitnessRequired(Problem p) {
		result.getProblems().add(p);
		p.setStatus(ProblemStatus.WITNESS_REQUIRED);

		// Not sure if I should mark the problem...
		// element.getProblems().add(p);		
	}

	public boolean removeProblem(LocalProblem p) {
		return result.getProblems().remove(p);		
	}

	public void addProblems(Collection<Problem> problems) {
		result.getProblems().addAll(problems);		
	}

	

	
}
