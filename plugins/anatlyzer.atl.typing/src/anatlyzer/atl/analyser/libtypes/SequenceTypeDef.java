package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public class SequenceTypeDef extends AtlTypeDef {
	
	private AtlTypeDef containedType;

	public SequenceTypeDef(AtlTypeDef containedType) {
		this.containedType = containedType;
	}

	protected void init() {
		operation("toString").returnType(AtlTypes.string());
	}

	@Override
	protected Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newSequenceType(containedType.getAnalyserType());
	}

}
