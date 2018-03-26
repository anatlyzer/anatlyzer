package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.ResolveTempResolution;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

/**
 * 
 * Analyses the transformation trying to figure out if
 * there are objects that will not be connected to the others.
 * (i.e., determine connected components).
 *
 * TODO: This does not work for lazy rules!
 * 
 * @author jesus
 *
 */
public class UnconnectedElementsAnalysis {

	private IAnalyserResult analyser;

	public UnconnectedElementsAnalysis(ATLModel model, IAnalyserResult analyser) {
		this.analyser = analyser;
	}
	
	public static class Result {
		HashSet<Node> rootNodes;
		private LinkedList<Cluster> clusters;
		private Collection<Node> allNodes;
		
		public Result(Collection<Node> allNodes, LinkedList<Cluster> clusters, HashSet<Node> nodes) {
			this.allNodes = allNodes;
			this.clusters = clusters;
			this.rootNodes = nodes;
		}
		
		public List<Node> getRootNodes() {
			return new ArrayList<UnconnectedElementsAnalysis.Node>(rootNodes);
		}
		
		public LinkedList<Cluster> getClusters() {
			return clusters;
		}
		
		public ArrayList<Node> getAllNodes() {
			return new ArrayList<Node>(allNodes);
		}
		
		/*
		public List<OutPatternElement> getUnconnected() {
			ArrayList<OutPatternElement> result = new ArrayList<OutPatternElement>();
			for (Node node : rootNodes) {
				result.add(node.out);
			}
			return result;
		}
		*/
	}
	
	/**
	 * Represents a set of target elements connected by the bindings.
	 * @author jesus
	 *
	 */
	public static class Cluster implements IDetectedProblem {
		private HashSet<Node> nodes = new HashSet<UnconnectedElementsAnalysis.Node>();
		private int id;
		
		public Cluster(int id) {
			this.id = id;
		}
		
		public void addNode(Node n) {
			nodes.add(n);
		}
		
		public HashSet<Node> getNodes() {
			return new HashSet<UnconnectedElementsAnalysis.Node>(nodes);
		}
		
		public HashSet<Node> getRootNodes() {
			return findRootNodes(nodes);
		}

		public int getId() {
			return id;
		}

		// IDetectedProblem
		
		@Override
		public ErrorSlice getErrorSlice(IAnalyserResult result) {
			return null;
		}

		@Override
		public OclExpression getWitnessCondition() {
			return null;
		}

		@Override
		public boolean isExpressionInPath(OclExpression expr) {
			return false;
		}

		@Override
		public List<OclExpression> getFrameConditions() {
			return null;
		}
		
	}
	
	public Result perform() {
		Unit unit = analyser.getATLModel().getRoot();		
		if ( unit instanceof Module ) {
			new RuleVisitor().perform((Module) unit);
			new BindingVisitor().perform((Module) unit);
			
			HashSet<Node> nodes = findRootNodes(this.nodes.values());
			LinkedList<Cluster> clusters = findConnectedComponents();
			
			return new Result(this.nodes.values(), clusters, nodes);
		}
		return new Result(new ArrayList<UnconnectedElementsAnalysis.Node>(), new LinkedList<Cluster>(), new HashSet<Node>());
	}
	

	private LinkedList<Cluster> findConnectedComponents() {
		// Compute connected components
		HashMap<Node, Cluster> visited = new HashMap<Node, Cluster>();
		LinkedList<Cluster> components = new LinkedList<UnconnectedElementsAnalysis.Cluster>();	
		
		int clusterId = 0;
		for(Node n : nodes.values()) {
			if ( visited.containsKey(n) ) {
				// already assigned
				continue;
			}
			
			Cluster cluster = new Cluster(clusterId++);
			components.add(cluster);
			
			Deque<Node> toVisit = new LinkedList<UnconnectedElementsAnalysis.Node>();
			toVisit.add(n);
			
			while ( ! toVisit.isEmpty() ) {
				Node current = toVisit.pop();
				if ( visited.containsKey(current) ) {
					if ( visited.get(n) != cluster )
						throw new IllegalStateException();
					continue;
				}
				
				cluster.addNode(current);
				visited.put(current, cluster);
				
				for(Link l : allLinks) {
					if ( l.getSrc() == current ) {
						toVisit.add(l.getTarget());
					}
					else if ( l.getTarget() == current ) {
						toVisit.add(l.getSrc());
					}
				}
			}

		}

		return components;
	}
	
	private static HashSet<Node> findRootNodes(Collection<Node> collection) {
		// This loop give the root elements
		HashSet<Node> rootNodes = new HashSet<UnconnectedElementsAnalysis.Node>(collection);
		for(Node n : collection) {
			for(Link l : n.links) {
				rootNodes.remove(l.tgt);
			}
		}
		
		
		for (Node node : rootNodes) {
			node.setRootNode(true);
		}
		
		return rootNodes;
	}

	// Common variables
	HashMap<OutPatternElement, Node> nodes = new HashMap<OutPatternElement, UnconnectedElementsAnalysis.Node>();
	ArrayList<Link> allLinks = new ArrayList<UnconnectedElementsAnalysis.Link>();
	
	// Auxiliary classes
	
	public class RuleVisitor extends AbstractVisitor {
		public void perform(Module m) {
			startVisiting(m);
		}
		
		@Override
		public void inMatchedRule(MatchedRule self) {
			if ( self.isIsAbstract() )
				return;
			
			OutPatternElement output1 = getMainOutputElement(self);
			if ( output1 == null )
				return;
			
			MainTarget node1 = new MainTarget(output1);
			nodes.put(output1, node1);
			
			for(int i = 1; i < self.getOutPattern().getElements().size(); i++) {
				OutPatternElement output2 = self.getOutPattern().getElements().get(i);
			
				SecondaryTarget node2 = new SecondaryTarget(output2);				
				nodes.put(output2, node2);
			}
			
			// Set the possible local links for every output element, including the first one
			for(int i = 0; i < self.getOutPattern().getElements().size(); i++) {
				OutPatternElement output = self.getOutPattern().getElements().get(i);
				setLocalLinks(self.getOutPattern(), nodes.get(output));
			}
		}

		private void setLocalLinks(OutPattern outPattern, Node node) {
			for (OutPatternElement out : outPattern.getElements()) {
				if ( out == node.out )
					continue;
				
				for (Binding b : out.getBindings()) {
					// Find VarExp referring to the variable declared in the
					// output pattern of the secondary target element. If found, assume it is
					// assigned to the binding.
					boolean found = false;
					TreeIterator<EObject> it = b.eAllContents();
					while (it.hasNext()) {
						EObject obj = it.next();
						if (obj instanceof VariableExp) {
							VariableExp exp = (VariableExp) obj;
							if (exp.getReferredVariable() == node.out) {
								found = true;
								break;
							}
						}
					}

					if (found) {
						// The node containing the binding that points to the
						// passed node is linked to it
						allLinks.add(nodes.get(out).linkTo(node));
					}
				}

			}
		}

	}

	public class BindingVisitor extends AbstractVisitor {
		
		public void perform(Module mod) {
			startVisiting(mod);
		}
		
		@Override
		public void inBinding(Binding self) {
			OutPatternElement out = self.getOutPatternElement();			
			Node src = nodes.get(out);
			
			for(RuleResolutionInfo r : self.getResolvedBy()) {
				OutPatternElement mainOutput = getMainOutputElement(r.getRule());
				Node dest = nodes.get(mainOutput);
							
				allLinks.add(src.linkTo(dest));
			}
		}
		
		@Override
		public void inOperationCallExp(OperationCallExp self) {
			if ( self.getOperationName().equals("resolveTemp") ) {
				OutPatternElement out = ATLUtils.getContainer(self, OutPatternElement.class);
				Node src = nodes.get(out);
				
				for(ResolveTempResolution r : self.getResolveTempResolvedBy() ) {
					OutPatternElement resolvedOutput = r.getElement();
					Node dest = nodes.get(resolvedOutput);
					
					allLinks.add(src.linkTo(dest));
				}
			}
		}
		
	}
	
	/**
	 * Represents a created element, with links to the other target elements
	 * that are linked to it through a reference set by a binding.
	 * 
	 * @author jesus
	 *
	 */
	public static abstract class Node {
		protected OutPatternElement out;
		protected LinkedList<Link> links = new LinkedList<UnconnectedElementsAnalysis.Link>();
		private boolean isRootNode;
		
		public Node(OutPatternElement out) {
			this.out = out;
		}
		

		public Link linkTo(Node dest) {
			Link l = new Link(this, dest);
			links.add(l);
			return l;
		}
		
		public OutPatternElement getOut() {
			return out;
		}
		
		public LinkedList<Link> getLinks() {
			return links;
		}

		public HashSet<Node> getGeneratedNodes() {
			return computeGeneratedNodes(new HashSet<UnconnectedElementsAnalysis.Node>());
		}

		protected HashSet<Node> computeGeneratedNodes(HashSet<Node> n) {
			if ( n.contains(this) )
				return n;
			
			n.add(this);
			for (Link l : links) {
				n = l.tgt.computeGeneratedNodes(n);
			}
			
			return n;
		}
		
		public void setRootNode(boolean isClusterBeginning) {
			this.isRootNode = isClusterBeginning;
		}
		
		public boolean isRootNode() {
			return isRootNode;
		}
	}
	
	public static class MainTarget extends Node {

		public MainTarget(OutPatternElement out) {
			super(out);
		}
		
	}
	
	public static class SecondaryTarget extends Node {

		public SecondaryTarget(OutPatternElement out) {
			super(out);
		}
		
	}
	
	/**
	 * A directed link between two nodes.
	 * 
	 * @author jesus
	 *
	 */
	public static class Link {
		private Node src;
		private Node tgt;

		public Link(Node src, Node tgt) {
			this.src = src;
			this.tgt = tgt;
		}

		public Node getSrc() {
			return src;
		}
		
		public Node getTarget() {
			return tgt;
		}
	}
	

	private OutPatternElement getMainOutputElement(MatchedRule self) {
		if ( self.getOutPattern() == null || self.getOutPattern().getElements().isEmpty() )
			return null;

		return ATLUtils.getMainOutputPatternElement(self);
	}	
}
