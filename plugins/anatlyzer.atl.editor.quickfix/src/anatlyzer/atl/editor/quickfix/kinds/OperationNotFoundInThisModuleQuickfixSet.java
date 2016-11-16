package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChangeAttributeToOperation;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateCalledRule;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateHelperFixed;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateLazyRule;

public class OperationNotFoundInThisModuleQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new OperationNotFoundInThisModuleQuickfix_ChooseExisting(),
				new OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall(),				
				new OperationNotFoundInThisModuleQuickfix_ChangeAttributeToOperation(),		
				new OperationNotFoundInThisModuleQuickfix_CreateHelperFixed(),
				new OperationNotFoundInThisModuleQuickfix_CreateLazyRule(),
				new OperationNotFoundInThisModuleQuickfix_CreateCalledRule(),
				new OperationNotFoundInThisModuleQuickfix_CreateHelper()
		};
	}
}
