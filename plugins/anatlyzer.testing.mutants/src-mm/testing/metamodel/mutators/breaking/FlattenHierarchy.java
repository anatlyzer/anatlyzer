/**
 * It eliminates a superclass, introducing all its properties in its subclasses.
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class FlattenHierarchy extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate source classes (having subclasses)
		List<EClass> candidates = new ArrayList<EClass>();
		for (EClass clazz : metamodel.getEClasses())
			if (metamodel.hasSubclasses(clazz))
				candidates.add(clazz);

		List<EClass> subtypes; 											// subtypes of the class I'm deleting
		Map<EClass, List<EStructuralFeature>> refs = new HashMap<>(); 	// references (and their enclosing classes) pointing to the class I'm deleting; removed features in general

		// generate each possible mutant
		for (EClass clazz : candidates) {
			EPackage pack = (EPackage)clazz.eContainer(); 
			EPackage top  = this.containerPackage(clazz);
			pack.getEClassifiers().remove(clazz);
			
			// move features to all direct subclasses
			Hashtable<EClass, List<EStructuralFeature>> newFeats = new Hashtable<EClass, List<EStructuralFeature>>();
			List<EStructuralFeature> feats = new ArrayList<EStructuralFeature>();
			feats.addAll(clazz.getEStructuralFeatures());
			for (EStructuralFeature feature : feats) {
				EClass containerClass    = feature.getEContainingClass();
				EClass containerClassOpp = (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null)? ((EReference)feature).getEOpposite().getEContainingClass() : null; // bidirectional case
				List<EClass> subclasses  = metamodel.subclasses(containerClass, false);
				for (EClass subclass : subclasses) {
					if (feature instanceof EReference)
						 clone((EReference)feature, subclass, newFeats, subclasses.size()>1);
					else clone((EAttribute)feature, subclass, newFeats);			
				}
				
				// remove feature from original class/es
				if (containerClassOpp != null) 
					containerClassOpp.getEStructuralFeatures().remove(((EReference)feature).getEOpposite());
			}
			clazz.getEStructuralFeatures().clear();
			
			// remove the class from the ancestors of its subtypes
			subtypes = metamodel.subclasses(clazz, false);
			for (EClass cl : subtypes) {
				cl.getESuperTypes().remove(clazz);
				cl.getESuperTypes().addAll(clazz.getESuperTypes());	// add clazz's supertypes
			}
			for (EClass cl : /*candidates*/metamodel.getEClasses()) {
				if ( cl == clazz) continue;
				List<EReference> rfs = cl.getEAllReferences().stream().
						filter(r -> r.getEReferenceType() == clazz).
						collect(Collectors.toList());
				for (EStructuralFeature f : rfs) deleteFeature(refs, cl, f);
			}		

			// register mutation
			EAnnotation annotation = registerMutation(null, new ENamedElement[]{clazz}, null, top); // use "top" because clazz is in no package
			// TODO: register deleted references

			final List<EClass> fSutbypes = subtypes;
			registerUndo(metamodel, () -> {
				// unregister mutation
				unregisterMutation(annotation);
	
				// undo
				for (EClass cl : newFeats.keySet()) // remove added features
					cl.getEStructuralFeatures().removeAll(newFeats.get(cl)); 			
				for (EStructuralFeature feature : feats) { // bidirectional case
					if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null ) {
						((EReference)feature).getEOpposite().setEType(clazz);
						((EClass)feature.getEType()).getEStructuralFeatures().add(((EReference)feature).getEOpposite());
					}
				}
				pack.getEClassifiers().add(clazz);
				for (EClass cl : fSutbypes) {
					cl.getESuperTypes().removeAll(clazz.getESuperTypes());	// add clazz's supertypes
					cl.getESuperTypes().add(clazz);
				}
				for (EClass pointing : refs.keySet()) {
					pointing.getEStructuralFeatures().addAll(refs.get(pointing));
				}
				clazz.getEStructuralFeatures().addAll(feats);
			});
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
