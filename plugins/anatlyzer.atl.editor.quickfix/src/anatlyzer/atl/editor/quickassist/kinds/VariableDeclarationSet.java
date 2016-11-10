package anatlyzer.atl.editor.quickassist.kinds;

import anatlyzer.atl.editor.quickassist.AbstractQuickAssistSet;
import anatlyzer.atl.editor.quickassist.refactorings.ExpressionRefactoring_CreateHelper;
import anatlyzer.atl.editor.quickassist.refactorings.VariableDeclaration_ReplaceWithInferredType;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;

public class VariableDeclarationSet extends AbstractQuickAssistSet  {
	@Override
	public AtlQuickAssist[] getPossibleAssists() {
		return new AtlQuickAssist[] {
				new VariableDeclaration_ReplaceWithInferredType()
		};
	}
}

