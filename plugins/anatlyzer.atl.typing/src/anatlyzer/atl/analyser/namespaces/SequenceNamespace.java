package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Type;


public class SequenceNamespace extends CollectionNamespace {

	public SequenceNamespace(TypingModel typ, Type nested) {
		super(typ, nested);
	}

	@Override
	protected CollectionType newCollectionType(Type nested) {
		return typ.newSequenceType(nested);
	}

}

