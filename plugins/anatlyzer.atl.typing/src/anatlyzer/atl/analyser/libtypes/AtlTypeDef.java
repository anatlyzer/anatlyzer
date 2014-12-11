package anatlyzer.atl.analyser.libtypes;

import java.util.HashMap;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public abstract class AtlTypeDef {
	protected HashMap<String, AtlOperationDef> operations = new HashMap<String, AtlOperationDef>();
	
	protected AtlOperationDef operation(String name) {
		AtlOperationDef op = new AtlOperationDef(name);
		operations.put(name, op);
		return op;
	}
	
	public boolean hasOperation(String operationName) {
		return operations.containsKey(operationName);
	}
	
	public Type getOperationReturnType(String operationName) {
		AtlOperationDef op = operations.get(operationName);
		if ( op == null ) {
			return null; 
			// throw new IllegalArgumentException("No operation " + operationName + " " + this.getClass().getName());
		}
		
		return op.getReturnType().getAnalyserType();
	}

	protected abstract Type getAnalyserType();
	
}
