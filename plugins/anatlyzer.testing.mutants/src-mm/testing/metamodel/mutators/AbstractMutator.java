package testing.metamodel.mutators;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

import testing.utils.MMResource;

public abstract class AbstractMutator {
	
	protected IStorageStrategy storage;

	public void setStorageStrategy(IStorageStrategy strategy) {
		this.storage = strategy;
	}
	
	/**
	 * It mutates a meta-model. To be implemented by concrete mutation operators. 
	 * @param metamodel resource containing the metamodel
	 * @param outputFolder folder where the mutants will be generated
	 */
	public abstract void mutate (MMResource metamodel);
	
	/**
	 * It returns whether the mutation is breaking (true) or not (false).
	 * @return breaking (true) or non-breaking (false).
	 */
	public abstract boolean isBreaking ();

	protected Runnable undo;

	protected void registerUndo(MMResource mutatedMetamodel, Runnable r) {
		storage.save(mutatedMetamodel);
		undo.run();
	}

	
	/**
	 * It returns a random string of the given length.
	 */
	protected String getRandomString (int length) {
		String candidateChars = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder  string = new StringBuilder();
		Random random = new Random();
		for (int i=0; i<length; i++) 
			string.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		return string.toString();
	}
	
	/**
	 * It registers in the meta-model the performed mutation (as an annotation).
	 */
	protected EAnnotation registerMutation (ENamedElement element) { 
		return this.registerMutation(element, containerPackage(element));	
	}
	protected EAnnotation registerMutation (ENamedElement element, EPackage container) { 
		return registerMutation (null, null, new ENamedElement[]{element}, container);	
	}
	protected EAnnotation registerMutation (ENamedElement[] created, ENamedElement[] deleted, ENamedElement[] changed) {
		if (created!=null && created.length>0) return registerMutation(created, deleted, changed, containerPackage(created[0]));
		if (deleted!=null && deleted.length>0) return registerMutation(created, deleted, changed, containerPackage(deleted[0]));
		if (changed!=null && changed.length>0) return registerMutation(created, deleted, changed, containerPackage(changed[0]));
		return null;
	}
	protected EAnnotation registerMutation (ENamedElement[] created, ENamedElement[] deleted, ENamedElement[] changed, EPackage container) {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EAnnotation annotation = factory.createEAnnotation();
		annotation.setSource("MutationRegistry");
		annotation.setEModelElement(container);
		annotation.getDetails().put("MutationKind", this.getClass().getSimpleName());
		annotation.getDetails().put("Created elements", toString(created));
		annotation.getDetails().put("Deleted elements", toString(deleted));
		annotation.getDetails().put("Changed elements", toString(changed)); 
		return annotation;
	}
	private String toString (ENamedElement[] elements) {
		String string = "";
		if (elements!=null)
			for (ENamedElement element : elements) 
				string += "\n" + toString(element);
		return string.replaceFirst("\n","");
	}
	private String toString (ENamedElement element) { 
		return element==null? "" :
			element.eClass().getName() + " " +
			(element instanceof EStructuralFeature && (ENamedElement)element.eContainer()!=null? ((ENamedElement)element.eContainer()).getName() + "." : "") +
			element.getName();
	}
	
	/**
	 * It unregisters from the meta-model a performed mutation (stored as an annotation).
	 * @param annotation
	 */
	protected void unregisterMutation (EAnnotation annotation) {
		annotation.setEModelElement(null);
	}
		
	public EPackage containerPackage (EObject element) {
		EPackage pack = null;
		EObject container = element.eContainer();
		while (container!=null) {
			if (container instanceof EPackage) 
				pack = (EPackage)container;
			container = container.eContainer();
		}
		return pack;
	}

	/**
	 * It clones a reference, and adds the clone to the received class.
	 * @param feature reference to clone
	 * @param newContainer owner class for the reference clone
	 * @param newFeats stores the pair (new-class, new reference) 
	 * @param concat (optional) it is only used for bidirectional features. If the reference is bidirectional, when the opposite is cloned, it allows concatenating its type to the name of the new opposite. False by default.
	 */
	protected void clone (EReference feature, EClass newContainer, Map<EClass, List<EStructuralFeature>> newFeats) { clone(feature, newContainer, newFeats, false); }
	protected void clone (EReference feature, EClass newContainer, Map<EClass, List<EStructuralFeature>> newFeats, boolean concat) {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EReference   newFeat = factory.createEReference(); 
		newFeat.setEType       ( feature.getEType()!=feature.getEContainingClass()? feature.getEType() : /* feature.getEContainingClass() */newContainer ); // the container class is being deleted, it cannot be the type of the new feature 
		newFeat.setLowerBound  ( feature.getLowerBound() );
		newFeat.setUpperBound  ( feature.getUpperBound() );
		newFeat.setName        ( feature.getName() );
		newFeat.setContainment ( feature.isContainment() );
		newContainer.getEStructuralFeatures().add(newFeat);
		
		// record added feature
		if (newFeats!=null) {
			if (!newFeats.containsKey(newContainer)) newFeats.put(newContainer, new ArrayList<EStructuralFeature>());
			newFeats.get(newContainer).add(newFeat);
		}
		
		// if the reference is bidirectional, create new opposite reference
		if (feature.getEOpposite() !=null) {
			EReference newOpp = factory.createEReference(); 
			newOpp.setEType       ( newContainer ); 
			newOpp.setLowerBound  ( feature.getEOpposite().getLowerBound());
			newOpp.setUpperBound  ( feature.getEOpposite().getUpperBound());
			newOpp.setName        ( feature.getEOpposite().getName()  + (concat? "_"+newContainer.getName() : "") );
			newOpp.setContainment ( feature.getEOpposite().isContainment() );
			feature.getEOpposite().getEContainingClass().getEStructuralFeatures().add(newOpp);
			
			// assign opposites
			newFeat.setEOpposite ( newOpp );
			newOpp.setEOpposite  ( newFeat );
			
			// record added features
			if (newFeats!=null) {
				if (!newFeats.containsKey(feature.getEOpposite().getEContainingClass())) newFeats.put(feature.getEOpposite().getEContainingClass(), new ArrayList<EStructuralFeature>());
				newFeats.get(feature.getEOpposite().getEContainingClass()).add(newOpp);
			}
		}		
	}
	
	/**
	 * It clones an attribute, and adds the clone to the received class.
	 * @param feature attribute to clone
	 * @param newContainer owner class for the attribute clone
	 * @param newFeats stores the pair (new-class, new attribute) 
	 */
	protected void clone (EAttribute feature, EClass newContainer, Map<EClass, List<EStructuralFeature>> newFeats) {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EAttribute   newFeat = factory.createEAttribute(); 
		newFeat.setEType       ( feature.getEType() );
		newFeat.setLowerBound  ( feature.getLowerBound() );
		newFeat.setUpperBound  ( feature.getUpperBound() );
		newFeat.setName        ( feature.getName() );
		for (EGenericType arg : feature.getEGenericType().getETypeArguments()) {
			EGenericType newArg = factory.createEGenericType();
			newArg.setEUpperBound   ( arg.getEUpperBound() );
			newArg.setEClassifier   ( arg.getEClassifier() );
			newArg.setELowerBound   ( arg.getELowerBound() );
			newArg.setETypeParameter( arg.getETypeParameter() );
			newFeat.getEGenericType().getETypeArguments().add(newArg);
		}
		newContainer.getEStructuralFeatures().add(newFeat);
		
		// record added feature
		if (newFeats!=null) {
			if (!newFeats.containsKey(newContainer)) newFeats.put(newContainer, new ArrayList<EStructuralFeature>());
			newFeats.get(newContainer).add(newFeat);
		}
	}	
}
