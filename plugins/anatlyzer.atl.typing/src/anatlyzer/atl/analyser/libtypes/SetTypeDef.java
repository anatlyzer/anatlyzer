package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public class SetTypeDef extends AtlTypeDef {
	
	private AtlTypeDef containedType;

	public SetTypeDef(AtlTypeDef containedType) {
		this.containedType = containedType;
	}

	protected void init() {
		operation("toString").returnType(AtlTypes.string());
		operation("asSet").returnType(AtlTypes.set(containedType));
	}

	@Override
	public Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newSequenceType(containedType.getAnalyserType());
	}

}
