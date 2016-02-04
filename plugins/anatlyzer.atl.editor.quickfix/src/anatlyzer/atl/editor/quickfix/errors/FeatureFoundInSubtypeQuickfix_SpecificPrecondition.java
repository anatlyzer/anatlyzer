package anatlyzer.atl.editor.quickfix.errors;

import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;

public class FeatureFoundInSubtypeQuickfix_SpecificPrecondition extends GeneratePrecondition {

	public FeatureFoundInSubtypeQuickfix_SpecificPrecondition() {
		super(FeatureFoundInSubtype.class);
	}

}
