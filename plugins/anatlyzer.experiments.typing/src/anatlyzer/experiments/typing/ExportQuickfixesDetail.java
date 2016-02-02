package anatlyzer.experiments.typing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

			exportQuickfixImpact(wb, ev, folder, "Generated problems", true);
			exportQuickfixImpact(wb, ev, folder, "Fixed problems", false);

			String name = "qfx_details" + ".xls";		
			File f = folder.getFile(name).getLocation().toFile();		
			wb.write(new FileOutputStream(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	private void exportQuickfixImpact(Workbook wb, QuickfixEvaluationAbstract ev, IFolder folder, String name, boolean exportGenerated) {
		Sheet sheet = wb.createSheet(name);
		Styler   st = new Styler(wb);
		
		HashMap<String, QuickfixSummary> summary = ev.summary;
		
		System.out.println(name);
		
		int startRow = 2;
		int startCol = 1;
		
		List<String> codes = QuickfixEvaluationAbstract.getQfxCodes().stream().map(c -> c.code).
				distinct().
				sorted((k1, k2) -> k1.compareTo(k2)).
				collect(Collectors.toList());

		List<AppliedQuickfixInfo> allAppliedQuickfixes = new ArrayList<QuickfixEvaluationAbstract.AppliedQuickfixInfo>();
		Set<String> errCodes = new HashSet<String>();
		List<AnalysedTransformation> trafos = ev.projects.values().stream().flatMap(p -> p.getTrafos().stream()).collect(Collectors.toList());
		trafos.stream().flatMap(t -> t.problemToQuickfix.values().stream()).forEach(qilist -> {
			allAppliedQuickfixes.addAll(qilist);
			qilist.forEach(qi -> {
				if ( exportGenerated )
					errCodes.addAll( qi.getImpact().getNewProblems().stream().map(p -> ev.getErrorCode(p)).collect(Collectors.toList()) );
				else
					errCodes.addAll( qi.getImpact().getFixedProblems().stream().map(p -> ev.getErrorCode(p)).collect(Collectors.toList()) );					
			});
		});
				
		// flatMap(qilist -> qilist.stream().flatMap(qi -> qi.getImpact().getNewProblems().stream().map(p -> ev.getErrorCode(p))))).collect(Collectors.toList());
		
		int col = 1;
				
		for (String errorCode : errCodes) {
//			String errorCode = entry.getKey();
//			QuickfixSummary qs = entry.getValue();
			
			st.cell(sheet, startRow + 0, startCol + col, errorCode);
			
			int row = 1;
			for (String code : codes) {
				// This will write the row header many times, but does not matter
				st.cell(sheet, startRow + row, startCol + 0, code);
								
				for (AppliedQuickfixInfo qi : allAppliedQuickfixes) {
					if ( ! qi.getCode().equals(code) ) 
						continue;
					
					Collection<Problem> list = null;
					if ( exportGenerated ) {
						list = qi.getImpact().getNewProblems();
					} else {
						list = qi.getImpact().getFixedProblems();
					}
				
					long total = list.stream().filter(p -> { 
						return ev.getErrorCode(p).equals(errorCode);						
					}).count();

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
		
	}

}
