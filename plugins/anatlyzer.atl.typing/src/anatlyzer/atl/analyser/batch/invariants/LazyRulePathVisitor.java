package anatlyzer.atl.analyser.batch.invariants;

import java.util.HashMap;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.graph.AbstractPathVisitor;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.LoopNode;
import anatlyzer.atl.graph.MatchedRuleExecution;
import anatlyzer.atlext.OCL.OclExpression;

public class LazyRulePathVisitor extends AbstractPathVisitor  {

	private CSPModel2 builder;

	public LazyRulePathVisitor(CSPModel2 builder) {
		this.builder = builder;
	}
	
	HashMap<GraphNode, OclExpression> map = new HashMap<>();
	
	@Override
	public boolean visit(MatchedRuleExecution node) {
		// TODO: Consider the product, which will be similar to AllInstances cross-product
		OclExpression exp = AllInstancesNode.genSingleIterator(builder, node.getRule());
		map.put(node, exp);
		return true;
	}
	
	@Override
	public boolean visit(LoopNode node) {
		node.getDepending()
	}
	
}
