package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;

public class BagNamespace extends CollectionNamespace {

	public BagNamespace(TypingModel typ, Type nested) {
		super(typ, nested);
	}

	@Override
	public CollectionType newCollectionType(Type nested) {
		return typ.newBagType(nested);
	}
	
	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		boolean b = super.hasOperation(operationName, arguments);	
		return b;
	}
	
	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		return t;
	}
}

