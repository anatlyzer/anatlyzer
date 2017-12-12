/**
 * It moves the relevant fields of a metaclass to a new metaclass.
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class ExtractMetaclass extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate source classes (having 2 or more features)
		List<EClass> candidates = new ArrayList<EClass>();
		for (EClass clazz : metamodel.getEClasses())
			if (clazz.getEStructuralFeatures().size()>1)
				candidates.add(clazz);
		
		// generate a mutant for each class
		EcoreFactory factory   = EcoreFactory.eINSTANCE;
		Random randomGenerator = new Random();
		for (EClass clazz : candidates) {
			// add new class to the meta-model 
			EPackage pack = (EPackage)clazz.eContainer(); 
			EClass   newClass = factory.createEClass();
			newClass.setName( clazz.getName()+"2" );
			pack.getEClassifiers().add(newClass);
			
			// add some random features from the candidate class to the new extracted class
			List<EStructuralFeature> feats = clazz.getEStructuralFeatures().stream().filter(f -> randomGenerator.nextInt(2) == 0).collect(Collectors.toList());
			if (feats.size() == clazz.getEStructuralFeatures().size()) feats.remove( randomGenerator.nextInt(feats.size()) );
			else if (feats.size() == 0) feats.add( clazz.getEStructuralFeatures().get(randomGenerator.nextInt(clazz.getEStructuralFeatures().size())) );
			for (EStructuralFeature feature : feats) { // bidirectional case
				if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null ) {
					((EReference)feature).getEOpposite().setEType(newClass);
				}
			}
			newClass.getEStructuralFeatures().addAll(feats);
			
			// register mutation
			Collection<ENamedElement> elements = new ArrayList<ENamedElement>();
			elements.add(newClass);
			elements.addAll(feats);
			EAnnotation annotation = registerMutation(elements.toArray(new ENamedElement[0]), null, null);
			
			registerUndo(metamodel, () -> {
				// unregister mutation
				unregisterMutation(annotation);
				
				// undo the mutation
				for (EStructuralFeature feature : feats) { // bidirectional case
					if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null ) {
						((EReference)feature).getEOpposite().setEType(clazz);
						((EClass)feature.getEType()).getEStructuralFeatures().add(((EReference)feature).getEOpposite());
					}
				}
				clazz.getEStructuralFeatures().addAll(feats);
				pack.getEClassifiers().remove(newClass);
			});
		}
	}

	@Override
	public boolean isBreaking() {
		return true;
	}
}
