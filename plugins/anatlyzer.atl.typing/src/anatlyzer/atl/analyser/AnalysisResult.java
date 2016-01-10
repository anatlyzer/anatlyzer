package anatlyzer.atl.analyser;

import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;

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
	
	public List<Problem> getConfirmedProblems() {
		return getProblems().stream().filter(p -> AnalyserUtils.isConfirmed(p)).collect(Collectors.toList());
	}

	public List<Problem> getPossibleProblems() {
		return getProblems().stream().filter(p -> 
			AnalyserUtils.isConfirmed(p) ||
			AnalyserUtils.isWitnessRequred(p) ||			
			AnalyserUtils.isInternalError(p)).collect(Collectors.toList());
	}

	public List<LocalProblem> getLocalProblems() {
		return analyser.getErrors().getLocalProblems();
	}
	
	public ATLModel getATLModel() {
		return analyser.getATLModel();
	}
}
