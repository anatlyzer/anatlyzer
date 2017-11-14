package anatlyzer.atl.analyser;

import java.util.ArrayList;
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
import anatlyzer.atl.util.IgnoredProblems;
import anatlyzer.atl.util.ProblemSets;

/**
 * This class wraps the result of an analysis.
 * 
 * @author jesus
 *
 */
public class AnalysisResult {
	protected IAnalyserResult analyser;
	protected GlobalNamespace namespace;
	
	protected ArrayList<LocalProblem> batchProblems = new ArrayList<>();
	
	public AnalysisResult(IAnalyserResult analyser) {
		this.analyser = analyser;
		this.namespace = analyser.getNamespaces();
	}
	
	public IAnalyserResult getAnalyser() {
		return analyser;
	}
	
	public GlobalNamespace getNamespace() {
		return namespace;
	}
	
	public List<Problem> getProblems() {
		return analyser.getErrors().getProblems();
	}
	
	public List<LocalProblem> getBatchProblems() {
		return Collections.unmodifiableList(batchProblems);
	}
	

	public List<Problem> getNonIgnoredProblems() {
		return IgnoredProblems.getNonIgnoredProblems(analyser);
	}	
	
	/**
	 * This method is intended to extend the analysis result with additional problems
	 * computed outside the analyser main procedure.
	 * 
	 * @param problems
	 */
	public void extendProblems(Collection<Problem> problems) {
		analyser.getErrors().addProblems(problems);
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
	
	public void configureProblems(ProblemSets problems) {
		for (LocalProblem p : new ArrayList<>(analyser.getErrors().getLocalProblems()) ) {
			if ( problems.getIgnored().contains( p.eClass() )  ) {
				analyser.getErrors().removeProblem(p);
			} else if ( problems.getBatch().contains( p.eClass() ) ) {
				batchProblems.add(p);
				analyser.getErrors().removeProblem(p);				
			}
		}
	}

	
}
