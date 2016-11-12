package anatlyzer.atl.explanations.errors;

import org.eclipse.swt.custom.StyledText;

import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.explanations.ExplanationWithWitness;

public class BindingPossiblyUnresolvedExplanation extends ExplanationWithWitness {

	@Override
	public boolean isApplicable() {
		return checkProblemType(BindingPossiblyUnresolved.class);
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		text.setText("There are some configurations of objects (in the right-hand side of the binding) which are not handled by any of the resolving rules");
		// Explain that you should document this or ignore...
	}


}
