package anatlyzer.atl.analyser.batch.invariants;

import java.util.HashMap;
import java.util.List;

import anatlyzer.atl.analyser.batch.invariants.AllInstancesNode.MockNode;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.graph.AbstractPathVisitor;
import anatlyzer.atl.graph.CallExprNode;
import anatlyzer.atl.graph.DependencyNode;
import anatlyzer.atl.graph.ExecutionNode;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.ImperativeRuleExecutionNode;
import anatlyzer.atl.graph.LoopNode;
import anatlyzer.atl.graph.MatchedRuleExecution;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.UnsupportedTranslation;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.SequenceExp;

public class LazyRulePathVisitor extends AbstractPathVisitor  {

	private CSPModel2 builder;

	public LazyRulePathVisitor(CSPModel2 builder) {
		this.builder = builder;
	}
	
	HashMap<GraphNode, OclExpression> map = new HashMap<>();

	public static OclExpression genCondition(ProblemPath path, CSPModel2 builder) {
		LazyRulePathVisitor visitor = new LazyRulePathVisitor(builder);
		visitor.bottomUp(path);
		
		List<? extends ExecutionNode> exec = path.getExecutionNodes();
		if ( exec.size() == 0 ) {
			return visitor.get(exec.get(0));			
		}
		
		SequenceExp seq = OCLFactory.eINSTANCE.createSequenceExp();
		for (ExecutionNode executionNode : exec) {
			seq.getElements().add(visitor.get(executionNode));
		}
		
		CollectionOperationCallExp r = builder.createCollectionOperationCall(seq, "flatten");
		ATLSerializer.serialize(r);
		return r;
	}

	
	@Override
	public boolean visit(MatchedRuleExecution node) {
		// TODO: Consider the product, which will be similar to AllInstances cross-product
		OclExpression exp = AllInstancesNode.genSingleIterator(builder, node.getRule());
		
		InPatternElement firstIPE = node.getRule().getInPattern().getElements().get(0);
		IteratorExp iterator = builder.createIterator(exp, "collect", firstIPE.getVarName());
		builder.addToScope(firstIPE, iterator.getIterators().get(0));
		
		// Defer setting the body after the evaluation of the depending body 
		put(node, iterator);

		return true;
	}

	@Override
	public void visitAfter(MatchedRuleExecution node) {
		IteratorExp iterator = (IteratorExp) get(node);
		OclExpression body = get(node.getDepending());
		iterator.setBody(body);
	}
	

	private void put(GraphNode node, OclExpression exp) {
		map.put(node, exp);
	}
	
	private OclExpression get(GraphNode node) {
		if ( ! map.containsKey(node)) {
			throw new IllegalStateException();
		}
		return map.get(node);
	}
	
	@Override
	public boolean visit(LoopNode node) {
		if ( node.getLoop() instanceof IterateExp ) {
			throw new UnsupportedTranslation("IterateExp not supported");
		}
		
		Iterator it = node.getLoop().getIterators().get(0);
		OclExpression newReceptor = builder.gen(node.getLoop().getSource());
		IteratorExp iterator = builder.createIterator(newReceptor, ((IteratorExp) node.getLoop()).getName(), it.getVarName());
		
		builder.addToScope(it, iterator.getIterators().get(0));
		
		map.put(node, iterator);
		return true;
	}
	
	@Override
	public void visitAfter(LoopNode node) {
		IteratorExp iterator = (IteratorExp) get(node);
		iterator.setBody(get(node.getDepending()));	
	}
	
	@Override
	public boolean visit(CallExprNode node) {
		DependencyNode dep = node.getDepending();
		if ( ! (dep instanceof ImperativeRuleExecutionNode) ) {
			// This means that actual lazy rule call is within a helper, which is not supported
			throw new UnsupportedTranslation("Lazy rule calls within helpers not supported");
		}
		
		// There are two cases.
		// (1) if the rule execution ends in a MockNode (i.e., the final node) then we just get the first argument (more arguments are unsupported)
		if ( dep.getDepending() instanceof MockNode ) {
			OperationCallExp opCall = (OperationCallExp) node.getCall();
			if ( opCall.getArguments().size() > 1 ) {
				throw new UnsupportedTranslation("Lazy rules with more than one input element are not suppported");
			}
			
			OclExpression expr = builder.gen(opCall.getArguments().get(0));
			put(node, expr);
			return false; 
		} else {
			throw new UnsupportedTranslation("TODO: Inline calls using the original code CallExprNode");
		}
		
	}

	
}
