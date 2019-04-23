package witness.generator.mmext;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import anatlyzer.atl.witness.IViewMetamodel;
import witness.generator.MetaModel;


public class ViewMetamodelStrategy extends AbstractMetamodelExtension implements IMetamodelExtensionStrategy {
	
	private IViewMetamodel viewMetamodel;

	public ViewMetamodelStrategy(IViewMetamodel viewMetamodel) {
		this.viewMetamodel = viewMetamodel;
	}

	public void extend(EPackage errorMM, MetaModel effectiveMM, MetaModel languageMM) {	
		// TODO: This is not straightforward...
	
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
