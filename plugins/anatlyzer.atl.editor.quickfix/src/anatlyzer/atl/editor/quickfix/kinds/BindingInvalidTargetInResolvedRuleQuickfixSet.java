package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_ModifiyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_Precondition;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_Remove;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_RemoveRule;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;

public class BindingInvalidTargetInResolvedRuleQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new BindingInvalidTargetInResolvedRule_FilterBinding(),
				new BindingInvalidTargetInResolvedRule_ModifiyRuleFilter(),
				new BindingInvalidTargetInResolvedRule_Remove(),
				new BindingInvalidTargetInResolvedRule_RemoveRule(),
				new GeneratePrecondition(BindingWithResolvedByIncompatibleRule.class),
				new BindingInvalidTargetInResolvedRule_Precondition()
		};
	}
}
