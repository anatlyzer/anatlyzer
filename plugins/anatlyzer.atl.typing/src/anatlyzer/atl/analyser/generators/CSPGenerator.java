package anatlyzer.atl.analyser.generators;

import java.util.List;
import java.util.function.BiFunction;

import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ExecutionNode;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ErrorUtils;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclExpression;

public class CSPGenerator {
	
	private ProblemGraph	graph;

	public CSPGenerator(ProblemGraph g) {
		this.graph = g;
	}

	public String generate() {
		return generateLoc(null);
	}
	
	/**
	 * TODO: Remove this function, because it does not even apply retyping to USE constraints
	 * 
	 * @param location
	 * @return
	 */
	public String generateLoc(String location) {
		List<ProblemPath> sorted = graph.getSortedPathsByLocation();
		
		String s = "";
		for(ProblemPath path : sorted) {
			LocalProblem lp = (LocalProblem) path.getProblem();

			if ( location != null && ! lp.getLocation().equals(location) ) 
				continue;
			
			s += ErrorUtils.getErrorMessage(lp) + " (" + lp.getLocation() +"): \n";
			s += USESerializer.gen(generateCSPCondition(path));
			s += "\n\n";
		}
		return s;
	}

	
	public static OclExpression generateCSPCondition(ProblemPath path) {		
		return genCondition(path, true, "or", (node, model) -> node.genCSP(model));
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
