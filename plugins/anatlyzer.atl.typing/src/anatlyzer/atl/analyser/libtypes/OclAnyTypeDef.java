package anatlyzer.atl.analyser.libtypes;

import static anatlyzer.atl.analyser.libtypes.AtlTypes.*;
import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public class OclAnyTypeDef extends AtlTypeDef {
	
	@Override
	public Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newUnknownType();
	}

	public void init() {
		// logical operators: =, <>
		// toString()
		// oclIsUndefined()
		operation("toString").
			returnType(string());
		
		operation("oclIsUndefined").
			returnType(boolean_());
		
		operation("oclIsKindOf").
			param("type", oclType()).
			returnType(boolean_());

		operation("oclIsTypeOf").
			param("type", oclType()).
			returnType(boolean_());

	}
	
}
