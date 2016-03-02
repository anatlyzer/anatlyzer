package anatlyzer.experiments.performance.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import anatlyzer.atl.errors.Problem;
import anatlyzer.experiments.typing.raw.TEProblem;

@Root(name="problemExecution")
public class PEProblemExecution extends TEProblem {

	@Element	
	private PETime createPathTime;
	
	@Element
	private PETime conditionGenerationTime;
	
	@ElementList
	private List<PETime> solverTimes;
	
	@Element
	private PETime effectiveMetamodelTime;

	@Element
	private PETime errorMetamodelTime;

	public PEProblemExecution() {
		this.solverTimes = new ArrayList<PETime>();
	}

	public PEProblemExecution(Problem problem) {
		super(problem);
		this.solverTimes = new ArrayList<PETime>();
	}

	public void setCreatePathTime(PETime time) {
		this.createPathTime = time;
	}

	public PETime getCreatePathTime() {
		return createPathTime;
	}
	
	public void setConditionGenerationTime(PETime time) {
		this.conditionGenerationTime = time;
	}
	
	public PETime getConditionGenerationTime() {
		return conditionGenerationTime;
	}

	public void addSolverTimes(List<PETime> times) {
		this.solverTimes.addAll(times);
	}

	public void addSolverTime(PETime time) {
		this.solverTimes.add(time);		
	}
	
	public PETime getTotalSolverTime() {
		Long total = solverTimes.stream().collect(Collectors.summingLong(t -> t.getTime()));
		return new PETime(total);
	}
	
	public void setEffectiveMetamodelTime(PETime time) {
		this.effectiveMetamodelTime = time;
	}

	public PETime getEffectiveMetamodelTime() {
		return effectiveMetamodelTime;
	}
	
	public void setErrorMetamodelTime(PETime time) {
		this.errorMetamodelTime = time;
	}

	public PETime getErrorMetamodelTime() {
		return errorMetamodelTime;
	}

}
