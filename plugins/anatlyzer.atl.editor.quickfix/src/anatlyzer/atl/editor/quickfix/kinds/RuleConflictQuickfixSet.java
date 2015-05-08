package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.RuleConflictQuickfix_ModifyRuleFilter;

public class RuleConflictQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new RuleConflictQuickfix_ModifyRuleFilter()
		};
	}
}
