package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddIfToExpression;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToExpression;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_ChangeOperationContext;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_CreateHelper;

public class FeatureFoundInSubtypeQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new FeatureFoundInSubtypeQuickfix_AddIfToExpression(),
				new FeatureFoundInSubtypeQuickfix_AddIfToBlock(),
				new FeatureFoundInSubtypeQuickfix_CreateHelper()
				//,
				//new FeatureFoundInSubtypeQuickfix_ChangeOperationContext()
		};
	}
}
