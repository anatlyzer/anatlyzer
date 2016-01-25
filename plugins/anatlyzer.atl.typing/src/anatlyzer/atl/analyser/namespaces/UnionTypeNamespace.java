package anatlyzer.atl.analyser.namespaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.type.ErrorType;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.typeconstraints.ITypeConstraint;
import anatlyzer.atl.analyser.typeconstraints.UnionTypeConstraint;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public class UnionTypeNamespace extends AbstractTypeNamespace implements ITypeNamespace {

	private UnionType	type;

	public UnionTypeNamespace(UnionType type) {
		this.type = type;
	}
	
	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		ArrayList<Type> results = new ArrayList<Type>();
		ArrayList<Type> noFeatureTypes = new ArrayList<Type>();
		
		// This is complicated and problematic, because a getFeature to e.g., a ClassNamespace
		// may create a "recovery error"...
		for(Type t : type.getPossibleTypes()) {
			ITypeNamespace ns = (ITypeNamespace) t.getMetamodelRef();
			if ( ns.hasFeature(featureName) ) {
				results.add(ns.getFeatureType(featureName, node));
			} else {
				noFeatureTypes.add(t);
			}
		}
		
		if ( results.size() == 0 ) {
			return AnalyserContext.getErrorModel().signalNoFeatureInUnionType(type, featureName, node);
		}
		
		Type t1 = AnalyserContext.getTypingModel().getCommonType(results);
		
		if ( noFeatureTypes.size() != 0 ) {
			AnalyserContext.getErrorModel().warningMissingFeatureInUnionType(noFeatureTypes, featureName, node);
		}
		
		return t1;
	}
	
	@Override
	public boolean hasFeature(String featureName) {
		for(Type t : type.getPossibleTypes()) {
			ITypeNamespace ns = (ITypeNamespace) t.getMetamodelRef();
			if ( ns.hasFeature(featureName) ) 
				return true;
		}
		return false;
	}
	

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type tsup = super.getOperationType(operationName, arguments, node);
		if ( tsup != null ) {
			return tsup;
		}
		
		ArrayList<Type> results = new ArrayList<Type>();
		ArrayList<Type> noFeatureTypes = new ArrayList<Type>();
		
		// This is complicated and problematic, because a getFeature to e.g., a ClassNamespace
		// may create a "recovery error"...
		for(Type t : type.getPossibleTypes()) {
			ITypeNamespace ns = (ITypeNamespace) t.getMetamodelRef();
			if ( ns.hasOperation(operationName, arguments) ) {
				results.add(ns.getOperationType(operationName, arguments, node));
			} else {
				noFeatureTypes.add(t);
			}
		}
		
		if ( results.size() == 0 ) {
			// TODO: Be more precise, it is not a feature, it is an operation
			return AnalyserContext.getErrorModel().signalNoFeatureInUnionType(type, operationName, node);
		}
		
		Type t1 = AnalyserContext.getTypingModel().getCommonType(results);
		
		if ( noFeatureTypes.size() != 0 ) {
			AnalyserContext.getErrorModel().warningMissingFeatureInUnionType(noFeatureTypes, operationName, node);
		}
		
		return t1;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		Type t = super.getOperatorType(operatorSymbol, optionalArgument, node);
		if ( t == null ) {
			// This may generate more than one error, but it is the easiest way by now
			List<Type> types = type.getPossibleTypes().stream().map(possibleType -> {
				ITypeNamespace ns = (ITypeNamespace) possibleType.getMetamodelRef();
				return ns.getOperatorType(operatorSymbol, optionalArgument, node);				
			}).filter(opType -> !(opType instanceof ErrorType)).collect(Collectors.toList());
			return AnalyserContext.getTypingModel().getCommonType(types);
		}
		return t;
	}

	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		throw new UnsupportedOperationException(featureName);		
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		throw new UnsupportedOperationException(operationName);				
	}

	@Override
	public void attachRule(String operationName, Type returnType, Rule r) {
		throw new UnsupportedOperationException(operationName);				
	}
	
	@Override
	public Type createType(boolean explicitOcurrence) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		return false;
	}

	public Operation getAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	
	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ITypeConstraint newTypeConstraint() {
		return new UnionTypeConstraint(type);
	}
}
