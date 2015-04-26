package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_AddArguments;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_RemoveFormalParameters;

public class OperationCallInvalidNumberOfParametersQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments(),
				new OperationCallInvalidNumberOfParametersQuickfix_AddArguments(),
				new OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters(),
				new OperationCallInvalidNumberOfParametersQuickfix_RemoveFormalParameters(),
				new OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation()
		};
	}
}
