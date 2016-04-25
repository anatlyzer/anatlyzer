package witness.generator.mmext;

import org.eclipse.emf.ecore.EPackage;

import witness.generator.MetaModel;

public class MandatoryEffectiveMetamodelStrategy extends AbstractMetamodelExtension implements IMetamodelExtensionStrategy {
	
	public void extend(EPackage errorMM, MetaModel effectiveMM, MetaModel languageMM) {
		System.out.println("Using 'Mandatory Effective Metamodel' strategy");
		
		// extend error meta-model with mandatory classes in effective meta-model 
		extendMetamodelWithMandatory(errorMM, effectiveMM);
	
		// extend error meta-model with concrete children classes of abstract leaf classes
		extendMetamodelWithConcreteLeaves(errorMM, effectiveMM);	
	
		removeUnnecessaryElements(errorMM);
	}
	
}
