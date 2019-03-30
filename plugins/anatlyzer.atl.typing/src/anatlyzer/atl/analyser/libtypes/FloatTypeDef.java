package anatlyzer.atl.analyser.libtypes;

import static anatlyzer.atl.analyser.libtypes.AtlTypes.*;
import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;


public class FloatTypeDef extends AtlTypeDef {
	
	@Override
	public Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newFloatType();
	}

	protected void init() {
		// Ocl operations
		// Operators hard-coded in the integer namepace
		// comparison operators: <, >, >=, <=;
		// binary operators: *, +, -, /, max(), min();
		operation("max").
			param("v", float_()).
			returnType(float_());
		operation("min").
			param("v", float_()).
			returnType(float_());
		operation("abs").
			returnType(float_());
		
		operation("floor").
			returnType(integer());
		operation("round").
			returnType(integer());
	
		// Atl-specific operations
		operation("longValue").
			returnType(integer()); // should be "long", how is that possible in OCL?

		operation("cos").returnType(float_());
		operation("sin").returnType(float_());
		operation("tan").returnType(float_());
		operation("acos").returnType(float_());
		operation("asin").returnType(float_());
		operation("toDegrees").returnType(float_());
		operation("toRadians").returnType(float_());
		operation("exp").returnType(float_());
		operation("log").returnType(float_());
		operation("sqrt").returnType(float_());
		
		// not documented
		operation("round").returnType(float_());
	}
	
}
