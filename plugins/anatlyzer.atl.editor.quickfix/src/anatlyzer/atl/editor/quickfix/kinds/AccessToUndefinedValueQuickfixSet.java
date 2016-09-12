package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddIf;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_SpecificPrecondition;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;

public class AccessToUndefinedValueQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new AccessToUndefinedValue_AddRuleFilter(),
				new AccessToUndefinedValue_AddIf(),
				new AccessToUndefinedValue_ChangeMetamodel(),
				new AccessToUndefinedValue_SpecificPrecondition(),
				new AccessToUndefinedValue_Precondition(),
		};
	}
}
