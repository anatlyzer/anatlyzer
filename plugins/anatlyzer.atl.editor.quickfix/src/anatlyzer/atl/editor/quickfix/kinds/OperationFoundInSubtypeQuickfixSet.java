package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToExpression;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_ChangeOperationContext;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_SpecificPrecondition;

public class OperationFoundInSubtypeQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new OperationFoundInSubtypeQuickfix_AddRuleFilter(),
				new OperationFoundInSubtypeQuickfix_AddIfToExpression(),
				// new OperationFoundInSubtypeQuickfix_AddIfToBlock(),
				new OperationFoundInSubtypeQuickfix_CreateHelper(),
				new OperationFoundInSubtypeQuickfix_ChangeOperationContext(),
				new OperationFoundInSubtypeQuickfix_SpecificPrecondition()
		};
	}
}
