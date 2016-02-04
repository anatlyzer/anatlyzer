package anatlyzer.atl.editor.quickfix.errors;

import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;

/**
 * This quick fix generates a pre-condition. 
 *   
 * @qfxName  Generate pre-condition
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved}
 * 
 * @author jesusc
 */
public class BindingPossiblyUnresolved_SpecificPrecondition extends GeneratePrecondition {

	public BindingPossiblyUnresolved_SpecificPrecondition() {
		super(BindingPossiblyUnresolved.class);
	}

}
