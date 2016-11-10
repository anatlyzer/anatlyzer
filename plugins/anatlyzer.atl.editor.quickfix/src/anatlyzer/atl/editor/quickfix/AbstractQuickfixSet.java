package anatlyzer.atl.editor.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

public abstract class AbstractQuickfixSet implements AtlProblemQuickfixSet {

	protected List<AtlProblemQuickfix> possibleQuickfixes;
	
	public abstract AtlProblemQuickfix[] getPossibleQuickfixes();	
	
	@Override
	public boolean isApplicable(IMarker marker) {
		possibleQuickfixes = new ArrayList<AtlProblemQuickfix>();
		for(AtlProblemQuickfix q : getPossibleQuickfixes()) {
			try {
				if ( q.isApplicable(marker) )
					possibleQuickfixes.add(q);
			} catch (Exception e) {
				// Internal exceptions are ignored
				// TODO: SHOULD BE LOGGED
				e.printStackTrace();
			}
		}
		return possibleQuickfixes.size() > 0;
	}

	@Override
	public List<AtlProblemQuickfix> getQuickfixes(IMarker marker) {
		return possibleQuickfixes;
	}

}
