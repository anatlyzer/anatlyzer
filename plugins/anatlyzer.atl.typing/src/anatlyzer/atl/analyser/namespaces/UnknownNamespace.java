package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public class UnknownNamespace extends AbstractTypeNamespace {

	private Type selfType;
	
	public UnknownNamespace(Type u) {
		selfType = u;
	}

	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		return AnalyserContext.getErrorModel().signalNoFeatureInOclAny(featureName, node);
	}

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t == null ) {
			return AnalyserContext.getErrorModel().signalNoOperationFound(selfType, operationName, node, null);
		}
		return t;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		if ( operatorSymbol.equals("=") ) return AnalyserContext.getTypingModel().newBooleanType();
		if ( operatorSymbol.equals("<>") ) return AnalyserContext.getTypingModel().newBooleanType();
		
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + " not support " + operatorSymbol + " in line" + node.getLocation());
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
		return false;
	}

	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		AnalyserContext.getOclAnyInheritedNamespace().extendType(featureName, returnType, attrDefinition);;
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		AnalyserContext.getOclAnyInheritedNamespace().extendType(operationName, returnType, opDefinition);
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
