package witness.generator.mmext;

import org.eclipse.emf.ecore.EPackage;

import witness.generator.MetaModel;

public interface IMetamodelExtensionStrategy {

	void extend(EPackage errorMM, MetaModel effectiveMM, MetaModel languageMM);
	void adaptDataTypes(EPackage errorMM);
}
