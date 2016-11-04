package anatlyzer.atl.editor.quickassist;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;
import anatlyzer.atl.editor.quickfix.AtlQuickAssistSet;
import anatlyzer.atlext.ATL.LocatedElement;

public abstract class AbstractQuickAssistSet implements AtlQuickAssistSet {

	protected List<AtlQuickAssist> possibleAssists;
	protected boolean canExpectUserInteraction;
	protected LocatedElement element;
	protected AnalysisResult result;
	
	public abstract AtlQuickAssist[] getPossibleAssists();	
	
	@Override
	public void setCanExpectUserInteraction(boolean b) {
		this.canExpectUserInteraction = b;
	}
	
	@Override
	public void setElement(LocatedElement element, AnalysisResult result) {
		this.element = element;		
		this.result  = result;
	}
	
	@Override
	public boolean isApplicable() {
		possibleAssists = new ArrayList<AtlQuickAssist>();
		for(AtlQuickAssist q : getPossibleAssists()) {
			q.setCanExpectUserInteraction(canExpectUserInteraction);
			q.setElement(element, result);
			if ( q.isApplicable() )
				possibleAssists.add(q);
		}
		return possibleAssists.size() > 0;
	}

	@Override
	public List<AtlQuickAssist> getQuickAssists() {
		return this.possibleAssists;
	}

}
