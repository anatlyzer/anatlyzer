package anatlyzer.examples.api;

import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.tests.api.AnalysisLoader;
import anatlyzer.atl.tests.api.AtlLoader;
import anatlyzer.atl.tests.api.StandaloneUSEWitnessFinder;

/**
 * This example shows how to launch an analysis and inspect the results. 
 * 
 * @author jesus
 */
public class Workflow2PN_LaunchAnalysis {
	public static final String TRANSFORMATION = "examples/workflow2pn/trafo/workflow2pn.atl";

	public static final String WF_METAMODEL  = "examples/workflow2pn/metamodels/workflow.ecore";
	public static final String PN_METAMODEL   = "examples/workflow2pn/metamodels/petri_nets.ecore";
	
	public static void main(String[] args) throws Exception {
		new Workflow2PN_LaunchAnalysis().run();
	}

	private void run() throws Exception {
		Resource atlTrafo = AtlLoader.load(TRANSFORMATION);
		
		// The analyser is configured with the paths to the meta-models (WF_METAMODEL and PN_METAMODEL)
		// and the logical names (as two arrays)
		AnalysisLoader loader = AnalysisLoader.fromResource(atlTrafo, 
				new Object[] { WF_METAMODEL, PN_METAMODEL },  
				new String[] { "WF", "PN" });

		// The analysis result is wrapped into AnalysisResult
		AnalysisResult result = loader.analyse();

		// Check which problems needs to be confirmed by the constraint solver
		for (Problem problem : result.getProblems()) {
			if ( problem.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {
				// Launchs the constraint solver and (hopefully) confirms or discard
				// the proble, updating the problem status
				ProblemStatus status = StandaloneUSEWitnessFinder.confirmOrDiscard(problem, result);
				System.out.println(status);
			}
		}
		
		System.out.println("Confirmed problems (either statically or by the constraint solver):");
		for (Problem p : result.getConfirmedProblems()) {
			String location = (p instanceof LocalProblem) ? ((LocalProblem) p).getLocation() : "-";
			System.out.println(location + ": " + p.getDescription());
		}
		
		
	}

	
	
}
