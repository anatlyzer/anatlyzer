package witness.generator.mmext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import witness.generator.MetaModel;

public class FullMetamodelStrategy extends AbstractMetamodelExtension implements IMetamodelExtensionStrategy {
	
	/**
	 * Copies everything that is not already in the errorMM  from the languageMM.
	 */
	public void extend(EPackage errorMM, MetaModel effectiveMM, MetaModel languageMM) {
		System.out.println("Using 'Full Metamodel' strategy");
		
		Copier copier = new Copier();
		
		for (EClassifier lc : languageMM.getEClassifiers()) {
			EClassifier errorClassifier = errorMM.getEClassifier(lc.getName());
			if ( errorClassifier == null ) {
				EClassifier newClass = (EClassifier) copier.copy(lc);
				errorMM.getEClassifiers().add(newClass);
				continue;
			}
			
			// Now map lc to errorClassifier in the copier in case they need to be resolved
			copier.put(lc, errorClassifier);
			
			if ( errorClassifier instanceof EClass ){
				EClass errorClass = (EClass) errorClassifier;
				EClass langClass  = (EClass) lc;
				for (EStructuralFeature f : langClass.getEStructuralFeatures()) {
					if ( errorClass.getEStructuralFeature(f.getName()) == null ) {
						EStructuralFeature newFeature = (EStructuralFeature) copier.copy(f);
						errorClass.getEStructuralFeatures().add(newFeature);
					} else {
						copier.put(f, errorClass.getEStructuralFeature(f.getName()));
					}
				}
			}
		}
		
		
		copier.copyReferences();
	
		removeUnnecessaryElements(errorMM);
	}


}
