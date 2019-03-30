package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.Type;
import static anatlyzer.atl.analyser.libtypes.AtlTypes.*;

/**
 * Definitions for the built-in operations in the String built-in type.
 * The definitions comply to the ATL Guide, but it is extended with non-documented
 * operations when needed.
 */
public class StringTypeDef extends AtlTypeDef {

	protected void init() {
		// Ocl defined operations 
		operation("size").returnType(integer());
		operation("concat").
			param("s", string()).
			returnType(string());
		operation("substring").
			param("lower", integer()).
			param("upper", integer()).
			returnType(AtlTypes.string());
		operation("toInteger").returnType(AtlTypes.integer());
		operation("toReal").returnType(AtlTypes.float_());
		
		// Hard-coded in the corresponding namespace...
		// comparison operators: <, >, >=, <=;
		// the string concatenation operator (+) can be used as a shortcut for the string concat() function; 
		
		// ATL-specific operations
		operation("toLower").returnType(string());
		operation("toUpper").returnType(string());
		operation("toSequence").returnType(seq(string()));
		operation("trim").returnType(AtlTypes.string());
		operation("compareTo").
			param("other", string()).
			returnType(integer());
		operation("startsWith").
			param("s", string()).
			returnType(boolean_());
		operation("endsWith").
			param("s", string()).
			returnType(boolean_());
		operation("indexOf").
			param("search", string()).
			returnType(integer());
		operation("split").
			param("regexp", string()).
			returnType(seq(string()));
		operation("replaceAll").
			param("c1", string()).
			param("c2", string()).
			returnType(string());
		operation("regexReplaceAll").
			param("regex", string()).
			param("replacement", string()).
			returnType(string());
		operation("writeTo").
			param("fileName", string()).
			returnType(boolean_());
		operation("println").
			param("s", string()).
			returnType(string());

		// Non-documented, looked up in the source code
		operation("firstToLower").
			returnType(string());
		operation("lastIndexOf").
			param("index", string()).
			returnType(integer());
		operation("writeToWithCharset").
			param("fileName", string()).
			param("charset", string()).
			returnType(boolean_());
	}

	@Override
	public Type getAnalyserType() {
		return AnalyserContext.getTypingModel().newStringType();
	}

}
