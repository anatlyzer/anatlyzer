package anatlyzer.experiments.typing.raw;

import org.eclipse.emf.ecore.EClass;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.AnalyserUtils;

/**
 * A problem detected by teh analyser.
 */
@Root(name="problem")
public class TEProblem  {

	@Element
	private String location;
	
	@Element
	private String description;
	
	@Element
	private ProblemStatus initialStatus;
	
	@Element
	private ProblemStatus finalStatus;
	
	@Element
	private boolean isDependent;
	
	@Element(required=false)
	private TEException exception;

	private EClass problemClass;
	
	public TEProblem() {
		
	}
	
	public TEProblem(Problem problem) {
		this.problemClass = problem.eClass();
		this.description = problem.getDescription();
		this.initialStatus = problem.getStatus();
		this.location = "-";
		if ( problem instanceof LocalProblem ) 
			location = ((LocalProblem) problem).getLocation();
	}

	/**
	 * This method is for serialization purposes only.
	 */
	@Element
	protected String getProblemClassName() {
		return problemClass.getName();
	}
	
	@Element
	protected void setProblemClassName(String className) {
		problemClass = (EClass) AtlErrorPackage.eINSTANCE.getEClassifier(className);
		if ( problemClass == null ) 
			throw new IllegalStateException("Not found " + className + " in AtlErrorPackage");
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
		this.exception   = new TEException(e, true);
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
		return AnalyserUtils.getProblemId(problemClass);
	}

	public String getProblemTypeDescription() {
		return AnalyserUtils.getProblemDescription(problemClass);
	}

	public boolean isStaticPrecision() {
		return AnalyserUtils.isStaticPrecision(problemClass);
	}
	
	public boolean isFinallyConfirmed() {
		return AnalyserUtils.isConfirmed(finalStatus);
	}

	public boolean isDependent() {
		return isDependent;
	}

	public String getSeverity() {
		return AnalyserUtils.getProblemSeverity(problemClass);
	}
	
	public String getKind() {
		return AnalyserUtils.getProblemKind(problemClass);
	}
}
