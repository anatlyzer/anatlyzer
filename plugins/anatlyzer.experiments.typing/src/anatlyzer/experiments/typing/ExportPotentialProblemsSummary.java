package anatlyzer.experiments.typing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.export.SimpleHint;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.CountTypeErrors.DetectedError;
import anatlyzer.experiments.typing.CountTypeErrors.ErrorCount;

public class ExportPotentialProblemsSummary implements IExperimentAction {

	public ExportPotentialProblemsSummary() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IExperiment experiment, IFile confFile) {
		CountTypeErrors exp = (CountTypeErrors) experiment;
			
		// IProject project = confFile.getProject();
		IFolder folder = confFile.getProject().getFolder(confFile.getFullPath().append("reports"));
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

			exportErrorOcurrences(wb, exp.errorOcurrences, folder);
			exportSolverErrorDetails(wb, exp.allProblems, folder);

			String name = "problems_summary" + ".xls";		
			File f = folder.getFile(name).getLocation().toFile();		
			wb.write(new FileOutputStream(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void exportSolverErrorDetails(Workbook wb, List<DetectedError> allProblems, IFolder folder) {
		Sheet sheet = wb.createSheet("Errors detail");
		Styler   st = new Styler(wb);

		int startRow = 2;
		int startCol = 1;

		st.cell(sheet, startRow, startCol + 0, "Problem Id");
		st.cell(sheet, startRow, startCol + 1, "Location");
		st.cell(sheet, startRow, startCol + 2, "Status");
		st.cell(sheet, startRow, startCol + 3, "Prob. path");
		
		startRow++;
		
		Map<Integer, List<DetectedError>> result = allProblems.stream().filter(e -> AnalyserUtils.isInternalError(e.getProblem())).collect(Collectors.groupingBy(e -> AnalyserUtils.getProblemId(e.getProblem())));
		for (Integer id : result.keySet().stream().sorted().collect(Collectors.toList())) {
			st.cell(sheet, startRow, startCol + 0, id);

			for (DetectedError error : result.get(id)) {
				startRow++;

				String location = error.getName() + ((error.getProblem() instanceof LocalProblem) ? " " + ((LocalProblem) error.getProblem()).getLocation() : "");
				
				st.cell(sheet, startRow, startCol + 1, location);				
				st.cell(sheet, startRow, startCol + 2, error.getProblem().getStatus().getName());
				st.cell(sheet, startRow, startCol + 3, error.hasProblemsInPath() ? "Yes" : "No");				
			}			
		}
	}

	private void exportErrorOcurrences(Workbook wb, HashMap<Integer, ErrorCount> errorOcurrences, IFolder folder) throws FileNotFoundException, IOException {
		Sheet sheet = wb.createSheet("Summary");
		Styler   st = new Styler(wb);

		List<Integer> allIds = errorOcurrences.keySet().stream().sorted().collect(Collectors.toList());

		int startRow = 2;
		int startCol = 1;

		st.cell(sheet, startRow, startCol + 0, "Id");
		st.cell(sheet, startRow, startCol + 1, "Description");
		st.cell(sheet, startRow, startCol + 2, "Occ.");
		st.cell(sheet, startRow, startCol + 3, "ST");
		st.cell(sheet, startRow, startCol + 4, "C");
		st.cell(sheet, startRow, startCol + 5, "D");
		st.cell(sheet, startRow, startCol + 6, "DM");
		st.cell(sheet, startRow, startCol + 7, "E1 - USE");
		st.cell(sheet, startRow, startCol + 8, "E2 - Impl.");
		st.cell(sheet, startRow, startCol + 9, "E3 - Unsupp.");
		st.cell(sheet, startRow, startCol + 10, "Path prob.");
		st.cell(sheet, startRow, startCol + 11, "Path rec.");

		startRow++;
		for(int i = 0; i < allIds.size(); i++) {
			int id = allIds.get(i);
			ErrorCount count = errorOcurrences.get(id);
						
			st.cell(sheet, startRow + i, startCol + 0, (long) id);
			st.cell(sheet, startRow + i, startCol + 1, count.desc);
			st.cell(sheet, startRow + i, startCol + 2, count.ocurrences);
			st.cell(sheet, startRow + i, startCol + 3, count.staticallyConfirmed);
			st.cell(sheet, startRow + i, startCol + 4, count.witnessConfirmed);
			st.cell(sheet, startRow + i, startCol + 5, count.witnessDiscarded);
			st.cell(sheet, startRow + i, startCol + 6, count.witnessDiscardedMetamodel);
			st.cell(sheet, startRow + i, startCol + 7, count.e1_use);
			st.cell(sheet, startRow + i, startCol + 8, count.e2_impl);
			st.cell(sheet, startRow + i, startCol + 9, count.e3_unsupp);
			st.cell(sheet, startRow + i, startCol + 10, count.problemsInPath);
			st.cell(sheet, startRow + i, startCol + 11, count.problemsInPathRecovered);
			
			// TODO: Count the number of problems with errors in the paths
			//       How many are recovered
			//       How many we get an error
		}

	}

}
