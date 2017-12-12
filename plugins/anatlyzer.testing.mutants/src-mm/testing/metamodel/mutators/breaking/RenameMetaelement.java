/**
 * It renames a metalement (i.e., metaclass, metafeature, and metapackage).
 */
package testing.metamodel.mutators.breaking;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class RenameMetaelement extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate elements (all metaelements)
		List<ENamedElement> candidates = new ArrayList<ENamedElement>();
		candidates.addAll(metamodel.getEPackages());
		candidates.addAll(metamodel.getEClasses());
		candidates.addAll(metamodel.getEStructuralFeatures());
		
		// generate each possible mutant
		for (ENamedElement element : candidates) {
			// rename metaelement 
			String oldName = element.getName();
			element.setName( this.getRandomString(6) );
			
			// register mutation
			EAnnotation annotation = registerMutation(null, null, new ENamedElement[]{element});
			
			registerUndo(metamodel, () -> {			
				// unregister mutation
				unregisterMutation(annotation);
				
				// undo the mutation
				element.setName(oldName);
			});
		}	
	}

	@Override
	public boolean isBreaking() {
		return true;
	}
}
