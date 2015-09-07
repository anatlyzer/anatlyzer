package anatlyzer.experiments.typing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
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
	protected List<ExpAnalyserData> allData = new ArrayList<ExpAnalyserData>();
	protected CountingModel<DetectedError> counting = new CountingModel<DetectedError>();
	protected List<DetectedError> allProblems = new ArrayList<DetectedError>();

	
	protected HashMap<Integer, ErrorCount> errorOcurrences = new HashMap<Integer, ErrorCount>();

	protected CountingModel<DetectedError> byKind = new CountingModel<DetectedError>();
	protected CountingModel<DetectedError> bySeverity = new CountingModel<DetectedError>();
	
	
	
	public CountTypeErrors() {
		for(CountingModel<?> c : new CountingModel<?>[] { counting, byKind, bySeverity }) {
			c.setRepetitions(true);
			c.showRepetitionDetails(false);
			c.showCategoryDescriptions(true);
		}
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
		
		AnalyserData original;
		try {
			original = executeAnalyser(resource);
			if ( original == null )
				return;
			
			if ( performRuleConflictAnalysis() ) {
				RuleConflict rc = doRuleAnalysis(monitor, original, true);
				project.conflicts.add(new RuleConflictResult((IFile) resource, rc));
			}
					
			if ( useCSP() ) {
				confirmProblems(original.getProblems(), original);
			}
						
			ExpAnalyserData data = copyData(original);
			project.trafos.add(data);
			allData.add(data);
			
			String fileName = resource.getName();
			counting.processingArtefact(fileName);
			
			ProblemGraph pGraph = AnalyserUtils.computeProblemGraph(original);
			for(Problem p : original.getProblems()) {
				int errorCode = AnalyserUtils.getProblemId(p);
				String desc   = AnalyserUtils.getProblemDescription(p);

				ProblemPath path = null;
				if ( ! AnalyserUtils.isDependent(p, pGraph) ) {
					path = pGraph.getPath(p);
				}
				
				summarizeError(p, errorCode, desc, path);				
				
				DetectedError e = new DetectedError(errorCode, fileName, new ExpProblem(p));
				e.setProblemsInPath(path == null);
				
				allProblems.add(e);
				
				if ( countPotential() || AnalyserUtils.isConfirmed(p) ) {
					counting.addByCategory(new ErrorCategory(errorCode, desc), e);
					
					String severity = AnalyserUtils.getProblemSeverity(p);
					String kind     = AnalyserUtils.getProblemKind(p);
					
					bySeverity.addByCategory(new anatlyzer.experiments.export.Category(severity), e);
					byKind.addByCategory(new anatlyzer.experiments.export.Category(kind), e);
				}				
			}
			
		} catch (Exception e) {
			counting.addError(resource.getName(), e);
			e.printStackTrace();
		} 
	}

	protected void summarizeError(Problem p, int errorCode, String desc, ProblemPath path) {
		boolean hasProblemsInPath = path == null;

		errorOcurrences.putIfAbsent(errorCode, new ErrorCount(errorCode, desc));
		ErrorCount c = errorOcurrences.get(errorCode);
		c.ocurrences++;
		
		if ( hasProblemsInPath && p.getStatus() != ProblemStatus.STATICALLY_CONFIRMED )
			c.problemsInPath++;		
		
		switch ( p.getStatus() ) {
		case STATICALLY_CONFIRMED:
			c.staticallyConfirmed++;
			break;
		case ERROR_CONFIRMED:
		case ERROR_CONFIRMED_SPECULATIVE:
			c.witnessConfirmed++;
			if ( hasProblemsInPath ) c.problemsInPathRecovered++;		
			break;
		case ERROR_DISCARDED:
			if ( hasProblemsInPath ) c.problemsInPathRecovered++;		
			c.witnessDiscarded++;
			break;
		case ERROR_DISCARDED_DUE_TO_METAMODEL:
			if ( hasProblemsInPath ) c.problemsInPathRecovered++;
			c.witnessDiscardedMetamodel++;
			break;
		case USE_INTERNAL_ERROR:
			c.e1_use++;
			break;
		case IMPL_INTERNAL_ERROR:
			c.e2_impl++;
			break;
		case NOT_SUPPORTED_BY_USE:
			c.e3_unsupp++;
			break;
		case WITNESS_REQUIRED:
			if ( useCSP() ) 
				throw new IllegalStateException();
			break;
		case PROBLEMS_IN_PATH:
		case CANNOT_DETERMINE:
			throw new IllegalStateException();
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
		for(ExpAnalyserData data : allData) {
			out.println(data.getMainFileLocation());
			
			// Analyser analyser = data.getAnalyser();
			
			ByteArrayOutputStream outS = new ByteArrayOutputStream();
			String[] fileLocations = new String[data.getFileLocations().size()];
			int i = 0;
			for (String eclipseLocation : data.getFileLocations()) {
				fileLocations[i] = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(eclipseLocation)).getRawLocation().toPortableString();			
				i++;
			}
			ErrorReport.printStatistics(data.getStatistics(), outS);
			// ErrorReport.printErrorsByType(data.getAnalyser(), outS);
			
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
		counting.toExcel(file.replace(".xlsx", "_byError.xlsx"));
		byKind.toExcel(file.replace(".xlsx", "_byKind.xlsx"));
		bySeverity.toExcel(file.replace(".xlsx", "_bySeverity.xlsx"));

		counting.toLatex(file.replace(".xlsx", "byError.tex"));
		byKind.toLatex(file.replace(".xlsx", "_byKind.tex"));
		bySeverity.toLatex(file.replace(".xlsx", "_bySeverity.tex"));
		
	}

	public class DetectedError implements IClassifiedArtefact {
		private int errorCode;
		private String fileName;
		private ExpProblem problem;
		private boolean problemsInPath;
		
		public DetectedError(int errorCode, String fileName, ExpProblem p) {
			this.errorCode = errorCode;
			this.fileName  = fileName;
			this.problem   = p;
		}
		
		public void setProblemsInPath(boolean b) {
			this.problemsInPath = b;
		}

		public boolean hasProblemsInPath() {
			return this.problemsInPath;
		}
		
		public ExpProblem getProblem() {
			return problem;
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
			hints.add(new SimpleHint(problem.getLocation()));
			hints.add(new SimpleHint(problem.getDescription()));			
			if ( AnalyserUtils.isErrorStatus(problem.getStatus()) ) {
				hints.add(new SimpleHint(problemsInPath ? "Prob. path" : ""));
				hints.add(new SimpleHint(problem.getStatus().getName()));
			}
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
		private List<ExpAnalyserData> trafos = new ArrayList<ExpAnalyserData>();
		private List<RuleConflictResult> conflicts = new ArrayList<RuleConflictResult>();
		private String name;

		public Project(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public List<ExpAnalyserData> getTrafos() {
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
	

	public class ErrorCount {
		int id;
		String desc;
		public ErrorCount(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}
		
		int problemsInPath;
		int problemsInPathRecovered;
		
		int ocurrences;
		int staticallyConfirmed;
		int witnessConfirmed;
		int witnessDiscarded;
		int witnessDiscardedMetamodel;
		int e1_use;
		int e2_impl;
		int e3_unsupp;
	}
}
