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

	private HashMap<String, MeasureResult> results = new HashMap<String, MeasureResult>();
	private boolean warmup = false;
	
	public MeasureQuickfixPerformance() {
		recordAll = false;
		checkProblemsInPath = true;
		performRuleAnalysis = true;
		
		optimizeWithProblemTracking = true;
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);
	}
	
	
	
	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		String projectName = resource.getProject().getName();
		if ( ! projects.containsKey(projectName) ) {
			projects.put(projectName, new Project(projectName));
		}
		Project project = projects.get(projectName);
		
		
		for(int i = 0; i < 3; i++) {
			if ( i < 2 ) {
				warmup  = true;
			} else {
				warmup = false;
			}
			
			evaluateQuickfixesOfFile(resource, project, monitor);
		}
	}
	
	
	@Override
	protected boolean checkIsApplicable(AtlProblemQuickfix qf, MockMarker iMarker) throws CoreException {
		return recordTime("checkApplicability", 
				() -> { 
					try {
						return super.checkIsApplicable(qf, iMarker);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				},
				(r, v) -> { 
					if ( v ) {
						MeasureResult mr = getMeasure(qf);
						mr.applicationCondition += r.totalTime();
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
					if ( v != null && !v.isImplError() && !v.isNotSupported() ) {
						MeasureResult mr = getMeasure(qfx);
						mr.quickfixApplication += r.totalTime();
						mr.invocations++;
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
					if ( v != null ) {
						MeasureResult mr = getMeasure(qfx);
						mr.speculativeAnalysisNoSolver += r.totalTime();
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
					if ( v != null ) {
						MeasureResult mr = getMeasure(qfx);
						mr.speculativeAnalysisSolver += r.totalTime();
					}
					return v;
				});
	}
	
	@Override
	protected void optimizeWithProblemTracking(AtlProblemQuickfix qfx, AnalyserData original, AnalysisResult newResult) {
		long beforeTracking = newResult.getProblems().stream().filter(AnalyserUtils::isWitnessRequred).count();
		super.optimizeWithProblemTracking(qfx, original, newResult);
		long afterTracking  = newResult.getProblems().stream().filter(AnalyserUtils::isWitnessRequred).count();
		
		MeasureResult mr = getMeasure(qfx);
		if ( afterTracking > beforeTracking )
			throw new IllegalStateException();
		mr.optimizedProblems = beforeTracking - afterTracking;
	}
	
	private MeasureResult getMeasure(AtlProblemQuickfix qf) {
		String code = QuickfixCodes.getCode(qf);
		if ( warmup ) {
			code = "Warmup";
		}
		
		MeasureResult measureResult = this.results.get(code);
		if ( measureResult == null ) {
			measureResult = new MeasureResult(code);
			results.put(code, measureResult);			
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
		out.println("\\begin{table}[h]");
		out.println("\\caption{Average execution times}");
		out.println("\\label{tab:performance}");
		out.println("\\scriptsize");
		out.println("\\center");
		out.println("\\begin{tabular}{|l|c|c||c|c|c|c|}");
		out.println("\\hline");
		out.println(MeasureResult.toLatexHeader());
		results.values().forEach(m -> out.println(m.toLatexRow()));
		
		out.println("\\end{tabular}");
		out.println("\\end{table}");		
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

	
	public static class MeasureResult {

		public long optimizedProblems;

		public String name;
		
		public long applicationCondition;
		public long quickfixApplication;
		public long speculativeAnalysisSolver;
		public long speculativeAnalysisNoSolver;
		public int    invocations;
		
		public MeasureResult(String qfxName) {
			this.name = qfxName;
		}

		public static String toLatexHeader() {
			return "{\\bf Qfx.}  & {\\bf Condition} & {\\bf Exec.} & {\\bf Static} & {\\bf Solver} & {\bf Opt.} & {\\bf \\#Invok.}  \\\\ \\hline";	
		}
		
		public String toLatexRow() {
			return String.format(Locale.US, "%s & %.1f & %.1f & %.1f & %.1f & %d %d \\\\ \\hline" , 
					name, 
					((double) applicationCondition) / invocations, 
					((double) quickfixApplication) / invocations, 
					((double) speculativeAnalysisNoSolver) / invocations, 
					((double) speculativeAnalysisSolver) / invocations, 
					optimizedProblems,
					invocations);
		}

	}
	
}
