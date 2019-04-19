package anatlyzer.ocl.emf.stdlib;

import static anatlyzer.atl.analyser.libtypes.AtlTypes.boolean_;
import static anatlyzer.atl.analyser.libtypes.AtlTypes.integer;
import static anatlyzer.atl.analyser.libtypes.AtlTypes.oclType;
import static anatlyzer.atl.analyser.libtypes.AtlTypes.seq;
import static anatlyzer.atl.analyser.libtypes.AtlTypes.string;

import java.util.HashMap;
import java.util.Map;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.libtypes.AtlTypeDef;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.analyser.libtypes.IOclStandardLibrary;
import anatlyzer.atl.analyser.namespaces.BooleanNamespace;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.ITypeNamespace;
import anatlyzer.atl.analyser.namespaces.IntegerNamespace;
import anatlyzer.atl.analyser.namespaces.StringNamespace;
import anatlyzer.atl.types.Type;

public class EMFOclStdLibrary implements IOclStandardLibrary {

	protected Map<Class<? extends ITypeNamespace>, AtlTypeDef> typeDefs = new HashMap<Class<? extends ITypeNamespace>, AtlTypeDef>();
	
	protected EMFOCL_StringTypeDef string_ = new EMFOCL_StringTypeDef();
	protected EMFOCL_IntegerTypeDef integer_ = new EMFOCL_IntegerTypeDef();
	protected EMFOCL_BooleanTypeDef boolean_ = new EMFOCL_BooleanTypeDef();
	
	public EMFOclStdLibrary() {
		string_.init();
		integer_.init();
		boolean_.init();
		// typeDefs.put(OclAnyInheritedNamespace.class, new EMFOCL_OclAnyTypeDef());
		// typeDefs.put(ClassNamespace.class, new EMFOCL_ClassTypeDef());
		
		typeDefs.put(StringNamespace.class, string_);
		typeDefs.put(IntegerNamespace.class, integer_);
		typeDefs.put(BooleanNamespace.class, boolean_);
		
		// How I do this??
		// typeDefs.put(SetNamespace.class, new EMFOCL_SetTypeDef(containedType));
	}

	
	public AtlTypeDef string_type() {
		return string_;
	}
	
	public AtlTypeDef integer_type() {
		return integer_;
	}
	
	public AtlTypeDef boolean_type() {
		return boolean_;
	}
	
	@Override
	public AtlTypeDef getTypeDefOf(ITypeNamespace ns) {
		if ( ns instanceof ClassNamespace ) {
			return new EMFOCL_ClassTypeDef(ns);
		}
		
		if ( typeDefs.containsKey(ns.getClass()))
			return typeDefs.get(ns.getClass());
		return null;
	}

	
	public static class EMFOCL_ClassTypeDef extends AtlTypeDef {
		
		private ITypeNamespace ns;

		public EMFOCL_ClassTypeDef(ITypeNamespace ns) {
			this.ns = ns;
			init();
		}

		@Override
		public Type getAnalyserType() {
			return ns.createType(false);
		} 

		protected void init() {
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
			
			operation("oclAsSet").				
			returnType(src -> new EMFOCL_SetTypeDef(src));
			
			// Also "asSet" because oclAsSet is transformed into asSet EMFOCL2UseFixer.Pre while all the library is fill
			operation("asSet").				
				returnType(src -> new EMFOCL_SetTypeDef(src));
		
		}		
	}
	
	public static class EMFOCL_SetTypeDef extends AtlTypeDef {
		
		private AtlTypeDef containedType;

		public EMFOCL_SetTypeDef(AtlTypeDef containedType) {
			this();
			this.containedType = containedType;
		}

		public EMFOCL_SetTypeDef() {
			operation("toString").returnType(AtlTypes.string());
		}

		@Override
		public Type getAnalyserType() {
			return AnalyserContext.getTypingModel().newSequenceType(containedType.getAnalyserType());
		}
	}

	public class EMFOCL_StringTypeDef extends AtlTypeDef {

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
			
			operation("toUpperCase").returnType(string());
			operation("toLowerCase").returnType(string());
			
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
			
//			operation("split").
//				param("regexp", string()).
//				returnType(seq(string()));
//			operation("replaceAll").
//				param("c1", string()).
//				param("c2", string()).
//				returnType(string());
			
//			operation("regexReplaceAll").
//				param("regex", string()).
//				param("replacement", string()).
//				returnType(string());
//			operation("writeTo").
//				param("fileName", string()).
//				returnType(boolean_());
//			operation("println").
//				param("s", string()).
//				returnType(string());

//			// Non-documented, looked up in the source code
//			operation("firstToLower").
//				returnType(string());
//			operation("lastIndexOf").
//				param("index", string()).
//				returnType(integer());
//			operation("writeToWithCharset").
//				param("fileName", string()).
//				param("charset", string()).
//				returnType(boolean_());

			// EMFOclSpecific
			operation("at").
				param("index", integer_type()).
				returnType(string_type());

			operation("matches").
				param("text", string_type()).
				returnType(boolean_type());

		}

		@Override
		public Type getAnalyserType() {
			return AnalyserContext.getTypingModel().newStringType();
		}

	}

	public class EMFOCL_IntegerTypeDef extends AtlTypeDef {
		
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
		
		}
	}

	public class EMFOCL_BooleanTypeDef extends AtlTypeDef {
		
		@Override
		public Type getAnalyserType() {
			return AnalyserContext.getTypingModel().newBooleanType();
		}

		public void init() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
