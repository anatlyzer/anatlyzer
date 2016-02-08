package anatlyzer.experiments.performance;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.errors.Problem;
import anatlyzer.experiments.typing.QuickfixEvaluationAbstract;

public class MeasureQuickfixPerformance extends QuickfixEvaluationAbstract {

	//private static final int NUM_DISCARDED = 5;
	//private static final int REPETITIONS   = 20 + NUM_DISCARDED;
	private static final int NUM_DISCARDED = 2;
	private static final int REPETITIONS   = 5;

	// private static final List<MeasureResult> results = new ArrayList<MeasureResult>();
	
	public TimeRecorder checkApplicability = new TimeRecorder();
	public TimeRecorder quickfixApplication = new TimeRecorder();
	public TimeRecorder speculativeAnalysis = new TimeRecorder();
	
	public MeasureQuickfixPerformance() {
		recordAll = false;
		checkProblemsInPath = false;
		
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
		
		evaluateQuickfixesOfFile(resource, project, monitor);
	}
	
	
	@Override
	protected boolean checkIsApplicable(AtlProblemQuickfix qf, MockMarker iMarker) throws CoreException {
		checkApplicability.start("checkApplicability");
		boolean res = super.checkIsApplicable(qf, iMarker);
		checkApplicability.stop();
		return res;
	}
	
	@Override
	protected AppliedQuickfixInfo applyQuickfix(AtlProblemQuickfix quickfix,
			IResource resource, Problem p, AnalyserData original,
			List<Problem> originalProblems, QuickfixSummary qs)
			throws IOException, CoreException, Exception {
		
		checkApplicability.start(resource.getName());
		AppliedQuickfixInfo res = super.applyQuickfix(quickfix, resource, p, original, originalProblems, qs);
		checkApplicability.stop();
		return res;
	}
	
	
	@Override
	public void printResult(PrintStream out) {
		//TimeRecorder x = getAnalyserTimeRecorder();
		//out.println(x.avgTime() + " millis");

		printLatexTable(out);
	
	}

	public void printLatexTable(PrintStream out) {
//		out.println("\\begin{table}[h]");
//		out.println("\\caption{Average execution times}");
//		out.println("\\label{tab:performance}");
//		out.println("\\scriptsize");
//		out.println("\\center");
//		out.println("\\begin{tabular}{|l|c|c|c|c|c|c|}");
//		out.println("\\hline");
//		out.println(MeasureResult.toLatexHeader());
//		results.forEach(m -> out.println(m.toLatexRow()));
//		
//		out.println("\\end{tabular}");
//		out.println("\\end{table}");		
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

	
//	public static class MeasureResult {
//		public String name;
//		
//		public double applicationCondition;
//		public double quickfixapplication;
//
//		public MeasureResult(String name) {
//			this.name = name;
//		}
//
//		public static String toLatexHeader() {
//			return "{\\bf Trafo.}  & {\\bf Analysis} & {\\bf Path} & {\\bf MM} & {\\bf \\#Paths} & {\\bf Solver} & {\\bf \\#Invok.}  \\\\ \\hline";	
//		}
//		
//		public String toLatexRow() {
//			return String.format(Locale.US, "%s & %.1f & %.1f & %.1f & %d & %.1f & %d  \\\\ \\hline" , 
//					name, analysis, (pathGen + condGen) / numPaths, errorMetamodel / numPaths, numPaths, solver / numSolver, numSolver);
//		}
//		
//		private String formatDouble(double d) {
//			return String.format(Locale.US, "%.1f", d);
//		}
//	}
	
}
