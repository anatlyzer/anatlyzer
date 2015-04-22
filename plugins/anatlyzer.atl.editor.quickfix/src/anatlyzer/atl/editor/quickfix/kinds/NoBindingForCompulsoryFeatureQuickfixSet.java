package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_AddBinding;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilar;

public class NoBindingForCompulsoryFeatureQuickfixSet extends AbstractQuickfixSet  {
	
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new NoBindingForCompulsoryFeature_AddBinding(),
				new NoBindingForCompulsoryFeature_FindSimilar()
				// Another option would be to remove the cardinality constraint in the meta-model
		};
	}
}
