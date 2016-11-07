package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickFix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickFix_FindSameOperation;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickfix_CreateHelper;

public class FeatureNotFoundInThisModuleQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new FeatureNotFoundInThisModuleQuickFix_ChooseExisting(),
				new FeatureNotFoundInThisModuleQuickfix_CreateHelper(),
				new FeatureNotFoundInThisModuleQuickFix_FindSameOperation(),
		};
	}
}
