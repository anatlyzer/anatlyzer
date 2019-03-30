package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public class OclTypeDef extends AtlTypeDef {
	
	protected void init() {
		operation("toString").returnType(AtlTypes.string());
	}

	@Override
	public Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newOclType();		
	}
}
