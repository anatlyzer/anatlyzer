package anatlyzer.atl.analyser.namespaces;

import org.eclipse.emf.ecore.EClass;

public interface IMetamodelNamespace {

	ITypeNamespace getClassifier(String name);
	
	/**
	 * Looks for an EClass in the meta-model, if not found,
	 * it looks in referenced packages.
	 * 
	 * @param c
	 * @return
	 */
	IClassNamespace getClass(EClass c);

}