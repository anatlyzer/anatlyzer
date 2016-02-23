package anatlyzer.experiments.typing.raw;

import org.simpleframework.xml.Element;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.AnalyserUtils;

/**
 * A problem detected by teh analyser.
 */
public class TEProblem  {

	@Element
	private String location;
	
	@Element
	private String description;
	
	@Element
	private int problemId;
	
	@Element
	private ProblemStatus initialStatus;
	
	@Element
	private ProblemStatus finalStatus;
	
	@Element
	private String problemTypeDescription;
	
	@Element	
	private boolean isStaticPrecision;
	
	@Element
	private boolean isDependent;
	
	// @Element
	private Exception exception;

	public TEProblem(Problem problem) {
		this.description = problem.getDescription();
		this.initialStatus = problem.getStatus();
		this.problemId = AnalyserUtils.getProblemId(problem);
		this.problemTypeDescription = AnalyserUtils.getProblemDescription(problem);
		this.location = "-";
		this.isStaticPrecision = AnalyserUtils.isStaticPrecision(problem);
		if ( problem instanceof LocalProblem ) 
			location = ((LocalProblem) problem).getLocation();
	}

	public void setFinalStatus(ProblemStatus status) {
		this.finalStatus = status;
	}

	public void setFinalStatus(ProblemStatus status, boolean isDependent) {
		this.finalStatus = status;
		this.isDependent = isDependent;
	}
	
	public void setFinalStatus(ProblemStatus status, boolean isDependent, Exception e) {
		this.finalStatus = status;
		this.isDependent = isDependent;
		this.exception   = e;
	}
	
	public String getLocation() {
		return this.location;
	}

	public String getDescription() {
		return description;
	}

	public ProblemStatus getInitialStatus() {
		return initialStatus;
	}

	public ProblemStatus getFinalStatus() {
		return finalStatus;
	}

	public int getProblemId() {
		return this.problemId;
	}

	public String getProblemTypeDescription() {
		return problemTypeDescription;
	}

	public boolean isStaticPrecision() {
		return isStaticPrecision;
	}

}
