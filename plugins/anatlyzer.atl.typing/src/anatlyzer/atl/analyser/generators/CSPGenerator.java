package anatlyzer.atl.analyser.generators;

import java.util.List;

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
		List<ProblemPath> sorted = graph.getSortedPaths();
		
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
		
		if ( path.getExecutionNodes().size() == 0 ) {
			// TODO: Dead code: Signal this otherwise
			return null;
		}
		
		// Create the thisModule context at the top level
		CSPModel model = new CSPModel();

		IteratorExp ctx = model.createThisModuleContext();
		model.setThisModuleVariable(ctx.getIterators().get(0));
		
		OclExpression orOp = null;
		for (ExecutionNode node : path.getExecutionNodes()) {		
			model.resetScope();
			
			OclExpression exp = node.genCSP(model);
			if ( orOp == null ) {
				orOp = exp;
			} else {
				orOp = model.createBinaryOperator(orOp, exp, "or");
			}

			// return OclGenerator.gen(ctx); // Not passing the analyser because it is not an original expression...
			// Only one execution node supported so far
		}
		
		ctx.setBody(orOp);

		//new Retyping(ctx).perform();
		
		return ctx;
	}


}
