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
import anatlyzer.atl.graph.MatchedRuleAbstract;
import anatlyzer.atl.graph.MatchedRuleExecution;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.UnsupportedTranslation;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class LazyRulePathVisitor extends AbstractPathVisitor  {

	public static final String TUPLE_FOR_LAZY_CALL = "TUPLE_FOR_LAZY_CALL";
	public static final String LAZY_RULE_CALL = "LAZY_RULE_CALL";
	private CSPModel2 builder;

	public LazyRulePathVisitor(CSPModel2 builder) {
		this.builder = builder;
	}
	
	HashMap<GraphNode, OclExpression> map = new HashMap<>();

	public static OclExpression genCondition(ProblemPath path, LazyRule rule, CSPModel2 builder) {
		LazyRulePathVisitor visitor = new LazyRulePathVisitor(builder);
		visitor.bottomUp(path);
		
		List<? extends ExecutionNode> exec = path.getExecutionNodes();
		if ( exec.size() == 1 ) {
			return markAsNrule(flatten(visitor.get(exec.get(0))), rule );			
		}
		
		SequenceExp seq = OCLFactory.eINSTANCE.createSequenceExp();
		for (ExecutionNode executionNode : exec) {
			seq.getElements().add(visitor.get(executionNode));
		}
		
		CollectionOperationCallExp r = builder.createCollectionOperationCall(seq, "flatten");
		
		return markAsNrule(flatten(r), rule);
	}

	private static OclExpression flatten(OclExpression exp) {
		// The original expression is flattened to make sure that all objects created by the lazy rule
		// are within a single collection (e.g., in case the lazy rule is invoked within nested collects).
		if  ( exp instanceof IteratorExp || ! ((CollectionOperationCallExp) exp).getOperationName().equals("flatten") ) {
			CollectionOperationCallExp flatten = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			flatten.setOperationName("flatten");
			flatten.setSource(exp);
			return flatten;
		}
		return exp;
	}

	public static OclExpression markAsNrule(OclExpression exp, LazyRule rule) {
		if ( exp instanceof IteratorExp || exp instanceof CollectionOperationCallExp ) {
			exp.getAnnotations().put(LAZY_RULE_CALL, rule.getName());					
			return exp;
		}
		throw new UnsupportedTranslation("Lazy rule calls with more than one parameter are only supported if enclosed in iterator expressions");
	}

	@Override
	public boolean visit(MatchedRuleAbstract node) {
//		if ( node.getRule().getInPattern().getFilter() != null ) {
//			throw new UnsupportedTranslation("Abstract rules with filter not supported yet: " + node.getRule().getLocation());
//		}
//		
//		InPatternElement firstIPE = node.getRule().getInPattern().getElements().get(0);
//		IteratorExp iterator = (IteratorExp) get(node.getDepending());
//		builder.addToScope(firstIPE, iterator.getIterators().get(0));
		
		// TODO: Create a select if there is a filter? Not actually needed...
		
		return true;
	}
	
	
	@Override
	public boolean visit(MatchedRuleExecution node) {
		builder.copyScope();

		// TODO: Consider the product, which will be similar to AllInstances cross-product
		OclExpression exp = AllInstancesNode.genSingleIterator(builder, node.getRule());
		
		InPatternElement firstIPE = node.getRule().getInPattern().getElements().get(0);
		IteratorExp iterator = builder.createIterator(exp, "collect", firstIPE.getVarName());
		builder.addToScope(firstIPE, iterator.getIterators().get(0));

		HashMap<String, VariableDeclaration> mappedVars = new HashMap<String, VariableDeclaration>();
		mappedVars.put(firstIPE.getVarName(), iterator.getIterators().get(0));
		MatchedRuleExecution.mapSuperRuleVariables(mappedVars, (MatchedRule) node.getRule().getSuperRule(), builder);
		
		// Defer setting the body after the evaluation of the depending body 
		put(node, iterator);

		return true;
	}

	@Override
	public void visitAfter(MatchedRuleExecution node) {
		DependencyNode dep = skipAbstract(node.getDepending());
		
		IteratorExp iterator = (IteratorExp) get(node);
		OclExpression body = get(dep);
		iterator.setBody(body);
		
		builder.closeScope();
	}
	

	// Abstract rules are skiped because they do not generate anything
	private DependencyNode skipAbstract(DependencyNode depending) {
		if ( depending instanceof MatchedRuleAbstract ) {
			return skipAbstract(depending.getDepending());
		}
		return depending;
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
		// (1) if the rule execution ends in a MockNode (i.e., the final node) then we just get the first argument
        //     or generate a tuple if there are more arguments
		if ( dep.getDepending() instanceof MockNode ) {
			OperationCallExp opCall = (OperationCallExp) node.getCall();
			OclExpression expr = null;
			// See CSPModel2::LazyRuleToParameter class
			if ( opCall.getArguments().size() > 1 ) {
				// A tuple is generated to gather each parameter
				TupleExp tuple = OCLFactory.eINSTANCE.createTupleExp();
				LazyRule lazy = (LazyRule) opCall.getStaticResolver();
				
				for (int i = 0; i < lazy.getInPattern().getElements().size(); i++) {
					InPatternElement e = lazy.getInPattern().getElements().get(i);
					OclExpression arg  = opCall.getArguments().get(i);

					TuplePart part = OCLFactory.eINSTANCE.createTuplePart();
					part.setVarName(e.getVarName());
					part.setInitExpression( builder.gen(arg) );
					
					tuple.getTuplePart().add(part);
				}
				
				tuple.getAnnotations().put(TUPLE_FOR_LAZY_CALL, lazy.getName());
				
				expr = tuple;
			} else {
				expr = builder.gen(opCall.getArguments().get(0));				
			}
			
			put(node, expr);
			return false; 
		} else {
			throw new UnsupportedTranslation("TODO: Inline calls using the original code CallExprNode");
		}
		
	}

	
}
