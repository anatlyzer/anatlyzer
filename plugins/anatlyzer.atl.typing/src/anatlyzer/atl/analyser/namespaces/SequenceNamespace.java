package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.libtypes.AtlTypeDef;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;


public class SequenceNamespace extends CollectionNamespace {

	public SequenceNamespace(TypingModel typ, Type nested) {
		super(typ, nested);
	}

	@Override
	public CollectionType newCollectionType(Type nested) {
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
		Type t = null;
		if ( operationName.equals("first") ) t = nested;
		if ( operationName.equals("last") ) t = nested;
		if ( operationName.equals("at") ) t = nested; // TODO: Indicate somehow that at may return null
		if ( operationName.equals("indexOf") ) {
			// TODO: indexOf may return a "bottom" value???
			t = typ.newIntegerType();
		}

		if ( t == null )
			t = super.getOperationType(operationName, arguments, node);
	
		// check the arguments... 
		// Currently the check is dual, part hardcoded in the namespace classes and
		// part in the library definition. There are inconsistencies that cause IllegalArgumentException
		// when it is correctly checked in the namespace but the operation is not added yet to the library
		AtlTypeDef typeDef = getLibTypeDef();
		try {
			checkArguments(operationName, 
					typeDef.getOperationParameters(operationName), 
					typeDef.getOperationParametersNames(operationName), 
					arguments, node);
		} catch ( IllegalArgumentException e ) { }
			
		return t;
	}

	public AtlTypeDef getLibTypeDef() {
		return AtlTypes.seq();
	}

}

