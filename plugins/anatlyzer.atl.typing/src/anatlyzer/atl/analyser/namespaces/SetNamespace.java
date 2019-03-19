package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;

public class SetNamespace extends CollectionNamespace {

	public SetNamespace(TypingModel typ, Type nested) {
		super(typ, nested);
	}

	@Override
	public CollectionType newCollectionType(Type nested) {
		return typ.newSetType(nested);
	}
	
	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		boolean b = super.hasOperation(operationName, arguments);
		if ( ! b ) {
			if ( operationName.equals("intersection") ) return true;
		}
		return b;
	}
	
	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		if ( operationName.equals("intersection") ) {
			return typ.newSetType(nested);
		}
		
		Type t = super.getOperationType(operationName, arguments, node);
		return t;
	}
}

