/**
 * It pushes a property from a class to all its subclasses.
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class PushMetaproperty extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (their container class has a subtype)
		List<EStructuralFeature> candidates = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : metamodel.getEStructuralFeatures()) 
			if (metamodel.hasSubclasses(feature.getEContainingClass()))
				candidates.add(feature);
		
		// generate each possible mutant
		for (EStructuralFeature feature : candidates) {			
			// register mutation
			EAnnotation annotation = registerMutation(null, null, new ENamedElement[]{feature});
				
			// move feature to all direct subclasses
			Hashtable<EClass, List<EStructuralFeature>> newFeats = new Hashtable<EClass, List<EStructuralFeature>>();
			EClass containerClass    = feature.getEContainingClass();
			EClass containerClassOpp = (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null)? ((EReference)feature).getEOpposite().getEContainingClass() : null; // bidirectional case
			List<EClass> subclasses  = metamodel.subclasses(containerClass, false);
			for (EClass subclass : subclasses) {
				if (feature instanceof EReference)
					 clone((EReference)feature, subclass, newFeats, subclasses.size()>1);
				else clone((EAttribute)feature, subclass, newFeats);			
			}
			
			// remove feature from original class/es
			containerClass.getEStructuralFeatures().remove(feature);
			if (containerClassOpp != null) 
				containerClassOpp.getEStructuralFeatures().remove(((EReference)feature).getEOpposite());
				
			
			registerUndo(metamodel, () -> {				
				// unregister mutation
				unregisterMutation(annotation);
	
				// undo the mutation
				for (EClass clazz : newFeats.keySet()) // remove added features
					clazz.getEStructuralFeatures().removeAll(newFeats.get(clazz)); 			
				containerClass.getEStructuralFeatures().add(feature); // add removed features
				if (containerClassOpp != null)  // bidirectional case
					containerClassOpp.getEStructuralFeatures().add(((EReference)feature).getEOpposite());
			});
		}
	}	

	@Override
	public boolean isBreaking() {
		return true;
	}
}
