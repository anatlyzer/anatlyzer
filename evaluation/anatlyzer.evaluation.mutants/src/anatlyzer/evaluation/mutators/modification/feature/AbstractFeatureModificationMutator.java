package anatlyzer.evaluation.mutators.modification.feature;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;

import witness.generator.MetaModel;
import anatlyzer.evaluation.mutators.modification.AbstractModificationMutator;

public abstract class AbstractFeatureModificationMutator extends AbstractModificationMutator {
	
	/**
	 * It returns the list of features that replace a given one: a feature with compatible type and compatible
	 * cardinality, a feature with compatible type and incompatible cardinality, a feature with incompatible 
	 * type and compatible cardinality, a feature with incompatible type and incompatible cardinality, and a
	 * feature in a subclass.   
	 * @param toReplace
	 * @param metamodel
	 * @return
	 */
	protected List<Object> featureReplacements (String type, String feature, MetaModel metamodel) {
		List<Object> replacements = new ArrayList<Object>();
		
		EClass mmtype = (EClass)metamodel.getEClassifier(type);
		if (mmtype!=null) {
			
			EStructuralFeature mmfeature = mmtype.getEStructuralFeature(feature);
			if (mmfeature!=null) {
			
				List<EStructuralFeature> options = new ArrayList<EStructuralFeature>();
			
				// search classes to use as replacement
				options.add( getCompatibleFeature1   (mmtype, mmfeature) );            // compatible type and cardinality
				options.add( getCompatibleFeature2   (mmtype, mmfeature, metamodel) ); // compatible type and cardinality, but defined in a subclass
				options.add( getIncompatibleFeature1 (mmtype, mmfeature) );            // compatible type and incompatible cardinality
				options.add( getIncompatibleFeature2 (mmtype, mmfeature) );            // incompatible type and compatible cardinality
				options.add( getIncompatibleFeature3 (mmtype, mmfeature) );            // incompatible type and cardinality
				
				// create list of replacements (name of selected features)  
				for (EStructuralFeature option : options) 
					if (option!=null) 
						replacements.add(option.getName());
			}
		}
		
		return replacements;	
	}
	
	/**
	 * Given a feature, it returns a compatible one (i.e. with compatible type and cardinality).
	 * @param feature
	 * @return a compatible feature, null if there is none
	 */
	protected EStructuralFeature getCompatibleFeature1 (EClass c, EStructuralFeature feature) {
		EStructuralFeature       compatible = null;
		List<EStructuralFeature> candidates = c.getEAllStructuralFeatures();
		
		for (int i=0; i<candidates.size() && compatible==null; i++) {
			EStructuralFeature feature2 = candidates.get(i);
			if (feature != feature2 && 
				// compatible type	
				isCompatibleWith(feature2.getEType(), feature.getEType()) &&
				// compatible lower cardinality (both 0, or both bigger than 0)
				((feature.getLowerBound() == 0 && feature2.getLowerBound() == 0) || 
				 (feature.getLowerBound() >  0 && feature2.getLowerBound() >  0)) &&
				// compatible upper cardinality (both 1, or both different from 1) 
				((feature.getUpperBound() == 1 && feature2.getUpperBound() == 1) ||
				 (feature.getUpperBound() != 1 && feature2.getUpperBound() != 1))) {
				compatible = feature2;
			}
		}
		
		return compatible;
	}
	
	/**
	 * Given a feature, it returns a compatible one (i.e. with compatible type and cardinality) defined by a subclass.
	 * @param feature
	 * @return a compatible feature, null if there is none
	 */
	protected EStructuralFeature getCompatibleFeature2 (EClass c, EStructuralFeature feature, MetaModel mm) {
		EStructuralFeature       compatible = null;
		List<EClass>             subclasses = subclasses(feature.getEContainingClass(), mm);
		List<EStructuralFeature> candidates = new ArrayList<EStructuralFeature>();
		for (EClass subclass : subclasses) candidates.addAll(subclass.getEStructuralFeatures());
		
		for (int i=0; i<candidates.size() && compatible==null; i++) {
			EStructuralFeature feature2 = candidates.get(i);
			if (feature != feature2 && 
				// compatible type	
				isCompatibleWith(feature2.getEType(), feature.getEType()) &&
				// compatible lower cardinality (both 0, or both bigger than 0)
				((feature.getLowerBound() == 0 && feature2.getLowerBound() == 0) || 
				 (feature.getLowerBound() >  0 && feature2.getLowerBound() >  0)) &&
				// compatible upper cardinality (both 1, or both different from 1) 
				((feature.getUpperBound() == 1 && feature2.getUpperBound() == 1) ||
				 (feature.getUpperBound() != 1 && feature2.getUpperBound() != 1))) {
				compatible = feature2;
			}
		}
		
		return compatible;
	}
	
	/**
	 * Given a feature, it returns another with compatible type and incompatible cardinality.
	 * @param feature
	 * @return an incompatible feature, null if there is none
	 */
	protected EStructuralFeature getIncompatibleFeature1 (EClass c, EStructuralFeature feature) {
		EStructuralFeature       incompatible = null;
		List<EStructuralFeature> candidates   = c.getEAllStructuralFeatures();
		
		for (int i=0; i<candidates.size() && incompatible==null; i++) {
			EStructuralFeature feature2 = candidates.get(i);
			if (feature != feature2 && 
				// compatible type	
				isCompatibleWith(feature2.getEType(), feature.getEType()) &&
				// incompatible lower cardinality (one equals to 0, and the other bigger than 0)
				((feature.getLowerBound() == 0 && feature2.getLowerBound() >  0) || 
				 (feature.getLowerBound() >  0 && feature2.getLowerBound() == 0) ||
				// incompatible upper cardinality (one equals to 1, and the other different from 1) 
				 (feature.getUpperBound() == 1 && feature2.getUpperBound() != 1) ||
				 (feature.getUpperBound() != 1 && feature2.getUpperBound() == 1))) {
				incompatible = feature2;
			}
		}
		
		return incompatible;
	}
	
	/**
	 * Given a feature, it returns another with incompatible type and compatible cardinality.
	 * @param feature
	 * @return an incompatible feature, null if there is none
	 */
	protected EStructuralFeature getIncompatibleFeature2 (EClass c, EStructuralFeature feature) {
		EStructuralFeature       incompatible = null;
		List<EStructuralFeature> candidates   = c.getEAllStructuralFeatures();
		
		for (int i=0; i<candidates.size() && incompatible==null; i++) {
			EStructuralFeature feature2 = candidates.get(i);
			if (feature != feature2 && 
				// incompatible type	
				!isCompatibleWith(feature2.getEType(), feature.getEType()) &&
				// compatible lower cardinality (both 0, or both bigger than 0)
				((feature.getLowerBound() == 0 && feature2.getLowerBound() == 0) || 
				 (feature.getLowerBound() >  0 && feature2.getLowerBound() >  0)) &&
				// compatible upper cardinality (both 1, or both different from 1) 
				((feature.getUpperBound() == 1 && feature2.getUpperBound() == 1) ||
				 (feature.getUpperBound() != 1 && feature2.getUpperBound() != 1))) {
				incompatible = feature2;
			}
		}
		
		return incompatible;
	}
	
	/**
	 * Given a feature, it returns another with incompatible type and incompatible cardinality.
	 * @param feature
	 * @return an incompatible feature, null if there is none
	 */
	protected EStructuralFeature getIncompatibleFeature3 (EClass c, EStructuralFeature feature) {
		EStructuralFeature       incompatible = null;
		List<EStructuralFeature> candidates   = c.getEAllStructuralFeatures();
		
		for (int i=0; i<candidates.size() && incompatible==null; i++) {
			EStructuralFeature feature2 = candidates.get(i);
			if (feature != feature2 && 
				// incompatible type	
				!isCompatibleWith(feature2.getEType(), feature.getEType()) &&
				// incompatible lower cardinality (one equals to 0, and the other bigger than 0)
				((feature.getLowerBound() == 0 && feature2.getLowerBound() >  0) || 
				 (feature.getLowerBound() >  0 && feature2.getLowerBound() == 0) ||
				// incompatible upper cardinality (one equals to 1, and the other different from 1) 
				 (feature.getUpperBound() == 1 && feature2.getUpperBound() != 1) ||
				 (feature.getUpperBound() != 1 && feature2.getUpperBound() == 1))) {
				incompatible = feature2;
			}
		}
		
		return incompatible;
	}

	/**
	 * It checks whether a classifier c1 is compatible with (i.e. it can substitute safely) another classifier c2.  
	 * If c1 and c2 are classes, then c1 is compatible with c2 if c1 defines at least all features that c2 defines 
	 * (it can define more). Two primitive types are compatible only if they are the same. 
	 * @param c1 class
	 * @param c2 class
	 * @return
	 */
	private boolean isCompatibleWith (EClassifier c1, EClassifier c2) {
		boolean compatible = true;

		// c1 and c2 are classes
		if (c1 instanceof EClass && c2 instanceof EClass) {
			for (int i=0; i<((EClass)c2).getEAllStructuralFeatures().size() && compatible; i++) {
				EStructuralFeature feature2 = ((EClass)c2).getEAllStructuralFeatures().get(i);
				EStructuralFeature feature1 = ((EClass)c1).getEStructuralFeature(feature2.getName());
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
		}
		
		// only one of them is a class
		else if ((c1 instanceof EClass && !(c2 instanceof EClass)) ||
				 (c2 instanceof EClass && !(c1 instanceof EClass)))
			compatible = false;
		
		// c1 and c2 are primitive types
		else compatible = c1.getName().equals(c2.getName());
		
		return compatible;
	}	
}
