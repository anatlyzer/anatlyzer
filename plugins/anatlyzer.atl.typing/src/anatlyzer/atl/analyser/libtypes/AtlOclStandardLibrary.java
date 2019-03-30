package anatlyzer.atl.analyser.libtypes;

import java.util.HashMap;
import java.util.Map;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.ITypeNamespace;
import anatlyzer.atl.analyser.namespaces.IntegerNamespace;
import anatlyzer.atl.analyser.namespaces.StringNamespace;

public class AtlOclStandardLibrary implements IOclStandardLibrary {

	protected Map<Class<? extends ITypeNamespace>, AtlTypeDef> typeDefs = new HashMap<Class<? extends ITypeNamespace>, AtlTypeDef>();
	
	public AtlOclStandardLibrary() {
		typeDefs.put(StringNamespace.class, AtlTypes.string());
		typeDefs.put(IntegerNamespace.class, AtlTypes.integer());
	}
	
	@Override
	public AtlTypeDef getTypeDefOf(ITypeNamespace ns) {
		if ( ns instanceof ClassNamespace ) {
			// return new EMFOCL_ClassTypeDef(ns);
			// TODO: Handle as in EMF/OCL
		}
		
		if ( typeDefs.containsKey(ns.getClass()))
			return typeDefs.get(ns.getClass());
		return null;
	}

}
