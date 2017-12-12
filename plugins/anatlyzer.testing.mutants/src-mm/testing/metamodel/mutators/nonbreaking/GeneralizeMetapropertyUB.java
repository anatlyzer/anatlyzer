/**
 * It relaxes the multiplicity of a metaproperty (e.g., from 0..4 to 0..*) 
 */
package testing.metamodel.mutators.nonbreaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class GeneralizeMetapropertyUB extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (bounded upper cardinality, it is not a container)
		List<EStructuralFeature> candidates = new ArrayList<EStructuralFeature>();
		for (EAttribute feature : metamodel.getEAttributes()) 
			if (feature.getUpperBound() != -1)
				candidates.add(feature);
		for (EReference feature : metamodel.getEReferences()) 
			if (feature.getUpperBound() != -1 && feature.getEOpposite() == null) // a container reference must have upper bound of 1 not -1
				candidates.add(feature);
		
		// generate each possible mutant
		for (EStructuralFeature feature : candidates) {
			// relax upper bound to *
			int aux = feature.getUpperBound();
			feature.setUpperBound(-1);
			
			// register mutation
			EAnnotation annotation = registerMutation(null, null, new ENamedElement[]{feature});
			
			registerUndo(metamodel, () -> {
				// unregister mutation
				unregisterMutation(annotation);
				
				// undo the mutation
				feature.setUpperBound(aux);
			});
		}
	}

	@Override
	public boolean isBreaking() {
		return false;
	}
}
