package anatlyzer.experiments.performance.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import anatlyzer.experiments.export.ExcelUtil;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.performance.MeasurePerformance;
import anatlyzer.experiments.performance.raw.PEData;
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
	

		// Save
		FileOutputStream fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		wb.close();
		fileOut.close();           
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
		st.cell(s, initRow, col + 12, "Path. gen").centeringBold();
		st.cell(s, initRow, col + 13, "Cond. gen").centeringBold();
		st.cell(s, initRow, col + 14, "Error MM").centeringBold();
		st.cell(s, initRow, col + 15, "Eff.  MM").centeringBold();
		st.cell(s, initRow, col + 16, "Solver").centeringBold();

		st.cell(s, initRow, col + 17, "Max. solver").centeringBold();
		st.cell(s, initRow, col + 18, "Min. solver").centeringBold();
		st.cell(s, initRow, col + 19, "Median solver").centeringBold();

		
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
			
			
			st.cell(s, row, col + 11, numProblems);
			st.cell(s, row, col + 12, createPath);
			st.cell(s, row, col + 13, condGen);
			st.cell(s, row, col + 14, errorMM);
			st.cell(s, row, col + 15, effMM);
			st.cell(s, row, col + 16, solver);
			st.cell(s, row, col + 17, maxSolver);
			st.cell(s, row, col + 18, minSolver);
			st.cell(s, row, col + 19, medianSolver);

			row++;
		}
		
	}
	
}
