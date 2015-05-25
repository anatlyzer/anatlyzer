package anatlyzer.experiments.typing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import transML.utils.transMLProperties;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.witness.EclipseUseWitnessFinder;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ErrorReport;
import anatlyzer.experiments.export.Category;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.IClassifiedArtefact;
import anatlyzer.experiments.export.IHint;
import anatlyzer.experiments.export.SimpleHint;
import anatlyzer.experiments.extensions.IExperiment;

public class CountTypeErrors extends AbstractATLExperiment implements IExperiment {


	protected HashMap<String, Project> projects = new HashMap<String, Project>();
	protected List<AnalyserData> allData = new ArrayList<AnalyserData>();
	protected CountingModel<DetectedError> counting = new CountingModel<DetectedError>();

	
	public CountTypeErrors() {
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);
		counting.showCategoryDescriptions(true);
	}
	
	@Override
	public void perform(IResource resource) {
		perform(resource, null);
	}

	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		String projectName = resource.getProject().getName();
		if ( ! projects.containsKey(projectName) ) {
			projects.put(projectName, new Project(projectName));
		}
		Project project = projects.get(projectName);
		
		AnalyserData data;
		try {
			data = executeAnalyser(resource);
			if ( data == null )
				return;

			if ( performRuleConflictAnalysis() ) {
				RuleConflict rc = doRuleAnalysis(monitor, data, true);
				project.conflicts.add(new RuleConflictResult((IFile) resource, rc));
			}
			
			project.trafos.add(data);
			allData.add(data);
		
			if ( useCSP() ) {
				confirmProblems(data.getProblems(), data);
			}
			
			String fileName = resource.getName();
			counting.processingArtefact(fileName);
			
			for(Problem p : data.getProblems()) {
				if ( ! countPotential() && ! AnalyserUtils.isConfirmed(p) ) {
					continue;
				}
				
				int errorCode = AnalyserUtils.getProblemId(p);
				DetectedError e = new DetectedError(errorCode, fileName, p);
				counting.addByCategory(new ErrorCategory(errorCode, AnalyserUtils.getProblemDescription(p)), e);
			}
			
		} catch (Exception e) {
			counting.addError(resource.getName(), e);
			e.printStackTrace();
		} 
	}

	private void confirmProblems(List<Problem> problems, AnalysisResult r) {
		for (Problem p : problems) {
			if ( p.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {				
				removeTempFile();
				
				ProblemStatus result = null;
				try {
					result = new EclipseUseWitnessFinder().			
							checkDiscardCause(false).
							find(p, r);
				} catch ( Exception e ) {
					result = ProblemStatus.IMPL_INTERNAL_ERROR;
				}
				
				p.setStatus(result);
			}
		}
	}

	private void removeTempFile() {
		try {
			transMLProperties.loadPropertiesFile(new EclipseUseWitnessFinder().getTempDirectory());					
			File dir = new File(transMLProperties.getProperty("temp"));
			FileUtils.deleteDirectory(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private boolean useCSP() {
		return this.options.containsKey("use_model_finder") &&
			(Boolean.parseBoolean((String) this.options.get("use_model_finder")));
	}
	
	private boolean countPotential() {
		return this.options.containsKey("count_potential") &&
				(Boolean.parseBoolean((String) this.options.get("count_potential")));
	}

	private boolean performRuleConflictAnalysis() {
		return this.options.containsKey("conflict_analysis") &&
				(Boolean.parseBoolean((String) this.options.get("conflict_analysis")));
	}


	@Override
	public void printResult(PrintStream out) {
		
		out.println("Transformations - Detail");
		for(AnalyserData data : allData) {
			out.println(data.getAnalyser().getATLModel().getFileLocations().get(0));
			
			Analyser analyser = data.getAnalyser();
			
			ByteArrayOutputStream outS = new ByteArrayOutputStream();
			String[] fileLocations = new String[analyser.getATLModel().getFileLocations().size()];
			int i = 0;
			for (String eclipseLocation : analyser.getATLModel().getFileLocations()) {
				fileLocations[i] = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(eclipseLocation)).getRawLocation().toPortableString();			
				i++;
			}
			ErrorReport.printStatistics(data.getAnalyser(), fileLocations, outS);
			ErrorReport.printErrorsByType(data.getAnalyser(), outS);
			
			out.print(outS.toString());
			
			// out.println(data.getProblems())
		}
		
		out.println();
		out.println("------------------------------------");
		out.println();
		counting.printResult(out);
	}

	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String file) throws IOException {
		counting.toExcel(file);
	}

	public class DetectedError implements IClassifiedArtefact {
		private int errorCode;
		private String fileName;
		private Problem problem;
		
		public DetectedError(int errorCode, String fileName, Problem p) {
			this.errorCode = errorCode;
			this.fileName  = fileName;
			this.problem   = p;
		}
		
		//
		// IArtefact methods
		//
		
		@Override
		public String getId() {
			return getName();
		}

		@Override
		public String getName() {
			return fileName;
		}

		@Override
		public List<IHint> getHints() {
			ArrayList<IHint> hints = new ArrayList<IHint>();
			String location = "-";
			if ( problem instanceof LocalProblem ) 
				location = ((LocalProblem) problem).getLocation();
			hints.add(new SimpleHint(location));
			hints.add(new SimpleHint(problem.getDescription()));			
			return hints;
		}
	}
	
	public class ErrorCategory extends anatlyzer.experiments.export.Category {

		private int errorCode;
		private String description;

		public ErrorCategory(int errorCode, String description) {
			super(errorCode + "");
			this.errorCode = errorCode;
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
		
		@Override
		public int compareTo(Category o) {
			return Integer.compare(errorCode, ((ErrorCategory) o).errorCode);
		}
		
	}

	class Project {
		private List<AnalyserData> trafos = new ArrayList<AnalyserData>();
		private List<RuleConflictResult> conflicts = new ArrayList<RuleConflictResult>();
		private String name;

		public Project(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public List<AnalyserData> getTrafos() {
			return trafos;
		}
		
		public List<RuleConflictResult> getConflicts() {
			return conflicts;
		}
	}
	
	class RuleConflictResult {
		public final IFile transformation;
		public final RuleConflict conflict;
		 
		public RuleConflictResult(IFile transformation, RuleConflict conflict) {
			this.transformation = transformation;
			this.conflict       = conflict;
		}
	}
	
}
