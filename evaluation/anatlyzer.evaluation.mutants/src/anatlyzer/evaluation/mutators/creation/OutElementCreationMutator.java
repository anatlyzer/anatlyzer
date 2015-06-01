package anatlyzer.evaluation.mutators.creation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.PatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.evaluation.mutators.ATLModel;
import anatlyzer.evaluation.mutators.AbstractMutator;

public class OutElementCreationMutator extends AbstractMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		
		ATLModel  wrapper = new ATLModel(atlModel.getResource());
		
		// we will add a comment to the module, documenting the mutation 
		Module module = wrapper.getModule();
		EDataTypeEList<String> comments = null;
		if (module!=null) {
			EStructuralFeature feature = wrapper.source(module).eClass().getEStructuralFeature("commentsBefore");	
			comments = (EDataTypeEList<String>)wrapper.source(module).eGet(feature);
		}
		
		// for each matched rule
		for (MatchedRule rule : (List<MatchedRule>)wrapper.allObjectsOf(MatchedRule.class)) {
			
			// current out-pattern elements 
			EStructuralFeature feature = wrapper.source(rule.getOutPattern()).eClass().getEStructuralFeature("elements");
			List<PatternElement> realelements = (List<PatternElement>)wrapper.source(rule.getOutPattern()).eGet(feature);
					
			// new out-pattern elements
			List<PatternElement> newelements = new ArrayList<PatternElement>();
			newelements.addAll(this.getOutElement1(outputMM)); // out-pattern element for each meta-model class
					
			// for each new out-pattern element 
			for (PatternElement element : newelements) {
				if (element!=null) {
						
					// mutation: add out-pattern element
					realelements.add(element);

					// mutation: documentation
					if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" " + toString(element) + " in " + toString(rule) + " (line " + rule.getLocation() + " of original transformation)\n");

					// save mutant
					this.save(atlModel, outputFolder);

					// restore: remove added out-pattern element and comment
					realelements.remove(element);
					if (comments!=null) comments.remove(comments.size()-1);						
				}
			}
		}
	}
	
	@Override
	public String getDescription() {
		return "Creation of OutPattern Element";
	}
	
	/**
	 * It returns a list of out-pattern elements.
	 * @param output metamodel
	 */
	private List<PatternElement> getOutElement1 (MetaModel metamodel) {
		List<PatternElement> elements = new ArrayList<PatternElement>();
		for (EClassifier classifier : metamodel.getEClassifiers()) {
			if (classifier instanceof EClass) {
				PatternElement element = ATLFactory.eINSTANCE.createSimpleInPatternElement();
				OclModelElement ome = OCLFactory.eINSTANCE.createOclModelElement();
				OclModel        mm  = OCLFactory.eINSTANCE.createOclModel();
				ome.setName(classifier.getName());
				mm.setName (metamodel.getName());
				ome.setModel(mm);
				element.setType(ome);
				element.setVarName("dummy" + ome.getName() + elements.size());
				elements.add(element);
			}
		}
		return elements;
	}	
}
