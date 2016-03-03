package anatlyzer.experiments.performance.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import anatlyzer.atl.util.Pair;


/**
 * A transformation shows performance analysis is evaluated.
 * 
 * @author jesus
 */
@Root(name="transformation")
public class PETransformation {

	@Attribute
	protected String name;
	
	@Attribute
	protected String path;
	
	@ElementList(name="executions")
	protected List<PETransformationExecution> executions; 
	
	public PETransformation() {
		executions = new ArrayList<PETransformationExecution>();
	}

	public PETransformation(String name, String path) {
		this();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}
	
	public void addExecution(PETransformationExecution exec) {
		executions.add(exec);
	}

	public List<PETransformationExecution> getExecutions() {
		return executions;
	}
	
	/**
	 * Computes the average of all executions discarding the first n executions.
	 * @return
	 */
	public PETransformationExecution getAvg(int discard) {
		if ( executions.size() + 1 < discard ) 
			throw new IllegalStateException("Too few executions to discard " + discard + " executions");
				
		PETransformationExecution avg = new PETransformationExecution();
		
		List<PETransformationExecution> rest = executions.subList(discard, executions.size());
		long totalTime = 0;
		long analysisTime = 0;
		long pathCreationTime = 0;
		long treeCreationTime = 0;
		long parseTime = 0;
		long metamodelTime = 0;
		long createATLModelTime = 0;
		long rawModelFindingTime = 0;
		
		for (PETransformationExecution te : rest) {
			totalTime    += te.getTotalTime().getTime();
			analysisTime += te.getAnalysisTime().getTime();
			parseTime    += te.getParserTime().getTime();
			metamodelTime += te.getMetamodelLoadTime().getTime();
			pathCreationTime += te.getPathGenerationTime().getTime();
			treeCreationTime += te.getProblemTreeCreationTime().getTime();
			createATLModelTime += te.getCreateATLModelTime().getTime();
			rawModelFindingTime += te.getRawModelFindingTime().getTime();
			
			Map<String, List<PEProblemExecution>> problemExecs = te.getValidProblemExecutions().stream().collect(Collectors.groupingBy(e -> e.getUniqueId()));
			checkAllSameExecutions(problemExecs);
			
			problemExecs.forEach((str, execList) -> {				
				PEProblemExecution avgProblem = getAvg(execList);
				avg.addProblemExecution(avgProblem);				
			});
			
		}
		
		avg.setTotalTime(new PETime(totalTime, rest.size()));
		avg.setAnalysisTime(new PETime(analysisTime, rest.size()));
		avg.setParserTime(new PETime(parseTime, rest.size()));
		avg.setMetamodelLoadTime(new PETime(metamodelTime, rest.size()));
		avg.setProblemTreeCreationTime(new PETime(treeCreationTime, rest.size()));
		avg.setPathGenerationTime(new PETime(pathCreationTime, rest.size()));
		avg.setCreateATLModelTime(new PETime(createATLModelTime, rest.size()));
		avg.setRawModelFindingTime(new PETime(rawModelFindingTime, rest.size()));
		
		return avg;
	}

	/**
	 * This is a sanity check to ensure that all problem executions are correctly paired,
	 * so that all problems have the same number of executions
	 * @param te 
	 * @param problemExecs
	 */
	private void checkAllSameExecutions(Map<String, List<PEProblemExecution>> problemExecs) {
		int number = -1;
		for (List<PEProblemExecution> exec : problemExecs.values()) {
			number = number == -1 ? exec.size() : number;
			if ( number != exec.size() ) {
				String location = exec.get(0).getLocation();				
				throw new IllegalStateException("Expected " + number + " problems but found " + exec.size() + ". " + name + " at " + location);
			}
		}
	}

	private PEProblemExecution getAvg(List<PEProblemExecution> execList) {
		PEProblemExecution avg = new PEProblemExecution();
		
		double pathTime = execList.stream().map(e -> e.getCreatePathTime()).collect(Collectors.averagingLong(t -> t.getTime()));
		double condTime = execList.stream().map(e -> e.getConditionGenerationTime()).collect(Collectors.averagingLong(t -> t.getTime()));
		double effTime = execList.stream().map(e -> e.getEffectiveMetamodelTime()).collect(Collectors.averagingLong(t -> t.getTime()));
		double errTime = execList.stream().map(e -> e.getErrorMetamodelTime()).collect(Collectors.averagingLong(t -> t.getTime()));
		double solverTime = execList.stream().map(e -> e.getTotalSolverTime()).collect(Collectors.averagingLong(t -> t.getTime()));

		
		avg.setCreatePathTime(new PETime(pathTime));
		avg.setConditionGenerationTime(new PETime(condTime));
		avg.setEffectiveMetamodelTime(new PETime(effTime));
		avg.setErrorMetamodelTime(new PETime(errTime));
		avg.addSolverTime(new PETime(solverTime));

		return avg;
	}
	
}
