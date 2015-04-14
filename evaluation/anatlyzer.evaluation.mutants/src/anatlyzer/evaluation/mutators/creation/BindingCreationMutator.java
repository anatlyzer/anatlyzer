package anatlyzer.evaluation.mutators.creation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import transML.utils.modeling.EMFUtils;
import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.evaluation.mutators.ATLModel;
import anatlyzer.evaluation.mutators.AbstractMutator;

public class BindingCreationMutator extends AbstractMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		
		ATLModel                wrapper    = new ATLModel(atlModel.getResource());
		List<OutPatternElement> containers = (List<OutPatternElement>)wrapper.allObjectsOf(OutPatternElement.class);
		
		// we will add a comment to the module, documenting the mutation 
		Module module = wrapper.getModule();
		EDataTypeEList<String> comments = null;
		if (module!=null) {
			EStructuralFeature feature = wrapper.source(module).eClass().getEStructuralFeature("commentsBefore");	
			comments = (EDataTypeEList<String>)wrapper.source(module).eGet(feature);
		}

		// for each out pattern
		for (OutPatternElement container : containers) {
			EStructuralFeature feature = wrapper.source(container).eClass().getEStructuralFeature("bindings");
			if (feature!=null) {
				
				// TODO: organize code (start traversing rules, instead of output pattern elements)
				// TODO: do not add binding if there is already one for the same property (?)
				// TODO: add properties of subclasses
				
				List<? extends VariableDeclaration> vars = new ArrayList<VariableDeclaration>();
				if (container.eContainer().eContainer() instanceof MatchedRule) 
					 vars = ((MatchedRule)container.eContainer().eContainer()).getInPattern().getElements();
				else if (container.eContainer().eContainer() instanceof CalledRule)
					vars = ((CalledRule)container.eContainer().eContainer()).getVariables();
				
				List<Binding> bindings = (List<Binding>)wrapper.source(container).eGet(feature);
				EClassifier classifier = outputMM.getEClassifier( container.getType().getName() );
				if (classifier instanceof EClass) {
					
					// for each feature of the out pattern type
					for (EStructuralFeature property : ((EClass)classifier).getEAllStructuralFeatures()) {
						
						// mutation: create binding
						Binding binding = ATLFactory.eINSTANCE.createBinding();
						binding.setPropertyName( property.getName() );
						binding.setValue( getCompatibleValue(property, vars) );
						bindings.add(binding);

						// mutation: documentation
						if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" " + toString(binding) + " in " + toString(container) + " (line " + container.getLocation() + " of original transformation)\n");

						// save mutant
						this.save(atlModel, outputFolder);

						// restore: remove added binding and comment
						bindings.remove(binding);
						if (comments!=null) comments.remove(comments.size()-1);
					}
				}	
			}
		}
	}
	
	@Override
	public String getDescription() {
		return "Creation of Binding";
	}

	/**
	 * It returns a compatible ocl expression for the received feature.
	 * @param feature
	 * @return
	 */
	private OclExpression getCompatibleValue (EStructuralFeature feature, List<? extends VariableDeclaration> variables) {
		OclExpression expression = null;
		
		System.out.println(feature.eContainer().eClass().getName()+"."+feature.getName()+" "+feature.getEType().getName()+"["+feature.getUpperBound()+"]");
		
		// monovalued features
		if (feature.getUpperBound()==1) {
			if (EMFUtils.isString(feature.getEType().getName())) {
				expression = OCLFactory.eINSTANCE.createStringExp();
				((StringExp)expression).setStringSymbol("dummy");
			}
			else if (EMFUtils.isInteger(feature.getEType().getName())) {
				expression = OCLFactory.eINSTANCE.createIntegerExp();
				((IntegerExp)expression).setIntegerSymbol(0);
			}
			else if (EMFUtils.isBoolean(feature.getEType().getName())) {
				expression = OCLFactory.eINSTANCE.createBooleanExp();
				((BooleanExp)expression).setBooleanSymbol(false);
			}
			else if (EMFUtils.isFloating(feature.getEType().getName())) {
				expression = OCLFactory.eINSTANCE.createRealExp();
				((RealExp)expression).setRealSymbol(0);
			}
			else {
				expression = OCLFactory.eINSTANCE.createVariableExp();
				((VariableExp)expression).setReferredVariable(variables.get(0));
			}
		}
		
		// multivalued features
		else {
			expression = feature.isOrdered()? 
					OCLFactory.eINSTANCE.createSequenceExp() : 
					OCLFactory.eINSTANCE.createSetExp();			
		}
			
		return expression;
	}
}
