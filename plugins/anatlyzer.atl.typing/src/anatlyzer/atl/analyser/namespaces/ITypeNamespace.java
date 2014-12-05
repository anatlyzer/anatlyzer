package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public interface ITypeNamespace {

	Type getFeatureType(String featureName, LocatedElement node);
	Type getOperationType(String operationName, Type[] arguments, LocatedElement node);
	Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node);
	
	boolean hasOperation(String operationName, Type[] arguments);
	boolean hasFeature(String featureName);
	
	void extendType(String featureName, Type returnType, Attribute attrDefinition);
	void extendType(String operationName, Type returnType, Operation opDefinition);
	void attachRule(String ruleName, Type returnType, Rule rule);
	
	boolean   hasAttachedOperation(String operationName);
	Operation getAttachedOperation(String operationName);
	
	OclFeature getAttachedOclFeature(String attributeOrOperationName);
	
	
	Type createType(boolean explicitOcurrence);

}
