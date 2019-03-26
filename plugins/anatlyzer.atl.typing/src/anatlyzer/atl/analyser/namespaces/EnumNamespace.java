package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

import org.eclipse.emf.ecore.EEnum;

import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public class EnumNamespace extends AbstractTypeNamespace {

	private EEnum eenum;
	private MetamodelNamespace metamodel;

	public EnumNamespace(MetamodelNamespace metamodelNamespace, EEnum eenum) {
		this.eenum = eenum;
		this.metamodel = metamodelNamespace;
	}

	public String getMetamodelName() {
		return this.metamodel.getName();
	}
	
	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t != null ) 
			return t;
		return AnalyserContext.getErrorModel().signalNoOperationFound(createType(false), operationName, node, null);
	}

	public Operation getAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		if ( operatorSymbol.equals("=") || operatorSymbol.equals("<>") ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		}
		return AnalyserContext.getErrorModel().signalInvalidOperator(operatorSymbol, createType(false), node);
		//throw new UnsupportedOperationException(node.getLocation());
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		return false;
	}

	@Override
	public boolean hasFeature(String featureName) {
		return false;
	}

	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		// Do nothing. 
		// Attaching an operation to an enumer is not valid in ATL,
		// but it might be valid in other dialects. At least don't fail.
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		// Do nothing. 
		// Attaching an operation to an enumer is not valid in ATL,
		// but it might be valid in other dialects. At least don't fail.
	}

	@Override
	public void attachRule(String ruleName, Type returnType, Rule rule) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Type createType(boolean explicitOcurrence) {
		return AnalyserContext.getTypingModel().createEEnum(eenum, this);
	}

	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		throw new UnsupportedOperationException();
	}

}
