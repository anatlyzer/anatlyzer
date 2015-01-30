package anatlyzer.atl.analyser.namespaces;

import java.util.HashMap;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public abstract class PrimitiveTypeNamespace extends AbstractTypeNamespace implements ITypeNamespace {

	private HashMap<String, VirtualFeature<PrimitiveTypeNamespace, Attribute>> features = 
			new HashMap<String, VirtualFeature<PrimitiveTypeNamespace, Attribute>>();
	private HashMap<String, VirtualFeature<PrimitiveTypeNamespace, Operation>> operations = 
			new HashMap<String, VirtualFeature<PrimitiveTypeNamespace, Operation>>();

	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		Type t = features.containsKey(featureName) ? features.get(featureName).returnType : null;
		if ( t != null )
			return t;

		t = super.getFeatureType(featureName, node);
		if ( t != null ) {
			return t;
		}
		
		// TODO: Should be no feature found!
		return AnalyserContext.getErrorModel().signalNoOperationFound(this.createType(false), featureName, node, null);
	}
	
	@Override
	public boolean hasFeature(String featureName) {
		if ( super.hasFeature(featureName) ) {
			return true;
		}
		return features.containsKey(featureName);
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		if ( super.hasOperation(operationName, arguments) ) {
			return true;
		}
		return operations.containsKey(operationName);
	}

	public Operation getAttachedOperation(String operationName) {
		return operations.get(operationName).definition;
	}
	
	public boolean hasAttachedOperation(String operationName) {
		return operations.containsKey(operationName);
	}
	
	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		VirtualFeature<?, ? extends OclFeature> vf = operations.get(attributeOrOperationName);
		if ( vf == null ) {
			vf = features.get(attributeOrOperationName);
		}
		return vf == null ? null : vf.definition;
	}


	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t == null && operations.containsKey(operationName) ) {
			t = operations.get(operationName).returnType;
		}
		
		if ( t == null ) {
			return null;
		}
		return t;
	}


	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		features.put(featureName, new VirtualFeature<PrimitiveTypeNamespace, Attribute>(this, featureName, returnType, attrDefinition));
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		operations.put(operationName, new VirtualFeature<PrimitiveTypeNamespace, Operation>(this, operationName, returnType, opDefinition));
	}

	/**
	 * Rules cannot be attached to primitive types
	 */
	@Override
	public void attachRule(String operationName, Type returnType, Rule r) {
		throw new UnsupportedOperationException(operationName);				
	}


}
