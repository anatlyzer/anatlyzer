package anatlyzer.visualizer.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;

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
			LocalProblem p = (LocalProblem) input;

			Object[] elems = new Object[r.getRules().size() + 1];
			elems[0] = r;
			for(int i = 0; i < r.getRules().size(); i++) {
				ResolvedRuleInfo ri = r.getRules().get(i);
				elems[i + 1] = ri;
			}
			
			return elems;
		}
		return null;
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if ( entity instanceof BindingResolution ) {
			BindingResolution r = (BindingResolution) entity;

			Object[] elems = new Object[r.getRules().size()];
			for(int i = 0; i < r.getRules().size(); i++) {
				ResolvedRuleInfo ri = r.getRules().get(i);
				elems[i] = ri;
			}
			return elems;
		}
		return null;
	}
}
