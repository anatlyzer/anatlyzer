package anatlyzer.atl.editor.quickassist.kinds;

import anatlyzer.atl.editor.quickassist.AbstractQuickAssistSet;
import anatlyzer.atl.editor.quickassist.refactorings.ExpressionRefactoring_CreateHelper;
import anatlyzer.atl.editor.quickassist.refactorings.ExpressionRefactoring_Simplify;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;

public class ExpressionSet extends AbstractQuickAssistSet  {
	@Override
	public AtlQuickAssist[] getPossibleAssists() {
		return new AtlQuickAssist[] {
				new ExpressionRefactoring_CreateHelper(),
				new ExpressionRefactoring_Simplify()
		};
	}
}

