package anatlyzer.atl.analyser.generators;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.graph.GraphNode;

public class GraphvizBuffer {
	private List<GraphNode> allNodes = new ArrayList<GraphNode>();

	private List<GraphNode> subraphList = new ArrayList<GraphNode>();
	private String nodes = "";
	private String edges = "";
	private ArrayList<String> subgraphs = new ArrayList<String>();

	public void addNode(GraphNode node, String label, boolean isLeadingToExecution) {
		int i = addNodeAux(node);
		
		String color = "";
		if ( ! isLeadingToExecution ) {
			color = " style=filled fillcolor = lightgray";
		}
		
		nodes = nodes + "\n" + (genNodeName(i) + "[label=\"" + label + "\"" + color + "];"); 
	}
	
	private int addNodeAux(GraphNode node) {
		// System.out.println("GrapvizBuffer: " + node.getClass().getSimpleName());
		int i = allNodes.indexOf(node);
		if ( i == -1 ) {
			subraphList.add(node);
			allNodes.add(node);
			i = allNodes.size() - 1;
		}
		return i;
	}

	private String genNodeName(int i) {
		return "node_" + i;
	}

	public void addEdge(GraphNode start, GraphNode end) {
		int i = addNodeAux(start);
		int j = addNodeAux(end);;
		if ( i == -1 || j == -1 ) 
			throw new IllegalArgumentException();
		
		edges += "\n" + genNodeName(i) + " -> " + genNodeName(j) + ";";
	}
	
	protected String getText() {
		String body = null;
		if ( subgraphs.size() == 0 ) body = genBody();
		else {
			body = "";
			for (String s : subgraphs) {
				body += s + "\n";
			}
		}

		return "digraph G {\n" +
			body +
			"}";
	}

	protected String genBody() {
	    return "    node [ shape = box ];\n" +
		"    " + nodes + "\n" + edges + "\n";
	}
	
	public void packSubgraph() {
		subgraphs.add("subgraph " + "cluster_" + subgraphs.size() + "{\n" + genBody() + "\n}"); 
		subraphList = new ArrayList<GraphNode>();
		nodes = "";
		edges = "";
	}
	
}
