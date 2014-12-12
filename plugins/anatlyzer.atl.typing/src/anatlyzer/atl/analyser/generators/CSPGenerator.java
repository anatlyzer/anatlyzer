package anatlyzer.atl.analyser.generators;

import java.util.List;

import anatlyzer.atl.analyser.Analyser;
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

	public String generate(Analyser analyser) {
		return generateLoc(null, analyser);
	}
	
	public String generateLoc(String location, Analyser analyser) {
		List<ProblemPath> sorted = graph.getSortedPaths();
		
		String s = "";
		for(ProblemPath path : sorted) {
			LocalProblem lp = (LocalProblem) path.getProblem();

			if ( location != null && ! lp.getLocation().equals(location) ) 
				continue;
			
			s += ErrorUtils.getErrorMessage(lp) + " (" + lp.getLocation() +"): \n";
			s += generateCSP(path, analyser);
			s += "\n\n";
		}
		return s;
	}
	
	public String generateCSP(ProblemPath path, Analyser analyser) {
		if ( path.getExecutionNodes().size() == 0 ) {
			// TODO: Signal this otherwise
			return "Dead code";
		}
		
		// Create the thisModule context at the top level
		CSPModel model = new CSPModel(analyser);

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
		return USESerializer.gen(ctx);
		
		// return "Nothing generated";
	}
}
