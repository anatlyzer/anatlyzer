/**
 * It moves a metaproperty to another class.
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class MoveMetaproperty extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (all)
		List<EStructuralFeature> candidates = metamodel.getEStructuralFeatures();
		
		// generate each possible mutant
		for (EStructuralFeature feature : candidates) {			
			// remove feature from its current class
			EClass containerClass = feature.getEContainingClass();
			containerClass.getEStructuralFeatures().remove(feature);
			EReference opposite = (feature instanceof EReference)? ((EReference)feature).getEOpposite() : null;
			if (opposite!=null) {
				((EReference)feature).setEOpposite(null);
				opposite.setEOpposite(null); 
			}			
			// select other class that do not contain a feature with the same name
			for (EClass otherClass : metamodel.getEClasses()) {
				if (containerClass != otherClass && otherClass.getEStructuralFeature(feature.getName()) == null) {
					// remove duplicated features from the subclasses of the other class
					Map<EClass, List<EStructuralFeature>> deleted = new HashMap<>();
					for (EClass subtype : metamodel.subclasses(otherClass, true)) {
						EStructuralFeature sf = subtype.getEStructuralFeature(feature.getName());
						if (sf!=null && feature!=sf) deleteFeature(deleted, subtype, sf);
					}
					
					// move feature to the other class
					otherClass.getEStructuralFeatures().add(feature);
					
					// register mutation
					EAnnotation annotation = registerMutation(null, null, new ENamedElement[]{feature});

					registerUndo(metamodel, () -> {
						// unregister mutation
						unregisterMutation(annotation);
	
						// undo the mutation
						otherClass.getEStructuralFeatures().remove(feature);				
						for (EClass cl : deleted.keySet()) cl.getEStructuralFeatures().addAll(deleted.get(cl));
					});
				}				
			}
			// undo the mutation
			containerClass.getEStructuralFeatures().add(feature);
			if (opposite!=null) {
				((EReference)feature).setEOpposite(opposite);
				opposite.setEOpposite((EReference)feature);
			}
		}
	}

	@Override
	public boolean isBreaking() {
		return true;
	}

	private void deleteFeature (Map<EClass, List<EStructuralFeature>> registry, EClass container, EStructuralFeature feature) {
		if (container.getEStructuralFeatures().contains(feature)) {
			container.getEStructuralFeatures().remove(feature);
			registerAction(registry, container, feature);
			// bidirectional case
			if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null ) { 
				EReference oppReference = ((EReference)feature).getEOpposite();
				EClass     oppContainer = oppReference.getEContainingClass(); 
				oppContainer.getEStructuralFeatures().remove(oppReference);
				registerAction(registry, oppContainer, oppReference);
			}
		}
	}

	private void registerAction (Map<EClass, List<EStructuralFeature>> registry, EClass container, EStructuralFeature feature) {
		if (registry != null) {
			if (!registry.containsKey(container)) 
				registry.put(container, new ArrayList<EStructuralFeature>());
			registry.get(container).add(feature);
		}
	}
}
