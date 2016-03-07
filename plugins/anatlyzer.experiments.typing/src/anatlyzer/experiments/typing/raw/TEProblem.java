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
 * A problem detected by the analyser.
 */
@Root(name="problem")
public class TEProblem  {

	@Element
	private String location;

	@Element
	private String fileLocation;

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

	
	public TEProblem() { }
	
	public TEProblem(Problem problem) {		
		this.problemClass = problem.eClass();
		this.description = problem.getDescription();
		this.initialStatus = problem.getStatus();
		this.location = "-";
		this.fileLocation = "-";
		if ( problem instanceof LocalProblem ) {
			fileLocation = ((LocalProblem) problem).getFileLocation();
			location = ((LocalProblem) problem).getLocation();
		}
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

	public String getFileLocation() {
		return fileLocation;
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
	
	/**
	 * TODO: Make a special case for rule conflicts
	 * @return The unique id of the problem instance made of the location and the problem class
	 */
	public String getUniqueId() {
		return description + " at " + this.location + "[" + getProblemClassName() + "]";
	}

	public TEProblem copy() {
		TEProblem p = new TEProblem();
		p.description = description;
		p.exception   = exception;
		p.finalStatus = finalStatus;
		p.initialStatus = initialStatus;
		p.isDependent = isDependent;
		p.location = location;
		p.problemClass = problemClass;
		return p;
	}

	/**
	 * Two problems are the same if they have the same location and the same problem class.
	 * @param p The other problem
	 * @return true if they are considered the same problem
	 */
	public boolean isSame(TEProblem p) {
		return p.getLocation().equals(this.location) && p.getProblemClassName().equals(getProblemClassName());
	}
	
	
}
