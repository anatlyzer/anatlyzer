package anatlyzer.atl.model;

import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLUtils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.ForStat;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analyser.namespaces.TypeErrorNamespace;
import anatlyzer.atl.analyser.recovery.IRecoveryAction;
import anatlyzer.atl.errors.AnalysisResult;
import anatlyzer.atl.errors.AnalysisResultFactory;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.Recovery;
import anatlyzer.atl.errors.SeverityKind;
import anatlyzer.atl.errors.atl_error.AmbiguousTargetModelReference;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.DifferentBranchTypes;
import anatlyzer.atl.errors.atl_error.ExpectedCollectionInForEach;
import anatlyzer.atl.errors.atl_error.FeatureAccessInCollection;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.InvalidArgument;
import anatlyzer.atl.errors.atl_error.IteratorBodyWrongType;
import anatlyzer.atl.errors.atl_error.IteratorOverEmptySequence;
import anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ModelElement;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.errors.atl_error.NoContainerForRefImmediateComposite;
import anatlyzer.atl.errors.atl_error.NoModelFound;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.errors.atl_error.ReadingTargetModel;
import anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound;
import anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.errors.atl_recovery.AtlRecoveryFactory;
import anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass;
import anatlyzer.atl.errors.atl_recovery.TentativeTypeAssigned;

/**
 * 
 * Implementation note: internal methods like signalError and initProblems
 * are declared as public to allow extensions to easily contribute new errors.
 * 
 * @author jesus
 *
 */
public class ErrorModel {
	
	protected Resource r;
	private AnalysisResult	result;
	
	public ErrorModel(Resource resource) {
		result = AnalysisResultFactory.eINSTANCE.createAnalysisResult();
		r = resource;
	}

	public AnalysisResult getAnalysis() {
		return result;
	}

	public List<Problem> getProblems() {
		return result.getProblems();
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

	public Type signalNoFeature(EClass c, String featureName, LocatedElement element) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, element);
		
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

	
	public void warningFeatureFoundInSubType(Metaclass type, Metaclass subtype, String featureName, LocatedElement node) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, node);

		error.setFeatureName(featureName);
		error.setClassName(type.getName());
		error.setMetamodelName(((IClassNamespace) type.getMetamodelRef()).getMetamodelName());
		
		FeatureFoundInSubclass recovery = AtlRecoveryFactory.eINSTANCE.createFeatureFoundInSubclass();
		recovery.setSubclassName(subtype.getName());
		recovery.setSubclass(subtype.getKlass());
		error.setRecovery(recovery);
		
		signalError(error, "Feature " + featureName + " expected in " + type.getName() + " but found in subtype " + subtype.getName(), node);
	}

	public void warningOperationFoundInSubType(Metaclass type, Metaclass subtype, String operationName, LocatedElement node) {
		OperationNotFound error = AtlErrorFactory.eINSTANCE.createOperationNotFound();
		initProblem(error, node);

		error.setOperationName(operationName);
		error.setClassName(type.getName());
		error.setMetamodelName(((IClassNamespace) type.getMetamodelRef()).getMetamodelName());
		
		FeatureFoundInSubclass recovery = AtlRecoveryFactory.eINSTANCE.createFeatureFoundInSubclass();
		recovery.setSubclassName(subtype.getName());
		recovery.setSubclass(subtype.getKlass());
		error.setRecovery(recovery);
		
		signalError(error, "Operation " + operationName + " expected in " + type.getName() + " but found in subtype " + subtype.getName(), node);

		// System.err.println("WARNING: Feature " + operationName + " expected in " + type.getName() + " but found in subtype " + subtype.getName() + ". " + node.getLocation() );		
	} 

	public Type signalInvalidOperand(String operatorSymbol, LocatedElement node, IRecoveryAction ra) {
		FeatureNotFound error = AtlErrorFactory.eINSTANCE.createFeatureNotFound();
		initProblem(error, node);
		
		if ( ra == null ) 
			throw new IllegalArgumentException();
		
		Type result = ra.recover(this, error);
		if ( result != null ) {
			signalError(error, "Invalid operand " + operatorSymbol, node);
			return result;
		}
	
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public void initProblem(LocalProblem p, LocatedElement element) {
		p.setLocation(element.getLocation());
		p.setElement(element);
		p.setFileLocation(element.getFileLocation());
		result.getProblems().add(p);
		
		element.getProblems().add(p);
	}

	public Type signalIteratorOverNoCollectionType(Type receptorType, LocatedElement node) {
		IteratorOverNoCollectionType error = AtlErrorFactory.eINSTANCE.createIteratorOverNoCollectionType();
		initProblem(error, node);
		
		if ( isErrorType(receptorType) )
			return createDependentError(error, receptorType);

		signalNoRecoverableError("Iterator operation over " + receptorType, node);		
		return null;
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
		
		signalError(error, "No operation " + featureName + " in thisModule", node);
	
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public void warningVarDclIncoherentTypes(Type exprType, Type declared, LocatedElement node) {
		IncoherentVariableDeclaration error = AtlErrorFactory.eINSTANCE.createIncoherentVariableDeclaration();
		initProblem(error, node);
		
		signalError(error, "Incoherent declared type (" + TypeUtils.typeToString(declared) + ") and inferred (" + TypeUtils.typeToString(exprType) + ")" , node);
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

	public void signalNoFeatureInUnionType(UnionType type, String featureName, LocatedElement node) {
		FeatureNotFoundInUnionType error = AtlErrorFactory.eINSTANCE.createFeatureNotFoundInUnionType();
		initProblem(error, node);
		error.setFeatureName(featureName);
		
		signalNoRecoverableError("No feature " + featureName + " for " + TypeUtils.typeToString(type), node);
	}

	// Binding problems
	
	public void signalNoBindingForCompulsoryFeature(EStructuralFeature unboundCompulsoryFeature, OutPatternElement pe) {
		NoBindingForCompulsoryFeature error = AtlErrorFactory.eINSTANCE.createNoBindingForCompulsoryFeature();
		initProblem(error, pe);
		error.setFeature(unboundCompulsoryFeature);
		error.setFeatureName(unboundCompulsoryFeature.getName());
		
		signalWarning(error, "In rule " + pe.getOutPattern().getRule().getName() + ", no binding for compulsory " + unboundCompulsoryFeature.getEContainingClass().getName() + "." + unboundCompulsoryFeature.getName(), pe);						
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



	public void signalBindingWithoutRule(Binding b, ModelElement right, ModelElement left) {
		BindingWithoutRule error = AtlErrorFactory.eINSTANCE.createBindingWithoutRule();
		initProblem(error, b);
		
		error.setRightType(right.getKlass());
		error.setTargetType(left.getKlass());
		error.setRight(right);		
		error.setLeft(left);		
		
		error.setFeatureName(b.getPropertyName());
		

		signalError(error, "No rule for binding", b);		
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
		initProblem(error, b);

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
		
		String s = "Binding may be resolved by rule with invalid target type (src : " +  rightType.getName() + "). " + b.getLocation() + "\n";
		for (ResolvedRuleInfo rinfo : error.getRules()) {
			s += "\t" + rinfo.getRuleName() + " " + rinfo.getLocation() + "\n";
		}
		
		signalWarning(error, s, b);		
	}
	
	public void signalBindingPossiblyUnresolved(Binding b, EClass rightType, EClass targetType, List<EClass> problematicClasses) {
		BindingPossiblyUnresolved error = AtlErrorFactory.eINSTANCE.createBindingPossiblyUnresolved();
		initProblem(error, b);

		error.setRightType(rightType);
		error.setTargetType(targetType);
		error.setFeatureName(b.getPropertyName());
		
		error.getProblematicClasses().addAll(problematicClasses);
		String s = "";
		for (EClass eClass : problematicClasses) {
			s += ", " + eClass.getName();
		}
		s = s.replaceFirst(",", "");
		
		signalWarning(error, "Possibly unresolved binding (" + rightType.getName() + "): "  + s, b);
	}
	// End-of binding problems


	public void signalIteratorBodyWrongType(IteratorExp node, Type bodyType) {
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
	
	public void signalAmbiguousTargetModelReference(OclModelElement model) {
		AmbiguousTargetModelReference error = AtlErrorFactory.eINSTANCE.createAmbiguousTargetModelReference();
		initProblem(error, model);
		error.setModelName(model.getName());
		
		signalError(error, "Ambiguous target model reference " + model.getModel().getName() + "!" + model.getName(), model);	
	}

	public void signalMatchedRuleWithoutOutputPattern(MatchedRule rule) {
		AmbiguousTargetModelReference error = AtlErrorFactory.eINSTANCE.createAmbiguousTargetModelReference();
		initProblem(error, rule);
		
		signalWarning(error, "Matched rule without output pattern: " + rule.getName(), rule);		
	}
	
	
	public void signalNoEnumLiteral(String name, LocatedElement node) {
		signalNoRecoverableError("No enum literal " + name, node);
	}

	public void warningMissingFeatureInUnionType(List<Type> noFeatureTypes, LocatedElement node) {
		String strTypes = "";
		for (Type type : noFeatureTypes) {
			strTypes += TypeUtils.typeToString(type) + " ";
		}
		signalWarning_WITHOUTERROR_TODO("Missing feature in these types: [" + strTypes + "]", node);
	}

	

	public void signalBindingPrimitiveExpectedButObjectAssigned(Binding binding, Metaclass targetVar, String propertyName) {
		signalWarning_WITHOUTERROR_TODO("In binding " + targetVar.getName() + "." + propertyName + ", expected primitive type, received objects", binding);		
	}

	public void signalBindingObjectExpectedButPrimitiveAssigned(Binding binding, Metaclass targetVar, String propertyName) {
		signalWarning_WITHOUTERROR_TODO("In binding " + targetVar.getName() + "." + propertyName + ", expected object type, received primitive value", binding);				
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

	public Type signalInvalidArgument(String operationName, LocatedElement node) {
		InvalidArgument error = AtlErrorFactory.eINSTANCE.createInvalidArgument();
		initProblem(error, node);

		signalError(error, "Invalid argument", node);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalFeatureAccessInCollection(String featureName, LocatedElement node) {
		FeatureAccessInCollection error = AtlErrorFactory.eINSTANCE.createFeatureAccessInCollection();
		initProblem(error, node);
		
		error.setFeatureName(featureName);

		signalError(error, "Feature access in collection, " + featureName, node);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
	}

	public Type signalCollectionOperationNotFound(String operationName, LocatedElement node) {
		CollectionOperationNotFound error = AtlErrorFactory.eINSTANCE.createCollectionOperationNotFound();
		initProblem(error, node);
		
		signalError(error, "Collection operation " + operationName + " not supported", node);		
		return AnalyserContext.getTypingModel().newTypeErrorType(error);
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


	

	
}
