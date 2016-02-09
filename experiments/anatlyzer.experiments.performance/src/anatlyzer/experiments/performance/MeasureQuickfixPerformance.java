package anatlyzer.experiments.performance;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.experiments.typing.QuickfixCodes;
import anatlyzer.experiments.typing.QuickfixEvaluationAbstract;
import anatlyzer.experiments.typing.QuickfixEvaluationAbstract.AppliedQuickfixInfo;

public class MeasureQuickfixPerformance extends QuickfixEvaluationAbstract {

	//private static final int NUM_DISCARDED = 5;
	//private static final int REPETITIONS   = 20 + NUM_DISCARDED;
	private static final int NUM_DISCARDED = 2;
	private static final int REPETITIONS   = 5;

	private List<EvaluatedResource> resourceResults = new ArrayList<EvaluatedResource>();
	private boolean warmup = false;
	private EvaluatedResource resourceInEvaluation;
	
	public MeasureQuickfixPerformance() {
		recordAll = false;
		checkProblemsInPath = true;
		performRuleAnalysis = true;
		
		optimizeWithProblemTracking = true;
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);
	}
	
	public static class EvaluatedResource {
		IResource resource;
		HashMap<String, MeasureResult> results = new HashMap<String, MeasureResult>();

		public EvaluatedResource(IResource resource) {
			this.resource = resource;
		}

		public String getResourceName() {
			return resource.getName();
		}

		
	}
	
	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		String projectName = resource.getProject().getName();
		if ( ! projects.containsKey(projectName) ) {
			projects.put(projectName, new Project(projectName));
		}
		Project project = projects.get(projectName);
		
		EvaluatedResource r = new EvaluatedResource(resource);
		this.resourceResults.add(r);
		this.resourceInEvaluation = r;
		
		for(int i = 0; i < 4; i++) {
			if ( i < 2 ) {
				warmup  = true;
			} else {
				warmup = false;
			}
			
			evaluateQuickfixesOfFile(resource, project, monitor);
		}
	}
	
	
	@Override
	protected boolean checkIsApplicable(AtlProblemQuickfix qfx, MockMarker iMarker) throws CoreException {
		return recordTime("checkApplicability", 
				() -> { 
					try {
						return super.checkIsApplicable(qfx, iMarker);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				},
				(r, v) -> { 
					if ( v ) {
						MeasureResult mr = getMeasure(qfx);
						Measure measure = new Measure(qfx);
						mr.put(qfx, measure);
						measure.applicationCondition += r.totalTime();
					}
					return v;
				});
	}
	

	@Override
	protected AppliedQuickfixInfo applyQuickfix(AtlProblemQuickfix qfx,
			IResource resource, Problem p, AnalyserData original,
			List<Problem> originalProblems, QuickfixSummary qs)
			throws IOException, CoreException, Exception {
		
		return recordTime("applyQuickfix", 
				() -> { 
					try {
						return super.applyQuickfix(qfx, resource, p, original, originalProblems, qs);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				},
				(r, v) -> { 
					MeasureResult mr = getMeasure(qfx);
					Measure measure = mr.get(qfx);
					if ( v != null && !v.isImplError() && !v.isNotSupported() ) {
						measure.quickfixApplication += r.totalTime();
						measure.invocations++;
					} else {
						measure.dirty();
					}
					return v;
				});
	}
	
	@Override
	protected AnalysisResult runSpeculativeAnalysis_noSolver(AtlProblemQuickfix qfx, Problem p, AnalyserData original) {
		return recordTime("runSpeculativeAnalysis_noSolver", 
				() -> { 
					try {
						return super.runSpeculativeAnalysis_noSolver(qfx, p, original);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				},
				(r, v) -> { 
					MeasureResult mr = getMeasure(qfx);
					Measure measure = mr.get(qfx);
					if ( v != null ) {
						measure.speculativeAnalysisNoSolver += r.totalTime();
					} else {
						measure.dirty();
					}
					return v;
				});
	}
	
	@Override
	protected List<Problem> completeSpeculativeAnalysis_withSolver(
			AtlProblemQuickfix qfx, AnalysisResult original,
			AnalysisResult newResult, AppliedQuickfixInfo qi) {
		return recordTime("completeSpeculativeAnalysis_withSolver", 
				() -> { 
					try {
						return super.completeSpeculativeAnalysis_withSolver(qfx, original, newResult, qi);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				},
				(r, v) -> { 
					MeasureResult mr = getMeasure(qfx);
					Measure measure = mr.get(qfx);
					if ( v != null ) {
						measure.speculativeAnalysisSolver += r.totalTime();
					} else {
						measure.dirty();
					}
					return v;
				});
	}
	
	@Override
	protected void optimizeWithProblemTracking(AtlProblemQuickfix qfx, AnalyserData original, AnalysisResult newResult) {
		long beforeTracking = newResult.getProblems().stream().filter(AnalyserUtils::isWitnessRequred).count();
		super.optimizeWithProblemTracking(qfx, original, newResult);
		long afterTracking  = newResult.getProblems().stream().filter(AnalyserUtils::isWitnessRequred).count();
		
		if ( afterTracking > beforeTracking )
			throw new IllegalStateException();
		
		MeasureResult mr = getMeasure(qfx);
		Measure measure = mr.get(qfx);
		measure.optimizedProblems = beforeTracking - afterTracking;
	}
	
	private MeasureResult getMeasure(AtlProblemQuickfix qf) {
		String code = QuickfixCodes.getCode(qf);
		if ( warmup ) {
			code = "Warmup";
		}
		
		MeasureResult measureResult = resourceInEvaluation.results.get(code);
		if ( measureResult == null ) {
			measureResult = new MeasureResult(code);
			resourceInEvaluation.results.put(code, measureResult);			
		}
		
		return measureResult;
	}

	protected <T> T recordTime(String id, Supplier<T> action, BiFunction<TimeRecorder, T, T> time) {
		TimeRecorder record = new TimeRecorder();
		record.enable();
		record.start(id);
		T v = action.get();
		record.stop();
		return time.apply(record,v );
	}
	
	@Override
	public void printResult(PrintStream out) {
		//TimeRecorder x = getAnalyserTimeRecorder();
		//out.println(x.avgTime() + " millis");

		printLatexTable(out);
	
	}

	public void printLatexTable(PrintStream out) {
		resourceResults.forEach(ev -> {			
			out.println("\\begin{table}[h]");
			out.println("\\caption{Average execution times: " + ev.getResourceName() + "}");
			out.println("\\label{tab:performance}");
			out.println("\\scriptsize");
			out.println("\\center");
			out.println("\\begin{tabular}{|l|c|c||c|c|c|c|}");
			out.println("\\hline");
			out.println(MeasureResult.toLatexHeader());
			
			ev.results.values().stream().sorted((m1, m2) -> convertToSortable(m1.name).compareTo(convertToSortable(m2.name))).
			forEach(m -> out.println(m.toLatexRow()));
			
			
			out.println("\\end{tabular}");
			out.println("\\end{table}");		
		});
		
	}

	
	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String fileName) throws IOException {
		// TODO Auto-generated method stub
		//TimeRecorder x = getAnalyserTimeRecorder();
		//System.out.println(x.avgTime() + " millis");
	
		//printLatexTable(System.out);
	}

	
	public static class Measure {
		public long applicationCondition;
		public long quickfixApplication;
		public long speculativeAnalysisSolver;
		public long speculativeAnalysisNoSolver;
		public int  invocations;
		public long optimizedProblems;
		private IResource resource;
		private AtlProblemQuickfix qfx;
		private boolean dirtyFlag;
		
		public Measure(AtlProblemQuickfix qfx) {
			this.qfx      = qfx;
			this.resource = (IResource) qfx.getData(ORIGINAL_RESOURCE);
		}

		public void dirty() {
			dirtyFlag = true;			
		}

		public boolean isDirty() {
			return dirtyFlag;
		}
	}
		
	public static class MeasureResult {
		protected String name;
		protected HashMap<AtlProblemQuickfix, Measure> qfxToMeasure = new HashMap<AtlProblemQuickfix, MeasureQuickfixPerformance.Measure>();
		private long v_applicationCondition;
		private long v_quickfixApplication;
		private long v_speculativeAnalysisNoSolver;
		private long v_speculativeAnalysisSolver;
		private long v_optimizedProblems;
		private long v_invocations;
		
		public MeasureResult(String qfxName) {
			this.name = qfxName;
		}

		public Measure get(AtlProblemQuickfix qfx) {
			if ( ! qfxToMeasure.containsKey(qfx) ) 
				throw new IllegalStateException();
			
			return qfxToMeasure.get(qfx);
		}

		public void put(AtlProblemQuickfix qfx, Measure measure) {
			if ( qfxToMeasure.containsKey(qfx) ) 
				throw new IllegalStateException();
			
			qfxToMeasure.put(qfx, measure);
		}

		public static String toLatexHeader() {
			return "{\\bf Qfx.}  & {\\bf Condition} & {\\bf Exec.} & {\\bf Static} & {\\bf Solver} & {\\bf Opt.} & {\\bf \\#Invok.}  \\\\ \\hline";	
		}
		
		public String toLatexRow() {
			List<Measure> measures = qfxToMeasure.values().stream().filter(m -> ! m.isDirty()).collect(Collectors.toList());
			if ( measures.size() == 0 )
				return "";
			
			v_applicationCondition = measures.stream().mapToLong(m -> m.applicationCondition).sum();
			v_quickfixApplication  = measures.stream().mapToLong(m -> m.quickfixApplication).sum();
			v_speculativeAnalysisNoSolver  = measures.stream().mapToLong(m -> m.speculativeAnalysisNoSolver).sum();
			v_speculativeAnalysisSolver  = measures.stream().mapToLong(m -> m.speculativeAnalysisSolver).sum();
			v_optimizedProblems  = measures.stream().mapToLong(m -> m.optimizedProblems).sum();
			v_invocations  = measures.stream().mapToLong(m -> m.invocations).sum();			
			
			return String.format(Locale.US, "%s & %.1f & %.1f & %.1f & %.1f & %d & %d \\\\ \\hline" , 
					name, 
					((double) v_applicationCondition) / v_invocations, 
					((double) v_quickfixApplication) / v_invocations, 
					((double) v_speculativeAnalysisNoSolver) / v_invocations, 
					((double) v_speculativeAnalysisSolver) / v_invocations, 
					v_optimizedProblems,
					v_invocations);
		}

	}
	
}
