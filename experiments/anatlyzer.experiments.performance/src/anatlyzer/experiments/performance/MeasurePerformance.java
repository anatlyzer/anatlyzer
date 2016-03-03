package anatlyzer.experiments.performance;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.experiments.performance.TimeRecorder.SingleExecution;
import anatlyzer.experiments.performance.export.PerformanceToExcel;
import anatlyzer.experiments.performance.raw.MeasureResult;
import anatlyzer.experiments.performance.raw.PEData;
import anatlyzer.experiments.performance.raw.PEProblemExecution;
import anatlyzer.experiments.performance.raw.PETime;
import anatlyzer.experiments.performance.raw.PETransformation;
import anatlyzer.experiments.performance.raw.PETransformationExecution;
import anatlyzer.experiments.typing.AbstractATLExperiment;
import anatlyzer.experiments.typing.raw.TEData;
import anatlyzer.ui.util.AtlEngineUtils;

public class MeasurePerformance extends AbstractATLExperiment {

	public static final int NUM_DISCARDED = 0;
	public static final int REPETITIONS   = 1;

	private static final List<MeasureResult> results = new ArrayList<MeasureResult>();
	private PEData expData;
	
	public MeasurePerformance() {
		MeasurePathComputationTime.aspectOf().activate();
		
		this.expData = new PEData();	
	}
	
	@Override
	public void printResult(PrintStream out) {
		//TimeRecorder x = getAnalyserTimeRecorder();
		//out.println(x.avgTime() + " millis");

		printLatexTable(out);
	
	}

	@Override
	public void openData(IFile expFile) {
		String fname = createDataFileName(expFile, "performance", "perf");
		
		FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell());
		dialog.setFileName(fname);
		fname = dialog.open();
		if ( fname != null ) {
			// Read the data
			Serializer serializer = new Persister();
			try {
				PEData expDataLocal = serializer.read(PEData.class, new File(fname));
				if ( expData != null && MessageDialog.openQuestion(null, "Data already loaded", "Do you want to replace?") ) {
					expData = expDataLocal;
				} else {
					expData = expDataLocal;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	
	
	}
	
	@Override
	public void saveData(IFile expFile) {
        String fname = createDataFileName(expFile, "performance", "perf");
        
		// http://www.ibm.com/developerworks/library/x-simplexobjs/
		// http://simple.sourceforge.net/download/stream/doc/examples/examples.php
		Serializer serializer = new Persister();
        File result = new File(fname);
        try {
			serializer.write(expData, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		new PerformanceToExcel(expData).exportToExcel(fileName);
	}

	/**
	 * This represents the execution time of the analyser without constraint solving.
	 * @return
	 */
	protected TimeRecorder getAnalyserTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().analyserTime;
	}

	protected TimeRecorder getParseTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().parserTime;
	}
	
	protected TimeRecorder getMetamodelTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().metamodelTime;
	}

	TimeRecorder createATLModelTimeRecorder = new TimeRecorder();
	protected TimeRecorder getCreateATLModelTimeRecorder() {
		return createATLModelTimeRecorder;
	}

	protected TimeRecorder getCreatePathTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().pathCreation;
	}

	protected TimeRecorder getProblemTreeTimeRecorder() {
		return MeasurePathComputationTime.aspectOf().problemTreeCreation;
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
			resetAll();
			
			/*
			for(int i = 0; i < NUM_DISCARDED; i++) {			
				AnalyserData data = executeAnalyser(resource);
				confirmProblems(data.getProblems(), data);
			}
			*/
			
			System.out.println("Measuring " + resource.getName());
		
			PETransformation trafo = new PETransformation(resource.getName(), resource.getFullPath().toPortableString());
			
			for(int i = 0; i < REPETITIONS + NUM_DISCARDED; i++) {			
				startAll();
				
				PETransformationExecution currentExecution = new PETransformationExecution();
				
				TimeRecorder totalAnalysis = new TimeRecorder();
				totalAnalysis.enable();
				totalAnalysis.start("total");

				
				AnalyserData data = executeAnalyser(resource);
				if ( data == null ) {
					if ( trafo.getExecutions().isEmpty() ) {
						// Discard the transformation, it is probably a library
						return;
					} else {
						throw new IllegalStateException("Failing now but not before...");
					}
				}
				
				currentExecution.setAnalysisTime( getSingleTime(getAnalyserTimeRecorder()) );
				currentExecution.setParserTime( getSingleTime( getParseTimeRecorder() ));
				currentExecution.setMetamodelLoadTime( getSingleTime( getMetamodelTimeRecorder() ));
				currentExecution.setCreateATLModelTime( getSingleTime( getCreateATLModelTimeRecorder() ) );
				
				long totalProblemTreeTime = mapTimes(getProblemTreeTimeRecorder()).stream().collect(Collectors.summingLong(t -> t.getTime()));
				long totalPathGenTime     = mapTimes(getCreatePathTimeRecorder()).stream().collect(Collectors.summingLong(t -> t.getTime()));
				currentExecution.setProblemTreeCreationTime(new PETime(totalProblemTreeTime));
				currentExecution.setPathGenerationTime(new PETime(totalPathGenTime));
				
				List<Problem> problems = data.getProblems();
				AnalysisResult r = data;
		
				TimeRecorder rawModelFindingTime = new TimeRecorder();
				rawModelFindingTime.enable();
				
				for (Problem p : problems) {
					if ( p.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {				
						resetSolver();
						startSolver();
						
						PEProblemExecution exec = new PEProblemExecution(p);

						rawModelFindingTime.start("RAW_MODEL_FINDER");
							ProblemStatus result = execFinder(p, r);
							p.setStatus(result);
						rawModelFindingTime.stop();
					
						exec.setFinalStatus(result);
						currentExecution.addProblemExecution(exec);
						
						PETime pathTime = getSingleTime(getCreatePathTimeRecorder());
						PETime condGenTime = getSingleTime(getConditionGenTimeRecorder());				
						List<PETime> solverTimes  = mapTimes(getSolverTimeRecorder());
						PETime effTime = getSingleTime(getEffectiveMetamodelRecorder());
						// PETime effTime = getSingleTime(getEffectiveMetamodelRecorder());
						PETime errTime = getSingleTime(getErrorMetamodelRecorder());

						if ( solverTimes.size() == 0 && (AnalyserUtils.isConfirmed(result) || AnalyserUtils.isDiscarded(result)) ) 
							throw new IllegalStateException("No solver time for " + p.getDescription());
						
						exec.setCreatePathTime(pathTime);
						exec.setConditionGenerationTime(condGenTime);
						exec.addSolverTimes(solverTimes);
						exec.setEffectiveMetamodelTime(effTime);
						exec.setErrorMetamodelTime(errTime);
						// TODO: Compute the meta-model extension? Error-path strategy does nothing so... no need.						
						
					}
				}
				
				
				long totalRawModelFindingTime = mapTimes(rawModelFindingTime).stream().collect(Collectors.summingLong(t -> t.getTime()));
				currentExecution.setRawModelFindingTime(new PETime(totalRawModelFindingTime));
				
				
				totalAnalysis.stop();
				currentExecution.setTotalTime(getSingleTime(totalAnalysis));

				// The execution is added at then, if there are exceptions no execution is added
				trafo.addExecution(currentExecution);

				
				resetAll();
			}

			// The transformation data is added when we have been able to
			// execute everything, without exceptions
			expData.addTransformation(trafo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected EMFModel parseATLFile(IFile file) {
		getParseTimeRecorder().start("PARSE");
		EMFModel r = super.parseATLFile(file);
		getParseTimeRecorder().stop();
		return r;
	}

	@Override
	protected ATLModel createATLModel(IFile file, EMFModel atlEMFModel) {
		getCreateATLModelTimeRecorder().start("CREATE_ATL_MODEL");
		ATLModel r = super.createATLModel(file, atlEMFModel);
		getCreateATLModelTimeRecorder().stop();
		return r;
	}
	
	private List<PETime> mapTimes(TimeRecorder tr) {
		return tr.getExecutions().stream().map(e -> new PETime(e.getMillis())).collect(Collectors.toList());		
	}

	private PETime getSingleTime(TimeRecorder tr) {
		List<SingleExecution> execs = tr.getExecutions();
		if ( execs.size() > 1 ) 
			throw new IllegalStateException();
		if ( execs.size() == 0 ) 
			return new PETime(-1);
		
		SingleExecution e = execs.get(0);
		return new PETime(e.getMillis());
	}

	protected void startAll() {
		getAnalyserTimeRecorder().enable();
		getParseTimeRecorder().enable();
		getCreateATLModelTimeRecorder().enable();
		getMetamodelTimeRecorder().enable();
		getProblemTreeTimeRecorder().enable();
		startSolver();
	}

	protected void startSolver() {
		getCreatePathTimeRecorder().enable();
		getConditionGenTimeRecorder().enable();
		getSolverTimeRecorder().enable();
		getEffectiveMetamodelRecorder().enable();
		getErrorMetamodelRecorder().enable();
		getExtendMetamodelRecorder().enable();
	}

	protected void resetAll() {
		getAnalyserTimeRecorder().reset();
		getParseTimeRecorder().reset();
		getCreateATLModelTimeRecorder().reset();
		getMetamodelTimeRecorder().reset();
		getProblemTreeTimeRecorder().reset();
		resetSolver();
	}

	protected void resetSolver() {
		getCreatePathTimeRecorder().reset();
		getConditionGenTimeRecorder().reset();
		getSolverTimeRecorder().reset();
		getEffectiveMetamodelRecorder().reset();
		getErrorMetamodelRecorder().reset();
		getExtendMetamodelRecorder().reset();
	}

}
