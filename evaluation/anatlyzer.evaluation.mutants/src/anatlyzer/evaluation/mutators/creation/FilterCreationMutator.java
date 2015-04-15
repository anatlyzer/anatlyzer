package anatlyzer.evaluation.mutators.creation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.evaluation.mutators.ATLModel;
import anatlyzer.evaluation.mutators.AbstractMutator;

public class FilterCreationMutator extends AbstractMutator {

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
			
			// variable declarations in the input pattern of the rule
			List<? extends VariableDeclaration> ivariables = rule.getInPattern().getElements();
			
			if (rule.getInPattern().getFilter() == null) {
				
				// for each in class
				for (InPatternElement inElement : rule.getInPattern().getElements() ) {
					EClassifier classifier = inputMM.getEClassifier( inElement.getType().getName() );
					if (classifier instanceof EClass) {
						EStructuralFeature feature = wrapper.source(rule.getInPattern()).eClass().getEStructuralFeature("filter");
					
						// TODO: new filters
						List<OclExpression> newfilters = new ArrayList<OclExpression>();
						//newfilters.add(this.getFilter1((EClass)classifier, inElement)); // filter on a property value
					
						// for each new filter 
						for (OclExpression filter : newfilters) {
							if (filter!=null) {
						
								// mutation: add filter
								wrapper.source(rule.getInPattern()).eSet(feature, filter);

								// mutation: documentation
								if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" " + toString(rule.getInPattern()) + " in " + toString(rule) + " (line " + rule.getInPattern().getLocation() + " of original transformation)\n");

								// save mutant
								this.save(atlModel, outputFolder);

								// restore: remove added filter and comment
								wrapper.source(rule.getInPattern()).eSet(feature, null);
								if (comments!=null) comments.remove(comments.size()-1);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public String getDescription() {
		return "Creation of Filter";
	}
	
	/**
	 * It returns a filter on ...
	 * @param 
	 * @param 
	 * @param  
	 */
	private OclExpression getFilter1 (EClass clazz, InPatternElement in) {
		OperatorCallExp exp1 = OCLFactory.eINSTANCE.createOperatorCallExp();
		System.out.println(exp1 instanceof OclExpression);
		exp1.setOperationName("=");
		
		NavigationOrAttributeCallExp exp2 = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
		exp2.setName("firstName");
		
		VariableExp exp3 = OCLFactory.eINSTANCE.createVariableExp();
		exp3.setReferredVariable(in);
		
		StringExp exp4 = OCLFactory.eINSTANCE.createStringExp();
		exp4.setStringSymbol("dummy");
		
		exp1.setSource(exp2);
		exp2.setSource(exp3);
		exp1.getArguments().add(exp4);
		
//		if (bindings!=null && bindings.size()>0) {
//			Binding duplicate   = bindings.get( new Random().nextInt(bindings.size()) );
//			String propertyName = duplicate.getPropertyName();
//			Binding binding     = ATLFactory.eINSTANCE.createBinding();
//			binding.setPropertyName( propertyName );
//			binding.setValue( getCompatibleValue(clazz.getEStructuralFeature(propertyName), ivariables) );
//			return binding;
//		}
		return exp1;
		//return null;
	}
	
	/*
	 variable.property = ''
        <filter xsi:type="ocl:OperatorCallExp" location="41:26-41:40" operationName="=">
          <source xsi:type="ocl:NavigationOrAttributeCallExp" location="41:26-41:37" name="firstName">
            <source xsi:type="ocl:VariableExp" location="41:26-41:27" referredVariable="/0/@elements.2/@inPattern/@elements.0"/>
          </source>
          <arguments xsi:type="ocl:StringExp" location="41:38-41:40" stringSymbol=""/>
        </filter>
	 */
	/*
	 variable.property.oclIsUndefined()
	 <filter xsi:type="ocl:OperationCallExp" location="41:26-41:54" operationName="oclIsUndefined">
          <source xsi:type="ocl:NavigationOrAttributeCallExp" location="41:26-41:37" name="firstName">
            <source xsi:type="ocl:VariableExp" location="41:26-41:27" referredVariable="/0/@elements.2/@inPattern/@elements.0"/>
          </source>
        </filter>
	 */
	/*
	 variable.oclIsKindOf(Type)
        <filter xsi:type="ocl:OperationCallExp" location="41:26-41:56" operationName="oclIsKindOf">
          <source xsi:type="ocl:VariableExp" location="41:26-41:27" referredVariable="/0/@elements.2/@inPattern/@elements.0"/>
          <arguments xsi:type="ocl:OclModelElement" location="41:40-41:55" name="Member" model="/16"/>
        </filter>
	 */
	/*
	 variable.property->size() = 0
        <filter xsi:type="ocl:OperatorCallExp" location="41:26-41:42" operationName="=">
          <source xsi:type="ocl:CollectionOperationCallExp" location="41:26-41:40" operationName="size">
            <source xsi:type="ocl:NavigationOrAttributeCallExp" location="41:26-41:32" name="sons">
              <source xsi:type="ocl:VariableExp" location="41:26-41:27" referredVariable="/0/@elements.2/@inPattern/@elements.0"/>
            </source>
          </source>
          <arguments xsi:type="ocl:IntegerExp" location="41:41-41:42"/>
        </filter>
	 */
	/*
	  variable.helper()=true
        <filter xsi:type="ocl:OperatorCallExp" location="49:26-49:43" operationName="=">
          <source xsi:type="ocl:OperationCallExp" location="49:26-49:38" operationName="isFemale">
            <source xsi:type="ocl:VariableExp" location="49:26-49:27" referredVariable="/0/@elements.3/@inPattern/@elements.0"/>
          </source>
          <arguments xsi:type="ocl:BooleanExp" location="49:39-49:43" booleanSymbol="true"/>
        </filter>
	 */
}
