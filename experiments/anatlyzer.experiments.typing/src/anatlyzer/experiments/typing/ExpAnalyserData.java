package anatlyzer.experiments.typing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.util.ErrorReport;
import anatlyzer.atl.util.ErrorReport.Report;

public class ExpAnalyserData {

	private String mainFileLocation;
	private List<String> fileLocations;
	private List<ExpProblem> problems;
	private Report statistics;
	
	public ExpAnalyserData(AnalyserData data) {
		this.mainFileLocation = data.getATLModel().getMainFileLocation();
		this.fileLocations    = new ArrayList<String>(data.getATLModel().getFileLocations());
		this.problems = data.getProblems().stream().map(p -> new ExpProblem(p)).collect(Collectors.toList());
		
		String[] absFileLocations = new String[this.fileLocations.size()];
		int i = 0;
		for (String eclipseLocation : this.fileLocations) {
			absFileLocations[i] = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(eclipseLocation)).getRawLocation().toPortableString();			
			i++;
		}
		
		this.statistics = ErrorReport.computeStatistics(data.getAnalyser(), absFileLocations);
	}
	
	public String getMainFileLocation() {
		return this.mainFileLocation;
	}

	public List<ExpProblem> getProblems() {
		return problems;
	}

	public List<String> getFileLocations() {
		return fileLocations;
	}
	
	public Report getStatistics() {
		return statistics;
	}
}
