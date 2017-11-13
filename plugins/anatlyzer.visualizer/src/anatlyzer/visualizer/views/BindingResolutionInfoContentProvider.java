package anatlyzer.visualizer.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

public class BindingResolutionInfoContentProvider implements IGraphEntityContentProvider {

	private BindingResolution problem;

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object input) {
		if ( input instanceof BindingResolution ) {
			BindingResolution r = (BindingResolution) input;			
			
			ArrayList<Object> result = new ArrayList<Object>();
			result.add(r);
			addResolutionInfo(r, result);
			
			/*
			Object[] elems = new Object[r.getRules().size() + 1];
			elems[0] = r;
			for(int i = 0; i < r.getRules().size(); i++) {
				ResolvedRuleInfo ri = r.getRules().get(i);
				elems[i + 1] = ri;
			}
			*/
			
			return result.toArray();
		}
		return null;
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if ( entity instanceof BindingResolution ) {
			BindingResolution r = (BindingResolution) entity;

			/*
			Object[] elems = new Object[r.getRules().size()];
			for(int i = 0; i < r.getRules().size(); i++) {
				ResolvedRuleInfo ri = r.getRules().get(i);
				elems[i] = ri;
			}
			return elems;
			*/
			ArrayList<Object> result = new ArrayList<Object>();
			addResolutionInfo(r, result);
			return result.toArray();
		}
		return null;
	}

	private void addResolutionInfo(BindingResolution r,	ArrayList<Object> result) {
		LocalProblem p = (LocalProblem) r;
		Binding b = (Binding) p.getElement();

		List<RuleResolutionInfo> possibleResolutions = ATLUtils.getPossibleResolutions(b);
		if ( p instanceof BindingPossiblyUnresolved ) {
			for(RuleResolutionInfo rri : possibleResolutions) {
				result.add(rri);
			}			
			return;
		}
		
		for(int i = 0; i < r.getRules().size(); i++) {
			ResolvedRuleInfo ri = r.getRules().get(i);
			if ( possibleResolutions.stream().anyMatch(res -> res.getRule() == ri.getElement()) )
				result.add(ri);
		}
		for(RuleResolutionInfo rri : possibleResolutions) {
			boolean alreadyConsidered = false;
			for(int i = 0; i < r.getRules().size(); i++) {
				ResolvedRuleInfo ri = r.getRules().get(i);
				if ( ri.getRuleName().equals(rri.getRule().getName()) ) {
					alreadyConsidered = true;
				}
				
				if ( ! alreadyConsidered ) {
					result.add(rri);
				}
			}				
		}
	}

}
