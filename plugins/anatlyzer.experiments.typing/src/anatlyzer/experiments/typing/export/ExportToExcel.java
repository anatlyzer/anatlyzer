package anatlyzer.experiments.typing.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.experiments.export.Category;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.IClassifiedArtefact;
import anatlyzer.experiments.export.IHint;
import anatlyzer.experiments.export.SimpleHint;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.typing.raw.TEData;
import anatlyzer.experiments.typing.raw.TEProblem;
import anatlyzer.experiments.typing.raw.TETransformation;

public class ExportToExcel {
	private TEData data;

	public ExportToExcel(TEData data) {
		this.data = data;
		summarizeData();
	}
	
	
	protected CountingModel<ExpDetectedError> counting = new CountingModel<ExpDetectedError>();
	protected CountingModel<ExpDetectedError> byKind = new CountingModel<ExpDetectedError>();
	protected CountingModel<ExpDetectedError> bySeverity = new CountingModel<ExpDetectedError>();	
	
	protected void summarizeData() {
		
		for(CountingModel<?> c : new CountingModel<?>[] { counting, byKind, bySeverity }) {
			c.setRepetitions(true);
			c.showRepetitionDetails(false);
			c.showCategoryDescriptions(true);
		}
		
		for (TETransformation trafo : data.getAllTransformations()) {
			// TODO: There may be name repetitions...
			String fileName = trafo.getName();
			counting.processingArtefact(fileName);
			
			for (TEProblem problem : trafo.getProblems()) {
				int errorCode = problem.getProblemId();
				String desc   = problem.getProblemTypeDescription();				
			
				ExpDetectedError e = new ExpDetectedError(errorCode, fileName, problem);
				e.setProblemsInPath(problem.isDependent());
				
				summarizeError(problem);
				
				if ( problem.isFinallyConfirmed() ) {
					counting.addByCategory(new ExpErrorCategory(errorCode, desc), e);
					
					String severity = problem.getSeverity();
					String kind     = problem.getKind();
										
					bySeverity.addByCategory(new anatlyzer.experiments.export.Category(severity), e);
					byKind.addByCategory(new anatlyzer.experiments.export.Category(kind), e);
				}		
				
			}
			
		}
	}
	
	
	
	// Classes for the counting model
	public class ExpDetectedError implements IClassifiedArtefact {
		private int errorCode;
		private String fileName;
		private TEProblem problem;
		private boolean problemsInPath;
		
		public ExpDetectedError(int errorCode, String fileName, TEProblem p) {
			this.errorCode = errorCode;
			this.fileName  = fileName;
			this.problem   = p;
		}
		
		public void setProblemsInPath(boolean b) {
			this.problemsInPath = b;
		}

		public boolean hasProblemsInPath() {
			return this.problemsInPath;
		}
		
		public TEProblem getProblem() {
			return problem;
		}

		//
		// IArtefact methods
		//
		
		@Override
		public String getId() {
			return getName();
		}

		@Override
		public String getName() {
			return fileName;
		}

		@Override
		public List<IHint> getHints() {
			ArrayList<IHint> hints = new ArrayList<IHint>();
			hints.add(new SimpleHint(problem.getLocation()));
			hints.add(new SimpleHint(problem.getDescription()));			
			if ( AnalyserUtils.isErrorStatus(problem.getFinalStatus()) ) {
				hints.add(new SimpleHint(problemsInPath ? "Prob. path" : ""));
				hints.add(new SimpleHint(problem.getFinalStatus().getName()));
			}
			return hints;
		}
	}
	
	public class ExpErrorCategory extends anatlyzer.experiments.export.Category {

		private int errorCode;
		private String description;

		public ExpErrorCategory(int errorCode, String description) {
			super(errorCode + "");
			this.errorCode = errorCode;
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
		
		@Override
		public int compareTo(Category o) {
			return Integer.compare(errorCode, ((ExpErrorCategory) o).errorCode);
		}		
	}
	
	public void exportToExcel(IFolder folder, IFile experimentFile) throws IOException {
		
		counting.toExcel(folder.getFile( experimentFile.getName().replace(".exp", "_byError.xlsx")).getLocation().toOSString() );
		byKind.toExcel( folder.getFile( experimentFile.getName().replace(".exp", "_byKind.xlsx")).getLocation().toOSString());
		bySeverity.toExcel( folder.getFile( experimentFile.getName().replace(".exp", "_bySeverity.xlsx")).getLocation().toOSString());

		counting.toLatex( folder.getFile( experimentFile.getName().replace(".exp", "byError.tex")).getLocation().toOSString());
		byKind.toLatex( folder.getFile( experimentFile.getName().replace(".exp", "_byKind.tex")).getLocation().toOSString());
		bySeverity.toLatex( folder.getFile( experimentFile.getName().replace(".exp", "_bySeverity.tex")).getLocation().toOSString());		

		List<TEProblem> allProblems = data.getAllTransformations().stream().flatMap(t -> t.getProblems().stream()).collect(Collectors.toList());	
		try {
			Workbook wb = new HSSFWorkbook();

			exportErrorOcurrences(wb, errorOcurrences, folder);
			exportSolverErrorDetails(wb, allProblems, folder);

			String name = "problems_summary" + ".xls";		
			File f = folder.getFile(name).getLocation().toFile();		
			wb.write(new FileOutputStream(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		MessageDialog.openInformation(null, "Finished", "Exported files to " + folder.getFullPath().toPortableString());
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
		st.cell(sheet, startRow, startCol + 4, "Solver");		
		st.cell(sheet, startRow, startCol + 5, "C");
		st.cell(sheet, startRow, startCol + 6, "D");
		st.cell(sheet, startRow, startCol + 7, "DM");
		st.cell(sheet, startRow, startCol + 8, "Unknown");
		
		st.cell(sheet, startRow, startCol + 9, "E1 - USE");
		st.cell(sheet, startRow, startCol + 10, "E2 - Impl.");
		st.cell(sheet, startRow, startCol + 11, "E3 - Unsupp.");
		st.cell(sheet, startRow, startCol + 12, "Path prob.");
		st.cell(sheet, startRow, startCol + 13, "Path rec.");

		startRow++;
		for(int i = 0; i < allIds.size(); i++) {
			int id = allIds.get(i);
			ErrorCount count = errorOcurrences.get(id);
						
			st.cell(sheet, startRow + i, startCol + 0, (long) id);
			st.cell(sheet, startRow + i, startCol + 1, count.desc);
			st.cell(sheet, startRow + i, startCol + 2, count.ocurrences);
			st.cell(sheet, startRow + i, startCol + 3, count.staticallyConfirmed);
			st.cell(sheet, startRow + i, startCol + 4, count.ocurrences - count.staticallyConfirmed);
			st.cell(sheet, startRow + i, startCol + 5, count.witnessConfirmed);
			st.cell(sheet, startRow + i, startCol + 6, count.witnessDiscarded);
			st.cell(sheet, startRow + i, startCol + 7, count.witnessDiscardedMetamodel);

			st.cell(sheet, startRow + i, startCol + 8, 
					(count.ocurrences - count.staticallyConfirmed) - (count.witnessConfirmed + count.witnessDiscarded + count.witnessDiscardedMetamodel));

			st.cell(sheet, startRow + i, startCol + 9, count.e1_use);
			st.cell(sheet, startRow + i, startCol + 10, count.e2_impl);
			st.cell(sheet, startRow + i, startCol + 11, count.e3_unsupp);
			st.cell(sheet, startRow + i, startCol + 12, count.problemsInPath);
			st.cell(sheet, startRow + i, startCol + 13, count.problemsInPathRecovered);
			
			// TODO: Count the number of problems with errors in the paths
			//       How many are recovered
			//       How many we get an error
		}

	}

	private void exportSolverErrorDetails(Workbook wb, List<TEProblem> allProblems, IFolder folder) {
		Sheet sheet = wb.createSheet("Errors detail");
		Styler   st = new Styler(wb);

		int startRow = 2;
		int startCol = 1;

		st.cell(sheet, startRow, startCol + 0, "Problem Id");
		st.cell(sheet, startRow, startCol + 1, "Location");
		st.cell(sheet, startRow, startCol + 2, "Status");
		st.cell(sheet, startRow, startCol + 3, "Prob. path");
		
		startRow++;
		
		Map<Integer, List<TEProblem>> result = allProblems.stream().filter(e -> AnalyserUtils.isErrorStatus( e.getFinalStatus() )).collect(Collectors.groupingBy(e -> e.getProblemId() ));
		for (Integer id : result.keySet().stream().sorted().collect(Collectors.toList())) {
			st.cell(sheet, startRow, startCol + 0, id);

			for (TEProblem error : result.get(id)) {
				startRow++;

				TETransformation trafo = findTrafo(error);
				
				String location = trafo.getPath() + " " + error.getLocation(); //((error.getProblem() instanceof LocalProblem) ? " " + ((LocalProblem) error.getProblem()).getLocation() : "");
				
				st.cell(sheet, startRow, startCol + 1, location);				
				st.cell(sheet, startRow, startCol + 2, error.getFinalStatus().getName());
				st.cell(sheet, startRow, startCol + 3, error.isDependent() ? "Yes" : "No");				
			}			
		}
	}

	
	private TETransformation findTrafo(TEProblem p) {
		return data.getAllTransformations().stream().
				filter(t -> t.getProblems().contains(p)).
				findFirst().
				orElseThrow(() -> new IllegalStateException());
	}


	// 
	// Pre-compute stats 
	//
	
	protected HashMap<Integer, ErrorCount> errorOcurrences = new HashMap<Integer, ErrorCount>();
	
	
	protected void summarizeError(TEProblem p) {
		String desc   				= p.getProblemTypeDescription();
		int errorCode 				= p.getProblemId();
		boolean hasProblemsInPath 	= p.isDependent();

		errorOcurrences.putIfAbsent(errorCode, new ErrorCount(errorCode, desc));
		ErrorCount c = errorOcurrences.get(errorCode);
		c.ocurrences++;
		
		if ( hasProblemsInPath && p.getFinalStatus() != ProblemStatus.STATICALLY_CONFIRMED )
			c.problemsInPath++;		
		
		switch ( p.getFinalStatus() ) {
		case STATICALLY_CONFIRMED:
			c.staticallyConfirmed++;
			break;
		case ERROR_CONFIRMED:
		case ERROR_CONFIRMED_SPECULATIVE:
			c.witnessConfirmed++;
			if ( hasProblemsInPath ) c.problemsInPathRecovered++;		
			break;
		case ERROR_DISCARDED:
			if ( hasProblemsInPath ) c.problemsInPathRecovered++;		
			c.witnessDiscarded++;
			break;
		case ERROR_DISCARDED_DUE_TO_METAMODEL:
			if ( hasProblemsInPath ) c.problemsInPathRecovered++;
			c.witnessDiscardedMetamodel++;
			break;
		case USE_INTERNAL_ERROR:
			c.e1_use++;
			break;
		case IMPL_INTERNAL_ERROR:
			c.e2_impl++;
			break;
		case NOT_SUPPORTED_BY_USE:
			c.e3_unsupp++;
			break;
		case WITNESS_REQUIRED:
			//if ( useCSP() ) 
				throw new IllegalStateException();
			//break;
		case PROBLEMS_IN_PATH:
		case CANNOT_DETERMINE:
			throw new IllegalStateException();
		}
	}

	public class ErrorCount {
		int id;
		String desc;
		public ErrorCount(int id, String desc) {
			this.id = id;
			this.desc = desc;
		}
		
		int problemsInPath;
		int problemsInPathRecovered;
		
		int ocurrences;
		int staticallyConfirmed;
		int witnessConfirmed;
		int witnessDiscarded;
		int witnessDiscardedMetamodel;
		int e1_use;
		int e2_impl;
		int e3_unsupp;
	}
	
}
