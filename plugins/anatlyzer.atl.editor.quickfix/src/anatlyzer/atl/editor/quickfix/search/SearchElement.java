package anatlyzer.atl.editor.quickfix.search;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atlext.ATL.LocatedElement;

/**
 * Represent an element of the search space.
 * @author jesus
 *
 */
public class SearchElement {
	private AtlProblemQuickfix qfx;

	public SearchElement(AtlProblemQuickfix qfx) {
		this.qfx = qfx;
	}

	public AtlProblemQuickfix getQfx() {
		return qfx;
	}
	
	@Override
	public String toString() {
		String location = "";
		if ( qfx instanceof AbstractAtlQuickfix) {
			LocatedElement p = ((AbstractAtlQuickfix) qfx).getProblematicElement();			
			location = p.getLocation();
		}
		
		return qfx.getDisplayString() + " " + qfx.getClass().getSimpleName() + " " + location;		
	}
}
