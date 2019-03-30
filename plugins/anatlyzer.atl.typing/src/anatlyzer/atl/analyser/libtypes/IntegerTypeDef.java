package anatlyzer.atl.analyser.libtypes;

import static anatlyzer.atl.analyser.libtypes.AtlTypes.*;
import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;

public class IntegerTypeDef extends AtlTypeDef {
	
	@Override
	public Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newIntegerType();
	}

	public void init() {
		// Ocl operations
		// Operators hard-coded in the integer namepace
		// comparison operators: <, >, >=, <=;
		// binary operators: *, +, -, /, max(), min();
		operation("max").
			param("v", integer()).
			returnType(integer());
		operation("min").
			param("v", integer()).
			returnType(integer());
		operation("abs").
			returnType(integer());

		operation("div").
			param("v", integer()).
			returnType(integer());
		operation("mod").
			param("v", integer()).
			returnType(integer());
	
		// Atl-specific operations
		operation("longValue").
			returnType(integer()); // should be "long", how is that possible in OCL?
		
		// Looked up in the source code
		operation("toHexString").
			returnType(string()); 
		operation("toBinaryString").
			returnType(string()); 
	
	}
	
}
