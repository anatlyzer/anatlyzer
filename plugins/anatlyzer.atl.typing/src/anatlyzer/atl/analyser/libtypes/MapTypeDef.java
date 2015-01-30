package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.types.Type;

public class MapTypeDef extends AtlTypeDef {
	
	protected void init() {
		operation("toString").returnType(AtlTypes.string());
		operation("getKeys").returnType( new SequenceTypeDef(AtlTypes.string()));
	}

	@Override
	protected Type getAnalyserType() {
		throw new UnsupportedOperationException();
	}

}
