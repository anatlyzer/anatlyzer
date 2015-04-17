package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateHelper;

public class OperationFoundInSubtypeQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new OperationNotFoundInThisModuleQuickfix_ChooseExisting(),
				new OperationNotFoundInThisModuleQuickfix_CreateHelper()
		};
	}
}
