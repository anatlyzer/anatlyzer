/**
 * It moves all features of a metaclass to another metaclass, and deletes the former.
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;

import testing.metamodel.mutators.AbstractMutator;
import testing.utils.MMResource;

public class InlineMetaclass extends AbstractMutator {

	@Override
	public void mutate(MMResource metamodel) {
		// obtain candidate source classes (having 1 or more features)
		List<EClass> candidates = new ArrayList<EClass>();
		for (EClass clazz : metamodel.getEClasses())
			if (clazz.getEStructuralFeatures().size()>0)
				candidates.add(clazz);

		List<EClass> subtypes; 											// subtypes of the class I'm deleting
		Map<EClass, List<EStructuralFeature>> refs = new HashMap<>(); 	// references (and their enclosing classes) pointing to the class I'm deleting; removed features in general

		// generate each possible mutant
		for (EClass clazz : candidates) {
			for (EClass targetClass : metamodel.getEClasses()) {
				if (clazz != targetClass) {
					refs.clear();
					
					// select features to move: those that do not appear in the target class
					List<EStructuralFeature> feats = clazz.getEStructuralFeatures().stream().filter(f -> targetClass.getEStructuralFeature(f.getName())==null).collect(Collectors.toList());
					if (!feats.isEmpty()) {
						
						// remove candidate class
						EPackage pack = (EPackage)clazz.eContainer(); 
						EPackage top  = this.containerPackage(clazz);
						pack.getEClassifiers().remove(clazz);
						
						// remove duplicated features from the subclasses of the target class
						for (EClass subtype : metamodel.subclasses(targetClass, true)) {
							for (EStructuralFeature f : feats) {
								EStructuralFeature sf = subtype.getEStructuralFeature(f.getName());
								if (sf!=null && f!=sf) deleteFeature(refs, subtype, sf);
							}
						}
						
						// empty opposite reference of the non-moved features
						List<EReference> bid = clazz.getEReferences().stream().filter(ref -> !feats.contains(ref) && ref.getEOpposite()!=null).collect(Collectors.toList());
						Map<EReference, EReference> opposite = new HashMap<>();
						for (EReference ref : bid) {
							opposite.put(ref, ref.getEOpposite());
							ref.getEOpposite().setEOpposite(null);
							ref.setEOpposite(null);
						}
						
						// move features from the deleted class to the target class
						targetClass.getEStructuralFeatures().addAll(feats);
						
						// retype all elements typed by the class to be inlined. Their new type should be the target class
						List<ETypedElement> retyped = metamodel.getETypedElements().stream().filter(element -> element.getEType()==clazz).collect(Collectors.toList());
						for (ETypedElement element : retyped) element.setEType(targetClass);
						
						// remove the class from the ancestors of its subtypes
						subtypes = metamodel.subclasses(clazz, false);
						for (EClass cl : subtypes) {
							cl.getESuperTypes().remove(clazz);
							cl.getESuperTypes().addAll(clazz.getESuperTypes());	// add clazz's supertypes
						}

						// register mutation
						EAnnotation annotation = registerMutation(feats.toArray(new ENamedElement[0]), new ENamedElement[]{clazz}, null, top); // use "top" because clazz is in no package
						// TODO: register deleted references

						final List<EClass> fSubtypes = subtypes;
						registerUndo(metamodel, () -> {
							// unregister mutation
							unregisterMutation(annotation);
	
							// undo
							for (EStructuralFeature feature : feats) { // bidirectional case
								if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null ) {
									((EReference)feature).getEOpposite().setEType(clazz);
									((EClass)feature.getEType()).getEStructuralFeatures().add(((EReference)feature).getEOpposite());
								}
							}
							pack.getEClassifiers().add(clazz);
							for (EClass cl : fSubtypes) {
								cl.getESuperTypes().removeAll(clazz.getESuperTypes());	// add clazz's supertypes
								cl.getESuperTypes().add(clazz);
							}
							for (EClass pointing : refs.keySet()) {
								pointing.getEStructuralFeatures().addAll(refs.get(pointing));
							}
							clazz.getEStructuralFeatures().addAll(feats);
							for (ETypedElement typedElement : retyped) {
								typedElement.setEType(clazz);
							}
							for (EReference ref : opposite.keySet()) {
								ref.setEOpposite(opposite.get(ref));
								opposite.get(ref).setEOpposite(ref);
							}
						});
					}
				}
			}			
		}
	}

	@Override
	public boolean isBreaking() {
		return true;
	}

	private void deleteFeature (Map<EClass, List<EStructuralFeature>> registry, EClass container, EStructuralFeature feature) {
		if (container.getEStructuralFeatures().contains(feature)) {
			container.getEStructuralFeatures().remove(feature);
			registerAction(registry, container, feature);
			// bidirectional case
			if (feature instanceof EReference && ((EReference)feature).getEOpposite()!=null ) { 
				EReference oppReference = ((EReference)feature).getEOpposite();
				EClass     oppContainer = oppReference.getEContainingClass(); 
				oppContainer.getEStructuralFeatures().remove(oppReference);
				registerAction(registry, oppContainer, oppReference);
			}
		}
	}

	private void registerAction (Map<EClass, List<EStructuralFeature>> registry, EClass container, EStructuralFeature feature) {
		if (registry != null) {
			if (!registry.containsKey(container)) 
				registry.put(container, new ArrayList<EStructuralFeature>());
			registry.get(container).add(feature);
		}
	}
}
