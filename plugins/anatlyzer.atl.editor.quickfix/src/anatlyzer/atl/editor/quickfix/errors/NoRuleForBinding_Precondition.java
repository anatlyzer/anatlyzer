package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;

/**
 * This quickfix generates a lightweight pre-condition that only takes into account
 * the type of the right part of the binding.
 * 
 * It is intended to generate a pre-condition that expresses which elements are not
 * handled by rules in the transformation.
 * 
 * @qfxName  Generate most general pre-condition
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved}
 * 
 * @author jesusc
 */
public class NoRuleForBinding_Precondition extends BindingPossiblyUnresolved_Precondition {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		if ( checkProblemType(marker, BindingWithoutRule.class) ) {
			Binding b = (Binding) getProblematicElement(marker);
			// Make sure the right part is only one metaclass, not an union type
			// It is just a limitation to facilitate the implementation and perhaps because
			// this 
			return ATLUtils.getUnderlyingBindingRightMetaclasses(b).size() == 1;
		}
		return false;
	}
	
}
