package anatlyzer.ocl.emf.stdlib;

import static anatlyzer.atl.analyser.libtypes.AtlTypes.boolean_;
import static anatlyzer.atl.analyser.libtypes.AtlTypes.oclType;
import static anatlyzer.atl.analyser.libtypes.AtlTypes.string;

import java.util.HashMap;
import java.util.Map;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.libtypes.AtlTypeDef;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.analyser.libtypes.IOclStandardLibrary;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.ITypeNamespace;
import anatlyzer.atl.types.Type;

public class EMFOclStdLibrary implements IOclStandardLibrary {

	protected Map<Class<? extends ITypeNamespace>, AtlTypeDef> typeDefs = new HashMap<Class<? extends ITypeNamespace>, AtlTypeDef>();
	
	public EMFOclStdLibrary() {
		// typeDefs.put(OclAnyInheritedNamespace.class, new EMFOCL_OclAnyTypeDef());
		// typeDefs.put(ClassNamespace.class, new EMFOCL_ClassTypeDef());
		// How I do this??
		// typeDefs.put(SetNamespace.class, new EMFOCL_SetTypeDef(containedType));
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
}
