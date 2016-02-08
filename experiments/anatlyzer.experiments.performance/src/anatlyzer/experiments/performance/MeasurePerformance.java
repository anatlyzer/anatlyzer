package anatlyzer.experiments.performance;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.experiments.typing.AbstractATLExperiment;
import anatlyzer.ui.util.WorkbenchUtil;

public class MeasurePerformance extends AbstractATLExperiment {

	//private static final int NUM_DISCARDED = 5;
	//private static final int REPETITIONS   = 20 + NUM_DISCARDED;
	private static final int NUM_DISCARDED = 2;
	private static final int REPETITIONS   = 5;

	private static final List<MeasureResult> results = new ArrayList<MeasureResult>();
	
	public MeasurePerformance() {
		MeasurePathComputationTime.aspectOf().activate();
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
		out.println("\\begin{tabular}{|l|c|c|c|c|c|c|}");
		out.println("\\hline");
		out.println(MeasureResult.toLatexHeader());
		results.forEach(m -> out.println(m.toLatexRow()));
		
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
		TimeRecorder x = getAnalyserTimeRecorder();
		System.out.println(x.avgTime() + " millis");
	
		printLatexTable(System.out);
	}

	protected TimeRecorder getAnalyserTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().analyserTime;
	}

	protected TimeRecorder getCreatePathTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().pathCreation;
	}
	
	protected TimeRecorder getConditionGenTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().conditionGen;
	}

	protected TimeRecorder getSolverTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().solverTime;
	}

	protected TimeRecorder getEffectiveMetamodelRecorder() {
		return MeasurePathComputationTime.aspectOf().effectiveMetamodel;
	}

	protected TimeRecorder getErrorMetamodelRecorder() {
		return MeasurePathComputationTime.aspectOf().errorMetamodel;
	}

	protected TimeRecorder getExtendMetamodelRecorder() {
		return MeasurePathComputationTime.aspectOf().extendMetamodels;
	}

	@Override
	protected void perform(IResource resource) {
		try {
			getAnalyserTimeRecorder().reset();
			getCreatePathTimeRecorder().reset();
			getConditionGenTimeRecorder().reset();
			getSolverTimeRecorder().reset();
			getEffectiveMetamodelRecorder().reset();
			getErrorMetamodelRecorder().reset();
			getExtendMetamodelRecorder().reset();
			
			for(int i = 0; i < NUM_DISCARDED; i++) {			
				AnalyserData data = executeAnalyser(resource);
				confirmProblems(data.getProblems(), data);
			}

			getAnalyserTimeRecorder().enable();
			getCreatePathTimeRecorder().enable();
			getConditionGenTimeRecorder().enable();
			getSolverTimeRecorder().enable();
			getEffectiveMetamodelRecorder().enable();
			getErrorMetamodelRecorder().enable();
			getExtendMetamodelRecorder().enable();
			
			for(int i = 0; i < REPETITIONS; i++) {			
				AnalyserData data = executeAnalyser(resource);
				confirmProblems(data.getProblems(), data);
			}
			
			MeasureResult r = new MeasureResult(resource.getFullPath().removeFileExtension().lastSegment());
			results.add(r);
			r.analysis = getAnalyserTimeRecorder().avgTime();
			r.pathGen  = ((double) getCreatePathTimeRecorder().totalTime()) / (REPETITIONS);
			r.condGen   = ((double) getConditionGenTimeRecorder().totalTime()) / (REPETITIONS);
			r.numPaths  = getConditionGenTimeRecorder().numRecorded() / REPETITIONS;
			r.solver    = ((double) getSolverTimeRecorder().totalTime()) / (REPETITIONS);
			r.numSolver = getSolverTimeRecorder().numRecorded() / REPETITIONS;
			r.errorMetamodel = ((double) (getEffectiveMetamodelRecorder().totalTime() + getErrorMetamodelRecorder().totalTime() + getExtendMetamodelRecorder().totalTime())) / REPETITIONS;
			
//			r.pathGen  = getCreatePathTimeRecorder().avgTime();
//			r.condGen  = getConditionGenTimeRecorder().avgTime();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected IWitnessFinder createWitnessFinder() {
		return new UseWitnessFinder() {
			
			@Override
			protected void onUSEInternalError(Exception e) {
				e.printStackTrace();
			}
			
			private String tempDirectory = null;
			
			@Override
			public String getTempDirectory() {
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						tempDirectory = WorkbenchUtil.getProjectPath();
					}
				});
				return tempDirectory;
			}

		}.checkDiscardCause(false);
	}
	
	public static class MeasureResult {
		public double errorMetamodel;

		public String name;
		
		public double analysis;
		public double pathGen;
		public double condGen;
		public double solver;

		public int numPaths;
		public int numSolver;

		public MeasureResult(String name) {
			this.name = name;
		}

		public static String toLatexHeader() {
			return "{\\bf Trafo.}  & {\\bf Analysis} & {\\bf Path} & {\\bf MM} & {\\bf \\#Paths} & {\\bf Solver} & {\\bf \\#Invok.}  \\\\ \\hline";	
		}
		
		public String toLatexRow() {
			return String.format(Locale.US, "%s & %.1f & %.1f & %.1f & %d & %.1f & %d  \\\\ \\hline" , 
					name, analysis, (pathGen + condGen) / numPaths, errorMetamodel / numPaths, numPaths, solver / numSolver, numSolver);
		}
		
		private String formatDouble(double d) {
			return String.format(Locale.US, "%.1f", d);
		}
	}
	
}
