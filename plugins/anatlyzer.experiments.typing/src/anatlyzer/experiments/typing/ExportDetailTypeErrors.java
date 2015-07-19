package anatlyzer.experiments.typing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ErrorReport;
import anatlyzer.atl.util.ErrorReport.Report;
import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.CountTypeErrors.Project;
import anatlyzer.experiments.typing.CountTypeErrors.RuleConflictResult;

public class ExportDetailTypeErrors implements IExperimentAction {

	public ExportDetailTypeErrors() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IExperiment experiment, IFile confFile) {
		CountTypeErrors exp = (CountTypeErrors) experiment;
			
//		IProject project = confFile.getProject();
//		IFolder folder = project.getFolder("details");
		IFolder folder = confFile.getProject().getFolder(confFile.getFullPath().append("details"));
		if ( ! folder.exists() ) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
				return;
			}
		}
		
		exp.projects.keySet().stream().sorted().forEach(pname -> {
			Project p = exp.projects.get(pname);
			try {
				exportProject(p, folder);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	private void exportProject(Project project, IFolder folder) throws IOException {
		InputStream inputStream = Activator.getDefault().getBundle().getResource("templates/detail.xls").openStream();
		Workbook wb = new HSSFWorkbook(inputStream);
		
		// First sheet: type errors
		Sheet sheet = wb.getSheetAt(0);
		Styler   st = new Styler(wb);

		Map<Integer, String> idToDescription = project.getTrafos().stream().flatMap(a -> a.getProblems().stream()).
			collect(Collectors.toMap(p -> AnalyserUtils.getProblemId(p), p -> AnalyserUtils.getProblemDescription(p), (d1, d2) -> d1));
		
		List<Integer> allIds = idToDescription.keySet().stream().distinct().sorted().collect(Collectors.toList());
		Function<Integer, Integer> idToRow = (id) -> allIds.indexOf(id);

		final int errorsRow = 12;
		
		for(int i = 0; i < allIds.size(); i++) {
			int id = allIds.get(i);
			String desc = idToDescription.get(id);
			
			st.cell(sheet, errorsRow + i, 0, (long) id);
			st.cell(sheet, errorsRow + i, 1, desc);						
		}
		
		
		for (int i = 0; i < project.getTrafos().size(); i++) {
			AnalyserData analyserData = project.getTrafos().get(i);
			export(analyserData, sheet, st, i, idToRow);			
		}

		// Second sheet, rule conflicts
		sheet = wb.getSheetAt(1);
		st = new Styler(wb);
		
		for (int i = 0; i < project.getConflicts().size(); i++) {
			RuleConflictResult conflict = project.getConflicts().get(i);
			exportConflict(conflict, sheet, st, i);			
		}
		
		
		
		String name = project.getName() + ".xls";		
		File f = folder.getFile(name).getLocation().toFile();		
		wb.write(new FileOutputStream(f));
	}

	private void exportConflict(RuleConflictResult info, Sheet sheet, Styler st, int idx) throws IOException {
		String trafoName = info.transformation.getName().replace(".atl", "");
		
		int baseCol   = (idx * 8);
		int startCol  = 4 + baseCol;
		int ruleConflictsRow  = 3;
		
		int overlappings = info.conflict.getConflicts().size();
		int staticallyConfirmed = 0;
		int solverConfirmed     = 0;
		int solverDiscarded     = 0;
		int e1                  = 0;
		
		for (ConflictingRuleSet cs : info.conflict.getConflicts()) {
			OverlappingRules ov = (OverlappingRules) cs.getAnalyserInfo();
			switch ( ov.getAnalysisResult() ) {
			case STATICALLY_CONFIRMED:
				staticallyConfirmed++;
				break;
			case ERROR_CONFIRMED:
			case ERROR_CONFIRMED_SPECULATIVE:
				solverConfirmed++;
				break;
			case ERROR_DISCARDED:
			case ERROR_DISCARDED_DUE_TO_METAMODEL:
				solverDiscarded++;
				break;
			case CANNOT_DETERMINE:
			case IMPL_INTERNAL_ERROR:
			case USE_INTERNAL_ERROR:
			case NOT_SUPPORTED_BY_USE:
				e1++;
				break;
			case PROBLEMS_IN_PATH:
			case WITNESS_REQUIRED:
				throw new IllegalStateException();
			}
		}
		
		st.cell(sheet, ruleConflictsRow + 0, startCol, (long) overlappings);
		st.cell(sheet, ruleConflictsRow + 1, startCol, (long) staticallyConfirmed);
		st.cell(sheet, ruleConflictsRow + 2, startCol, (long) solverConfirmed);
		st.cell(sheet, ruleConflictsRow + 3, startCol, (long) solverDiscarded);
		st.cell(sheet, ruleConflictsRow + 4, startCol, (long) e1);
		
		
	}
	
	private void export(AnalyserData data, Sheet sheet, Styler st, int idx, Function<Integer, Integer> idToRow) throws IOException {
		
		ATLModel atlModel = data.getAnalyser().getATLModel();
		String trafoName = new File(atlModel.getMainFileLocation()).getName().replace(".atl", "");
		
		String[] fileLocations = new String[atlModel.getFileLocations().size()];
		int i = 0;
		for (String eclipseLocation : atlModel.getFileLocations()) {
			fileLocations[i] = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(eclipseLocation)).getRawLocation().toPortableString();			
			i++;
		}

		Report r = ErrorReport.computeStatistics(data.getAnalyser(), fileLocations );
		
		int baseCol   = (idx * 8);
		int startCol  = 4 + baseCol;
		int statsRow  = 3;
		int errorsRow = 12;

		st.cell(sheet, 1, startCol, trafoName);
		
		st.cell(sheet, statsRow + 0, startCol, (long) r.nLOC);
		st.cell(sheet, statsRow + 1, startCol, (long) r.nHelpers);
		st.cell(sheet, statsRow + 2, startCol, (long) r.nMatchedRules);
		st.cell(sheet, statsRow + 3, startCol, (long) r.nLazyRules);
		st.cell(sheet, statsRow + 4, startCol, (long) r.nCalledRules);
		st.cell(sheet, statsRow + 5, startCol, (long) r.nSourceClasses);
		st.cell(sheet, statsRow + 6, startCol, (long) r.nTargetClasses);
		
		HashMap<Integer, ProblemStatistics> grouping = groupProblems(data);
		
		for (int id : grouping.keySet()) {
			int j = idToRow.apply(id);
			ProblemStatistics ps = grouping.get(id);
		
			st.cell(sheet, errorsRow + j, startCol + 0, ps.total);
			if ( ! ps.isStaticPrecision ) {
				st.cell(sheet, errorsRow + j, startCol + 2, (long) ps.confirmed);
				st.cell(sheet, errorsRow + j, startCol + 3, (long) ps.discarded);
				st.cell(sheet, errorsRow + j, startCol + 4, (long) ps.e1);
				st.cell(sheet, errorsRow + j, startCol + 5, (long) ps.e2);
				st.cell(sheet, errorsRow + j, startCol + 6, (long) ps.e3);
				st.cell(sheet, errorsRow + j, startCol + 7, (long) ps.discardedDueMM);				
			}			
		}
		
		
	}

	private HashMap<Integer, ProblemStatistics> groupProblems(AnalyserData data) {
		HashMap<Integer, ProblemStatistics> problemData = new HashMap<Integer, ProblemStatistics>();
		
		for (Problem problem : data.getProblems()) {
			int id = AnalyserUtils.getProblemId(problem);
			ProblemStatistics ps = problemData.computeIfAbsent(id, k -> new ProblemStatistics(k, AnalyserUtils.getProblemDescription(problem), AnalyserUtils.isStaticPrecision(problem)));
			ps.total++;
			
			switch (problem.getStatus()) {
			case STATICALLY_CONFIRMED:
				break;
			case ERROR_CONFIRMED: 
			case ERROR_CONFIRMED_SPECULATIVE:
				ps.confirmed++; break;
			case ERROR_DISCARDED: ps.discarded++; break;
			case ERROR_DISCARDED_DUE_TO_METAMODEL: ps.discardedDueMM++; break;
			case USE_INTERNAL_ERROR: ps.e1++; break;
			case CANNOT_DETERMINE: 
			case IMPL_INTERNAL_ERROR: ps.e2++; break;
			case NOT_SUPPORTED_BY_USE: ps.e3++; break;
			case PROBLEMS_IN_PATH:
			case WITNESS_REQUIRED:
				throw new IllegalStateException();
			}
		}
		
		return problemData;
	}

	public static class ProblemStatistics {
		public int id;
		public String description;
		public int total;
		public int confirmed;
		public int discarded;
		public int discardedDueMM;
		public int e1;
		public int e2;
		public int e3;
		public boolean isStaticPrecision;
		
		public ProblemStatistics(int id, String description, boolean isStaticPrecision) {
			this.id = id;
			this.description = description;
			this.isStaticPrecision = isStaticPrecision;
		}
	}

}
