package anatlyzer.evaluation.raw;

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
public class MUProblem  {

	@Element
	protected String location;

	@Element(required = false)
	protected String fileLocation = "-";

	@Element
	protected String description;
	
	@Element
	protected ProblemStatus status;
	
	@Element(required=false)
	protected MUException exception;

	protected EClass problemClass;

	
	public MUProblem() { }
	
	public MUProblem(Problem problem) {		
		this.problemClass = problem.eClass();
		this.description = problem.getDescription();
		this.status = problem.getStatus();
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
	
	public String getLocation() {
		return this.location;
	}

	public String getFileLocation() {
		return fileLocation;
	}
	
	public String getDescription() {
		return description;
	}

	public ProblemStatus getStatus() {
		return status;
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
		
	public String getSeverity() {
		return AnalyserUtils.getProblemSeverity(problemClass);
	}
	
	public String getKind() {
		return AnalyserUtils.getProblemKind(problemClass);
	}
	
	public MUException getException() {
		return this.exception;
	}
	
	
	/**
	 * TODO: Make a special case for rule conflicts
	 * @return The unique id of the problem instance made of the location and the problem class
	 */
	public String getUniqueId() {
		return description + " at " + this.location + "[" + getProblemClassName() + "]";
	}

	/**
	 * Two problems are the same if they have the same location and the same problem class.
	 * @param p The other problem
	 * @return true if they are considered the same problem
	 */
	public boolean isSame(MUProblem p) {
		return p.getLocation().equals(this.location) && p.getProblemClassName().equals(getProblemClassName());
	}
	
}
