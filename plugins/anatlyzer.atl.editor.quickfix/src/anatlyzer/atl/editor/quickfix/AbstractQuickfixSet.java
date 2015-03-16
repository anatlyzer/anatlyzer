package anatlyzer.atl.editor.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;

public abstract class AbstractQuickfixSet implements AtlProblemQuickfixSet {

	protected List<AtlProblemQuickfix> possibleQuickfixes;
	
	public abstract AtlProblemQuickfix[] getPossibleQuickfixes();	
	
	@Override
	public boolean isApplicable(IMarker marker) {
		possibleQuickfixes = new ArrayList<AtlProblemQuickfix>();
		for(AtlProblemQuickfix q : getPossibleQuickfixes()) {
			if ( q.isApplicable(marker) )
				possibleQuickfixes.add(q);
		}
		return possibleQuickfixes.size() > 0;
	}

	@Override
	public List<AtlProblemQuickfix> getQuickfixes(IMarker marker) {
		return possibleQuickfixes;
	}

}
