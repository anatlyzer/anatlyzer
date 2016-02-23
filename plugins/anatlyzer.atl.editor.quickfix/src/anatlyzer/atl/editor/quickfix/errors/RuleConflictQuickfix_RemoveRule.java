package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.quickfixast.QuickfixApplication;

/**
 * Given a rule conflict the quick fixes shows the user a dialog to
 * select the rule to be deleted.
 *    
 * @qfxName Remove conflicting rule
 * @qfxError {@link anatlyzer.atl.errors.atl_error.ConflictingRuleSet}
 * 
 * @author jesusc
 */
public class RuleConflictQuickfix_RemoveRule extends RuleConflictQuickfix_ModifyRuleFilter {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, ConflictingRuleSet.class);
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean requiresUserIntervention() {
		return true;
	}
	
	@Override
	public String getDisplayString() {
		return "Remove conflicting rule";
	}
	
}
