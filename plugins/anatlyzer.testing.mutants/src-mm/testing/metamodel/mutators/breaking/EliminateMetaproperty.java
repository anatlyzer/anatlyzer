/**
 * It deletes a metaproperty. 
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class EliminateMetaproperty extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (all)
		List<EStructuralFeature> candidates = new ArrayList<EStructuralFeature>();
		candidates.addAll(metamodel.getEStructuralFeatures());
		
		// generate each possible mutant
		for (EStructuralFeature feature : candidates) {
			// register mutation (to be applied next)
			EAnnotation annotation = registerMutation(null, new ENamedElement[]{feature}, null);
			
			// delete metaproperty from container class
			EClass containerClass = feature.getEContainingClass();
			containerClass.getEStructuralFeatures().remove(feature);
			
			// delete metaproperty from opposite references (if needed)
			if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null )
				((EReference)feature).getEOpposite().setEOpposite(null);
			
			registerUndo(metamodel, () -> {
				// unregister mutation
				unregisterMutation(annotation);
				
				// undo the mutation
				containerClass.getEStructuralFeatures().add(feature);
				if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null )
					((EReference)feature).getEOpposite().setEOpposite((EReference)feature);
			});
		}
	}

	@Override
	public boolean isBreaking() {
		return true;
	}

}
