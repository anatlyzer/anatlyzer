package anatlyzer.experiments.typing.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.experiments.export.Category;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.IClassifiedArtefact;
import anatlyzer.experiments.export.IHint;
import anatlyzer.experiments.export.SimpleHint;
import anatlyzer.experiments.typing.ExpProblem;
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

	
	public void exportToExcel(String file) throws IOException {
		counting.toExcel(file.replace(".xlsx", "_byError.xlsx"));
		byKind.toExcel(file.replace(".xlsx", "_byKind.xlsx"));
		bySeverity.toExcel(file.replace(".xlsx", "_bySeverity.xlsx"));

		counting.toLatex(file.replace(".xlsx", "byError.tex"));
		byKind.toLatex(file.replace(".xlsx", "_byKind.tex"));
		bySeverity.toLatex(file.replace(".xlsx", "_bySeverity.tex"));		
	}	
}
