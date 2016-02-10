package anatlyzer.experiments.typing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfixSet;
import anatlyzer.atl.editor.quickfix.ConstraintSolvingQuickFix;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.editor.quickfix.SpeculativeQuickfixUtils;
import anatlyzer.atl.editor.quickfix.TransformationSliceQuickFix;
import anatlyzer.atl.editor.quickfix.errors.FeatureFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.witness.EclipseUseWitnessFinder;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.impact.ImpactComputation;
import anatlyzer.atl.model.ATLModel.ITracedATLModel;
import anatlyzer.atl.problemtracking.ProblemTracker;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.IClassifiedArtefact;
import anatlyzer.experiments.export.IHint;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.CountTypeErrors.DetectedError;

public class QuickfixEvaluationAbstract extends AbstractATLExperiment implements IExperiment {

	protected static final String ORIGINAL_RESOURCE = "ORIGINAL_RESOURCE";
	private static final Object MIN_QUICKFIX = "minqfx";
	private static final Object MAX_QUICKFIX = "maxqfx";
	
 	List<AnalyserData> allData = new ArrayList<AnalyserData>();
	protected CountingModel<DetectedError> counting = new CountingModel<DetectedError>();

	protected boolean recordAll = false;
	protected boolean useCSP    = true;
	protected Workbook workbook = new XSSFWorkbook();
	protected boolean compactNotClassified = false;
	protected boolean performRuleAnalysis = false;
	protected boolean deleteUSETempFolder = true;
	protected boolean optimizeWithProblemTracking = false;

	
	protected ArrayList<String> resourcesWithInvalidQuickfixes = new ArrayList<String>();
	
	public static class QuickfixSummary {
		int id;
		int maxQuickfixes = 0;
		int minQuickfixes = Integer.MAX_VALUE;
		int totalQuickfixes;
		int totalValidQuickfixes;
		int totalProblems;
		int totalErrorsFixed;
		int totalErrorsGenerated;
		int totalErrorsMayBeFixed;
		int totalErrorsMayBeGenerated;
		
		String description;
		String errorCode;
		HashMap<String, List<AppliedQuickfixInfo>> quickfixesByType = new HashMap<String, List<AppliedQuickfixInfo>>();
		
		public QuickfixSummary(int problemId, String description, String errorCode) {
			this.id = problemId;
			this.description = description;
			this.errorCode = errorCode;
		}
		
		public void appliedQuickfixes(int count, int validQuickfixes, int errorsFixed, int errorsGenerated, int errorsMayBeFixed, int errorsMayBeGenerated) {
			if ( count < minQuickfixes )  {
				minQuickfixes = count;
			}
			if ( count > maxQuickfixes ) {
				maxQuickfixes = count;
			}
			totalValidQuickfixes += validQuickfixes;
			totalErrorsFixed += errorsFixed;
			totalErrorsGenerated += errorsGenerated;
			totalErrorsMayBeFixed += errorsMayBeFixed;
			totalErrorsMayBeGenerated += errorsMayBeGenerated;
			
			totalQuickfixes += count;
			totalProblems++;
		}


		protected double getAvg() {
			return totalQuickfixes / (1.0 * totalProblems);
		}

		public static String toLatexHeader() {
			return "{\\bf Prob.}        & {\\bf \\#Occ.} & {\\bf \\#Qfx} & {\\bf Avg} & {\\bf Min} & {\\bf Max} & {\\bf Valid} & {\\bf Fix.} & {\\bf Gen.} \\\\ \\hline";	
		}

		public String toLatexRow() {
			String first = "{\\bf " + getLatexDesc() + "} & " + totalProblems + " & " + totalQuickfixes + " & " + formatDouble(getAvg()) + " & " + minQuickfixes + " & " + maxQuickfixes + " & " + totalValidQuickfixes + " & " + totalErrorsFixed + " & " + totalErrorsGenerated+ "\\\\ \\hline" ;
			List<String> lines = new ArrayList<String>();
			lines.add(first);
			quickfixesByType.keySet().stream().sorted((k1, k2) -> k1.compareTo(k2)).forEach(k -> {
				List<AppliedQuickfixInfo> list = quickfixesByType.get(k);
				
				int totalQuickfix = list.size();
//				int generated = list.stream().filter(qi -> qi.getNumOfFixes() < 0 ).mapToInt(qi -> -1 * qi.getNumOfFixes()).sum();
//				int fixed     = list.stream().filter(qi -> qi.getNumOfFixes() >= 0 ).mapToInt(qi -> qi.getNumOfFixes()).sum();

				int valid     = list.stream().mapToInt(qi -> qi.isValid() ? 1 : 0).sum();
				int fixed     = list.stream().mapToInt(qi -> qi.getNumFixedProblems()).sum();
				int generated = list.stream().mapToInt(qi -> qi.getNumNewProblems()).sum();

				String line = "~ {\\bf " + k + "} & " + "-" + " & " + totalQuickfix + " & " + "-" + " & " + "-" + " & " + "-" + " & " + valid + " & " + fixed + " & " + generated + "\\\\ \\hline" ;
				lines.add(line);
			});
			
			return lines.stream().collect(Collectors.joining("\n"));
		}
		
		private String formatDouble(double d) {
			return String.format(Locale.US, "%.1f", d);
		}

		protected String getLatexDesc() {
			String desc = errorCode;
			if ( desc == null ) {
				desc = "X-" + description; // .substring(0, 10) + "...";
			}
			return desc;
		}

		@Override
		public String toString() {
			return id + ": \n" + 
					"\t" + "min: " + minQuickfixes + "\n" +
					"\t" + "max: " + maxQuickfixes + "\n" +					
					"\t" + "avg:" + totalQuickfixes / (1.0 * totalProblems) + "\n" +
					"\t" + "pro:" + totalProblems + "\n" +
					"\t" + "qfx:" + totalQuickfixes + "\n";
		}

		public void appliedQuickfix(AppliedQuickfixInfo qi) {
			String code = qi.getCode();
			if ( ! quickfixesByType.containsKey(code) ) {
				quickfixesByType.put(code, new ArrayList<AppliedQuickfixInfo>());
			}
			
			quickfixesByType.get(code).add(qi);
		}


	}
	
	protected HashMap<String, QuickfixSummary> summary = new HashMap<String, QuickfixEvaluationAbstract.QuickfixSummary>();
	
	
	public class AppliedQuickfixInfo implements IClassifiedArtefact {

		protected AtlProblemQuickfix quickfix;
		protected AnalysisResult original;
		protected AnalysisResult newResult;
		protected boolean notSupported;
		protected boolean implError;
		public String description = "no-description";
		protected ImpactComputation impact;
		private Problem originalProblem;

		public AppliedQuickfixInfo(AtlProblemQuickfix quickfix, Problem originalProblem, AnalysisResult original, List<Problem> originalProblems) {
			this.quickfix = quickfix;
			this.original = original;
			this.originalProblems = new ArrayList<Problem>(originalProblems);	
			this.originalProblem  = originalProblem;
			setDescription(getCode() + " - " + quickfix.getDisplayString());
		}

		public boolean isValid() {
			return impact != null ? impact.isFixed(originalProblem) : false;
		}

		public String getCode() {
			return QuickfixCodes.getCode(this.quickfix);
		}		

		public void setRetyped(AnalysisResult newResult, List<Problem> retypedProblems) {
			this.newResult = newResult;
			this.retypedProblems = new ArrayList<Problem>(retypedProblems);
		
			
			ITracedATLModel trace = (ITracedATLModel) newResult.getATLModel();
			this.impact = new ImpactComputation(this.original, this.newResult, trace).perform();			
		}
		
		public AnalysisResult getRetyped() {
			if ( newResult == null )
				throw new IllegalStateException();
			return newResult;
		}

		public void setNotSupported() {
			this.notSupported = true;
		}
		
		public boolean isNotSupported() {
			return notSupported;
		}

		public AnalysisResult getOriginal() {
			return original;
		}

		public void setImplError() {
			this.implError = true;
		}
		
		public boolean isImplError() {
			return implError;
		}

		public void setDescription(String displayString) {
			this.description = displayString;
		}

		
		List<Problem> originalProblems;
		List<Problem> retypedProblems;
		private int withRuleConflict;
		
		
		public List<Problem> getRetypedProblems() {
			return retypedProblems;
		}

		public List<Problem> getOriginalProblems() {
			return originalProblems;
		}

		public int getNumFixedProblems() {
			// TODO: This may actually happen?
			if ( retypedProblems == null )
				return 0;
			return this.impact.getFixedProblems().size();
		}

		public int getNumNewProblems() {
			// TODO: This may actually happen?
			if ( retypedProblems == null )
				return 0;
			return this.impact.getNewProblems().size();			
		}

		public int getNumMayBeFixedProblems() {
			// TODO: This may actually happen?
			if ( retypedProblems == null )
				return 0;
			return this.impact.getMayBeFixedProblems().size();
		}

		public int getNumMayBeNewProblems() {
			// TODO: This may actually happen?
			if ( retypedProblems == null )
				return 0;
			return this.impact.getMayBeNewProblems().size();
		}

		public void withRuleConflict() {
			withRuleConflict++;
		}

		public long getNumRemainingProblems() {
			return this.impact.getChanged().getPossibleProblems().size();
		}

		public ImpactComputation getImpact() {
			return impact;
		}
		
		// IClassified artifacts
		
		@Override
		public String getId() {
			return null;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<IHint> getHints() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	class AnalysedTransformation {
		protected IResource r;
		protected HashMap<Problem, List<AppliedQuickfixInfo>> problemToQuickfix = new HashMap<>();
		protected Problem currentProblem;
		protected AnalysisResult original;
		protected List<Problem> originalProblems;

		public AnalysedTransformation(IResource resource, AnalysisResult original, List<Problem> originalProblems) {
			this.r = resource;
			this.original = original;
			this.originalProblems = originalProblems;
		}
		
		public void currentProblem(Problem p) {
			ArrayList<AppliedQuickfixInfo> applications = new ArrayList<QuickfixEvaluationAbstract.AppliedQuickfixInfo>();
			problemToQuickfix.put(p, applications);
			this.currentProblem = p;
		}

		public void appliedQuickfix(AppliedQuickfixInfo qi) {
			problemToQuickfix.get(currentProblem).add(qi);
		}
		
		public List<Problem> getOriginalProblems() {
			return originalProblems;
		}

		public List<AppliedQuickfixInfo> getQuickfixes(Problem p) {
			List<AppliedQuickfixInfo> l = problemToQuickfix.get(p);
			if ( l == null )
				l = new ArrayList<QuickfixEvaluationAbstract.AppliedQuickfixInfo>();
			return l;
		}
	}
	
	public class Project {
		private List<AnalysedTransformation> trafos = new ArrayList<QuickfixEvaluationAbstract.AnalysedTransformation>();
		private String name;

		public Project(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public List<AnalysedTransformation> getTrafos() {
			return trafos;
		}
		
	}
	
	
	// Just for test purposes
	protected List<String> messages = new ArrayList<String>();
	protected HashMap<String, Project> projects = new HashMap<String, QuickfixEvaluationAbstract.Project>();
	protected boolean checkProblemsInPath = true;
	
	protected CountingModel<AppliedQuickfixInfo> qfxCounting = new  CountingModel<AppliedQuickfixInfo>();
	
	public QuickfixEvaluationAbstract() {
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);		
	}

	@Override
	public void projectDone(IProject project) {
		// Detect that we are in a new project, so dump the previous
		// and free memory
		if ( ! recordAll && projects.size() == 1 ) {
			Project p = projects.get(project.getName());
			createDetail(workbook, p);
			
			projects.clear(); // free memory
		}
	}
	
	@Override
	protected void perform(IResource resource) {
		perform(resource, null);
	}

	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		String projectName = resource.getProject().getName();
		if ( ! projects.containsKey(projectName) ) {
			projects.put(projectName, new Project(projectName));
		}
		Project project = projects.get(projectName);
		
		evaluateQuickfixesOfFile(resource, project, monitor); 
	}

	protected void evaluateQuickfixesOfFile(IResource resource, Project project, IProgressMonitor monitor) {
		AnalyserData data;
		try {
			data = executeAnalyser(resource);
			if ( data == null )
				return;
			
			allData.add(data);
			
			String fileName = resource.getName();
			System.out.println("*************************************");
			System.out.println("****** Analysing mutant... " + fileName);
			System.out.println("*************************************");
			counting.processingArtefact(fileName);
			
			List<Problem> allProblems = selectProblems(data);

			if ( performRuleAnalysis ) {
				RuleConflict rc = doRuleAnalysis(monitor, data);
				if ( rc != null ) {
					allProblems.add(rc);
					data.extendProblems(Collections.singleton(rc));
				}
			}

			
			AnalysedTransformation trafo = new AnalysedTransformation(resource, data, allProblems);
			project.trafos.add(trafo);

			monitor.beginTask("Processing problems.", allProblems.size());
			
			
			int i = 0;
			for (Problem p : allProblems) {
//				if ( QuickfixCodes.getErrorCode(p).equals("E11")) {
//					System.out.println("here" + p.getClass());
//				} else {
//					continue;
//				}
				
				if ( monitor.isCanceled() ) {
					return;
				}
				
				// Get summary and initialize if needed
				QuickfixSummary qs = summary.get(QuickfixCodes.getErrorCode(p));
				if ( qs == null ) {
					qs = new QuickfixSummary(AnalyserUtils.getProblemId(p), AnalyserUtils.getProblemDescription(p), QuickfixCodes.getErrorCode(p));
					summary.put(QuickfixCodes.getErrorCode(p), qs);										
				}

				
				i++;
				monitor.subTask("Problem " + "(" + i + "/" + allProblems.size() + "): " + p.getDescription());
				
				printMessage("\n");
				printMessage("[" + (isLocal(p) ? ((LocalProblem) p).getLocation() : "") + "]" + p.getDescription());
				
				trafo.currentProblem(p);
			
				List<AtlProblemQuickfix> quickfixes = filter(getQuickfixes(p, data));
				
				if ( quickfixes.size() > 0 ) { 
					// Printing
					printMessage("Available quickfixes:");
					for (AtlProblemQuickfix atlProblemQuickfix : quickfixes) {
						try { 
							printMessage(" * " + atlProblemQuickfix.getDisplayString());
						} catch (Exception e) {
							printMessage(" * Error in 'displayString' method");
							e.printStackTrace();
						}
					}
		
					// Recording					
					int appliedQuickfixesCount = 0;
					int errorsFixed     = 0;
					int errorsGenerated = 0;
					int errorsMayBeFixed     = 0;
					int errorsMayBeGenerated = 0;
					
					int validQuickfixes = 0;
					for (AtlProblemQuickfix quickfix : quickfixes) {
						if ( monitor != null && monitor.isCanceled() )
							return;
						
						AppliedQuickfixInfo qi = null;
						try {
							qi = applyQuickfix(quickfix, resource, p, data, allProblems, qs);
						} catch ( Exception e ) {
							printMessage("ERROR when applying qfx: " + quickfix.getClass().getSimpleName() + " . File: " + resource.getName() + e.getMessage());
							continue;
						}

						
						// I can record this specially somewhere else to count errors and so on
						if ( qi.isImplError() || qi.isNotSupported() )
							continue;
						
						trafo.appliedQuickfix(qi);							
						if ( qi.getRetypedProblems() != null ) {
							errorsFixed += qi.getNumFixedProblems();
							errorsGenerated += qi.getNumNewProblems();
							errorsMayBeFixed += qi.getNumMayBeFixedProblems();
							errorsMayBeGenerated += qi.getNumMayBeNewProblems();

							validQuickfixes += qi.isValid() ? 1 : 0;
							if ( ! qi.isValid() ) {
								System.out.println(resource.getName());
								resourcesWithInvalidQuickfixes.add("Invalid: " + resource.getName() + " - " + qi.getCode() + " - " + p.getDescription());
							}

						}
						
						appliedQuickfixesCount++;
					}
										
					// Add to summary
					qs.appliedQuickfixes(appliedQuickfixesCount, validQuickfixes, errorsFixed, errorsGenerated, errorsMayBeFixed, errorsMayBeGenerated);					
				} else {
					qs.appliedQuickfixes(0, 0, 0, 0, 0, 0);					
					printMessage(" - No quickfixes available");
				}
				
				monitor.worked(1);
			}
			
			monitor.done();
		} catch (Exception e) {
			printMessage("Error " + resource.getName() + e.getMessage());
			counting.addError(resource.getName(), e);
			e.printStackTrace();
			// throw new RuntimeException(e);
		}
	}

	private List<AtlProblemQuickfix> filter(List<AtlProblemQuickfix> quickfixes) {
		String minQuickfix = (String) options.getOrDefault(MIN_QUICKFIX, "Q0.0");
		String maxQuickfix = (String) options.getOrDefault(MAX_QUICKFIX, "Q9999.9999");
		
		ArrayList<AtlProblemQuickfix> result = new ArrayList<AtlProblemQuickfix>();
		for (AtlProblemQuickfix qfx : quickfixes) {
			String code = QuickfixCodes.getCode(qfx);
			if ( code.startsWith("Q")) {
				if ( code.compareToIgnoreCase(minQuickfix) >= 0 && code.compareToIgnoreCase(maxQuickfix) <= 0 ) {
					// Consider the quickfix
					result.add(qfx);
				}
			} else {
				result.add(qfx);
			}
		}
		
		return result;
	}

	private boolean isLocal(Problem p) {
		return p instanceof LocalProblem;
	}


	private List<Problem> selectProblems(AnalysisResult r) {
		List<Problem> originalProblems = r.getProblems();
		ArrayList<Problem> allProblems = new ArrayList<Problem>();
				
		
		// If the flag is active then we only take into account top-level problems
		List<Problem> problems;
		if ( checkProblemsInPath ) {
			problems = r.getAnalyser().getDependencyGraph().getProblemTree().
					getNodes().stream().map(n -> n.getProblem()).collect(Collectors.toList());		
		} else {
			problems = originalProblems;
		}
		
		for (Problem p : problems) {
			if ( useCSP && requireCSP(p) ) {				
				ProblemStatus result = getFinder().find(p, r);
				p.setStatus(result);
				
				switch (result) {
				case ERROR_CONFIRMED:
				case ERROR_CONFIRMED_SPECULATIVE:
					// that's fine						
					// printMessage("Confirmed: " + ((LocalProblem) p).getLocation());
					allProblems.add(p);
					break;
				case ERROR_DISCARDED:
				case ERROR_DISCARDED_DUE_TO_METAMODEL:
					// printMessage("Discarded: " + ((LocalProblem) p).getLocation());
					continue;
				case IMPL_INTERNAL_ERROR:
				case USE_INTERNAL_ERROR:
				case NOT_SUPPORTED_BY_USE:
					printMessage("USE ERROR: " + (isLocal(p) ? ((LocalProblem) p).getLocation() : "") + ", " + ((LocalProblem) p).getFileLocation());
					continue;
				case PROBLEMS_IN_PATH:
					// printMessage("Problems in path for: " + ((LocalProblem) p).getLocation() + ", " + ((LocalProblem) p).getFileLocation());
					continue;					
				}
			} else {
				allProblems.add(p);
			}
		}
		return allProblems;
	}

	protected IWitnessFinder getFinder() {
		return new EclipseUseWitnessFinder().			
				checkProblemsInPath(checkProblemsInPath ).
				checkDiscardCause(false);
	}

	private boolean requireCSP(Problem p) {
		return 	p instanceof BindingPossiblyUnresolved ||
				p instanceof BindingWithResolvedByIncompatibleRule
		;
	}

	protected AppliedQuickfixInfo applyQuickfix(AtlProblemQuickfix quickfix, IResource resource, Problem p, AnalyserData original, List<Problem> originalProblems, QuickfixSummary qs) throws IOException, CoreException, Exception {
		quickfix.setData(ORIGINAL_RESOURCE, resource);
		
		AppliedQuickfixInfo qi = new AppliedQuickfixInfo(quickfix, p, original, originalProblems);
		
		// Run the incremental analyser
		AnalysisResult newResult = runSpeculativeAnalysis_noSolver(quickfix, p, original);		
		//optimizeWithProblemTracking(quickfix, original, newResult);	
		List<Problem> problemsInCopy = completeSpeculativeAnalysis_withSolver(quickfix, original, newResult, qi);
		
			
		try {	
			// This problemsInCopy is not actually needed...
			qi.setRetyped(newResult, problemsInCopy);

//			if ( qi.getCode().equals("Q4.1") ) {
//				printMessage("DEBUG: " +  qi.getNumOfFixes() + " - " + p.getDescription() + " - " + ((LocalProblem) p).getLocation() + " - " + ((LocalProblem) p).getFileLocation() );
//				System.out.println("--");
//			}
			
		} catch ( UnsupportedOperationException e ) {
			printMessage("Quickfix not implemented at the AST Level");
			qi.setNotSupported();
			return qi;
		} catch ( Exception e ) {
			e.printStackTrace();
			qi.setImplError();
			return qi;
		}
		
		qs.appliedQuickfix(qi);
		return qi;
	}

	protected void optimizeWithProblemTracking(AtlProblemQuickfix qfx, AnalyserData original,
			AnalysisResult newResult) {
		if ( optimizeWithProblemTracking  ) {
			ProblemTracker tracker  = new ProblemTracker(original, newResult);
			tracker.copyStatus();
		}
	}

	protected List<Problem> completeSpeculativeAnalysis_withSolver(
			AtlProblemQuickfix quickfix, AnalysisResult original, AnalysisResult newResult,  AppliedQuickfixInfo qi) {
			
		SpeculativeQuickfixUtils.confirmOrDiscardProblems(createFinder(), newResult);
		
		List<Problem> newProblems = selectProblems(newResult);
		try { 
			if ( performRuleAnalysis ) {
				RuleConflict rc = doRuleAnalysis(null, newResult);
				if ( rc != null ) {
					newProblems.add(rc);
					newResult.extendProblems(Collections.singleton(rc));
					qi.withRuleConflict();
				}
			}
		} catch (Exception e) { }
		
		return newProblems;
	}

	protected AnalysisResult runSpeculativeAnalysis_noSolver(
			AtlProblemQuickfix quickfix, Problem p, AnalyserData original) {
		IncrementalCopyBasedAnalyser inc = SpeculativeQuickfixUtils.createIncrementalAnalyser(original, p, quickfix);
		inc.perform();
		AnalysisResult newResult = new AnalysisResult(inc);
		return newResult;
	}

	private IWitnessFinder createFinder() {
		return new EclipseUseWitnessFinder().			
				checkProblemsInPath(checkProblemsInPath ).
				checkDiscardCause(false);
	}

	private void printMessage(String msg) {
		System.out.println(msg);
		messages.add(msg);
	}

	public void printLatexTable(PrintStream out) {
		out.println("\\begin{table}[h]");
		out.println("\\caption{Errors detected and their fixes}");
		out.println("\\label{tab:mutant_fixes}");
		out.println("\\scriptsize");
		out.println("\\center");
		out.println("\\begin{tabular}{|p{1cm}|c|c|c|c|c|c|c|}");
		out.println("\\hline");
		out.println(QuickfixSummary.toLatexHeader());
		summary.values().stream().sorted((q1, q2) -> q1.getLatexDesc().compareTo(q2.getLatexDesc())).forEach(qs -> {
			out.println(qs.toLatexRow());
		});
		
		out.println("\\end{tabular}");
		out.println("\\end{table}");		
	}
	
	@Override
	public void printResult(PrintStream out) {
		printLatexTable(out);
		
		out.println("Invalid quickfixes");
		out.println("******************");
		resourcesWithInvalidQuickfixes.forEach(m -> out.println(m));
		
		out.println();
		out.println("Statistics");
		out.println("********");
		summary.values().forEach(qs -> {
			out.println(qs);
		});
		
		out.println();
		out.println("Messages");
		out.println("********");
		for (String str : messages) {
			out.println(str);
		}
	}

	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String fileName) throws IOException {
		Workbook wb = null;
		if ( recordAll || projects.size() > 0) {		
			wb = new XSSFWorkbook();
			
			for (Project p : projects.values()) {
				createDetail(wb, p);			
			}
		} else {
			wb = workbook;
		}
		
		FileOutputStream fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		wb.close();
		fileOut.close();     
	}

	
	protected void createDetail(Workbook wb, Project project) {
		Sheet s = wb.createSheet(project.getName());
		List<AnalysedTransformation> trafos = project.trafos;
		
		Styler st = new Styler(wb);

		int startCol = 1;
		int starRow  = 1;
		
		int row = starRow;
		
		for (AnalysedTransformation t : trafos) {
			row++;
			
			st.cell(s, row, startCol + 0, t.r.getName()).centeringBold();
			st.createCell(s, row, startCol + 1, (long) t.getOriginalProblems().size());
			row++;
			
			st.cell(s, row, startCol + 1, "Id.").centeringBold();
			st.cell(s, row, startCol + 2, "Description.").centeringBold().charsWidth(50);
			st.cell(s, row, startCol + 3, "Quickfixes").centeringBold();
			st.cell(s, row, startCol + 4, "Fixed").centeringBold();
			st.cell(s, row, startCol + 5, "New").centeringBold();
			st.cell(s, row, startCol + 6, "Remaining").centeringBold();
			
			row++;
			
			
			// for (Problem p : t.problemToQuickfix.keySet()) {
			for (Problem p : t.getOriginalProblems()) {			
				st.createCell(s, row, startCol + 1, (long) AnalyserUtils.getProblemId(p));
				st.createCell(s, row, startCol + 2, QuickfixCodes.getErrorCode(p) + " - " + p.getDescription() + " at " + (isLocal(p) ? ((LocalProblem) p).getLocation() : "-"));
				
				List<AppliedQuickfixInfo> quickfixes = t.getQuickfixes(p);
				if ( quickfixes.isEmpty() ) {
					st.createCell(s, row, startCol + 3, 0L);
				} else {
					st.createCell(s, row, startCol + 3, (long) quickfixes.size());	
				}
				row++;
				for(AppliedQuickfixInfo qi : quickfixes) {
					st.createCell(s, row, startCol + 3, qi.description);
					if ( qi.isNotSupported() ) {
						st.cell(s, row, startCol + 4, (long) 0).background(HSSFColor.DARK_RED.index);
						st.cell(s, row, startCol + 6, "Impl. error");							
					} else if ( qi.isImplError() ) {
						st.cell(s, row, startCol + 4, (long) 0).background(HSSFColor.RED.index);	
						st.cell(s, row, startCol + 6, "Impl. Error");							
					} else {										
						st.createCell(s, row, startCol + 4, (long) qi.getNumFixedProblems());											
						st.createCell(s, row, startCol + 5, (long) qi.getNumNewProblems());											
						st.createCell(s, row, startCol + 6, (long) qi.getNumRemainingProblems());
						
						
						List<Problem> fixed = new ArrayList<>(qi.impact.getFixedProblems());
						List<Problem> gen   = new ArrayList<>(qi.impact.getNewProblems());
						List<Problem> remaining = new ArrayList<>(qi.retypedProblems);
						int max = Math.max(remaining.size(), Math.max(fixed.size(), gen.size()));
						
						for(int i = 0; i < max; i++) {
							row++;
							if ( i < fixed.size() ) {
								st.createCell(s, row, startCol + 4, fixed.get(i).getDescription());							
							}
							if ( i < gen.size() ) {
								st.createCell(s, row, startCol + 5, gen.get(i).getDescription());											
								
							}
							if ( i < remaining.size() ) {
								st.createCell(s, row, startCol + 6, remaining.get(i).getDescription());								
							}							
						}
					}
					row++;
				}				
			}
		}
		
	
	}

	// Similar to the method in the editor...
	public List<AtlProblemQuickfix> getQuickfixes(Problem p, AnalysisResult r) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_QUICKFIX_EXTENSION_POINT);
		ArrayList<AtlProblemQuickfix> quickfixes = new ArrayList<AtlProblemQuickfix>();
		
		MockMarker iMarker = new MockMarker(p, r);
		
		for (IConfigurationElement ce : extensions) {
			try {
				if ( ce.getName().equals("quickfix") ) {
					AtlProblemQuickfix qf = (AtlProblemQuickfix) ce.createExecutableExtension("apply");
					if ( checkIsApplicable(qf, iMarker) && ! discardQuickfix(qf) && ! qf.requiresUserIntervention()) {
						qf.setErrorMarker(iMarker);
						quickfixes.add(qf);
					}
				} 
				else if ( ce.getName().equals("quickfixset") ) {
					AtlProblemQuickfixSet detector = (AtlProblemQuickfixSet) ce.createExecutableExtension("detector");
					if ( detector.isApplicable(iMarker) ) {
						for(AtlProblemQuickfix q : detector.getQuickfixes(iMarker) ) {
							if ( ! checkIsApplicable(q, iMarker) ) {
								throw new IllegalStateException();
							}
							
							if ( discardQuickfix(q) || q.requiresUserIntervention())
								continue;
							
							q.setErrorMarker(iMarker);
							quickfixes.add(q);							
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return quickfixes;
	}

	protected boolean checkIsApplicable(AtlProblemQuickfix qf, MockMarker iMarker) throws CoreException {
		try {
			return qf.isApplicable(iMarker);
		} catch ( Exception e ) {
			return false;
		}
	}

	private static boolean discardQuickfix(AtlProblemQuickfix q) {
		return 	q instanceof ConstraintSolvingQuickFix || 
				q instanceof TransformationSliceQuickFix ||
				
				// Removed because they will "collide" with AddIfToBlock
				q instanceof FeatureFoundInSubtypeQuickfix_AddIfToBlock ||
				q instanceof OperationFoundInSubtypeQuickfix_AddIfToBlock;
	}

	public static String convertToSortable(String quickfixCode) {
		if ( ! quickfixCode.startsWith("Q") ) {
			return quickfixCode;
		}
		
		String[] text = quickfixCode.substring(1).split("\\.");
		if ( text.length != 2 ) 
			return quickfixCode;
		if ( text[0].length() == 1 ) 
			text[0] = "0" + text[0];
		return "Q" + text[0] + "." + text[1];
	}

}
