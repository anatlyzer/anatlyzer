package anatlyzer.evaluation.mutators.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import transML.utils.modeling.EMFUtils;
import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
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
		
		ATLModel  wrapper = new ATLModel(atlModel.getResource());
		
		// we will add a comment to the module, documenting the mutation 
		Module module = wrapper.getModule();
		EDataTypeEList<String> comments = null;
		if (module!=null) {
			EStructuralFeature feature = wrapper.source(module).eClass().getEStructuralFeature("commentsBefore");	
			comments = (EDataTypeEList<String>)wrapper.source(module).eGet(feature);
		}
		
		// for each matched rule
//		for (MatchedRule rule : (List<MatchedRule>)wrapper.allObjectsOf(MatchedRule.class)) {
		for (Rule rule : (List<Rule>)wrapper.allObjectsOf(Rule.class)) {
			
			// variable declarations in the input pattern of the rule
//			List<? extends VariableDeclaration> ivariables = rule.getInPattern().getElements();
			List<? extends VariableDeclaration> ivariables = getVariableDeclarations(rule);
			
			// for each out class
			for (OutPatternElement outElement : rule.getOutPattern().getElements()) {
				EClassifier classifier = outputMM.getEClassifier( outElement.getType().getName() );
				if (classifier instanceof EClass) {
					
					// current bindings 
					EStructuralFeature feature = wrapper.source(outElement).eClass().getEStructuralFeature("bindings");
					List<Binding> realbindings = (List<Binding>)wrapper.source(outElement).eGet(feature);
					
					// new bindings
					List<Binding> newbindings = new ArrayList<Binding>();
					newbindings.add(this.getBinding1((EClass)classifier, outElement.getBindings(), ivariables)); // duplicate binding
					newbindings.add(this.getBinding2((EClass)classifier, outElement.getBindings(), ivariables)); // non-duplicate binding with primitive type and correct value
					newbindings.add(this.getBinding3((EClass)classifier, outElement.getBindings(), ivariables)); // non-duplicate binding with non-primitive type and correct value
					newbindings.add(this.getBinding4((EClass)classifier, outElement.getBindings(), ivariables)); // non-duplicate binding and assign an incorrect value
					newbindings.add(this.getBinding5((EClass)classifier, outputMM,                 ivariables)); // binding for property of subclass (correct value)
					
					// for each new binding 
					for (Binding binding : newbindings) {
						if (binding!=null) {
						
							// mutation: add binding
							realbindings.add(binding);

							// mutation: documentation
							if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" " + toString(binding) + " in " + toString(outElement) + " (line " + outElement.getLocation() + " of original transformation)\n");

							// save mutant
							this.save(atlModel, outputFolder);

							// restore: remove added binding and comment
							realbindings.remove(binding);
							if (comments!=null) comments.remove(comments.size()-1);						
						}
					}
				}
			}
		}
	}
	
	public List<? extends VariableDeclaration> getVariableDeclarations (Rule rule) {
		if (rule instanceof MatchedRule && ((MatchedRule)rule).getInPattern()!=null) 
			return ((MatchedRule)rule).getInPattern().getElements();
		if (rule.getVariables() != null) 
			return rule.getVariables();
		return new ArrayList<>();                 
	}
	
	@Override
	public String getDescription() {
		return "Creation of Binding";
	}
	
	/**
	 * It returns a duplicate binding.
	 * @param clazz out class for binding
	 * @param bindings list of bindings defined for the our class
	 * @param ivariables input variable declarations 
	 */
	private Binding getBinding1 (EClass clazz, List<Binding> bindings, List<? extends VariableDeclaration> ivariables) {
		if (bindings!=null && bindings.size()>0) {
			Binding duplicate   = bindings.get( new Random().nextInt(bindings.size()) );
			String propertyName = duplicate.getPropertyName();
			Binding binding     = ATLFactory.eINSTANCE.createBinding();
			binding.setPropertyName( propertyName );
			binding.setValue( getCompatibleValue(clazz.getEStructuralFeature(propertyName), ivariables) );
			return binding;
		}
		return null;
	}
	
	/**
	 * It returns a non-duplicate binding with primitive type and correct value.
	 * @param clazz out class for binding
	 * @param bindings list of bindings defined for the our class
	 * @param ivariables input variable declarations 
	 */
	private Binding getBinding2 (EClass clazz, List<Binding> bindings, List<? extends VariableDeclaration> ivariables) {
		for (EStructuralFeature feature : clazz.getEAllStructuralFeatures()) {
			if ( hasPrimitiveType(feature) && !bindings.stream().anyMatch( b -> b.getPropertyName().equals(feature.getName())) ) {
				String propertyName = feature.getName();
				Binding binding     = ATLFactory.eINSTANCE.createBinding();
				binding.setPropertyName( propertyName );
				binding.setValue( getCompatibleValue(feature, ivariables) );
				return binding;
			}
		}
		return null;
	}
	
	/**
	 * It returns a non-duplicate binding with non-primitive type and correct value.
	 * @param clazz out class for binding
	 * @param bindings list of bindings defined for the our class
	 * @param ivariables input variable declarations 
	 */
	private Binding getBinding3 (EClass clazz, List<Binding> bindings, List<? extends VariableDeclaration> ivariables) {
		for (EStructuralFeature feature : clazz.getEAllStructuralFeatures()) {
			if ( !hasPrimitiveType(feature) && !bindings.stream().anyMatch( b -> b.getPropertyName().equals(feature.getName())) ) {
				String propertyName = feature.getName();
				Binding binding     = ATLFactory.eINSTANCE.createBinding();
				binding.setPropertyName( propertyName );
				binding.setValue( getCompatibleValue(feature, ivariables) );
				return binding;
			}
		}
		return null;
	}
	
	/**
	 * It returns a non-duplicate binding with an incorrect value.
	 * @param clazz out class for binding
	 * @param bindings list of bindings defined for the our class
	 * @param ivariables input variable declarations 
	 */
	private Binding getBinding4 (EClass clazz, List<Binding> bindings, List<? extends VariableDeclaration> ivariables) {
		for (EStructuralFeature feature : clazz.getEAllStructuralFeatures()) {
			if ( !bindings.stream().anyMatch( b -> b.getPropertyName().equals(feature.getName())) ) {
				String propertyName = feature.getName();
				Binding binding     = ATLFactory.eINSTANCE.createBinding();
				binding.setPropertyName( propertyName );
				binding.setValue( getIncompatibleValue(feature, ivariables) );
				return binding;
			}
		}
		return null;
	}
	
	/**
	 * It returns a binding for a property defined in a subclass and correct value.
	 * @param clazz out class for binding
	 * @param metamodel output metamodel
	 * @param ivariables input variable declarations 
	 */
	private Binding getBinding5 (EClass clazz, MetaModel metamodel, List<? extends VariableDeclaration> ivariables) {
		for (EClassifier classifier : metamodel.getEClassifiers()) {
			if (classifier instanceof EClass) {
				EClass child = ((EClass)classifier);
				if (child.getEAllSuperTypes().contains(clazz) && !child.getEStructuralFeatures().isEmpty()) {
					int                number  = new Random().nextInt(child.getEStructuralFeatures().size());
					EStructuralFeature feature = child.getEStructuralFeatures().get(number);
					Binding            binding = ATLFactory.eINSTANCE.createBinding();
					binding.setPropertyName( feature.getName() );
					binding.setValue( getCompatibleValue(feature, ivariables) );
					return binding;
				}
			}
		}
		return null;
	}
	
	/**
	 * It returns a compatible ocl expression for the received feature.
	 * @param feature
	 * @param variables (used when the feature has a non-primitive type)
	 */
	private OclExpression getCompatibleValue (EStructuralFeature feature, List<? extends VariableDeclaration> variables) {
		return getCompatibleValue(feature.getEType().getName(), feature.getUpperBound()==1, feature.isOrdered(), variables);
	}
	
	/**
	 * It returns a compatible ocl expression for the received type.
	 * @param type
	 * @param monovalued
	 * @param ordered (used in case of collections, i.e., when monovalued==true)
	 * @param variables (used when the type is not primitive)
	 */
	private OclExpression getCompatibleValue (String type, boolean monovalued, boolean ordered, List<? extends VariableDeclaration> variables) {
		OclExpression expression = null;
		
		if (monovalued) {
			if (EMFUtils.isString(type)) {
				expression = OCLFactory.eINSTANCE.createStringExp();
				((StringExp)expression).setStringSymbol("dummy");
			}
			else if (EMFUtils.isInteger(type)) {
				expression = OCLFactory.eINSTANCE.createIntegerExp();
				((IntegerExp)expression).setIntegerSymbol(0);
			}
			else if (EMFUtils.isBoolean(type)) {
				expression = OCLFactory.eINSTANCE.createBooleanExp();
				((BooleanExp)expression).setBooleanSymbol(false);
			}
			else if (EMFUtils.isFloating(type)) {
				expression = OCLFactory.eINSTANCE.createRealExp();
				((RealExp)expression).setRealSymbol(0);
			}
			else {
				expression = OCLFactory.eINSTANCE.createVariableExp();
				if (variables.size()>0) ((VariableExp)expression).setReferredVariable(variables.get(0));
			}
		}
		
		else {
			expression = ordered? 
					OCLFactory.eINSTANCE.createSequenceExp() : 
					OCLFactory.eINSTANCE.createSetExp();			
		}
			
		return expression;
	}
	
	/**
	 * It returns a compatible ocl expression for the received feature.
	 * @param feature
	 * @return
	 */
	private OclExpression getIncompatibleValue (EStructuralFeature feature, List<? extends VariableDeclaration> variables) {
		List<String> types = new ArrayList<String>();
		if (!EMFUtils.isBoolean (feature.getEType().getName())) { types.add("Boolean"); }
		if (!EMFUtils.isFloating(feature.getEType().getName())) { types.add("Double");  }
		if (!EMFUtils.isInteger (feature.getEType().getName())) { types.add("Integer"); }
		if (!EMFUtils.isString  (feature.getEType().getName())) { types.add("String");  }
		if (types.size() < 4)                                   { types.add("Other");   }
		return getCompatibleValue(types.get(new Random().nextInt(types.size())), feature.getUpperBound()!=1, !feature.isOrdered(), variables);
	}	
	
	/**
	 * It returns whether the received feature has a primitive type.
	 */
	private boolean hasPrimitiveType (EStructuralFeature feature) {
		return EMFUtils.isBoolean(feature.getEType().getName()) || EMFUtils.isFloating(feature.getEType().getName()) ||
			   EMFUtils.isInteger(feature.getEType().getName()) || EMFUtils.isString(feature.getEType().getName())   ;	
	}
}
