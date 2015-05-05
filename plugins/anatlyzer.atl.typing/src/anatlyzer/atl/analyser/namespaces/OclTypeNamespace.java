package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.analyser.typeconstraints.ITypeConstraint;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Type;

public class OclTypeNamespace implements ITypeNamespace {

	private TypingModel	typ;
	private Type theType;

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
		if ( operatorSymbol.equals("=") || operatorSymbol.equals("<>") ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		}

		return AnalyserContext.getErrorModel().signalInvalidOperator(operatorSymbol, getTheType(), node);
	}
	
	public Type getTheType() {
		if ( theType == null ) {
			this.theType = AnalyserContext.getTypingModel().newOclType();
		}
		return theType;
	}
	
	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		return AtlTypes.oclType().hasOperation(operationName);		
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

	@Override
	public ITypeConstraint newTypeConstraint() {
		throw new UnsupportedOperationException();
	}

}
