package anatlyzer.atl.editor.quickfix.search;

import java.util.List;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.Problem;

public interface ISearchExpansionStrategy {

	List<Expansion> getExpansion(ISearchState state);
	
	public static class Expansion {
		public final Problem p;
		public final AbstractAtlQuickfix qfx;
		
		public Expansion(Problem p, AbstractAtlQuickfix qfx) {
			this.p = p;
			this.qfx = qfx;
		}
	}
}
