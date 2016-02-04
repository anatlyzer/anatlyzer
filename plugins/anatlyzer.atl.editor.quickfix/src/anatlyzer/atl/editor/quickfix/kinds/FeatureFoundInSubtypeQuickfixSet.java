package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddIfToExpression;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_SpecificPrecondition;

public class FeatureFoundInSubtypeQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new FeatureFoundInSubtypeQuickfix_AddRuleFilter(),
				new FeatureFoundInSubtypeQuickfix_AddIfToExpression(),
				new FeatureFoundInSubtypeQuickfix_AddIfToBlock(),
				new FeatureFoundInSubtypeQuickfix_CreateHelper(),
				new FeatureFoundInSubtypeQuickfix_SpecificPrecondition()
				// This is not very applicable...
				// new FeatureFoundInSubtypeQuickfix_ChangeOperationContext()
		};
	}
}
