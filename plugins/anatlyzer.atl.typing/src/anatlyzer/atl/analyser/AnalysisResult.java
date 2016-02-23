package anatlyzer.atl.analyser;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.model.ATLModel;
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
	
	/**
	 * This method is intended to extend the analysis result with additional problems
	 * computed outside the analyser main procedure.
	 * 
	 * @param problems
	 */
	public void extendProblems(Collection<Problem> problems) {
		analyser.getErrors().getProblems().addAll(problems);
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

	public void extendWithRuleConflicts(RuleConflicts rc, boolean removeExisting) {
		if ( removeExisting ) {
			analyser.getErrors().removeRuleConflicts();
		}
		
		if ( rc != null ) {
			analyser.getErrors().addRuleConflicts(rc);
		}				
	}
}
