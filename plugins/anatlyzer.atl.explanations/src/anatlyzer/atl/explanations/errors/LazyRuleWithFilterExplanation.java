package anatlyzer.atl.explanations.errors;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.errors.atl_error.LazyRuleWithFilter;
import anatlyzer.atl.explanations.AbstractAtlExplanation;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.witness.IWitnessModel;

public class LazyRuleWithFilterExplanation extends AbstractAtlExplanation {

	@Override
	public IWitnessModel getWitness() {
		return null;
	}

	@Override
	public boolean isApplicable() {
		return checkProblemType(LazyRuleWithFilter.class);
	}

	@Override
	public void setDetailedProblemDescription(StyledText text) {
		text.setText("The filter of a lazy rule is ignored at runtime unless the rule inherits from an abstract rule. " 
				+ "\n" 
				+ "" 
				+ "\n");		
	}

	@Override
	public void setAdditionalInfo(Composite scrolledComposite) {
	}

}
