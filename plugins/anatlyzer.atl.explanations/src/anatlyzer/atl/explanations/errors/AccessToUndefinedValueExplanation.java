package anatlyzer.atl.explanations.errors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.explanations.ExplanationWithWitness;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;

public class AccessToUndefinedValueExplanation extends ExplanationWithWitness {

	@Override
	public boolean isApplicable() {
		return checkProblemType(AccessToUndefinedValue.class);
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		AccessToUndefinedValue p = (AccessToUndefinedValue) getProblem();
		NavigationOrAttributeCallExp source = (NavigationOrAttributeCallExp) ((PropertyCallExp) p.getElement()).getSource();		
		text.setText("The '" + source.getName() + "' feature is optional in the meta-model. \n\n"
				+ "The expression may fail because the feature access will return OclUndefined. This is "
				+ "similar to 'Null pointer exception' in most object-oriented languages.");
	}
	
	@Override
	public void setAdditionalInfo(Composite composite) {
		// Could also create witness...
		AccessToUndefinedValue p = (AccessToUndefinedValue) getProblem();
		NavigationOrAttributeCallExp source = (NavigationOrAttributeCallExp) ((PropertyCallExp) p.getElement()).getSource();		
	
		EStructuralFeature f = (EStructuralFeature) source.getUsedFeature();
	    
		String mmName = ((Metaclass) source.getInferredType()).getModel().getName();
	    createMetamodelViewer(composite, mmName, f);		
	}


}
