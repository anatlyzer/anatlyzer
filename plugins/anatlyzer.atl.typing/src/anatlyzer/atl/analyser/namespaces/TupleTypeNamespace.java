package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public class TupleTypeNamespace implements ITypeNamespace {

	private TupleType	tuple;

	public TupleTypeNamespace(TupleType tuple) {
		this.tuple = tuple;
	}
	
	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		for(TupleAttribute attr : tuple.getAttributes()) {
			if ( attr.getName().equals(featureName) ) 
				return attr.getType();
		}
		return AnalyserContext.getErrorModel().signalNoTupleFeature(tuple, featureName, node);
	}

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		return AnalyserContext.getErrorModel().signalNoOperationFound(tuple, operationName, node, null);
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		return AnalyserContext.getErrorModel().signalNoOperationFound(tuple, operatorSymbol, node, null);
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
	public boolean hasFeature(String featureName) {
		for(TupleAttribute attr : tuple.getAttributes()) {
			if ( attr.getName().equals(featureName) ) 
				return true;
		}
		return false;
	}

	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void attachRule(String ruleName, Type returnType, Rule rule) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public Type createType(boolean explicitOcurrence) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		throw new UnsupportedOperationException();
	}

}
