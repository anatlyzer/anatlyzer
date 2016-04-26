package anatlyzer.experiments.performance.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;

@Root(name="transformationExecution")
public class PETransformationExecution {

	/* If there is an error, then total time will be null */ 
	// TODO: Indicate error...
	@Element(name="totalTime", required=false)
	private PETime totalTime;

	@Element(name="analysisTime")
	private PETime analysisTime;

	@Element(name="parserTime")
	private PETime parserTime;
	
	@Element(name="createATLModelTime")
	private PETime createATLModelTime;

	@Element(name="metamodelLoadTime")
	private PETime metamodelLoadTime;

	@Element(name="problemTreeCreationTime")
	private PETime problemTreeCreationTime;

	@Element(name="pathGenerationTime")
	private PETime pathGenerationTime;

	@Element(name="rawModelFindingTime")
	private PETime rawModelFindingTime;
	
	@ElementList(name="problemExecutions")
	private ArrayList<PEProblemExecution> problemExecutions;


	public PETransformationExecution() {
		this.problemExecutions = new ArrayList<PEProblemExecution>();
	}

	public List<PEProblemExecution> getProblemExecutions() {
		return problemExecutions;
	}


	public List<PEProblemExecution> getValidProblemExecutions() {
		return problemExecutions.stream()
				.filter(e -> 
						AnalyserUtils.isConfirmed(e.getFinalStatus()) || 
						AnalyserUtils.isDiscarded(e.getFinalStatus()) ||
						// Also consider the timeouts
						e.getFinalStatus() == ProblemStatus.USE_TIME_OUT)
				.collect(Collectors.toList());
	}
	
	public void setAnalysisTime(PETime time) {
		this.analysisTime = time;
	}

	public void setTotalTime(PETime time) {
		this.totalTime = time;
	}

	public void addProblemExecution(PEProblemExecution exec) {
		this.problemExecutions.add(exec);
	}
	
	public PETime getTotalTime() {
		return totalTime;
	}
	
	public PETime getAnalysisTime() {
		return analysisTime;
	}

	public void setProblemTreeCreationTime(PETime time) {
		this.problemTreeCreationTime = time;
	}
	
	public PETime getProblemTreeCreationTime() {
		return problemTreeCreationTime;
	}

	public void setPathGenerationTime(PETime peTime) {
		this.pathGenerationTime = peTime;
	}
	
	public PETime getPathGenerationTime() {
		return pathGenerationTime;
	}

	public void setParserTime(PETime t) {
		this.parserTime = t;
	}

	public void setMetamodelLoadTime(PETime t) {
		this.metamodelLoadTime = t;
	}

	public PETime getParserTime() {
		return parserTime;
	}
	
	public PETime getMetamodelLoadTime() {
		return metamodelLoadTime;
	}

	public void setCreateATLModelTime(PETime t) {
		this.createATLModelTime = t;
	}
	
	public PETime getCreateATLModelTime() {
		return createATLModelTime;
	}

	public void setRawModelFindingTime(PETime peTime) {
		this.rawModelFindingTime = peTime;
	}
	
	public PETime getRawModelFindingTime() {
		return rawModelFindingTime;
	}

}
