package anatlyzer.experiments.typing;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.AnalyserUtils;

public class ExpProblem  {

	private String location;
	private String description;
	private int problemId;
	private ProblemStatus status;
	private String problemTypeDescription;
	private boolean isStaticPrecision;

	public ExpProblem(Problem problem) {
		this.description = problem.getDescription();
		this.status = problem.getStatus();
		this.problemId = AnalyserUtils.getProblemId(problem);
		this.problemTypeDescription = AnalyserUtils.getProblemDescription(problem);
		this.location = "-";
		this.isStaticPrecision = AnalyserUtils.isStaticPrecision(problem);
		if ( problem instanceof LocalProblem ) 
			location = ((LocalProblem) problem).getLocation();

	
	}

	public String getLocation() {
		return this.location;
	}

	public String getDescription() {
		return description;
	}

	public ProblemStatus getStatus() {
		return status;
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
