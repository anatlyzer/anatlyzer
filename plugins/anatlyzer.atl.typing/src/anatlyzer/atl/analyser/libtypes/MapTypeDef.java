package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.types.Type;

public class MapTypeDef extends AtlTypeDef {
	
	protected void init() {
		operation("toString").returnType(AtlTypes.string());
		operation("getKeys").returnType( new SequenceTypeDef(AtlTypes.string()));
		operation("containsKey").param("key", AtlTypes.string()).returnType(AtlTypes.boolean_());
	}

	@Override
	public Type getAnalyserType() {
		throw new UnsupportedOperationException();
	}

}
