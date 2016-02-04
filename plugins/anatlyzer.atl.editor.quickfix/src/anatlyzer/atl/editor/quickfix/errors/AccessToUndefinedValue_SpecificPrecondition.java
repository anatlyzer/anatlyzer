package anatlyzer.atl.editor.quickfix.errors;

import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;

public class AccessToUndefinedValue_SpecificPrecondition extends
		GeneratePrecondition {

	public AccessToUndefinedValue_SpecificPrecondition() {
		super(AccessToUndefinedValue.class);
	}

}
