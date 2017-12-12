package anatlyzer.atl.explanations.errors;

import org.eclipse.swt.custom.StyledText;

import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.explanations.ExplanationWithWitness;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atlext.ATL.Binding;

public class BindingInvalidTargetExplanation extends ExplanationWithWitness {

	@Override
	public boolean isApplicable() {
		return checkProblemType(BindingWithResolvedByIncompatibleRule.class);
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		Binding b = (Binding) getProblematicElement();
		text.setText("The binding is resolved by a rule whose first output element has a type which "
				+ "is not compatible with the target feature of the binding: " + b.getPropertyName() + " : " + TypeUtils.typeToString(b.getLeftType()) + "."   
				+ "\n" 
				+ "There are several solutions: you can filter the binding or the problematic rule. Also, if the problematic rule have several target elements, perhaps "
				+ "the order is wrong and the problem can be solved just by swapping the order of the elements in the 'to' part." 
				+ "\n");
	}


}
