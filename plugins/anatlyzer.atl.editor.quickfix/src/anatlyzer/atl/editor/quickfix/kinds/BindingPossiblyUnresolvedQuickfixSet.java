package anatlyzer.atl.editor.quickfix.kinds;

import anatlyzer.atl.editor.quickfix.AbstractQuickfixSet;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_Remove;

public class BindingPossiblyUnresolvedQuickfixSet extends AbstractQuickfixSet  {
		
	@Override
	public AtlProblemQuickfix[] getPossibleQuickfixes() {
		return new AtlProblemQuickfix[] {
				new BindingPossiblyUnresolved_FilterBinding(),
				new BindingPossiblyUnresolved_Remove(),
				new BindingPossiblyUnresolved_Remove()
		};
	}
}
