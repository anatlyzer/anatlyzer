package witness.generator.mmext;

import org.eclipse.emf.ecore.EPackage;

import witness.generator.MetaModel;

public class MandatoryFullMetamodelStrategy extends AbstractMetamodelExtension implements IMetamodelExtensionStrategy {
	
	public void extend(EPackage errorMM, MetaModel effectiveMM, MetaModel languageMM) {
		System.out.println("Using 'Mandatory Full Metamodel' strategy");
		
		// extend error meta-model with mandatory classes in language meta-model 
		extendMetamodelWithMandatory(errorMM, languageMM);
	
		// extend error meta-model with concrete children classes of abstract leaf classes
		extendMetamodelWithConcreteLeaves(errorMM, effectiveMM);
		extendMetamodelWithConcreteLeaves(errorMM, languageMM);	
	
		removeUnnecessaryElements(errorMM);
	}
	
}
