package anatlyzer.atl.analyser.generators;

import java.util.function.BiFunction;

import anatlyzer.atl.graph.ExecutionNode;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclExpression;

public class CSPGenerator {
	
	private ProblemGraph	graph;

	public CSPGenerator(ProblemGraph g) {
		this.graph = g;
	}
	
	public static OclExpression generateCSPCondition(ProblemPath path) {		
		return generateCSPCondition(path, true);
	}
	
	public static OclExpression generateCSPCondition(ProblemPath path, boolean genThisModule) {		
		return genCondition(path, genThisModule, "or", (node, model) -> node.genCSP(model, null));
	}

	public static OclExpression generateWeakestPrecondition(ProblemPath path) {
		return genCondition(path, false, "and", (node, model) -> node.genWeakestPrecondition(model));		
	}
	
	/*
	public static OclExpression generateWeakestPrecondition(ProblemPath path) {
		if ( path.getExecutionNodes().size() == 0 ) {
			// TODO: Dead code: Signal this otherwise
			return null;
		}

		CSPModel model = new CSPModel();
	
		OclExpression andOp = null;
		for (ExecutionNode node : path.getExecutionNodes()) {		
			model.resetScope();
			
			OclExpression exp = node.genWeakestPrecondition(model);
			if ( andOp == null ) {
				andOp = exp;
			} else {
				andOp = model.createBinaryOperator(andOp, exp, "and");
			}
		}
		
		return andOp;
	}
	*/
	
	protected static OclExpression genCondition(ProblemPath path, boolean genThisModuleIteration, String join, BiFunction<ExecutionNode, CSPModel, OclExpression> generator) {
		if ( path.getExecutionNodes().size() == 0 ) {
			// TODO: Dead code: Signal this otherwise
			return null;
		}
		
		// Create the thisModule context at the top level
		CSPModel model = new CSPModel();

		IteratorExp ctx = null;
		if ( genThisModuleIteration ) {
			ctx = model.createThisModuleContext();
			model.setThisModuleVariable(ctx.getIterators().get(0));
		} else {
			model.initWithoutThisModuleContext();
		}
				
		OclExpression joinOp = null;
		for (ExecutionNode node : path.getExecutionNodes()) {		
			model.resetScope();
			
			OclExpression exp = generator.apply(node, model); // node.genCSP(model);
			if ( joinOp == null ) {
				joinOp = exp;
			} else {
				joinOp = model.createBinaryOperator(joinOp, exp, join);
			}

			// return OclGenerator.gen(ctx); // Not passing the analyser because it is not an original expression...
			// Only one execution node supported so far
		}
		
		if ( genThisModuleIteration ) {
			ctx.setBody(joinOp);
			return ctx;
		} else {
			return joinOp;
		}
	}



}
