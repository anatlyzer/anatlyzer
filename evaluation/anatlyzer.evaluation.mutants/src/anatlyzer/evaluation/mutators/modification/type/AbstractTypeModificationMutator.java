package anatlyzer.evaluation.mutators.modification.type;

// REMARKS
// => any subtype of a class is compatible with the class
// => no class has incompatible supertypes
// => the information of the static analysis could be used to select an incompatible
//    class of interest, e.g. a class that does not define a feature used by the rule

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.evaluation.mutators.ATLModel;
import anatlyzer.evaluation.mutators.AbstractMutator;

public abstract class AbstractTypeModificationMutator extends AbstractMutator {
	
	/**
	 * Generic type modification. It allows subtypes of the container class.
	 * @param atlModel
	 * @param outputFolder
	 * @param ToModifyClass container class of the class of objects to modify (example Parameter)
	 * @param featureName feature to modify (example type)
	 * @param metamodel metamodel containing the candidate types for the modification   
	 */
	protected <ToModify extends LocatedElement> 
	void genericTypeModification(EMFModel atlModel, String outputFolder, Class<ToModify> ToModifyClass, String featureName, MetaModel[] metamodels) {
		this.genericTypeModification(atlModel, outputFolder, ToModifyClass, featureName, metamodels, false); 
	}
	
	/**
	 * Generic type modification (OclModelElement, CollectionType or PrimitiveType). 
	 * @param atlModel
	 * @param outputFolder
	 * @param ToModifyClass container class of the class of objects to modify (example Parameter)
	 * @param featureName feature to modify (example type)
	 * @param metamodel metamodel containing the candidate types for the modification   
	 * @param exactToModifyType
	 */
	protected <ToModify extends LocatedElement> 
	void genericTypeModification(EMFModel atlModel, String outputFolder, Class<ToModify> ToModifyClass, String featureName, MetaModel[] metamodels, boolean exactToModifyType) {
		
		ATLModel       wrapper    = new ATLModel(atlModel.getResource());
		List<ToModify> modifiable = (List<ToModify>)wrapper.allObjectsOf(ToModifyClass);
		
		// filter subtypes (only if parameter exactToModifyType is true)
		if (exactToModifyType) filterSubtypes(modifiable, ToModifyClass);
		
		// we will add a comment to the module, documenting the mutation 
		Module module = wrapper.getModule();
		EDataTypeEList<String> comments = null;
		if (module!=null) {
			EStructuralFeature f = wrapper.source(module).eClass().getEStructuralFeature("commentsBefore");	
			comments = (EDataTypeEList<String>)wrapper.source(module).eGet(f);
		}

		// generate mutants
		for (ToModify object2modify : modifiable) {
			EStructuralFeature featureDefinition = wrapper.source(object2modify).eClass().getEStructuralFeature(featureName);
			
			// CASE 1: monovalued feature .........................................................
			
			if (featureDefinition!=null && featureDefinition.getUpperBound() == 1) {
				EObject object2modify_src = wrapper.source(object2modify);			
				EObject oldFeatureValue   = (EObject)object2modify_src.eGet(featureDefinition); 

				if (oldFeatureValue!=null) {
					List<EObject> replacements = this.replacements(atlModel, (EObject)oldFeatureValue, metamodels);
					for (EObject replacement : replacements) {

						// copy features from original object to replacement
						copyFeatures(oldFeatureValue, replacement);

						// modify original object by replacement
						object2modify_src.eSet(featureDefinition, replacement);

						// mutation: documentation
						if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" from " + toString(object2modify) + ":" + toString(oldFeatureValue) + " to " + toString(object2modify) + ":" + toString(replacement)  + " (line " + object2modify.getLocation() + " of original transformation)\n");

						// save mutant
						this.save(atlModel, outputFolder);

						// remove comment
						if (comments!=null) comments.remove(comments.size()-1);

						// copy features from replacement to original object
						copyFeatures(replacement, oldFeatureValue);
					}

					// restore original value
					object2modify_src.eSet(featureDefinition, oldFeatureValue);					
				}
			}
			
			// CASE 2: multivalued feature ........................................................
			
			else if (featureDefinition!=null) {
				List<EObject> value = (List<EObject>)wrapper.source(object2modify).eGet(featureDefinition);
				
				for (int i=0; i<value.size(); i++) {
					
					EObject oldFeatureValue = value.get(i);
					
					List<EObject> replacements = this.replacements(atlModel, (EObject)oldFeatureValue, metamodels);
					for (EObject replacement : replacements) {

						// copy features from original object to replacement
						copyFeatures(oldFeatureValue, replacement);

						// modify original object by replacement
						value.set(i, replacement);

						// mutation: documentation
						if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" from " + toString(object2modify) + ":" + toString(oldFeatureValue) + " to " + toString(object2modify) + ":" + toString(replacement)  + " (line " + object2modify.getLocation() + " of original transformation)\n");

						// save mutant
						this.save(atlModel, outputFolder);

						// remove comment
						if (comments!=null) comments.remove(comments.size()-1);

						// copy features from replacement to original object
						copyFeatures(replacement, oldFeatureValue);
						
						// restore original value
						value.set(i, oldFeatureValue);
					}
				}
			}
		}
	}

	
	/**
	 * It returns the list of classes that replace a given one: a compatible subclass, a compatible superclass,
	 * a compatible unrelated class, an incompatible subclass, and an incompatible unrelated class. 
	 * @param toReplace
	 * @param metamodel
	 * @return
	 */
	private List<EObject> replacements(EMFModel atlModel, EObject toReplace, MetaModel[] metamodels) {
		List<EObject> replacements = new ArrayList<EObject>();
		
		EPackage pack          = toReplace.eClass().getEPackage();
		EClass   mmtype        = (EClass)pack.getEClassifier("OclModelElement");
		EClass   collection    = (EClass)pack.getEClassifier("CollectionType");
		EClass   collectionExp = (EClass)pack.getEClassifier("CollectionExp");
		EClass   primitive     = (EClass)pack.getEClassifier("Primitive");
		
		// OCL MODEL ELEMENT .......................................................................
		
		if (mmtype.isInstance(toReplace)) {
			for (MetaModel metamodel : metamodels) {
			// search class to replace
			EStructuralFeature featureName  = toReplace.eClass().getEStructuralFeature("name");
			String             featureValue = toReplace.eGet(featureName).toString();
			EClassifier      classToReplace = metamodel.getEClassifier(featureValue); 
		
			if (classToReplace != null && classToReplace instanceof EClass) {
				EClass       replace = (EClass)classToReplace;
				List<EClass> options = new ArrayList<EClass>();

				// search classes to use as replacement
				options.add( getCompatibleSubclass        (replace, metamodel) );
				options.add( getCompatibleSuperclass      (replace) );
				options.add( getCompatibleUnrelatedClass  (replace, metamodel) );
				options.add( getIncompatibleSubclass      (replace, metamodel) );
				options.add( getIncompatibleUnrelatedClass(replace, metamodel) );
				
				// create an OclModelElement for each found replacement class  
				for (EClass option : options) {
					if (option!=null) {
						EObject object = (EObject)atlModel.newElement(mmtype);
						object.eSet(mmtype.getEStructuralFeature("name"), option.getName());
						replacements.add(object);
					}
				}
			}}
		}
		
		// COLLECTION TYPE .........................................................................
		
		else if (collection.isInstance(toReplace)) {
			
			// replace by each different collection type
			String[] options = {"SequenceType", "SetType", "BagType", "OrderedSetType"};
			for (String option : options) {
				if (!((EClass)pack.getEClassifier(option)).isInstance(toReplace)) {
					EClass collectionType = (EClass)pack.getEClassifier(option);
					replacements.add((EObject)atlModel.newElement(collectionType));
				}
			}
		} 
		
		// COLLECTION EXPRESSION TYPE ..............................................................
		
		else if (collectionExp.isInstance(toReplace)) {
			
			// replace by each different collection type
			String[] options = {"SequenceExp", "SetExp", "BagExp", "OrderedSetExp"};
			for (String option : options) {
				if (!((EClass)pack.getEClassifier(option)).isInstance(toReplace)) {
					EClass collectionExpType = (EClass)pack.getEClassifier(option);
					replacements.add((EObject)atlModel.newElement(collectionExpType));
				}
			}
		} 
		
		// PRIMITIVE TYPE ..........................................................................
		
		else if (primitive.isInstance(toReplace)) {
			
			// replace by each different collection type
			String[] options = {"StringType", "BooleanType", "IntegerType", "RealType"};
			for (String option : options) {
				if (!((EClass)pack.getEClassifier(option)).isInstance(toReplace)) {
					EClass primitiveType = (EClass)pack.getEClassifier(option);
					replacements.add((EObject)atlModel.newElement(primitiveType));
				}
			}
		} 
		
		return replacements;	
	}
	
	/**
	 * Given a class, it returns a compatible superclass (i.e. with the same features).
	 * @param c
	 * @return a compatible superclass, null if there is none
	 */
	protected EClass getCompatibleSuperclass (EClass c) {
		List<EClass> superclasses = c.getEAllSuperTypes();
		return c.getEStructuralFeatures().size()>0 ?
			   getCompatible(c, superclasses) :
			   null;
	}
	
	/**
	 * Given a class, it returns a compatible subclass (i.e. with the same features).
	 * @param c
	 * @param mm
	 * @return a compatible subclass, null if there is none
	 */
	protected EClass getCompatibleSubclass (EClass c, MetaModel mm) {
		List<EClass> subclasses = subclasses(c, mm);
		return getCompatible(c, subclasses);
	}

	/**
	 * Given a class, it returns a compatible class that is neither a superclass nor a subclass.
	 * @param c
	 * @param mm
	 * @return a compatible unrelated class, null if there is none
	 */
	protected EClass getCompatibleUnrelatedClass (EClass c, MetaModel mm) {
		List<EClass> unrelatedClasses = unrelatedClasses(c, mm);
		return getCompatible(c, unrelatedClasses);
	}
	
	/**
	 * Given a class, it returns an incompatible subclass (i.e. with different features).
	 * @param c
	 * @param mm
	 * @return an incompatible subclass, null if there is none
	 */
	protected EClass getIncompatibleSubclass (EClass c, MetaModel mm) {
		List<EClass> subclasses = subclasses(c, mm);
		return getIncompatible(c, subclasses);
	}

	/**
	 * Given a class, it returns an incompatible class that is neither a superclass nor a subclass.
	 * @param c
	 * @param mm
	 * @return an incompatible unrelated class, null if there is none
	 */
	protected EClass getIncompatibleUnrelatedClass (EClass c, MetaModel mm) {
		List<EClass> unrelatedClasses = unrelatedClasses(c, mm);
		return getIncompatible(c, unrelatedClasses);
	}
	
	// -------------------------------------------------------------------------------------
	// AUXILIARY METHODS
	// -------------------------------------------------------------------------------------
	
	/**
	 * Given a class, it returns a compatible class (i.e. with the same features) from a list of candidates.
	 * @param c
	 * @param candidates
	 * @return a compatible class, null if there is none
	 */
	private EClass getCompatible (EClass c, List<EClass> candidates) {
		EClass compatible = null;
		for (int i=0; i<candidates.size() && compatible==null; i++) {
			EClass c2 = candidates.get(i);
			if (isCompatibleWith(c2, c))
				compatible = c2;
		}
		return compatible;
	}
	
	private EClass getIncompatible (EClass c, List<EClass> candidates) {
		EClass incompatible = null;
		for (int i=0; i<candidates.size() && incompatible==null; i++) {
			EClass c2 = candidates.get(i);
			if (!isCompatibleWith(c2, c))
				incompatible = c2;
		}
		return incompatible;
	}
	
	/**
	 * It checks whether a class c1 is compatible with (i.e. it can substitute safely) another class c2.  
	 * c1 is compatible with c2 if c1 defines at least all features that c2 defines (it can define more).
	 * @param c1 class
	 * @param c2 class
	 * @return
	 */
	private boolean isCompatibleWith (EClass c1, EClass c2) {
		boolean compatible = true;

		for (int i=0; i<c2.getEAllStructuralFeatures().size() && compatible; i++) {
			EStructuralFeature feature2 = c2.getEAllStructuralFeatures().get(i);
			EStructuralFeature feature1 = c1.getEStructuralFeature(feature2.getName());
			// c1 cannot substitute c2 if:
			// - c1 lacks one of the features of c2
			// - c1 has a feature with same name but different type
			// - c1 has a feature with same name but it is monovalued, while the one in c1 is multivalued (or viceversa)
			if (feature1 == null ||
				feature1.getEType() != feature2.getEType() ||
				(feature1.getUpperBound()==1 && feature2.getUpperBound()!=1) ||
				(feature1.getUpperBound()!=1 && feature2.getUpperBound()==1) ) 
				compatible = false;
		}
		
		return compatible;
	}
	
	/**
	 * It returns the list of classes that are neither superclasses nor subclasses of a given class.
	 * @param c
	 * @param mm
	 * @return
	 */
	private List<EClass> unrelatedClasses (EClass c, MetaModel mm) {
		List<EClass> subclasses       = subclasses(c, mm);
		List<EClass> unrelatedClasses = new ArrayList<EClass>();
		for (EClassifier classifier : mm.getEClassifiers()) {
			if (classifier instanceof EClass && 
				classifier != c && 
				!c.getEAllSuperTypes().contains(classifier) &&
				!subclasses.contains(classifier))
				unrelatedClasses.add((EClass)classifier);
		}
		return unrelatedClasses;
	}

	/**
	 * It copies all features (except name) "from" an object "to" another object.
	 * @param from
	 * @param to
	 * @param features
	 */
	private void copyFeatures (EObject from, EObject to) {
		for (EStructuralFeature feature : from.eClass().getEAllStructuralFeatures()) {
			if (!feature.getName().equals("name") && to.eClass().getEAllStructuralFeatures().contains(feature))
				to.eSet(feature, from.eGet(feature));  
		}
	}	
}
