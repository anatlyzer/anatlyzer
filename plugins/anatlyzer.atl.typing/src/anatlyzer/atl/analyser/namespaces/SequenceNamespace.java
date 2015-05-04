package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atlext.ATL.LocatedElement;


public class SequenceNamespace extends CollectionNamespace {

	public SequenceNamespace(TypingModel typ, Type nested) {
		super(typ, nested);
	}

	@Override
	protected CollectionType newCollectionType(Type nested) {
		return typ.newSequenceType(nested);
	}
	
	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		boolean b = super.hasOperation(operationName, arguments);
		if (!b) {
			if ( operationName.equals("first") || operationName.equals("indexOf") ||
				 operationName.equals("at") || operationName.equals("last")) {
				b = true;
			}
		}
		return b;
	}
	
	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		if ( operationName.equals("first") ) return nested;
		if ( operationName.equals("last") ) return nested;
		if ( operationName.equals("at") ) return nested; // TODO: Indicate somehow that at may return null
		if ( operationName.equals("indexOf") ) {
			// TODO: indexOf may return a "bottom" value???
			return typ.newIntegerType();
		}

		Type t = super.getOperationType(operationName, arguments, node);
		return t;
	}


}

