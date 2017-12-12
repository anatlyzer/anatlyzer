/**
 * It pulls up a metaproperty to a superclass.
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

public class PullMetaproperty extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (their container class has a supertype)
		List<EStructuralFeature> candidates = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : metamodel.getEStructuralFeatures()) 
			if (!feature.getEContainingClass().getESuperTypes().isEmpty())
				candidates.add(feature);
		
		Map<EClass, List<EStructuralFeature>> deleted = new HashMap<>(); // removed features

		// generate each possible mutant
		for (EStructuralFeature feature : candidates) {			
			EClass containerClass = feature.getEContainingClass();
			EReference opposite = (feature instanceof EReference)? ((EReference)feature).getEOpposite() : null;
			if (opposite!=null) {
				((EReference)feature).setEOpposite(null);
				opposite.setEOpposite(null); 
			}
			for (EClass superclass : containerClass.getESuperTypes()) {
				// remove duplicated features from the subclasses of the target class
				for (EClass subtype : metamodel.subclasses(superclass, true)) {
					EStructuralFeature sf = subtype.getEStructuralFeature(feature.getName());
					if (sf!=null && feature!=sf) deleteFeature(deleted, subtype, sf);
				}
				
				// move feature to direct superclass
				superclass.getEStructuralFeatures().add(feature);
				
				// register mutation
				EAnnotation annotation = registerMutation(null, null, new ENamedElement[]{feature});
				
				registerUndo(metamodel, () -> {
					// unregister mutation
					unregisterMutation(annotation);
	
					// undo the mutation
					superclass.getEStructuralFeatures().remove(feature);
					containerClass.getEStructuralFeatures().add(feature);
					if (opposite!=null) {
						((EReference)feature).setEOpposite(opposite);
						opposite.setEOpposite((EReference)feature);
					}
					for (EClass pointing : deleted.keySet()) {
						pointing.getEStructuralFeatures().addAll(deleted.get(pointing));
					}
				});
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
