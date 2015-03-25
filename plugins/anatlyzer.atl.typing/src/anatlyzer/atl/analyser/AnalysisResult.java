package anatlyzer.atl.analyser;

import java.util.List;

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
	
	public AnalysisResult(Analyser analyser) {
		this.analyser = analyser;
		this.namespace = analyser.getNamespaces();
	}
	
	public Analyser getAnalyser() {
		return analyser;
	}
	
	public GlobalNamespace getNamespace() {
		return namespace;
	}
	
	public List<Problem> getProblems() {
		return analyser.getErrors().getProblems();
	}

	public List<LocalProblem> getLocalProblems() {
		return analyser.getErrors().getLocalProblems();
	}
}
