package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.types.Type;

public class VirtualFeature<T extends ITypeNamespace, F> {
	protected String featureName;
	protected T receptorType;
	protected Type returnType;
	protected F	definition;
	
	public VirtualFeature(T receptorType, String featureName, Type returnType, F definition) {
		this.receptorType = receptorType;
		this.featureName  = featureName;
		this.returnType   = returnType;
		this.definition = definition;
	}
}