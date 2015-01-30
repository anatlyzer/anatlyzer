package anatlyzer.atl.analyser.namespaces;

import java.util.HashMap;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;


public class OclAnyInheritedNamespace implements ITypeNamespace {

	protected HashMap<String, VirtualFeature<OclAnyInheritedNamespace, Attribute>> features = 
			new HashMap<String, VirtualFeature<OclAnyInheritedNamespace, Attribute>>();
	protected HashMap<String, VirtualFeature<OclAnyInheritedNamespace, Operation>> operations = 
			new HashMap<String, VirtualFeature<OclAnyInheritedNamespace, Operation>>();
	
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		features.put(featureName, new VirtualFeature<OclAnyInheritedNamespace, Attribute>(this, featureName, returnType, attrDefinition));
	}

	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		VirtualFeature<OclAnyInheritedNamespace, Attribute> r = features.get(featureName);
		if ( r == null )
			return null;
		return r.returnType;
	}

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		VirtualFeature<OclAnyInheritedNamespace, Operation> r = operations.get(operationName);
		if ( r == null )
			return null;
		return r.returnType;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		return null;
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		return operations.containsKey(operationName);
	}

	@Override
	public boolean hasFeature(String featureName) {
		return features.containsKey(featureName);
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		
	}

	@Override
	public void attachRule(String ruleName, Type returnType, Rule rule) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasAttachedOperation(String operationName) {
		return hasOperation(operationName, null);
	}

	@Override
	public Operation getAttachedOperation(String operationName) {
		return operations.get(operationName).definition;
	}

	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		return features.get(attributeOrOperationName).definition;
	}

	@Override
	public Type createType(boolean explicitOcurrence) {
		throw new UnsupportedOperationException();
	}

}
