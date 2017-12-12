/**
 * It adds a new mandatory metaclass (i.e., a new class which will be the type of a reference with lower bound 1) 
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class AddObligatoryMetaclass extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate source classes (all)
		List<EClass> candidates = new ArrayList<EClass>();
		candidates.addAll(metamodel.getEClasses());
		
		// generate each possible mutant
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		for (EClass clazz : candidates) {
			// add new class to the meta-model 
			EPackage pack = (EPackage)clazz.eContainer(); 
			EClass   newClass = factory.createEClass();
			newClass.setName( this.getRandomString(6) );
			pack.getEClassifiers().add(newClass);
			
			// add new optional reference to the candidate class
			EReference   newRef   = factory.createEReference();
			newRef.setEType(newClass);
			newRef.setLowerBound(1);
			newRef.setName( this.getRandomString(6) );
			clazz.getEStructuralFeatures().add(newRef);
			
			// register mutation
			EAnnotation annotation = registerMutation(new ENamedElement[]{newClass, newRef}, null, null);

			registerUndo(metamodel, () -> { 
				// unregister mutation
				unregisterMutation(annotation);
				
				// undo the mutation
				clazz.getEStructuralFeatures().remove(newRef);
				pack.getEClassifiers().remove(newClass);
			});
		}
	}
	
	@Override
	public boolean isBreaking() {
		return true;
	}	
}
