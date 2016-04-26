package witness.generator.mmext;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import witness.generator.MetaModel;

/**
 * This strategy leaves the error meta-model as is.
 * @author jesus
 *
 */
public class ErrorPathMetamodelStrategy extends AbstractMetamodelExtension implements IMetamodelExtensionStrategy {
	
	public void extend(EPackage errorMM, MetaModel effectiveMM, MetaModel languageMM) {	
		System.out.println("Using 'Error Path Metamodel' strategy");
				
		// extend error meta-model with concrete children classes of abstract leaf classes
		extendMetamodelWithConcreteLeaves(errorMM, effectiveMM);
		extendMetamodelWithConcreteLeaves(errorMM, languageMM);			

		removeUnnecessaryElements(errorMM);
	}
	
	/**
	 * Overriden to avoid adding mandatory features when a new class is added due to extendWithConcreteLeaves
	 */
	@Override
	protected EClassifier addClassifier2Metamodel (EClassifier clazz, List<EClassifier> classifiers) {
		EClassifier newClass = clone(clazz);
		classifiers.add(newClass);
		//if (clazz instanceof EClass && newClass instanceof EClass) 
		//  extendClassWithMandatory((EClass)newClass, (EClass)clazz);
		return newClass;
	}

	
}
