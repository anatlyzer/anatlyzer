package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateHelper;

public class FeatureNotFoundQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new FeatureNotFoundQuickFix(),
				new FeatureNotFoundQuickfix_CreateHelper()
		};
	}
}
