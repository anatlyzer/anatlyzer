package anatlyzer.evaluation.mutators.modification;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.evaluation.mutators.ATLModel;
import anatlyzer.evaluation.mutators.AbstractMutator;

public abstract class AbstractModificationMutator extends AbstractMutator {

	/**
	 * It receives the object to modify, and the value of the attribute to modify.
	 * It returns a set of valid replacements for the attribute value. 
	 * To be implemented only by subclasses that call to genericAttributeModification.
	 * @param object2modify
	 * @param currentAttributeValue
	 * @param metamodel
	 * @return set of valid replacements for the attribute value.
	 */
	protected abstract List<String> replacements(EObject object2modify, String currentAttributeValue, MetaModel metamodel);

	/**
	 * Generic modification. It allows subtypes of the class to modify.
	 * @param atlModel
	 * @param outputFolder
	 * @param ToModifyClass class of objects to modify (example Binding)
	 * @param featureName feature to modify (example propertyName)
	 * @param metamodel metamodel containing the candidate types for the modification   
	 */ 
	protected <ToModify extends LocatedElement> 
	void genericAttributeModification(EMFModel atlModel, String outputFolder, Class<ToModify> ToModifyClass, String feature, MetaModel metamodel) {
		genericAttributeModification(atlModel, outputFolder, ToModifyClass, feature, metamodel, false);
	}
	
	/**
	 * Generic modification. The parameter 'exactContainerType' allows configuring whether the type of  
	 * the class to modify must be exactly the one received, or if the subtypes should be also considered.  
	 * @param atlModel
	 * @param outputFolder
	 * @param ToModifyClass class of objects to modify (example Binding)
	 * @param featureName feature to modify (example propertyName)
	 * @param metamodel metamodel containing the candidate types for the modification   
	 * @param exactToModifyType false to consider also subtypes of the class ToModify, true to discard subtypes of the class ToModify  
	 */ 
	protected <ToModify extends LocatedElement> 
	void genericAttributeModification(EMFModel atlModel, String outputFolder, Class<ToModify> ToModifyClass, String feature, MetaModel metamodel, boolean exactToModifyType) {

		ATLModel       wrapper    = new ATLModel(atlModel.getResource());
		List<ToModify> modifiable = (List<ToModify>)wrapper.allObjectsOf(ToModifyClass);

		// we will add a comment to the module, documenting the mutation 
		Module module = wrapper.getModule();
		EDataTypeEList<String> comments = null;
		if (module!=null) {
			EStructuralFeature f = wrapper.source(module).eClass().getEStructuralFeature("commentsBefore");	
			comments = (EDataTypeEList<String>)wrapper.source(module).eGet(f);
		}
		
		// filter subtypes (only if parameter exactToModifyType is true)
		if (exactToModifyType) filterSubtypes(modifiable, ToModifyClass);
		
		for (ToModify object2modify : modifiable) {
			EStructuralFeature featureDefinition = wrapper.source(object2modify).eClass().getEStructuralFeature(feature);
			
			if (featureDefinition!=null && featureDefinition.getUpperBound() == 1) {
				EObject object2modify_src = wrapper.source(object2modify);			
				Object oldFeatureValue = object2modify_src.eGet(featureDefinition); 

				List<String> replacements = this.replacements(object2modify, oldFeatureValue.toString(), metamodel);
				for (String replacement : replacements) {
					if (replacement!=null) {	
						wrapper.source(object2modify).eSet(featureDefinition, replacement);
					
						// mutation: documentation
						if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" from " + oldFeatureValue.toString() + " to " + replacement  + " (line " + object2modify.getLocation() + " of original transformation)\n");

						// save mutant
						this.save(atlModel, outputFolder);
							
						// remove comment
						if (comments!=null) comments.remove(comments.size()-1);
					}
				}
				
				// restore original value
				object2modify_src.eSet(featureDefinition, oldFeatureValue);					
			}
		}
	}
}
