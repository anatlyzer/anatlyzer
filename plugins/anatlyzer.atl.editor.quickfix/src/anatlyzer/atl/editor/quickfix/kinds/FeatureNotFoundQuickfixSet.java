package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_FindSameOperation;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_MakeSubclass;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickfix_CreateHelper;

public class FeatureNotFoundQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new FeatureNotFoundQuickFix_ChooseExisting(),
				new FeatureNotFoundQuickfix_CreateHelper(),
				new FeatureNotFoundQuickFix_FindSameOperation(),
				new FeatureNotFoundQuickFix_ChangeMetamodel(),
				new FeatureNotFoundQuickFix_MakeSubclass()
		};
	}
}
