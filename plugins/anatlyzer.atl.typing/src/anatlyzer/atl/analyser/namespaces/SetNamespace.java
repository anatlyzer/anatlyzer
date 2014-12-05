package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Type;

public class SetNamespace extends CollectionNamespace {

	public SetNamespace(TypingModel typ, Type nested) {
		super(typ, nested);
	}

	@Override
	protected CollectionType newCollectionType(Type nested) {
		return typ.newSetType(nested);
	}

}

