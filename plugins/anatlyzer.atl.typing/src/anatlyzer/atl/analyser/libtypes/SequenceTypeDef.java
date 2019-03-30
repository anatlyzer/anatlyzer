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
		operation("size").returnType(AtlTypes.string());
		operation("isEmpty").returnType(AtlTypes.boolean_());
		
		operation("includes").
			param("o", AtlTypes.oclAny()).
			returnType(AtlTypes.boolean_());
		operation("excludes").
			param("o", AtlTypes.oclAny()).
			returnType(AtlTypes.boolean_());

		operation("first").returnType(containedType);
		operation("last").returnType(containedType);
		
		operation("asSet").returnType(AtlTypes.set(containedType));

	}

	@Override
	public Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newSequenceType(containedType.getAnalyserType());
	}

}
