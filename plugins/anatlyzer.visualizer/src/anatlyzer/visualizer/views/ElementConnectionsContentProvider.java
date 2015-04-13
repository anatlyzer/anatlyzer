package anatlyzer.visualizer.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Cluster;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Node;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;

public class ElementConnectionsContentProvider implements IGraphEntityContentProvider {

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
			
			List<Cluster> clusters = r.getClusters();
			ArrayList<Node> allNodes = r.getAllNodes();
			
			Object[] elems = new Object[clusters.size() + allNodes.size()];
			int i;
			for(i = 0; i < clusters.size(); i++) {
				elems[i] = clusters.get(i);
			}
			
			for(int j = 0; j < allNodes.size(); j++) {
				elems[i + j] = allNodes.get(j);
			}
			
			return elems;
		}
		return null;
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if ( entity instanceof Cluster ) {
			Cluster c = (Cluster) entity;
			HashSet<Node> nodes = c.getRootNodes(); 
			Object[] result = new Object[nodes.size()];
			int i = 0;
			for (Node node : nodes) {
				result[i++] = node;
			}
			return result;
		} else if ( entity instanceof Node ) {
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
