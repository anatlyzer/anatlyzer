package anatlyzer.experiments.typing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.errors.Problem;
import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.QuickfixEvaluationAbstract.AnalysedTransformation;
import anatlyzer.experiments.typing.QuickfixEvaluationAbstract.AppliedQuickfixInfo;
import anatlyzer.experiments.typing.QuickfixEvaluationAbstract.QuickfixSummary;

public class ExportQuickfixesDetail implements IExperimentAction {

	public ExportQuickfixesDetail() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IExperiment experiment, IFile confFile) {
		QuickfixEvaluationAbstract ev = (QuickfixEvaluationAbstract) experiment;

		IFolder folder = confFile.getProject().getFolder(confFile.getFullPath().removeFirstSegments(1).removeLastSegments(1).append("reports"));
		if ( ! folder.exists() ) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
				return;
			}
		}

		try {
			Workbook wb = new HSSFWorkbook();

			exportQuickfixImpact(wb, ev, folder, "Generated problems (all)", true, true);
			exportQuickfixImpact(wb, ev, folder, "Fixed problems (all)", false, true);

			exportQuickfixImpact(wb, ev, folder, "Generated problems (unique)", true, false);
			exportQuickfixImpact(wb, ev, folder, "Fixed problems (unique)", false, false);

			
			String name = "qfx_details" + ".xls";		
			File f = folder.getFile(name).getLocation().toFile();		
			wb.write(new FileOutputStream(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// The summary table
		try {
			Workbook wb = new HSSFWorkbook();

			exportSummaryTable(wb, ev);

			String name = "qfx_summary" + ".xls";		
			File f = folder.getFile(name).getLocation().toFile();		
			wb.write(new FileOutputStream(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void exportSummaryTable(Workbook wb, QuickfixEvaluationAbstract ev) {
		Sheet sheet = wb.createSheet("Summary");
		Styler   st = new Styler(wb);

		int startRow = 2;
		int startCol = 1;

		st.cell(sheet, startRow, startCol + 0, "Problem");
		st.cell(sheet, startRow, startCol + 1, "# Occ.");
		st.cell(sheet, startRow, startCol + 2, "# Qfx.");
		st.cell(sheet, startRow, startCol + 3, "Avg.");
		st.cell(sheet, startRow, startCol + 4, "Min");
		st.cell(sheet, startRow, startCol + 5, "Max");
		st.cell(sheet, startRow, startCol + 6, "Valid");
		st.cell(sheet, startRow, startCol + 7, "Fix.");
		st.cell(sheet, startRow, startCol + 8, "Gen.");

		int row = startRow++;
		List<QuickfixSummary> l = ev.summary.values().stream().sorted((q1, q2) -> q1.getLatexDesc().compareTo(q2.getLatexDesc())).collect(Collectors.toList());
		for (QuickfixSummary qs : l) {
			st.cell(sheet, row, startCol + 0, qs.getLatexDesc()).bold();
			st.cell(sheet, row, startCol + 1, (long) qs.totalProblems);
			st.cell(sheet, row, startCol + 2, (long) qs.totalQuickfixes);
			st.cell(sheet, row, startCol + 3, (long) qs.getAvg());
			st.cell(sheet, row, startCol + 4, (long) qs.minQuickfixes);
			st.cell(sheet, row, startCol + 5, (long) qs.maxQuickfixes);
			st.cell(sheet, row, startCol + 6, (long) qs.totalValidQuickfixes);
			st.cell(sheet, row, startCol + 7, (long) qs.totalErrorsFixed);
			st.cell(sheet, row, startCol + 8, (long) qs.totalErrorsGenerated );

			row++;
			List<String> applied = qs.quickfixesByType.keySet().stream().sorted((k1, k2) -> k1.compareTo(k2)).collect(Collectors.toList());
			for (String k : applied) {
				List<AppliedQuickfixInfo> list = qs.quickfixesByType.get(k);				
				int totalQuickfix = list.size();
				
				int valid     = list.stream().mapToInt(qi -> qi.isValid() ? 1 : 0).sum();
				int fixed     = list.stream().mapToInt(qi -> qi.getNumFixedProblems()).sum();
				int generated = list.stream().mapToInt(qi -> qi.getNumNewProblems()).sum();

				st.cell(sheet, row, startCol + 0, converToSortable(k)).alignRight();
				st.cell(sheet, row, startCol + 1, "-");
				st.cell(sheet, row, startCol + 2, totalQuickfix);
				st.cell(sheet, row, startCol + 3, "-");
				st.cell(sheet, row, startCol + 4, "-");
				st.cell(sheet, row, startCol + 5, "-");
				st.cell(sheet, row, startCol + 6, valid);
				st.cell(sheet, row, startCol + 7, fixed);
				st.cell(sheet, row, startCol + 8, generated);
				row++;
			}
			
		}
		
		
	}

	private void exportQuickfixImpact(Workbook wb, QuickfixEvaluationAbstract ev, IFolder folder, String name, boolean exportGenerated, boolean countAll) {
		Sheet sheet = wb.createSheet(name);
		Styler   st = new Styler(wb);
		
		HashMap<String, QuickfixSummary> summary = ev.summary;
		
		System.out.println(name);
		
		int startRow = 2;
		int startCol = 1;
		
		List<String> qfxCodes = QuickfixCodes.getQfxCodes().stream().map(c -> c.code).
				distinct().
				sorted((k1, k2) -> k1.compareTo(k2)).
				collect(Collectors.toList());

		List<AppliedQuickfixInfo> allAppliedQuickfixes = new ArrayList<QuickfixEvaluationAbstract.AppliedQuickfixInfo>();
		Set<String> errCodesSet = new HashSet<String>();
		List<AnalysedTransformation> trafos = ev.projects.values().stream().flatMap(p -> p.getTrafos().stream()).collect(Collectors.toList());
		trafos.stream().flatMap(t -> t.problemToQuickfix.values().stream()).forEach(qilist -> {
			allAppliedQuickfixes.addAll(qilist);
			qilist.forEach(qi -> {
				if ( exportGenerated ) {
					errCodesSet.addAll( qi.getImpact().getNewProblems().stream().map(p -> QuickfixCodes.getErrorCode(p)).collect(Collectors.toList()) );
				} else {
					errCodesSet.addAll( qi.getImpact().getFixedProblems().stream().map(p -> QuickfixCodes.getErrorCode(p)).collect(Collectors.toList()) );					
				}	
			});
		});
				
		
		
		// flatMap(qilist -> qilist.stream().flatMap(qi -> qi.getImpact().getNewProblems().stream().map(p -> ev.getErrorCode(p))))).collect(Collectors.toList());
		
		
		int col = 2; // One hole for the quickfix name and the other for the number of applications
		
		List<String> errCodeOrdered = QuickfixCodes.getErrorCodes().stream().map(c -> c.code).
				distinct().
				sorted((k1, k2) -> k1.compareTo(k2)).
				collect(Collectors.toList());
		
		
		// List<String> errCodeOrdered = errCodesSet.stream().sorted((k1, k2) -> k1.compareTo(k2)).collect(Collectors.toList());
		for (String errorCode : errCodeOrdered) {
//			String errorCode = entry.getKey();
//			QuickfixSummary qs = entry.getValue();
			
			st.cell(sheet, startRow + 0, startCol + col, errorCode);
			
			int row = 1;
			for (String qfxCode : qfxCodes) {
				// This will write the row header many times, but does not matter
				st.cell(sheet, startRow + row, startCol + 0, converToSortable(qfxCode));
								
				for (AppliedQuickfixInfo qi : allAppliedQuickfixes) {
					if ( ! qi.getCode().equals(qfxCode) ) 
						continue;
					
					Collection<Problem> list = null;
					if ( exportGenerated ) {
						list = qi.getImpact().getNewProblems();
					} else {
						list = qi.getImpact().getFixedProblems();
					}
				
					long total = 0;
					
					if ( countAll ) {					
						total = list.stream().filter(p -> { 
							return QuickfixCodes.getErrorCode(p).equals(errorCode);						
						}).count();
					} else {
						total = list.stream().anyMatch(p -> { 
							return QuickfixCodes.getErrorCode(p).equals(errorCode);						
						}) ? 1 : 0;
					}

					Cell c = st.getRow(sheet, startRow + row).getCell(startCol + col);
					long value = total;

					System.out.println("[" + startRow + row + "," + startCol + col + "] = " + value);
					
					if ( c != null ) {
						value = total + (long) c.getNumericCellValue();
					}
					
					st.cell(sheet, startRow + row, startCol + col, value);					
				}
				
				row++;
			}
			
			col++;
		}

		// Fill the number of applications of each quickfix
		int row = 1;
		for (String qfxCode : qfxCodes) {					
			for (AppliedQuickfixInfo qi : allAppliedQuickfixes) {
				if ( ! qi.getCode().equals(qfxCode) ) 
					continue;
		
				Cell numberOfApplicationsCell = st.getRow(sheet, startRow + row).getCell(startCol + 1);
				long numberOfApplicationsValue = 1;
				if ( numberOfApplicationsCell != null ) {
					numberOfApplicationsValue = numberOfApplicationsValue + (long) numberOfApplicationsCell.getNumericCellValue();
				}
				st.cell(sheet, startRow + row, startCol + 1, numberOfApplicationsValue);					
			}		
			row++;
		}		
		
	}

	private String converToSortable(String quickfixCode) {
		String[] text = quickfixCode.substring(1).split(".");
		if ( text.length != 2 ) 
			return quickfixCode;
		if ( text[0].length() == 1 ) 
			text[0] = "0" + text[0];
		return "Q" + text[0] + "." + text[1];
	}

}
