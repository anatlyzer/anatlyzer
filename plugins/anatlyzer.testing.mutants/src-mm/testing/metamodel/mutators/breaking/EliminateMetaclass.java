/**
 * It deletes a metaclass. 
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class EliminateMetaclass extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate classes
		List<EClass> candidates = new ArrayList<EClass>();
		candidates.addAll(metamodel.getEClasses());

		List<EClass> subtypes; 									// subtypes of the class I'm deleting
		Map<EClass, List<EReference>> refs = new HashMap<>(); 	// references (and their enclosing classes) pointing to the class I'm deleting

		// generate each possible mutant
		for (EClass clazz : candidates) {
			EPackage pack = (EPackage)clazz.eContainer(); 
			EPackage top  = this.containerPackage(clazz);
			pack.getEClassifiers().remove(clazz);
			
			// remove the class from the ancestors of its subtypes
			subtypes = metamodel.subclasses(clazz, false);
			for (EClass cl : subtypes) {
				cl.getESuperTypes().remove(clazz);
				cl.getESuperTypes().addAll(clazz.getESuperTypes());	// add clazz's supertypes
			}
			for (EClass cl : candidates) {
				if ( cl == clazz) continue;
				List<EReference> rfs = cl.getEReferences().stream().
						filter(r -> r.getEReferenceType() == clazz).
						collect(Collectors.toList());
				if (!rfs.isEmpty()) {
					refs.put(cl, rfs);
					cl.getEStructuralFeatures().removeAll(rfs);
				}
			}		

			// register mutation
			EAnnotation annotation = registerMutation(null, new ENamedElement[]{clazz}, null, top); // use "top" because clazz is in no package
			// TODO: register deleted references

			final List<EClass> fSubtypes = subtypes;
			registerUndo(metamodel, () -> {
				// unregister mutation
				unregisterMutation(annotation);
				
				// undo
				pack.getEClassifiers().add(clazz);
				for (EClass cl : fSubtypes) {
					cl.getESuperTypes().removeAll(clazz.getESuperTypes());	// add clazz's supertypes
					cl.getESuperTypes().add(clazz);
				}
				for (EClass pointing : refs.keySet()) {
					pointing.getEStructuralFeatures().addAll(refs.get(pointing));
				}
			});
		}
	}

	@Override
	public boolean isBreaking() {
		return true;
	}
}
