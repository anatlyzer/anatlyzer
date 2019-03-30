package anatlyzer.atl.analyser.libtypes;

import anatlyzer.atl.analyser.namespaces.ITypeNamespace;

/**
 * Represents an standard OCL library. This is needed to allow anatlyzer to
 * understand operations from other OCL variants others than ATL.
 * 
 * @author jesus
 */
public interface IOclStandardLibrary {

	public AtlTypeDef getTypeDefOf(ITypeNamespace ns);
	
}
