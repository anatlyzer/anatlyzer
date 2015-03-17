package anatlyzer.visualizer.views;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Link;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Node;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;

public class ElementConnectionsContentProvider implements IGraphEntityContentProvider {

	private Result result;
/*
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.result = (Result) newInput;
	}

	@Override
	public Object getSource(Object rel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDestination(Object rel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getElements(Object input) {
		if ( input instanceof Result ) {
			Result r = (Result) input;
			
			List<Node> roots = r.getRootNodes();
			Object[] elems = new Object[roots.size()];
			for(int i = 0; i < roots.size(); i++) {
				elems[i] = r.getRootNodes().get(i);
			}
			return elems;
		}
		return null;
	}
	*/

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object input) {
		if ( input instanceof Result ) {
			Result r = (Result) input;
			
			List<Node> roots = r.getRootNodes();
			Object[] elems = new Object[roots.size()];
			for(int i = 0; i < roots.size(); i++) {
				elems[i] = r.getRootNodes().get(i);
			}
			return elems;
		}
		return null;
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if ( entity instanceof Node ) {
			Node n = (Node) entity;
			Object[] result = new Object[n.getLinks().size()];
			for(int i = 0; i < n.getLinks().size(); i++) {
				result[i] = n.getLinks().get(i).getTarget();
			}
			return result;
		}
		// TODO Auto-generated method stub
		return null;
	}
}
