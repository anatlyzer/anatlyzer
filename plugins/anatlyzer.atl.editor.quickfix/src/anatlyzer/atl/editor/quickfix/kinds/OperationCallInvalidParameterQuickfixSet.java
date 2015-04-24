package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidParameterQuickfix_CreateHelper;

public class OperationCallInvalidParameterQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new OperationCallInvalidParameterQuickfix_CreateHelper(),
				new OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition()
		};
	}
}
