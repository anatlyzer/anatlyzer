/**
 * It restricts the type of a metaproperty (e.g., from T to T.subtype) 
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class RestrictMetapropertyType extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate metaproperties (their type has subtypes)
		List<EReference> candidates = new ArrayList<EReference>();
		for (EReference reference : metamodel.getEReferences()) 
			if (metamodel.hasSubclasses(reference.getEReferenceType()) &&
				// A containment reference of a type with a container feature that requires instances to be contained elsewhere cannot be populated
				!(reference.isContainment() && ((EClass)reference.getEType()).getEReferences().stream().anyMatch(r -> r.getEOpposite()!=null)))
				candidates.add(reference);
		
		// generate each possible mutant
		for (EReference reference : candidates) {
			// restrict type t of metaproperty to be a subtype of t
			EClass     oldType     = reference.getEReferenceType();
			EReference oldOpposite = reference.getEOpposite();
			if (oldOpposite!=null) oldOpposite.setEOpposite(null);
			for (EClass subtype : metamodel.subclasses(oldType, true)) {
				reference.setEType(subtype);
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
		return true;
	}
}
