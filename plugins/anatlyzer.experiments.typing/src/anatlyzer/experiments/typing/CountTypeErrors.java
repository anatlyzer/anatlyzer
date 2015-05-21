package anatlyzer.experiments.typing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import transML.utils.transMLProperties;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.witness.EclipseUseWitnessFinder;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ErrorReport;
import anatlyzer.experiments.export.Category;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.IClassifiedArtefact;
import anatlyzer.experiments.export.IHint;
import anatlyzer.experiments.extensions.IExperiment;

public class CountTypeErrors extends AbstractATLExperiment implements IExperiment {

	List<AnalyserData> allData = new ArrayList<AnalyserData>();
	private CountingModel<DetectedError> counting = new CountingModel<DetectedError>();
	
	public CountTypeErrors() {
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);
		counting.showCategoryDescriptions(true);
	}

	@Override
	public void perform(IResource resource) {
		AnalyserData data;
		try {
			data = executeAnalyser(resource);
			if ( data == null )
				return;

			allData.add(data);
		
			if ( useCSP() ) {
				System.out.println("REQUIRE!!");
			}
			
			String fileName = resource.getName();
			counting.processingArtefact(fileName);
			
			for(Problem p : data.getProblems()) {
				int errorCode = AnalyserUtils.getProblemId(p);
				DetectedError e = new DetectedError(errorCode, fileName);
				counting.addByCategory(new ErrorCategory(errorCode, AnalyserUtils.getProblemDescription(p)), e);
			}
			
		} catch (Exception e) {
			counting.addError(resource.getName(), e);
			e.printStackTrace();
		} 
	}

	private List<Problem> selectProblems(List<Problem> problems, AnalysisResult r) {
		ArrayList<Problem> allProblems = new ArrayList<Problem>();
		for (Problem p : problems) {
			if ( requireCSP(p) ) {
				removePropertiesFile();
				
				ProblemStatus result = new EclipseUseWitnessFinder().			
						// checkProblemsInPath(checkProblemsInPath ).
						checkDiscardCause(false).find(p, r);

				
				switch (result) {
				case ERROR_CONFIRMED:
				case ERROR_CONFIRMED_SPECULATIVE:
					// that's fine						
					// printMessage("Confirmed: " + ((LocalProblem) p).getLocation());
					allProblems.add(p);
					break;
				case ERROR_DISCARDED:
				case ERROR_DISCARDED_DUE_TO_METAMODEL:
					// printMessage("Discarded: " + ((LocalProblem) p).getLocation());
					continue;
				case CANNOT_DETERMINE:
				case INTERNAL_ERROR:
				case NOT_SUPPORTED_BY_USE:
					// printMessage("USE ERROR: " + (isLocal(p) ? ((LocalProblem) p).getLocation() : "") + ", " + ((LocalProblem) p).getFileLocation());
					continue;
				case PROBLEMS_IN_PATH:
					// printMessage("Problems in path for: " + ((LocalProblem) p).getLocation() + ", " + ((LocalProblem) p).getFileLocation());
					continue;					
				}
			} else {
				allProblems.add(p);
			}
		}
		return allProblems;
	}

	
	private boolean requireCSP(Problem p) {
		// TODO Auto-generated method stub
		return false;
	}

	private void removePropertiesFile() {
		try {
			transMLProperties.loadPropertiesFile(new EclipseUseWitnessFinder().getTempDirectory());					
			File dir = new File(transMLProperties.getProperty("temp"));
			FileUtils.deleteDirectory(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private boolean useCSP() {
		return this.options.containsKey("use_model_finder") &&
			(Boolean.parseBoolean((String) this.options.get("use_model_finder")));
	}

	@Override
	public void printResult(PrintStream out) {
		
		out.println("Transformations - Detail");
		for(AnalyserData data : allData) {
			out.println(data.getAnalyser().getATLModel().getFileLocations().get(0));
			
			Analyser analyser = data.getAnalyser();
			
			ByteArrayOutputStream outS = new ByteArrayOutputStream();
			String[] fileLocations = new String[analyser.getATLModel().getFileLocations().size()];
			int i = 0;
			for (String eclipseLocation : analyser.getATLModel().getFileLocations()) {
				fileLocations[i] = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(eclipseLocation)).getRawLocation().toPortableString();			
				i++;
			}
			ErrorReport.printStatistics(data.getAnalyser(), fileLocations, outS);
			ErrorReport.printErrorsByType(data.getAnalyser(), outS);
			
			out.print(outS.toString());
			
			// out.println(data.getProblems())
		}
		
		out.println();
		out.println("------------------------------------");
		out.println();
		counting.printResult(out);
	}

	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String file) throws IOException {
		counting.toExcel(file);
	}

	public class DetectedError implements IClassifiedArtefact {
		private int errorCode;
		private String fileName;
		
		public DetectedError(int errorCode, String fileName) {
			this.errorCode = errorCode;
			this.fileName  = fileName;
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
			return hints;
		}
	}
	
	public class ErrorCategory extends anatlyzer.experiments.export.Category {

		private int errorCode;
		private String description;

		public ErrorCategory(int errorCode, String description) {
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
			return Integer.compare(errorCode, ((ErrorCategory) o).errorCode);
		}
		
	}

	
}
