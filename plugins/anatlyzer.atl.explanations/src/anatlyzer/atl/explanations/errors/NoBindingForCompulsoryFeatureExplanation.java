package anatlyzer.atl.explanations.errors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.explanations.AbstractAtlExplanation;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atlext.ATL.OutPatternElement;

public class NoBindingForCompulsoryFeatureExplanation extends AbstractAtlExplanation {

	@Override
	public IWitnessModel getWitness() {
		return null;
	}
	
	@Override
	public boolean isApplicable() {
		return checkProblemType(NoBindingForCompulsoryFeature.class);
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		text.setText("This problem appears when a meta-model feature is compulsory but there is no binding to initialize it.");
		// Explain it may provoke issues in others trafos...
		// Put an example...
	}

	@Override
	public void setAdditionalInfo(Composite composite) {
	    NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
	    OutPatternElement out = (OutPatternElement) p.getElement();
	    String mmName = ((Metaclass) out.getInferredType()).getModel().getName();
	    
	    EStructuralFeature f = p.getFeature();
		createMetamodelViewer(composite, mmName, f);	    
	}



}
