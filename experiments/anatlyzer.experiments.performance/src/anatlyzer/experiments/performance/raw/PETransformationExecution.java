package anatlyzer.experiments.performance.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import anatlyzer.atl.util.AnalyserUtils;

@Root(name="transformationExecution")
public class PETransformationExecution {

	/* If there is an error, then total time will be null */ 
	// TODO: Indicate error...
	@Element(name="totalTime", required=false)
	private PETime totalTime;

	@Element(name="analysisTime")
	private PETime analysisTime;

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
				.filter(e -> AnalyserUtils.isConfirmed(e.getFinalStatus()) || AnalyserUtils.isDiscarded(e.getFinalStatus()))
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

}
