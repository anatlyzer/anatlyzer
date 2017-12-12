/**
 * It relaxes the multiplicity of a metaproperty (e.g., from 3..n to 0..n) 
 */
package testing.metamodel.mutators.nonbreaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class GeneralizeMetapropertyLB extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (lower bound bigger than 0)
		List<EStructuralFeature> candidates = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : metamodel.getEStructuralFeatures()) 
			if (feature.getLowerBound()>0)
				candidates.add(feature);
		
		// generate each possible mutant
		for (EStructuralFeature feature : candidates) {
			// relax lower bound to 0
			int aux = feature.getLowerBound();
			feature.setLowerBound(0);
			
			// register mutation
			EAnnotation annotation = registerMutation(null, null, new ENamedElement[]{feature});
			
			registerUndo(metamodel, () -> {
				// unregister mutation
				unregisterMutation(annotation);
				
				// undo the mutation
				feature.setLowerBound(aux);
			});
		}
	}

	@Override
	public boolean isBreaking() {
		return false;
	}
}
