package anatlyzer.atl.explanations.errors;

import org.eclipse.swt.custom.StyledText;

import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.explanations.ExplanationWithWitness;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;

public class AccessToUndefinedValueExplanation extends ExplanationWithWitness {

	@Override
	public boolean isApplicable() {
		return checkProblemType(AccessToUndefinedValue.class);
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		AccessToUndefinedValue p = (AccessToUndefinedValue) getProblem();
		
		text.setText("The '" + ((NavigationOrAttributeCallExp) p.getElement()).getName() + "feature is optional. "
				+ "The expression may fail because the feature access will return OclUndefined. This is"
				+ "similar to 'Null pointer exception' in most object-oriented languages.");
	}


}
