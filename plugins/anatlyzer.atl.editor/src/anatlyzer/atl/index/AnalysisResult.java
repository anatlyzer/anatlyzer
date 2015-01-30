package anatlyzer.atl.index;

import java.util.List;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;

/**
 * This class wraps the result of an analysis.
 * 
 * @author jesus
 *
 */
public class AnalysisResult {
	protected Analyser analyser;
	protected GlobalNamespace namespace;
	
	public AnalysisResult(Analyser analyser, GlobalNamespace gn) {
		this.analyser = analyser;
		this.namespace = gn;
	}
	
	public Analyser getAnalyser() {
		return analyser;
	}
	
	public List<Problem> getProblems() {
		return analyser.getErrors().getProblems();
	}

	public List<LocalProblem> getLocalProblems() {
		return analyser.getErrors().getLocalProblems();
	}
}
