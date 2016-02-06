package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChangeAttributeToOperation;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ConvertReceptorToCollection;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_CreateHelper;

public class OperationNotFoundQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new OperationNotFoundQuickfix_ChooseExisting(),
				new OperationNotFoundQuickfix_ChangeToFeatureCall(),		
				new OperationNotFoundQuickfix_ChangeAttributeToOperation(),		
				new OperationNotFoundQuickfix_CreateHelper(),
				new OperationNotFoundQuickfix_ConvertReceptorToCollection()
		};
	}
}
