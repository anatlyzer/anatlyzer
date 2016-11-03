package anatlyzer.atl.editor.quickassist.kinds;

import anatlyzer.atl.editor.quickassist.AbstractQuickAssistSet;
import anatlyzer.atl.editor.quickassist.refactorings.RuleRefactoring_ChangeRuleName;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;

public class RuleRefactoringsSet extends AbstractQuickAssistSet  {
		

	
	@Override
	public AtlQuickAssist[] getPossibleAssists() {
		return new AtlQuickAssist[] {
				new RuleRefactoring_ChangeRuleName()
		};
	}
}
