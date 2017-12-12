/**
 * It restricts the multiplicity of a metaproperty (e.g., from x..n to x+1..n) 
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class RestrictMetapropertyLB extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (different lower and upper bound)
		List<EStructuralFeature> candidates = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : metamodel.getEStructuralFeatures()) 
			if (feature.getLowerBound() != feature.getUpperBound())
				candidates.add(feature);
		
		// generate each possible mutant
		for (EStructuralFeature feature : candidates) {
			// restrict lower bound by adding 1
			int aux = feature.getLowerBound();
			feature.setLowerBound(aux+1);
			
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
		return true;
	}
}
