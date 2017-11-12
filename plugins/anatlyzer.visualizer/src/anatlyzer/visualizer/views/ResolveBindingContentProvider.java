package anatlyzer.visualizer.views;

import java.util.ArrayList;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleResolutionStatus;

public class ResolveBindingContentProvider implements IGraphEntityContentProvider {


	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object input) {
		if ( input instanceof Binding ) {
			Binding b = (Binding) input;			
			
			ArrayList<Object> result = new ArrayList<Object>();
			// result.add(b.getOutPatternElement().getOutPattern().getRule());
			result.add(b);
			addResolutionInfo(b, result);
			
			return result.toArray();
		}
		return null;
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if ( entity instanceof Binding ) {
			Binding r = (Binding) entity;

			ArrayList<Object> result = new ArrayList<Object>();
			addResolutionInfo(r, result);
			return result.toArray();
		}
		return null;
	}

	private void addResolutionInfo(Binding b,	ArrayList<Object> result) {
		/*
		if ( p instanceof BindingPossiblyUnresolved ) {
			for(RuleResolutionInfo rri : b.getResolvedBy()) {
				result.add(rri);
			}			
			return;
		}
		*/
		/*
		for(int i = 0; i < r.getRules().size(); i++) {
			ResolvedRuleInfo ri = r.getRules().get(i);
			result.add(ri);
		}
		*/
		
		// Not sure if this is needed
		for(RuleResolutionInfo rri : b.getResolvedBy()) {
			if ( rri.getStatus() == RuleResolutionStatus.RESOLUTION_DISCARDED  )
				continue;
			
			result.add(rri);
			/*
			boolean alreadyConsidered = false;
			for(int i = 0; i < result.size(); i++) {
				MatchedRule ri = (MatchedRule) result.get(i);
				if ( ri.getName().equals(rri.getRule().getName()) ) {
					alreadyConsidered = true;
				}
				
				if ( ! alreadyConsidered ) {
					result.add(rri);
				}
			}				
			*/
		}
		
	}

}
