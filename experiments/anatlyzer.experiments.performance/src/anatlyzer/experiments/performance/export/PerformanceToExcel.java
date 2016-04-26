package anatlyzer.experiments.performance.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.experiments.export.ExcelUtil;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.performance.MeasurePerformance;
import anatlyzer.experiments.performance.raw.PEData;
import anatlyzer.experiments.performance.raw.PEProblemExecution;
import anatlyzer.experiments.performance.raw.PETime;
import anatlyzer.experiments.performance.raw.PETransformation;
import anatlyzer.experiments.performance.raw.PETransformationExecution;

public class PerformanceToExcel {

	private static final int DISCARD = MeasurePerformance.NUM_DISCARDED;
	private PEData data;

	public PerformanceToExcel(PEData data) {
		this.data = data;
	}

	public void exportToExcel(String fileName) throws IOException {
		Workbook wb = new XSSFWorkbook();
		
		createSummary(wb);
		createSolverDetails(wb);

		// Save
		FileOutputStream fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		wb.close();
		fileOut.close();           
	}

	private void createSolverDetails(Workbook wb) {
		Sheet s = wb.createSheet("Solver details");
		ExcelUtil u = new ExcelUtil();
		Styler    st = new Styler(wb);

		int initRow = 1;
		int col = 0;
	
		col = printSolverDetails(s, st, "All", initRow, col, (exec) -> true);
		col = printSolverDetails(s, st, "Confirmed", initRow, col + 1, (exec) -> exec.isFinallyConfirmed());
		col = printSolverDetails(s, st, "Discarded", initRow, col + 1, (exec) -> exec.isFinallyDiscarded());
	}

	protected int printSolverDetails(Sheet s, Styler st, String name, int initRow, int col,
			Function<PEProblemExecution, Boolean> f) {
		
		st.cell(s, initRow, col + 1, name).centeringBold();			
		
		initRow++;
		st.cell(s, initRow, col + 1, "Trafo").centeringBold();			
		st.cell(s, initRow, col + 2, "Id").centeringBold();			
		st.cell(s, initRow, col + 3, "Time").centeringBold();			
		st.cell(s, initRow, col + 4, "Status").centeringBold();				
		st.cell(s, initRow, col + 5, "Description").centeringBold();		
		st.cell(s, initRow, col + 6, "Location").centeringBold();				
		
		
		int row = initRow + 1;	
		for (PETransformation trafo : data.getTransformations()) {
			PETransformationExecution avg = trafo.getAvg(DISCARD);
			
			for (PEProblemExecution exec : avg.getProblemExecutions()) {
				if ( ! f.apply(exec) )
					continue;
				
				st.cell(s, row, col + 1, trafo.getName());
				st.cell(s, row, col + 2, exec.getProblemId());
				st.cell(s, row, col + 3, exec.getTotalSolverTime().getTime());			
				st.cell(s, row, col + 4, convert(exec.getFinalStatus()));							
				st.cell(s, row, col + 5, exec.getProblemTypeDescription());
				st.cell(s, row, col + 6, exec.getLocation());

				row++;
			}
		}
		
		return col + 5;
	}
	
	private String convert(ProblemStatus status) {
		if ( AnalyserUtils.isConfirmed(status) ) return "C";
		if ( AnalyserUtils.isDiscarded(status) ) return "D";
		if ( AnalyserUtils.isTimeOut(status)) return "TO";
		throw new IllegalStateException();
	}

	private void createSummary(Workbook wb) {
		Sheet s = wb.createSheet("Summary");
		ExcelUtil u = new ExcelUtil();
		Styler    st = new Styler(wb);
		
		int initRow = 2;
		int col = 0;
		int row = initRow + 1;	
		
		st.cell(s, initRow, col + 1, "Name").centeringBold();			
		st.cell(s, initRow, col + 2, "Total").centeringBold();
		st.cell(s, initRow, col + 3, "Parse").centeringBold();
		st.cell(s, initRow, col + 4, "Ext. ATL").centeringBold();		
		st.cell(s, initRow, col + 5, "Metamodel").centeringBold();
		st.cell(s, initRow, col + 6, "Analysis").centeringBold();
		st.cell(s, initRow, col + 7, "Path. gen").centeringBold();
		st.cell(s, initRow, col + 8, "Tree  gen").centeringBold();
		st.cell(s, initRow, col + 9, "Finding").centeringBold();
		
		st.cell(s, initRow, col + 10, "").centeringBold();
		
		st.cell(s, initRow, col + 11, "Num. Prob.").centeringBold();
		st.cell(s, initRow, col + 12, "Timed out").centeringBold();
		
		st.cell(s, initRow, col + 13, "Path. gen").centeringBold();
		st.cell(s, initRow, col + 14, "Cond. gen").centeringBold();
		st.cell(s, initRow, col + 15, "Error MM").centeringBold();
		st.cell(s, initRow, col + 16, "Eff.  MM").centeringBold();
		st.cell(s, initRow, col + 17, "Solver").centeringBold();

		st.cell(s, initRow, col + 18, "Max. solver").centeringBold();
		st.cell(s, initRow, col + 19, "Min. solver").centeringBold();
		st.cell(s, initRow, col + 20, "Median solver").centeringBold();

		st.cell(s, initRow, col + 21, "MM classes").centeringBold();
		st.cell(s, initRow, col + 22, "MM features").centeringBold();
		st.cell(s, initRow, col + 23, "MM comp. ref.").centeringBold();

		st.cell(s, initRow, col + 24, "Avg classes").centeringBold();
		st.cell(s, initRow, col + 25, "Avg features").centeringBold();
		st.cell(s, initRow, col + 26, "Avg comp. ref.").centeringBold();

		
		for (PETransformation trafo : data.getTransformations()) {
			PETransformationExecution avg = trafo.getAvg(DISCARD);
			
			st.cell(s, row, col + 1, trafo.getName());
			st.cell(s, row, col + 2, avg.getTotalTime().toSeconds());
			st.cell(s, row, col + 3, avg.getParserTime().toSeconds());
			st.cell(s, row, col + 4, avg.getCreateATLModelTime().toSeconds());			
			st.cell(s, row, col + 5, avg.getMetamodelLoadTime().toSeconds());

			st.cell(s, row, col + 6, avg.getAnalysisTime().toSeconds() - (avg.getPathGenerationTime().toSeconds() + avg.getProblemTreeCreationTime().toSeconds()) );
			st.cell(s, row, col + 7, avg.getPathGenerationTime().toSeconds());
			st.cell(s, row, col + 8, avg.getProblemTreeCreationTime().toSeconds());
			st.cell(s, row, col + 9, avg.getRawModelFindingTime().toSeconds());
			
			long numProblems  = avg.getProblemExecutions().size();
			double createPath = avg.getProblemExecutions().stream().map(e -> e.getCreatePathTime()).collect(Collectors.averagingDouble(t -> t.toSeconds()));
			double condGen    = avg.getProblemExecutions().stream().map(e -> e.getConditionGenerationTime()).collect(Collectors.averagingDouble(t -> t.toSeconds()));
			double errorMM    = avg.getProblemExecutions().stream().map(e -> e.getErrorMetamodelTime()).collect(Collectors.averagingDouble(t -> t.toSeconds()));
			double effMM      = avg.getProblemExecutions().stream().map(e -> e.getEffectiveMetamodelTime()).collect(Collectors.averagingDouble(t -> t.toSeconds()));
			double solver     = avg.getProblemExecutions().stream().map(e -> e.getTotalSolverTime()).collect(Collectors.averagingDouble(t -> t.toSeconds()));

			double maxSolver  = avg.getProblemExecutions().stream().map(e -> e.getTotalSolverTime()).collect(Collectors.maxBy(Comparator.comparingLong(t -> t.getTime()))).map(t -> t.toSeconds()).orElse(-1.0);
			double minSolver  = avg.getProblemExecutions().stream().map(e -> e.getTotalSolverTime()).collect(Collectors.minBy(Comparator.comparingLong(t -> t.getTime()))).map(t -> t.toSeconds()).orElse(-1.0);
			
			List<PETime> sortedExecs = avg.getProblemExecutions().stream().map(e -> e.getTotalSolverTime()).sorted(Comparator.comparingLong(t -> t.getTime())).collect(Collectors.toList());
			double medianSolver = sortedExecs.size() == 0 ? -1 : sortedExecs.get(sortedExecs.size() / 2 ).toSeconds();
						
			// This would be the total of the succesfully evaluated problems
			// st.cell(s, row, col + 9, (createPath + condGen + errorMM + effMM + solver) * numProblems);
			
			long timedOut = avg.getProblemExecutions().stream().filter(exec -> exec.getFinalStatus() == ProblemStatus.USE_TIME_OUT).count();
			
			st.cell(s, row, col + 11, numProblems);
			st.cell(s, row, col + 12, timedOut);
			
			st.cell(s, row, col + 13, createPath);
			st.cell(s, row, col + 14, condGen);
			st.cell(s, row, col + 15, errorMM);
			st.cell(s, row, col + 16, effMM);
			st.cell(s, row, col + 17, solver);
			st.cell(s, row, col + 18, maxSolver);
			st.cell(s, row, col + 19, minSolver);
			st.cell(s, row, col + 20, medianSolver);

			
			double avgClasses = avg.getProblemExecutions().stream().filter(e -> e.getStats() != null).map(e -> e.getStats()).collect(Collectors.averagingDouble(t -> t.getNumClasses()));
			double avgFeatures = avg.getProblemExecutions().stream().filter(e -> e.getStats() != null).map(e -> e.getStats()).collect(Collectors.averagingDouble(t -> t.getNumFeatures()));
			double avgCompRefs = avg.getProblemExecutions().stream().filter(e -> e.getStats() != null).map(e -> e.getStats()).collect(Collectors.averagingDouble(t -> t.getNumCompulsoryReferences()));

			st.cell(s, row, col + 21, trafo.getStats().getNumClasses());
			st.cell(s, row, col + 22, trafo.getStats().getNumFeatures());
			st.cell(s, row, col + 23, trafo.getStats().getNumCompulsoryReferences());

			st.cell(s, row, col + 24, avgClasses);
			st.cell(s, row, col + 25, avgFeatures);
			st.cell(s, row, col + 26, avgCompRefs);

			row++;
		}
		
	}
	
}
