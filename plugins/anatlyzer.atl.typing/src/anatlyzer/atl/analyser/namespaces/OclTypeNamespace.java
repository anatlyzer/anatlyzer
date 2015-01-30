package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Type;

public class OclTypeNamespace implements ITypeNamespace {

	private TypingModel	typ;

	public OclTypeNamespace(TypingModel typingModel) {
		this.typ = typingModel;
	}

	@Override
	public boolean hasFeature(String featureName) {
		return featureName.equals("name");
	}
	
	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		if ( featureName.equals("name" ) ) 
			return typ.newStringType();
		
		throw new UnsupportedOperationException(featureName);
	}

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = AtlTypes.oclType().getOperationReturnType(operationName);
		if ( t == null ) {
			t = AnalyserContext.getErrorModel().signalNoOperationFound(AnalyserContext.getTypingModel().newStringType(), operationName, node, null);
		}
		return t;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		throw new UnsupportedOperationException();
	}

	public Operation getAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}
	
	public boolean hasAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
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
	public Type createType(boolean explicitOcurrence) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void attachRule(String ruleName, Type returnType, Rule rule) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		throw new UnsupportedOperationException();
	}

	

}
