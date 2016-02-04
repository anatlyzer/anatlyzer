package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_AddRule;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_ModifiyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_Precondition;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_Remove;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_SpecificPrecondition;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;

public class BindingPossiblyUnresolvedQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new BindingPossiblyUnresolved_FilterBinding(),
				new BindingPossiblyUnresolved_ModifiyRuleFilter(),
				new BindingPossiblyUnresolved_Remove(),
				new BindingPossiblyUnresolved_AddRule(),
				new BindingPossiblyUnresolved_Precondition(),
				new BindingPossiblyUnresolved_SpecificPrecondition()
		};
	}
}
