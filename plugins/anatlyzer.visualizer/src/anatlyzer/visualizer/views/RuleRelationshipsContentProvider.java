package anatlyzer.visualizer.views;

import java.util.ArrayList;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityRelationshipContentProvider;

import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

public class RuleRelationshipsContentProvider implements IGraphEntityRelationshipContentProvider {

	@Override
	public Object[] getElements(Object input) {
		if ( input instanceof MatchedRule ) {
			MatchedRule mr = (MatchedRule) input;			
			
			ArrayList<Object> result = new ArrayList<Object>();
			// result.add(b.getOutPatternElement().getOutPattern().getRule());
			result.add(mr);
			
			ATLUtils.getAllOutputPatternElement(mr).stream().
				flatMap(ope -> ope.getBindings().stream()).
				flatMap(b -> b.getResolvedBy().stream()).
				forEach(rri -> result.add(rri.getRule()));
			
			return result.toArray();
		}
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getRelationships(Object source, Object dest) {
		MatchedRule mr = (MatchedRule) source;
		
		return ATLUtils.getAllOutputPatternElement(mr).stream().
			flatMap(ope -> ope.getBindings().stream()).
			flatMap(b -> b.getResolvedBy().stream()).
			filter(rri -> rri.getRule() == dest).
			findFirst().map(rri -> new Object[] { rri }).orElse(new Object[0]);
	}


}
