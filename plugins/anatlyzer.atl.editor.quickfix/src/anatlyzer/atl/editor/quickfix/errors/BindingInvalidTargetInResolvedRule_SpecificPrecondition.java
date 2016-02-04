package anatlyzer.atl.editor.quickfix.errors;

import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;

/**
 * This quick fix generates a pre-condition. 
 *   
 * @qfxName  Generate pre-condition
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule}
 * 
 * @author jesusc
 */
public class BindingInvalidTargetInResolvedRule_SpecificPrecondition extends
		GeneratePrecondition {

	public BindingInvalidTargetInResolvedRule_SpecificPrecondition() {
		super(BindingWithResolvedByIncompatibleRule.class);
	}
}
