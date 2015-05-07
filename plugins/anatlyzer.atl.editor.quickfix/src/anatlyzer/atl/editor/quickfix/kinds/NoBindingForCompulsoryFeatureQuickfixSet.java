package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_AddBinding;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarExpression;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarFeature;

public class NoBindingForCompulsoryFeatureQuickfixSet extends AbstractQuickfixSet  {
	
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new NoBindingForCompulsoryFeature_AddBinding(),
				new NoBindingForCompulsoryFeature_FindSimilarExpression(),
				new NoBindingForCompulsoryFeature_FindSimilarFeature(),				
				new NoBindingForCompulsoryFeature_ChangeMetamodel()
				// Another option would be to remove the cardinality constraint in the meta-model
		};
	}
}
