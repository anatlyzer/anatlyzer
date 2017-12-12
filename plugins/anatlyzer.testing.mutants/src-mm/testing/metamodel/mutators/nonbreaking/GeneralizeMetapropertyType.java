/**
 * It relaxes the type of a metaproperty (e.g., from T to T.supertype) 
 */
package testing.metamodel.mutators.nonbreaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class GeneralizeMetapropertyType extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (their type has a supertype)
		List<EReference> candidates = new ArrayList<EReference>();
		for (EReference reference : metamodel.getEReferences()) 
			if (!reference.getEReferenceType().getESuperTypes().isEmpty())
				candidates.add(reference);
		
		// generate each possible mutant
		for (EReference reference : candidates) {
			// relax type t of metaproperty to be a supertype of t
			EClass     oldType     = reference.getEReferenceType();
			EReference oldOpposite = reference.getEOpposite();
			if (oldOpposite!=null) oldOpposite.setEOpposite(null);
			for (EClass supertype : oldType.getEAllSuperTypes()) {
				reference.setEType(supertype);
				reference.setEOpposite(null);
			
				// register mutation
				EAnnotation annotation = registerMutation(null, null, new ENamedElement[]{reference});
				
				registerUndo(metamodel, () -> {
					// unregister mutation
					unregisterMutation(annotation);
	
					
					// undo the mutation
					reference.setEType    (oldType);
					reference.setEOpposite(oldOpposite);
					if (oldOpposite!=null) oldOpposite.setEOpposite(reference);
				});
			}
		}
	}

	@Override
	public boolean isBreaking() {
		return false;
	}
}
