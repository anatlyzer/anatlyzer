package anatlyzer.experiments.typing;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.CountTypeErrors.Project;

public class ExportTestFile implements IExperimentAction {

	public ExportTestFile() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IExperiment experiment, IFile confFile) {
		CountTypeErrors exp = (CountTypeErrors) experiment;
			
//		IProject project = confFile.getProject();
//		IFolder folder = project.getFolder("details");
		IFolder folder = confFile.getProject().getFolder(confFile.getFullPath().removeFirstSegments(1).removeLastSegments(1).append("tests"));
		if ( ! folder.exists() ) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
				return;
			}
		}

		try {
			int day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			int minute = Calendar.getInstance().get(Calendar.MINUTE);
			String name = "test_" + day + "_" + hour + "_" + minute + ".txt";		
			File f = folder.getFile(name).getLocation().toFile();	
			PrintStream out = new PrintStream(f);
			
			exp.projects.keySet().stream().sorted().forEach(pname -> {
				Project p = exp.projects.get(pname);
				try {
					exportProject(p, out);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void exportProject(Project project, PrintStream out) throws IOException {
		out.println("Project: " + project.getName());
		List<ExpAnalyserData> trafos = project.getTrafos().stream().
			sorted((d1, d2) -> d1.getMainFileLocation().compareTo(d2.getMainFileLocation())).
			collect(Collectors.toList());
		
		for (ExpAnalyserData analyserData : trafos) {
			String trafoName = getTrafoName(analyserData.getMainFileLocation());
			out.println("  Transformation: " + trafoName);

			analyserData.getProblems().stream().sorted((p1, p2) -> p1.getLocation().compareTo(p2.getLocation())).forEach(p -> {
				out.println("    " + p.getLocation() + ": "+ p.getProblemTypeDescription());
			});
		}
	}

	private String getProblemLocation(Problem p) {
		return ((p instanceof LocalProblem) ? " " + ((LocalProblem) p).getLocation() : "");		
	}

	protected String getTrafoName(String mainFileLocation) {
		return new File(mainFileLocation).getName().replace(".atl", "");
	}


}
