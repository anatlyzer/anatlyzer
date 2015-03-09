package anatlyzer.evaluation.mutators.deletion;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.evaluation.mutators.ATLModel;
import anatlyzer.evaluation.mutators.AbstractMutator;

public abstract class AbstractDeletionMutator extends AbstractMutator {

	/**
	 * Generic deletion. It allows subtypes of both the container class and the class to delete.
	 * @param atlModel
	 * @param outputFolder
	 * @param ContainerClass container class of the class of objects to delete (example OutPattern)
	 * @param ToDeleteClass class of objects to delete (example Binding)
	 * @param relation containment relation (example bindings)
	 */
	protected <Container extends LocatedElement, ToDelete/* extends LocatedElement*/> 
	void genericDeletion(EMFModel atlModel, String outputFolder, Class<Container> ContainerClass, Class<ToDelete> ToDeleteClass, String relation) { 
		genericDeletion(atlModel, outputFolder, ContainerClass, ToDeleteClass, relation, false); 
	}
	
	/**
	 * Generic deletion. It allows subtypes of the class to delete. The parameter 'exactContainerType'
	 * allows configuring whether the type of the container must be exactly the one received, or if
	 * the subtypes should be also considered.  
	 * @param atlModel
	 * @param outputFolder
	 * @param ContainerClass container class of the class of objects to delete (example OutPattern)
	 * @param ToDeleteClass class of objects to delete (example Binding)
	 * @param relation containment relation (example bindings)
	 * @param exactContainerType false to consider also subtypes of the ContainerClass, true to discard subtypes of the ContainerClass  
	 */
	protected <Container extends LocatedElement, ToDelete/* extends LocatedElement*/> 
	void genericDeletion(EMFModel atlModel, String outputFolder, Class<Container> ContainerClass, Class<ToDelete> ToDeleteClass, String relation, boolean exactContainerType) {

		ATLModel        wrapper = new ATLModel(atlModel.getResource());
		List<Container> containers = (List<Container>)wrapper.allObjectsOf(ContainerClass);
		
		// we will add a comment to the module, documenting the mutation 
		Module module = wrapper.getModule();
		EDataTypeEList<String> comments = null;
		if (module!=null) {
			EStructuralFeature feature = wrapper.source(module).eClass().getEStructuralFeature("commentsBefore");	
			comments = (EDataTypeEList<String>)wrapper.source(module).eGet(feature);
		}

		// filter subtypes (only if parameter exactContainerType is true)
		if (exactContainerType) filterSubtypes(containers, ContainerClass);

		for (Container container : containers) {
			EStructuralFeature feature = wrapper.source(container).eClass().getEStructuralFeature(relation);

			if (feature!=null) {

				// CASE 1: monovalued feature .........................................................

				if (feature.getUpperBound() == 1 && feature.getLowerBound() == 0) {
					EObject link = (EObject) wrapper.source(container).eGet(feature);

					// mutation: remove object
					if (link!=null) {
						LocatedElement object = (LocatedElement)wrapper.target(link);
						if (ToDeleteClass.isAssignableFrom(object.getClass())) {
							wrapper.source(container).eSet(feature, null);

							// mutation: documentation
							if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" " + toString(object) + " in " + toString(container) + " (line " + object.getLocation() + " of original transformation)\n");

							// save mutant
							this.save(atlModel, outputFolder);

							// restore: restore object and remove comment
							wrapper.source(container).eSet(feature, link);
							if (comments!=null) comments.remove(comments.size()-1);
						}
					}
				}

				// CASE 2: multivalued feature ........................................................

				else {
					List<EObject> link = (List<EObject>)wrapper.source(container).eGet(feature);
					if (feature.getLowerBound() < link.size()) {
						int size = link.size();
						for (int i=0; i<size; i++) { 

							// mutation: remove object
							EObject        eobject = link.get(i);
							LocatedElement object = (LocatedElement)wrapper.target(eobject);
							if (ToDeleteClass.isAssignableFrom(object.getClass())) {
								link.remove(i);

								// mutation: documentation
								if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" " + toString(object) + " in " + toString(container) + " (line " + object.getLocation() + " of original transformation)\n");

								// save mutant
								this.save(atlModel, outputFolder);

								// restore: restore object and remove comment
								link.add(i, eobject);
								if (comments!=null) comments.remove(comments.size()-1);
							}
						}
					}
				}
			}
		}
	}
}
