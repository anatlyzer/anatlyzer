package anatlyzer.atl.editor.quickfix.errors;

import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;

public class OperationFoundInSubtypeQuickfix_SpecificPrecondition extends
		GeneratePrecondition {

	public OperationFoundInSubtypeQuickfix_SpecificPrecondition() {
		super(OperationFoundInSubtype.class);
	}

}
