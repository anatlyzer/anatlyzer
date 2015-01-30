package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.Unit;
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
 * @author jesus
 *
 */
public class UnconnectedElementsAnalysis {

	private Analyser analyser;

	public UnconnectedElementsAnalysis(ATLModel model, Analyser analyser) {
		this.analyser = analyser;
	}
	
	public List<OutPatternElement> perform() {
		Unit unit = analyser.getATLModel().getRoot();		
		if ( unit instanceof Module ) {
			new RuleVisitor().perform((Module) unit);
			new BindingVisitor().perform((Module) unit);
			List<OutPatternElement> elements = findConnectedComponents();
			
			return elements;
		}
		return Collections.emptyList();
	}
	
	private List<OutPatternElement> findConnectedComponents() {
		HashSet<Node> notConnected = new HashSet<UnconnectedElementsAnalysis.Node>(nodes.values());
		for(Node n : nodes.values()) {
			for(Link l : n.links) {
				notConnected.remove(l.tgt);
			}
		}
		
		ArrayList<OutPatternElement> result = new ArrayList<OutPatternElement>();
		for (Node node : notConnected) {
			result.add(node.out);
		}
		return result;
	}

	// Common variables
	HashMap<OutPatternElement, Node> nodes = new HashMap<OutPatternElement, UnconnectedElementsAnalysis.Node>();
	
	
	// Auxiliary classes
	
	public class RuleVisitor extends AbstractVisitor {
		public void perform(Module m) {
			startVisiting(m);
		}
		
		@Override
		public void inMatchedRule(MatchedRule self) {
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
						nodes.get(out).linkTo(node);
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
							
				src.linkTo(dest);
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
					
					src.linkTo(dest);
				}
			}
		}
		
	}
	
	public abstract class Node {
		protected OutPatternElement out;
		protected LinkedList<Link> links = new LinkedList<UnconnectedElementsAnalysis.Link>();
		
		public Node(OutPatternElement out) {
			this.out = out;
		}
		

		public void linkTo(Node dest) {
			Link l = new Link(this, dest);
			links.add(l);
		}
	}
	
	public class MainTarget extends Node {

		public MainTarget(OutPatternElement out) {
			super(out);
		}
		
	}
	
	public class SecondaryTarget extends Node {

		public SecondaryTarget(OutPatternElement out) {
			super(out);
		}
		
	}
	
	class Link {
		@SuppressWarnings("unused")
		private Node src;
		private Node tgt;

		public Link(Node src, Node tgt) {
			this.src = src;
			this.tgt = tgt;
		}
	}
	

	private OutPatternElement getMainOutputElement(MatchedRule self) {
		if ( self.getOutPattern() == null || self.getOutPattern().getElements().isEmpty() )
			return null;
		
		OutPatternElement output = self.getOutPattern().getElements().get(0);
		return output;
	}	
}
